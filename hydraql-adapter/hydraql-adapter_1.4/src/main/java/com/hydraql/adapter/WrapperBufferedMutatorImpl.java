package com.hydraql.adapter;

import com.hydraql.adapter.context.HTableContext;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Mutation;

import java.io.IOException;
import java.util.List;

/**
 * @author leojie@apache.org 2024/4/7 22:18
 */
public class WrapperBufferedMutatorImpl implements WrapperBufferedMutator {
    private final HTableContext tableContext;
    private final BufferedMutator bufferedMutator;

    public WrapperBufferedMutatorImpl(HTableContext tableContext, BufferedMutator bufferedMutator) {
        this.tableContext = tableContext;
        this.bufferedMutator = bufferedMutator;
    }

    @Override
    public HTableContext getTableContext() {
        return tableContext;
    }

    @Override
    public void mutate(List<? extends Mutation> mutations) throws IOException {
        bufferedMutator.mutate(mutations);
        if (this.autoFlush()) {
            flush();
        }
    }

    @Override
    public void flush() throws IOException {
        bufferedMutator.flush();
    }

    @Override
    public void close() throws IOException {
        bufferedMutator.close();
    }
}
