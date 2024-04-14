package com.hydraql.adapter.hedgedread;

import com.hydraql.adapter.WrapperBufferedMutator;
import com.hydraql.adapter.context.HTableContext;
import com.hydraql.adapter.service.AbstractHTableService;
import com.hydraql.common.callback.MutatorCallback;
import com.hydraql.common.callback.TableCallback;
import com.hydraql.common.exception.HTableServiceException;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author leojie@apache.org 2024/4/8 20:07
 */
public class HedgedReadHashStrategy extends AbstractHedgedReadStrategy {
    private final static LongAdder COUNTER = new LongAdder();

    public HedgedReadHashStrategy(AbstractHTableService tableService) {
        super(tableService);
    }

    @Override
    public <T> T execute(String tableName, TableCallback<T, Table> action) {
        if (this.getHBaseClientConf().isHedgedReadWriteDisable()) {
            try {
                return executeOnPrefer(tableName, action);
            } catch (IOException e) {
                throw new HTableServiceException(e);
            }
        } else {
            try {
                COUNTER.increment();
                if ((COUNTER.longValue() & 1) == 0) {
                    return executeOnPrefer(tableName, action);
                } else {
                    return executeOnSpare(tableName, action);
                }
            } catch (IOException e) {
                throw new HTableServiceException(e);
            }
        }
    }

    @Override
    public void mutate(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) {
        if (this.getHBaseClientConf().isHedgedReadWriteDisable()) {
            try {
                executeOnPreferWithBuffer(tableContext, action);
            } catch (IOException e) {
                throw new HTableServiceException(e);
            }
            return;
        }

        COUNTER.increment();
        try {
            if ((COUNTER.longValue() & 1) == 0) {
                executeOnPreferWithBuffer(tableContext, action);
            } else {
                executeOnPreferWithBuffer(tableContext, action);
            }
        } catch (IOException e) {
            throw new HTableServiceException(e);
        }
    }
}
