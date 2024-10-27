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

import com.hydraql.exceptions.HBaseFuncNotSupportedException;
import com.hydraql.util.Assert;
import com.hydraql.common.util.StringUtil;

/**
 * @author leojie 2022/12/3 13:11
 */
public enum RowKeyFunction {
  /**
   * row key
   */
  convert_to_int("convert_to_int", new ConvertIntRowKeyFunc()),
  convert_to_long("convert_to_long", new ConvertLongRowKeyFunc()),
  md5_prefix("md5_prefix", new Md5PrefixRowKeyFunc()), md5("md5", new Md5RowKeyFunc()),
  reverse("reverse", new ReverseRowKeyFunc());

  private final String funcName;
  private final RowKeyFunc<?> rowKeyFunc;

  RowKeyFunction(String funcName, RowKeyFunc<?> rowKeyFunc) {
    this.funcName = funcName;
    this.rowKeyFunc = rowKeyFunc;
  }

  public String getFuncName() {
    return funcName;
  }

  public RowKeyFunc<?> getRowKeyFunc() {
    return rowKeyFunc;
  }

  public static RowKeyFunction findRowKeyFunc(String funcName) {
    Assert.checkArgument(StringUtil.isNotBlank(funcName), "The function name must not ne empty");
    funcName = funcName.trim().toLowerCase();
    for (RowKeyFunction function : RowKeyFunction.values()) {
      if (funcName.equals(function.getFuncName())) {
        return function;
      }
    }
    throw new HBaseFuncNotSupportedException("The function " + funcName + " not supported.");
  }
}
