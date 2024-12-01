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

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2023/7/20 11:09
 */
@Deprecated
public class GetRowsParam extends BaseGetRowParam<GetRowsParam> {
  private final List<String> rowKeyList;

  public GetRowsParam(Builder builder) {
    super(builder);
    this.rowKeyList = builder.rowKeyList;
  }

  public static class Builder extends BaseGetRowParam.Builder<GetRowsParam> {
    private List<String> rowKeyList;

    private Builder() {
      super();
    }

    public Builder of(List<String> rowKeyList) {
      this.rowKeyList = rowKeyList;
      return this;
    }

    public Builder appendRowKey(String rowKey) {
      if (this.rowKeyList == null) {
        this.rowKeyList = new ArrayList<>();
      }
      this.rowKeyList.add(rowKey);
      return this;
    }

    @Override
    public GetRowsParam build() {
      return new GetRowsParam(this);
    }
  }

  public static Builder of(List<String> rowKeyList) {
    return new GetRowsParam.Builder().of(rowKeyList);
  }

  public List<String> getRowKeyList() {
    return rowKeyList;
  }

  @Override
  public String toString() {
    return "GetParams{" + "rowKeyList='" + rowKeyList + '\'' + ", familyName='" + getFamily() + '\''
        + ", qualifiers=" + getQualifiers() + ", timeRange=" + getTimeRange() + ", versions="
        + getVersions() + '}';
  }
}
