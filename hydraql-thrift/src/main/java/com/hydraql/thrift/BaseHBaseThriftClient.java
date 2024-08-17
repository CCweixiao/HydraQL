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

package com.hydraql.thrift;

import com.hydraql.core.callback.TableCallback;
import com.hydraql.common.constants.HBaseConstants;
import com.hydraql.core.exceptions.HBaseMetaDataException;
import com.hydraql.core.exceptions.HBaseThriftException;
import com.hydraql.core.toolkit.Assert;
import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.query.GetRowParam;
import com.hydraql.common.query.GetRowsParam;
import com.hydraql.common.query.ScanParams;
import com.hydraql.core.metadata.HBaseFieldInfo;
import com.hydraql.core.metadata.HBaseTableInfo;
import com.hydraql.core.metadata.HBaseTableInfoHelper;
import com.hydraql.core.type.ColumnType;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.thrift.generated.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author leojie 2022/11/24 22:18
 */
public abstract class BaseHBaseThriftClient extends HBaseThriftConnection {
  protected Hbase.Client hbaseClient;

  public BaseHBaseThriftClient(final IHBaseThriftTSocket hbaseThriftTSocket) {
    super(hbaseThriftTSocket);
  }

  @Override
  public void connect() {
    super.connect();
    TSocket socket = getSocket();
    TProtocol protocol = new TBinaryProtocol(socket, true, true);
    hbaseClient = new Hbase.Client(protocol);
  }

  abstract boolean ping();

  protected <T> T execute(TableCallback<T, Hbase.Client> action) {
    try {
      return action.doInTable(this.hbaseClient);
    } catch (Throwable throwable) {
      throw new HBaseThriftException(throwable);
    }
  }

  protected void save(String tableName, Object rowKey, List<Mutation> mutations) {
    this.execute(thriftClient -> {
      thriftClient.mutateRow(ColumnType.toByteBufferFromStr(tableName),
        ColumnType.toByteBuffer(rowKey), mutations, getAttributesMap(new HashMap<>(0)));
      return null;
    });
  }

  protected void saveBatch(String tableName, List<BatchMutation> batchMutations) {
    this.execute(thriftClient -> {
      thriftClient.mutateRows(ColumnType.toByteBufferFromStr(tableName), batchMutations,
        getAttributesMap(new HashMap<>(0)));
      return batchMutations.size();
    });
  }

  protected void delete() {

  }

  protected <T> List<Mutation> createMutationList(T t, HBaseTableInfo tableMeta)
      throws HBaseMetaDataException {
    if (t == null) {
      return new ArrayList<>(0);
    }
    List<HBaseFieldInfo> fields = tableMeta.getFields();
    if (fields == null || fields.isEmpty()) {
      return new ArrayList<>(0);
    }
    List<Mutation> mutations = new ArrayList<>(fields.size());
    fields.forEach(field -> {
      if (!field.isRowKey()) {
        ByteBuffer fieldValue = field.toByteBuffer(t);
        mutations.add(new Mutation(false,
            ColumnType.toByteBufferFromStr(field.getFamilyAndQualifier()), fieldValue, true));
      }
    });
    return mutations;
  }

  protected <T> BatchMutation createBatchMutation(T t, HBaseTableInfo tableMeta) {
    Object rowKeyVal = createRowKeyVal(tableMeta, t);
    List<Mutation> mutations = createMutationList(t, tableMeta);
    return new BatchMutation(ColumnType.toByteBuffer(rowKeyVal), mutations);
  }

  protected <T> List<BatchMutation> createBatchMutationList(List<T> lst, HBaseTableInfo tableMeta)
      throws HBaseMetaDataException {
    if (lst == null || lst.isEmpty()) {
      return new ArrayList<>(0);
    }
    List<BatchMutation> batchMutations = new ArrayList<>(lst.size());
    for (T t : lst) {
      batchMutations.add(createBatchMutation(t, tableMeta));
    }
    return batchMutations;
  }

  protected <T> T pSave(T t) {
    if (t == null) {
      throw new NullPointerException("The data model class object to be saved cannot be null.");
    }
    Class<?> clazz = t.getClass();
    HBaseTableInfo tableMeta = HBaseTableInfoHelper.getTableInfo(clazz);
    Object rowKeyVal = createRowKeyVal(tableMeta, t);
    List<Mutation> mutations = createMutationList(t, tableMeta);
    this.save(tableMeta.getTableName(), rowKeyVal, mutations);
    return t;
  }

  protected List<TRowResult> getToRowResultList(Hbase.Client thriftClient, String tableName,
      GetRowParam getRowParam) {
    return getToRowResultList(thriftClient, tableName, getRowParam.getRowKey(),
      getRowParam.getFamily(), getRowParam.getQualifiers());
  }

