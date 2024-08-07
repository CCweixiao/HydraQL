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
import org.apache.hadoop.hbase.KeepDeletedCells;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
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

public class ColumnFamilyDescriptorConverter
    extends BaseColumnFamilyDescriptorConverter<ColumnFamilyDesc, ColumnFamilyDescriptor> {
  public ColumnFamilyDescriptorConverter(ColumnFamilyDesc columnFamilyDesc) {
    super(columnFamilyDesc);
  }

  @Override
  protected ColumnFamilyDescriptor doForward(ColumnFamilyDesc cfd) {
    ColumnFamilyDescriptorBuilder builder =
        ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cfd.getName()));

    if (cfd.getReplicationScope() != null) {
      builder.setScope(cfd.getReplicationScope());
    }

    if (cfd.getMaxVersions() != null) {
      builder.setMaxVersions(cfd.getMaxVersions());
    }

    if (cfd.getMinVersions() != null) {
      builder.setMinVersions(cfd.getMinVersions());
    }

    Compression.Algorithm compressionType =
        CompressionAlgorithmConverter.apply(cfd.getCompressionType());
    if (compressionType != Compression.Algorithm.NONE) {
      builder.setCompressionType(compressionType);
    }

    BloomType bloomFilterType = BloomTypeConverter.apply(cfd.getBloomFilterType());
    if (bloomFilterType != BloomType.ROW) {
      builder.setBloomFilterType(bloomFilterType);
    }

    if (cfd.getTimeToLive() != null) {
      builder.setTimeToLive(cfd.getTimeToLive());
    }

    if (cfd.getBlockSize() != null) {
      builder.setBlocksize(cfd.getBlockSize());
    }

    if (cfd.getBlockCacheEnabled() != null) {
      builder.setBlockCacheEnabled(cfd.getBlockCacheEnabled());
    }

    if (cfd.getInMemory() != null) {
      builder.setInMemory(cfd.getInMemory());
    }

    KeepDeletedCells keepDeletedCells = KeepDeletedCellsConverter.apply(cfd.getKeepDeletedCells());
    if (keepDeletedCells != KeepDeletedCells.FALSE) {
      builder.setKeepDeletedCells(keepDeletedCells);
    }

    if (cfd.getNewVersionBehavior() != null) {
      builder.setNewVersionBehavior(cfd.getNewVersionBehavior());
    }

    DataBlockEncoding dataBlockEncoding =
        DataBlockEncodingConverter.apply(cfd.getDataBlockEncoding());
    if (dataBlockEncoding != DataBlockEncoding.NONE) {
      builder.setDataBlockEncoding(dataBlockEncoding);
    }

    if (cfd.getCacheDataOnWrite() != null) {
      builder.setCacheDataOnWrite(cfd.getCacheDataOnWrite());
    }

    if (cfd.getCacheIndexesOnWrite() != null) {
      builder.setCacheIndexesOnWrite(cfd.getCacheIndexesOnWrite());
    }

    if (cfd.getCacheBloomsOnWrite() != null) {
      builder.setCacheBloomsOnWrite(cfd.getCacheBloomsOnWrite());
    }

    if (cfd.getEvictBlocksOnClose() != null) {
      builder.setEvictBlocksOnClose(cfd.getEvictBlocksOnClose());
    }

    if (cfd.getPrefetchBlocksOnOpen() != null) {
      builder.setPrefetchBlocksOnOpen(cfd.getPrefetchBlocksOnOpen());
    }

    if (cfd.getMobEnabled() != null) {
      builder.setMobEnabled(cfd.getMobEnabled());
    }

    if (cfd.getMobThreshold() != null) {
      builder.setMobThreshold(cfd.getMobThreshold());
    }

    String storagePolicy = cfd.getStoragePolicy();
    boolean storagePolicyHasSet = false;
    if (StringUtil.isNotBlank(storagePolicy)) {
      builder.setStoragePolicy(storagePolicy);
      storagePolicyHasSet = true;
    }

    Map<String, String> configuration = cfd.getConfiguration();
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
            throw new IllegalArgumentException(String.format(
              "There are conflict storage policies %s and %s", storagePolicy, storagePolicyConfig));
          }
        } else {
          builder.setConfiguration(key, configuration.get(key));
        }
      }
    }

    Map<String, String> values = cfd.getValues();
    if (values != null && !values.isEmpty()) {
      values.forEach(builder::setValue);
    }
    return builder.build();
  }

  @Override
  protected ColumnFamilyDesc doBackward(ColumnFamilyDescriptor cd) {
    final BaseColumnFamilyDesc.Builder<ColumnFamilyDesc> builder =
        ColumnFamilyDesc.newBuilder(cd.getNameAsString()).setReplicationScope(cd.getScope())
            .setMaxVersions(cd.getMaxVersions()).setMinVersions(cd.getMinVersions())
            .setCompressionType(cd.getCompressionType().name())
            .setBloomFilterType(cd.getBloomFilterType().name()).setTimeToLive(cd.getTimeToLive())
            .setBlockSize(cd.getBlocksize()).setBlockCacheEnabled(cd.isBlockCacheEnabled())
            .setInMemory(cd.isInMemory()).setKeepDeletedCells(cd.getKeepDeletedCells().name())
            .setNewVersionBehavior(cd.isNewVersionBehavior())
            .setDataBlockEncoding(cd.getDataBlockEncoding().name())
            .setCacheDataOnWrite(cd.isCacheDataOnWrite())
            .setCacheIndexesOnWrite(cd.isCacheIndexesOnWrite())
            .setCacheBloomsOnWrite(cd.isCacheBloomsOnWrite())
            .setEvictBlocksOnClose(cd.isEvictBlocksOnClose())
            .setPrefetchBlocksOnOpen(cd.isPrefetchBlocksOnOpen()).setMobEnabled(cd.isMobEnabled())
            .setMobThreshold(cd.getMobThreshold());

    Map<String, String> configuration = cd.getConfiguration();
    if (!configuration.isEmpty()) {
      String storagePolicy = configuration.get(BLOCK_STORAGE_POLICY_KEY);
      if (StringUtil.isNotBlank(storagePolicy)) {
        builder.setStoragePolicy(storagePolicy);
      } else {
        configuration.forEach(builder::setConfiguration);
      }
    }

    Map<Bytes, Bytes> values = cd.getValues();
    if (!values.isEmpty()) {
      for (Bytes keyBytes : values.keySet()) {
        String keyStr = Bytes.toString(keyBytes.get());
        String valueStr = Bytes.toString(values.get(keyBytes).get());
        if (STORAGE_POLICY.equals(keyStr) && StringUtil.isNotBlank(valueStr)) {
          builder.setStoragePolicy(valueStr);
          continue;
        }
        builder.setValue(keyStr, valueStr);
      }
    }

    return builder.build();
  }
}
