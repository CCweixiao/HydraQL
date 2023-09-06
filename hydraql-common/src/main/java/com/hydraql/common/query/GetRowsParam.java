package com.hydraql.common.query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2023/7/20 11:09
 */
public class GetRowsParam extends BaseGetRowParam<GetRowsParam> {
    private final List<String> rowKeyList;

    public GetRowsParam(Builder builder) {
        super(builder);
        this.rowKeyList = builder.rowKeyList;
    }

    public static class Builder extends BaseGetRowParam.Builder<GetRowsParam>{
        private List<String> rowKeyList;

        private Builder() {
            super();
        }

        public Builder of(List<String> rowKeyList) {
            this.rowKeyList = rowKeyList;
            return this;
        }

        public Builder appendRowKey(String rowKey) {
            if (this.rowKeyList == null) {
                this.rowKeyList = new ArrayList<>();
            }
            this.rowKeyList.add(rowKey);
            return this;
        }

        @Override
        public GetRowsParam build() {
            return new GetRowsParam(this);
        }
    }

    public static Builder of(List<String> rowKeyList) {
        return new GetRowsParam.Builder().of(rowKeyList);
    }

    public List<String> getRowKeyList() {
        return rowKeyList;
    }

    @Override
    public String toString() {
        return "GetParams{" +
                "rowKeyList='" + rowKeyList + '\'' +
                ", familyName='" + getFamily() + '\'' +
                ", qualifiers=" + getQualifiers() +
                ", timeRange=" + getTimeRange() +
                ", versions=" + getVersions() +
                '}';
    }
}
