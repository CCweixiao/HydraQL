package com.hydraql.adapter.schema;

import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;

/**
 * @author leojie@apache.org 2024/7/13 23:53
 */
public final class DataBlockEncodingConverter {
    private DataBlockEncodingConverter() {
    }

    public static DataBlockEncoding apply(String dataBlockEncoding) {
        if (StringUtil.isBlank(dataBlockEncoding)) {
            return DataBlockEncoding.NONE;
        }
        for (DataBlockEncoding value : DataBlockEncoding.values()) {
            if (dataBlockEncoding.equals(value.name())) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unsupported dataBlockEncoding " + dataBlockEncoding);
    }
}
