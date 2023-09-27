package com.hydraql.dsl.antlr.interpreter;

import com.hydraql.adapter.AbstractHBaseSqlAdapter;
import com.hydraql.common.model.HQLType;

/**
 * @author leojie 2023/9/27 19:22
 */
public class UpsetExecutor extends BaseHqlExecutor<Boolean> implements Interpreter<Boolean> {

    private final AbstractHBaseSqlAdapter sqlAdapter;

    private UpsetExecutor(UpsetExecutor.ExecutorBuilder builder) {
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

    private static class ExecutorBuilder extends Builder<UpsetExecutor, Boolean> {
        private final String hql;
        private final AbstractHBaseSqlAdapter sqlAdapter;
        private ExecutorBuilder(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
            this.hql = hql;
            this.sqlAdapter = sqlAdapter;
        }

        @Override
        public UpsetExecutor build() {
            return new UpsetExecutor(this);
        }
    }

    public static UpsetExecutor of(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
        return new UpsetExecutor.ExecutorBuilder(hql, sqlAdapter).build();
    }
}
