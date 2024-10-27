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
import com.hydraql.common.constants.HydraQLConstants;
import com.hydraql.exceptions.HBaseOperationsException;
import com.hydraql.exceptions.HBaseSqlAnalysisException;
import com.hydraql.common.util.StringUtil;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.adapter.dsl.antlr.data.InsertColData;
import com.hydraql.adapter.dsl.antlr.data.InsertRowData;
import com.hydraql.adapter.dsl.antlr.data.RowKeyRange;
import com.hydraql.dsl.model.QueryHBaseColumn;
import com.hydraql.adapter.hql.filter.QueryFilterVisitor;
import com.hydraql.dsl.client.QueryExtInfo;
import com.hydraql.dsl.client.rowkey.RowKey;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import com.hydraql.dsl.util.Util;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.*;

/**
 * @author leojie 2020/11/28 8:36 下午
 */

public class HBaseSqlAdapter extends AbstractHBaseSqlAdapter {
  public HBaseSqlAdapter(Configuration configuration) {
    super(configuration);
  }

  @Override
  public void checkAndCreateHqlMetaTable() {
    HBaseAdminAdapter adminAdapter = new HBaseAdminAdapter(this.getConfiguration());
    if (adminAdapter.tableExists(HQL_META_DATA_TABLE_NAME)) {
      return;
    }
    TableDescriptorBuilder tableDescriptorBuilder =
        TableDescriptorBuilder.newBuilder(HQL_META_DATA_TABLE_NAME);
    ColumnFamilyDescriptor columnFamilyDescriptor =
        ColumnFamilyDescriptorBuilder.of(HQL_META_DATA_TABLE_FAMILY);
    tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
    adminAdapter.execute(admin -> tableDescriptorBuilder.build());
  }

  @Override
  public boolean saveTableSchemaMeta(HBaseTableSchema tableSchema, String hql,
      boolean ifNotExists) {
    HBaseAdminAdapter adminAdapter = new HBaseAdminAdapter(this.getConfiguration());
    String tableName = HydraQLConstants.getFullTableName(tableSchema.getTableName());
    if (!adminAdapter.tableExists(tableName)) {
      throw new HBaseSqlAnalysisException(String.format("The virtual table %s was created failed, "
          + "because the original table %s does not exist.",
        tableName, tableName));
    }

    String tableSchemaJson = tableSchema.toJson();
    Get get = new Get(Bytes.toBytes(tableName));
    String res = this.executeQuery(HQL_META_DATA_TABLE_NAME.getNameAsString(), table -> {
      Result result = table.get(get);
      if (result == null) {
        return "";
      }
      return Bytes.toString(result.getRow());
    });
    if (StringUtil.isNotBlank(res) && !ifNotExists) {
      throw new HBaseSqlAnalysisException(
          String.format("The schema of table %s has been created.", tableName));
    }
    Put put = new Put(Bytes.toBytes(tableName));
    put.addColumn(HQL_META_DATA_TABLE_FAMILY, HQL_META_DATA_TABLE_QUALIFIER,
      Bytes.toBytes(tableSchemaJson));
    put.addColumn(HQL_META_DATA_TABLE_FAMILY, HQL_META_DATA_CREATE_HQL_QUALIFIER,
      Bytes.toBytes(hql));
    this.execSinglePut(HQL_META_DATA_TABLE_NAME.getNameAsString(), put);
    return true;
  }

  @Override
  public Filter parseFilter(HydraQLParser.WhereColContext whereColContext,
      HBaseTableSchema tableSchema) {
    QueryFilterVisitor filterVisitor = new QueryFilterVisitor(tableSchema, new HashMap<>(0));
    return filterVisitor.extractFilter(whereColContext);
  }

  @Override
  public Filter parseFilter(HydraQLParser.WhereColContext whereColContext,
      Map<String, Object> queryParams, HBaseTableSchema tableSchema) {
    QueryFilterVisitor filterVisitor = new QueryFilterVisitor(tableSchema, queryParams);
    return filterVisitor.extractFilter(whereColContext);
  }

  @Override
  public Get constructGet(RowKey<?> rowKey, QueryExtInfo queryExtInfo, Filter filter,
      List<QueryHBaseColumn> columnList) {
    Util.checkRowKey(rowKey);
    Get get = new Get(rowKey.toBytes());
    if (queryExtInfo != null) {
      if (queryExtInfo.isMaxVersionSet()) {
        try {
          get.readVersions(queryExtInfo.getMaxVersions());
        } catch (IOException e) {
          throw new HBaseOperationsException("should never happen.", e);
        }
      }
      if (queryExtInfo.isTimeRangeSet()) {
        try {
          get.setTimeRange(queryExtInfo.getMinStamp(), queryExtInfo.getMaxStamp());
        } catch (IOException e) {
          throw new HBaseOperationsException("should never happen.", e);
        }
      }
    }
    if (filter != null) {
      get.setFilter(filter);
    }
    if (columnList != null && !columnList.isEmpty()) {
      for (QueryHBaseColumn queryHBaseColumn : columnList) {
        HBaseColumn column = queryHBaseColumn.getColumn();
        if (column.columnIsRow()) {
          continue;
        }
        get.addColumn(column.getFamilyNameBytes(), column.getColumnNameBytes());
      }
    }
    return get;
  }

