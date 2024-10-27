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

package com.hydraql.adapter;

import com.hydraql.executor.BaseExecutor;
import com.hydraql.executor.ExecutorException;
import com.hydraql.metadata.HFieldInfo;
import org.apache.hadoop.hbase.client.Put;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leojie@apache.org 2024/9/7 23:04
 */
public class HQlExecutor extends BaseExecutor {

  public HQlExecutor(HQLTableImpl table) {
    super(table);
  }

  @Override
  public <E> Put buildPut(E entity) {
    if (null == entity) {
      return null;
    }
    if (null == table.getTableInfo()) {
      throw new ExecutorException(
          String.format("Table meta data of entity class %s is not set.", entity.getClass().getName()));
    }
    HFieldInfo.RowKey rowKey = table.getTableInfo().getRowKey();
    List<HFieldInfo.Qualifier> qualifiers = table.getTableInfo().getQualifiers();
    byte[] row = rowKey.getBytesValue(entity);
    Put put = new Put(row);
    qualifiers.forEach(qualifier -> {
      byte[] valueOfCol = qualifier.getBytesValue(entity);
      put.addColumn(qualifier.getFamily(), qualifier.getQualifier(), valueOfCol);
    });
    return put;
  }

  @Override
  public <E> List<Put> buildPuts(List<E> entities) {
    if (null == entities || entities.isEmpty()) {
      return Collections.emptyList();
    }
    return entities.stream().map(this::buildPut).collect(Collectors.toList());
  }
}
