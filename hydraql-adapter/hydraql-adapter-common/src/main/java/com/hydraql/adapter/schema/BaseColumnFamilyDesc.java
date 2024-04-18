package com.hydraql.adapter.schema;

import com.hydraql.common.constants.HBaseConstants;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.KeepDeletedCells;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leojie 2023/5/19 20:43
 */
public abstract class BaseColumnFamilyDesc {
    public static final String IS_MOB = "IS_MOB";
    public static final String MOB_THRESHOLD = "MOB_THRESHOLD";
    public static final String KEEP_DELETED_CELLS = "KEEP_DELETED_CELLS";
    public static final String MIN_VERSIONS = "MIN_VERSIONS";
    public static final String MAX_VERSIONS = "VERSIONS";
    public static final String REPLICATION_SCOPE = "REPLICATION_SCOPE";
    public static final String CACHE_DATA_IN_L1 = "CACHE_DATA_IN_L1";
    public static final String IN_MEMORY = "IN_MEMORY";
    public static final String COMPRESSION = "COMPRESSION";
    public static final String DATA_BLOCK_ENCODING = "DATA_BLOCK_ENCODING";
    public static final String BLOCKCACHE = "BLOCKCACHE";
    public static final String CACHE_DATA_ON_WRITE = "CACHE_DATA_ON_WRITE";
    public static final String CACHE_INDEX_ON_WRITE = "CACHE_INDEX_ON_WRITE";
    public static final String CACHE_BLOOMS_ON_WRITE = "CACHE_BLOOMS_ON_WRITE";
    public static final String EVICT_BLOCKS_ON_CLOSE = "EVICT_BLOCKS_ON_CLOSE";
    public static final String PREFETCH_BLOCKS_ON_OPEN = "PREFETCH_BLOCKS_ON_OPEN";
    public static final String BLOCKSIZE = "BLOCKSIZE";
    public static final String TTL = "TTL";
    public static final String BLOOMFILTER = "BLOOMFILTER";
    public static final String STORAGE_POLICY = "STORAGE_POLICY";
    public static final String BLOCK_STORAGE_POLICY_KEY = "hbase.hstore.block.storage.policy";

    private final String name;
    private final int replicationScope;
    private final int maxVersions;
    private final int minVersions;
    private final String compressionType;
    private final String storagePolicy;
    private final String bloomFilterType;
    private final int timeToLive;
    private final int blockSize;
    private final boolean blockCacheEnabled;
    private final boolean inMemory;
    private final String keepDeletedCells;
    private final String dataBlockEncoding;
    private final boolean cacheDataOnWrite;
    private final boolean cacheDataInL1;
    private final boolean cacheIndexesOnWrite;
    private final boolean cacheBloomsOnWrite;
    private final boolean evictBlocksOnClose;
    private final boolean prefetchBlocksOnOpen;
    private final Map<String, String> configuration;
    private final Map<String, String> values;
    private final boolean mobEnabled;
    private final Long mobThreshold;

    public BaseColumnFamilyDesc(Builder<?> builder) {
        this.name = builder.name;
        this.replicationScope = builder.replicationScope;
        this.maxVersions = builder.maxVersions;
        this.minVersions = builder.minVersions;
        this.compressionType = builder.compressionType;
        this.storagePolicy = builder.storagePolicy;
        this.bloomFilterType = builder.bloomFilterType;
        this.timeToLive = builder.timeToLive;
        this.blockSize = builder.blockSize;
        this.blockCacheEnabled = builder.blockCacheEnabled;
        this.inMemory = builder.inMemory;
        this.keepDeletedCells = builder.keepDeletedCells;
        this.dataBlockEncoding = builder.dataBlockEncoding;
        this.cacheDataOnWrite = builder.cacheDataOnWrite;
        this.cacheDataInL1 = builder.cacheDataInL1;
        this.cacheIndexesOnWrite = builder.cacheIndexesOnWrite;
        this.cacheBloomsOnWrite = builder.cacheBloomsOnWrite;
        this.evictBlocksOnClose = builder.evictBlocksOnClose;
        this.prefetchBlocksOnOpen = builder.prefetchBlocksOnOpen;
        this.configuration = builder.configuration;
        this.values = builder.values;
        this.mobEnabled = builder.mobEnabled;
        this.mobThreshold = builder.mobThreshold;
    }

