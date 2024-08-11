package com.hydraql.common.row;

import com.hydraql.common.util.DigestUtil;
import com.hydraql.common.util.StringUtil;

/**
 * @author leojie@apache.org 2024/8/10 23:40
 */
public class RowKeyHashingGenerator implements RowKeyGenerator {
    @Override
    public String apply(String originalRow) {
        if (StringUtil.isBlank(originalRow)) {
            return originalRow;
        }
        String md5Key = DigestUtil.md5Hex(originalRow);
        long hashCode = Math.abs(md5Key.hashCode());
        return String.valueOf(hashCode).substring(0, 4) + "|" + originalRow;
    }
}