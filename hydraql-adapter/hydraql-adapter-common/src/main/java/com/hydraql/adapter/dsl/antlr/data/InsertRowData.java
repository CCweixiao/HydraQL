/**
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

package com.hydraql.adapter.dsl.antlr.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2023/7/28 19:54
 */
public class InsertRowData {
  private final byte[] rows;
  private final List<InsertColData> colDataList;

  private InsertRowData(Builder builder) {
    this.rows = builder.rows;
    this.colDataList = builder.colDataList;
  }

  public static class Builder {
    private final byte[] rows;
    private List<InsertColData> colDataList;

    private Builder(byte[] rows) {
      this.rows = rows;
      this.colDataList = new ArrayList<>();
    }

    public Builder addColDataList(List<InsertColData> colDataList) {
      this.colDataList = colDataList;
      return this;
    }

    public Builder addColData(InsertColData colData) {
      this.colDataList.add(colData);
      return this;
    }

    public Builder addColData(byte[] familyBytes, byte[] qualifierBytes, byte[] valueBytes) {
      return this.addColData(new InsertColData(familyBytes, qualifierBytes, valueBytes));
    }

    public InsertRowData build() {
      return new InsertRowData(this);
    }

  }

  public static Builder of(byte[] rows) {
    return new Builder(rows);
  }

  public byte[] getRows() {
    return rows;
  }

  public List<InsertColData> getColDataList() {
    return colDataList;
  }
}
