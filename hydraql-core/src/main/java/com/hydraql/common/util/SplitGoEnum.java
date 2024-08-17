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

package com.hydraql.common.util;

/**
 * @author leojie 2020/10/7 9:48 下午
 */
public enum SplitGoEnum {
  /**
   * 预分区策略，十六进制前缀
   */
  HEX_STRING_SPLIT("HexStringSplit"),
  /**
   * 十进制前缀
   */
  DECIMAL_STRING_SPLIT("DecimalStringSplit"),
  /**
   * 随机字符串前缀
   */
  UNIFORM_SPLIT("UniformSplit");

  private final String splitGo;

  SplitGoEnum(String splitGo) {
    this.splitGo = splitGo;
  }

  public String getSplitGo() {
    return splitGo;
  }

  public static SplitGoEnum getSplitGoEnum(String splitGo) {
    for (SplitGoEnum splitGoEnum : SplitGoEnum.values()) {
      if (splitGoEnum.splitGo.equals(splitGo)) {
        return splitGoEnum;
      }
    }
    return null;
  }

}
