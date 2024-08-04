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

package com.hydraql.template;

import com.hydraql.adapter.HBaseSqlAdapter;
import com.hydraql.adapter.service.HQLService;
import com.hydraql.common.model.row.HBaseDataSet;
import com.hydraql.dsl.model.HBaseTableSchema;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author leojie 2022/11/27 17:14
 */
public class HBaseSqlTemplate implements BaseHBaseSqlTemplate {
  private final Configuration configuration;
  private final HQLService sqlAdapter;

  private HBaseSqlTemplate(Builder builder) {
    this.configuration = builder.configuration;
    this.sqlAdapter = new HBaseSqlAdapter(this.configuration);
  }

  @Override
  public void createVirtualTable(String hql) {
    sqlAdapter.createVirtualTable(hql);
  }

  @Override
  public void dropVirtualTable(String hql) {
    sqlAdapter.dropVirtualTable(hql);
  }

  @Override
  public String showCreateVirtualTable(String hql) {
    return sqlAdapter.showCreateVirtualTable(hql);
  }

  @Override
  public List<String> showVirtualTables(String hql) {
    return sqlAdapter.showVirtualTables(hql);
  }

  @Override
  public HBaseDataSet select(String hql) {
    return sqlAdapter.select(hql);
  }

  @Override
  public HBaseDataSet select(String hql, Map<String, Object> params) {
    return sqlAdapter.select(hql, params);
  }

  @Override
  public void insert(String hql) {
    sqlAdapter.insert(hql);
  }

  @Override
  public void delete(String hql) {
    sqlAdapter.delete(hql);
  }

  @Override
  public void registerTableSchema(HBaseTableSchema tableSchema) {
    sqlAdapter.registerTableSchema(tableSchema);
  }

  @Override
  public void printTableSchema(String tableName) {
    sqlAdapter.printTableSchema(tableName);
  }

  static class Builder extends BaseTemplateBuilder<HBaseSqlTemplate> {
    @Override
    HBaseSqlTemplate build() {
      return new HBaseSqlTemplate(this);
    }
  }

  public static HBaseSqlTemplate of(Connection connection) {
    return new Builder().connection(connection).build();
  }

  public static HBaseSqlTemplate of(Configuration configuration) {
    return new Builder().configuration(configuration).build();
  }

  public static HBaseSqlTemplate of(Properties properties) {
    return new Builder().configuration(properties).build();
  }

  public static HBaseSqlTemplate of(String zkQuorum, String zkClientPort) {
    return new Builder().configuration(zkQuorum, zkClientPort).build();
  }

  public Configuration getConfiguration() {
    return configuration;
  }
}
