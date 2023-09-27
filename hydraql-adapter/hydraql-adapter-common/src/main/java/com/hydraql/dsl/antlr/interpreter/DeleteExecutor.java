package com.hydraql.dsl.antlr.interpreter;

import com.hydraql.adapter.AbstractHBaseSqlAdapter;
import com.hydraql.common.model.HQLType;

/**
 * @author leojie 2023/9/27 19:22
 */
public class DeleteExecutor extends BaseHqlExecutor<Boolean> implements Interpreter<Boolean> {

    private final AbstractHBaseSqlAdapter sqlAdapter;

    private DeleteExecutor(DeleteExecutor.ExecutorBuilder builder) {
        super(builder.hql);
        this.sqlAdapter = builder.sqlAdapter;
    }

    @Override
    public void execute() {

    }

    @Override
    public HQLType expectHqlType() {
        return HQLType.PUT;
    }

    private static class ExecutorBuilder extends Builder<DeleteExecutor, Boolean> {
        private final String hql;
        private final AbstractHBaseSqlAdapter sqlAdapter;
        private ExecutorBuilder(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
            this.hql = hql;
            this.sqlAdapter = sqlAdapter;
        }

        @Override
        public DeleteExecutor build() {
            return new DeleteExecutor(this);
        }
    }

    public static DeleteExecutor of(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
        return new DeleteExecutor.ExecutorBuilder(hql, sqlAdapter).build();
    }
}
