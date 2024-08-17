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
import java.util.HashMap;
import java.util.Map;

/**
 * @author leojie 2023/7/20 20:09
 */
public class HBaseRowData implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String rowKey;
  private final Map<String, HBaseColData> colDataContainer;

  private HBaseRowData(Builder builder) {
    this.rowKey = builder.rowKey;
    this.colDataContainer = builder.colDataContainer;
  }

  public static class Builder {
    private String rowKey;
    private Map<String, HBaseColData> colDataContainer;

    private Builder() {
    }

    public Builder row(String rowKey) {
      this.rowKey = rowKey;
      return this;
    }

    public Builder appendColData(String familyAndQualifierName, HBaseColData colData) {
      if (this.colDataContainer == null) {
        this.colDataContainer = new HashMap<>();
      }
      this.colDataContainer.put(familyAndQualifierName, colData);
      return this;
    }

    public Builder appendColData(String familyAndQualifierName, String value, long ts) {
      return this.appendColData(familyAndQualifierName, new HBaseColData(value, ts));
    }

    public Builder emptyColData() {
      this.colDataContainer = new HashMap<>(0);
      return this;
    }

    public HBaseRowData build() {
      return new HBaseRowData(this);
    }
  }

  public static Builder of(String rowKey) {
    return new Builder().row(rowKey);
  }

  public static HBaseRowData empty() {
    return HBaseRowData.of("").emptyColData().build();
  }

  public String getRowKey() {
    return rowKey;
  }

  public Map<String, HBaseColData> getColDataContainer() {
    return colDataContainer;
  }
}
