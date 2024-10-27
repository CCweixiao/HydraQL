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

import com.hydraql.type.ColumnType;
import com.hydraql.dsl.client.rowkey.func.Md5RowKeyFunc;

/**
 * @author leojie 2022/12/3 12:53
 */
public class Md5RowKey extends BaseRowKey<String> {

  public Md5RowKey(String value) {
    super(value, new Md5RowKeyFunc());
  }

  @Override
  public ColumnType columnType() {
    return ColumnType.StringType;
  }
}
