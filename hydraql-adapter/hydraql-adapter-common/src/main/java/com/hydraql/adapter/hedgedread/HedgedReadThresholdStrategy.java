package com.hydraql.adapter.hedgedread;

import com.hydraql.adapter.WrapperBufferedMutator;
import com.hydraql.adapter.context.HTableContext;
import com.hydraql.adapter.service.AbstractHTableService;
import com.hydraql.common.callback.MutatorCallback;
import com.hydraql.common.callback.TableCallback;
import com.hydraql.common.exception.HydraQLTableOpException;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author leojie@apache.org 2024/4/8 17:44
 */
public class HedgedReadThresholdStrategy extends AbstractHedgedReadStrategy {
    private final int maxThreads;
    private final long thresholdMillis;

    public HedgedReadThresholdStrategy(AbstractHTableService tableService) {
        super(tableService);
        this.maxThreads = this.getHBaseClientConf().getHedgedReadThreadpoolSize();
        this.thresholdMillis = this.getHBaseClientConf().getHedgedReadThresholdMillis();
    }

    protected HedgedReadThresholdStrategy(long thresholdMillis, AbstractHTableService tableService) {
        super(tableService);
        this.maxThreads = this.getHBaseClientConf().getHedgedReadThreadpoolSize();
        this.thresholdMillis = thresholdMillis;
    }

    @Override
    public <T> T execute(String tableName, TableCallback<T, Table> action) {
        Callable<T> prefer = () -> executeOnPrefer(tableName, action);
        Callable<T> spare = () -> executeOnSpare(tableName, action);
        try {
            return execute(prefer, spare);
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

    @Override
    public void mutate(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) {
        if (this.getHBaseClientConf().isHedgedReadWriteDisable()) {
            try {
                executeOnPreferWithBuffer(tableContext, action);
            } catch (IOException e) {
                throw new HydraQLTableOpException(e);
            }
            return;
        }
        Callable<Void> prefer = () -> {
            executeOnPreferWithBuffer(tableContext, action);
            return null;
        };
        Callable<Void> spare = () -> {
            executeOnSpareWithBuffer(tableContext, action);
            return null;
        };
        try {
            execute(prefer, spare);
        } catch (RejectedExecutionException e) {
            try {
                executeOnPreferWithBuffer(tableContext, action);
            } catch (IOException ex) {
                throw new HydraQLTableOpException(ex);
            }
        } catch (IOException e) {
            throw new HydraQLTableOpException(e);
        }
    }

    private <T> T execute(Callable<T> prefer, Callable<T> spare) throws IOException {
        ArrayList<Future<T>> futures = new ArrayList<>();
        CompletionService<T> hedgedService = this.getHedgedReadService(maxThreads);

        Future<T> preferRequest = hedgedService.submit(prefer);
        futures.add(preferRequest);

        Future<T> future = null;
        try {
            if (thresholdMillis <= 0) {
                future = hedgedService.poll();
            } else {
                future = hedgedService.poll(thresholdMillis, TimeUnit.MILLISECONDS);
            }
            if (future != null) {
                return future.get();
            }
        } catch (ExecutionException e) {
            futures.remove(future);
        } catch (InterruptedException e) {
            throw new IOException("Interrupted while waiting for reading/writing task", e);
        }

        Future<T> spareRequest = hedgedService.submit(spare);
        futures.add(spareRequest);

        try {
            T result = getFirstToComplete(hedgedService, futures);
            cancelAll(futures);
            return result;
        } catch (InterruptedException e) {
            //todo Ignore and retry
        }
        return null;
    }
}
