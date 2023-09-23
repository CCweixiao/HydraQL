package com.hydraql.dsl.antlr.visitor;

import com.hydraql.dsl.antlr.HydraQLParser;

/**
 * @author leojie 2023/9/10 21:07
 */
public class TableNameVisitor extends BaseVisitor<String> {
    public TableNameVisitor() {
        super(null);
    }

    @Override
    public String visitTable_ref(HydraQLParser.Table_refContext ctx) {
        String namespace = "";
        String tableName = "";
        HydraQLParser.Namespace_nameContext namespaceNameContext = ctx.namespace_name();
        if (namespaceNameContext != null && !namespaceNameContext.isEmpty()) {
            namespace = getText(namespaceNameContext.name());
        }
        HydraQLParser.Table_nameContext tableNameContext = ctx.table_name();
        if (tableNameContext != null && !tableNameContext.isEmpty()) {
            tableName = getText(tableNameContext.name());
        }
        return namespace.concat(":").concat(tableName);
    }

    public String extractTableName(HydraQLParser.Table_refContext tableRefContext) {
        return tableRefContext.accept(this);
    }
}
