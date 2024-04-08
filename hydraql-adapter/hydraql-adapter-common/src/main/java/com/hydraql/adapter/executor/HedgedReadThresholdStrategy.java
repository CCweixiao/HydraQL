package com.hydraql.adapter.executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author leojie@apache.org 2024/4/8 17:44
 */
public class HedgedReadThresholdStrategy implements HedgedReadStrategy {
    private final long thresholdMillis;
    private final int maxThreads;

    public HedgedReadThresholdStrategy(long thresholdMillis, int maxThreads) {
        this.thresholdMillis = thresholdMillis;
        this.maxThreads = maxThreads;
    }

    @Override
    public <T> T execute(Callable<T> prefer, Callable<T> spare) throws IOException {
        if (prefer == null || spare == null) {
            throw new IOException("Please pass two callable objects.");
        }

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
