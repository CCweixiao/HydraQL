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

package com.hydraql.util;

import com.hydraql.common.util.StringUtil;

/**
 * @author leojie@apache.org 2024/8/17 20:50
 */
public class TableNameUtils {
  private TableNameUtils() {
  }

  public static String format(String namespace, String tableName) {
    if (StringUtil.isBlank(namespace)) {
      return tableName;
    }
    if (namespace.equals(Constants.DEFAULT_NAMESPACE)) {
      return tableName;
    }
    return String.format("%s%s%s", namespace, Constants.NAMESPACE_DELIM, tableName);
  }
}
