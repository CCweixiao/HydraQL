package com.hydraql.adapter;

import com.hydraql.adapter.WrapperBufferedMutator;
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
        this.init();
    }

    private void init() {
        // todo fix
    }

    public HTableContext getTableContext() {
        return tableContext;
    }

    public BufferedMutator getBufferedMutator() {
        return bufferedMutator;
    }

    @Override
    public void mutate(Mutation mutation) throws IOException {
        bufferedMutator.mutate(mutation);
        if (autoFlush()) {
            bufferedMutator.flush();
        }
    }

    @Override
    public void mutate(List<? extends Mutation> mutations) throws IOException {
        bufferedMutator.mutate(mutations);
        if (autoFlush()) {
            bufferedMutator.flush();
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

    public boolean autoFlush() {
        return this.getTableContext().getBatchSaveOptions().isAutoFlush();
    }
}
