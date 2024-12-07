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
import org.apache.hadoop.hbase.util.MD5Hash;

import java.util.Objects;

/**
 * @author leojie@apache.org 2024/8/10 23:40
 */
public class RowKeyHashingGenerator implements RowKeyGenerator {
  private static final int DEFAULT_STEP = 8;
  private static final String DELIMITER = "|";

  @Override
  public Object apply(Object originalRow) {
    String row = Objects.toString(originalRow, null);
    if (StringUtil.isBlank(row)) {
      return row;
    }
    String md5AsHex = MD5Hash.getMD5AsHex(Bytes.toBytes(row));
    return md5AsHex.substring(0, DEFAULT_STEP) + DELIMITER + originalRow;
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
    if (StringUtil.isBlank(row)) {
      return generatedRow;
    }
    return recover(row);
  }

  private Object recover(String row) {
    int index = row.indexOf(DELIMITER);
    if (index == -1) {
      return row;
    }
    String realPrefix = row.substring(0, index);
    String originalRow = row.substring(index + 1);
    String md5AsHex = MD5Hash.getMD5AsHex(Bytes.toBytes(originalRow));
    String computePrefix = md5AsHex.substring(0, DEFAULT_STEP);
    if (computePrefix.equals(realPrefix)) {
      return originalRow;
    }
    return row;
  }

  @Override
  public byte[] recoverToBytes(byte[] generatedRow) {
    String row = Bytes.toString(generatedRow);
    if (row == null) {
      return null;
    }
    return Bytes.toBytes(recover(row).toString());
  }
}