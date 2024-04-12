package com.hydraql.adapter.service;

import com.hydraql.adapter.WrapperBufferedMutator;
import com.hydraql.adapter.context.ConnectionContext;
import com.hydraql.adapter.context.HTableContext;

/**
 * @author leojie@apache.org 2024/4/12 22:56
 */
public abstract class AbstractHTableService implements ConnectionContext {
    public abstract WrapperBufferedMutator getWrapperBufferedMutator(HTableContext tableContext);

    public abstract WrapperBufferedMutator getHedgedReadWrapperBufferedMutator(HTableContext tableContext);
}
