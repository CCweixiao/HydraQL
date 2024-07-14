package com.hydraql.adapter.schema;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.KeepDeletedCells;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.Map;

import static com.hydraql.adapter.schema.BaseColumnFamilyDesc.BLOCK_STORAGE_POLICY_KEY;
import static com.hydraql.adapter.schema.BaseColumnFamilyDesc.STORAGE_POLICY;

/**
 * @author leojie 2023/5/17 22:43
 */
public class ColumnFamilyDescriptorConverter extends BaseColumnFamilyDescriptorConverter<ColumnFamilyDesc, HColumnDescriptor> {
    public ColumnFamilyDescriptorConverter(ColumnFamilyDesc columnFamilyDesc) {
        super(columnFamilyDesc);
    }

    @Override
    protected HColumnDescriptor doForward(ColumnFamilyDesc cfd) {
        HColumnDescriptor cd = new HColumnDescriptor(cfd.getName());
        if (compareNeedSet(cd.getScope(), cfd.getReplicationScope())) {
            cd.setScope(cfd.getReplicationScope());
        }

        if (compareNeedSet(cd.getMaxVersions(), cfd.getMaxVersions())) {
            cd.setMaxVersions(cfd.getMaxVersions());
        }

        if (compareNeedSet(cd.getMinVersions(), cfd.getMinVersions())) {
            cd.setMinVersions(cfd.getMinVersions());
        }

        Compression.Algorithm compressionType = CompressionAlgorithmConverter.apply(cfd.getCompressionType());
        if (compareNeedSet(cd.getCompressionType().name(), compressionType.name())) {
            cd.setCompressionType(compressionType);
        }

        BloomType bloomFilterType = BloomTypeConverter.apply(cfd.getBloomFilterType());
        if (compareNeedSet(cd.getBloomFilterType().name(), bloomFilterType.name())) {
            cd.setBloomFilterType(bloomFilterType);
        }

        if (compareNeedSet(cd.getTimeToLive(), cfd.getTimeToLive())) {
            cd.setTimeToLive(cfd.getTimeToLive());
        }

        if (compareNeedSet(cd.getBlocksize(), cfd.getBlockSize())) {
            cd.setBlocksize(cfd.getBlockSize());
        }

        if (compareNeedSet(cd.isBlockCacheEnabled(), cfd.getBlockCacheEnabled())) {
            cd.setBlockCacheEnabled(cfd.getBlockCacheEnabled());
        }

        if (compareNeedSet(cd.isInMemory(), cfd.getInMemory())) {
            cd.setInMemory(cfd.getInMemory());
        }

        KeepDeletedCells keepDeletedCells = KeepDeletedCellsConverter.apply(cfd.getKeepDeletedCells());
        if (compareNeedSet(cd.getKeepDeletedCells().name(), keepDeletedCells.name())) {
            cd.setKeepDeletedCells(keepDeletedCells);
        }

        DataBlockEncoding dataBlockEncoding = DataBlockEncodingConverter.apply(cfd.getDataBlockEncoding());
        if (compareNeedSet(cd.getDataBlockEncoding().name(), dataBlockEncoding.name())) {
            cd.setDataBlockEncoding(dataBlockEncoding);
        }

        if (compareNeedSet(cd.isCacheBloomsOnWrite(), cfd.getCacheBloomsOnWrite())) {
            cd.setCacheBloomsOnWrite(cfd.getCacheBloomsOnWrite());
        }

        if (compareNeedSet(cd.isCacheIndexesOnWrite(), cfd.getCacheIndexesOnWrite())) {
            cd.setCacheIndexesOnWrite(cfd.getCacheIndexesOnWrite());
        }

        if (compareNeedSet(cd.isCacheBloomsOnWrite(), cfd.getCacheBloomsOnWrite())) {
            cd.setCacheBloomsOnWrite(cfd.getCacheBloomsOnWrite());
        }

        if (compareNeedSet(cd.isEvictBlocksOnClose(), cfd.getEvictBlocksOnClose())) {
            cd.setEvictBlocksOnClose(cfd.getEvictBlocksOnClose());
        }

        if (compareNeedSet(cd.isPrefetchBlocksOnOpen(), cfd.getPrefetchBlocksOnOpen())) {
            cd.setPrefetchBlocksOnOpen(cfd.getPrefetchBlocksOnOpen());
        }

        String storagePolicy = cfd.getStoragePolicy();
        boolean storagePolicyHasSet = false;
        if (StringUtils.isNotBlank(storagePolicy)) {
            cd.setValue(STORAGE_POLICY, storagePolicy);
            storagePolicyHasSet = true;
        }
        Map<String, String> configuration = cfd.getConfiguration();
        if (configuration != null && !configuration.isEmpty()) {
            for (String key : configuration.keySet()) {
                if (BLOCK_STORAGE_POLICY_KEY.equals(key)) {
                    String storagePolicyConfig = configuration.get(key);
                    if (StringUtils.isBlank(storagePolicyConfig)) {
                        continue;
                    }
                    if (!storagePolicyHasSet) {
                        cd.setValue(STORAGE_POLICY, storagePolicyConfig);
                        continue;
                    }
                    if (!storagePolicyConfig.equals(storagePolicy)) {
                        throw new IllegalArgumentException(
                                String.format("There are conflict storage policies %s and %s", storagePolicy, storagePolicyConfig));
                    }
                } else {
                    String defaultVal = cd.getConfigurationValue(key);
                    String newVal = configuration.get(key);
                    if (defaultVal == null) {
                        cd.setConfiguration(key, newVal);
                        continue;
                    }
                    if (compareNeedSet(defaultVal, newVal)) {
                        cd.setConfiguration(key, newVal);
                    }
                }
            }
        }

        Map<String, String> values = cfd.getValues();
        if (values != null && !values.isEmpty()) {
            for (String key : values.keySet()) {
                String defaultVal = BaseColumnFamilyDesc.DEFAULT_VALUES.get(key);
                String newVal = values.get(key);
                if (defaultVal == null) {
                    cd.setValue(key, newVal);
                    continue;
                }
                if (compareNeedSet(defaultVal, newVal)) {
                    cd.setValue(key, newVal);
                }
            }
        }

        return cd;
    }

