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

package com.hydraql.common.query;

import com.hydraql.common.util.StringUtil;

import java.util.List;

/**
 * @author leojie 2023/7/20 19:50
 */
@Deprecated
public class FamilyQualifierUtil {
  /**
   * 判断列簇名满足条件，需要筛选的字段列表未指定
   * @param familyName 列簇名
   * @param qualifiers 需要筛选的字段列表
   * @return 最终数据
   */
  public static boolean familyNameOnly(String familyName, List<String> qualifiers) {
    return StringUtil.isNotBlank(familyName) && (qualifiers == null || qualifiers.isEmpty());
  }

  /**
   * 判断列簇名和需要筛选的字段列表同时成立
   * @param familyName 列簇名
   * @param qualifiers 需要筛选的字段列表
   * @return 最终数据
   */
  public static boolean familyAndColumnNames(String familyName, List<String> qualifiers) {
    return StringUtil.isNotBlank(familyName) && (qualifiers != null && !qualifiers.isEmpty());
  }
}
