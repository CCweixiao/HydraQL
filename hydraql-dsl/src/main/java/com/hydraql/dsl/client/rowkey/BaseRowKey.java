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

import com.hydraql.dsl.client.rowkey.func.RowKeyFunc;

/**
 * @author leojie 2022/12/3 18:21
 */
public abstract class BaseRowKey<T> implements RowKey<T> {
  protected String value;
  protected byte[] valueBytes;
  protected RowKeyFunc<T> rowKeyFunc;

  public BaseRowKey(String value, RowKeyFunc<T> rowKeyFunc) {
    this.value = value;
    this.rowKeyFunc = rowKeyFunc;
  }

  @Override
  public byte[] toBytes() {
    // set order 1
    if (this.valueBytes != null) {
      return this.valueBytes;
    }
    // convert order 2
    T v = this.computeRowValue();
    if (v == null) {
      this.valueBytes = null;
    } else {
      this.valueBytes =
          this.columnType().getTypeHandler().toBytes(this.columnType().getTypeClass(), v);
    }
    return this.valueBytes;
  }

  @Override
  public void setValueBytes(byte[] valueBytes) {
    this.valueBytes = valueBytes;
  }

  @Override
  public String getOriValue() {
    return value;
  }

  public RowKeyFunc<T> getRowKeyFunc() {
    return rowKeyFunc;
  }

  @Override
  public T computeRowValue() {
    return this.getRowKeyFunc().evalFuncReturnRowValue(this);
  }

  public void setRowKeyFunc(RowKeyFunc<T> rowKeyFunc) {
    this.rowKeyFunc = rowKeyFunc;
  }
}
