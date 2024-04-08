package com.hydraql.adapter.executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author leojie@apache.org 2024/4/8 19:43
 */
public class HedgedReadConsistencyStrategy implements HedgedReadStrategy {
    private final int maxThreads;
    private final long overallTimeoutMillis;

    public HedgedReadConsistencyStrategy(int maxThreads, long overallTimeoutMillis) {
        //todo 优化时间定义
        this.maxThreads = maxThreads;
        this.overallTimeoutMillis = overallTimeoutMillis;
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
        long timeoutMillis = overallTimeoutMillis / 2;
        if (timeoutMillis < 0) {
            timeoutMillis = 0;
        }
        while (!futures.isEmpty()) {
            Iterator<Future<T>> iterator = futures.iterator();
            while (iterator.hasNext()) {
                Future<T> future = iterator.next();
                try {
                    if (timeoutMillis > 0) {
                        resultList.add(future.get(timeoutMillis, TimeUnit.MILLISECONDS));
                    } else {
                        resultList.add(future.get());
                    }
                } catch (InterruptedException e) {
                    throw new IOException("Interrupted while waiting for reading/writing task", e);
                } catch (TimeoutException e) {
                    throw new IOException(String.format("Reading/writing task execution timeout," +
                            " the timeout is %sms, and overall timeout is %sms", timeoutMillis, overallTimeoutMillis), e);
                } catch (ExecutionException e) {
                    throw new IOException("Reading/writing task execution failed," +
                            " and the error reason is", e);
                }
                iterator.remove();
            }
        }

        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }
}
