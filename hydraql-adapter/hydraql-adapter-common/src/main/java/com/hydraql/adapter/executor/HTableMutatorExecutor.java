package com.hydraql.adapter.executor;

import com.hydraql.adapter.WrapperBufferedMutator;
import com.hydraql.adapter.context.HTableContext;
import com.hydraql.common.callback.MutatorCallback;
import com.hydraql.common.exception.HydraQLTableOpException;
import org.apache.hadoop.hbase.client.Mutation;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author leojie 2024/4/7 19:51
 */
public interface HTableMutatorExecutor extends HTableSingleExecutor {
    WrapperBufferedMutator getWrapperBufferedMutator(HTableContext tableContext);

    WrapperBufferedMutator getHedgedReadWrapperBufferedMutator(HTableContext tableContext);

    default void executeOnPrefer(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) throws IOException {
        try {
            WrapperBufferedMutator mutator = this.getWrapperBufferedMutator(tableContext);
            action.doInMutator(mutator);
        } catch (Throwable throwable) {
            throw new IOException(throwable);
        }
    }

    default void executeOnSpare(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) throws IOException {
        try {
            WrapperBufferedMutator mutator = this.getHedgedReadWrapperBufferedMutator(tableContext);
            action.doInMutator(mutator);
        } catch (Throwable throwable) {
            throw new IOException(throwable);
        }
    }

    default void execute(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) throws IOException {
        HedgedReadStrategy hedgedReadStrategy = createHedgedReadStrategy();
        if (hedgedReadStrategy != null && !hedgedReadWriteDisable()) {
            Callable<Void> preferCall = () -> {
                executeOnPrefer(tableContext, action);
                return null;
            };
            Callable<Void> spareCall = () -> {
                executeOnSpare(tableContext, action);
                return null;
            };
            hedgedReadStrategy.execute(preferCall, spareCall);
        } else {
            executeOnPrefer(tableContext, action);
        }
    }

    default void executeMutationBatch(HTableContext tableContext, List<Mutation> mutations) {
        if (mutations == null || mutations.isEmpty()) {
            return;
        }
        try {
            this.execute(tableContext, mutator -> mutator.mutate(mutations));
        } catch (IOException e) {
            throw new HydraQLTableOpException(e);
        }
    }

    default void execBatchPuts(String tableName, List<Mutation> puts) {
        this.executeMutationBatch(HTableContext.createDefault(tableName), puts);
    }

    default void execBatchPuts(HTableContext tableContext, List<Mutation> puts) {
        this.executeMutationBatch(tableContext, puts);
    }

    default void execBatchDeletes(HTableContext tableContext, List<Mutation> deletes) {
        this.executeMutationBatch(tableContext, deletes);
    }

    default void execBatchDeletes(String tableName, List<Mutation> deletes) {
        this.executeMutationBatch(HTableContext.createDefault(tableName), deletes);
    }
}
