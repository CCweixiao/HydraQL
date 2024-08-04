/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hydraql.adapter.schema;

import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.KeepDeletedCells;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.regionserver.BloomType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leojie 2023/5/19 20:43
 */
public abstract class BaseColumnFamilyDesc {
  public static final String IS_MOB = "IS_MOB";
  public static final boolean DEFAULT_MOB = false;

  public static final String MOB_THRESHOLD = "MOB_THRESHOLD";
  public static final long DEFAULT_MOB_THRESHOLD = 100 * 1024;

  /**
   * Retain all cells across flushes and compactions even if they fall behind a delete tombstone. To
   * see all retained cells, do a 'raw' scan; see Scan#setRaw or pass RAW =&gt; true attribute in
   * the shell.
   */
  public static final String KEEP_DELETED_CELLS = "KEEP_DELETED_CELLS";
  public static final KeepDeletedCells DEFAULT_KEEP_DELETED = KeepDeletedCells.FALSE;

  /**
   * Default is not to keep a minimum of versions.
   */
  public static final String MIN_VERSIONS = "MIN_VERSIONS";
  public static final int DEFAULT_MIN_VERSIONS = 0;

  /**
   * Default number of versions of a record to keep.
   */
  public static final String MAX_VERSIONS = HConstants.VERSIONS;
  public static final int DEFAULT_MAX_VERSIONS = 1;

  public static final String NEW_VERSION_BEHAVIOR = "NEW_VERSION_BEHAVIOR";
  public static final boolean DEFAULT_NEW_VERSION_BEHAVIOR = false;

  public static final String REPLICATION_SCOPE = "REPLICATION_SCOPE";
  public static final int DEFAULT_REPLICATION_SCOPE = HConstants.REPLICATION_SCOPE_LOCAL;

  /**
   * Default setting for whether to cache data blocks in L1 tier. Only makes sense if more than one
   * tier in operations: i.e. if we have an L1 and a L2. This will be the cases if we are using
   * BucketCache.
   */
  public static final String CACHE_DATA_IN_L1 = "CACHE_DATA_IN_L1";
  public static final boolean DEFAULT_CACHE_DATA_IN_L1 = false;

  /**
   * Default setting for whether to try and serve this column family from memory or not.
   */
  public static final String IN_MEMORY = "IN_MEMORY";
  public static final boolean DEFAULT_IN_MEMORY = false;

  public static final String COMPRESSION = "COMPRESSION";
  public static final Compression.Algorithm DEFAULT_COMPRESSION = Compression.Algorithm.NONE;

  public static final String DATA_BLOCK_ENCODING = "DATA_BLOCK_ENCODING";
  public static final DataBlockEncoding DEFAULT_DATA_BLOCK_ENCODING = DataBlockEncoding.NONE;

  /**
   * Key for the BLOCKCACHE attribute. A more exact name would be CACHE_DATA_ON_READ because this
   * flag sets whether or not we cache DATA blocks. We always cache INDEX and BLOOM blocks; caching
   * these blocks cannot be disabled.
   */
  public static final String BLOCK_CACHE = "BLOCKCACHE";
  public static final boolean DEFAULT_BLOCK_CACHE = true;

  public static final String CACHE_DATA_ON_WRITE = "CACHE_DATA_ON_WRITE";
  public static final boolean DEFAULT_CACHE_DATA_ON_WRITE = false;

  public static final String CACHE_INDEX_ON_WRITE = "CACHE_INDEX_ON_WRITE";
  public static final boolean DEFAULT_CACHE_INDEX_ON_WRITE = false;

  public static final String CACHE_BLOOMS_ON_WRITE = "CACHE_BLOOMS_ON_WRITE";
  public static final boolean DEFAULT_CACHE_BLOOMS_ON_WRITE = false;

  public static final String EVICT_BLOCKS_ON_CLOSE = "EVICT_BLOCKS_ON_CLOSE";
  public static final boolean DEFAULT_EVICT_BLOCKS_ON_CLOSE = false;

