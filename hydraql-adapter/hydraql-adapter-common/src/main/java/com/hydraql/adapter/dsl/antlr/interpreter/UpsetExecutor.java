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

package com.hydraql.adapter.dsl.antlr.interpreter;

import com.hydraql.adapter.AbstractHBaseSqlAdapter;
import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.adapter.dsl.antlr.data.InsertRowData;
import com.hydraql.adapter.dsl.antlr.visitor.TableNameVisitor;
import com.hydraql.adapter.dsl.antlr.visitor.UpsertColListVisitor;
import com.hydraql.adapter.dsl.antlr.visitor.UpsertValuesVisitor;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leojie 2023/9/27 19:22
 */
public class UpsetExecutor extends BaseHqlExecutor<Boolean> implements Interpreter {

  private final AbstractHBaseSqlAdapter sqlAdapter;

  private UpsetExecutor(UpsetExecutor.ExecutorBuilder builder) {
    super(builder.hql);
    this.sqlAdapter = builder.sqlAdapter;
  }

  @Override
  public Boolean execute() {
    this.checkParsedHqlTypeMatched(this.getHqlType());
    HydraQLParser.Upsert_values_commandContext upsertValuesCommandContext =
        this.getSqlCommandContext().dml_command().upsert_values_command();
    HydraQLParser.Upsert_column_def_listContext upsertColumnDefListContext =
        upsertValuesCommandContext.upsert_column_def_list();
    String tableName =
        new TableNameVisitor().extractTableName(upsertValuesCommandContext.table_ref());
    HBaseTableSchema tableSchema = sqlAdapter.getTableSchema(tableName);
    List<HBaseColumn> upsertColumns =
        new UpsertColListVisitor(tableSchema).extractUpsertColumns(upsertColumnDefListContext);
    UpsertValuesVisitor upsertValuesVisitor = new UpsertValuesVisitor(tableSchema, upsertColumns);

    List<InsertRowData> rowDataList =
        new ArrayList<>(upsertValuesCommandContext.insert_values().size());
    for (HydraQLParser.Insert_valuesContext insertValuesContext : upsertValuesCommandContext
        .insert_values()) {
      InsertRowData insertRowData = upsertValuesVisitor.extractInsertRowData(insertValuesContext);
      rowDataList.add(insertRowData);
    }

    if (rowDataList.size() == 1) {
      // todo timestamp处理
      Put put = sqlAdapter.constructPut(rowDataList.get(0), 0L);
      sqlAdapter.execSinglePut(tableName, put);
      return true;
    }

    List<Mutation> puts = rowDataList.stream().map(rowData -> sqlAdapter.constructPut(rowData, 0L))
        .collect(Collectors.toList());
    sqlAdapter.execBatchPuts(tableName, puts);
    return true;
  }

  @Override
  public HQLType expectHqlType() {
    return HQLType.PUT;
  }

  private static class ExecutorBuilder extends Builder<UpsetExecutor, Boolean> {
    private final String hql;
    private final AbstractHBaseSqlAdapter sqlAdapter;

    private ExecutorBuilder(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
      this.hql = hql;
      this.sqlAdapter = sqlAdapter;
    }

    @Override
    public UpsetExecutor build() {
      return new UpsetExecutor(this);
    }
  }

  public static UpsetExecutor of(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
    return new UpsetExecutor.ExecutorBuilder(hql, sqlAdapter).build();
  }
}