  @Override
  public Scan constructScan(String tableName, RowKeyRange rowKeyRange, QueryExtInfo queryExtInfo,
      Filter filter, List<QueryHBaseColumn> columnList) {
    Scan scan = new Scan();
    RowKey<?> startRowKey = rowKeyRange.getStart();
    if (startRowKey != null && startRowKey.toBytes() != null) {
      scan.withStartRow(startRowKey.toBytes(), rowKeyRange.isIncludeStart());
    }
    RowKey<?> endRowKey = rowKeyRange.getStop();
    if (endRowKey != null && endRowKey.toBytes() != null) {
      scan.withStopRow(endRowKey.toBytes(), rowKeyRange.isIncludeStop());
    }
    if (queryExtInfo != null) {
      if (queryExtInfo.isMaxVersionSet()) {
        scan.readVersions(queryExtInfo.getMaxVersions());
      }
      if (queryExtInfo.isTimeRangeSet()) {
        try {
          scan.setTimeRange(queryExtInfo.getMinStamp(), queryExtInfo.getMaxStamp());
        } catch (IOException e) {
          throw new IllegalArgumentException("Shouldn't happen.", e);
        }
      }
      if (queryExtInfo.isLimitSet()) {
        scan.setLimit(queryExtInfo.getLimit());
      }
    }
    scan.setCaching(getScanCaching(tableName));
    scan.setCacheBlocks(scanCacheBlocks(tableName));
    if (filter != null) {
      scan.setFilter(filter);
    } else {
      scan.setBatch(getScanBatch(tableName));
    }
    if (columnList != null && !columnList.isEmpty()) {
      for (QueryHBaseColumn queryHBaseColumn : columnList) {
        HBaseColumn column = queryHBaseColumn.getColumn();
        if (column.columnIsRow()) {
          continue;
        }
        scan.addColumn(column.getFamilyNameBytes(), column.getColumnNameBytes());
      }
    }
    return scan;
  }

  @Override
  public Scan setScanRowPrefixFilter(Scan scan, RowKey<?> rowPrefixKey) {
    scan.setStartStopRowForPrefixScan(rowPrefixKey.toBytes());
    return scan;
  }

  @Override
  public Put constructPut(InsertRowData rowData, long ts) {
    Put put = new Put(rowData.getRows());
    for (InsertColData colData : rowData.getColDataList()) {
      if (ts > 0) {
        put.addColumn(colData.getFamily(), colData.getQualifier(), ts, colData.getValue());
      } else {
        put.addColumn(colData.getFamily(), colData.getQualifier(), colData.getValue());
      }
    }
    return put;
  }

  @Override
  public Delete constructDelete(RowKey<?> rowKey, List<HBaseColumn> columnSchemaList, long ts) {
    return constructDelete(rowKey.toBytes(), columnSchemaList, ts);
  }

  @Override
  public Delete constructDelete(Result result, List<HBaseColumn> columnSchemaList, long ts) {
    return constructDelete(result.getRow(), columnSchemaList, ts);
  }

  private Delete constructDelete(byte[] row, List<HBaseColumn> columnSchemaList, long ts) {
    Delete delete = new Delete(row);
    if (columnSchemaList == null || columnSchemaList.isEmpty()) {
      return delete;
    }
    for (HBaseColumn hBaseColumnSchema : columnSchemaList) {
      if (hBaseColumnSchema.columnIsRow()) {
        continue;
      }
      byte[] familyBytes = hBaseColumnSchema.getFamilyNameBytes();
      byte[] qualifierBytes = hBaseColumnSchema.getColumnNameBytes();
      if (ts < 1) {
        delete.addColumn(familyBytes, qualifierBytes);
      } else {
        delete.addColumn(familyBytes, qualifierBytes, ts);
      }
    }
    return delete;
  }

  @Override
  public WrapperBufferedMutator getWrapperBufferedMutator(HTableContext tableContext) {
    BufferedMutator bufferedMutator = this.getBufferedMutator(tableContext);
    return new WrapperBufferedMutatorImpl(tableContext, bufferedMutator);
  }

  @Override
  public WrapperBufferedMutator getHedgedReadWrapperBufferedMutator(HTableContext tableContext) {
    BufferedMutator bufferedMutator = this.getHedgedReadBufferedMutator(tableContext);
    return new WrapperBufferedMutatorImpl(tableContext, bufferedMutator);
  }
}