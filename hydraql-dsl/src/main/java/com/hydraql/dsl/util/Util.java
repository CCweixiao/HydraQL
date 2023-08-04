package com.hydraql.dsl.util;

import com.hydraql.dsl.client.rowkey.RowKey;

/**
 * @author leojie 2022/11/13 21:18
 */
public class Util {
    public static void checkRowKey(RowKey<?> rowKey) {
        if (rowKey == null || rowKey.toBytes() == null) {
            throw new NullPointerException("The row key must not be null.");
        }
    }
}
