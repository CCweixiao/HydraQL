package com.hydraql.adapter.schema;

import com.hydraql.common.exception.HBaseFamilyNotEmptyException;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.yetus.audience.InterfaceAudience;

import java.util.Map;

import static com.hydraql.adapter.schema.BaseColumnFamilyDesc.BLOCK_STORAGE_POLICY_KEY;
import static com.hydraql.adapter.schema.BaseColumnFamilyDesc.STORAGE_POLICY;

/**
 * @author leojie 2023/5/17 22:43
 */
@InterfaceAudience.Private
public class ColumnFamilyDescriptorConverter extends BaseColumnFamilyDescriptorConverter<ColumnFamilyDesc, HColumnDescriptor> {
    public ColumnFamilyDescriptorConverter(ColumnFamilyDesc columnFamilyDesc) {
        super(columnFamilyDesc);
    }

    @Override
    protected HColumnDescriptor doForward(ColumnFamilyDesc columnFamilyDesc) {
        if (StringUtil.isBlank(columnFamilyDesc.getNameAsString())) {
            throw new HBaseFamilyNotEmptyException("The family name is not empty.");
        }
        HColumnDescriptor columnDescriptor = new HColumnDescriptor(columnFamilyDesc.getNameAsString());
        columnDescriptor.setScope(columnFamilyDesc.getReplicationScope());
        columnDescriptor.setMaxVersions(columnFamilyDesc.getMaxVersions());
        columnDescriptor.setMinVersions(columnFamilyDesc.getMinVersions());
        columnDescriptor.setCompressionType(getCompression(columnFamilyDesc.getCompressionType()));
        columnDescriptor.setBloomFilterType(getBloomType(columnFamilyDesc.getBloomFilterType()));
        columnDescriptor.setTimeToLive(columnFamilyDesc.getTimeToLive());
        columnDescriptor.setBlocksize(columnFamilyDesc.getBlockSize());
        columnDescriptor.setBlockCacheEnabled(columnFamilyDesc.isBlockCacheEnabled());
        columnDescriptor.setInMemory(columnFamilyDesc.isInMemory());
        columnDescriptor.setKeepDeletedCells(getKeepDeletedCells(columnFamilyDesc.getKeepDeletedCells()));
        columnDescriptor.setDataBlockEncoding(getDataBlockEncoding(columnFamilyDesc.getDataBlockEncoding()));
        columnDescriptor.setCacheDataOnWrite(columnFamilyDesc.isCacheDataOnWrite());
        columnDescriptor.setCacheDataInL1(columnFamilyDesc.isCacheDataInL1());
        columnDescriptor.setCacheIndexesOnWrite(columnFamilyDesc.isCacheIndexesOnWrite());
        columnDescriptor.setCacheBloomsOnWrite(columnFamilyDesc.isCacheBloomsOnWrite());
        columnDescriptor.setEvictBlocksOnClose(columnFamilyDesc.isEvictBlocksOnClose());
        columnDescriptor.setPrefetchBlocksOnOpen(columnFamilyDesc.isPrefetchBlocksOnOpen());

        String storagePolicy = columnFamilyDesc.getStoragePolicy();
        boolean storagePolicyHasSet = false;
        if (StringUtil.isNotBlank(storagePolicy)) {
            columnDescriptor.setValue(STORAGE_POLICY, storagePolicy);
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
                        columnDescriptor.setValue(STORAGE_POLICY, storagePolicyConfig);
                        continue;
                    }
                    if (!storagePolicyConfig.equals(storagePolicy)) {
                        throw new IllegalArgumentException(
                                String.format("There are conflicting storage policies %s and %s", storagePolicy, storagePolicyConfig));
                    }
                } else {
                    columnDescriptor.setConfiguration(key, configuration.get(key));
                }
            }
        }

        Map<String, String> values = columnFamilyDesc.getValues();
        if (values != null && !values.isEmpty()) {
            values.forEach(columnDescriptor::setValue);
        }
        return columnDescriptor;
    }

    @Override
    protected ColumnFamilyDesc doBackward(HColumnDescriptor columnDescriptor) {
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
                        .cacheDataInL1(columnDescriptor.isCacheDataInL1())
                        .cacheIndexesOnWrite(columnDescriptor.isCacheIndexesOnWrite())
                        .cacheBloomsOnWrite(columnDescriptor.isCacheBloomsOnWrite())
                        .evictBlocksOnClose(columnDescriptor.isEvictBlocksOnClose())
                        .prefetchBlocksOnOpen(columnDescriptor.isPrefetchBlocksOnOpen());

        Map<String, String> configuration = columnDescriptor.getConfiguration();
        if (configuration != null && !configuration.isEmpty()) {
            String storagePolicy = configuration.getOrDefault(BLOCK_STORAGE_POLICY_KEY, "");
            if (StringUtil.isNotBlank(storagePolicy)) {
                builder.storagePolicy(storagePolicy);
            }
            configuration.forEach(builder::setConfiguration);
        }

        Map<ImmutableBytesWritable, ImmutableBytesWritable> values = columnDescriptor.getValues();
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
