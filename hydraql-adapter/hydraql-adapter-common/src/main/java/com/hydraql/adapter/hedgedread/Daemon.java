package com.hydraql.adapter.hedgedread;

import java.util.concurrent.ThreadFactory;

/**
 * @author leojie@apache.org 2024/4/8 16:52
 */
class Daemon extends Thread {

    {
        setDaemon(true);                              // always a daemon
    }

    /**
     * Provide a factory for named daemon threads,
     * for use in ExecutorServices constructors
     */
    public static class DaemonFactory extends Daemon implements ThreadFactory {
        @Override
        public Thread newThread(Runnable runnable) {
            return new Daemon(runnable);
        }

    }

    Runnable runnable = null;

    /**
     * Construct a daemon thread.
     */
    public Daemon() {
        super();
    }

    /**
     * Construct a daemon thread.
     */
    public Daemon(Runnable runnable) {
        super(runnable);
        this.runnable = runnable;
        this.setName(((Object) runnable).toString());
    }

    /**
     * Construct a daemon thread to be part of a specified thread group.
     */
    public Daemon(ThreadGroup group, Runnable runnable) {
        super(group, runnable);
        this.runnable = runnable;
        this.setName(((Object) runnable).toString());
    }

    public Runnable getRunnable() {
        return runnable;
    }
}
