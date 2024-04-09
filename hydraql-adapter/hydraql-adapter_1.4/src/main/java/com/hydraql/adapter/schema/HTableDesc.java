package com.hydraql.adapter.schema;

import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.yetus.audience.InterfaceAudience;

/**
 * @author leojie 2021/6/23 9:48 下午
 */
@InterfaceAudience.Public
public class HTableDesc extends BaseHTableDesc implements Comparable<HTableDesc>{

    private final BaseHTableDescriptorConverter<HTableDesc, HTableDescriptor> tableDescriptorConverter;

    private HTableDesc(BaseHTableDesc.Builder<HTableDesc> builder) {
        super(builder);
        this.tableDescriptorConverter = new HTableDescriptorConverter(this);
    }

    public static class Builder extends BaseHTableDesc.Builder<HTableDesc> {

        private Builder(String name) {
            super(name);
        }

        @Override
        public HTableDesc build() {
            return new HTableDesc(this);
        }
    }

    public static Builder newBuilder(String name) {
        return new HTableDesc.Builder(name);
    }

    public static HTableDesc createDefault(String name) {
        return new HTableDesc.Builder(name).build();
    }

    public HTableDescriptor convertFor() {
        return this.tableDescriptorConverter.convertFor();
    }

    public HTableDesc convertTo(HTableDescriptor tableDescriptor) {
        return this.tableDescriptorConverter.convertTo(tableDescriptor);
    }

    @Override
    public int compareTo(HTableDesc o) {
        return this.convertFor().compareTo(o.convertFor());
    }

    @Override
    public int hashCode() {
        return this.convertFor().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = super.equals(obj);
        if (!res) {
            return false;
        }
        return this.convertFor().equals(((HTableDesc) obj).convertFor());
    }

    @Override
    public String toString() {
        return this.convertFor().toString();
    }
}