  public static final String PREFETCH_BLOCKS_ON_OPEN = "PREFETCH_BLOCKS_ON_OPEN";
  public static final boolean DEFAULT_PREFETCH_BLOCKS_ON_OPEN = false;

  public static final String BLOCKSIZE = "BLOCKSIZE";
  public static final int DEFAULT_BLOCKSIZE = HConstants.DEFAULT_BLOCKSIZE;

  public static final String TTL = "TTL";
  public static final int DEFAULT_TTL = HConstants.FOREVER;

  public static final String BLOOMFILTER = "BLOOMFILTER";
  public static final BloomType DEFAULT_BLOOMFILTER = BloomType.ROW;

  public static final String STORAGE_POLICY = "STORAGE_POLICY";
  public static final String BLOCK_STORAGE_POLICY_KEY = "hbase.hstore.block.storage.policy";

  public final static Map<String, String> DEFAULT_VALUES = new HashMap<>();

  static {
    DEFAULT_VALUES.put(BLOCK_CACHE, String.valueOf(DEFAULT_BLOCK_CACHE));
    DEFAULT_VALUES.put(COMPRESSION, DEFAULT_COMPRESSION.name());
    DEFAULT_VALUES.put(MAX_VERSIONS, String.valueOf(DEFAULT_MAX_VERSIONS));
    DEFAULT_VALUES.put(MIN_VERSIONS, String.valueOf(DEFAULT_MIN_VERSIONS));
    DEFAULT_VALUES.put(TTL, String.valueOf(DEFAULT_TTL));
    DEFAULT_VALUES.put(BLOCKSIZE, String.valueOf(DEFAULT_BLOCKSIZE));
    DEFAULT_VALUES.put(IN_MEMORY, String.valueOf(DEFAULT_IN_MEMORY));
    DEFAULT_VALUES.put(BLOOMFILTER, DEFAULT_BLOOMFILTER.name());
    DEFAULT_VALUES.put(IS_MOB, String.valueOf(DEFAULT_MOB));
    DEFAULT_VALUES.put(MOB_THRESHOLD, String.valueOf(DEFAULT_MOB_THRESHOLD));
    DEFAULT_VALUES.put(KEEP_DELETED_CELLS, String.valueOf(DEFAULT_KEEP_DELETED));
    DEFAULT_VALUES.put(REPLICATION_SCOPE, String.valueOf(DEFAULT_REPLICATION_SCOPE));
    DEFAULT_VALUES.put(DATA_BLOCK_ENCODING, String.valueOf(DEFAULT_DATA_BLOCK_ENCODING));
    DEFAULT_VALUES.put(NEW_VERSION_BEHAVIOR, String.valueOf(DEFAULT_NEW_VERSION_BEHAVIOR));
    DEFAULT_VALUES.put(CACHE_DATA_IN_L1, String.valueOf(DEFAULT_CACHE_DATA_IN_L1));
    DEFAULT_VALUES.put(CACHE_DATA_ON_WRITE, String.valueOf(DEFAULT_CACHE_DATA_ON_WRITE));
    DEFAULT_VALUES.put(CACHE_INDEX_ON_WRITE, String.valueOf(DEFAULT_CACHE_INDEX_ON_WRITE));
    DEFAULT_VALUES.put(CACHE_BLOOMS_ON_WRITE, String.valueOf(DEFAULT_CACHE_BLOOMS_ON_WRITE));
    DEFAULT_VALUES.put(EVICT_BLOCKS_ON_CLOSE, String.valueOf(DEFAULT_EVICT_BLOCKS_ON_CLOSE));
    DEFAULT_VALUES.put(PREFETCH_BLOCKS_ON_OPEN, String.valueOf(DEFAULT_PREFETCH_BLOCKS_ON_OPEN));
  }

