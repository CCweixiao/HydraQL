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

package com.hydraql.dsl.client.rowkey.func;

import com.hydraql.core.type.ColumnType;
import com.hydraql.dsl.client.rowkey.BaseRowKey;
import com.hydraql.dsl.model.HBaseColumn;

/**
 * @author leojie 2022/12/3 13:40
 */
public class ConvertLongRowKeyFunc extends ConvertRowKeyFunc<Long> {
  public ConvertLongRowKeyFunc() {
    super(ColumnType.LongType);
  }

  @Override
  public Long evalFuncReturnRowValue(BaseRowKey<Long> rowKey) {
    return Long.parseLong(
      rowKey.columnType().getTypeHandler().extractMatchTtypeValue(rowKey.getOriValue()));
  }

  @Override
  public Long evalFuncReturnRowValue(HBaseColumn row, String value) {
    return Long.parseLong(row.getColumnType().getTypeHandler().extractMatchTtypeValue(value));
  }
}
