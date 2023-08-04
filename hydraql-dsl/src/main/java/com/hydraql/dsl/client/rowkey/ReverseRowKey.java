package com.hydraql.dsl.client.rowkey;

import com.hydraql.common.type.ColumnType;
import com.hydraql.dsl.client.rowkey.func.ReverseRowKeyFunc;

/**
 * @author leojie 2022/12/3 12:57
 */
public class ReverseRowKey extends BaseRowKey<String> {

    public ReverseRowKey(String value) {
        super(value, new ReverseRowKeyFunc());
    }

    @Override
    public ColumnType columnType() {
        return ColumnType.StringType;
    }
}
