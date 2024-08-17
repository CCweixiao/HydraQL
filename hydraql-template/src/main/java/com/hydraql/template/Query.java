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

import com.hydraql.adapter.HBaseTableAdapter;
import com.hydraql.core.metadata.HBaseFieldInfo;
import com.hydraql.core.metadata.HBaseTableInfoHelper;
import com.hydraql.core.metadata.HBaseTableInfo;
import com.hydraql.common.query.GetRowParam;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jielongping
 */
public class Query<T> {
  private final Class<T> domainModelClass;
  private final Configuration configuration;
  private final HBaseTableAdapter tableAdapter;
  private final List<String> rows;
  private final String family;
  private final List<String> qualifiers;

  private Query(Builder<T> builder) {
    this.domainModelClass = builder.domainModelClass;
    this.configuration = builder.configuration;
    this.tableAdapter = new HBaseTableAdapter(this.configuration);
    this.rows = builder.rows;
    this.family = builder.family;
    this.qualifiers = builder.qualifiers;
  }

  public static <T> Query.Builder<T> of(Class<T> domainModelClass) {
    return new Builder<>(domainModelClass);
  }

  public T get() {
    if (this.rows.isEmpty()) {
      return null;
    }
    HBaseTableInfo tableSchema = HBaseTableInfoHelper.getTableInfo(this.getModelClass());
    HBaseFieldInfo rowField = tableSchema.getFields().get(0);
    byte[] row = rowField.getRow(rows.get(0));
    // todo 此处需优化
    GetRowParam getRowParam = GetRowParam.of(Bytes.toString(row)).family(this.getFamily())
        .qualifiers(this.getQualifiers()).build();
    return tableAdapter.getRow(getRowParam, this.getModelClass());
  }

  public static class Builder<T> {
    private final Class<T> domainModelClass;
    private Configuration configuration;
    private final List<String> rows;
    private String family;
    private final List<String> qualifiers;

    private Builder(final Class<T> domainModelClass) {
      this.domainModelClass = domainModelClass;
      this.configuration = HBaseConfiguration.create();
      this.rows = new ArrayList<>();
      this.family = domainModelClass.getSimpleName();
      this.qualifiers = new ArrayList<>();
    }

    public Builder<T> config(Configuration configuration) {
      this.configuration = configuration;
      return this;
    }

    public Builder<T> config(String key, String value) {
      configuration.set(key, value);
      return this;
    }

    public Builder<T> withRow(String rowKey) {
      this.rows.add(rowKey);
      return this;
    }

    public Builder<T> withRows(List<String> rows) {
      this.rows.addAll(rows);
      return this;
    }

    public Builder<T> addFamily(String family) {
      this.family = family;
      return this;
    }

    public Builder<T> addQualifier(String qualifier) {
      this.qualifiers.add(qualifier);
      return this;
    }

    public Builder<T> addQualifiers(List<String> qualifiers) {
      this.qualifiers.addAll(qualifiers);
      return this;
    }

    public Query<T> build() {
      return new Query<>(this);
    }
  }

  private Class<T> getModelClass() {
    return domainModelClass;
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public String getFamily() {
    return family;
  }

  public List<String> getQualifiers() {
    return qualifiers;
  }
}
