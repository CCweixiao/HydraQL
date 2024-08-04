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

import com.hydraql.common.type.ColumnType;

/**
 * @author leojie 2022/12/3 13:08
 */
public abstract class ConvertRowKeyFunc<T> implements RowKeyFunc<T> {
  protected final ColumnType targetColumnType;

  public ConvertRowKeyFunc(ColumnType targetColumnType) {
    this.targetColumnType = targetColumnType;
  }

  @Override
  public String showFuncName() {
    return "convert_to_" + this.targetColumnType.getTypeName();
  }

  @Override
  public String showDesc() {
    return "Convert string row value to " + this.targetColumnType.getTypeName() + " .";
  }
}
