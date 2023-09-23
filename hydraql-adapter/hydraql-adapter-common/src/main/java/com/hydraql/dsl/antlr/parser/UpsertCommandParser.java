package com.hydraql.dsl.antlr.parser;

import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.antlr.visitor.TableNameVisitor;
import com.hydraql.dsl.antlr.visitor.UpsertColListVisitor;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.QueryHBaseColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2023/9/10 21:24
 */
public class UpsertCommandParser implements Parser {
    private final BaseQueryExplainPlan queryExplainPlan;
    private final HydraQLParser.Upsert_values_commandContext upsertValuesCommandContext;

    public UpsertCommandParser(BaseQueryExplainPlan queryExplainPlan) {
        this.queryExplainPlan = queryExplainPlan;
        this.upsertValuesCommandContext = queryExplainPlan.getDmlCommandContext().upsert_values_command();
    }

    @Override
    public String getTableName() {
        return new TableNameVisitor().extractTableName(this.upsertValuesCommandContext.table_ref());
    }

    @Override
    public HQLType getHqlType() {
        return HQLType.PUT;
    }

    @Override
    public List<QueryHBaseColumn> getSelectColumns() {
        return new ArrayList<>();
    }

    @Override
    public List<HBaseColumn> getUpsertColumns() {
        HydraQLParser.Upsert_column_def_listContext upsertColumnDefListContext =
                upsertValuesCommandContext.upsert_column_def_list();
        return new UpsertColListVisitor(this.queryExplainPlan.getTableSchema())
                .extractUpsertColumns(upsertColumnDefListContext);
    }

    @Override
    public List<HBaseColumn> getDeleteColumns() {
        return null;
    }
}
