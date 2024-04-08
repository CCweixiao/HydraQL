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
