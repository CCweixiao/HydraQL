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

import com.hydraql.common.lang.Assert;
import com.hydraql.common.util.DigestUtil;
import com.hydraql.common.util.StringUtil;
import com.hydraql.dsl.client.rowkey.BaseRowKey;
import com.hydraql.dsl.model.HBaseColumn;

/**
 * @author leojie 2022/12/3 13:48
 */
public class Md5RowKeyFunc implements RowKeyFunc<String> {
  @Override
  public String evalFuncReturnRowValue(BaseRowKey<String> rowKey) {
    String oriValue = rowKey.getOriValue();
    Assert.checkArgument(StringUtil.isNotBlank(oriValue), "The value of row is not empty.");
    return DigestUtil.md5Hex(oriValue);
  }

  @Override
  public String evalFuncReturnRowValue(HBaseColumn row, String value) {
    Assert.checkArgument(StringUtil.isNotBlank(value), "The value of row is not empty.");
    return DigestUtil.md5Hex(value);
  }

  @Override
  public String showFuncName() {
    return "md5";
  }

  @Override
  public String showDesc() {
    return "MD5 encryption of row key, example md5('abcdefg')";
  }
}
