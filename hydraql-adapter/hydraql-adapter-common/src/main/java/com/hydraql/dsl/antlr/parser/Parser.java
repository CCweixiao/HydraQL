package com.hydraql.dsl.antlr.parser;

import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.QueryHBaseColumn;

import java.util.List;

/**
 * @author leojie 2023/9/10 21:01
 */
public interface Parser {
    String getTableName();

    HQLType getHqlType();

    List<QueryHBaseColumn> getSelectColumns();

    List<HBaseColumn> getUpsertColumns();

    List<HBaseColumn> getDeleteColumns();
}
