package com.hydraql.adapter;

import com.hydraql.common.exception.HBaseSdkTableIsExistsException;
import com.hydraql.common.exception.HBaseSdkTableIsNotDisabledException;
import com.hydraql.common.exception.HBaseSdkTableIsNotExistsException;
import com.hydraql.connection.HBaseConnectionManager;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.yetus.audience.InterfaceAudience;

import java.util.Iterator;
import java.util.Map;

import static com.hydraql.common.constants.HBaseConfigKeys.HBASE_CLIENT_DEFAULT_SCANNER_CACHING;
import static com.hydraql.common.constants.HBaseConfigKeys.HBASE_CLIENT_SCANNER_CACHING;

/**
 * @author leojie 2020/11/13 11:52 下午
 */
@InterfaceAudience.Private
public abstract class AbstractHBaseBaseAdapter implements IHBaseBaseAdapter {
    private final Configuration configuration;
    private final HBaseClientConf clientConf;
    private final Connection connection;
    private Configuration hedgedConfiguration;
    private Connection hedgedConnection;

    public AbstractHBaseBaseAdapter(Configuration configuration) {
        this.configuration = configuration;
        this.clientConf = new HBaseClientConf(configuration);
        this.connection = HBaseConnectionManager.getInstance().getConnection(this.configuration);
        if (this.clientConf.hedgedReadIsOpen()) {
            this.hedgedConfiguration = createHedgedReadClusterConf(configuration);
            this.hedgedConnection = HBaseConnectionManager.getInstance().getConnection(this.hedgedConfiguration);
        }
    }

    private Configuration createHedgedReadClusterConf(Configuration conf) {
        if (conf == null) {
            throw new NullPointerException("The source cluster configuration cannot be empty.");
        }
        Configuration hedgedReadConf = HBaseConfiguration.create();

        for (Map.Entry<String, String> entry : conf) {
            String hedgedReadKey = entry.getKey();
            if (hedgedReadKey.startsWith(HBaseClientConfigKeys.HedgedRead.PREFIX) ||
                    hedgedReadKey.startsWith(HBaseClientConfigKeys.HEDGED_READ_CLUSTER_CONF_PREFIX)) {
                continue;
            }
            hedgedReadConf.set(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, String> entry : conf) {
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

    @Override
    public HBaseClientConf getHBaseClientConf() {
        return this.clientConf;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public BufferedMutator getBufferedMutator(String tableName) {
        return HBaseConnectionManager.getInstance().getBufferedMutator(tableName,
                this.getConfiguration(), this.getConnection());
    }

    public Configuration getHedgedConfiguration() {
        return hedgedConfiguration;
    }

    @Override
    public Connection getHedgedReadConnection() {
        return hedgedConnection;
    }

    @Override
    public BufferedMutator getHedgedReadBufferedMutator(String tableName) {
        return HBaseConnectionManager.getInstance().getBufferedMutator(tableName, this.getHedgedConfiguration(),
                this.getHedgedReadConnection());
    }


    protected void tableIsNotExistsThrowError(boolean tableIsNotExists, String msg) {
        if (tableIsNotExists) {
            throw new HBaseSdkTableIsNotExistsException(msg);
        }
    }

    protected void tableIsExistsThrowError(boolean tableIsExists, String msg) {
        if (tableIsExists) {
            throw new HBaseSdkTableIsExistsException(msg);
        }
    }

    protected void tableIsNotDisableThrowError(boolean tableIsDisabled, String msg) {
        if (!tableIsDisabled) {
            throw new HBaseSdkTableIsNotDisabledException(msg);
        }
    }

    protected int getClientScannerCaching() {
        Configuration configuration = this.getConfiguration();
        if (configuration == null) {
            return HBASE_CLIENT_DEFAULT_SCANNER_CACHING;
        }
        return configuration.getInt(HBASE_CLIENT_SCANNER_CACHING, HBASE_CLIENT_DEFAULT_SCANNER_CACHING);
    }
}
