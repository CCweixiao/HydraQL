package com.hydraql.adapter.context;

import org.apache.hadoop.hbase.client.BufferedMutator;

/**
 * @author leojie 2024/4/7 14:09
 */
public class BatchSaveOptions {
    private static final int UNSET = -1;
    private final long writeBufferSize;
    private final long writeBufferPeriodicFlushMs;
    private final long writeBufferPeriodicFlushTimeoutMs;
    private final boolean autoFlush;
    private final BufferedMutator.ExceptionListener exceptionListener;

    public BatchSaveOptions(Builder builder) {
        this.writeBufferSize = builder.writeBufferSize;
        this.writeBufferPeriodicFlushMs = builder.writeBufferPeriodicFlushMs;
        this.writeBufferPeriodicFlushTimeoutMs = builder.writeBufferPeriodicFlushTimeoutMs;
        this.autoFlush = builder.autoFlush;
        this.exceptionListener = builder.exceptionListener;
    }

    public static class Builder {
        private long writeBufferSize = UNSET;
        private long writeBufferPeriodicFlushMs = UNSET;
        private long writeBufferPeriodicFlushTimeoutMs = UNSET;
        private boolean autoFlush;
        private BufferedMutator.ExceptionListener exceptionListener;

        public Builder writeBufferSize(long writeBufferSize) {
            this.writeBufferSize = writeBufferSize;
            return this;
        }

        public Builder writeBufferPeriodicFlushMs(long writeBufferPeriodicFlushMs) {
            this.writeBufferPeriodicFlushMs = writeBufferPeriodicFlushMs;
            return this;
        }

        public Builder writeBufferPeriodicFlushTimeoutMs(long writeBufferPeriodicFlushTimeoutMs) {
            this.writeBufferPeriodicFlushTimeoutMs = writeBufferPeriodicFlushTimeoutMs;
            return this;
        }

        public Builder autoFlush(boolean autoFlush) {
            this.autoFlush = autoFlush;
            return this;
        }

        public Builder exceptionListener(BufferedMutator.ExceptionListener exceptionListener) {
            this.exceptionListener = exceptionListener;
            return this;
        }

        public BatchSaveOptions build() {
            return new BatchSaveOptions(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getWriteBufferSize() {
        return writeBufferSize;
    }

    public long getWriteBufferPeriodicFlushMs() {
        return writeBufferPeriodicFlushMs;
    }

    public long getWriteBufferPeriodicFlushTimeoutMs() {
        return writeBufferPeriodicFlushTimeoutMs;
    }

    public boolean isAutoFlush() {
        return autoFlush;
    }

    public BufferedMutator.ExceptionListener getExceptionListener() {
        return exceptionListener;
    }
}
