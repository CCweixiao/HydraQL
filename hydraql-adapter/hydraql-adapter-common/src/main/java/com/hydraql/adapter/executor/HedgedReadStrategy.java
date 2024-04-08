package com.hydraql.adapter.executor;

import com.hydraql.adapter.HBaseClientConfigKeys;
import com.hydraql.common.util.StringUtil;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;

/**
 * @author leojie@apache.org 2024/4/8 17:29
 */
public interface HedgedReadStrategy {
    enum Level {
        /**
         * Based on time thresholds
         */
        THRESHOLD,

        /**
         * Two requests are initiated at the same time, and the first one returns a result
         */
        FIRST_ONE,

        /**
         * Multiple requests must all be processed
         */
        CONSISTENCY,

        /**
         * For multiple clusters, the request is made in hash mode
         */
        HASH,

        /**
         * Do not use hedged
         */
        NONE;
        public static HedgedReadStrategy.Level find(String strategy) {
            if (StringUtil.isBlank(strategy)) {
                return NONE;
            }
            for (Level level : Level.values()) {
                if (strategy.equalsIgnoreCase(level.name())) {
                    return level;
                }
            }
            throw new IllegalStateException(String.format("%s=%s is not supported yet",
                    HBaseClientConfigKeys.HedgedRead.STRATEGY, strategy));
        }
    }

    /**
     * 不同hedged read strategy策略中需要实现的方法
     *
     * @param prefer 首选执行
     * @param spare  备选执行
     * @param <T>    范型类型
     * @return 执行结果，可以是Void
     * @throws IOException ioe
     */
    <T> T execute(Callable<T> prefer, Callable<T> spare) throws IOException;

    default <T> CompletionService<T> getHedgedReadService(int maxThreads) {
        return new ExecutorCompletionService<>(
                HedgedReadExecutor.create().getExecutor(maxThreads));
    }

    default <T> T getFirstToComplete(CompletionService<T> hedgedService,
                                     List<Future<T>> futures) throws InterruptedException {
        if (futures.isEmpty()) {
            throw new InterruptedException("let's retry.");
        }

        Future<T> future = null;
        try {
            future = hedgedService.take();
            T t = future.get();
            futures.remove(future);
            return t;
        } catch (ExecutionException | CancellationException e) {
            futures.remove(future);
        }

        throw new InterruptedException("let's retry.");
    }

    default <T> void cancelAll(List<Future<T>> futures) {
        for (Future<T> future : futures) {
            future.cancel(false);
        }
    }
}
