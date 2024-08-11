package com.hydraql.common.row;

/**
 * @author leojie@apache.org 2024/8/11 20:14
 */
public class RowKeyNothingGenerator implements RowKeyGenerator {
    @Override
    public String apply(String originalRow) {
        return originalRow;
    }
}
