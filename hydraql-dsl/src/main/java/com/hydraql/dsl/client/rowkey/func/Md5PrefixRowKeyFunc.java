/**
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
 * @author leojie 2022/12/3 13:42
 */
public class Md5PrefixRowKeyFunc implements RowKeyFunc<String> {
  private final int prefixLength;
  private final String prefixContactChar;

  public Md5PrefixRowKeyFunc(int prefixLength, String prefixContactChar) {
    this.prefixLength = prefixLength;
    this.prefixContactChar = prefixContactChar;
  }

  public Md5PrefixRowKeyFunc(int prefixLength) {
    this(prefixLength, "|");
  }

  public Md5PrefixRowKeyFunc() {
    this(4, "|");
  }

  @Override
  public String evalFuncReturnRowValue(BaseRowKey<String> rowKey) {
    String oriValue = rowKey.getOriValue();
    Assert.checkArgument(StringUtil.isNotBlank(oriValue), "The value of row is not empty.");
    String prefix = DigestUtil.md5Hex(oriValue).substring(0, prefixLength);
    return prefix.concat(prefixContactChar).concat(oriValue);
  }

  @Override
  public String evalFuncReturnRowValue(HBaseColumn row, String value) {
    Assert.checkArgument(StringUtil.isNotBlank(value), "The value of row is not empty.");
    String prefix = DigestUtil.md5Hex(value).substring(0, prefixLength);
    return prefix.concat(prefixContactChar).concat(value);
  }

  @Override
  public String showFuncName() {
    return "md5_prefix";
  }

  @Override
  public String showDesc() {
    return "Prefix the fixed bits after the rowkey splicing MD5. example: md5_prefix ( 'abcdef' ) "
        + ", md5_prefix ( abcdef , 4), md5_prefix ( abcdef , 4, '|')";
  }
}
