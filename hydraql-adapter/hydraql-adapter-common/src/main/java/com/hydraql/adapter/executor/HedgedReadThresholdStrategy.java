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
class HedgedReadThresholdStrategy implements HedgedReadStrategy {
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
        long start = System.currentTimeMillis();
        try {
            if (thresholdMillis <= 0) {
                future = hedgedService.poll();
            } else {
                future = hedgedService.poll(thresholdMillis, TimeUnit.MILLISECONDS);
            }

            if (future != null) {
                System.out.println(Thread.currentThread().getName() + "主集群获取耗时：" + (System.currentTimeMillis() - start) + "ms");
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
            start = System.currentTimeMillis();
            T result = getFirstToComplete(hedgedService, futures);
            System.out.println(Thread.currentThread().getName() + "备集群获取耗时：" + (System.currentTimeMillis() - start) + "ms");
            cancelAll(futures);
            return result;
        } catch (InterruptedException e) {
            //todo Ignore and retry
        }
        return null;
    }
}
