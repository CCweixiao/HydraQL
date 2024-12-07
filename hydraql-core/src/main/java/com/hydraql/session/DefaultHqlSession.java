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

import com.hydraql.executor.HqlExecutor;
import com.hydraql.result.GetResult;
import com.hydraql.result.MultiGetResult;
import com.hydraql.result.ScanResult;
import com.hydraql.wrapper.BaseScanWrapper;
import com.hydraql.wrapper.DeleteWrapper;
import com.hydraql.wrapper.GetWrapper;
import com.hydraql.wrapper.MultiDeleteWrapper;
import com.hydraql.wrapper.MultiGetWrapper;
import com.hydraql.wrapper.MultiPutWrapper;
import com.hydraql.wrapper.PutWrapper;

import java.util.List;

/**
 * @author leojie@apache.org 2024/9/5 21:41
 */
public class DefaultHqlSession implements HqlSession {
  private final HqlExecutor executor;

  public DefaultHqlSession(HqlExecutor executor) {
    this.executor = executor;
  }

  @Override
  public <E> E get(GetWrapper get, Class<E> entityClass) {
    return executor.get(get, entityClass);
  }

  @Override
  public GetResult get(GetWrapper get) {
    return executor.get(get);
  }

  @Override
  public <E> MultiGetResult<E> multiGet(MultiGetWrapper gets, Class<E> entityClass) {
    return executor.get(gets, entityClass);
  }

  @Override
  public MultiGetResult<GetResult> multiGet(MultiGetWrapper gets) {
    return executor.get(gets);
  }

  @Override
  public <E> List<E> scan(BaseScanWrapper scan, Class<E> entityClass) {
    return executor.scan(scan.unwrapper(), entityClass);
  }

  @Override
  public ScanResult scan(BaseScanWrapper scan) {
    return executor.scan(scan.unwrapper());
  }

  @Override
  public void put(PutWrapper put) {
    executor.put(put.unwrapper());
  }

  @Override
  public void multiPut(MultiPutWrapper put) {
    executor.put(put.unwrapper());
  }

  @Override
  public <E> void put(E entity) {
    executor.put(entity);
  }

  @Override
  public <E> void multiPut(List<E> entities) {
    executor.multiPut(entities);
  }

  @Override
  public void delete(DeleteWrapper delete) {
    executor.delete(delete.unwrapper());
  }

  @Override
  public void delete(MultiDeleteWrapper deletes) {
    executor.delete(deletes.unwrapper());
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
