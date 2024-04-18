package com.hydraql.dsl.model;

import com.hydraql.common.constants.HBaseConstants;
import com.hydraql.common.util.StringUtil;

/**
 * @author leojie 2023/9/10 22:51
 */
public class QueryHBaseColumn {
    private final HBaseColumn column;
    private final String alias;

    private QueryHBaseColumn(Builder builder) {
        this.column = builder.column;
        this.alias = builder.alias;
    }

    static class Builder {
        private final HBaseColumn column;
        private String alias;

        private Builder(HBaseColumn column) {
            this.column = column;
        }

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public QueryHBaseColumn build() {
            return new QueryHBaseColumn(this);
        }
    }

    public static QueryHBaseColumn column(HBaseColumn column) {
        String alias = column.getColumnName();
        String family = column.getFamily();
        if (!column.columnIsRow() && StringUtil.isNotBlank(family)) {
            alias = HBaseConstants.getColumnName(family, alias);
        }
        return new QueryHBaseColumn.Builder(column).alias(alias).build();
    }

    public static QueryHBaseColumn column(HBaseColumn column, String alias) {
        return new QueryHBaseColumn.Builder(column).alias(alias).build();
    }

    public HBaseColumn getColumn() {
        return column;
    }

    public String getAlias() {
        return alias;
    }
}
