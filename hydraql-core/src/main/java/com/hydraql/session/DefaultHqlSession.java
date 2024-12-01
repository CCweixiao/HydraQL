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

package com.hydraql.session;

import com.hydraql.result.GetResult;
import com.hydraql.result.MultiGetResult;
import com.hydraql.result.ScanResult;
import com.hydraql.executor.BaseExecutor;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;

import java.util.List;
import java.util.Map;

/**
 * @author leojie@apache.org 2024/9/5 21:41
 */
public class DefaultHqlSession implements HqlSession {
  private final BaseExecutor executor;

  public DefaultHqlSession(BaseExecutor executor) {
    this.executor = executor;
  }

  @Override
  public <E> E get(Get get, Class<E> entityClass) {
    return executor.get(get, entityClass);
  }

  @Override
  public GetResult get(Get get) {
    return executor.get(get);
  }

  @Override
  public <E> Map<byte[], E> get(List<Get> gets, Class<E> entityClass) {
    return executor.get(gets, entityClass);
  }

  @Override
  public MultiGetResult get(List<Get> gets) {
    return executor.get(gets);
  }

  @Override
  public <E> List<E> scan(Scan scan, Class<E> entityClass) {
    return executor.scan(scan, entityClass);
  }

  @Override
  public ScanResult scan(Scan scan) {
    return executor.scan(scan);
  }

  @Override
  public void put(Put put) {
    executor.put(put);
  }

  @Override
  public void put(List<Put> puts) {
    executor.put(puts);
  }

  @Override
  public <E> void put(E entity, Class<E> entityClass) {
    Put put = executor.buildPut(entity, entityClass);
    executor.put(put);
  }

  @Override
  public <E> void put(List<E> entities, Class<E> entityClass) {
    List<Put> puts = executor.buildPuts(entities, entityClass);
    executor.put(puts);
  }

  @Override
  public void delete(Delete delete) {
    executor.delete(delete);
  }

  @Override
  public void delete(List<Delete> deletes) {
    executor.delete(deletes);
  }

  @Override
  public void close() {
    if (null != executor) {
      if (executor.isClosed()) {
        return;
      }
      executor.close();
    }
  }
}
