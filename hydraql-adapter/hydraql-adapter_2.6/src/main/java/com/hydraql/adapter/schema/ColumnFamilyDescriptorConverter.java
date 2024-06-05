package com.hydraql.adapter.schema;

import com.hydraql.common.exception.HBaseFamilyNotEmptyException;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;


import java.util.Map;

import static com.hydraql.adapter.schema.BaseColumnFamilyDesc.BLOCK_STORAGE_POLICY_KEY;
import static com.hydraql.adapter.schema.BaseColumnFamilyDesc.STORAGE_POLICY;

/**
 * @author leojie 2023/5/17 22:43
 */

public class ColumnFamilyDescriptorConverter extends BaseColumnFamilyDescriptorConverter<ColumnFamilyDesc, ColumnFamilyDescriptor> {
    public ColumnFamilyDescriptorConverter(ColumnFamilyDesc columnFamilyDesc) {
        super(columnFamilyDesc);
    }

    @Override
    protected ColumnFamilyDescriptor doForward(ColumnFamilyDesc columnFamilyDesc) {
        if (StringUtil.isBlank(columnFamilyDesc.getNameAsString())) {
            throw new HBaseFamilyNotEmptyException("The family name is not empty.");
        }
        ColumnFamilyDescriptorBuilder builder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(columnFamilyDesc.getNameAsString()));
        builder.setScope(columnFamilyDesc.getReplicationScope());
        builder.setMaxVersions(columnFamilyDesc.getMaxVersions());
        builder.setMinVersions(columnFamilyDesc.getMinVersions());
        builder.setCompressionType(getCompression(columnFamilyDesc.getCompressionType()));
        builder.setBloomFilterType(getBloomType(columnFamilyDesc.getBloomFilterType()));
        builder.setTimeToLive(columnFamilyDesc.getTimeToLive());
        builder.setBlocksize(columnFamilyDesc.getBlockSize());
        builder.setBlockCacheEnabled(columnFamilyDesc.isBlockCacheEnabled());
        builder.setInMemory(columnFamilyDesc.isInMemory());
        builder.setKeepDeletedCells(getKeepDeletedCells(columnFamilyDesc.getKeepDeletedCells()));
        builder.setDataBlockEncoding(getDataBlockEncoding(columnFamilyDesc.getDataBlockEncoding()));
        builder.setCacheDataOnWrite(columnFamilyDesc.isCacheDataOnWrite());
        builder.setCacheIndexesOnWrite(columnFamilyDesc.isCacheIndexesOnWrite());
        builder.setCacheBloomsOnWrite(columnFamilyDesc.isCacheBloomsOnWrite());
        builder.setEvictBlocksOnClose(columnFamilyDesc.isEvictBlocksOnClose());
        builder.setPrefetchBlocksOnOpen(columnFamilyDesc.isPrefetchBlocksOnOpen());

        String storagePolicy = columnFamilyDesc.getStoragePolicy();
        boolean storagePolicyHasSet = false;
        if (StringUtil.isNotBlank(storagePolicy)) {
            builder.setStoragePolicy(storagePolicy);
            storagePolicyHasSet = true;
        }

        Map<String, String> configuration = columnFamilyDesc.getConfiguration();
        if (configuration != null && !configuration.isEmpty()) {
            for (String key : configuration.keySet()) {
                if (BLOCK_STORAGE_POLICY_KEY.equals(key)) {
                    String storagePolicyConfig = configuration.get(key);
                    if (StringUtil.isBlank(storagePolicyConfig)) {
                        continue;
                    }
                    if (!storagePolicyHasSet) {
                        builder.setStoragePolicy(storagePolicyConfig);
                        continue;
                    }
                    if (!storagePolicyConfig.equals(storagePolicy)) {
                        throw new IllegalArgumentException(
                                String.format("There are conflicting storage policies %s and %s", storagePolicy, storagePolicyConfig));
                    }
                } else {
                    builder.setConfiguration(key, configuration.get(key));
                }
            }
        }

        builder.setMobEnabled(columnFamilyDesc.isMobEnabled());
        builder.setMobThreshold(columnFamilyDesc.getMobThreshold());

        Map<String, String> values = columnFamilyDesc.getValues();
        if (values != null && !values.isEmpty()) {
            values.forEach(builder::setValue);
        }
        return builder.build();
    }

    @Override
    protected ColumnFamilyDesc doBackward(ColumnFamilyDescriptor columnDescriptor) {
        final BaseColumnFamilyDesc.Builder<ColumnFamilyDesc> builder =
                ColumnFamilyDesc.newBuilder(columnDescriptor.getNameAsString())
                        .replicationScope(columnDescriptor.getScope())
                        .maxVersions(columnDescriptor.getMaxVersions())
                        .minVersions(columnDescriptor.getMinVersions())
                        .compressionType(columnDescriptor.getCompressionType().getName())
                        .bloomFilterType(columnDescriptor.getBloomFilterType().name())
                        .timeToLive(columnDescriptor.getTimeToLive())
                        .blockSize(columnDescriptor.getBlocksize())
                        .blockCacheEnabled(columnDescriptor.isBlockCacheEnabled())
                        .inMemory(columnDescriptor.isInMemory())
                        .keepDeletedCells(columnDescriptor.getKeepDeletedCells().name())
                        .dataBlockEncoding(columnDescriptor.getDataBlockEncoding().name())
                        .cacheDataOnWrite(columnDescriptor.isCacheDataOnWrite())
                        .cacheIndexesOnWrite(columnDescriptor.isCacheIndexesOnWrite())
                        .cacheBloomsOnWrite(columnDescriptor.isCacheBloomsOnWrite())
                        .evictBlocksOnClose(columnDescriptor.isEvictBlocksOnClose())
                        .prefetchBlocksOnOpen(columnDescriptor.isPrefetchBlocksOnOpen())
                        .mobEnabled(columnDescriptor.isMobEnabled())
                        .mobThreshold(columnDescriptor.getMobThreshold());

        Map<String, String> configuration = columnDescriptor.getConfiguration();
        if (configuration != null && !configuration.isEmpty()) {
            String storagePolicy = configuration.getOrDefault(BLOCK_STORAGE_POLICY_KEY, "");
            if (StringUtil.isNotBlank(storagePolicy)) {
                builder.storagePolicy(storagePolicy);
            }
            configuration.forEach(builder::setConfiguration);
        }

        Map<Bytes, Bytes> values = columnDescriptor.getValues();
        if (values != null && !values.isEmpty()) {
            values.forEach((key, value) -> {
                String keyStr = Bytes.toString(key.get());
                String valueStr = Bytes.toString(value.get());
                if (STORAGE_POLICY.equals(keyStr) && StringUtil.isNotBlank(valueStr)) {
                    builder.storagePolicy(valueStr);
                }
                builder.setValue(keyStr, valueStr);
            });
        }

        return builder.build();
    }
}
