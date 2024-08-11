package com.hydraql.common.row;

import com.hydraql.common.util.StringUtil;

/**
 * @author leojie@apache.org 2024/8/10 23:38
 */
public class RowKeyReverseGenerator implements RowKeyGenerator {
    @Override
    public String apply(String originalRow) {
        if (StringUtil.isBlank(originalRow)) {
            return originalRow;
        }
        return StringUtil.reverse(originalRow);
    }
}
