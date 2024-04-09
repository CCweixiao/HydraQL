package com.hydraql.adapter.schema;

import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.yetus.audience.InterfaceAudience;

import java.util.HashSet;
import java.util.Set;

/**
 * @author leojie 2020/9/9 10:25 下午
 */
@InterfaceAudience.Public
public class ColumnFamilyDesc extends BaseColumnFamilyDesc implements Comparable<ColumnFamilyDesc>{

    private final BaseColumnFamilyDescriptorConverter<ColumnFamilyDesc, ColumnFamilyDescriptor>
            familyDescriptorConverter;

    private ColumnFamilyDesc(BaseColumnFamilyDesc.Builder<ColumnFamilyDesc> builder) {
        super(builder);
        this.familyDescriptorConverter = new ColumnFamilyDescriptorConverter(this);
    }

    public static class Builder extends BaseColumnFamilyDesc.Builder<ColumnFamilyDesc> {
        public static final Set<String> UNSUPPORTED_ATTRIBUTES =
                new HashSet<>();

        static {
            UNSUPPORTED_ATTRIBUTES.add(CACHE_DATA_IN_L1);
        }

        private Builder(String name) {
            super(name);
        }
        @Override
        public ColumnFamilyDesc build() {
            return new ColumnFamilyDesc(this);
        }

        @Override
        public void verifyKey(String key) {
            if (StringUtil.isBlank(key)) {
                throw new IllegalArgumentException("Column family attribute key is blank");
            }
            if (UNSUPPORTED_ATTRIBUTES.contains(key)) {
                throw new IllegalArgumentException(String.format("Column family attribute key '%s' is not supported", key));
            }
        }
    }

    public static Builder newBuilder(String name) {
        return new ColumnFamilyDesc.Builder(name);
    }

    public static ColumnFamilyDesc createDefault(String name) {
        return newBuilder(name).build();
    }

    public ColumnFamilyDescriptor convertFor() {
        return this.familyDescriptorConverter.convertFor();
    }

    public ColumnFamilyDesc convertTo(ColumnFamilyDescriptor columnDescriptor) {
        return this.familyDescriptorConverter.convertTo(columnDescriptor);
    }

    @Override
    public int compareTo(ColumnFamilyDesc o) {
        return ColumnFamilyDescriptor.COMPARATOR.compare(this.convertFor(), o.convertFor());
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
        return this.convertFor().equals(((ColumnFamilyDesc) obj).convertFor());
    }

    @Override
    public String toString() {
        return this.convertFor().toString();
    }
}
