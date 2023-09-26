package com.hydraql.dsl.model;

import com.hydraql.common.constants.HBaseConfigKeys;

/**
 * @author leojie 2022/12/3 11:13
 */
public class TableQueryProperties {
    private int scanCaching = HBaseConfigKeys.HBASE_CLIENT_DEFAULT_SCANNER_CACHING;
    private int scanBatch = HBaseConfigKeys.HBASE_CLIENT_DEFAULT_SCANNER_BATCH;
    private boolean scanCacheBlocks = HBaseConfigKeys.HBASE_CLIENT_DEFAULT_SCANNER_CACHE;
    private int deleteBatch = HBaseConfigKeys.HBASE_CLIENT_DEFAULT_DELETE_BATCH;

    public int getScanCaching() {
        return scanCaching;
    }

    public void setScanCaching(int scanCaching) {
        this.scanCaching = scanCaching;
    }

    public int getScanBatch() {
        return scanBatch;
    }

    public void setScanBatch(int scanBatch) {
        this.scanBatch = scanBatch;
    }

    public boolean isScanCacheBlocks() {
        return scanCacheBlocks;
    }

    public void setScanCacheBlocks(boolean scanCacheBlocks) {
        this.scanCacheBlocks = scanCacheBlocks;
    }

    public int getDeleteBatch() {
        return deleteBatch;
    }

    public void setDeleteBatch(int deleteBatch) {
        this.deleteBatch = deleteBatch;
    }
}
