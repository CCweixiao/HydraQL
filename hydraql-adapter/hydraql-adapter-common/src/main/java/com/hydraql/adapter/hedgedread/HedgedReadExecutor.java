package com.hydraql.adapter.hedgedread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author leojie 2023/6/30 18:49
 */
public class HedgedReadExecutor {
    private static final Logger LOG = LoggerFactory.getLogger(HedgedReadExecutor.class);
    private static volatile HedgedReadExecutor executor;
    private static ThreadPoolExecutor HEDGED_READ_THREAD_POOL;

    private HedgedReadExecutor() {
    }

    public static HedgedReadExecutor create() {
        if (executor == null) {
            synchronized (HedgedReadExecutor.class) {
                if (executor == null) {
                    executor = new HedgedReadExecutor();
                }
            }
        }
        return executor;
    }

    public synchronized ThreadPoolExecutor getExecutor(int num) {
        if (HEDGED_READ_THREAD_POOL != null) {
            return HEDGED_READ_THREAD_POOL;
        }

        HEDGED_READ_THREAD_POOL = new ThreadPoolExecutor(1, num, 60,
                TimeUnit.SECONDS, new SynchronousQueue<>(),
                new Daemon.DaemonFactory() {
                    private final AtomicInteger threadIndex = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = super.newThread(r);
                        t.setName("hedgedRead-" + threadIndex.getAndIncrement());
                        return t;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy() {
                    @Override
                    public void rejectedExecution(Runnable r,
                                                  ThreadPoolExecutor e) {
                        LOG.warn("Execution rejected, Executing in current thread");
                        // will run in the current thread
                        throw new RejectedExecutionException("Task " + r.toString() +
                                " rejected from " + e.toString());
                    }
                });
        HEDGED_READ_THREAD_POOL.allowCoreThreadTimeOut(true);

        return HEDGED_READ_THREAD_POOL;
    }
}
