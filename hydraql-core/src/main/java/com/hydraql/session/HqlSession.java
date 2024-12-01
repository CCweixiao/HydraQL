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
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;

import java.io.Closeable;
import java.util.List;
import java.util.Map;

/**
 * The primary Java interface for working with HydraQl. Through this interface you can execute
 * get/put/scan.
 * @author leojie@apache.org 2024/8/18 19:29
 */
public interface HqlSession extends Closeable {
  <E> E get(Get get, Class<E> entityClass);

  GetResult get(Get get);

  <E> Map<byte[], E> get(List<Get> gets, Class<E> entityClass);

  MultiGetResult get(List<Get> gets);

  <E> List<E> scan(Scan scan, Class<E> entityClass);

  ScanResult scan(Scan scan);

  void put(Put put);

  void put(List<Put> puts);

  <E> void put(E entity, Class<E> entityClass);

  <E> void put(List<E> entities, Class<E> entityClass);

  void delete(Delete delete);

  void delete(List<Delete> deletes);

  void close();
}