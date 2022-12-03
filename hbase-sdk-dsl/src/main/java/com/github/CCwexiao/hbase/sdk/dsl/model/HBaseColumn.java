package com.github.CCwexiao.hbase.sdk.dsl.model;


import com.github.CCweixiao.hbase.sdk.common.constants.HMHBaseConstants;
import com.github.CCweixiao.hbase.sdk.common.lang.MyAssert;
import com.github.CCweixiao.hbase.sdk.common.type.ColumnType;
import com.github.CCweixiao.hbase.sdk.common.util.StringUtil;

import java.util.Objects;

/**
 * @author leojie 2020/11/27 10:45 下午
 */
public class HBaseColumn {
    /**
     * family name
     */
    private final String familyName;
    /**
     * qualifier name
     */
    private final String columnName;
    /**
     * qualifier value type
     */
    private final ColumnType columnType;

    /**
     * is null or not
     */
    private final boolean nullable;

    /**
     * set one column is row
     */
    private final boolean columnIsRow;

    public HBaseColumn(Builder builder) {
        this.familyName = builder.familyName;
        this.columnName = builder.columnName;
        this.columnType = builder.columnType;
        this.nullable = builder.nullable;
        this.columnIsRow = builder.columnIsRow;
    }

    public static class Builder {
        private final String familyName;
        private final String columnName;
        private ColumnType columnType = ColumnType.StringType;
        private boolean nullable = true;

        private boolean columnIsRow = false;

        public Builder(String familyName, String columnName) {
            MyAssert.checkArgument(StringUtil.isNotBlank(columnName), "The mame of col must not be empty.");
            this.familyName = familyName;
            this.columnName = columnName;
        }

        public Builder(String columnName) {
            this("", columnName);
        }

        public Builder columnType(ColumnType columnType) {
            this.columnType = columnType;
            return this;
        }

        public Builder columnIsRow(boolean columnIsRow) {
            this.columnIsRow = columnIsRow;
            return this;
        }

        public Builder nullable(boolean nullable) {
            this.nullable = nullable;
            return this;
        }

        public HBaseColumn build() {
            return new HBaseColumn(this);
        }
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getColumnName() {
        return columnName;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public boolean isNullable() {
        return nullable;
    }

    public boolean columnIsRow() {
        return columnIsRow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HBaseColumn)) {
            return false;
        }
        HBaseColumn column = (HBaseColumn) o;
        return familyName.equals(column.familyName)
                && columnName.equals(column.columnName) && columnIsRow == column.columnIsRow;
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyName, columnName, columnIsRow);
    }

    public String generateSchema(String defaultFamily) {
        String familyName = defaultFamily;
        if (StringUtil.isNotBlank(this.getFamilyName())) {
            familyName = this.getFamilyName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        sb.append("—— ");
        sb.append(familyName);
        sb.append(HMHBaseConstants.FAMILY_QUALIFIER_SEPARATOR);
        sb.append(this.getColumnName());
        sb.append(": ");
        sb.append("(");
        sb.append("isRow");
        sb.append(" = ");
        sb.append(this.columnIsRow());
        sb.append(", ");
        sb.append("nullable");
        sb.append(" = ");
        sb.append(this.isNullable());
        sb.append(")");
        return sb.toString();
    }
}
