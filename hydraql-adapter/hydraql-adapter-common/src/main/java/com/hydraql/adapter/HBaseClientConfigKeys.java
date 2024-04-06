package com.hydraql.adapter;

/**
 * @author leojie 2024/4/6 22:17
 */
public interface HBaseClientConfigKeys {
    String PREFIX = "hbase.client.";

    String HEDGED_READ_CLUSTER_CONF_PREFIX = "hedged.read.";

    interface HedgedRead {
        String PREFIX = HBaseClientConfigKeys.PREFIX + "hedged.read.";

        String WRITE_DISABLE = PREFIX + "write.disable";

        boolean WRITE_DISABLE_DEFAULT = true;

        String THRESHOLD_MILLIS_KEY = PREFIX + "threshold.millis";

        long THRESHOLD_MILLIS_DEFAULT = 500;

        String THREADPOOL_SIZE_KEY = PREFIX + "threadpool.size";

        int THREADPOOL_SIZE_DEFAULT = 0;
    }
}
