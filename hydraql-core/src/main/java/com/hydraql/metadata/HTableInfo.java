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

package com.hydraql.metadata;

import com.hydraql.exceptions.InvalidTableModelClassException;
import com.hydraql.util.Preconditions;
import com.hydraql.reflectasm.Reflector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author leojie 2022/11/20 11:07
 */
public class HTableInfo implements Serializable {
  private static final long serialVersionUID = -1129697459766514524L;

  private final Class<?> tableEntityClass;
  private final Reflector reflector;
  private final String tableName;
  private HFieldInfo.RowKey rowKey;
  private final List<HFieldInfo.Qualifier> qualifiers;

  private HTableInfo(Builder builder) {
    this.tableEntityClass = builder.tableEntityClass;
    this.reflector = builder.reflector;
    this.tableName = builder.tableName;
    this.qualifiers = new ArrayList<>();
  }

  public static Builder newBuilder(Class<?> tableClass) {
    return new Builder(tableClass);
  }

  public static class Builder {
    private final Class<?> tableEntityClass;
    private Reflector reflector;
    private String tableName;

    private Builder(Class<?> tableEntityClass) {
      this.tableEntityClass = tableEntityClass;
    }

    public Builder setReflector(Reflector reflector) {
      this.reflector = reflector;
      return this;
    }

    public Builder setTableName(String tableName) {
      this.tableName = tableName;
      return this;
    }

    public HTableInfo build() {
      return new HTableInfo(this);
    }
  }

  public Class<?> getTableEntityClass() {
    return tableEntityClass;
  }

  public String getTableName() {
    return tableName;
  }

  public HFieldInfo.RowKey getRowKey() {
    if (this.rowKey == null) {
      throw new InvalidTableModelClassException(String.format(
              "The model class [%s] should contain one row key.", this.getTableEntityClass().getName()));
    }
    return rowKey;
  }

  public List<HFieldInfo.Qualifier> getQualifiers() {
    if (this.qualifiers.isEmpty()) {
      throw new InvalidTableModelClassException(String.format(
              "The model class [%s] should contain least one qualifier.", this.getTableEntityClass().getName()));
    }
    return Collections.unmodifiableList(this.qualifiers);
  }

  public void addRowKey(HFieldInfo.RowKey rowKey) {
    Preconditions.checkNotNull(rowKey, "rowKey cannot be null");
    if (this.rowKey == null) {
      this.rowKey = rowKey;
    }
    throw new InvalidTableModelClassException(String.format(
            "The model class [%s] contains more than one row key.", this.getTableEntityClass().getName()));
  }

  public void appendQualifier(HFieldInfo.Qualifier qualifier) {
    Preconditions.checkNotNull(rowKey, "qualifier cannot be null");
    if (qualifiers.contains(qualifier)) {
      throw new InvalidTableModelClassException(String.format(
              "The qualifier [%s] has been added.", qualifier.getName()));
    }
    qualifiers.add(qualifier);
  }

  @SuppressWarnings("unchecked")
  public <T> T newInstance() {
    Preconditions.checkNotNull(this.reflector,"Reflector cannot be null");
    return (T) this.reflector.create(this.getTableEntityClass());
  }
}
