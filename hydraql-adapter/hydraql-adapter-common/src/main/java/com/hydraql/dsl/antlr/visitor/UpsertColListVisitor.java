package com.hydraql.dsl.antlr.visitor;

import com.hydraql.common.constants.HMHBaseConstants;
import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author leojie 2020/11/27 11:28 下午
 */
public class UpsertColListVisitor extends BaseVisitor<List<HBaseColumn>> {

    public UpsertColListVisitor(HBaseTableSchema tableSchema) {
        super(tableSchema);
    }

    @Override
    public List<HBaseColumn> visitUpsert_column_def_list(HydraQLParser.Upsert_column_def_listContext ctx) {
        if (ctx == null || ctx.isEmpty()) {
            return tableSchema.findAllColumns();
        }
        List<HydraQLParser.Column_refContext> columnRefContexts = ctx.column_ref();
        Map<String, HBaseColumn> columnSchemaMap = tableSchema.getColumnSchemaMap();
        List<HBaseColumn> columns = new ArrayList<>(columnSchemaMap.size());
        for (HydraQLParser.Column_refContext columnRefContext : columnRefContexts) {
            HydraQLParser.Family_nameContext familyNameContext = columnRefContext.family_name();
            HydraQLParser.Column_nameContext columnNameContext = columnRefContext.column_name();
            String family = "";
            String column = "";
            if (familyNameContext != null && !familyNameContext.isEmpty()) {
                family = getText(familyNameContext.name());
            }
            if (columnNameContext != null && !columnNameContext.isEmpty()) {
                column = getText(columnNameContext.name());
            }
            String columnName = HMHBaseConstants.getColumnName(family, column);
            HBaseColumn columnSchema = columnSchemaMap.get(columnName);
            if (columnSchema == null) {
                throw new HBaseSqlAnalysisException(String.format(
                        "The column %s is not exists.", columnRefContext.getText()));
            }
            columns.add(columnSchema);
        }
        return columns;
    }

    public List<HBaseColumn> extractUpsertColumns(HydraQLParser.Upsert_column_def_listContext
                                                         upsertColumnDefListContext) {
        return upsertColumnDefListContext.accept(this);
    }
}