  private final String name;
  private final Integer replicationScope;
  private final Integer maxVersions;
  private final Integer minVersions;
  private final String compressionType;
  private final String storagePolicy;
  private final String bloomFilterType;
  private final Integer timeToLive;
  private final Integer blockSize;
  private final Boolean blockCacheEnabled;
  private final Boolean inMemory;
  private final String keepDeletedCells;
  private final Boolean newVersionBehavior;
  private final String dataBlockEncoding;
  private final Boolean cacheDataOnWrite;
  private final Boolean cacheDataInL1;
  private final Boolean cacheIndexesOnWrite;
  private final Boolean cacheBloomsOnWrite;
  private final Boolean evictBlocksOnClose;
  private final Boolean prefetchBlocksOnOpen;
  private final Map<String, String> configuration;
  private final Map<String, String> values;
  private final Boolean mobEnabled;
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
    this.newVersionBehavior = builder.newVersionBehavior;
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

  public abstract static class Builder<CF extends BaseColumnFamilyDesc> implements ConfigFilter {
    private final String name;
    private Integer replicationScope;
    private Integer maxVersions;
    private Integer minVersions;
    private String compressionType;
    private String storagePolicy;
    private String bloomFilterType;
    private Integer timeToLive;
    private Integer blockSize;
    private Boolean blockCacheEnabled;
    private Boolean inMemory;
    private String keepDeletedCells;
    private Boolean newVersionBehavior;
    private String dataBlockEncoding;
    private Boolean cacheDataOnWrite;
    private Boolean cacheDataInL1;
    private Boolean cacheIndexesOnWrite;
    private Boolean cacheBloomsOnWrite;
    private Boolean evictBlocksOnClose;
    private Boolean prefetchBlocksOnOpen;
    private final Map<String, String> configuration;
    private final Map<String, String> values;
    private Boolean mobEnabled;
    private Long mobThreshold;

    protected Builder(String name) {
      if (StringUtil.isBlank(name)) {
        throw new IllegalArgumentException("Column family name cannot be empty");
      }
      this.name = name;
      this.configuration = new HashMap<>();
      this.values = new HashMap<>();
    }

    @Override
    public Map<String, String> defaultValues() {
      return DEFAULT_VALUES;
    }

    public Builder<CF> setReplicationScope(Integer replicationScope) {
      if (intercept(REPLICATION_SCOPE, replicationScope)) {
        return this;
      }
      if (replicationScope != HConstants.REPLICATION_SCOPE_LOCAL
          && replicationScope != HConstants.REPLICATION_SCOPE_GLOBAL) {
        throw new IllegalArgumentException("The value of REPLICATION_SCOPE can only be 0 or 1");
      }
      this.replicationScope = replicationScope;
      this.setValue(REPLICATION_SCOPE, String.valueOf(replicationScope));
      return this;
    }

    public Builder<CF> setMaxVersions(Integer maxVersions) {
      if (intercept(MAX_VERSIONS, maxVersions)) {
        return this;
      }
      if (maxVersions < 1) {
        throw new IllegalArgumentException("The max versions must be > 0");
      }
      this.maxVersions = maxVersions;
      this.setValue(MAX_VERSIONS, String.valueOf(maxVersions));
      return this;
    }

    public Builder<CF> setMinVersions(Integer minVersions) {
      if (intercept(MIN_VERSIONS, minVersions)) {
        return this;
      }
      if (minVersions < 0) {
        throw new IllegalArgumentException("The min versions must be >= 0");
      }
      this.minVersions = minVersions;
      this.setValue(MIN_VERSIONS, String.valueOf(minVersions));
      return this;
    }

    public Builder<CF> setCompressionType(String compressionType) {
      if (intercept(COMPRESSION, compressionType)) {
        return this;
      }
      Compression.Algorithm algorithm = CompressionAlgorithmConverter.apply(compressionType);
      this.compressionType = algorithm.name();
      this.setValue(COMPRESSION, this.compressionType);
      return this;
    }

    public Builder<CF> setStoragePolicy(String storagePolicy) {
      if (intercept(STORAGE_POLICY, storagePolicy)) {
        return this;
      }
      this.storagePolicy = storagePolicy;
      this.setValue(STORAGE_POLICY, storagePolicy);
      return this;
    }

