package com.hydraql.dsl.antlr.parser;

import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.antlr.visitor.TableNameVisitor;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.QueryHBaseColumn;

import java.util.List;

/**
 * @author leojie 2023/9/10 21:30
 */
public class ShowCreateVirtualTableParser implements Parser{
    private final HydraQLParser.Show_table_commandContext showTableCommandContext;

    public ShowCreateVirtualTableParser(BaseQueryExplainPlan queryExplainPlan) {
        this.showTableCommandContext = queryExplainPlan.getDdlCommandContext().show_table_command();
    }

    @Override
    public String getTableName() {
        return new TableNameVisitor().extractTableName(this.showTableCommandContext.table_ref());
    }

    @Override
    public HQLType getHqlType() {
        return HQLType.SHOW_VIRTUAL_TABLE;
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
        return null;
    }
}
