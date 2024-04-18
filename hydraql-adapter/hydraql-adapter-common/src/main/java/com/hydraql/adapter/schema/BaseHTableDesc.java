package com.hydraql.adapter.schema;

import com.hydraql.common.constants.HBaseConstants;
import com.hydraql.common.exception.HBaseFamilyNotUniqueException;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.TableName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author leojie 2023/5/19 20:35
 */
public abstract class BaseHTableDesc {
    public static final String NAMESPACE_DEFAULT = "default";
    public static final String TABLE_NAME_SPLIT_CHAR = ":";
    public static final String MAX_FILESIZE = "MAX_FILESIZE";
    public static final long MAX_FILESIZE_DEFAULT = 10737418240L;
    public static final String READONLY = "READONLY";
    public static final String COMPACTION_ENABLED = "COMPACTION_ENABLED";
    public static final String MEMSTORE_FLUSHSIZE = "MEMSTORE_FLUSHSIZE";
    public static final long MEMSTORE_FLUSHSIZE_DEFAULT = 134217728L;
    public static final String SPLIT_POLICY = "SPLIT_POLICY";

    private final String name;
    private final long maxFileSize;
    private final boolean readOnly;
    private final long memStoreFlushSize;
    private final boolean compactionEnabled;
    private final String regionSplitPolicyClassName;
    private final Map<String, String> configuration;
    private final Map<String, String> values;
    private final List<BaseColumnFamilyDesc> columnFamilyDescList;

    public BaseHTableDesc(Builder<?> builder) {
        this.name = builder.name;
        this.maxFileSize = builder.maxFileSize;
        this.readOnly = builder.readOnly;
        this.memStoreFlushSize = builder.memStoreFlushSize;
        this.compactionEnabled = builder.compactionEnabled;
        this.configuration = builder.configuration;
        this.values = builder.values;
        this.columnFamilyDescList = builder.columnFamilyDescList;
        this.regionSplitPolicyClassName = builder.regionSplitPolicyClassName;
    }

    public abstract static class Builder<HTD extends BaseHTableDesc> implements HTableSchemaInterceptor {
        private final String name;
        private long maxFileSize = MAX_FILESIZE_DEFAULT;
        private boolean readOnly = false;
        private long memStoreFlushSize = MEMSTORE_FLUSHSIZE_DEFAULT;
        private boolean compactionEnabled = true;
        private String regionSplitPolicyClassName;
        private final Map<String, String> configuration;
        private final Map<String, String> values;
        private final List<BaseColumnFamilyDesc> columnFamilyDescList;

        protected Builder(String name) {
            this.name = name;
            this.columnFamilyDescList = new ArrayList<>();
            this.configuration = new HashMap<>();
            this.values = new HashMap<>();
        }

        public Builder<HTD> maxFileSize(long maxFileSize) {
            verifyKey(MAX_FILESIZE);
            if (maxFileSize <= 0) {
                maxFileSize = MAX_FILESIZE_DEFAULT;
            }
            this.maxFileSize = maxFileSize;
            this.setValue(MAX_FILESIZE, String.valueOf(maxFileSize));
            return this;
        }

        public Builder<HTD> readOnly(boolean readOnly) {
            verifyKey(READONLY);
            this.readOnly = readOnly;
            this.setValue(READONLY, String.valueOf(readOnly));
            return this;
        }

        public Builder<HTD> memStoreFlushSize(long memStoreFlushSize) {
            verifyKey(MEMSTORE_FLUSHSIZE);
            if (memStoreFlushSize <= 0) {
                memStoreFlushSize = MEMSTORE_FLUSHSIZE_DEFAULT;
            }
            this.memStoreFlushSize = memStoreFlushSize;
            this.setValue(MEMSTORE_FLUSHSIZE, String.valueOf(memStoreFlushSize));
            return this;
        }

        public Builder<HTD> compactionEnabled(boolean compactionEnabled) {
            verifyKey(COMPACTION_ENABLED);
            this.compactionEnabled = compactionEnabled;
            this.setValue(COMPACTION_ENABLED, String.valueOf(compactionEnabled));
            return this;
        }

