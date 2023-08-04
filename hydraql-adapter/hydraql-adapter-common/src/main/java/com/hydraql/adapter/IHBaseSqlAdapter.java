package com.hydraql.adapter;

import com.hydraql.common.model.HQLType;
import com.hydraql.common.model.row.HBaseDataSet;
import com.hydraql.dsl.model.HBaseTableSchema;

import java.util.Map;

/**
 * @author leojie 2022/12/7 20:41
 */
public interface IHBaseSqlAdapter {

    void dropVirtualTable(String hql);

    void createVirtualTable(String hql);

    HBaseDataSet select(String hql);

    HBaseDataSet select(String hql, Map<String, Object> params);

    void insert(String hql);

    void delete(String hql);

    String parseTableNameFromHql(String hql);

    HQLType parseHQLType(String hql);

    void registerTableSchema(HBaseTableSchema tableSchema);
    void printTableSchema(String tableName);
}