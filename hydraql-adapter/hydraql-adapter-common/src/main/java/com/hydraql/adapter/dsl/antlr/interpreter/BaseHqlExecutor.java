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

import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.model.HQLType;
import com.hydraql.common.util.StringUtil;
import com.hydraql.adapter.dsl.antlr.HBaseSQLErrorListener;
import com.hydraql.adapter.dsl.antlr.HBaseSQLStatementsLexer;
import com.hydraql.dsl.antlr.HydraQLParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.List;

/**
 * @author leojie 2023/9/27 11:39
 */
public abstract class BaseHqlExecutor<T> {
  private final String hql;
  private HydraQLParser.Sql_commandContext sqlCommandContext;

  protected BaseHqlExecutor(String hql) {
    this.hql = hql;
    this.parseSqlCommandContext();
  }

  public abstract T execute();

  private void parseSqlCommandContext() {
    if (StringUtil.isBlank(hql)) {
      throw new HBaseSqlAnalysisException("Please enter hql.");
    }
    try {
      HBaseSQLStatementsLexer lexer = new HBaseSQLStatementsLexer(CharStreams.fromString(hql));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      HydraQLParser parser = new HydraQLParser(tokens);
      parser.removeErrorListeners();
      parser.addErrorListener(new HBaseSQLErrorListener());
      HydraQLParser.RootContext root = parser.root();
      List<HydraQLParser.BatchContext> batchList = root.batch();
      if (batchList.size() > 1) {
        throw new HBaseSqlAnalysisException(
            "The execution of multi-segment SQL is not currently supported.");
      }
      HydraQLParser.BatchContext batchContext = batchList.get(0);
      this.sqlCommandContext = batchContext.sql_command();
    } catch (Exception e) {
      throw new HBaseSqlAnalysisException(String.format("The hql %s was parsed failed.", hql), e);
    }
  }

  public HydraQLParser.Sql_commandContext getSqlCommandContext() {
    return sqlCommandContext;
  }

  public HQLType getHqlType() {
    HydraQLParser.Dml_commandContext dmlCommandContext = sqlCommandContext.dml_command();
    HydraQLParser.Ddl_commandContext ddlCommandContext = sqlCommandContext.ddl_command();

    if (isDdlCommand()) {
      DDLSqlCommandParser ddlSqlCommandParser = new DDLSqlCommandParser(ddlCommandContext);
      if (ddlSqlCommandParser.isCreateVirtualTable()) {
        return HQLType.CREATE_VIRTUAL_TABLE;
      } else if (ddlSqlCommandParser.isDropVirtualTable()) {
        return HQLType.DROP_VIRTUAL_TABLE;
      } else if (ddlSqlCommandParser.isShowVirtualTables()) {
        return HQLType.SHOW_VIRTUAL_TABLES;
      } else if (ddlSqlCommandParser.isShowCreateVirtualTable()) {
        return HQLType.SHOW_VIRTUAL_TABLE;
      } else {
        throw new HBaseSqlAnalysisException("Illegal hql command.");
      }
    } else if (isDmlCommand()) {
      DMLSqlCommandParser dmlSqlCommandParser = new DMLSqlCommandParser(dmlCommandContext);
      if (dmlSqlCommandParser.isSelectStatement()) {
        return HQLType.SELECT;
      } else if (dmlSqlCommandParser.isUpsetStatement()) {
        return HQLType.PUT;
      } else if (dmlSqlCommandParser.isDeleteStatement()) {
        return HQLType.DELETE;
      } else {
        throw new HBaseSqlAnalysisException("Illegal hql command.");
      }
    } else {
      throw new HBaseSqlAnalysisException("Illegal hql statement.");
    }
  }

  public String getHql() {
    return hql;
  }

  protected static abstract class Builder<E extends BaseHqlExecutor<T>, T> {
    public abstract E build();
  }

  protected static class DMLSqlCommandParser {
    private final HydraQLParser.Dml_commandContext dmlCommandContext;

    public DMLSqlCommandParser(HydraQLParser.Dml_commandContext dmlCommandContext) {
      this.dmlCommandContext = dmlCommandContext;
    }

    public boolean isSelectStatement() {
      return this.dmlCommandContext.select_command() != null
          && !this.dmlCommandContext.select_command().isEmpty();
    }

    public boolean isUpsetStatement() {
      return this.dmlCommandContext.upsert_values_command() != null
          && !this.dmlCommandContext.upsert_values_command().isEmpty();
    }

    public boolean isDeleteStatement() {
      return this.dmlCommandContext.delete_command() != null
          && !this.dmlCommandContext.delete_command().isEmpty();
    }
  }

  protected static class DDLSqlCommandParser {
    private final HydraQLParser.Ddl_commandContext ddlCommandContext;

    public DDLSqlCommandParser(HydraQLParser.Ddl_commandContext ddlCommandContext) {
      this.ddlCommandContext = ddlCommandContext;
    }

    public boolean isCreateVirtualTable() {
      return this.ddlCommandContext.create_virtual_table_command() != null
          && !this.ddlCommandContext.create_virtual_table_command().isEmpty();
    }

    public boolean isShowVirtualTables() {
      return this.ddlCommandContext.show_tables_command() != null
          && !this.ddlCommandContext.show_tables_command().isEmpty();
    }

    public boolean isShowCreateVirtualTable() {
      return this.ddlCommandContext.show_table_command() != null
          && !this.ddlCommandContext.show_table_command().isEmpty();
    }

    public boolean isDropVirtualTable() {
      return this.ddlCommandContext.drop_table_command() != null
          && !this.ddlCommandContext.drop_table_command().isEmpty();
    }
  }

  protected boolean isDdlCommand() {
    return this.sqlCommandContext.ddl_command() != null;
  }

  protected boolean isDmlCommand() {
    return this.sqlCommandContext.dml_command() != null;
  }

}
