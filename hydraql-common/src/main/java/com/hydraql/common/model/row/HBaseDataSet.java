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

package com.hydraql.common.model.row;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2022/12/5 23:07
 */
public class HBaseDataSet {
  private final String tableName;
  private List<HBaseDataRow> rowSet;

  public HBaseDataSet(String tableName) {
    this.tableName = tableName;
  }

  public String getTableName() {
    return tableName;
  }

  public List<HBaseDataRow> getRowSet() {
    return rowSet;
  }

  public static HBaseDataSet of(String tableName) {
    return new HBaseDataSet(tableName);
  }

  public HBaseDataSet appendRow(HBaseDataRow row) {
    if (this.rowSet == null) {
      this.rowSet = new ArrayList<>();
    }
    this.rowSet.add(row);
    return this;
  }

  public HBaseDataSet appendRows(List<HBaseDataRow> rowList) {
    if (this.rowSet == null) {
      this.rowSet = new ArrayList<>();
    }
    this.rowSet.addAll(rowList);
    return this;
  }

  public void show() {
    show(false);
  }

  public void show(boolean showTs) {
    System.out.println(showTable(showTs));
  }

  public String showTable(boolean showTs) {
    if (this.getRowSet() == null || this.getRowSet().isEmpty()) {
      return "";
    }
    List<String> colNams = new ArrayList<>();
    HBaseDataRow row = this.getRowSet().get(0);
    colNams.add(row.getRowKeyFieldName());
    for (HBaseDataColumn column : row.getColumns()) {
      colNams.add(column.getAlias());
      if (showTs) {
        colNams.add("timestamp");
      }
    }
    List<List<String>> valueList = new ArrayList<>();
    for (HBaseDataRow r : this.getRowSet()) {
      List<String> tmpValueList = new ArrayList<>(r.getColumns().size() + 1);
      tmpValueList.add(r.getRowKeyVal().toString());

      for (HBaseDataColumn column : r.getColumns()) {
        Object value = column.getValue();
        if (value == null) {
          tmpValueList.add("NULL");
        } else {
          tmpValueList.add(value.toString());
        }
        if (showTs) {
          tmpValueList.add(String.valueOf(column.getTimestamp()));
        }
      }
      valueList.add(tmpValueList);
    }
    DataSetFormatter dataSetFormatter = new DataSetFormatter(colNams, valueList);
    return dataSetFormatter.printTable();
  }
}
