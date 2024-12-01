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

import com.hydraql.common.util.StringUtil;
import com.hydraql.conf.AbstractHqlConfiguration;
import com.hydraql.conf.BufferedMutatorOptions;
import com.hydraql.executor.BaseExecutor;
import com.hydraql.metadata.HTableInfo;
import com.hydraql.metadata.HTableInfoContainer;
import com.hydraql.session.HqlConnection;
import com.hydraql.util.Preconditions;

/**
 * @author leojie@apache.org 2024/10/26 20:14
 */
public abstract class AbstractHqlTable {
  private final String tableName;

  private Class<?> tableEntityClass;
  private HTableInfo tableInfo;
  private final AbstractHqlConfiguration configuration;
  private BufferedMutatorOptions bufferedMutatorOptions;
  private HqlConnection connection;

  public AbstractHqlTable(String tableName, AbstractHqlConfiguration configuration) {
    Preconditions.checkArgument(StringUtil.isNotBlank(tableName), "Table name is blank.");
    Preconditions.checkArgument(configuration != null, "Configuration is null.");
    this.tableName = tableName;
    this.configuration = configuration;
  }

  public <E> void initTableEntityClass(Class<E> tableEntityClass) {
    this.tableEntityClass = tableEntityClass;
    this.tableInfo = HTableInfoContainer.getInstance().get(tableEntityClass);
  }

  public String getTableName() {
    return tableName;
  }

  public Class<?> getTableEntityClass() {
    return tableEntityClass;
  }

  public HTableInfo getTableInfo() {
    return tableInfo;
  }

  public AbstractHqlConfiguration getConfiguration() {
    return configuration;
  }

  public void bindConnection(HqlConnection connection) {
    this.connection = connection;
  }

  public HqlConnection getConnection() {
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

  public abstract BaseExecutor newExecutor();
}
