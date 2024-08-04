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

package com.hydraql.dsl.client.rowkey;

import com.hydraql.common.exception.HBaseSqlRowKeyUnsupportedException;
import com.hydraql.dsl.client.rowkey.func.RowKeyFunction;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;

/**
 * // todo 单例 或 缓存优化
 * @author leojie 2022/12/3 21:25
 */
public class RowKeyFactory {
  public static RowKey<?> getRowKeyByTableSchema(String rowValue, HBaseTableSchema tableSchema) {
    HBaseColumn row = tableSchema.findRow();
    RowKey<?> rowKey;
    switch (row.getColumnType()) {
      case StringType:
        rowKey = new StringRowKey(rowValue);
        break;
      case IntegerType:
        rowKey = new IntRowKey(rowValue);
        break;
      case LongType:
        rowKey = new LongRowKey(rowValue);
        break;
      default:
        throw new HBaseSqlRowKeyUnsupportedException(String
            .format("The column type [%s] is unsupported to be row key", row.getColumnType()));
    }
    return rowKey;
  }

  public static RowKey<?> getRowKeyByFuncName(String functionName, String... params) {
    RowKeyFunction rowKeyFunc = RowKeyFunction.findRowKeyFunc(functionName);
    RowKey<?> rowKey;
    switch (rowKeyFunc) {
      case convert_to_int:
        rowKey = new IntRowKey(params[0]);
        break;
      case convert_to_long:
        rowKey = new LongRowKey(params[0]);
        break;
      case md5:
        rowKey = new Md5RowKey(params[0]);
        break;
      case md5_prefix:
        rowKey = new Md5PrefixRowKey(params[0]);
        break;
      case reverse:
        rowKey = new ReverseRowKey(params[0]);
        break;
      default:
        rowKey = new StringRowKey(params[0]);
        break;
    }
    return rowKey;
  }
}
