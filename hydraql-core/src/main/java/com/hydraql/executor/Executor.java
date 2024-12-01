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

package com.hydraql.executor;

import com.hydraql.result.GetResult;
import com.hydraql.result.MultiGetResult;
import com.hydraql.result.ScanResult;
import com.hydraql.conf.AbstractHqlConfiguration;
import com.hydraql.session.HqlConnection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;

import java.util.List;
import java.util.Map;

/**
 * @author leojie@apache.org 2024/8/18 22:58
 */
public interface Executor {
  AbstractHqlConfiguration getConfiguration();

  HqlConnection getConnection();

  <E> E get(Get get, Class<E> entityClass);

  GetResult get(Get get);

  <E> Map<byte[], E> get(List<Get> gets, Class<E> entityClass);

  MultiGetResult get(List<Get> gets);

  <E> List<E> scan(Scan scan, Class<E> entityClass);

  ScanResult scan(Scan scan);

  void put(Put put);

  void put(List<Put> puts);

  void delete(Delete delete);

  void delete(List<Delete> deletes);

  void mutate(Mutation mutation);

  void mutate(List<? extends Mutation> mutations);

  void close();

  boolean isClosed();
}
