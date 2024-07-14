package com.hydraql.adapter.schema;

import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.Durability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leojie 2023/5/19 20:35
 */
public abstract class BaseHTableDesc {
    public static final String DURABILITY = "DURABILITY";
    private static final Durability DEFAULT_DURABILITY = Durability.USE_DEFAULT;

    /**
     * The number of region replicas for the table.
     */
    public static final String REGION_REPLICATION = "REGION_REPLICATION";
    public static final int DEFAULT_REGION_REPLICATION = 1;

    /**
     * the maximum size of the store file after which a
     * region split occurs.
     */
    public static final String MAX_FILESIZE = "MAX_FILESIZE";
    public static final long DEFAULT_MAX_FILESIZE = HConstants.DEFAULT_MAX_FILE_SIZE;

    public static final String READONLY = "READONLY";
    /**
     * Constant that denotes whether the table is READONLY by default and is false
     */
    public static final boolean DEFAULT_READONLY = false;

    public static final String COMPACTION_ENABLED = "COMPACTION_ENABLED";
    /**
     * Constant that denotes whether the table is compaction enabled by default
     */
    public static final boolean DEFAULT_COMPACTION_ENABLED = true;

    /**
     * the maximum size of the memstore after which
     * its contents are flushed onto the disk
     */
    public static final String MEMSTORE_FLUSHSIZE = "MEMSTORE_FLUSHSIZE";
    public static final long DEFAULT_MEMSTORE_FLUSH_SIZE = 1024 * 1024 * 128L;

    public static final String SPLIT_POLICY = "SPLIT_POLICY";
    public static final String FLUSH_POLICY = "FLUSH_POLICY";

    public static final String NORMALIZATION_ENABLED = "NORMALIZATION_ENABLED";
    public static final boolean DEFAULT_NORMALIZATION_ENABLED = false;

    public static final String MERGE_ENABLED = "MERGE_ENABLED";
    /**
     * Constant that denotes whether the table is merge enabled by default
     */
    public static final boolean DEFAULT_MERGE_ENABLED = true;

    public static final String SPLIT_ENABLED = "SPLIT_ENABLED";
    /**
     * Constant that denotes whether the table is split enabled by default
     */
    public static final boolean DEFAULT_SPLIT_ENABLED = true;


    public static final String PRIORITY = "PRIORITY";
    /**
     * Relative priority of the table used for rpc scheduling
     */
    private static final int DEFAULT_PRIORITY = HConstants.NORMAL_QOS;

    /**
     * Used by rest interface to access this metadata attribute
     * which denotes if it is a catalog table, either <code> hbase:meta </code>.
     */
    public static final String IS_META = "IS_META";


    public final static Map<String, String> DEFAULT_VALUES = new HashMap<>();

    static {
        DEFAULT_VALUES.put(MAX_FILESIZE, String.valueOf(DEFAULT_MAX_FILESIZE));
        DEFAULT_VALUES.put(READONLY, String.valueOf(DEFAULT_READONLY));
        DEFAULT_VALUES.put(MEMSTORE_FLUSHSIZE, String.valueOf(DEFAULT_MEMSTORE_FLUSH_SIZE));
        DEFAULT_VALUES.put(DURABILITY, DEFAULT_DURABILITY.name());
        DEFAULT_VALUES.put(REGION_REPLICATION, String.valueOf(DEFAULT_REGION_REPLICATION));
        DEFAULT_VALUES.put(COMPACTION_ENABLED, String.valueOf(DEFAULT_COMPACTION_ENABLED));
        DEFAULT_VALUES.put(NORMALIZATION_ENABLED, String.valueOf(DEFAULT_NORMALIZATION_ENABLED));
        DEFAULT_VALUES.put(MERGE_ENABLED, String.valueOf(DEFAULT_MERGE_ENABLED));
        DEFAULT_VALUES.put(SPLIT_ENABLED, String.valueOf(DEFAULT_SPLIT_ENABLED));
        DEFAULT_VALUES.put(PRIORITY, String.valueOf(DEFAULT_PRIORITY));
        DEFAULT_VALUES.put(SPLIT_POLICY, "");
        DEFAULT_VALUES.put(FLUSH_POLICY, "");
        DEFAULT_VALUES.put(IS_META, String.valueOf(false));
    }

    private final String name;
    private final Long maxFileSize;
    private final Boolean readOnly;
    private final Long memStoreFlushSize;
    private final Boolean compactionEnabled;
    private final Boolean normalizationEnabled;
    private final Boolean mergeEnabled;
    private final Boolean splitEnabled;
    private final String durability;
    private final Integer priority;
    private final String regionSplitPolicyClassName;
    private final String flushPolicyClassName;
    private final Map<String, String> configuration;
    private final Map<String, String> values;
    private final List<BaseColumnFamilyDesc> columnFamilyDescList;

