package com.hydraql.adapter;

import org.apache.hadoop.hbase.client.Mutation;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author leojie 2024/4/7 15:12
 */
public interface WrapperBufferedMutator extends Closeable {
    void mutate(Mutation mutation) throws IOException;

    void flush() throws IOException;
}
