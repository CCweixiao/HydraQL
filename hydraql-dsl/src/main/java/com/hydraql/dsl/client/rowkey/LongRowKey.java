package com.hydraql.dsl.client.rowkey;

import com.hydraql.common.type.ColumnType;
import com.hydraql.dsl.client.rowkey.func.ConvertLongRowKeyFunc;

/**
 * @author leojie 2022/12/3 12:49
 */
public class LongRowKey extends BaseRowKey<Long> {
    public LongRowKey(String value) {
        super(value, new ConvertLongRowKeyFunc());
    }

    @Override
    public ColumnType columnType() {
        return ColumnType.LongType;
    }
}
