package com.hydraql.common.row;

/**
 * @author leojie@apache.org 2024/8/10 23:24
 */
public interface RowKeyGenerator {
    /**
     * The original row key gets a new row key after applying the generation strategy.
     *
     * @param originalRow original row key
     * @return new row key
     */
    String apply(String originalRow);
}