    public abstract static class Builder<CF extends BaseColumnFamilyDesc> implements HTableSchemaInterceptor {
        private final String name;
        private int replicationScope = 0;
        private int maxVersions = 1;
        private int minVersions = 0;
        private String compressionType = Compression.Algorithm.NONE.getName();
        private String storagePolicy;
        private String bloomFilterType = BloomType.ROW.toString();
        private int timeToLive = Integer.MAX_VALUE;
        private int blockSize = 64 * 1024;
        private boolean blockCacheEnabled = true;
        private boolean inMemory = false;
        private String keepDeletedCells = KeepDeletedCells.FALSE.name();
        private String dataBlockEncoding = DataBlockEncoding.NONE.toString();
        private boolean cacheDataOnWrite = false;
        private boolean cacheDataInL1 = false;
        private boolean cacheIndexesOnWrite = false;
        private boolean cacheBloomsOnWrite = false;
        private boolean evictBlocksOnClose = false;
        private boolean prefetchBlocksOnOpen = false;
        private final Map<String, String> configuration;
        private final Map<String, String> values;
        private boolean mobEnabled = false;
        private Long mobThreshold = 102400L;

        protected Builder(String name) {
            this.name = name;
            this.configuration = new HashMap<>();
            this.values = new HashMap<>();
        }

        public Builder<CF> parseFrom(BaseColumnFamilyDesc source) {
            this.bloomFilterType = source.bloomFilterType;
            this.replicationScope = source.replicationScope;
            this.maxVersions = source.maxVersions;
            this.minVersions = source.minVersions;
            this.compressionType = source.compressionType;
            this.storagePolicy = source.storagePolicy;
            this.timeToLive = source.timeToLive;
            this.blockSize = source.blockSize;
            this.inMemory = source.inMemory;
            this.blockCacheEnabled = source.blockCacheEnabled;
            this.keepDeletedCells = source.keepDeletedCells;
            this.dataBlockEncoding = source.dataBlockEncoding;
            this.cacheDataOnWrite = source.cacheDataOnWrite;
            this.cacheDataInL1 = source.cacheDataInL1;
            this.cacheIndexesOnWrite = source.cacheIndexesOnWrite;
            this.cacheBloomsOnWrite = source.cacheBloomsOnWrite;
            this.evictBlocksOnClose = source.cacheIndexesOnWrite;
            this.prefetchBlocksOnOpen = source.prefetchBlocksOnOpen;
            if (source.configuration != null && !source.configuration.isEmpty()) {
                this.configuration.putAll(source.configuration);
            }
            if (source.values != null && !source.values.isEmpty()) {
                this.values.putAll(source.values);
            }
            this.mobEnabled = source.mobEnabled;
            this.mobThreshold = source.mobThreshold;
            return this;
        }

        public Builder<CF> replicationScope(int replicationScope) {
            verifyKey(REPLICATION_SCOPE);
            if (replicationScope != 0 && replicationScope != 1) {
                throw new IllegalArgumentException("The value of REPLICATION_SCOPE can only be 0 or 1");
            }
            this.replicationScope = replicationScope;
            this.setValue(REPLICATION_SCOPE, String.valueOf(replicationScope));
            return this;
        }


        public Builder<CF> maxVersions(int maxVersions) {
            verifyKey(MAX_VERSIONS);
            if (maxVersions < 1) {
                maxVersions = 1;
            }
            this.maxVersions = maxVersions;
            this.setValue(MAX_VERSIONS, String.valueOf(maxVersions));
            return this;
        }

        public Builder<CF> minVersions(int minVersions) {
            verifyKey(MIN_VERSIONS);
            if (minVersions < 0) {
                minVersions = 0;
            }
            this.minVersions = minVersions;
            this.setValue(MIN_VERSIONS, String.valueOf(minVersions));
            return this;
        }

        public Builder<CF> compressionType(String compressionType) {
            verifyKey(COMPRESSION);
            if (StringUtil.isBlank(compressionType)) {
                compressionType = Compression.Algorithm.NONE.getName();
            }
            this.compressionType = compressionType;
            this.setValue(COMPRESSION, compressionType);
            return this;
        }

        public Builder<CF> storagePolicy(String storagePolicy) {
            verifyKey(STORAGE_POLICY);
            if (StringUtil.isBlank(storagePolicy)) {
                return this;
            }
            this.storagePolicy = storagePolicy;
            this.setValue(STORAGE_POLICY, storagePolicy);
            return this;
        }

