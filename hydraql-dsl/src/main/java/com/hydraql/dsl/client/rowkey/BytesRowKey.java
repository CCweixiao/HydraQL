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

import com.hydraql.util.Assert;
import com.hydraql.type.ColumnType;
import com.hydraql.common.util.EncodingUtil;
import com.hydraql.dsl.client.rowkey.func.RowKeyFunc;

import java.util.Arrays;

/**
 * @author leojie 2020/11/28 11:58 上午
 */
@Deprecated
public class BytesRowKey implements RowKey<byte[]> {
  private final byte[] value;

  public BytesRowKey(byte[] value) {
    Assert.checkNotNull(value);
    this.value = value.clone();
  }

  @Override
  public byte[] toBytes() {
    return new byte[0];
  }

  @Override
  public byte[] computeRowValue() {
    return this.value;
  }

  @Override
  public String getOriValue() {
    return null;
  }

  @Override
  public void setValueBytes(byte[] valueBytes) {

  }

  @Override
  public ColumnType columnType() {
    return null;
  }

  @Override
  public void setRowKeyFunc(RowKeyFunc<byte[]> rowKeyFunc) {

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BytesRowKey)) {
      return false;
    }
    BytesRowKey that = (BytesRowKey) o;
    return Arrays.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(value);
  }

  @Override
  public String toString() {
    return "BytesRowKey [key=" + EncodingUtil.toHexString(value) + "]";
  }

}
