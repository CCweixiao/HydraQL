package com.hydraql.dsl.client.rowkey;

import com.hydraql.common.type.ColumnType;
import com.hydraql.dsl.client.rowkey.func.Md5PrefixRowKeyFunc;

/**
 * @author leojie 2022/12/3 13:01
 */
public class Md5PrefixRowKey extends BaseRowKey<String>{
    private final int prefixLength;
    private final String prefixContactChar;

    public Md5PrefixRowKey(String value, int prefixLength, String prefixContactChar) {
        super(value, new Md5PrefixRowKeyFunc(prefixLength, prefixContactChar));
        this.prefixLength = prefixLength;
        this.prefixContactChar = prefixContactChar;
    }

    public Md5PrefixRowKey(String value, int prefixLength) {
        this(value, prefixLength, "|");
    }

    public Md5PrefixRowKey(String value) {
        this(value, 4, "|");
    }

    public int getPrefixLength() {
        return prefixLength;
    }

    public String getPrefixContactChar() {
        return prefixContactChar;
    }

    @Override
    public ColumnType columnType() {
        return ColumnType.StringType;
    }
}
