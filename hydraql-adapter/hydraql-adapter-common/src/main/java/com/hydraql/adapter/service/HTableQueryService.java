package com.hydraql.adapter.service;

import com.hydraql.adapter.HBaseClientConf;
import com.hydraql.adapter.context.ConnectionContext;
import com.hydraql.adapter.hedgedread.HedgedReadConsistencyStrategy;
import com.hydraql.adapter.hedgedread.HedgedReadFirstOneStrategy;
import com.hydraql.adapter.hedgedread.HedgedReadHashStrategy;
import com.hydraql.adapter.hedgedread.HedgedReadStrategy;
import com.hydraql.adapter.hedgedread.HedgedReadThresholdStrategy;
import com.hydraql.common.callback.TableCallback;
import com.hydraql.common.exception.HydraQLTableOpException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author leojie@apache.org 2024/4/12 19:45
 */
public abstract class HTableQueryService implements ConnectionContext {
    private static final Logger LOG = LoggerFactory.getLogger(HTableQueryService.class);

    private final Configuration configuration;

    protected HTableQueryService(Configuration configuration) {
        this.configuration = configuration;
        boolean connectionIsWarmUp = this.warmUpConnection();
        LOG.info("The new connection is warmed up successfully? {}", connectionIsWarmUp);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    public <T> T executeGetOrScan(String tableName, TableCallback<T, Table> action) {
        return this.execute(tableName, action);
    }

    protected <T> T execute(String tableName, TableCallback<T, Table> action) {
        HedgedReadStrategy hedgedReadStrategy = createHedgedReadStrategy();
        try {
            if (hedgedReadStrategy != null) {
                Callable<T> preferCall = () -> executeOnPrefer(tableName, action);
                Callable<T> spareCall = () -> executeOnSpare(tableName, action);
                return hedgedReadStrategy.execute(preferCall, spareCall);
            } else {
                return executeOnPrefer(tableName, action);
            }
        } catch (RejectedExecutionException e) {
            try {
                return executeOnPrefer(tableName, action);
            } catch (IOException ex) {
                throw new HydraQLTableOpException(ex);
            }
        } catch (IOException e) {
            throw new HydraQLTableOpException(e);
        }
    }

    protected <T> T executeOnPrefer(String tableName, TableCallback<T, Table> action) throws IOException {
        try (Table table = this.getConnection().getTable(TableName.valueOf(tableName))) {
            return action.execute(table);
        } catch (Throwable throwable) {
            throw new IOException(throwable);
        }
    }

    protected <T> T executeOnSpare(String tableName, TableCallback<T, Table> action) throws IOException {
        try (Table table = this.getHedgedReadConnection().getTable(TableName.valueOf(tableName))) {
            return action.execute(table);
        } catch (Throwable throwable) {
            throw new IOException(throwable);
        }
    }

    protected HedgedReadStrategy createHedgedReadStrategy() {
        HedgedReadStrategy strategy = null;
        if (hedgedReadIsOpen()) {
            HedgedReadStrategy.Level level = getHBaseClientConf().getHedgedReadStrategy();
            switch (level) {
                case THRESHOLD:
                    strategy = new HedgedReadThresholdStrategy(getHBaseClientConf().getHedgedReadThresholdMillis(),
                            getHBaseClientConf().getHedgedReadThreadpoolSize());
                    break;
                case FIRST_ONE:
                    strategy = new HedgedReadFirstOneStrategy(getHBaseClientConf().getHedgedReadThreadpoolSize());
                    break;
                case HASH:
                    strategy = new HedgedReadHashStrategy(getHBaseClientConf().getHedgedReadThreadpoolSize());
                    break;
                case CONSISTENCY:
                    strategy = new HedgedReadConsistencyStrategy(getHBaseClientConf().getHedgedReadThreadpoolSize(),
                            getHBaseClientConf().getHedgedReadOverallTimeoutMillis());
                    break;
                default:
                    break;
            }
        }
        return strategy;
    }

    protected boolean hedgedReadIsOpen() {
        return getHBaseClientConf().hedgedReadIsOpen();
    }

    protected boolean hedgedReadWriteDisable() {
        return getHBaseClientConf().isHedgedReadWriteDisable();
    }

    private HBaseClientConf getHBaseClientConf() {
        return new HBaseClientConf(this.getConfiguration());
    }
}
