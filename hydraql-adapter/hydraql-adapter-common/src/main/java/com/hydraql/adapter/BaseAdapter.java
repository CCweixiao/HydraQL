package com.hydraql.adapter;

import com.hydraql.adapter.context.HTableContext;
import com.hydraql.common.callback.AdminCallback;
import com.hydraql.common.callback.MutatorCallback;
import com.hydraql.common.callback.TableCallback;
import com.hydraql.common.exception.HBaseOperationsException;
import com.hydraql.common.exception.HBaseSdkException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.yetus.audience.InterfaceAudience;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author leo.jie (leojie1314@gmail.com)
 */
@InterfaceAudience.Private
public interface BaseAdapter {
    HBaseClientConf getHBaseClientConf();

    WrapperBufferedMutator getWrapperBufferedMutator(HTableContext tableContext);

    WrapperBufferedMutator getHedgedReadWrapperBufferedMutator(HTableContext tableContext);





    default void executeOnSource(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) throws IOException {
        try {
            WrapperBufferedMutator mutator = this.getWrapperBufferedMutator(tableContext);
            action.doInMutator(mutator);
        } catch (Throwable throwable) {
            throw new HBaseOperationsException(throwable);
        }
    }

    default void executeOnTarget(String tableName, MutatorCallback<BufferedMutator> action) throws IOException {
        try {
            BufferedMutator mutator = this.getHedgedReadBufferedMutator(tableName);
            action.doInMutator(mutator);
        } catch (Throwable throwable) {
            throw new HBaseOperationsException(throwable);
        }
    }

    default void execute(String tableName, MutatorCallback<BufferedMutator> action) {
        if (getHBaseClientConf().hedgedReadIsOpen() && !getHBaseClientConf().isHedgedReadWriteDisable()) {
            ArrayList<Future<Void>> futures = new ArrayList<>();
            CompletionService<Void> hedgedService =
                    new ExecutorCompletionService<>(HBaseHedgedReadExecutor.create()
                            .getExecutor(getHBaseClientConf().getHedgedReadThreadpoolSize()));

            Callable<Void> executeInSource = () -> {
                executeOnSource(tableName, action);
                return null;
            };

            Future<Void> firstRequest = hedgedService.submit(executeInSource);
            futures.add(firstRequest);
            Future<Void> future = null;
            try {
                long thresholdMillis = getHBaseClientConf().getHedgedReadThresholdMillis();
                future = hedgedService.poll(thresholdMillis, TimeUnit.MICROSECONDS);
                long start = System.currentTimeMillis();
                while (future == null && (System.currentTimeMillis() - start) < thresholdMillis) {
                    future = hedgedService.poll(thresholdMillis, TimeUnit.MICROSECONDS);
                }
                if (future != null) {
                    future.get();
                    return;
                }
            } catch (ExecutionException e) {
                futures.remove(future);
            } catch (InterruptedException e) {
                throw new HBaseSdkException("Interrupted while waiting for reading task.");
            }

            Callable<Void> executeInTarget = () -> {
                executeOnTarget(tableName, action);
                return null;
            };

            Future<Void> oneMoreRequest = hedgedService.submit(executeInTarget);
            futures.add(oneMoreRequest);

            try {
                getFirstToComplete(hedgedService, futures);
                cancelAll(futures);
            } catch (InterruptedException e) {
                throw new HBaseSdkException(e);
            }

        } else {
            try {
                executeOnSource(tableName, action);
            } catch (IOException e) {
                throw new HBaseSdkException(e);
            }
        }
    }


    default void executeSaveBatch(String tableName, List<Mutation> puts) {
        this.executeMutationBatch(tableName, puts);
    }

    default void executeDeleteBatch(String tableName, List<Mutation> deletes) {
        this.executeMutationBatch(tableName, deletes);
    }

    default void executeMutationBatch(String tableName, List<Mutation> mutations) {
        if (mutations == null || mutations.isEmpty()) {
            return;
        }
        Mutation firstDelete = mutations.get(0);
        long deleteSize = firstDelete.heapSize();

        this.execute(tableName, mutator -> {
            long writeBufferSize = mutator.getWriteBufferSize();
            long batchSize = writeBufferSize / deleteSize;
            if (batchSize <= 0) {
                batchSize = 1;
            }
            int index = 0;
            for (Mutation mutation : mutations) {
                index += 1;
                mutator.mutate(mutation);
                if (index == batchSize) {
                    mutator.flush();
                    index = 0;
                }
            }
            mutator.flush();
        });
    }
}
