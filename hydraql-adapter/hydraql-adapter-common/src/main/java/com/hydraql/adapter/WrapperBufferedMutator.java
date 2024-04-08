package com.hydraql.adapter;

import com.hydraql.adapter.context.HTableContext;
import org.apache.hadoop.hbase.client.Mutation;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author leojie 2024/4/7 15:12
 */
public interface WrapperBufferedMutator extends Closeable {
    long MIN_WRITE_BUFFER_PERIODIC_FLUSH_TIMER_TICK_MS = 100;

    default void mutate(Mutation mutation) throws IOException {
        mutate(Collections.singletonList(mutation));
    }

    void mutate(List<? extends Mutation> mutations) throws IOException;

    void flush() throws IOException;

    default void setWriteBufferPeriodicFlush(long timeoutMs, long timerTickMs) {
        throw new UnsupportedOperationException(
                "The BufferedMutator::setWriteBufferPeriodicFlush has not been implemented");
    }

    default void disableWriteBufferPeriodicFlush() {
        setWriteBufferPeriodicFlush(0, MIN_WRITE_BUFFER_PERIODIC_FLUSH_TIMER_TICK_MS);
    }

    HTableContext getTableContext();

    default boolean autoFlush() {
        return getTableContext().isAutoFlush();
    }
}
