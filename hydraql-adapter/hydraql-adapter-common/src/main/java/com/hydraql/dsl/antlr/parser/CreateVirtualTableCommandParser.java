package com.hydraql.dsl.antlr.parser;

import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.antlr.visitor.TableNameVisitor;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.QueryHBaseColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2023/9/10 21:28
 */
public class CreateVirtualTableCommandParser implements Parser {
    private final HydraQLParser.Create_virtual_table_commandContext createVirtualTableCommandContext;

    public CreateVirtualTableCommandParser(BaseQueryExplainPlan queryExplainPlan) {
        this.createVirtualTableCommandContext = queryExplainPlan.getDdlCommandContext()
                .create_virtual_table_command();
    }

    @Override
    public String getTableName() {
        return new TableNameVisitor().extractTableName(this.createVirtualTableCommandContext.table_ref());
    }

    @Override
    public HQLType getHqlType() {
        return HQLType.CREATE_VIRTUAL_TABLE;
    }

    @Override
    public List<QueryHBaseColumn> getSelectColumns() {
        return new ArrayList<>();
    }

    @Override
    public List<HBaseColumn> getUpsertColumns() {
        return new ArrayList<>();
    }

    @Override
    public List<HBaseColumn> getDeleteColumns() {
        return new ArrayList<>();
    }
}
