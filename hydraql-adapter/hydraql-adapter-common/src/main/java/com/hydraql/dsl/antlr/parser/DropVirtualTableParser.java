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
public class DropVirtualTableParser implements Parser {
    private final HydraQLParser.Drop_table_commandContext dropTableCommandContext;

    public DropVirtualTableParser(BaseQueryExplainPlan queryExplainPlan) {
        this.dropTableCommandContext = queryExplainPlan.getDdlCommandContext().drop_table_command();
    }

    @Override
    public String getTableName() {
        return new TableNameVisitor().extractTableName(this.dropTableCommandContext.table_ref());
    }

    @Override
    public HQLType getHqlType() {
        return HQLType.DROP_VIRTUAL_TABLE;
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
