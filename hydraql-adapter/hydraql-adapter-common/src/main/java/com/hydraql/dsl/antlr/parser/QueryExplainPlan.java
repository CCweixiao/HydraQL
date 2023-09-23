package com.hydraql.dsl.antlr.parser;

import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.model.HBaseTableSchema;

/**
 * @author leojie 2023/9/13 22:10
 */
public class QueryExplainPlan extends BaseQueryExplainPlan {
    private HBaseTableSchema tableSchema;
    public QueryExplainPlan(HydraQLParser.Sql_commandContext sqlCommandContext) {
        super(sqlCommandContext);
    }

    @Override
    public Parser getSqlCommandParser() {
        if (isDdlCommand()) {
            DDLSqlCommandParser ddlSqlCommandParser = new DDLSqlCommandParser();
            if (ddlSqlCommandParser.isCreateVirtualTable()) {
                return new CreateVirtualTableCommandParser(this);
            }
            if (ddlSqlCommandParser.isShowVirtualTables()) {
                return new ShowVirtualTablesParser();
            }
            if (ddlSqlCommandParser.isShowCreateVirtualTable()) {
                return new ShowCreateVirtualTableParser(this);
            }
            if (ddlSqlCommandParser.isDropVirtualTable()) {
                return new DropVirtualTableParser(this);
            }
        }
        if (isDmlCommand()) {
            DMLSqlCommandParser dmlSqlCommandParser = new DMLSqlCommandParser();
            if (dmlSqlCommandParser.isSelectStatement()) {
                return new SelectCommandParser(this);
            }
            if (dmlSqlCommandParser.isUpsetStatement()) {
                return new UpsertCommandParser(this);
            }
            if (dmlSqlCommandParser.isDeleteStatement()) {
                return new DeleteCommandParser(this);
            }
        }
        throw new HBaseSqlAnalysisException("Unsupported SQL type.");
    }

    @Override
    HBaseTableSchema getTableSchema() {
        return this.tableSchema;
    }

    public void setTableSchema(HBaseTableSchema tableSchema) {
        this.tableSchema = tableSchema;
    }
}
