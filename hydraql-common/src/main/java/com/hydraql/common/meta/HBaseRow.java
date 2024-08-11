package com.hydraql.common.meta;

import com.hydraql.common.exception.InvalidTableModelClassException;
import com.hydraql.common.row.RowKeyGenerator;

import java.nio.ByteBuffer;

/**
 * @author leojie@apache.org 2024/8/11 21:53
 */
public class HBaseRow extends HBaseField {
    private static final long serialVersionUID = 1597454830964257527L;

    private final RowKeyGenerator rowKeyGenerator;

    private HBaseRow(HBaseRow.Builder builder) {
        super(builder);
        this.rowKeyGenerator = builder.rowKeyGenerator;
    }

    public static Builder newBuilder(Class<?> type, String rowName) {
        return new Builder(type, rowName);
    }

    public static class Builder extends HBaseField.Builder<HBaseRow> {
        private RowKeyGenerator rowKeyGenerator;

        private Builder(Class<?> type, String name) {
            super(type, name);
            this.setIsRowKey(true);
            this.setNullable(false);
        }

        public void setRowKeyGenerator(RowKeyGenerator rowKeyGenerator) {
            this.rowKeyGenerator = rowKeyGenerator;
        }

        @Override
        HBaseRow build() {
            return new HBaseRow(this);
        }
    }

    @Override
    public void setByteValue(Object target, byte[] value) {
        Object val = this.getTypeHandler().toObject(this.getType(), value);
        if (val == null) {
            return;
        }
        if (this.isRowKey() && this.getRowKeyGenerator() != null) {
            if (this.getType() != String.class) {
                throw new InvalidTableModelClassException("RowKeyGenerator cannot be used for non-string type row key.");
            }
            String valStr = this.getRowKeyGenerator().apply(val.toString());
            this.getSetMethodInvoker().invoke(target, new Object[]{valStr});
            return;
        }
        this.getSetMethodInvoker().invoke(target, new Object[]{val});
    }

    @Override
    public void setByteBufferValue(Object target, ByteBuffer value) {
        Object val = this.getTypeHandler().toObject(this.getType(), value);
        if (val == null) {
            return;
        }
        if (this.isRowKey() && this.getRowKeyGenerator() != null) {
            if (this.getType() != String.class) {
                throw new InvalidTableModelClassException("RowKeyGenerator cannot be used for non-string type row key.");
            }
            String valStr = this.getRowKeyGenerator().apply(val.toString());
            this.getSetMethodInvoker().invoke(target, new Object[]{valStr});
            return;
        }
        this.getSetMethodInvoker().invoke(target, new Object[]{val});
    }

    @Override
    public void setValue(Object target, Object value) {
        if (value == null) {
            return;
        }
        this.getSetMethodInvoker().invoke(target, new Object[]{value});
    }

    public RowKeyGenerator getRowKeyGenerator() {
        return rowKeyGenerator;
    }
}
