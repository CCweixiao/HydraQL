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
public interface HTableOpExecutor extends ConnectionContext {
    default <T> T execute(String tableName, TableCallback<T, Table> action) {
        if (this.hedgedReadIsOpen()) {
            ArrayList<Future<T>> futures = new ArrayList<>();
            CompletionService<T> hedgedService =
                    new ExecutorCompletionService<>(HBaseHedgedReadExecutor.create()
                            .getExecutor(this.getHedgedReadThreadpoolSize()));
            // There is no read request already executing
            Callable<T> executeInSource = () -> executeOnSource(tableName, action);
            Future<T> firstRequest = hedgedService.submit(executeInSource);
            futures.add(firstRequest);
            Future<T> future = null;
            try {
                long thresholdMillis = this.getHedgedReadThresholdMillis();
                future = hedgedService.poll(thresholdMillis, TimeUnit.MICROSECONDS);
                long start = System.currentTimeMillis();
                while (future == null && (System.currentTimeMillis() - start) < thresholdMillis) {
                    future = hedgedService.poll(thresholdMillis, TimeUnit.MICROSECONDS);
                }
                if (future != null) {
                    return future.get();
                }
            } catch (ExecutionException e) {
                futures.remove(future);
            } catch (InterruptedException e) {
                throw new HydraQLTableOpException("Interrupted while waiting for reading task", e);
            }

            Callable<T> executeInTarget = () -> executeOnTarget(tableName, action);
            Future<T> oneMoreRequest = hedgedService.submit(executeInTarget);
            futures.add(oneMoreRequest);
            try {
                T result = getFirstToComplete(hedgedService, futures);
                cancelAll(futures);
                return result;
            } catch (InterruptedException e) {
                //todo Ignore and retry
            }
            return null;
        } else {
            try {
                return executeOnSource(tableName, action);
            } catch (IOException e) {
                throw new HydraQLTableOpException(e);
            }
        }
    }

    default <T> T getFirstToComplete(CompletionService<T> hedgedService,
                                     ArrayList<Future<T>> futures) throws InterruptedException {
        if (futures.isEmpty()) {
            throw new InterruptedException("let's retry.");
        }
        Future<T> future = null;
        try {
            future = hedgedService.take();
            T t = future.get();
            futures.remove(future);
            return t;
        } catch (ExecutionException | CancellationException e) {
            futures.remove(future);
        }

        throw new InterruptedException("let's retry.");
    }

    default <T> void cancelAll(List<Future<T>> futures) {
        for (Future<T> future : futures) {
            future.cancel(false);
        }
    }

    default <T> T executeOnSource(String tableName, TableCallback<T, Table> action) throws IOException {
        try (Table table = this.getConnection().getTable(TableName.valueOf(tableName))) {
            return action.doInTable(table);
        } catch (Throwable throwable) {
            throw new IOException(throwable);
        }
    }

    default <T> T executeOnTarget(String tableName, TableCallback<T, Table> action) throws IOException {
        try (Table table = this.getHedgedReadConnection().getTable(TableName.valueOf(tableName))) {
            return action.doInTable(table);
        } catch (Throwable throwable) {
            throw new IOException(throwable);
        }
    }

    default void executeSave(String tableName, Put put) {
        if (this.hedgedReadWriteDisable()) {
            try {
                this.executeOnSource(tableName, table -> {
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
                this.executeOnSource(tableName, table -> {
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

    default boolean hedgedReadIsOpen() {
        return this.getHBaseClientConf().hedgedReadIsOpen();
    }

    default boolean hedgedReadWriteDisable() {
        return this.getHBaseClientConf().isHedgedReadWriteDisable();
    }

    default int getHedgedReadThreadpoolSize() {
        return this.getHBaseClientConf().getHedgedReadThreadpoolSize();
    }

    default long getHedgedReadThresholdMillis() {
        return this.getHBaseClientConf().getHedgedReadThresholdMillis();
    }
}
