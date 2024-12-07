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

package com.hydraql.generator;

import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.Objects;

/**
 * @author leojie@apache.org 2024/8/10 23:38
 */
public class RowKeyReverseGenerator implements RowKeyGenerator {
  @Override
  public Object apply(Object originalRow) {
    String row = Objects.toString(originalRow, null);
    if (StringUtil.isBlank(row)) {
      return row;
    }
    return StringUtil.reverse(row);
  }

  @Override
  public byte[] applyToBytes(Object originalRow) {
    Object value = apply(originalRow);
    if (value == null) {
      return null;
    }
    return Bytes.toBytes(value.toString());
  }

  @Override
  public Object recover(Object generatedRow) {
    String row = Objects.toString(generatedRow, null);
    if (row == null) {
      return null;
    }
    return StringUtil.reverse(row);
  }

  @Override
  public byte[] recoverToBytes(byte[] generatedRow) {
    String row = Bytes.toString(generatedRow);
    if (row == null) {
      return null;
    }
    return Bytes.toBytes(StringUtil.reverse(row));
  }
}
