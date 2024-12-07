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

package com.hydraql.result.handler;

import com.hydraql.exceptions.HydraQlException;
import com.hydraql.metadata.HFieldInfo;
import com.hydraql.metadata.HTableInfo;
import org.apache.hadoop.hbase.client.Result;

import java.util.List;

/**
 * @author leojie@apache.org 2024/9/2 21:27
 */
public class GetResultToEntityClassHandler<E> implements GetResultHandler<E> {
  private final HTableInfo tableInfo;

  public GetResultToEntityClassHandler(HTableInfo tableInfo) {
    this.tableInfo = tableInfo;
  }

  @Override
  public <R> E handleResult(R r) throws Exception {
    if (null == tableInfo) {
      throw new HydraQlException("Table info is null");
    }
    if (r == null) {
      return null;
    }
    Result result = (Result) r;
    if (result.isEmpty()) {
      return null;
    }
    E entity = tableInfo.newInstance();
    HFieldInfo.RowKey rowKey = tableInfo.getRowKey();
    rowKey.setBytesValue(entity, result.getRow());
    List<HFieldInfo.Qualifier> qualifiers = tableInfo.getQualifiers();
    for (HFieldInfo.Qualifier qualifier : qualifiers) {
      byte[] value = result.getValue(qualifier.getFamily(), qualifier.getQualifier());
      qualifier.setBytesValue(entity, value);
    }
    return entity;
  }
}