  protected List<TRowResult> getToRowResultList(Hbase.Client thriftClient, String tableName,
      GetRowsParam getRowsParam) {
    return getToRowResultList(thriftClient, tableName, getRowsParam.getRowKeyList().get(0),
      getRowsParam.getFamily(), getRowsParam.getQualifiers());
  }

  protected List<TRowResult> getToRowResultList(Hbase.Client thriftClient, String tableName,
      String rowKey, String family, List<String> qualifiers) {
    Assert.checkArgument(StringUtil.isNotBlank(tableName), "The table name must not be null.");
    Assert.checkArgument(StringUtil.isNotBlank(rowKey), "The value of row key must not be null.");
    ByteBuffer rowByteBuffer = ColumnType.toByteBufferFromStr(rowKey);
    List<ByteBuffer> familyQualifiers = createFamilyQualifiesBuffer(family, qualifiers);
    List<TRowResult> results;
    try {
      if (familyQualifiers != null && !familyQualifiers.isEmpty()) {
        results = thriftClient.getRowWithColumns(ColumnType.toByteBufferFromStr(tableName),
          rowByteBuffer, familyQualifiers, getAttributesMap(new HashMap<>(0)));
      } else {
        results = thriftClient.getRow(ColumnType.toByteBufferFromStr(tableName), rowByteBuffer,
          getAttributesMap(new HashMap<>(0)));
      }
    } catch (TException e) {
      throw new HBaseThriftException(e);
    }
    return results;
  }

  protected List<TRowResult> getToRowsResultList(Hbase.Client thriftClient, String tableName,
      GetRowsParam getRowsParam) {
    Assert.checkArgument(StringUtil.isNotBlank(tableName), "The table name must not be null.");
    Assert.checkArgument((getRowsParam != null && getRowsParam.getRowKeyList() != null
        && !getRowsParam.getRowKeyList().isEmpty()),
      "The row key(s) must not be empty.");
    if (getRowsParam.getRowKeyList().size() == 1) {
      return getToRowResultList(thriftClient, tableName, getRowsParam);
    }

    List<ByteBuffer> rowByteBuffers = getRowsParam.getRowKeyList().stream().map(row -> {
      Assert.checkArgument(StringUtil.isNotBlank(row), "The row key must not be empty.");
      return ColumnType.toByteBufferFromStr(row);
    }).collect(Collectors.toList());
    List<ByteBuffer> familyQualifiers =
        createFamilyQualifiesBuffer(getRowsParam.getFamily(), getRowsParam.getQualifiers());

    List<TRowResult> results;
    try {
      if (familyQualifiers != null && !familyQualifiers.isEmpty()) {
        results = thriftClient.getRowsWithColumns(ColumnType.toByteBufferFromStr(tableName),
          rowByteBuffers, familyQualifiers, getAttributesMap(new HashMap<>(0)));
      } else {
        results = thriftClient.getRows(ColumnType.toByteBufferFromStr(tableName), rowByteBuffers,
          getAttributesMap(new HashMap<>(0)));
      }
    } catch (TException e) {
      throw new HBaseThriftException(e);
    }
    return results;
  }

  protected <T> T mapperRowToT(TRowResult result, Class<T> clazz) throws Exception {
    // TODO 这里的反射调用构造函数是否可以再优化
    T t = clazz.getDeclaredConstructor().newInstance();
    if (result == null) {
      return t;
    }
    Map<String, TCell> tmpDataMap = new HashMap<>(result.getColumnsSize());
    result.getColumns()
        .forEach((keyBuffer, cell) -> tmpDataMap.put(Bytes.toString(keyBuffer.array()), cell));
    if (tmpDataMap.isEmpty()) {
      return t;
    }
    HBaseTableInfo hBaseTableMeta = HBaseTableInfoHelper.getTableInfo(clazz);
    List<HBaseFieldInfo> fields = hBaseTableMeta.getFields();

    fields.forEach(field -> {
      if (field.isRowKey()) {
        Object rowVal = ColumnType.toObject(field.getType(), result.getRow());
        field.setValue(t, rowVal);
      } else {
        TCell tCell = tmpDataMap.get(field.getFamilyAndQualifier());
        if (tCell != null) {
          Object fieldValue = ColumnType.toObject(field.getType(), tCell.getValue());
          field.setValue(t, fieldValue);
        }
      }
    });
    return t;
  }

  protected <T> List<T> mapperRowToTList(List<TRowResult> results, Class<T> clazz) {
    if (results == null || results.isEmpty()) {
      return new ArrayList<>(0);
    }
    List<T> list = new ArrayList<>(results.size());
    try {
      for (TRowResult result : results) {
        list.add(mapperRowToT(result, clazz));
      }
    } catch (Exception e) {
      throw new HBaseThriftException(e);
    }
    return list;
  }

