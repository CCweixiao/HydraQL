package com.hydraql.adapter.dsl.antlr.interpreter;

import com.hydraql.adapter.AbstractHBaseSqlAdapter;
import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.adapter.dsl.antlr.visitor.CreateVirtualTableVisitor;
import com.hydraql.dsl.model.HBaseTableSchema;

/**
 * @author leojie 2023/9/27 15:07
 */
public class CreateVirtualTableExecutor extends BaseHqlExecutor<Boolean> implements Interpreter {
    private final AbstractHBaseSqlAdapter sqlAdapter;

    private CreateVirtualTableExecutor(ExecutorBuilder builder) {
        super(builder.hql);
        this.sqlAdapter = builder.sqlAdapter;
    }


    @Override
    public HQLType expectHqlType() {
        return HQLType.CREATE_VIRTUAL_TABLE;
    }

    @Override
    public Boolean execute() {
        this.checkParsedHqlTypeMatched(this.getHqlType());
        HydraQLParser.Create_virtual_table_commandContext virtualTableCommand
                = this.getSqlCommandContext().ddl_command().create_virtual_table_command();
        HydraQLParser.If_not_existsContext ifNotExistsContext = virtualTableCommand.if_not_exists();
        boolean ifNotExists = ifNotExistsContext != null;
        CreateVirtualTableVisitor createVirtualTableVisitor = new CreateVirtualTableVisitor();
        HBaseTableSchema tableSchema = createVirtualTableVisitor.extractHBaseTableSchema(virtualTableCommand);
        String hql = this.getHql();
        tableSchema.setCreateSql(hql);
        sqlAdapter.checkAndCreateHqlMetaTable();
        boolean res = sqlAdapter.saveTableSchemaMeta(tableSchema, hql, ifNotExists);
        if (res) {
            sqlAdapter.registerTableSchema(tableSchema);
        }
        return true;
    }

    private static class ExecutorBuilder extends Builder<CreateVirtualTableExecutor, Boolean> {
        private final String hql;
        private final AbstractHBaseSqlAdapter sqlAdapter;
        private ExecutorBuilder(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
            this.hql = hql;
            this.sqlAdapter = sqlAdapter;
        }

        @Override
        public CreateVirtualTableExecutor build() {
            return new CreateVirtualTableExecutor(this);
        }
    }

    public static CreateVirtualTableExecutor of(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
        return new CreateVirtualTableExecutor.ExecutorBuilder(hql, sqlAdapter).build();
    }
}
