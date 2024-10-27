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

import com.hydraql.action.MutatorAction;
import com.hydraql.action.HTableAction;
import com.hydraql.hedgedread.HedgedReadStrategy;
import com.hydraql.hedgedread.factory.HedgedReadStrategyDefaultFactory;
import com.hydraql.mutator.WrapperBufferedMutator;
import com.hydraql.conf.AbstractHQLConfiguration;
import com.hydraql.session.HQlConnection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;

import java.util.List;

/**
 * @author leojie@apache.org 2024/8/18 22:58
 */
public interface Executor {
  AbstractHQLConfiguration getConfiguration();

  HQlConnection getConnection();

  default HedgedReadStrategy getHedgedReadStrategy() {
    return new HedgedReadStrategyDefaultFactory(this).create();
  }

  default <E> E execute(HTableAction<E, Table> action, boolean isWrite) {
    return this.getHedgedReadStrategy().execute(action, isWrite);
  }

  <E> E get(Get get);

  <E> List<E> gets(List<Get> gets);

  <E> List<E> scan(Scan scan);

  void put(Put put);

  void puts(List<Put> puts);

  void delete(Delete delete);

  void deletes(List<Delete> deletes);

  default void mutate(MutatorAction<WrapperBufferedMutator> action) {
    this.getHedgedReadStrategy().mutate(action);
  }

  void mutate(Mutation mutation);

  void mutates(List<? extends Mutation> mutations);

  void close();

  boolean isClosed();
}
