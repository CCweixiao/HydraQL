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

import com.hydraql.core.type.ColumnType;
import com.hydraql.dsl.client.rowkey.func.RowKeyFunc;

/**
 * @author leojie 2020/11/28 10:58 上午
 */
public interface RowKey<T> {
  /**
   * byte arr of row key
   * @return row value byte arr
   */
  byte[] toBytes();

  /**
   * extract value
   * @return object value
   */
  T computeRowValue();

  String getOriValue();

  void setValueBytes(byte[] valueBytes);

  /**
   * column type
   * @return type
   */
  ColumnType columnType();

  /**
   * set function by name
   * @param rowKeyFunc function
   */
  void setRowKeyFunc(RowKeyFunc<T> rowKeyFunc);
}
