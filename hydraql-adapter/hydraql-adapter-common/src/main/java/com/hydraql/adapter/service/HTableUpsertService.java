package com.hydraql.adapter.service;

import com.hydraql.adapter.WrapperBufferedMutator;
import com.hydraql.adapter.connection.HBaseConnectionManager;
import com.hydraql.adapter.context.HTableContext;
import com.hydraql.adapter.hedgedread.HedgedReadStrategy;
import com.hydraql.common.callback.MutatorCallback;
import com.hydraql.common.exception.HydraQLTableOpException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author leojie@apache.org 2024/4/12 19:49
 */
public abstract class HTableUpsertService extends HTableQueryService {
    protected HTableUpsertService(Configuration configuration) {
        super(configuration);
    }

    protected abstract WrapperBufferedMutator getWrapperBufferedMutator(HTableContext tableContext);

    protected abstract WrapperBufferedMutator getHedgedReadWrapperBufferedMutator(HTableContext tableContext);

    public void execSinglePut(String tableName, Put put) {
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

    public void execSingleDelete(String tableName, Delete delete) {
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

    public void execBatchPuts(String tableName, List<Mutation> puts) {
        this.executeMutationBatch(HTableContext.createDefault(tableName), puts);
    }

    public void execBatchPuts(HTableContext tableContext, List<Mutation> puts) {
        this.executeMutationBatch(tableContext, puts);
    }

    public void execBatchDeletes(HTableContext tableContext, List<Mutation> deletes) {
        this.executeMutationBatch(tableContext, deletes);
    }

    public void execBatchDeletes(String tableName, List<Mutation> deletes) {
        this.executeMutationBatch(HTableContext.createDefault(tableName), deletes);
    }

    private void executeOnPreferWithBuffer(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) throws IOException {
        try {
            WrapperBufferedMutator mutator = this.getWrapperBufferedMutator(tableContext);
            action.doInMutator(mutator);
        } catch (Throwable throwable) {
            throw new IOException(throwable);
        }
    }

    private void executeOnSpareWithBuffer(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) throws IOException {
        try {
            WrapperBufferedMutator mutator = this.getHedgedReadWrapperBufferedMutator(tableContext);
            action.doInMutator(mutator);
        } catch (Throwable throwable) {
            throw new IOException(throwable);
        }
    }

    private void executeWithBuffer(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) throws IOException {
        HedgedReadStrategy hedgedReadStrategy = createHedgedReadStrategy();
        if (hedgedReadStrategy != null && !hedgedReadWriteDisable()) {
            Callable<Void> preferCall = () -> {
                executeOnPreferWithBuffer(tableContext, action);
                return null;
            };
            Callable<Void> spareCall = () -> {
                executeOnSpareWithBuffer(tableContext, action);
                return null;
            };
            hedgedReadStrategy.execute(preferCall, spareCall);
        } else {
            executeOnPreferWithBuffer(tableContext, action);
        }
    }

    protected BufferedMutator getBufferedMutator(HTableContext tableContext) {
        return HBaseConnectionManager.create().getBufferedMutator(tableContext,
                this.getConfiguration(), this.getConnection());
    }

    protected BufferedMutator getHedgedReadBufferedMutator(HTableContext tableContext) {
        return HBaseConnectionManager.create().getBufferedMutator(tableContext,
                this.getHedgedReadConfiguration(), this.getHedgedReadConnection());
    }

    private void executeMutationBatch(HTableContext tableContext, List<Mutation> mutations) {
        if (mutations == null || mutations.isEmpty()) {
            return;
        }
        try {
            this.executeWithBuffer(tableContext, mutator -> mutator.mutate(mutations));
        } catch (IOException e) {
            throw new HydraQLTableOpException(e);
        }
    }
}
