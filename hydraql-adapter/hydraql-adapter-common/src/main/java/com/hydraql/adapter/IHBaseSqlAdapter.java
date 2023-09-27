package com.hydraql.adapter;

import com.hydraql.common.model.row.HBaseDataSet;
import com.hydraql.dsl.model.HBaseTableSchema;

import java.util.List;
import java.util.Map;

/**
 * @author leojie 2022/12/7 20:41
 */
public interface IHBaseSqlAdapter {

    String showCreateVirtualTable(String hql);
    List<String> showVirtualTables(String hql);

    void createVirtualTable(String hql);

    void dropVirtualTable(String hql);

    HBaseDataSet select(String hql);

    HBaseDataSet select(String hql, Map<String, Object> params);

    void insert(String hql);

    void delete(String hql);

    void registerTableSchema(HBaseTableSchema tableSchema);
    void printTableSchema(String tableName);
}
