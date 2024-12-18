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

package com.hydraql.adapter.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.hydraql.common.constants.HydraQLConstants;
import com.hydraql.result.handler.RowMapper;
import com.hydraql.common.model.data.HBaseColData;
import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.model.data.HBaseRowDataWithMultiVersions;
import com.hydraql.common.query.BaseGetRowParam;
import com.hydraql.common.query.GetRowParam;
import com.hydraql.common.query.GetRowsParam;
import com.hydraql.metadata.HFieldInfo;
import com.hydraql.metadata.HTableInfo;
import com.hydraql.metadata.HTableInfoContainer;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * 定义HBase的数据操作接口
 * @author leojie 2020/9/26 11:04 上午
 */
@Deprecated
public interface GetService {
  /**
   * 传入GetParams对象，生成Get的接口方法，由具体的HBaseTableAdapterImpl类去实现
   * @param getRowParam GetParams对象
   * @return Get
   */
  Get buildGet(GetRowParam getRowParam);

  List<Get> buildGets(GetRowsParam getRowsParam);

  <T> T get(Get get, Class<T> clazz);

  <T> T get(String tableName, Get get, RowMapper<T> rowMapper);

  HBaseRowData get(String tableName, Get get);

  <T> List<T> getWithMultiVersions(Get get, int versions, Class<T> clazz);

  <T> List<T> getWithMultiVersions(String tableName, Get get, int versions, RowMapper<T> rowMapper);

  HBaseRowDataWithMultiVersions getWithMultiVersions(String tableName, Get get, int versions);

  <T> List<T> gets(String tableName, List<Get> gets, Class<T> clazz);

  <T> List<T> gets(String tableName, List<Get> gets, RowMapper<T> rowMapper);

  List<HBaseRowData> gets(String tableName, List<Get> gets);

  /**
   * 利用反射，绑定查询结果集到定义的实体对象
   * @param result {@link org.apache.hadoop.hbase.client.Result}
   * @param clazz 数据实体类的class
   * @param <T> 泛型类型
   * @return 绑定数据实体对象后的查询结果
   * @throws Exception 异常抛出
   */
  default <T> T mapperRowToT(Result result, Class<T> clazz) throws Exception {
    // TODO 这里的反射调用构造函数是否可以再优化
    HTableInfo tableInfo = HTableInfoContainer.getInstance().get(clazz);
    T t = tableInfo.newInstance();
    HFieldInfo.RowKey rowKey = tableInfo.getRowKey();
    rowKey.setBytesValue(t, result.getRow());

    List<HFieldInfo.Qualifier> qualifiers = tableInfo.getQualifiers();
    qualifiers.forEach(field -> {
      byte[] valBytes = result.getValue(field.getFamily(), field.getQualifier());
      field.setBytesValue(t, valBytes);
    });
    return t;
  }

  default <T> List<T> mapperRowToList(Result result, int versions, Class<T> clazz)
      throws Exception {
    if (versions <= 0) {
      throw new IllegalArgumentException("The versions must be a positive number.");
    }
    if (versions == Integer.MAX_VALUE) {
      throw new IllegalArgumentException("You must specify an exact number of versions.");
    }
    HTableInfo tableInfo = HTableInfoContainer.getInstance().get(clazz);
    HFieldInfo.RowKey rowKey = tableInfo.getRowKey();
    List<HFieldInfo.Qualifier> qualifiers = tableInfo.getQualifiers();

    List<T> rowDataList = new ArrayList<>(versions);
    for (int i = 0; i < versions; i++) {
      // todo 优化构造器获取
      T t = clazz.getDeclaredConstructor().newInstance();
      rowKey.setBytesValue(t, result.getRow());
      rowDataList.add(t);
    }

    for (HFieldInfo.Qualifier qualifier : qualifiers) {
      List<Cell> cells = result.getColumnCells(qualifier.getFamily(), qualifier.getQualifier());
      if (cells.isEmpty()) {
        continue;
      }
      for (int i = 0; i < cells.size(); i++) {
        byte[] value = CellUtil.cloneValue(cells.get(i));
        qualifier.setBytesValue(rowDataList.get(i), value);
        if (i >= (versions - 1)) {
          break;
        }
      }
    }
    return rowDataList;
  }

