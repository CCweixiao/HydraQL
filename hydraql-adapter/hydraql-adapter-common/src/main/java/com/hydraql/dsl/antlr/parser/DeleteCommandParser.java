package com.hydraql.dsl.antlr.parser;

import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.antlr.visitor.DeleteColListVisitor;
import com.hydraql.dsl.antlr.visitor.TableNameVisitor;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.QueryHBaseColumn;

import java.util.List;

/**
 * @author leojie 2023/9/10 21:24
 */
public class DeleteCommandParser implements Parser {
    private final BaseQueryExplainPlan queryExplainPlan;
    private final HydraQLParser.Delete_commandContext deleteCommandContext;

    public DeleteCommandParser(BaseQueryExplainPlan queryExplainPlan) {
        this.queryExplainPlan = queryExplainPlan;
        this.deleteCommandContext = queryExplainPlan.getDmlCommandContext().delete_command();
    }

    @Override
    public String getTableName() {
        return new TableNameVisitor().extractTableName(this.deleteCommandContext.table_ref());
    }

    @Override
    public HQLType getHqlType() {
        return HQLType.DELETE;
    }

    @Override
    public List<QueryHBaseColumn> getSelectColumns() {
        return null;
    }

    @Override
    public List<HBaseColumn> getUpsertColumns() {
        return null;
    }

    @Override
    public List<HBaseColumn> getDeleteColumns() {
        HydraQLParser.Delete_column_def_listContext deleteColumnDefListContext =
                this.deleteCommandContext.delete_column_def_list();
        return new DeleteColListVisitor(this.queryExplainPlan.getTableSchema())
                .extractDeleteColumns(deleteColumnDefListContext);
    }
}
