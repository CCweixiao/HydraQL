package com.hydraql.common.model.row;

import com.hydraql.common.constants.HMHBaseConstants;
import com.hydraql.common.type.ColumnType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2022/12/5 23:04
 */
public class HBaseDataRow {
    private final String rowKeyFieldName;
    private final Object rowKeyVal;
    private List<HBaseDataColumn> columns;

    private HBaseDataRow(String rowKeyFieldName, Object rowKeyVal) {
        this.rowKeyFieldName = rowKeyFieldName;
        this.rowKeyVal = rowKeyVal;
    }

    public String getRowKeyFieldName() {
        return rowKeyFieldName;
    }

    public Object getRowKeyVal() {
        return rowKeyVal;
    }

    public List<HBaseDataColumn> getColumns() {
        return columns;
    }

    public HBaseDataRow appendColumn(String family, String qualifier, String alias, ColumnType columnType, Object value, long timestamp) {
        if (this.columns == null) {
            this.columns = new ArrayList<>();
        }
        this.columns.add(new HBaseDataColumn(family, qualifier, alias, columnType, value, timestamp));
        return this;
    }

    public HBaseDataRow appendColumn(String family, String qualifier, ColumnType columnType, Object value) {
        if (this.columns == null) {
            this.columns = new ArrayList<>();
        }
        String alias = HMHBaseConstants.getColumnName(family, qualifier);
        this.columns.add(new HBaseDataColumn(family, qualifier, alias, columnType, value, 0L));
        return this;
    }

    public HBaseDataRow appendColumn(String family, String qualifier, String alias, Object value) {
        if (this.columns == null) {
            this.columns = new ArrayList<>();
        }
        this.columns.add(new HBaseDataColumn(family, qualifier, alias, ColumnType.StringType, value, 0L));
        return this;
    }

    public HBaseDataRow appendColumn(String family, String qualifier, Object value) {
        String alias = HMHBaseConstants.getColumnName(family, qualifier);
        return appendColumn(family, qualifier, alias, value);
    }

    public static HBaseDataRow of(String rowKeyFieldName, Object rowKeyVal) {
        return new HBaseDataRow(rowKeyFieldName, rowKeyVal);
    }

    public static HBaseDataRow of(Object rowKey) {
        return new HBaseDataRow("row_key", rowKey);
    }
}
