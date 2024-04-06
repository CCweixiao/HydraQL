package com.hydraql.adapter;

import org.apache.hadoop.util.Daemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author leojie 2023/6/30 18:49
 */
public class HBaseHedgedReadExecutor {
    private static final Logger LOG = LoggerFactory.getLogger(HBaseHedgedReadExecutor.class);
    private static volatile HBaseHedgedReadExecutor executor;
    private static ThreadPoolExecutor HEDGED_READ_THREAD_POOL;

    private HBaseHedgedReadExecutor() {
    }

    public static HBaseHedgedReadExecutor create() {
        if (executor == null) {
            synchronized (HBaseHedgedReadExecutor.class) {
                if (executor == null) {
                    executor = new HBaseHedgedReadExecutor();
                }
            }
        }
        return executor;
    }

    public synchronized ThreadPoolExecutor getExecutor(int num) {
        if (HEDGED_READ_THREAD_POOL != null) return HEDGED_READ_THREAD_POOL;

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
                    public void rejectedExecution(Runnable runnable,
                                                  ThreadPoolExecutor e) {
                        LOG.info("Execution rejected, Executing in current thread");
                        // will run in the current thread
                        super.rejectedExecution(runnable, e);
                    }
                });
        HEDGED_READ_THREAD_POOL.allowCoreThreadTimeOut(true);

        return HEDGED_READ_THREAD_POOL;
    }
}
