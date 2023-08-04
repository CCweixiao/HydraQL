package com.hydraql.dsl.client.rowkey;

import com.hydraql.common.type.ColumnType;

/**
 * @author leojie 2022/12/3 12:51
 */
public class StringRowKey extends BaseRowKey<String> {
    public StringRowKey(String value) {
        super(value, null);
    }

    @Override
    public byte[] toBytes() {
        return this.columnType().getTypeHandler().toBytes(String.class, value);
    }

    @Override
    public String computeRowValue() {
        return this.value;
    }

    @Override
    public ColumnType columnType() {
        return ColumnType.StringType;
    }
}
