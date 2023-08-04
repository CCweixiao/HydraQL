package com.hydraql.dsl.client.rowkey;

import com.hydraql.common.type.ColumnType;
import com.hydraql.dsl.client.rowkey.func.Md5RowKeyFunc;

/**
 * @author leojie 2022/12/3 12:53
 */
public class Md5RowKey extends BaseRowKey<String> {

    public Md5RowKey(String value) {
        super(value, new Md5RowKeyFunc());
    }
    @Override
    public ColumnType columnType() {
        return ColumnType.StringType;
    }
}
