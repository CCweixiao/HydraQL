package com.hydraql.adapter.context;

import com.hydraql.common.util.StringUtil;
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
        if (StringUtil.isBlank(tableName)) {
            throw new IllegalStateException("The table name cannot be empty.");
        }
        return new Builder(tableName);
    }

    public static HTableContext createDefault(String tableName) {
        return builder(tableName).batchSaveOptions(BatchSaveOptions.builder().build()).build();
    }

    public TableName getTableName() {
        return tableName;
    }

    public BatchSaveOptions getBatchSaveOptions() {
        return batchSaveOptions;
    }
}
