package com.hydraql.adapter.service;

import com.hydraql.common.callback.RowMapper;
import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.model.data.HBaseRowDataWithMultiVersions;
import com.hydraql.common.query.ScanParams;
import org.apache.hadoop.hbase.client.Scan;

import java.util.List;

/**
 * @author leojie 2023/7/20 19:36
 */
public interface ScanService {
    Scan buildScan(ScanParams scanParams);
    <T> List<T> scan(Scan scan, Class<T> clazz);

    <T> List<T> scan(String tableName, Scan scan, RowMapper<T> rowMapper);

    List<HBaseRowData> scan(String tableName, Scan scan);

    List<HBaseRowDataWithMultiVersions> scanWithMultiVersions(String tableName, Scan scan, int versions);
}