  default HBaseRowDataWithMultiVersions
      convertResultsToHBaseColDataListWithMultiVersion(Result result, int versions) {
    List<Cell> cells = result.listCells();
    if (cells == null || cells.isEmpty()) {
      return null;
    }

    HBaseRowDataWithMultiVersions.Builder colDataBuilder =
        HBaseRowDataWithMultiVersions.of(Bytes.toString(result.getRow()));
    List<HBaseColData> colDataList = new ArrayList<>(versions);
    StringBuilder preFieldSb = new StringBuilder();
    StringBuilder currentFieldSb = new StringBuilder();
    for (int i = 0; i < cells.size(); i++) {
      preFieldSb.delete(0, preFieldSb.length());
      currentFieldSb.delete(0, currentFieldSb.length());

      if (i > 0) {
        preFieldSb.append(Bytes.toString(CellUtil.cloneFamily(cells.get(i - 1))));
        preFieldSb.append(HydraQLConstants.FAMILY_QUALIFIER_SEPARATOR);
        preFieldSb.append(Bytes.toString(CellUtil.cloneQualifier(cells.get(i - 1))));
      }
      currentFieldSb.append(Bytes.toString(CellUtil.cloneFamily(cells.get(i))));
      currentFieldSb.append(HydraQLConstants.FAMILY_QUALIFIER_SEPARATOR);
      currentFieldSb.append(Bytes.toString(CellUtil.cloneQualifier(cells.get(i))));
      String value = Bytes.toString(cells.get(i).getValueArray(), cells.get(i).getValueOffset(),
        cells.get(i).getValueLength());
      HBaseColData colData = new HBaseColData(value, cells.get(i).getTimestamp());
      boolean fieldChange = StringUtil.isNotBlank(preFieldSb.toString())
          && !currentFieldSb.toString().contentEquals(preFieldSb);
      if (fieldChange) {
        colDataBuilder = colDataBuilder.appendColData(preFieldSb.toString(), colDataList);
        colDataList = new ArrayList<>(versions);
      }
      colDataList.add(colData);

      if (i == cells.size() - 1) {
        colDataBuilder = colDataBuilder.appendColData(currentFieldSb.toString(), colDataList);
      }
    }

    return colDataBuilder.build();
  }

  default HBaseRowData convertResultToHBaseColData(Result result) {
    List<Cell> cells = result.listCells();
    if (cells == null || cells.isEmpty()) {
      return null;
    }
    HBaseRowData.Builder builder = HBaseRowData.of(Bytes.toString(result.getRow()));
    StringBuilder colNameSb = new StringBuilder();
    for (Cell cell : cells) {
      colNameSb.delete(0, colNameSb.length());
      colNameSb.append(Bytes.toString(CellUtil.cloneFamily(cell)));
      colNameSb.append(HydraQLConstants.FAMILY_QUALIFIER_SEPARATOR);
      colNameSb.append(Bytes.toString(CellUtil.cloneQualifier(cell)));
      String value =
          Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
      builder = builder.appendColData(colNameSb.toString(), value, cell.getTimestamp());
    }
    return builder.build();
  }

  default Result checkGetAndReturnResult(Get get, Table table) throws IOException {
    if (get == null) {
      return null;
    }
    Result result = table.get(get);
    if (result == null || result.getRow() == null) {
      return null;
    }
    return result;
  }

  default Result[] checkBatchGetAndReturnResult(List<Get> gets, Table table) throws IOException {
    if (gets == null || gets.isEmpty()) {
      return null;
    }
    Result[] results = table.get(gets);
    if (results == null || results.length == 0) {
      return null;
    }
    return results;
  }

  default List<Get> buildBatchGetCondition(List<String> rowKeys, String familyName,
      List<String> qualifiers) {
    if (null == rowKeys || rowKeys.isEmpty()) {
      return new ArrayList<>();
    }
    return rowKeys.stream().distinct()
        .map(rowKey -> buildGetCondition(rowKey, familyName, qualifiers, 1))
        .filter(Objects::nonNull).collect(Collectors.toList());
  }

  default Get buildGetCondition(String rowKey, String familyName, List<String> qualifiers) {
    return this.buildGetCondition(rowKey, familyName, qualifiers, 1, 0, 0, 0);
  }

  default Get buildGetCondition(String rowKey, String familyName, List<String> qualifiers,
      int versions) {
    return this.buildGetCondition(rowKey, familyName, qualifiers, versions, 0, 0, 0);
  }

  default Get buildGetCondition(String rowKey, String familyName, List<String> qualifiers,
      long ts) {
    return this.buildGetCondition(rowKey, familyName, qualifiers, 1, ts, 0, 0);
  }

  default Get buildGetCondition(String rowKey, String familyName, List<String> qualifiers,
      int versions, long ts, long minTs, long maxTs) {
    BaseGetRowParam.Builder<GetRowParam> builder = GetRowParam.of(rowKey);
    if (ts > 0) {
      builder = builder.withTimestamp(ts);
    }
    if (minTs > 0 && maxTs > 0) {
      builder = builder.withTimeRange(minTs, maxTs);
    }
    if (versions <= 0) {
      versions = 1;
    }
    GetRowParam getRowParam =
        builder.family(familyName).qualifiers(qualifiers).versions(versions).build();
    return this.buildGet(getRowParam);
  }
}
