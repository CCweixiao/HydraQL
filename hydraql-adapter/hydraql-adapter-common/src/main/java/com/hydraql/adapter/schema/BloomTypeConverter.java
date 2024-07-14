package com.hydraql.adapter.schema;

import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.regionserver.BloomType;

/**
 * @author leojie@apache.org 2024/7/13 23:50
 */
public final class BloomTypeConverter {
    private BloomTypeConverter() {}

    public static BloomType apply(String bloomType) {
        if (StringUtil.isBlank(bloomType)) {
            return BloomType.ROW;
        }
        for (BloomType value : BloomType.values()) {
            if (bloomType.equals(value.name())) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unsupported bloom type " + bloomType);
    }
}
