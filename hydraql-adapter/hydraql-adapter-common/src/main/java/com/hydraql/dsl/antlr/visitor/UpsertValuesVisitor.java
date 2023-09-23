package com.hydraql.dsl.antlr.visitor;

import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.util.StringUtil;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.antlr.data.InsertRowData;
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
            throw new HBaseSqlAnalysisException("Insert field list and value list numbers don't match.");
        }
        int rowIndex = getRowIndex();
        HydraQLParser.LiteralContext rowValContext = valueContextList.get(rowIndex);
        // todo 类型
        String rowVal = rowValContext.string().STRING_LITERAL().getText();
        if (StringUtil.isBlank(rowVal)) {
            throw new HBaseSqlAnalysisException("Unable to parse the rowKey value" +
                    " from the list of data to be inserted.");
        }
        String rowValueType = this.findRow().getColumnType().getTypeHandler().extractMatchTtypeValue(rowVal);
        InsertRowData.Builder rowDataBuilder = InsertRowData.of(this.findRow().getColumnType()
                .getTypeHandler().toBytes(rowValueType));
        for (int i = 0; i < valueContextList.size(); i++) {
            if (i == rowIndex) {
                continue;
            }
            HydraQLParser.LiteralContext colValContext = valueContextList.get(i);
            HBaseColumn column = this.upsertColumns.get(i);
            this.tableSchema.columnIsAvailable(column.getFamily(), column.getColumnName());
            //todo 类型
            String colValueOri = colValContext.string().STRING_LITERAL().getText();
            if (StringUtil.isBlank(colValueOri)) {
                if (colValueOri == null && !column.isNullable()) {
                    throw new HBaseSqlAnalysisException(String.format("The value of column %s cannot be empty.", column));
                }
                rowDataBuilder.addColData(column.getFamilyNameBytes(), column.getColumnNameBytes(), new byte[0]);
            } else {
                byte[] valueBytes = column.getColumnType().getTypeHandler().toBytes(column.getColumnType().getTypeClass(),
                        colValueOri);
                rowDataBuilder.addColData(column.getFamilyNameBytes(), column.getColumnNameBytes(), valueBytes);
            }
        }
        return rowDataBuilder.build();
    }

    private int getRowIndex() {
        int rowIndex = -1;
        for (int i = 0; i < upsertColumns.size(); i++) {
            if (upsertColumns.get(i).columnIsRow()) {
                rowIndex = i;
                break;
            }
        }
        if (rowIndex < 0) {
            throw new HBaseSqlAnalysisException("The specified insertion list field does not contain rowKey.");
        }
        return rowIndex;
    }

    public InsertRowData extractInsertRowData(HydraQLParser.Insert_valuesContext insertValuesContext) {
        return insertValuesContext.accept(this);
    }
}
