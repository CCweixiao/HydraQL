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

package com.hydraql.common.row;

import com.hydraql.common.util.DigestUtil;
import com.hydraql.common.util.StringUtil;

/**
 * @author leojie@apache.org 2024/8/10 23:40
 */
public class RowKeyHashingGenerator implements RowKeyGenerator {
  private static final int DEFAULT_STEP = 4;
  private static final String DELIMITER = "|";
  private static final int HASHING_PREFIX_LENGTH = DEFAULT_STEP + DELIMITER.length();

  @Override
  public String apply(String originalRow) {
    if (StringUtil.isBlank(originalRow)) {
      return originalRow;
    }
    String md5Key = DigestUtil.md5Hex(originalRow);
    long hashCode = Math.abs(md5Key.hashCode());
    return String.valueOf(hashCode).substring(0, DEFAULT_STEP) + DELIMITER + originalRow;
  }

  @Override
  public String recover(String generatedRow) {
    if (StringUtil.isBlank(generatedRow)) {
      return generatedRow;
    }
    if (generatedRow.length() <= HASHING_PREFIX_LENGTH) {
      return generatedRow;
    }
    return generatedRow.substring(HASHING_PREFIX_LENGTH);
  }

  @Override
  public boolean isDefault() {
    return false;
  }
}