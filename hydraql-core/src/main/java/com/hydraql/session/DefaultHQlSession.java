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

import com.hydraql.executor.BaseExecutor;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;

import java.util.List;

/**
 * @author leojie@apache.org 2024/9/5 21:41
 */
public class DefaultHQlSession implements HQlSession {
  private final BaseExecutor executor;

  public DefaultHQlSession(BaseExecutor executor) {
    this.executor = executor;
  }

  @Override
  public <E> E get(Get get) {
    return executor.get(get);
  }

  @Override
  public <E> List<E> gets(List<Get> gets) {
    return executor.gets(gets);
  }

  @Override
  public void put(Put put) {
    executor.put(put);
  }

  @Override
  public void puts(List<Put> puts) {
    executor.puts(puts);
  }

  @Override
  public void delete(Delete delete) {
    executor.delete(delete);
  }

  @Override
  public void deletes(List<Delete> deletes) {
    executor.deletes(deletes);
  }

  @Override
  public <E> void upsert(E t) {
    Put put = executor.buildPut(t);
    executor.put(put);
  }

  @Override
  public <E> void upsets(List<E> list) {
    List<Put> puts = executor.buildPuts(list);
    executor.puts(puts);
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
