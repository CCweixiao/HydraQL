package com.hydraql.adapter.executor;

import com.hydraql.adapter.context.ConnectionContext;
import com.hydraql.common.callback.TableCallback;
import com.hydraql.common.exception.HydraQLTableOpException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * @author leojie 2024/4/7 19:45
 */
public interface HTableSingleExecutor extends ConnectionContext {
    default HedgedReadStrategy createHedgedReadStrategy() {
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

    default <T> T execute(String tableName, TableCallback<T, Table> action) {
        HedgedReadStrategy hedgedReadStrategy = createHedgedReadStrategy();
        try {
            if (hedgedReadStrategy != null) {
                Callable<T> preferCall = () -> executeOnPrefer(tableName, action);
                Callable<T> spareCall = () -> executeOnSpare(tableName, action);
                return hedgedReadStrategy.execute(preferCall, spareCall);
            } else {
                return executeOnPrefer(tableName, action);
            }
        } catch (IOException e) {
            throw new HydraQLTableOpException(e);
        }
    }

    default <T> T executeOnPrefer(String tableName, TableCallback<T, Table> action) throws IOException {
        try (Table table = this.getConnection().getTable(TableName.valueOf(tableName))) {
            return action.execute(table);
        } catch (Throwable throwable) {
            throw new IOException(throwable);
        }
    }

    default <T> T executeOnSpare(String tableName, TableCallback<T, Table> action) throws IOException {
        try (Table table = this.getHedgedReadConnection().getTable(TableName.valueOf(tableName))) {
            return action.execute(table);
        } catch (Throwable throwable) {
            throw new IOException(throwable);
        }
    }

    default void execSinglePut(String tableName, Put put) {
        if (this.hedgedReadWriteDisable()) {
            try {
                this.executeOnPrefer(tableName, table -> {
                    table.put(put);
                    return true;
                });
            } catch (IOException e) {
                throw new HydraQLTableOpException(e);
            }
        } else {
            this.execute(tableName, table -> {
                table.put(put);
                return true;
            });
        }
    }

    default void execSingleDelete(String tableName, Delete delete) {
        if (this.hedgedReadWriteDisable()) {
            try {
                this.executeOnPrefer(tableName, table -> {
                    table.delete(delete);
                    return true;
                });
            } catch (IOException e) {
                throw new HydraQLTableOpException(e);
            }
        } else {
            this.execute(tableName, table -> {
                table.delete(delete);
                return true;
            });
        }
    }

}
