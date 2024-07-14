package com.hydraql.adapter.schema;

import org.apache.hadoop.hbase.client.TableDescriptor;

import java.util.ArrayList;
import java.util.List;


/**
 * @author leojie 2021/6/23 9:48 下午
 */

public class HTableDesc extends BaseHTableDesc implements Comparable<HTableDesc> {
    private final BaseHTableDescriptorConverter<HTableDesc, TableDescriptor> tableDescriptorConverter;

    private HTableDesc(BaseHTableDesc.Builder<HTableDesc> builder) {
        super(builder);
        this.tableDescriptorConverter = new HTableDescriptorConverter(this);
    }

    public static class Builder extends BaseHTableDesc.Builder<HTableDesc> {
        private static final List<String> IGNORE_VALUE_KEYS = new ArrayList<>(3);

        static {
            IGNORE_VALUE_KEYS.add("IS_META");
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
        public HTableDesc build() {
            return new HTableDesc(this);
        }
    }

    public static Builder newBuilder(String name) {
        return new HTableDesc.Builder(name);
    }

    public static BaseHTableDesc.Builder<HTableDesc> copyFrom(String name, HTableDesc td) {
        return new Builder(name).copyFrom(td);
    }

    public static HTableDesc createDefault(String name) {
        return newBuilder(name).build();
    }

    public TableDescriptor convertTo() {
        return this.tableDescriptorConverter.convertTo();
    }

    public HTableDesc convertFrom(TableDescriptor tableDescriptor) {
        return this.tableDescriptorConverter.convertFrom(tableDescriptor);
    }

    @Override
    public int compareTo(HTableDesc o) {
        return TableDescriptor.COMPARATOR.compare(this.convertTo(), o.convertTo());
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
        return this.convertTo().equals(((HTableDesc) obj).convertTo());
    }

    @Override
    public String toString() {
        return this.convertTo().toString();
    }
}
