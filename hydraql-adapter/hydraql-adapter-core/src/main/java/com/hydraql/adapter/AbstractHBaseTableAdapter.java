/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hydraql.adapter;

import com.hydraql.adapter.context.HTableContext;
import com.hydraql.adapter.service.DeleteService;
import com.hydraql.adapter.service.GetService;
import com.hydraql.adapter.service.HTableUpsertService;
import com.hydraql.adapter.service.MutatorService;
import com.hydraql.adapter.service.ScanService;
import com.hydraql.adapter.service.UpsertService;
import com.hydraql.HTableService;
import com.hydraql.result.handler.RowMapper;
import com.hydraql.metadata.HTableInfo;
import com.hydraql.metadata.HTableInfoContainer;
import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.model.data.HBaseRowDataWithMultiVersions;
import com.hydraql.common.query.GetRowParam;
import com.hydraql.common.query.GetRowsParam;
import com.hydraql.common.query.ScanParams;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hydraql.adapter.HBaseClientConfigKeys.HBASE_CLIENT_SCANNER_CACHING;
import static com.hydraql.adapter.HBaseClientConfigKeys.HBASE_CLIENT_SCANNER_CACHING_DEFAULT;

/**
 * <p>
 * the abstract class of HBaseTemplate,
 * </p>
 * @author leo.jie (leojie1314@gmail.com)
 */
