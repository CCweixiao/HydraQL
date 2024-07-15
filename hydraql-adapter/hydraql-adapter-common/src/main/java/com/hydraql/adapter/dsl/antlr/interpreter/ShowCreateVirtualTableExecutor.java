/**
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
import com.hydraql.adapter.dsl.antlr.visitor.TableNameVisitor;
import com.hydraql.dsl.model.HBaseTableSchema;

/**
 * @author leojie 2023/9/27 15:03
 */
public class ShowCreateVirtualTableExecutor extends BaseHqlExecutor<String> implements Interpreter {
  private final AbstractHBaseSqlAdapter sqlAdapter;

  private ShowCreateVirtualTableExecutor(ExecutorBuilder builder) {
    super(builder.hql);
    this.sqlAdapter = builder.sqlAdapter;
  }

  @Override
  public String execute() {
    this.checkParsedHqlTypeMatched(this.getHqlType());
    HydraQLParser.Show_table_commandContext showTableCommandContext =
        this.getSqlCommandContext().ddl_command().show_table_command();
    String tableName = new TableNameVisitor().extractTableName(showTableCommandContext.table_ref());
    HBaseTableSchema tableSchema = sqlAdapter.getTableSchema(tableName);
    String schema = tableSchema.toString();
    String createSql = tableSchema.getCreateSql();
    createSql = createSql.replaceAll("\n", "").replaceAll(",", ",\n");
    return createSql + "\n\n" + schema;
  }

  @Override
  public HQLType expectHqlType() {
    return HQLType.SHOW_VIRTUAL_TABLE;
  }

  private static class ExecutorBuilder extends Builder<ShowCreateVirtualTableExecutor, String> {
    private final String hql;
    private final AbstractHBaseSqlAdapter sqlAdapter;

    private ExecutorBuilder(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
      this.hql = hql;
      this.sqlAdapter = sqlAdapter;
    }

    @Override
    public ShowCreateVirtualTableExecutor build() {
      return new ShowCreateVirtualTableExecutor(this);
    }
  }

  public static ShowCreateVirtualTableExecutor of(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
    return new ShowCreateVirtualTableExecutor.ExecutorBuilder(hql, sqlAdapter).build();
  }
}