    public BaseHTableDesc(Builder<?> builder) {
        this.name = builder.name;
        this.maxFileSize = builder.maxFileSize;
        this.readOnly = builder.readOnly;
        this.memStoreFlushSize = builder.memStoreFlushSize;
        this.compactionEnabled = builder.compactionEnabled;
        this.normalizationEnabled = builder.normalizationEnabled;
        this.mergeEnabled = builder.mergeEnabled;
        this.splitEnabled = builder.splitEnabled;
        this.durability = builder.durability;
        this.priority = builder.priority;
        this.regionSplitPolicyClassName = builder.regionSplitPolicyClassName;
        this.flushPolicyClassName = builder.flushPolicyClassName;
        this.configuration = builder.configuration;
        this.values = builder.values;
        this.columnFamilyDescList = builder.columnFamilyDescList;
    }

    public abstract static class Builder<HTD extends BaseHTableDesc> implements ConfigFilter {
        private final String name;
        private Long maxFileSize;
        private Boolean readOnly;
        private Long memStoreFlushSize;
        private Boolean compactionEnabled;
        private Boolean normalizationEnabled;
        private Boolean mergeEnabled;
        private Boolean splitEnabled;
        private String durability;
        private Integer priority;
        private String regionSplitPolicyClassName;
        private String flushPolicyClassName;
        private final Map<String, String> configuration;
        private final Map<String, String> values;
        private final List<BaseColumnFamilyDesc> columnFamilyDescList;

        protected Builder(String name) {
            this.name = name;
            this.configuration = new HashMap<>();
            this.values = new HashMap<>();
            this.columnFamilyDescList = new ArrayList<>();
        }

        @Override
        public Map<String, String> defaultValues() {
            return DEFAULT_VALUES;
        }

        public Builder<HTD> maxFileSize(Long maxFileSize) {
            if (intercept(MAX_FILESIZE, maxFileSize)) {
                return this;
            }
            if (maxFileSize <= 0) {
                return this;
            }
            this.maxFileSize = maxFileSize;
            this.setValue(MAX_FILESIZE, String.valueOf(maxFileSize));
            return this;
        }

        public Builder<HTD> readOnly(Boolean readOnly) {
            if (intercept(READONLY, readOnly)) {
                return this;
            }
            this.readOnly = readOnly;
            this.setValue(READONLY, String.valueOf(readOnly));
            return this;
        }

        public Builder<HTD> memStoreFlushSize(Long memStoreFlushSize) {
            if (intercept(MEMSTORE_FLUSHSIZE, memStoreFlushSize)) {
                return this;
            }
            if (memStoreFlushSize <= 0) {
                return this;
            }
            this.memStoreFlushSize = memStoreFlushSize;
            this.setValue(MEMSTORE_FLUSHSIZE, String.valueOf(memStoreFlushSize));
            return this;
        }

        public Builder<HTD> compactionEnabled(Boolean compactionEnabled) {
            if (intercept(COMPACTION_ENABLED, compactionEnabled)) {
                return this;
            }
            this.compactionEnabled = compactionEnabled;
            this.setValue(COMPACTION_ENABLED, String.valueOf(compactionEnabled));
            return this;
        }

        public Builder<HTD> normalizationEnabled(Boolean normalizationEnabled) {
            if (intercept(NORMALIZATION_ENABLED, normalizationEnabled)) {
                return this;
            }
            this.normalizationEnabled = normalizationEnabled;
            this.setValue(NORMALIZATION_ENABLED, String.valueOf(normalizationEnabled));
            return this;
        }

        public Builder<HTD> mergeEnabled(Boolean mergeEnabled) {
            if (intercept(MERGE_ENABLED, mergeEnabled)) {
                return this;
            }
            this.mergeEnabled = mergeEnabled;
            this.setValue(MERGE_ENABLED, String.valueOf(mergeEnabled));
            return this;
        }

        public Builder<HTD> splitEnabled(Boolean splitEnabled) {
            if (intercept(SPLIT_ENABLED, splitEnabled)) {
                return this;
            }
            this.splitEnabled = splitEnabled;
            this.setValue(SPLIT_ENABLED, String.valueOf(splitEnabled));
            return this;
        }

        public Builder<HTD> durability(String durability) {
            if (intercept(DURABILITY, durability)) {
                return this;
            }
            this.durability = durability;
            this.setValue(DURABILITY, String.valueOf(durability));
            return this;
        }

