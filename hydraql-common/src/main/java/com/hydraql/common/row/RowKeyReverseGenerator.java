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

import com.hydraql.common.util.StringUtil;

/**
 * @author leojie@apache.org 2024/8/10 23:38
 */
public class RowKeyReverseGenerator implements RowKeyGenerator {
  @Override
  public String apply(String originalRow) {
    if (StringUtil.isBlank(originalRow)) {
      return originalRow;
    }
    return StringUtil.reverse(originalRow);
  }

  @Override
  public String recover(String generatedRow) {
    if (StringUtil.isBlank(generatedRow)) {
      return generatedRow;
    }
    return StringUtil.reverse(generatedRow);
  }

  @Override
  public boolean isDefault() {
    return false;
  }
}
