package com.hydraql.adapter.executor;

import com.hydraql.adapter.WrapperBufferedMutator;
import com.hydraql.adapter.context.HTableContext;
import com.hydraql.common.callback.MutatorCallback;
import com.hydraql.common.exception.HydraQLTableOpException;
import org.apache.hadoop.hbase.client.Mutation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author leojie 2024/4/7 19:51
 */
public interface HTableBatchOpExecutor extends HTableOpExecutor {
    WrapperBufferedMutator getWrapperBufferedMutator(HTableContext tableContext);

    WrapperBufferedMutator getHedgedReadWrapperBufferedMutator(HTableContext tableContext);

    default void executeOnSource(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) throws IOException {
        try {
            WrapperBufferedMutator mutator = this.getWrapperBufferedMutator(tableContext);
            action.doInMutator(mutator);
        } catch (Throwable throwable) {
            throw new IOException(throwable);
        }
    }

    default void executeOnTarget(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) throws IOException {
        try {
            WrapperBufferedMutator mutator = this.getHedgedReadWrapperBufferedMutator(tableContext);
            action.doInMutator(mutator);
        } catch (Throwable throwable) {
            throw new IOException(throwable);
        }
    }

    default void execute(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) {
        if (this.hedgedReadIsOpen() && !this.hedgedReadWriteDisable()) {
            ArrayList<Future<Void>> futures = new ArrayList<>();
            CompletionService<Void> hedgedService =
                    new ExecutorCompletionService<>(HBaseHedgedReadExecutor.create()
                            .getExecutor(this.getHedgedReadThreadpoolSize()));

            Callable<Void> executeInSource = () -> {
                executeOnSource(tableContext, action);
                return null;
            };

            Future<Void> firstRequest = hedgedService.submit(executeInSource);
            futures.add(firstRequest);
            Future<Void> future = null;
            try {
                long thresholdMillis = this.getHedgedReadThresholdMillis();
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
                throw new HydraQLTableOpException("Interrupted while waiting for reading task.");
            }

            Callable<Void> executeInTarget = () -> {
                executeOnTarget(tableContext, action);
                return null;
            };
            Future<Void> oneMoreRequest = hedgedService.submit(executeInTarget);
            futures.add(oneMoreRequest);
            try {
                getFirstToComplete(hedgedService, futures);
                cancelAll(futures);
            } catch (InterruptedException e) {
                //todo Ignore and retry
            }

        } else {
            try {
                executeOnSource(tableContext, action);
            } catch (IOException e) {
                throw new HydraQLTableOpException(e);
            }
        }
    }

    default void executeMutationBatch(HTableContext tableContext, List<Mutation> mutations) {
        if (mutations == null || mutations.isEmpty()) {
            return;
        }
        this.execute(tableContext, mutator -> mutator.mutate(mutations));
    }

    default void executeSaveBatch(String tableName, List<Mutation> puts) {
        this.executeMutationBatch(HTableContext.createDefault(tableName), puts);
    }

    default void executeSaveBatch(HTableContext tableContext, List<Mutation> puts) {
        this.executeMutationBatch(tableContext, puts);
    }

    default void executeDeleteBatch(HTableContext tableContext, List<Mutation> deletes) {
        this.executeMutationBatch(tableContext, deletes);
    }

    default void executeDeleteBatch(String tableName, List<Mutation> deletes) {
        this.executeMutationBatch(HTableContext.createDefault(tableName), deletes);
    }
}