        public Builder<CF> bloomFilterType(String bloomFilterType) {
            verifyKey(BLOOMFILTER);
            if (StringUtil.isBlank(bloomFilterType)) {
                bloomFilterType = BloomType.ROW.toString();
            }
            this.bloomFilterType = bloomFilterType;
            this.setValue(BLOOMFILTER, bloomFilterType);
            return this;
        }

        public Builder<CF> timeToLive(int timeToLive) {
            verifyKey(TTL);
            if (timeToLive < 0) {
                timeToLive = Integer.MAX_VALUE;
            }
            this.timeToLive = timeToLive;
            this.setValue(TTL, String.valueOf(timeToLive));
            return this;
        }

        public Builder<CF> blockSize(int blockSize) {
            verifyKey(BLOCKSIZE);
            if (blockSize < 0) {
                blockSize = 64 * 1024;
            }
            this.blockSize = blockSize;
            this.setValue(BLOCKSIZE, String.valueOf(blockSize));
            return this;
        }

        public Builder<CF> blockCacheEnabled(boolean blockCacheEnabled) {
            verifyKey(BLOCKCACHE);
            this.blockCacheEnabled = blockCacheEnabled;
            this.setValue(BLOCKCACHE, String.valueOf(blockCacheEnabled));
            return this;
        }

        public Builder<CF> inMemory(boolean inMemory) {
            verifyKey(IN_MEMORY);
            this.inMemory = inMemory;
            this.setValue(IN_MEMORY, String.valueOf(inMemory));
            return this;
        }

        public Builder<CF> keepDeletedCells(String keepDeletedCells) {
            verifyKey(KEEP_DELETED_CELLS);
            if (StringUtil.isNotBlank(keepDeletedCells)) {
                keepDeletedCells = KeepDeletedCells.FALSE.name();
            }
            this.keepDeletedCells = keepDeletedCells;
            this.setValue(KEEP_DELETED_CELLS, keepDeletedCells);
            return this;
        }

        public Builder<CF> dataBlockEncoding(String dataBlockEncoding) {
            verifyKey(DATA_BLOCK_ENCODING);
            this.dataBlockEncoding = dataBlockEncoding;
            if (StringUtil.isBlank(dataBlockEncoding)) {
                dataBlockEncoding = DataBlockEncoding.NONE.toString();
            }
            this.setValue(DATA_BLOCK_ENCODING, dataBlockEncoding);
            return this;
        }

        public Builder<CF> cacheDataOnWrite(boolean cacheDataOnWrite) {
            verifyKey(CACHE_DATA_ON_WRITE);
            this.cacheDataOnWrite = cacheDataOnWrite;
            this.setValue(CACHE_DATA_ON_WRITE, String.valueOf(cacheDataOnWrite));
            return this;
        }

        public Builder<CF> cacheDataInL1(boolean cacheDataInL1) {
            verifyKey(CACHE_DATA_IN_L1);
            this.cacheDataInL1 = cacheDataInL1;
            this.setValue(CACHE_DATA_IN_L1, String.valueOf(cacheDataInL1));
            return this;
        }

        public Builder<CF> cacheIndexesOnWrite(boolean cacheIndexesOnWrite) {
            verifyKey(CACHE_INDEX_ON_WRITE);
            this.cacheIndexesOnWrite = cacheIndexesOnWrite;
            this.setValue(CACHE_INDEX_ON_WRITE, String.valueOf(cacheIndexesOnWrite));
            return this;
        }

        public Builder<CF> cacheBloomsOnWrite(boolean cacheBloomsOnWrite) {
            verifyKey(CACHE_BLOOMS_ON_WRITE);
            this.cacheBloomsOnWrite = cacheBloomsOnWrite;
            this.setValue(CACHE_BLOOMS_ON_WRITE, String.valueOf(cacheBloomsOnWrite));
            return this;
        }

        public Builder<CF> evictBlocksOnClose(boolean evictBlocksOnClose) {
            verifyKey(EVICT_BLOCKS_ON_CLOSE);
            this.evictBlocksOnClose = evictBlocksOnClose;
            this.setValue(EVICT_BLOCKS_ON_CLOSE, String.valueOf(evictBlocksOnClose));
            return this;
        }

        public Builder<CF> prefetchBlocksOnOpen(boolean prefetchBlocksOnOpen) {
            verifyKey(PREFETCH_BLOCKS_ON_OPEN);
            this.prefetchBlocksOnOpen = prefetchBlocksOnOpen;
            this.setValue(PREFETCH_BLOCKS_ON_OPEN, String.valueOf(prefetchBlocksOnOpen));
            return this;
        }

