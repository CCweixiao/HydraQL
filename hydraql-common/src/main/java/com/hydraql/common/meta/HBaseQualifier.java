package com.hydraql.common.meta;

import com.hydraql.common.constants.HBaseConstants;
import com.hydraql.common.lang.Preconditions;
import com.hydraql.common.util.BytesUtil;
import com.hydraql.common.util.StringUtil;

import java.nio.ByteBuffer;

/**
 * @author leojie@apache.org 2024/8/11 22:09
 */
public class HBaseQualifier extends HBaseField {
    private static final long serialVersionUID = -8740558976936931605L;

    private final String family;
    private final byte[] familyBytes;
    private final String qualifier;
    private final byte[] qualifierBytes;
    private final String familyAndQualifier;

    private HBaseQualifier(HBaseQualifier.Builder builder) {
        super(builder);
        this.family = builder.family;
        this.familyBytes = builder.familyBytes;
        this.qualifier = builder.qualifier;
        this.qualifierBytes = builder.qualifierBytes;
        this.familyAndQualifier = builder.familyAndQualifier;
    }

    public static HBaseQualifier.Builder newBuilder(Class<?> type, String rowName) {
        return new HBaseQualifier.Builder(type, rowName);
    }

    public static class Builder extends HBaseField.Builder<HBaseQualifier> {
        private String family;
        private byte[] familyBytes;
        private String qualifier;
        private byte[] qualifierBytes;
        private String familyAndQualifier;

        private Builder(Class<?> type, String fieldName) {
            super(type, fieldName);
            this.setIsRowKey(false);
        }

        public void setFamily(String family) {
            this.family = family;
            this.familyBytes = BytesUtil.toBytes(family);
        }

        public void setQualifier(String qualifier) {
            this.qualifier = qualifier;
            this.qualifierBytes = BytesUtil.toBytes(qualifier);
        }

        @Override
        HBaseQualifier build() {
            Preconditions.checkState(StringUtil.isNotBlank(family), "The family cannot be null or empty.");
            Preconditions.checkState(StringUtil.isNotBlank(qualifier), "The qualifier cannot be null or empty.");
            this.familyAndQualifier = HBaseConstants.formatFamilyAndQualifier(family, qualifier);
            return new HBaseQualifier(this);
        }
    }

    @Override
    public void setByteValue(Object target, byte[] value) {
        Object val = this.getTypeHandler().toObject(this.getType(), value);
        if (val == null) {
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
        this.getSetMethodInvoker().invoke(target, new Object[]{val});
    }

    @Override
    public void setValue(Object target, Object value) {
        if (value == null) {
            return;
        }
        this.getSetMethodInvoker().invoke(target, new Object[]{value});
    }

    public String getFamily() {
        return family;
    }

    @Override
    public byte[] getFamilyBytes() {
        return familyBytes;
    }

    public String getQualifier() {
        return qualifier;
    }

    @Override
    public byte[] getQualifierBytes() {
        return qualifierBytes;
    }

    @Override
    public String getFamilyAndQualifier() {
        return familyAndQualifier;
    }
}
