package com.hydraql.adapter.schema;

import org.apache.hadoop.hbase.HColumnDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2020/9/9 10:25 下午
 */
public class ColumnFamilyDesc extends BaseColumnFamilyDesc implements Comparable<ColumnFamilyDesc> {

    private final BaseColumnFamilyDescriptorConverter<ColumnFamilyDesc, HColumnDescriptor>
            familyDescriptorConverter;

    private ColumnFamilyDesc(BaseColumnFamilyDesc.Builder<ColumnFamilyDesc> builder) {
        super(builder);
        this.familyDescriptorConverter = new ColumnFamilyDescriptorConverter(this);
    }

    public static class Builder extends BaseColumnFamilyDesc.Builder<ColumnFamilyDesc> {

        private static final List<String> IGNORE_VALUE_KEYS = new ArrayList<>(3);

        static {
            IGNORE_VALUE_KEYS.add("IS_MOB");
            IGNORE_VALUE_KEYS.add("MOB_THRESHOLD");
            IGNORE_VALUE_KEYS.add("STORAGE_POLICY");
        }

        private Builder(String name) {
            super(name);
        }

        @Override
        public boolean ignoreValue(String key) {
            boolean unsupported = super.ignoreValue(key);
            if (unsupported) {
                return true;
            }
            return IGNORE_VALUE_KEYS.contains(key);
        }

        @Override
        public ColumnFamilyDesc build() {
            return new ColumnFamilyDesc(this);
        }
    }

    public static Builder newBuilder(String name) {
        return new Builder(name);
    }

    public static BaseColumnFamilyDesc.Builder<ColumnFamilyDesc> copyFrom(String name, ColumnFamilyDesc cf) {
        return new Builder(name).copyFrom(cf);
    }

    public static ColumnFamilyDesc createDefault(String name) {
        return newBuilder(name).build();
    }

    public HColumnDescriptor convertTo() {
        return this.familyDescriptorConverter.convertTo();
    }

    public ColumnFamilyDesc convertFrom(HColumnDescriptor columnDescriptor) {
        return this.familyDescriptorConverter.convertFrom(columnDescriptor);
    }

    @Override
    public int compareTo(ColumnFamilyDesc o) {
        return this.convertTo().compareTo(o.convertTo());
    }

    @Override
    public int hashCode() {
        return this.convertTo().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = super.equals(obj);
        if (!res) {
            return false;
        }
        return this.convertTo().equals(((ColumnFamilyDesc) obj).convertTo());
    }

    @Override
    public String toString() {
        return this.convertTo().toString();
    }
}
