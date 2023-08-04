package com.hydraql.dsl.client.rowkey.func;

import com.hydraql.common.type.ColumnType;
import com.hydraql.dsl.client.rowkey.BaseRowKey;
import com.hydraql.dsl.model.HBaseColumn;

/**
 * @author leojie 2022/12/3 13:40
 */
public class ConvertLongRowKeyFunc extends ConvertRowKeyFunc<Long> {
    public ConvertLongRowKeyFunc() {
        super(ColumnType.LongType);
    }

    @Override
    public Long evalFuncReturnRowValue(BaseRowKey<Long> rowKey) {
        return Long.parseLong(rowKey.columnType().getTypeHandler().extractMatchTtypeValue(rowKey.getOriValue()));
    }

    @Override
    public Long evalFuncReturnRowValue(HBaseColumn row, String value) {
        return Long.parseLong(row.getColumnType().getTypeHandler().extractMatchTtypeValue(value));
    }
}
