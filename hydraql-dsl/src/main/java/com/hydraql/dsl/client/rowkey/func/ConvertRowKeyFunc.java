package com.hydraql.dsl.client.rowkey.func;

import com.hydraql.common.type.ColumnType;

/**
 * @author leojie 2022/12/3 13:08
 */
public abstract class ConvertRowKeyFunc<T> implements RowKeyFunc<T> {
    protected final ColumnType targetColumnType;

    public ConvertRowKeyFunc(ColumnType targetColumnType) {
        this.targetColumnType = targetColumnType;
    }

    @Override
    public String showFuncName() {
        return "convert_to_" + this.targetColumnType.getTypeName();
    }

    @Override
    public String showDesc() {
        return "Convert string row value to " + this.targetColumnType.getTypeName() + " .";
    }
}