    public Builder<CF> setBloomFilterType(String bloomFilterType) {
      if (intercept(BLOOMFILTER, bloomFilterType)) {
        return this;
      }
      BloomType bloomType = BloomTypeConverter.apply(bloomFilterType);
      this.bloomFilterType = bloomType.name();
      this.setValue(BLOOMFILTER, this.bloomFilterType);
      return this;
    }

    public Builder<CF> setTimeToLive(Integer timeToLive) {
      if (intercept(TTL, timeToLive)) {
        return this;
      }
      if (timeToLive < 0) {
        throw new IllegalArgumentException("TTL must be > 0");
      }
      this.timeToLive = timeToLive;
      this.setValue(TTL, String.valueOf(timeToLive));
      return this;
    }

    public Builder<CF> setBlockSize(Integer blockSize) {
      if (intercept(BLOCKSIZE, blockSize)) {
        return this;
      }
      if (blockSize < 0) {
        throw new IllegalArgumentException(
            "blockSize must be > 0, and the default blockSize is 64KB");
      }
      this.blockSize = blockSize;
      this.setValue(BLOCKSIZE, String.valueOf(blockSize));
      return this;
    }

    public Builder<CF> setBlockCacheEnabled(Boolean blockCacheEnabled) {
      if (intercept(BLOCKSIZE, blockCacheEnabled)) {
        return this;
      }
      this.blockCacheEnabled = blockCacheEnabled;
      this.setValue(BLOCK_CACHE, String.valueOf(blockCacheEnabled));
      return this;
    }

    public Builder<CF> setInMemory(Boolean inMemory) {
      if (intercept(IN_MEMORY, inMemory)) {
        return this;
      }
      this.inMemory = inMemory;
      this.setValue(IN_MEMORY, String.valueOf(inMemory));
      return this;
    }

    public Builder<CF> setKeepDeletedCells(String keepDeletedCells) {
      if (intercept(KEEP_DELETED_CELLS, keepDeletedCells)) {
        return this;
      }
      KeepDeletedCells kdc = KeepDeletedCellsConverter.apply(keepDeletedCells);
      this.keepDeletedCells = kdc.name();
      this.setValue(KEEP_DELETED_CELLS, this.keepDeletedCells);
      return this;
    }

    public Builder<CF> setNewVersionBehavior(Boolean newVersionBehavior) {
      if (intercept(NEW_VERSION_BEHAVIOR, newVersionBehavior)) {
        return this;
      }
      this.newVersionBehavior = newVersionBehavior;
      this.setValue(NEW_VERSION_BEHAVIOR, String.valueOf(newVersionBehavior));
      return this;
    }

    public Builder<CF> setDataBlockEncoding(String dataBlockEncoding) {
      if (intercept(DATA_BLOCK_ENCODING, dataBlockEncoding)) {
        return this;
      }
      DataBlockEncoding encoding = DataBlockEncodingConverter.apply(dataBlockEncoding);
      this.dataBlockEncoding = encoding.name();
      this.setValue(DATA_BLOCK_ENCODING, this.dataBlockEncoding);
      return this;
    }

    public Builder<CF> setCacheDataOnWrite(Boolean cacheDataOnWrite) {
      if (intercept(CACHE_DATA_ON_WRITE, cacheDataOnWrite)) {
        return this;
      }
      this.cacheDataOnWrite = cacheDataOnWrite;
      this.setValue(CACHE_DATA_ON_WRITE, String.valueOf(cacheDataOnWrite));
      return this;
    }

    public Builder<CF> setCacheDataInL1(Boolean cacheDataInL1) {
      if (intercept(CACHE_DATA_IN_L1, cacheDataInL1)) {
        return this;
      }
      this.cacheDataInL1 = cacheDataInL1;
      this.setValue(CACHE_DATA_IN_L1, String.valueOf(cacheDataInL1));
      return this;
    }

