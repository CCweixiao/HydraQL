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

package com.hydraql.dsl.model;

import com.hydraql.common.constants.HydraQLConstants;
import com.hydraql.common.util.StringUtil;

/**
 * @author leojie 2023/9/10 22:51
 */
public class QueryHBaseColumn {
  private final HBaseColumn column;
  private final String alias;

  private QueryHBaseColumn(Builder builder) {
    this.column = builder.column;
    this.alias = builder.alias;
  }

  static class Builder {
    private final HBaseColumn column;
    private String alias;

    private Builder(HBaseColumn column) {
      this.column = column;
    }

    public Builder alias(String alias) {
      this.alias = alias;
      return this;
    }

    public QueryHBaseColumn build() {
      return new QueryHBaseColumn(this);
    }
  }

  public static QueryHBaseColumn column(HBaseColumn column) {
    String alias = column.getColumnName();
    String family = column.getFamily();
    if (!column.columnIsRow() && StringUtil.isNotBlank(family)) {
      alias = HydraQLConstants.getColumnName(family, alias);
    }
    return new QueryHBaseColumn.Builder(column).alias(alias).build();
  }

  public static QueryHBaseColumn column(HBaseColumn column, String alias) {
    return new QueryHBaseColumn.Builder(column).alias(alias).build();
  }

  public HBaseColumn getColumn() {
    return column;
  }

  public String getAlias() {
    return alias;
  }
}
