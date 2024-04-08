package com.hydraql.adapter.executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author leojie@apache.org 2024/4/8 19:43
 */
public class ConsistencyHedgedReadStrategy implements HedgedReadStrategy {
    private final int maxThreads;

    public ConsistencyHedgedReadStrategy(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    @Override
    public <T> T execute(Callable<T> prefer, Callable<T> spare) throws IOException {
        if (prefer == null || spare == null) {
            throw new IOException("Please pass two callable objects.");
        }
        ArrayList<Future<T>> futures = new ArrayList<>();
        ThreadPoolExecutor executor = HedgedReadExecutor.create().getExecutor(maxThreads);
        Future<T> preferRequest = executor.submit(prefer);
        futures.add(preferRequest);
        Future<T> spareRequest = executor.submit(spare);
        futures.add(spareRequest);
        List<T> resultList = new ArrayList<>(2);

        try {
            while (!futures.isEmpty()) {
                Iterator<Future<T>> iterator = futures.iterator();
                while (iterator.hasNext()) {
                    Future<T> resFuture = iterator.next();
                    if (resFuture.isDone() && !resFuture.isCancelled()) {
                        resultList.add(resFuture.get());
                        iterator.remove();
                    }
                }
            }
        } catch (Exception e) {
            throw new IOException(e);
        }
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }
}
