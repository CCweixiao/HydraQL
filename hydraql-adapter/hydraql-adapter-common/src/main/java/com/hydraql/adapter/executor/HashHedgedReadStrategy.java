package com.hydraql.adapter.executor;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author leojie@apache.org 2024/4/8 20:07
 */
public class HashHedgedReadStrategy implements HedgedReadStrategy {
    private final LongAdder COUNTER = new LongAdder();
    private final int maxThreads;

    public HashHedgedReadStrategy(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    @Override
    public <T> T execute(Callable<T> prefer, Callable<T> spare) throws IOException {
        COUNTER.increment();
        ThreadPoolExecutor executor = HedgedReadExecutor.create().getExecutor(maxThreads);
        Future<T> targetFuture;
        if ((COUNTER.longValue() & 1) == 0) {
            targetFuture = executor.submit(prefer);
        } else {
            targetFuture = executor.submit(spare);
        }
        try {
            return targetFuture.get();
        } catch (InterruptedException e) {
            throw new IOException("Interrupted while waiting for reading/writing task", e);
        } catch (ExecutionException e) {
            throw new IOException("The reading/writing task failed to be executed", e);
        }
    }
}
