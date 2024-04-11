package com.hydraql.adapter.context;

import com.hydraql.adapter.HBaseClientConf;
import com.hydraql.adapter.HBaseClientConfigKeys;
import com.hydraql.adapter.connection.HBaseConnectionManager;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;
import java.util.Map;

/**
 * @author leojie 2024/4/7 19:20
 */
public interface ConnectionContext {
    Configuration getConfiguration();

    default Connection getConnection() {
        return HBaseConnectionManager.create().getConnection(this.getConfiguration());
    }

    default void warmUpConnection() {
        try(Admin admin = this.getConnection().getAdmin()) {
            admin.listNamespaceDescriptors();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default BufferedMutator getBufferedMutator(HTableContext tableContext) {
        return HBaseConnectionManager.create().getBufferedMutator(tableContext,
                this.getConfiguration(), this.getConnection());
    }

    default boolean hedgedReadIsOpen() {
        return getHBaseClientConf().hedgedReadIsOpen();
    }

    default boolean hedgedReadWriteDisable() {
        return getHBaseClientConf().isHedgedReadWriteDisable();
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

    default BufferedMutator getHedgedReadBufferedMutator(HTableContext tableContext) {
        return HBaseConnectionManager.create().getBufferedMutator(tableContext,
                this.getHedgedReadConfiguration(), this.getHedgedReadConnection());
    }

    default HBaseClientConf getHBaseClientConf() {
        return new HBaseClientConf(this.getConfiguration());
    }
}