@Deprecated
abstract class AbstractHBaseTableAdapter extends HTableUpsertService implements HTableService,
    GetService, UpsertService, MutatorService, DeleteService, ScanService {

  public AbstractHBaseTableAdapter(Configuration configuration) {
    super(configuration);
  }

  @Override
  public void save(String tableName, String rowKey, Map<String, Object> data) {
    this.execSinglePut(tableName, buildPut(rowKey, data));
  }

  @Override
  public void saveWithBuffer(HTableContext tableContext, String rowKey, Map<String, Object> data) {
    this.execPutWithBuffer(tableContext, buildPut(rowKey, data));
  }

  @Override
  public <T> void save(T t) {
    final Class<?> clazz = t.getClass();
    HTableInfo tableInfo = HTableInfoContainer.getInstance().get(clazz);
    this.execSinglePut(tableInfo.getTableName(), new Put(buildPut(t)));
  }

  @Override
  public <T> void saveWithBuffer(HTableContext tableContext, T t) {
    this.execPutWithBuffer(tableContext, new Put(buildPut(t)));
  }

  @Override
  public void saveBatch(String tableName, Map<String, Map<String, Object>> data) {
    if (data == null || data.isEmpty()) {
      return;
    }
    List<Mutation> puts = new ArrayList<>(data.size());
    data.forEach((row, d) -> {
      if (d != null && !d.isEmpty()) {
        puts.add(buildPut(row, d));
      }
    });
    this.execBatchPuts(tableName, puts);
  }

  @Override
  public void saveBatchWithBuffer(HTableContext tableContext,
      Map<String, Map<String, Object>> data) {
    if (data == null || data.isEmpty()) {
      return;
    }
    List<Mutation> puts = new ArrayList<>(data.size());
    data.forEach((row, d) -> {
      if (d != null && !d.isEmpty()) {
        puts.add(buildPut(row, d));
      }
    });
    this.execBatchPuts(tableContext, puts);
  }

  @Override
  public <T> void saveBatch(List<T> list) {
    if (list == null || list.isEmpty()) {
      return;
    }
    final Class<?> clazz = list.get(0).getClass();
    HTableInfo tableInfo = HTableInfoContainer.getInstance().get(clazz);
    List<Mutation> putList = new ArrayList<>(list.size());
    for (T t : list) {
      putList.add(new Put(buildPut(t)));
    }
    this.execBatchPuts(tableInfo.getTableName(), putList);
  }

  @Override
  public <T> void saveBatchWithBuffer(HTableContext tableContext, List<T> list) {
    if (list == null || list.isEmpty()) {
      return;
    }

    List<Mutation> putList = new ArrayList<>(list.size());
    for (T t : list) {
      putList.add(new Put(buildPut(t)));
    }
    this.saveBatchWithBuffer(tableContext, putList);
  }

  @Override
  public <T> T getRow(GetRowParam getRowParam, Class<T> clazz) {
    Get get = this.buildGet(getRowParam);
    return this.get(get, clazz);
  }

  @Override
  public <T> T get(Get get, Class<T> clazz) {
    String tableName = HTableInfoContainer.getInstance().get(clazz).getTableName();
    return this.executeQuery(tableName, table -> {
      Result result = checkGetAndReturnResult(get, table);
      if (result == null) {
        return null;
      }
      return mapperRowToT(result, clazz);
    });
  }

  @Override
  public <T> T getRow(String tableName, GetRowParam getRowParam, RowMapper<T> rowMapper) {
    Get get = buildGet(getRowParam);
    return this.get(tableName, get, rowMapper);
  }

  @Override
  public <T> T get(String tableName, Get get, RowMapper<T> rowMapper) {
    return this.executeQuery(tableName, table -> {
      Result result = checkGetAndReturnResult(get, table);
      if (result == null) {
        return null;
      }
      return rowMapper.mapRow(result, 0);
    });
  }

  @Override
  public HBaseRowData getRow(String tableName, GetRowParam getRowParam) {
    Get get = buildGet(getRowParam);
    return this.get(tableName, get);
  }

  @Override
  public HBaseRowData get(String tableName, Get get) {
    return this.executeQuery(tableName, table -> {
      Result result = checkGetAndReturnResult(get, table);
      if (result == null) {
        return null;
      }
      return convertResultToHBaseColData(result);
    });
  }

  @Override
  public <T> List<T> getWithMultiVersions(Get get, int versions, Class<T> clazz) {
    String tableName = HTableInfoContainer.getInstance().get(clazz).getTableName();
    return this.executeQuery(tableName, table -> {
      Result result = checkGetAndReturnResult(get, table);
      if (result == null) {
        return new ArrayList<>();
      }
      return mapperRowToList(result, versions, clazz);
    });
  }

  @Override
  public <T> List<T> getWithMultiVersions(GetRowParam getRowParam, Class<T> clazz) {
    Get get = this.buildGet(getRowParam);
    int versions = getRowParam.getVersions();
    return this.getWithMultiVersions(get, versions, clazz);
  }

  @Override
  public <T> List<T> getWithMultiVersions(String tableName, Get get, int versions,
      RowMapper<T> rowMapper) {
    return this.executeQuery(tableName, table -> {
      Result result = checkGetAndReturnResult(get, table);
      if (result == null) {
        return new ArrayList<>();
      }
      return rowMapper.mapRowWithVersions(result, 0);
    });
  }

  @Override
  public <T> List<T> getWithMultiVersions(String tableName, GetRowParam getRowParam,
      RowMapper<T> rowMapper) {
    Get get = this.buildGet(getRowParam);
    int versions = getRowParam.getVersions();
    return this.getWithMultiVersions(tableName, get, versions, rowMapper);
  }

  @Override
  public HBaseRowDataWithMultiVersions getWithMultiVersions(String tableName,
      GetRowParam getRowParam) {
    Get get = buildGet(getRowParam);
    return this.getWithMultiVersions(tableName, get, getRowParam.getVersions());
  }

  @Override
  public HBaseRowDataWithMultiVersions getWithMultiVersions(String tableName, Get get,
      int versions) {
    return this.executeQuery(tableName, table -> {
      Result result = checkGetAndReturnResult(get, table);
      if (result == null) {
        return null;
      }
      return convertResultsToHBaseColDataListWithMultiVersion(result, versions);
    });
  }

  @Override
  public <T> List<T> getRows(GetRowsParam getRowsParam, Class<T> clazz) {
    String tableName = HTableInfoContainer.getInstance().get(clazz).getTableName();
    List<Get> gets = this.buildGets(getRowsParam);
    return this.gets(tableName, gets, clazz);
  }

  @Override
  public <T> List<T> gets(String tableName, List<Get> gets, Class<T> clazz) {
    return this.executeQuery(tableName, table -> {
      Result[] results = checkBatchGetAndReturnResult(gets, table);
      if (results == null) {
        return new ArrayList<>();
      }
      List<T> data = new ArrayList<>(results.length);
      for (Result result : results) {
        data.add(mapperRowToT(result, clazz));
      }
      return data;
    });
  }

  @Override
  public <T> List<T> getRows(String tableName, GetRowsParam getRowsParam, RowMapper<T> rowMapper) {
    List<Get> gets = this.buildGets(getRowsParam);
    return this.gets(tableName, gets, rowMapper);
  }

  @Override
  public <T> List<T> gets(String tableName, List<Get> gets, RowMapper<T> rowMapper) {
    return this.executeQuery(tableName, table -> {
      Result[] results = checkBatchGetAndReturnResult(gets, table);
      if (results == null) {
        return new ArrayList<>();
      }
      List<T> data = new ArrayList<>(results.length);
      for (Result result : results) {
        T t = rowMapper.mapRow(result, 0);
        if (t != null) {
          data.add(t);
        }
      }
      return data;
    });
  }

  @Override
  public List<HBaseRowData> getRows(String tableName, GetRowsParam getRowsParam) {
    List<Get> gets = this.buildGets(getRowsParam);
    return this.gets(tableName, gets);
  }

  @Override
  public List<HBaseRowData> gets(String tableName, List<Get> gets) {
    return this.gets(tableName, gets, new RowMapper<HBaseRowData>() {
      @Override
      public <R> HBaseRowData mapRow(R r, int rowNum) throws Exception {
        Result result = (Result) r;
        return convertResultToHBaseColData(result);
      }
    });
  }

  @Override
  public <T> List<T> scan(ScanParams scanParams, Class<T> clazz) {
    Scan scan = buildScan(scanParams);
    return this.scan(scan, clazz);
  }

  @Override
  public <T> List<T> scan(Scan scan, Class<T> clazz) {
    String tableName = HTableInfoContainer.getInstance().get(clazz).getTableName();
    return this.executeQuery(tableName, table -> {
      try (ResultScanner scanner = table.getScanner(scan)) {
        List<T> rs = new ArrayList<>();
        for (Result result : scanner) {
          rs.add(mapperRowToT(result, clazz));
        }
        return rs;
      }
    });
  }

  @Override
  public <T> List<T> scan(String tableName, ScanParams scanParams, RowMapper<T> rowMapper) {
    Scan scan = buildScan(scanParams);
    return this.scan(tableName, scan, rowMapper);
  }

  @Override
  public <T> List<T> scan(String tableName, Scan scan, RowMapper<T> rowMapper) {
    return this.executeQuery(tableName, table -> {
      try (ResultScanner scanner = table.getScanner(scan)) {
        List<T> rs = new ArrayList<>();
        int rowNum = 0;
        for (Result result : scanner) {
          rs.add(rowMapper.mapRow(result, rowNum++));
        }
        return rs;
      }
    });
  }

  @Override
  public List<HBaseRowData> scan(String tableName, ScanParams scanParams) {
    Scan scan = buildScan(scanParams);
    return this.scan(tableName, scan);
  }

  @Override
  public List<HBaseRowData> scan(String tableName, Scan scan) {
    List<HBaseRowData> rowDataList = new ArrayList<>();
    return this.executeQuery(tableName, table -> {
      try (ResultScanner scanner = table.getScanner(scan)) {
        for (Result result : scanner) {
          HBaseRowData data = convertResultToHBaseColData(result);
          if (data != null) {
            rowDataList.add(data);
          }
        }
        return rowDataList;
      }
    });
  }

  @Override
  public List<HBaseRowDataWithMultiVersions> scanWithMultiVersions(String tableName,
      ScanParams scanParams) {
    Scan scan = buildScan(scanParams);
    int versions = scanParams.getVersions();
    return this.scanWithMultiVersions(tableName, scan, versions);
  }

  @Override
  public List<HBaseRowDataWithMultiVersions> scanWithMultiVersions(String tableName, Scan scan,
      int versions) {
    List<HBaseRowDataWithMultiVersions> rowDataListWithMultiVersions = new ArrayList<>();
    return this.executeQuery(tableName, table -> {
      try (ResultScanner scanner = table.getScanner(scan)) {
        for (Result result : scanner) {
          rowDataListWithMultiVersions
              .add(convertResultsToHBaseColDataListWithMultiVersion(result, versions));
        }
        return rowDataListWithMultiVersions;
      }
    });
  }

  @Override
  public <T> void delete(T t) {
    if (t == null) {
      return;
    }
    final Class<?> clazz = t.getClass();
    HTableInfo tableSchema = HTableInfoContainer.getInstance().get(clazz);
    this.execSingleDelete(tableSchema.getTableName(), new Delete(buildDelete(t)));
  }

  @Override
  public void delete(String tableName, String rowKey) {
    this.delete(tableName, rowKey, null, new ArrayList<>());
  }

  @Override
  public void deleteWithBuffer(HTableContext tableContext, String rowKey) {
    this.execDeleteWithBuffer(tableContext, new Delete(Bytes.toBytes(rowKey)));
  }

  @Override
  public void delete(String tableName, String rowKey, String familyName) {
    this.delete(tableName, rowKey, familyName, new ArrayList<>());
  }

  @Override
  public void deleteWithBuffer(HTableContext tableContext, String rowKey, String familyName) {
    this.execDeleteWithBuffer(tableContext,
      buildDeleteCondition(rowKey, familyName, new ArrayList<>()));
  }

  @Override
  public void delete(String tableName, String rowKey, String familyName, List<String> qualifiers) {
    if (StringUtil.isBlank(tableName)) {
      throw new IllegalArgumentException("the table name is not empty.");
    }
    if (StringUtil.isBlank(rowKey)) {
      throw new IllegalArgumentException("the row key of the table will be deleted is not empty.");
    }
    Delete delete = buildDeleteCondition(rowKey, familyName, qualifiers);
    this.execSingleDelete(tableName, delete);
  }

  @Override
  public void deleteWithBuffer(HTableContext tableContext, String rowKey, String familyName,
      List<String> qualifiers) {
    Delete delete = buildDeleteCondition(rowKey, familyName, qualifiers);
    this.execDeleteWithBuffer(tableContext, delete);
  }

  @Override
  public void delete(String tableName, String rowKey, String familyName, String... qualifiers) {
    if (qualifiers == null || qualifiers.length == 0) {
      this.delete(tableName, rowKey, familyName);
    } else {
      this.delete(tableName, rowKey, familyName, Arrays.asList(qualifiers));
    }
  }

  @Override
  public void deleteWithBuffer(HTableContext tableContext, String rowKey, String familyName,
      String... qualifiers) {
    if (qualifiers == null || qualifiers.length == 0) {
      this.deleteWithBuffer(tableContext, rowKey, familyName);
    } else {
      this.deleteWithBuffer(tableContext, rowKey, familyName, Arrays.asList(qualifiers));
    }
  }

  @Override
  public <T> void deleteBatch(List<T> list) {
    if (list == null || list.isEmpty()) {
      return;
    }
    final Class<?> clazz0 = list.get(0).getClass();
    HTableInfo tableSchema = HTableInfoContainer.getInstance().get(clazz0);
    List<Mutation> deleteList = new ArrayList<>(list.size());
    for (T t : list) {
      deleteList.add(new Delete(buildDelete(t)));
    }
    this.execBatchDeletes(tableSchema.getTableName(), deleteList);
  }

  @Override
  public void deleteBatchWithBuffer(HTableContext tableContext, List<String> rowKeys) {
    if (rowKeys == null || rowKeys.isEmpty()) {
      return;
    }
    List<Mutation> deleteList = new ArrayList<>(rowKeys.size());
    for (String rowKey : rowKeys) {
      deleteList.add(new Delete(Bytes.toBytes(rowKey)));
    }
    this.execBatchDeletes(tableContext, deleteList);
  }

  @Override
  public void deleteBatch(String tableName, List<String> rowKeys) {
    this.deleteBatch(tableName, rowKeys, null, new ArrayList<>());
  }

  @Override
  public void deleteBatch(String tableName, List<String> rowKeys, String familyName) {
    this.deleteBatch(tableName, rowKeys, familyName, new ArrayList<>());
  }

  @Override
  public void deleteBatch(String tableName, List<String> rowKeys, String familyName,
      List<String> qualifiers) {
    if (StringUtil.isBlank(tableName)) {
      throw new IllegalArgumentException("the table name is not empty.");
    }
    if (rowKeys == null || rowKeys.isEmpty()) {
      throw new IllegalArgumentException("the row keys of the table will be deleted is not empty.");
    }
    List<Mutation> mutations =
        rowKeys.stream().map(rowKey -> buildDeleteCondition(rowKey, familyName, qualifiers))
            .collect(Collectors.toList());
    this.execBatchDeletes(tableName, mutations);
  }

  @Override
  public void deleteBatch(String tableName, List<String> rowKeys, String familyName,
      String... qualifiers) {
    if (qualifiers == null || qualifiers.length == 0) {
      this.deleteBatch(tableName, rowKeys, familyName);
    } else {
      this.deleteBatch(tableName, rowKeys, familyName, Arrays.asList(qualifiers));
    }
  }

  @Override
  public void deleteBatchWithBuffer(HTableContext tableContext, List<String> rowKeys,
      String familyName) {
    List<Mutation> mutations =
        rowKeys.stream().map(rowKey -> buildDeleteCondition(rowKey, familyName, new ArrayList<>()))
            .collect(Collectors.toList());
    this.execBatchDeletes(tableContext, mutations);
  }

  @Override
  public void deleteBatchWithBuffer(HTableContext tableContext, List<String> rowKeys,
      String familyName, String... qualifiers) {
    if (qualifiers == null || qualifiers.length == 0) {
      this.deleteBatchWithBuffer(tableContext, rowKeys, familyName);
    } else {
      this.deleteBatchWithBuffer(tableContext, rowKeys, familyName, Arrays.asList(qualifiers));
    }
  }

  @Override
  public void deleteBatchWithBuffer(HTableContext tableContext, List<String> rowKeys,
      String familyName, List<String> qualifiers) {
    if (rowKeys == null || rowKeys.isEmpty()) {
      throw new IllegalArgumentException("the row keys of the table will be deleted is not empty.");
    }
    List<Mutation> mutations =
        rowKeys.stream().map(rowKey -> buildDeleteCondition(rowKey, familyName, qualifiers))
            .collect(Collectors.toList());
    this.execBatchDeletes(tableContext, mutations);
  }

  protected int getClientScannerCachingFromConfig() {
    // todo 不同版本集群的配置可能不一样
    Configuration configuration = this.getConfiguration();
    if (configuration == null) {
      return HBASE_CLIENT_SCANNER_CACHING_DEFAULT;
    }
    return configuration.getInt(HBASE_CLIENT_SCANNER_CACHING, HBASE_CLIENT_SCANNER_CACHING_DEFAULT);
  }
}
