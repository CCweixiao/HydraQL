package com.hydraql.dsl.client.rowkey;

import com.hydraql.common.lang.Assert;
import com.hydraql.common.type.ColumnType;
import com.hydraql.common.util.EncodingUtil;
import com.hydraql.dsl.client.rowkey.func.RowKeyFunc;

import java.util.Arrays;

/**
 * @author leojie 2020/11/28 11:58 上午
 */
@Deprecated
public class BytesRowKey implements RowKey<byte[]> {
    private final byte[] value;

    public BytesRowKey(byte[] value) {
        Assert.checkNotNull(value);
        this.value = value.clone();
    }


    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public byte[] computeRowValue() {
        return this.value;
    }

    @Override
    public String getOriValue() {
        return null;
    }

    @Override
    public void setValueBytes(byte[] valueBytes) {

    }

    @Override
    public ColumnType columnType() {
        return null;
    }

    @Override
    public void setRowKeyFunc(RowKeyFunc<byte[]> rowKeyFunc) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BytesRowKey)) {
            return false;
        }
        BytesRowKey that = (BytesRowKey) o;
        return Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }


    @Override
    public String toString() {
        return "BytesRowKey [key=" + EncodingUtil.toHexString(value) + "]";
    }

}
