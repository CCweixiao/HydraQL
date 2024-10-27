package com.hydraql.adapter;

import com.hydraql.HQLTable;
import com.hydraql.activerecord.Model;
import com.hydraql.conf.AbstractHQLConfiguration;
import com.hydraql.executor.BaseExecutor;

/**
 * @author leojie@apache.org 2024/10/27 12:37
 */
public class HQLTableImpl extends HQLTable {
    public HQLTableImpl(String tableName, AbstractHQLConfiguration configuration) {
        super(tableName, configuration);
    }

    public HQLTableImpl(Class<? extends Model<?>> tableEntityClass, AbstractHQLConfiguration configuration) {
        super(tableEntityClass, configuration);
    }

    @Override
    public BaseExecutor newExecutor() {
        return new HQlExecutor(this);
    }
}
