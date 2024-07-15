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

package com.hydraql.service.row;

import com.hydraql.common.model.row.HBaseDataSet;
import com.hydraql.common.model.row.DataSetFormatter;
import com.hydraql.common.model.row.HBaseDataRow;
import com.hydraql.common.type.ColumnType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author leojie 2022/12/6 22:34
 */
public class DataSetTest {
  @Test
  public void testDataSet() {

    HBaseDataRow row1 = HBaseDataRow.of("10001").appendColumn("f1", "name", "leo")
        .appendColumn("f1", "age", ColumnType.IntegerType, 12).appendColumn("f1", "address", "上海市");

    HBaseDataRow row2 = HBaseDataRow.of("10002").appendColumn("f1", "name", "leo1")
        .appendColumn("f1", "age", ColumnType.IntegerType, 14).appendColumn("f1", "address", "北京市");
    HBaseDataSet dataSet = HBaseDataSet.of("test:test_sql").appendRow(row1).appendRow(row2);
    dataSet.show();
  }

  @Test
  public void testShow() {
    List<String> cols = Arrays.asList("name", "age", "address");
    List<List<String>> values = Arrays.asList(Arrays.asList("leo", "18", "通过JDBC查询数据库表中的数据"),
      Arrays.asList("leo2", "17", "通过JDBC查询数据库表中的数据通过JDBC查询数据库表中的数据"));
    DataSetFormatter dataSetFormatter = new DataSetFormatter(cols, values);
    System.out.println(dataSetFormatter.printTable());
    System.out.println();
  }
}
