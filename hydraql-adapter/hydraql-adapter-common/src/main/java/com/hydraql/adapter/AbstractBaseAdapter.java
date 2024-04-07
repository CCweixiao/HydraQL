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

import java.util.Map;

import static com.hydraql.common.constants.HBaseConfigKeys.HBASE_CLIENT_DEFAULT_SCANNER_CACHING;
import static com.hydraql.common.constants.HBaseConfigKeys.HBASE_CLIENT_SCANNER_CACHING;

/**
 * @author leojie 2020/11/13 11:52 下午
 */
@InterfaceAudience.Private
public abstract class AbstractBaseAdapter implements BaseAdapter {
    private final Configuration configuration;
    private final HBaseClientConf clientConf;
    private final Connection connection;
    private Configuration hedgedConfiguration;
    private Connection hedgedConnection;

    public AbstractBaseAdapter(Configuration configuration) {
        this.configuration = configuration;
        this.clientConf = new HBaseClientConf(configuration);
        this.connection = HBaseConnectionManager.getInstance().getConnection(this.configuration);
        if (this.clientConf.hedgedReadIsOpen()) {
            this.hedgedConfiguration = createHedgedReadClusterConf(configuration);
            this.hedgedConnection = HBaseConnectionManager.getInstance().getConnection(this.hedgedConfiguration);
        }
    }

    private Configuration createHedgedReadClusterConf(Configuration conf) {

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
