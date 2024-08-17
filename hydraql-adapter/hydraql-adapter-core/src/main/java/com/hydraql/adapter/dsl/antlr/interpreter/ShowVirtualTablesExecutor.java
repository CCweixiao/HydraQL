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
import com.hydraql.dsl.context.HBaseSqlContext;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author leojie 2023/9/27 14:57
 */
public class ShowVirtualTablesExecutor extends BaseHqlExecutor<List<String>>
    implements Interpreter {
  private final AbstractHBaseSqlAdapter sqlAdapter;

  private ShowVirtualTablesExecutor(ExecutorBuilder builder) {
    super(builder.hql);
    this.sqlAdapter = builder.sqlAdapter;
  }

  @Override
  public List<String> execute() {
    this.checkParsedHqlTypeMatched(this.getHqlType());
    List<String> allRegisteredVirtualTables =
        HBaseSqlContext.getInstance().getAllRegisteredVirtualTables();
    List<String> allVirtualTables = queryAllVirtualTables();
    Set<String> tableNames = new HashSet<>(allRegisteredVirtualTables);
    tableNames.addAll(allVirtualTables);
    return new ArrayList<>(tableNames);
  }

  @Override
  public HQLType expectHqlType() {
    return HQLType.SHOW_VIRTUAL_TABLES;
  }

  private List<String> queryAllVirtualTables() {
    Scan scan = new Scan();
    return sqlAdapter
        .executeQuery(AbstractHBaseSqlAdapter.HQL_META_DATA_TABLE_NAME.getNameAsString(), table -> {
          ResultScanner scanner = table.getScanner(scan);
          List<String> tables = new ArrayList<>();
          for (Result result : scanner) {
            tables.add(Bytes.toString(result.getRow()));
          }
          return tables;
        });
  }

  private static class ExecutorBuilder extends Builder<ShowVirtualTablesExecutor, List<String>> {
    private final String hql;
    private final AbstractHBaseSqlAdapter sqlAdapter;

    private ExecutorBuilder(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
      this.hql = hql;
      this.sqlAdapter = sqlAdapter;
    }

    @Override
    public ShowVirtualTablesExecutor build() {
      return new ShowVirtualTablesExecutor(this);
    }
  }

  public static ShowVirtualTablesExecutor of(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
    return new ShowVirtualTablesExecutor.ExecutorBuilder(hql, sqlAdapter).build();
  }
}
