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

package com.hydraql.common.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leojie 2023/7/20 20:09
 */
public class HBaseRowDataWithMultiVersions implements Serializable {
  public static final long serialVersionUID = 1L;

  private final String rowKey;
  private final Map<String, List<HBaseColData>> colDataWithMultiVersions;

  private HBaseRowDataWithMultiVersions(Builder builder) {
    this.rowKey = builder.rowKey;
    this.colDataWithMultiVersions = builder.colDataWithMultiVersions;
  }

  public static class Builder {
    private String rowKey;
    private Map<String, List<HBaseColData>> colDataWithMultiVersions;

    private Builder() {
    }

    public Builder row(String rowKey) {
      this.rowKey = rowKey;
      return this;
    }

    public Builder appendColData(String familyAndQualifierName, List<HBaseColData> colDataList) {
      if (this.colDataWithMultiVersions == null) {
        this.colDataWithMultiVersions = new HashMap<>();
      }
      this.colDataWithMultiVersions.put(familyAndQualifierName, colDataList);
      return this;
    }

    public Builder appendColData(String familyAndQualifierName, String value, long ts) {
      if (this.colDataWithMultiVersions == null) {
        this.colDataWithMultiVersions = new HashMap<>();
      }
      List<HBaseColData> colDataList =
          this.colDataWithMultiVersions.getOrDefault(familyAndQualifierName, new ArrayList<>());
      colDataList.add(new HBaseColData(value, ts));
      this.colDataWithMultiVersions.put(familyAndQualifierName, colDataList);
      return this;
    }

    public Builder empty() {
      this.colDataWithMultiVersions = new HashMap<>(0);
      return this;
    }

    public HBaseRowDataWithMultiVersions build() {
      return new HBaseRowDataWithMultiVersions(this);
    }
  }

  public static Builder of(String rowKey) {
    return new Builder().row(rowKey);
  }

  public static HBaseRowDataWithMultiVersions empty() {
    return HBaseRowDataWithMultiVersions.of("").empty().build();
  }

  public String getRowKey() {
    return rowKey;
  }

  public Map<String, List<HBaseColData>> getColDataWithMultiVersions() {
    return colDataWithMultiVersions;
  }
}
