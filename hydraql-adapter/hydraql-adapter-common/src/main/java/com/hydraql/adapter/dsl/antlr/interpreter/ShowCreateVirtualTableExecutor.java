package com.hydraql.adapter.dsl.antlr.interpreter;

import com.hydraql.adapter.HqlOpAdapter;
import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.adapter.dsl.antlr.visitor.TableNameVisitor;
import com.hydraql.dsl.model.HBaseTableSchema;

/**
 * @author leojie 2023/9/27 15:03
 */
public class ShowCreateVirtualTableExecutor extends BaseHqlExecutor<String> implements Interpreter  {
    private final HqlOpAdapter sqlAdapter;

    private ShowCreateVirtualTableExecutor(ExecutorBuilder builder) {
        super(builder.hql);
        this.sqlAdapter = builder.sqlAdapter;
    }

    @Override
    public String execute() {
        this.checkParsedHqlTypeMatched(this.getHqlType());
        HydraQLParser.Show_table_commandContext showTableCommandContext =
                this.getSqlCommandContext().ddl_command().show_table_command();
        String tableName = new TableNameVisitor().extractTableName(showTableCommandContext.table_ref());
        HBaseTableSchema tableSchema = sqlAdapter.getTableSchema(tableName);
        String schema = tableSchema.toString();
        String createSql = tableSchema.getCreateSql();
        createSql = createSql.replaceAll("\n", "").replaceAll(",", ",\n");
        return createSql + "\n\n" + schema;
    }

    @Override
    public HQLType expectHqlType() {
        return HQLType.SHOW_VIRTUAL_TABLE;
    }

    private static class ExecutorBuilder extends Builder<ShowCreateVirtualTableExecutor, String> {
        private final String hql;
        private final HqlOpAdapter sqlAdapter;
        private ExecutorBuilder(String hql, HqlOpAdapter sqlAdapter) {
            this.hql = hql;
            this.sqlAdapter = sqlAdapter;
        }

        @Override
        public ShowCreateVirtualTableExecutor build() {
            return new ShowCreateVirtualTableExecutor(this);
        }
    }

    public static ShowCreateVirtualTableExecutor of(String hql, HqlOpAdapter sqlAdapter) {
        return new ShowCreateVirtualTableExecutor.ExecutorBuilder(hql, sqlAdapter).build();
    }
}