        public Builder<CF> setConfiguration(String key, String value) {
            if (StringUtil.isBlank(key)) {
                return this;
            }
            verifyConfiguration(key, value);
            this.configuration.put(key, value);
            return this;
        }

        public Builder<CF> setValue(String key, String value) {
            if (StringUtil.isBlank(key)) {
                return this;
            }
            this.values.put(key, value);
            return this;
        }

        public Builder<CF> mobEnabled(boolean mobEnabled) {
            verifyKey(IS_MOB);
            this.mobEnabled = mobEnabled;
            this.setValue(IS_MOB, String.valueOf(mobEnabled));
            return this;
        }

        public Builder<CF> mobThreshold(long mobThreshold) {
            verifyKey(MOB_THRESHOLD);
            if (mobThreshold <= 0) {
                mobThreshold = 102400L;
            }
            this.mobThreshold = mobThreshold;
            this.setValue(MOB_THRESHOLD, String.valueOf(mobThreshold));
            return this;
        }

        public abstract CF build();
    }

    public String getNameAsString() {
        return name;
    }

    public byte[] getName() {
        return Bytes.toBytes(name);
    }

    public int getReplicationScope() {
        return replicationScope;
    }

    public int getMaxVersions() {
        return maxVersions;
    }

    public int getMinVersions() {
        return minVersions;
    }

    public String getCompressionType() {
        return compressionType;
    }

    public String getStoragePolicy() {
        return storagePolicy;
    }

    public String getBloomFilterType() {
        return bloomFilterType;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public boolean isBlockCacheEnabled() {
        return blockCacheEnabled;
    }

    public boolean isInMemory() {
        return inMemory;
    }

    public String getKeepDeletedCells() {
        return keepDeletedCells;
    }

    public String getDataBlockEncoding() {
        return dataBlockEncoding;
    }

    public boolean isCacheDataOnWrite() {
        return cacheDataOnWrite;
    }

    public boolean isCacheDataInL1() {
        return cacheDataInL1;
    }

    public boolean isCacheIndexesOnWrite() {
        return cacheIndexesOnWrite;
    }

    public boolean isCacheBloomsOnWrite() {
        return cacheBloomsOnWrite;
    }

    public boolean isEvictBlocksOnClose() {
        return evictBlocksOnClose;
    }

    public boolean isPrefetchBlocksOnOpen() {
        return prefetchBlocksOnOpen;
    }

    public void setConfiguration(String key, String value) {
        if (StringUtil.isBlank(key)) {
            return;
        }
        this.configuration.put(key, value);
    }

    public void setValue(String key, String value) {
        if (StringUtil.isBlank(key)) {
            return;
        }
        this.values.put(key, value);
    }

    public Map<String, String> getConfiguration() {
        return configuration;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public boolean isMobEnabled() {
        return mobEnabled;
    }

    public long getMobThreshold() {
        return mobThreshold;
    }

    public String humanReadableTTL(long interval) {
        StringBuilder sb = new StringBuilder();
        if (interval == HBaseConstants.DEFAULT_TTL) {
            sb.append("FOREVER");
            return sb.toString();
        } else if (interval < 60L) {
            sb.append(interval);
            sb.append(" 秒");
            return sb.toString();
        } else {
            int days = (int) (interval / 86400L);
            int hours = (int) (interval - (86400L * days)) / 3600;
            int minutes = (int) (interval - (86400L * days) - (long) (3600 * hours)) / 60;
            int seconds = (int) (interval - (86400L * days) - (long) (3600 * hours) - (long) (60 * minutes));
            sb.append(interval);
            sb.append(" 秒 (");
            if (days > 0) {
                sb.append(days);
                sb.append(" 天");
            }

            if (hours > 0) {
                sb.append(days > 0 ? " " : "");
                sb.append(hours);
                sb.append(" 小时");
            }

            if (minutes > 0) {
                sb.append(days + hours > 0 ? " " : "");
                sb.append(minutes);
                sb.append(" 分钟");
            }

            if (seconds > 0) {
                sb.append(days + hours + minutes > 0 ? " " : "");
                sb.append(seconds);
                sb.append(" 秒");
            }

            sb.append(")");
            return sb.toString();
        }
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return getClass() == obj.getClass();
    }
}
