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

package com.hydraql.adapter.context;

import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.BufferedMutator;

/**
 * @author leojie 2024/4/7 15:22
 */
public class HTableContext {
  private final TableName tableName;
  private BatchSaveOptions batchSaveOptions;

  private HTableContext(Builder builder) {
    this.tableName = TableName.valueOf(builder.tableName);
    this.batchSaveOptions = builder.batchSaveOptions;
  }

  public static class Builder {
    private final String tableName;
    private BatchSaveOptions batchSaveOptions;

    public Builder(String tableName) {
      if (StringUtil.isBlank(tableName)) {
        throw new IllegalStateException("The table name cannot be empty.");
      }
      this.tableName = tableName;
    }

    public Builder batchSaveOptions(BatchSaveOptions batchSaveOptions) {
      this.batchSaveOptions = batchSaveOptions;
      return this;
    }

    public HTableContext build() {
      return new HTableContext(this);
    }
  }

  public static HTableContext.Builder builder(String tableName) {
    return new Builder(tableName);
  }

  public static HTableContext createDefault(String tableName) {
    return builder(tableName).batchSaveOptions(BatchSaveOptions.builder().build()).build();
  }

  public TableName getTableName() {
    return tableName;
  }

  private BatchSaveOptions getBatchSaveOptions() {
    if (batchSaveOptions == null) {
      batchSaveOptions = BatchSaveOptions.builder().build();
    }
    return batchSaveOptions;
  }

  public int getMaxKeyValueSize() {
    return getBatchSaveOptions().getMaxKeyValueSize();
  }

  public long getWriteBufferSize() {
    return getBatchSaveOptions().getWriteBufferSize();
  }

  public long getWriteBufferPeriodicFlushTimerTickMs() {
    return getBatchSaveOptions().getWriteBufferPeriodicFlushTimerTickMs();
  }

  public long getWriteBufferPeriodicFlushTimeoutMs() {
    return getBatchSaveOptions().getWriteBufferPeriodicFlushTimeoutMs();
  }

  public boolean isAutoFlush() {
    return getBatchSaveOptions().isAutoFlush();
  }

  public BufferedMutator.ExceptionListener getExceptionListener() {
    return getBatchSaveOptions().getExceptionListener();
  }
}
