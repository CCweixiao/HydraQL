package com.hydraql.adapter;

import org.apache.hadoop.conf.Configuration;

/**
 * @author leojie@apache.org 2024/4/6 22:26
 */
public class HBaseClientConf {
    private final long hedgedReadThresholdMillis;
    private final int hedgedReadThreadpoolSize;
    private final boolean hedgedReadWriteDisable;

    public HBaseClientConf(Configuration conf) {
        hedgedReadThresholdMillis = conf.getLong(
                HBaseClientConfigKeys.HedgedRead.THRESHOLD_MILLIS_KEY,
                HBaseClientConfigKeys.HedgedRead.THRESHOLD_MILLIS_DEFAULT);
        hedgedReadThreadpoolSize = conf.getInt(
                HBaseClientConfigKeys.HedgedRead.THREADPOOL_SIZE_KEY,
                HBaseClientConfigKeys.HedgedRead.THREADPOOL_SIZE_DEFAULT);
        hedgedReadWriteDisable = conf.getBoolean(
                HBaseClientConfigKeys.HedgedRead.WRITE_DISABLE,
                HBaseClientConfigKeys.HedgedRead.WRITE_DISABLE_DEFAULT);
    }

    public long getHedgedReadThresholdMillis() {
        return hedgedReadThresholdMillis;
    }

    public int getHedgedReadThreadpoolSize() {
        return hedgedReadThreadpoolSize;
    }

    public boolean isHedgedReadWriteDisable() {
        return hedgedReadWriteDisable;
    }

    public boolean hedgedReadIsOpen() {
        return this.getHedgedReadThreadpoolSize() > 0;
    }
}
