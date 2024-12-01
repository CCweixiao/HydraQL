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

package com.hydraql.common.query;

import com.hydraql.common.util.StringUtil;

/**
 * @author leojie 2023/7/20 11:09
 */
@Deprecated
public class GetRowParam extends BaseGetRowParam<GetRowParam> {
  private final String rowKey;

  public GetRowParam(Builder builder) {
    super(builder);
    this.rowKey = builder.rowKey;
  }

  public static class Builder extends BaseGetRowParam.Builder<GetRowParam> {
    private String rowKey;

    private Builder() {
      super();
    }

    private Builder of(String rowKey) {
      if (StringUtil.isBlank(rowKey)) {
        throw new IllegalArgumentException("The rowKey must be specified in the get query.");
      }
      this.rowKey = rowKey;
      return this;
    }

    @Override
    public GetRowParam build() {
      return new GetRowParam(this);
    }
  }

  public static Builder of(String rowKey) {
    return new GetRowParam.Builder().of(rowKey);
  }

  public String getRowKey() {
    return rowKey;
  }

  @Override
  public String toString() {
    return "GetParams{" + "rowKey='" + rowKey + '\'' + ", familyName='" + getFamily() + '\''
        + ", columnNames=" + getQualifiers() + ", timeRange=" + getTimeRange() + ", versions="
        + getVersions() + '}';
  }
}
