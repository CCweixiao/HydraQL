package com.hydraql.template;

import com.hydraql.adapter.HTableScanService;
import com.hydraql.common.IHBaseTableOpAdapter;
import com.hydraql.common.mapper.RowMapper;
import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.model.data.HBaseRowDataWithMultiVersions;
import org.apache.hadoop.hbase.client.Get;
import java.util.List;

/**
 * @author leojie 2023/7/20 22:12
 */
public abstract class BaseHBaseTableTemplate implements IHBaseTableOpAdapter, HTableScanService {
    abstract <T> T get(Get get, Class<T> clazz);
    abstract <T> T get(String tableName, Get get, RowMapper<T> rowMapper);
    abstract HBaseRowData get(String tableName, Get get);
    abstract <T> List<T> getWithMultiVersions(Get get, int versions, Class<T> clazz);
    abstract <T> List<T> getWithMultiVersions(String tableName, Get get, int versions, RowMapper<T> rowMapper);
    abstract HBaseRowDataWithMultiVersions getWithMultiVersions(String tableName, Get get, int versions);
    abstract <T> List<T> gets(String tableName, List<Get> gets, Class<T> clazz);
    abstract <T> List<T> gets(String tableName, List<Get> gets, RowMapper<T> rowMapper);
    abstract List<HBaseRowData> gets(String tableName, List<Get> gets);
}
