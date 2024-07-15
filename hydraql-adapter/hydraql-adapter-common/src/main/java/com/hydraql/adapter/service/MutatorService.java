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

package com.hydraql.adapter.service;

import com.hydraql.adapter.context.HTableContext;
import com.hydraql.common.schema.HBaseTableSchema;
import com.hydraql.common.schema.ReflectFactory;

import java.util.List;
import java.util.Map;

/**
 * @author leojie@apache.org 2024/4/14 21:18
 */
public interface MutatorService {
  default void saveWithBuffer(String tableName, String rowKey, Map<String, Object> data) {
    if (data == null || data.isEmpty()) {
      return;
    }
    saveWithBuffer(HTableContext.createDefault(tableName), rowKey, data);
  }

  void saveWithBuffer(HTableContext tableContext, String rowKey, Map<String, Object> data);

  default void saveBatchWithBuffer(String tableName, Map<String, Map<String, Object>> data) {
    if (data == null || data.isEmpty()) {
      return;
    }
    saveWithBuffer(HTableContext.createDefault(tableName), data);
  }

  void saveBatchWithBuffer(HTableContext tableContext, Map<String, Map<String, Object>> data);

  default <T> void saveWithBuffer(T t) {
    if (t == null) {
      return;
    }
    saveWithBuffer(HTableContext.createDefault(getTableName(t)), t);
  }

  <T> void saveWithBuffer(HTableContext tableContext, T t);

  default <T> void saveBatchWithBuffer(List<T> list) {
    saveBatchWithBuffer(HTableContext.createDefault(getTableName(list.get(0))), list);
  }

  <T> void saveBatchWithBuffer(HTableContext tableContext, List<T> list);

  default void deleteWithBuffer(String tableName, String rowKey) {
    deleteWithBuffer(HTableContext.createDefault(tableName), rowKey);
  }

  void deleteWithBuffer(HTableContext tableContext, String rowKey);

  default void deleteWithBuffer(String tableName, String rowKey, String familyName) {
    deleteWithBuffer(HTableContext.createDefault(tableName), rowKey, familyName);
  }

  void deleteWithBuffer(HTableContext tableContext, String rowKey, String familyName);

  default void deleteWithBuffer(String tableName, String rowKey, String familyName,
      List<String> qualifiers) {
    deleteWithBuffer(HTableContext.createDefault(tableName), rowKey, familyName, qualifiers);
  }

  void deleteWithBuffer(HTableContext tableContext, String rowKey, String familyName,
      List<String> qualifiers);

  default void deleteWithBuffer(String tableName, String rowKey, String familyName,
      String... qualifiers) {
    deleteWithBuffer(HTableContext.createDefault(tableName), rowKey, familyName, qualifiers);
  }

  void deleteWithBuffer(HTableContext tableContext, String rowKey, String familyName,
      String... qualifiers);

  default void deleteBatchWithBuffer(String tableName, List<String> rowKeys) {
    deleteBatchWithBuffer(HTableContext.createDefault(tableName), rowKeys);
  }

  void deleteBatchWithBuffer(HTableContext tableContext, List<String> rowKeys);

  default void deleteBatchWithBuffer(String tableName, List<String> rowKeys, String familyName) {
    deleteBatchWithBuffer(HTableContext.createDefault(tableName), rowKeys, familyName);
  }

  void deleteBatchWithBuffer(HTableContext tableContext, List<String> rowKeys, String familyName);

  default void deleteBatchWithBuffer(String tableName, List<String> rowKeys, String familyName,
      List<String> qualifiers) {
    deleteBatchWithBuffer(HTableContext.createDefault(tableName), rowKeys, familyName, qualifiers);
  }

  void deleteBatchWithBuffer(HTableContext tableContext, List<String> rowKeys, String familyName,
      List<String> qualifiers);

  default void deleteBatchWithBuffer(String tableName, List<String> rowKeys, String familyName,
      String... qualifiers) {
    deleteBatchWithBuffer(HTableContext.createDefault(tableName), rowKeys, familyName, qualifiers);
  }

  void deleteBatchWithBuffer(HTableContext tableContext, List<String> rowKeys, String familyName,
      String... qualifiers);

  default <T> String getTableName(T t) {
    final Class<?> clazz = t.getClass();
    HBaseTableSchema tableMeta = ReflectFactory.getInstance().register(clazz);
    return tableMeta.getTableName();
  }
}
