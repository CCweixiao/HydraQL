package com.hydraql.adapter.executor;

import com.hydraql.adapter.context.ConnectionContext;
import com.hydraql.common.callback.TableCallback;
import com.hydraql.common.exception.HydraQLTableOpException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
                    strategy = new ThresholdHedgedReadStrategy(getHBaseClientConf().getHedgedReadThresholdMillis(),
                            getHBaseClientConf().getHedgedReadThreadpoolSize());
                    break;
                case FIRST_ONE:
                    strategy = new FirstOneHedgedReadStrategy(getHBaseClientConf().getHedgedReadThreadpoolSize());
                    break;
                case HASH:
                    strategy = new HashHedgedReadStrategy(getHBaseClientConf().getHedgedReadThreadpoolSize());
                    break;
                case CONSISTENCY:
                    strategy = new ConsistencyHedgedReadStrategy(getHBaseClientConf().getHedgedReadThreadpoolSize());
                    break;
                default:
                    break;
            }
        }
        return strategy;
    }

    default <T> T execute(String tableName, TableCallback<T, Table> action) throws IOException {
        HedgedReadStrategy hedgedReadStrategy = createHedgedReadStrategy();
        if (hedgedReadStrategy != null) {
            Callable<T> preferCall = () -> {
                executeOnPrefer(tableName, action);
                return null;
            };
            Callable<T> spareCall = () -> {
                executeOnSpare(tableName, action);
                return null;
            };
            return hedgedReadStrategy.execute(preferCall, spareCall);
        } else {
            return executeOnPrefer(tableName, action);
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

    default void executeSave(String tableName, Put put) {
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

    default void executeDelete(String tableName, Delete delete) {
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

//    default boolean hedgedReadWriteDisable() {
//        return this.getHBaseClientConf().isHedgedReadWriteDisable();
//    }
//
//    default int getHedgedReadThreadpoolSize() {
//        return this.getHBaseClientConf().getHedgedReadThreadpoolSize();
//    }
//
//    default long getHedgedReadThresholdMillis() {
//        return this.getHBaseClientConf().getHedgedReadThresholdMillis();
//    }
}