    public Builder<CF> setCacheIndexesOnWrite(Boolean cacheIndexesOnWrite) {
      if (intercept(CACHE_INDEX_ON_WRITE, cacheIndexesOnWrite)) {
        return this;
      }
      this.cacheIndexesOnWrite = cacheIndexesOnWrite;
      this.setValue(CACHE_INDEX_ON_WRITE, String.valueOf(cacheIndexesOnWrite));
      return this;
    }

    public Builder<CF> setCacheBloomsOnWrite(Boolean cacheBloomsOnWrite) {
      if (intercept(CACHE_BLOOMS_ON_WRITE, cacheBloomsOnWrite)) {
        return this;
      }
      this.cacheBloomsOnWrite = cacheBloomsOnWrite;
      this.setValue(CACHE_BLOOMS_ON_WRITE, String.valueOf(cacheBloomsOnWrite));
      return this;
    }

    public Builder<CF> setEvictBlocksOnClose(Boolean evictBlocksOnClose) {
      if (intercept(EVICT_BLOCKS_ON_CLOSE, evictBlocksOnClose)) {
        return this;
      }
      this.evictBlocksOnClose = evictBlocksOnClose;
      this.setValue(EVICT_BLOCKS_ON_CLOSE, String.valueOf(evictBlocksOnClose));
      return this;
    }

    public Builder<CF> setPrefetchBlocksOnOpen(Boolean prefetchBlocksOnOpen) {
      if (intercept(PREFETCH_BLOCKS_ON_OPEN, prefetchBlocksOnOpen)) {
        return this;
      }
      this.prefetchBlocksOnOpen = prefetchBlocksOnOpen;
      this.setValue(PREFETCH_BLOCKS_ON_OPEN, String.valueOf(prefetchBlocksOnOpen));
      return this;
    }

    public Builder<CF> setMobEnabled(Boolean mobEnabled) {
      if (intercept(IS_MOB, mobEnabled)) {
        return this;
      }
      this.mobEnabled = mobEnabled;
      this.setValue(IS_MOB, String.valueOf(mobEnabled));
      return this;
    }

    public Builder<CF> setMobThreshold(Long mobThreshold) {
      if (intercept(MOB_THRESHOLD, mobThreshold)) {
        return this;
      }
      if (mobThreshold <= 0) {
        throw new IllegalArgumentException("The MOB_THRESHOLD must be > 0");
      }
      this.mobThreshold = mobThreshold;
      this.setValue(MOB_THRESHOLD, String.valueOf(mobThreshold));
      return this;
    }

    public Builder<CF> setConfiguration(String key, String value) {
      if (intercept(key, value, true)) {
        return this;
      }
      this.configuration.put(key, value);
      return this;
    }

    public Builder<CF> setValue(String key, String value) {
      if (intercept(key, value)) {
        return this;
      }
      this.values.put(key, value);
      return this;
    }

    public Builder<CF> copyFrom(CF cf) {
      this.setReplicationScope(cf.getReplicationScope()).setMaxVersions(cf.getMaxVersions())
          .setMinVersions(cf.getMinVersions()).setCompressionType(cf.getCompressionType())
          .setBloomFilterType(cf.getBloomFilterType()).setTimeToLive(cf.getTimeToLive())
          .setBlockSize(cf.getBlockSize()).setBlockCacheEnabled(cf.getBlockCacheEnabled())
          .setInMemory(cf.getInMemory()).setKeepDeletedCells(cf.getKeepDeletedCells())
          .setNewVersionBehavior(cf.getNewVersionBehavior())
          .setDataBlockEncoding(cf.getDataBlockEncoding())
          .setCacheDataOnWrite(cf.getCacheDataOnWrite()).setCacheDataInL1(cf.getCacheDataInL1())
          .setCacheIndexesOnWrite(cf.getCacheIndexesOnWrite())
          .setCacheBloomsOnWrite(cf.getCacheDataOnWrite())
          .setEvictBlocksOnClose(cf.getEvictBlocksOnClose())
          .setPrefetchBlocksOnOpen(cf.getPrefetchBlocksOnOpen());

      if (!cf.getConfiguration().isEmpty()) {
        cf.getConfiguration().forEach(this::setConfiguration);
      }

      if (!cf.getValues().isEmpty()) {
        cf.getValues().forEach(this::setValue);
      }

      return this;
    }

