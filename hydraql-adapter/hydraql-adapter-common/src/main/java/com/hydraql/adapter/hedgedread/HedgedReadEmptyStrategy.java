package com.hydraql.adapter.hedgedread;

import com.hydraql.adapter.WrapperBufferedMutator;
import com.hydraql.adapter.context.HTableContext;
import com.hydraql.adapter.service.AbstractHTableService;
import com.hydraql.common.callback.MutatorCallback;
import com.hydraql.common.callback.TableCallback;
import com.hydraql.common.exception.HTableServiceException;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;

/**
 * @author leojie@apache.org 2024/4/14 20:58
 */
public class HedgedReadEmptyStrategy extends AbstractHedgedReadStrategy {

    public HedgedReadEmptyStrategy(AbstractHTableService tableService) {
        super(tableService);
    }

    @Override
    public <T> T execute(String tableName, TableCallback<T, Table> action) {
        try {
            return executeOnPrefer(tableName, action);
        } catch (IOException e) {
            throw new HTableServiceException(e);
        }
    }

    @Override
    public void mutate(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) {
        try {
            executeOnPreferWithBuffer(tableContext, action);
        } catch (IOException e) {
            throw new HTableServiceException(e);
        }
    }
}
