package com.hydraql.dsl.antlr.parser;

import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.model.HBaseTableSchema;

/**
 * @author leojie 2023/9/13 22:32
 */
public abstract class BaseQueryExplainPlan {
    protected final HydraQLParser.Sql_commandContext sqlCommandContext;

    public BaseQueryExplainPlan(HydraQLParser.Sql_commandContext sqlCommandContext) {
        this.sqlCommandContext = sqlCommandContext;
    }



    abstract Parser getSqlCommandParser();
    abstract HBaseTableSchema getTableSchema();

    public HQLType getHqlType() {
        return this.getSqlCommandParser().getHqlType();
    }

    public String getTableName() {
        return this.getSqlCommandParser().getTableName();
    }

    private HydraQLParser.Sql_commandContext getSqlCommandContext() {
        return sqlCommandContext;
    }

    public HydraQLParser.Dml_commandContext getDmlCommandContext() {
        return this.getSqlCommandContext().dml_command();
    }

    public HydraQLParser.Ddl_commandContext getDdlCommandContext() {
        return this.getSqlCommandContext().ddl_command();
    }


}