  protected HBaseRowData convertResultToHBaseColData(TRowResult result) {
    if (result == null) {
      return HBaseRowData.empty();
    }
    Map<ByteBuffer, TCell> resultColumns = result.getColumns();
    if (resultColumns == null || resultColumns.isEmpty()) {
      return HBaseRowData.empty();
    }
    HBaseRowData.Builder builder = HBaseRowData.of(Bytes.toString(result.getRow()));
    for (Map.Entry<ByteBuffer, TCell> entry : result.getColumns().entrySet()) {
      String colName = ColumnType.toString(entry.getKey().array());
      String value = ColumnType.toString(entry.getValue().getValue());
      builder = builder.appendColData(colName, value, entry.getValue().getTimestamp());
    }
    return builder.build();
  }

  protected List<HBaseRowData> convertResultsToHBaseColDataList(List<TRowResult> results) {
    if (results == null || results.isEmpty()) {
      return new ArrayList<>(0);
    }
    List<HBaseRowData> rowDataList = new ArrayList<>(results.size());
    results.forEach(result -> rowDataList.add(convertResultToHBaseColData(result)));
    return rowDataList;
  }

  protected TScan buildScan(ScanParams scanQueryParams) {
    TScan scan = new TScan();
    if (StringUtil.isNotBlank(scanQueryParams.getStartRow())) {
      scan.setStartRow(ColumnType.toByteBufferFromStr(scanQueryParams.getStartRow()));
    }
    if (StringUtil.isNotBlank(scanQueryParams.getStopRow())) {
      scan.setStopRow(ColumnType.toByteBufferFromStr(scanQueryParams.getStopRow()));
    }
    if (StringUtil.isNotBlank(scanQueryParams.getFamilyName())) {
      if (scanQueryParams.getColumnNames() != null && !scanQueryParams.getColumnNames().isEmpty()) {
        final List<ByteBuffer> columns =
            scanQueryParams.getColumnNames().stream().filter(StringUtil::isNotBlank)
                .map(qualifier -> ColumnType
                    .toByteBufferFromStr(scanQueryParams.getFamilyName() + ":" + qualifier))
                .collect(Collectors.toList());
        scan.setColumns(columns);
      } else {
        scan.setColumns(Collections
            .singletonList(ColumnType.toByteBufferFromStr(scanQueryParams.getFamilyName())));
      }
    }

    if (scanQueryParams.getFilter() != null
        && scanQueryParams.getFilter().customFilter() instanceof String) {
      scan.setFilterString(ColumnType.toStrByteBuffer(scanQueryParams.getFilter().customFilter()));
    }

    if (scanQueryParams.getTimestamp() > 0) {
      scan.setTimestamp(scanQueryParams.getTimestamp());
    }

    if (scanQueryParams.getCaching() > 0) {
      scan.setCaching(scanQueryParams.getCaching());
    }

    if (scanQueryParams.getBatch() > 0) {
      scan.setBatchSize(scanQueryParams.getBatch());
    }

    if (scanQueryParams.isReversed()) {
      scan.setReversed(true);
    }
    return scan;
  }

  protected Map<ByteBuffer, ByteBuffer> getAttributesMap(Map<String, String> attributes) {
    if (attributes != null && !attributes.isEmpty()) {
      Map<ByteBuffer, ByteBuffer> attributesMap = new HashMap<>(attributes.size());
      attributes.forEach((key, value) -> attributesMap.put(ColumnType.toByteBuffer(key),
        ColumnType.toByteBuffer(value)));
      return attributesMap;
    } else {
      return new HashMap<>(0);
    }
  }

  private <T> Object createRowKeyVal(HBaseTableInfo tableMeta, T t) {
    List<HBaseFieldInfo> fields = tableMeta.getFields();
    HBaseFieldInfo rowField = fields.get(0);
    Assert.checkArgument(rowField.isRowKey(),
      "The first field is not row key, please check hbase table mata data.");
    return rowField.getValue(t);
  }

  private List<ByteBuffer> createFamilyQualifiesBuffer(String familyName, List<String> qualifiers) {
    List<ByteBuffer> familyQualifiers = null;
    if (StringUtil.isNotBlank(familyName)) {
      if (qualifiers != null && !qualifiers.isEmpty()) {
        familyQualifiers = qualifiers.stream()
            .map(q -> ColumnType
                .toByteBufferFromStr(familyName + HBaseConstants.FAMILY_QUALIFIER_SEPARATOR + q))
            .collect(Collectors.toList());
      } else {
        familyQualifiers = Collections.singletonList(ColumnType.toByteBufferFromStr(familyName));
      }
    }
    return familyQualifiers;
  }

  protected void checkFamilyAndQualifierName(String colName) {
    Assert.checkArgument(StringUtil.isNotBlank(colName), "The col name is not empty.");
    Assert.checkArgument(colName.split(HBaseConstants.FAMILY_QUALIFIER_SEPARATOR).length == 2,
      "The col name must be in the format 'family:qualifier'.");
  }

}
