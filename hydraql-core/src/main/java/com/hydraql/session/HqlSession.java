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
import com.hydraql.wrapper.BaseScanWrapper;
import com.hydraql.wrapper.DeleteWrapper;
import com.hydraql.wrapper.GetWrapper;
import com.hydraql.wrapper.MultiDeleteWrapper;
import com.hydraql.wrapper.MultiGetWrapper;
import com.hydraql.wrapper.MultiPutWrapper;
import com.hydraql.wrapper.PutWrapper;

import java.io.Closeable;
import java.util.List;

/**
 * The primary Java interface for working with HydraQl. Through this interface you can execute
 * get/put/scan.
 * @author leojie@apache.org 2024/8/18 19:29
 */
public interface HqlSession extends Closeable {
  <E> E get(GetWrapper get, Class<E> entityClass);

  GetResult get(GetWrapper get);

  <E> MultiGetResult<E> multiGet(MultiGetWrapper gets, Class<E> entityClass);

  MultiGetResult<GetResult> multiGet(MultiGetWrapper gets);

  <E> List<E> scan(BaseScanWrapper scan, Class<E> entityClass);

  ScanResult scan(BaseScanWrapper scan);

  void put(PutWrapper put);

  void multiPut(MultiPutWrapper put);

  <E> void put(E entity);

  <E> void multiPut(List<E> entities);

  void delete(DeleteWrapper delete);

  void delete(MultiDeleteWrapper deletes);

  void close();
}