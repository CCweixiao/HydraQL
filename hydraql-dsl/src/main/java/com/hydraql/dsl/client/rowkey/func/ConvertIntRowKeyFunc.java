package com.hydraql.dsl.client.rowkey.func;

import com.hydraql.common.type.ColumnType;
import com.hydraql.dsl.client.rowkey.BaseRowKey;
import com.hydraql.dsl.model.HBaseColumn;

/**
 * @author leojie 2022/12/3 13:32
 */
public class ConvertIntRowKeyFunc extends ConvertRowKeyFunc<Integer> {

    public ConvertIntRowKeyFunc() {
        super(ColumnType.IntegerType);
    }

    @Override
    public Integer evalFuncReturnRowValue(BaseRowKey<Integer> rowKey) {
        return Integer.parseInt(rowKey.columnType().getTypeHandler().extractMatchTtypeValue(rowKey.getOriValue()));
    }

    @Override
    public Integer evalFuncReturnRowValue(HBaseColumn row, String value) {
        return Integer.parseInt(row.getColumnType().getTypeHandler().extractMatchTtypeValue(value));
    }

}