    @Override
    protected ColumnFamilyDesc doBackward(HColumnDescriptor cd) {
        final BaseColumnFamilyDesc.Builder<ColumnFamilyDesc> builder =
                ColumnFamilyDesc.newBuilder(cd.getNameAsString())
                        .replicationScope(cd.getScope())
                        .maxVersions(cd.getMaxVersions())
                        .minVersions(cd.getMinVersions())
                        .compressionType(cd.getCompressionType().name())
                        .bloomFilterType(cd.getBloomFilterType().name())
                        .timeToLive(cd.getTimeToLive())
                        .blockSize(cd.getBlocksize())
                        .blockCacheEnabled(cd.isBlockCacheEnabled())
                        .inMemory(cd.isInMemory())
                        .keepDeletedCells(cd.getKeepDeletedCells().name())
                        .dataBlockEncoding(cd.getDataBlockEncoding().name())
                        .cacheDataOnWrite(cd.isCacheDataOnWrite())
                        .cacheDataInL1(cd.isCacheDataInL1())
                        .cacheIndexesOnWrite(cd.isCacheIndexesOnWrite())
                        .cacheBloomsOnWrite(cd.isCacheBloomsOnWrite())
                        .evictBlocksOnClose(cd.isEvictBlocksOnClose())
                        .prefetchBlocksOnOpen(cd.isPrefetchBlocksOnOpen());

        Map<String, String> configuration = cd.getConfiguration();
        if (!configuration.isEmpty()) {
            String storagePolicy = configuration.get(BLOCK_STORAGE_POLICY_KEY);
            if (StringUtils.isNotBlank(storagePolicy)) {
                builder.storagePolicy(storagePolicy);
            } else {
                configuration.forEach(builder::setConfiguration);
            }
        }

        Map<ImmutableBytesWritable, ImmutableBytesWritable> values = cd.getValues();
        if (!values.isEmpty()) {
            for (ImmutableBytesWritable keyBytes : values.keySet()) {
                String keyStr = Bytes.toString(keyBytes.get());
                String valueStr = Bytes.toString(values.get(keyBytes).get());
                if (STORAGE_POLICY.equals(keyStr) && StringUtils.isNotBlank(valueStr)) {
                    builder.storagePolicy(valueStr);
                    continue;
                }
                builder.setValue(keyStr, valueStr);
            }
        }

        return builder.build();
    }
}
