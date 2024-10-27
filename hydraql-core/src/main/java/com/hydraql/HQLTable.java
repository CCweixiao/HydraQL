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

package com.hydraql;

import com.hydraql.activerecord.Model;
import com.hydraql.common.util.StringUtil;
import com.hydraql.conf.BufferedMutatorOptions;
import com.hydraql.conf.AbstractHQLConfiguration;
import com.hydraql.executor.BaseExecutor;
import com.hydraql.handler.DefaultResultHandler;
import com.hydraql.handler.DefaultResultListHandler;
import com.hydraql.handler.DefaultResultSetHandler;
import com.hydraql.handler.ResultHandler;
import com.hydraql.handler.ResultListHandler;
import com.hydraql.handler.ResultSetHandler;
import com.hydraql.metadata.HTableInfo;
import com.hydraql.metadata.HTableInfoContainer;
import com.hydraql.session.HQlConnection;
import com.hydraql.util.Preconditions;

/**
 * @author leojie@apache.org 2024/10/26 20:14
 */
public abstract class HQLTable {
  private final String tableName;
  private final Class<? extends Model<?>> tableEntityClass;
  private final HTableInfo tableInfo;
  private final AbstractHQLConfiguration configuration;
  private BufferedMutatorOptions bufferedMutatorOptions;
  private HQlConnection connection;

  public HQLTable(String tableName, AbstractHQLConfiguration configuration) {
    Preconditions.checkArgument(StringUtil.isNotBlank(tableName), "tableName is blank.");
    Preconditions.checkArgument(configuration != null, "configuration is null.");
    this.tableName = tableName;
    this.configuration = configuration;
    this.tableEntityClass = null;
    this.tableInfo = null;
  }

  public HQLTable(Class<? extends Model<?>> tableEntityClass,
      AbstractHQLConfiguration configuration) {
    Preconditions.checkArgument(tableEntityClass != null, "tableEntityClass is null.");
    Preconditions.checkArgument(configuration != null, "configuration is null.");
    this.tableEntityClass = tableEntityClass;
    this.configuration = configuration;
    HTableInfoContainer.getInstance().register(this.tableEntityClass);
    this.tableInfo = HTableInfoContainer.getInstance().get(tableEntityClass);
    this.tableName = this.tableInfo.getTableName();
  }

  public String getTableName() {
    return tableName;
  }

  public HTableInfo getTableInfo() {
    return tableInfo;
  }

  public AbstractHQLConfiguration getConfiguration() {
    return configuration;
  }

  public void bindConnection(HQlConnection connection) {
    this.connection = connection;
  }

  public HQlConnection getConnection() {
    return connection;
  }

  public void setBufferedMutatorOptions(BufferedMutatorOptions bufferedMutatorOptions) {
    this.bufferedMutatorOptions = bufferedMutatorOptions;
  }

  public BufferedMutatorOptions getBufferedMutatorOptions() {
    if (bufferedMutatorOptions == null) {
      return getConfiguration().createDefaultBufferedMutatorOptions();
    }
    return bufferedMutatorOptions;
  }

  public <E> ResultHandler<E> getResultHandler() {
    return new DefaultResultHandler<>(this.getTableInfo());
  }

  public <E> ResultListHandler<E> getResultListHandler() {
    return new DefaultResultListHandler<>(this.getTableInfo());
  }

  public <E> ResultSetHandler<E> getResultSetHandler() {
    return new DefaultResultSetHandler<>(this.getTableInfo());
  }

  public abstract BaseExecutor newExecutor();
}