        public Builder<HTD> priority(Integer priority) {
            if (intercept(PRIORITY, priority)) {
                return this;
            }
            this.priority = priority;
            this.setValue(PRIORITY, String.valueOf(priority));
            return this;
        }

        public Builder<HTD> regionSplitPolicyClassName(String regionSplitPolicyClassName) {
            if (intercept(SPLIT_POLICY, regionSplitPolicyClassName)) {
                return this;
            }
            this.regionSplitPolicyClassName = regionSplitPolicyClassName;
            this.setValue(SPLIT_POLICY, regionSplitPolicyClassName);
            return this;
        }

        public Builder<HTD> flushPolicyClassName(String flushPolicyClassName) {
            if (intercept(FLUSH_POLICY, flushPolicyClassName)) {
                return this;
            }
            this.flushPolicyClassName = flushPolicyClassName;
            this.setValue(FLUSH_POLICY, flushPolicyClassName);
            return this;
        }

        public Builder<HTD> setConfiguration(String key, String value) {
            if (intercept(key, value, false)) {
                return this;
            }
            this.configuration.put(key, value);
            return this;
        }

        public Builder<HTD> setValue(String key, String value) {
            if (intercept(key, value)) {
                return this;
            }
            this.values.put(key, value);
            return this;
        }

        public Builder<HTD> addFamilyDesc(BaseColumnFamilyDesc columnFamilyDesc) {
            String name = columnFamilyDesc.getName();
            for (BaseColumnFamilyDesc familyDesc : this.columnFamilyDescList) {
                if (name.equals(familyDesc.getName())) {
                    throw new IllegalArgumentException(String.format("Column family %s already exists", name));
                }
            }
            this.columnFamilyDescList.add(columnFamilyDesc);
            return this;
        }

        public Builder<HTD> copyFrom(HTD htd) {
            maxFileSize(htd.getMaxFileSize())
                    .memStoreFlushSize(htd.getMemStoreFlushSize())
                    .readOnly(htd.getReadOnly())
                    .durability(htd.getDurability())
                    .priority(htd.getPriority())
                    .compactionEnabled(htd.getCompactionEnabled())
                    .normalizationEnabled(htd.getNormalizationEnabled())
                    .mergeEnabled(htd.getMergeEnabled())
                    .splitEnabled(htd.getSplitEnabled())
                    .regionSplitPolicyClassName(htd.getRegionSplitPolicyClassName())
                    .flushPolicyClassName(htd.getFlushPolicyClassName());
            if (!htd.getColumnFamilyDescList().isEmpty()) {
                htd.getColumnFamilyDescList().forEach(this::addFamilyDesc);
            }
            if (!htd.getConfiguration().isEmpty()) {
                htd.getConfiguration().forEach(this::setConfiguration);
            }
            if (!htd.getValues().isEmpty()) {
                htd.getValues().forEach(this::setValue);
            }
            return this;
        }

        public abstract HTD build();
    }

    public String getName() {
        return name;
    }

    public Long getMaxFileSize() {
        return maxFileSize;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public Long getMemStoreFlushSize() {
        return memStoreFlushSize;
    }

    public Boolean getCompactionEnabled() {
        return compactionEnabled;
    }

    public Boolean getNormalizationEnabled() {
        return normalizationEnabled;
    }

    public Boolean getMergeEnabled() {
        return mergeEnabled;
    }

    public Boolean getSplitEnabled() {
        return splitEnabled;
    }

    public String getDurability() {
        return durability;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getRegionSplitPolicyClassName() {
        return regionSplitPolicyClassName;
    }

    public String getFlushPolicyClassName() {
        return flushPolicyClassName;
    }

    public Map<String, String> getConfiguration() {
        return configuration;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public List<BaseColumnFamilyDesc> getColumnFamilyDescList() {
        return columnFamilyDescList;
    }

    public void appendColumnFamily(BaseColumnFamilyDesc columnFamilyDesc) {
        if (columnFamilyDesc == null) {
            throw new IllegalArgumentException("columnFamilyDesc is null");
        }
        if (this.getColumnFamilyDescList().isEmpty()) {
            this.columnFamilyDescList.add(columnFamilyDesc);
            return;
        }
        for (BaseColumnFamilyDesc cf : this.getColumnFamilyDescList()) {
            if (cf.getName().equals(columnFamilyDesc.getName())) {
                throw new IllegalArgumentException(String.format("Column family %s already exists", columnFamilyDesc.getName()));
            }
        }
        this.columnFamilyDescList.add(columnFamilyDesc);
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
