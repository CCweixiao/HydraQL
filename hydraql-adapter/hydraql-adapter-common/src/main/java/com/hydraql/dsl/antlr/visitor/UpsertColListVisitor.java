package com.hydraql.dsl.antlr.visitor;

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
            HBaseColumn column = this.extractColumn(columnRefContext);
            columns.add(column);
        }
        return columns;
    }

    public List<HBaseColumn> extractUpsertColumns(HydraQLParser.Upsert_column_def_listContext ctx) {
        return ctx.accept(this);
    }
}
