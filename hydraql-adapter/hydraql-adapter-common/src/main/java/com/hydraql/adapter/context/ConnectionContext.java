package com.hydraql.adapter.context;

import com.hydraql.adapter.HBaseClientConf;
import com.hydraql.adapter.HBaseClientConfigKeys;
import com.hydraql.adapter.connection.HBaseConnectionManager;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Connection;

import java.util.Map;

/**
 * @author leojie 2024/4/7 19:20
 */
public interface ConnectionContext {
    Configuration getConfiguration();

    default HBaseClientConf getHBaseClientConf() {
        return new HBaseClientConf(this.getConfiguration());
    }

    default Connection getConnection() {
        return HBaseConnectionManager.create().getConnection(this.getConfiguration());
    }

    default void warmUpConnection() {
        Connection conn = this.getConnection();
        if (conn == null || conn.isClosed()) {
            throw new IllegalArgumentException("Connection is null or closed.");
        }
    }

    default BufferedMutator getBufferedMutator(HTableContext tableContext) {
        return HBaseConnectionManager.create().getBufferedMutator(tableContext,
                this.getConfiguration(), this.getConnection());
    }

    default Configuration getHedgedReadConfiguration() {
        if (this.getConfiguration() == null) {
            throw new NullPointerException("The source cluster configuration cannot be empty.");
        }
        Configuration hedgedReadConf = HBaseConfiguration.create();

        for (Map.Entry<String, String> entry : this.getConfiguration()) {
            String hedgedReadKey = entry.getKey();
            if (hedgedReadKey.startsWith(HBaseClientConfigKeys.HedgedRead.PREFIX) ||
                    hedgedReadKey.startsWith(HBaseClientConfigKeys.HEDGED_READ_CLUSTER_CONF_PREFIX)) {
                continue;
            }
            hedgedReadConf.set(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, String> entry : this.getConfiguration()) {
            String hedgedReadKey = entry.getKey();
            if (hedgedReadKey.startsWith(HBaseClientConfigKeys.HEDGED_READ_CLUSTER_CONF_PREFIX)) {
                String clientKey = hedgedReadKey
                        .substring(hedgedReadKey.indexOf(HBaseClientConfigKeys.HEDGED_READ_CLUSTER_CONF_PREFIX)
                                + HBaseClientConfigKeys.HEDGED_READ_CLUSTER_CONF_PREFIX.length());
                if (HBaseClientConfigKeys.HedgedRead.THREADPOOL_SIZE_KEY.equals(clientKey)) {
                    throw new IllegalStateException("The hedged read cluster can no longer support the hedged read function.");
                }
                hedgedReadConf.set(clientKey, entry.getValue());
            }
        }
        return hedgedReadConf;
    }

    default Connection getHedgedReadConnection() {
        return HBaseConnectionManager.create().getConnection(this.getHedgedReadConfiguration());
    }

    default void warmUpHedgedReadConnection() {
        Connection conn = this.getHedgedReadConnection();
        if (conn == null || conn.isClosed()) {
            throw new IllegalArgumentException("Hedged read connection is null or closed.");
        }
    }

    default BufferedMutator getHedgedReadBufferedMutator(HTableContext tableContext) {
        return HBaseConnectionManager.create().getBufferedMutator(tableContext,
                this.getHedgedReadConfiguration(), this.getHedgedReadConnection());
    }
}
