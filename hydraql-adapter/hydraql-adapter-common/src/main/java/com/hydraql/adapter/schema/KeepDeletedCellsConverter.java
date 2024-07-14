package com.hydraql.adapter.schema;

import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.KeepDeletedCells;

/**
 * @author leojie@apache.org 2024/7/13 23:51
 */
public final class KeepDeletedCellsConverter {
    private KeepDeletedCellsConverter() {}

    public static KeepDeletedCells apply(String keepDeletedCells) {
        if (StringUtil.isBlank(keepDeletedCells)) {
            return KeepDeletedCells.FALSE;
        }
        for (KeepDeletedCells value : KeepDeletedCells.values()) {
            if (keepDeletedCells.equals(value.name())) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unsupported keepDeletedCells " + keepDeletedCells);
    }
}
