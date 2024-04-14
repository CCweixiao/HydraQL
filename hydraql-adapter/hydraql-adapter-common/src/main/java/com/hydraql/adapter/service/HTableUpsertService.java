package com.hydraql.adapter.service;

import com.hydraql.adapter.WrapperBufferedMutator;
import com.hydraql.adapter.context.HTableContext;
import com.hydraql.adapter.hedgedread.HedgedReadStrategy;
import com.hydraql.common.callback.MutatorCallback;
import com.hydraql.common.exception.HTableServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;

import java.io.IOException;
import java.util.List;

/**
 * @author leojie@apache.org 2024/4/12 19:49
 */
public abstract class HTableUpsertService extends HTableQueryService {
    protected HTableUpsertService(Configuration configuration) {
        super(configuration);
    }

    public void execSinglePut(String tableName, Put put) {
        HedgedReadStrategy hedgedReadStrategy = createHedgedReadStrategy();
        hedgedReadStrategy.execute(tableName, table -> {
            table.put(put);
            return true;
        });
    }

    public void execSingleDelete(String tableName, Delete delete) {
        HedgedReadStrategy hedgedReadStrategy = createHedgedReadStrategy();
        hedgedReadStrategy.execute(tableName, table -> {
            table.delete(delete);
            return true;
        });
    }

    public void execBatchPuts(String tableName, List<Mutation> puts) {
        this.executeMutationBatch(HTableContext.createDefault(tableName), puts);
    }

    public void execBatchPuts(HTableContext tableContext, List<Mutation> puts) {
        this.executeMutationBatch(tableContext, puts);
    }

    public void execPutWithBuffer(HTableContext tableContext, Mutation put) {
        this.executeMutation(tableContext, put);
    }

    public void execBatchDeletes(HTableContext tableContext, List<Mutation> deletes) {
        this.executeMutationBatch(tableContext, deletes);
    }

    public void execDeleteWithBuffer(HTableContext tableContext, Mutation delete) {
        if (delete == null) {
            return;
        }
        this.executeMutation(tableContext, delete);
    }

    public void execBatchDeletes(String tableName, List<Mutation> deletes) {
        this.executeMutationBatch(HTableContext.createDefault(tableName), deletes);
    }

    private void mutate(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) throws IOException {
        HedgedReadStrategy hedgedReadStrategy = createHedgedReadStrategy();
        hedgedReadStrategy.mutate(tableContext, action);
    }

    private void executeMutationBatch(HTableContext tableContext, List<Mutation> mutations) {
        if (mutations == null || mutations.isEmpty()) {
            return;
        }
        try {
            this.mutate(tableContext, mutator -> mutator.mutate(mutations));
        } catch (IOException e) {
            throw new HTableServiceException(e);
        }
    }

    private void executeMutation(HTableContext tableContext, Mutation mutation) {
        if (mutation == null) {
            return;
        }

        try {
            this.mutate(tableContext, mutator -> mutator.mutate(mutation));
        } catch (IOException e) {
            throw new HTableServiceException(e);
        }
    }
}