        public Builder<HTD> regionSplitPolicyClassName(String regionSplitPolicyClassName) {
            verifyKey(SPLIT_POLICY);
            this.regionSplitPolicyClassName = regionSplitPolicyClassName;
            this.setValue(SPLIT_POLICY, regionSplitPolicyClassName);
            return this;
        }

        public Builder<HTD> setConfiguration(String key, String value) {
            if (StringUtil.isBlank(key)) {
                return this;
            }
            verifyConfiguration(key, value);
            this.configuration.put(key, value);
            return this;
        }

        public Builder<HTD> setValue(String key, String value) {
            if (StringUtil.isBlank(key)) {
                return this;
            }
            this.values.put(key, value);
            return this;
        }

        public Builder<HTD> addFamilyDesc(BaseColumnFamilyDesc columnFamilyDesc) {
            String familyName = columnFamilyDesc.getNameAsString();
            for (BaseColumnFamilyDesc familyDesc : this.columnFamilyDescList) {
                if (familyName.equals(familyDesc.getNameAsString())) {
                    throw new IllegalArgumentException(String.format("Column family %s already exists", familyName));
                }
            }
            this.columnFamilyDescList.add(columnFamilyDesc);
            return this;
        }

        public abstract HTD build();
    }

    public String getTableNameAsString() {
        return name;
    }

    public TableName getTableName() {
        return TableName.valueOf(name);
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public long getMemStoreFlushSize() {
        return memStoreFlushSize;
    }

    public boolean isCompactionEnabled() {
        return compactionEnabled;
    }

    public String getRegionSplitPolicyClassName() {
        return regionSplitPolicyClassName;
    }

    public Map<String, String> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String key, String value) {
        if (StringUtil.isBlank(key)) {
            return;
        }
        this.configuration.put(key, value);
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValue(String key, String value) {
        if (StringUtil.isBlank(key)) {
            return;
        }
        this.values.put(key, value);
    }

    public List<BaseColumnFamilyDesc> getColumnFamilyDescList() {
        return columnFamilyDescList;
    }

    public void append(BaseColumnFamilyDesc columnFamilyDesc) {
        this.columnFamilyDescList.add(columnFamilyDesc);
    }

    public String getNamespaceName() {
        return HBaseConstants.getNamespaceName(name);
    }

    public String getTableNameWithNamespace() {
        String tabName = this.getTableNameAsString();
        if (StringUtil.isBlank(tabName)) {
            throw new IllegalArgumentException("The table name is not empty.");
        }
        String namespaceName = getNamespaceName();
        if (!tabName.contains(HBaseConstants.TABLE_NAME_SPLIT_CHAR)) {
            return namespaceName.concat(HBaseConstants.TABLE_NAME_SPLIT_CHAR).concat(tabName);
        } else {
            return tabName;
        }
    }

    public boolean columnFamilyExists(BaseColumnFamilyDesc columnFamilyDesc) {
        if (columnFamilyIsEmpty()) {
            return false;
        }
        for (BaseColumnFamilyDesc familyDesc : this.columnFamilyDescList) {
            if (familyDesc.getNameAsString().equals(columnFamilyDesc.getNameAsString())) {
                return true;
            }
        }
        return false;
    }

    private boolean columnFamilyIsEmpty() {
        return this.columnFamilyDescList == null || this.columnFamilyDescList.isEmpty();
    }

    public void checkHasSameColumnFamilyOrNot() {
        if (columnFamilyIsEmpty()) {
            return;
        }

        final Map<String, Long> familyCountMap = this.columnFamilyDescList.stream()
                .collect(Collectors.groupingBy(BaseColumnFamilyDesc::getNameAsString, Collectors.counting()));
        familyCountMap.forEach((familyName, count) -> {
            if (count > 1) {
                throw new HBaseFamilyNotUniqueException(
                        String.format("The family %s in the same table should be unique.", familyName));
            }
        });
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return getClass() == obj.getClass();
    }
}
