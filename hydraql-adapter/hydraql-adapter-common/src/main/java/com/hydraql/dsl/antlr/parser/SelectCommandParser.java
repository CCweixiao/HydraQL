package com.hydraql.dsl.antlr.parser;

import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.antlr.visitor.SelectColListVisitor;
import com.hydraql.dsl.antlr.visitor.TableNameVisitor;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.QueryHBaseColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2023/9/10 20:59
 */
public class SelectCommandParser implements Parser {
    private final BaseQueryExplainPlan queryExplainPlan;
    private final HydraQLParser.Select_statementContext selectStatementContext;

    public SelectCommandParser(BaseQueryExplainPlan queryExplainPlan) {
        this.queryExplainPlan = queryExplainPlan;
        this.selectStatementContext = queryExplainPlan.getDmlCommandContext().select_command().select_statement();
    }

    @Override
    public String getTableName() {
        return new TableNameVisitor().extractTableName(this.selectStatementContext.table_ref());
    }

    @Override
    public HQLType getHqlType() {
        return HQLType.SELECT;
    }

    @Override
    public List<QueryHBaseColumn> getSelectColumns() {
        List<HydraQLParser.Select_column_defContext> selectColumnDefContexts =
                this.selectStatementContext.select_column_def();
        SelectColListVisitor selectColListVisitor = new SelectColListVisitor(this.queryExplainPlan.getTableSchema());
        List<QueryHBaseColumn> queryHBaseColumns = new ArrayList<>();
        for (HydraQLParser.Select_column_defContext selectColumnDefContext : selectColumnDefContexts) {
            List<QueryHBaseColumn> columns =
                    selectColListVisitor.extractSelectColumns(selectColumnDefContext);
            queryHBaseColumns.addAll(columns);
        }
        return queryHBaseColumns;
    }

    @Override
    public List<HBaseColumn> getUpsertColumns() {
        return null;
    }

    @Override
    public List<HBaseColumn> getDeleteColumns() {
        return null;
    }
}
