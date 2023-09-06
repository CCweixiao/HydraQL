package com.hydraql.common.query;

import com.hydraql.common.util.StringUtil;

/**
 * @author leojie 2023/7/20 11:09
 */
public class GetRowParam extends BaseGetRowParam<GetRowParam>{
    private final String rowKey;

    public GetRowParam(Builder builder) {
        super(builder);
        this.rowKey = builder.rowKey;
    }

    public static class Builder extends BaseGetRowParam.Builder<GetRowParam> {
        private String rowKey;

        private Builder() {
            super();
        }

        private Builder of(String rowKey) {
            if (StringUtil.isBlank(rowKey)) {
                throw new IllegalArgumentException("The rowKey must be specified in the get query.");
            }
            this.rowKey = rowKey;
            return this;
        }

        @Override
        public GetRowParam build() {
            return new GetRowParam(this);
        }
    }

    public static Builder of(String rowKey) {
        return new GetRowParam.Builder().of(rowKey);
    }

    public String getRowKey() {
        return rowKey;
    }

    @Override
    public String toString() {
        return "GetParams{" +
                "rowKey='" + rowKey + '\'' +
                ", familyName='" + getFamily() + '\'' +
                ", columnNames=" + getQualifiers() +
                ", timeRange=" + getTimeRange() +
                ", versions=" + getVersions() +
                '}';
    }
}
