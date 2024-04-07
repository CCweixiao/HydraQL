package com.hydraql.adapter.context;

import org.apache.hadoop.hbase.TableName;

/**
 * @author leojie 2024/4/7 15:22
 */
public class HTableContext {
    private final TableName tableName;
    private final BatchSaveOptions batchSaveOptions;

    private HTableContext(Builder builder) {
        this.tableName = TableName.valueOf(builder.tableName);
        this.batchSaveOptions = builder.batchSaveOptions;
    }

    public static class Builder {
        private final String tableName;
        private BatchSaveOptions batchSaveOptions;

        public Builder(String tableName) {
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

    public TableName getTableName() {
        return tableName;
    }

    public BatchSaveOptions getBatchSaveOptions() {
        return batchSaveOptions;
    }
}
