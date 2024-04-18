package com.hydraql.common.schema;

import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

import java.util.List;

/**
 * @author leojie 2022/11/20 11:07
 */
public class HBaseTableSchema {
    private String tableName;
    private MethodAccess methodAccess;
    private FieldAccess fieldAccess;
    private List<HBaseField> fieldStructList;

    public HBaseTableSchema() {
    }

    public MethodAccess getMethodAccess() {
        return methodAccess;
    }

    public void setMethodAccess(MethodAccess methodAccess) {
        this.methodAccess = methodAccess;
    }

    public FieldAccess getFieldAccess() {
        return fieldAccess;
    }

    public void setFieldAccess(FieldAccess fieldAccess) {
        this.fieldAccess = fieldAccess;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<HBaseField> getFieldStructList() {
        return fieldStructList;
    }

    public void setFieldStructList(List<HBaseField> fieldStructList) {
        this.fieldStructList = fieldStructList;
    }
}
