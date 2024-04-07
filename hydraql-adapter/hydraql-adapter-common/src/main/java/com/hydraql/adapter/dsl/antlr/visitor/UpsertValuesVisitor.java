package com.hydraql.adapter.dsl.antlr.visitor;

import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.util.StringUtil;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.adapter.dsl.antlr.data.InsertRowData;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;

import java.util.List;

/**
 * @author leojie 2023/9/21 21:55
 */
public class UpsertValuesVisitor extends BaseVisitor<InsertRowData> {
    private final List<HBaseColumn> upsertColumns;

    public UpsertValuesVisitor(HBaseTableSchema tableSchema, List<HBaseColumn> upsertColumns) {
        super(tableSchema);
        this.upsertColumns = upsertColumns;
    }

    @Override
    public InsertRowData visitInsert_values(HydraQLParser.Insert_valuesContext ctx) {
        List<HydraQLParser.LiteralContext> valueContextList = ctx.literal();
        if (valueContextList.size() != this.upsertColumns.size()) {
            throw new HBaseSqlAnalysisException(
                    "The number of fields to be inserted and the number of values must match.");
        }
        int rowIndex = getRowIndex();
        HydraQLParser.LiteralContext rowValContext = valueContextList.get(rowIndex);
        String rowVal = this.extractLiteralVal(rowValContext);
        if (StringUtil.isBlank(rowVal)) {
            throw new HBaseSqlAnalysisException("The value of rowKey to be inserted cannot be empty.");
        }
        InsertRowData.Builder rowDataBuilder = InsertRowData.of(this.findRow().getColumnType()
                .getTypeHandler().toBytes(rowVal));
        for (int i = 0; i < valueContextList.size(); i++) {
            if (i == rowIndex) {
                continue;
            }
            HydraQLParser.LiteralContext colValContext = valueContextList.get(i);
            HBaseColumn column = this.upsertColumns.get(i);
            String colValue = this.extractLiteralVal(colValContext);
            if (colValue == null && !column.isNullable()) {
                throw new HBaseSqlAnalysisException(
                        String.format("The value of field %s to be inserted cannot be empty.", column));
            }
            if (colValue == null) {
                rowDataBuilder.addColData(column.getFamilyNameBytes(), column.getColumnNameBytes(), new byte[0]);
            } else {
                byte[] valueBytes = column.getColumnType().getTypeHandler()
                        .toBytes(column.getColumnType().getTypeClass(), colValue);
                rowDataBuilder.addColData(column.getFamilyNameBytes(), column.getColumnNameBytes(), valueBytes);
            }
        }
        return rowDataBuilder.build();
    }

    private int getRowIndex() {
        for (int i = 0; i < upsertColumns.size(); i++) {
            if (upsertColumns.get(i).columnIsRow()) {
                return i;
            }
        }
        throw new HBaseSqlAnalysisException("The list of fields to be inserted must contain rowKey.");
    }

    public InsertRowData extractInsertRowData(HydraQLParser.Insert_valuesContext insertValuesContext) {
        return insertValuesContext.accept(this);
    }
}
