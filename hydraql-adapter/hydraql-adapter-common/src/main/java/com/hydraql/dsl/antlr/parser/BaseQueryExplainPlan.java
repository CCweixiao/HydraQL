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

    protected class DMLSqlCommandParser {
        private final HydraQLParser.Dml_commandContext dmlCommandContext;

        public DMLSqlCommandParser() {
            this.dmlCommandContext = sqlCommandContext.dml_command();
        }

        public boolean isSelectStatement() {
            return this.dmlCommandContext.select_command() != null &&
                    !this.dmlCommandContext.select_command().isEmpty();
        }

        public boolean isUpsetStatement() {
            return this.dmlCommandContext.upsert_values_command() != null &&
                    !this.dmlCommandContext.upsert_values_command().isEmpty();
        }

        public boolean isDeleteStatement() {
            return this.dmlCommandContext.delete_command() != null &&
                    !this.dmlCommandContext.delete_command().isEmpty();
        }
    }

    protected class DDLSqlCommandParser {
        private final HydraQLParser.Ddl_commandContext ddlCommandContext;

        public DDLSqlCommandParser() {
            this.ddlCommandContext = sqlCommandContext.ddl_command();
        }

        public boolean isCreateVirtualTable() {
            return this.ddlCommandContext.create_virtual_table_command() != null &&
                    !this.ddlCommandContext.create_virtual_table_command().isEmpty();
        }

        public boolean isShowVirtualTables() {
            return this.ddlCommandContext.show_tables_command() != null &&
                    !this.ddlCommandContext.show_tables_command().isEmpty();
        }

        public boolean isShowCreateVirtualTable() {
            return this.ddlCommandContext.show_table_command() != null &&
                    !this.ddlCommandContext.show_table_command().isEmpty();
        }

        public boolean isDropVirtualTable() {
            return this.ddlCommandContext.drop_table_command() != null &&
                    !this.ddlCommandContext.drop_table_command().isEmpty();
        }
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

    protected boolean isDdlCommand() {
        return this.sqlCommandContext.ddl_command() != null &&
                !this.sqlCommandContext.ddl_command().isEmpty();
    }

    protected boolean isDmlCommand() {
        return this.sqlCommandContext.dml_command() != null &&
                !this.sqlCommandContext.dml_command().isEmpty();
    }
}
