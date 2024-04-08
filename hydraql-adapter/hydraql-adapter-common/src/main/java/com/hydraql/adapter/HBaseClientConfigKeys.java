package com.hydraql.adapter;

/**
 * @author leojie 2024/4/6 22:17
 */
public interface HBaseClientConfigKeys {
    String PREFIX = "hbase.client.";

    String HEDGED_READ_CLUSTER_CONF_PREFIX = "hedged.read.";

    interface HedgedRead {
        String PREFIX = HBaseClientConfigKeys.PREFIX + "hedged.read.";

        String STRATEGY = PREFIX + "strategy";

        String STRATEGY_DEFAULT = "none";

        String WRITE_DISABLE = PREFIX + "write.disable";

        boolean WRITE_DISABLE_DEFAULT = true;

        String OVERALL_TIMEOUT_MILLIS = PREFIX + "overall.timeout.millis";

        long OVERALL_TIMEOUT_MILLIS_DEFAULT = 1000;

        String THRESHOLD_MILLIS_KEY = PREFIX + "threshold.millis";

        long THRESHOLD_MILLIS_DEFAULT = 500;

        String THREADPOOL_SIZE_KEY = PREFIX + "threadpool.size";

        int THREADPOOL_SIZE_DEFAULT = 0;
    }

    String HBASE_CLIENT_SCANNER_CACHING = PREFIX + "scanner.caching";
    int HBASE_CLIENT_SCANNER_CACHING_DEFAULT = 1000;
}