    public abstract CF build();
  }

  public String getName() {
    return name;
  }

  public Integer getReplicationScope() {
    return replicationScope;
  }

  public Integer getMaxVersions() {
    return maxVersions;
  }

  public Integer getMinVersions() {
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

  public Integer getTimeToLive() {
    return timeToLive;
  }

  public String getHumanReadableTTL() {
    Integer interval = this.getTimeToLive();
    if (interval == null) {
      interval = Integer.MAX_VALUE;
    }

    StringBuilder sb = new StringBuilder();
    int days, hours, minutes, seconds;

    // edge cases first
    if (interval == Integer.MAX_VALUE) {
      sb.append("FOREVER");
      return sb.toString();
    }
    if (interval < HConstants.MINUTE_IN_SECONDS) {
      sb.append(interval);
      sb.append(" SECOND").append(interval == 1 ? "" : "S");
      return sb.toString();
    }

    days = interval / HConstants.DAY_IN_SECONDS;
    hours = (interval - HConstants.DAY_IN_SECONDS * days) / HConstants.HOUR_IN_SECONDS;
    minutes = (interval - HConstants.DAY_IN_SECONDS * days - HConstants.HOUR_IN_SECONDS * hours)
        / HConstants.MINUTE_IN_SECONDS;
    seconds = interval - HConstants.DAY_IN_SECONDS * days - HConstants.HOUR_IN_SECONDS * hours
        - HConstants.MINUTE_IN_SECONDS * minutes;

    sb.append(interval);
    sb.append(" SECONDS (");

    if (days > 0) {
      sb.append(days);
      sb.append(" DAY").append(days == 1 ? "" : "S");
    }

    if (hours > 0) {
      sb.append(days > 0 ? " " : "");
      sb.append(hours);
      sb.append(" HOUR").append(hours == 1 ? "" : "S");
    }

    if (minutes > 0) {
      sb.append(days + hours > 0 ? " " : "");
      sb.append(minutes);
      sb.append(" MINUTE").append(minutes == 1 ? "" : "S");
    }

    if (seconds > 0) {
      sb.append(days + hours + minutes > 0 ? " " : "");
      sb.append(seconds);
      sb.append(" SECOND").append(minutes == 1 ? "" : "S");
    }

    sb.append(")");

    return sb.toString();
  }

  public Integer getBlockSize() {
    return blockSize;
  }

  public Boolean getBlockCacheEnabled() {
    return blockCacheEnabled;
  }

  public Boolean getInMemory() {
    return inMemory;
  }

  public String getKeepDeletedCells() {
    return keepDeletedCells;
  }

  public Boolean getNewVersionBehavior() {
    return newVersionBehavior;
  }

  public String getDataBlockEncoding() {
    return dataBlockEncoding;
  }

  public Boolean getCacheDataOnWrite() {
    return cacheDataOnWrite;
  }

  public Boolean getCacheDataInL1() {
    return cacheDataInL1;
  }

  public Boolean getCacheIndexesOnWrite() {
    return cacheIndexesOnWrite;
  }

  public Boolean getCacheBloomsOnWrite() {
    return cacheBloomsOnWrite;
  }

  public Boolean getEvictBlocksOnClose() {
    return evictBlocksOnClose;
  }

  public Boolean getPrefetchBlocksOnOpen() {
    return prefetchBlocksOnOpen;
  }

  public Map<String, String> getConfiguration() {
    return configuration;
  }

  public Map<String, String> getValues() {
    return values;
  }

  public Boolean getMobEnabled() {
    return mobEnabled;
  }

  public Long getMobThreshold() {
    return mobThreshold;
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
