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

package com.hydraql.core.metadata;

import com.hydraql.core.exceptions.InvalidTableModelClassException;
import com.hydraql.core.toolkit.Preconditions;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author leojie 2022/11/20 11:07
 */
public class HBaseTableInfo implements Serializable {
  private static final long serialVersionUID = -1129697459766514524L;

  private final Class<?> tableClass;
  private final Constructor<?> defaultConstructor;
  private final String tableName;
  private final List<HBaseFieldInfo> fields;
  private transient int rowCount;

  private HBaseTableInfo(Builder builder) {
    this.tableClass = builder.tableClass;
    this.defaultConstructor = builder.defaultConstructor;
    this.tableName = builder.tableName;
    this.fields = new LinkedList<>();
  }

  public static Builder newBuilder(Class<?> tableClass) {
    return new Builder(tableClass);
  }

  public static class Builder {
    private final Class<?> tableClass;
    private Constructor<?> defaultConstructor;
    private String tableName;

    private Builder(Class<?> tableClass) {
      this.tableClass = tableClass;
    }

    public Builder setDefaultConstructor(Constructor<?> defaultConstructor) {
      this.defaultConstructor = defaultConstructor;
      return this;
    }

    public Builder setTableName(String tableName) {
      this.tableName = tableName;
      return this;
    }

    public HBaseTableInfo build() {
      return new HBaseTableInfo(this);
    }
  }

  public Class<?> getTableClass() {
    return tableClass;
  }

  public Constructor<?> getDefaultConstructor() {
    return defaultConstructor;
  }

  public String getTableName() {
    return tableName;
  }

  public List<HBaseFieldInfo> getFields() {
    if (fields.isEmpty()) {
      throw new InvalidTableModelClassException(String
          .format("No fields defined for the model class [%s].", this.getTableClass().getName()));
    }
    return Collections.unmodifiableList(this.fields);
  }

  public void appendColumnMeta(HBaseFieldInfo field) {
    Preconditions.checkState(field != null, "The field is null");
    if (field.isRowKey()) {
      rowCount++;
      if (rowCount > 1) {
        throw new InvalidTableModelClassException(String.format(
          "The model class [%s] contains more than one row key.", this.getTableClass().getName()));
      }
      fields.add(0, field);
      return;
    }
    fields.add(field);
  }
}
