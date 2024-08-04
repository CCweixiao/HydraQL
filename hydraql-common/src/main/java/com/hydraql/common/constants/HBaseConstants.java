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

package com.hydraql.common.constants;

import com.hydraql.common.exception.HBaseOperationsException;
import com.hydraql.common.util.StringUtil;

/**
 * @author leojie 2020/9/25 10:37 下午
 */
public class HBaseConstants {
  public static final String META_TABLE_NAME = "hbase:meta";
  public static final String DEFAULT_SYS_TABLE_NAMESPACE = "hbase";
  public static final String TABLE_NAME_SPLIT_CHAR = ":";
  public static final String FAMILY_QUALIFIER_SEPARATOR = ":";
  public static final String DEFAULT_NAMESPACE_NAME = "default";
  public static final Integer DEFAULT_TTL = 2147483647;
  public static final String DEFAULT_COMPRESSION_TYPE = "NONE";
  public static final Integer DEFAULT_MAX_VERSIONS = 1;
  public static final Integer DEFAULT_REPLICATION_SCOPE = 0;

  public static final Integer DISABLE_REPLICATION_SCOPE = 0;
  public static final Integer ENABLE_REPLICATION_SCOPE = 1;
  public static final String SIMPLE_AUTH = "simple";
  public static final String KERBEROS_AUTH = "kerberos";
  public static final String ENABLED = "ENABLED";
  public static final String DISABLED = "DISABLED";

  public static String getFullTableName(String tableName) {
    if (StringUtil.isBlank(tableName)) {
      throw new IllegalArgumentException("The table name is not empty.");
    }

    if (tableName.contains(TABLE_NAME_SPLIT_CHAR)) {
      return tableName;
    } else {
      return DEFAULT_NAMESPACE_NAME.concat(TABLE_NAME_SPLIT_CHAR).concat(tableName);
    }
  }

  public static String getNamespaceName(String tableName) {
    if (tableName.contains(TABLE_NAME_SPLIT_CHAR)) {
      String[] ns = tableName.split(TABLE_NAME_SPLIT_CHAR);
      if (ns.length == 2) {
        return tableName.split(TABLE_NAME_SPLIT_CHAR)[0];
      } else {
        throw new IllegalArgumentException(String
            .format("The table name format of %s does not conform to specification.", tableName));
      }
    } else {
      return DEFAULT_NAMESPACE_NAME;
    }
  }

  public static String getTableName(String tableName) {
    if (tableName.contains(TABLE_NAME_SPLIT_CHAR)) {
      return tableName.split(TABLE_NAME_SPLIT_CHAR)[1];
    } else {
      return tableName;
    }
  }

  public static String getFullTableName(String namespaceName, String tableName) {
    if (StringUtil.isBlank(tableName)) {
      throw new HBaseOperationsException("The table name is not empty.");
    }
    if (tableName.contains(TABLE_NAME_SPLIT_CHAR)) {
      return tableName;
    } else {
      if (StringUtil.isBlank(namespaceName)) {
        return DEFAULT_NAMESPACE_NAME + TABLE_NAME_SPLIT_CHAR + tableName;
      }
      return namespaceName + TABLE_NAME_SPLIT_CHAR + tableName;
    }
  }

  public static String getColumnName(String family, String column) {
    if (StringUtil.isBlank(column)) {
      throw new IllegalArgumentException("The column name cannot empty.");
    }
    if (StringUtil.isBlank(family)) {
      return column;
    }
    return family.concat(FAMILY_QUALIFIER_SEPARATOR).concat(column);
  }

  private HBaseConstants() {

  }
}
