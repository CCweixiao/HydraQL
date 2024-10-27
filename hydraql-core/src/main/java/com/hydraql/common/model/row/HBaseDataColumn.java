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

package com.hydraql.common.model.row;

import com.hydraql.type.ColumnType;

/**
 * @author leojie 2022/12/5 23:03
 */
public class HBaseDataColumn {
  private final String family;
  private final String qualifier;
  private final String alias;
  private final ColumnType columnType;
  private final Object value;
  private final long timestamp;

  public HBaseDataColumn(String family, String qualifier, String alias, ColumnType columnType,
      Object value, long timestamp) {
    this.family = family;
    this.qualifier = qualifier;
    this.alias = alias;
    this.columnType = columnType;
    this.value = value;
    this.timestamp = timestamp;
  }

  public String getFamily() {
    return family;
  }

  public String getQualifier() {
    return qualifier;
  }

  public String getAlias() {
    return alias;
  }

  public ColumnType getColumnType() {
    return columnType;
  }

  public Object getValue() {
    return value;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
