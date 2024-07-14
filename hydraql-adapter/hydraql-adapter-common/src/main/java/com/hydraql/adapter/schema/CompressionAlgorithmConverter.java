package com.hydraql.adapter.schema;

import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.io.compress.Compression;

/**
 * @author leojie@apache.org 2024/7/13 23:48
 */
public final class CompressionAlgorithmConverter {
    private CompressionAlgorithmConverter() {}

    public static Compression.Algorithm apply(String compressionAlgorithm) {
        if (StringUtil.isBlank(compressionAlgorithm)) {
            return Compression.Algorithm.NONE;
        }
        for (Compression.Algorithm value : Compression.Algorithm.values()) {
            if (compressionAlgorithm.equalsIgnoreCase(value.getName())) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unsupported compression algorithm " + compressionAlgorithm);
    }
}
