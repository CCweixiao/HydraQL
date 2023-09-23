package com.hydraql.dsl.antlr.parser;

import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.QueryHBaseColumn;

import java.util.List;

/**
 * @author leojie 2023/9/10 21:30
 */
public class ShowVirtualTablesParser implements Parser {

    public ShowVirtualTablesParser() {
    }

    @Override
    public String getTableName() {
        return "";
    }

    @Override
    public HQLType getHqlType() {
        return HQLType.SHOW_VIRTUAL_TABLES;
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
