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

import com.hydraql.HqlTable;
import com.hydraql.action.HTableAction;
import com.hydraql.action.MutatorAction;
import com.hydraql.conf.HqlConfiguration;
import com.hydraql.hedgedread.HedgedReadStrategy;
import com.hydraql.hedgedread.factory.HedgedReadStrategyDefaultFactory;
import com.hydraql.metadata.HTableInfo;
import com.hydraql.mutator.WrapperBufferedMutator;
import com.hydraql.result.GetResult;
import com.hydraql.result.MultiGetResult;
import com.hydraql.result.ScanResult;
import com.hydraql.result.handler.DefaultGetResultHandler;
import com.hydraql.result.handler.DefaultMultiGetResultHandler;
import com.hydraql.result.handler.DefaultScanResultHandler;
import com.hydraql.result.handler.GetResultHandler;
import com.hydraql.result.handler.GetResultToEntityClassHandler;
import com.hydraql.result.handler.MultiGetResultHandler;
import com.hydraql.result.handler.MultiGetResultToEntityClassHandler;
import com.hydraql.result.handler.ScanResultHandler;
import com.hydraql.result.handler.ScanResultToEntityClassHandler;
import com.hydraql.session.HqlConnection;
import com.hydraql.wrapper.GetWrapper;
import com.hydraql.wrapper.MultiGetWrapper;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leojie@apache.org 2024/8/18 23:10
 */
public class HqlExecutor implements Executor {
  private static final Logger LOG = LoggerFactory.getLogger(HqlExecutor.class);

  private final HqlConfiguration configuration;
  private final HqlTable hqlTable;

  private final GetResultHandler<GetResult> defaultGetResultHandler;
  private final MultiGetResultHandler<MultiGetResult<GetResult>> defaultMultiGetResultHandler;
  private final ScanResultHandler<ScanResult> defaultScanResultHandler;

  private HqlConnection connection;
  private HTableInfo tableInfo = null;

  private GetResultHandler<?> getResultHandler = null;
  private MultiGetResultToEntityClassHandler<MultiGetResult<?>> multiGetResultHandler = null;
  private ScanResultToEntityClassHandler<?> scanResultHandler = null;

  private boolean closed;

  public HqlExecutor(HqlTable hqlTable) {
    this.hqlTable = hqlTable;
    this.configuration = hqlTable.getConfiguration();
    this.connection = hqlTable.getConnection();
    this.defaultGetResultHandler = new DefaultGetResultHandler();
    this.defaultMultiGetResultHandler = new DefaultMultiGetResultHandler();
    this.defaultScanResultHandler = new DefaultScanResultHandler();
    closed = false;
  }

  @Override
  public HqlConfiguration getConfiguration() {
    return configuration;
  }

  @Override
  public HqlConnection getConnection() {
    if (closed) {
      throw new ExecutorException("HydraQL executor was closed.");
    }
    return connection;
  }

  private <E> void initTableEntityClass(Class<E> entityClass) {
    this.hqlTable.initTableEntityClass(entityClass);
    this.tableInfo = this.hqlTable.getTableInfo();
  }

  private HTableInfo getTableInfo() {
    if (this.tableInfo == null) {
      throw new ExecutorException(
          String.format("Table metadata of entity class [%s] registration failed.",
            this.hqlTable.getTableEntityClass().getName()));
    }
    return tableInfo;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <E> E get(GetWrapper get, Class<E> entityClass) {
    this.initTableEntityClass(entityClass);
    return this.execute(
      table -> (E) this.getGetResultHandler().handleResult(table.get(get.unwrapper())), false);
  }

  @Override
  public GetResult get(GetWrapper get) {
    return this.execute(table -> this.defaultGetResultHandler
        .handleResult(table.get(get.unwrapper()), get.rowGenerationStrategy()),
      false);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <E> MultiGetResult<E> get(MultiGetWrapper gets, Class<E> entityClass) {
    this.initTableEntityClass(entityClass);
    return (MultiGetResult<E>) this.execute(table -> this.getMultiGetResultHandler()
        .handleResult(table.get(gets.unwrapper()), gets.getStrategy()),
      false);
  }

  @Override
  public MultiGetResult<GetResult> get(MultiGetWrapper gets) {
    return this.execute(table -> this.defaultMultiGetResultHandler
        .handleResult(table.get(gets.unwrapper()), gets.getStrategy()),
      false);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <E> List<E> scan(Scan scan, Class<E> entityClass) {
    this.initTableEntityClass(entityClass);
    return this.execute(
      table -> (List<E>) this.getScanResultHandler().handleResult(table.getScanner(scan)), false);
  }

  @Override
  public ScanResult scan(Scan scan) {
    return this.execute(table -> this.defaultScanResultHandler.handleResult(table.getScanner(scan)),
      false);
  }

  @Override
  public void put(Put put) {
    if (put == null) {
      return;
    }
    this.execute(table -> {
      table.put(put);
      return null;
    }, true);
  }

  @Override
  public void delete(Delete delete) {
    if (delete == null) {
      return;
    }
    this.execute(table -> {
      table.delete(delete);
      return null;
    }, true);
  }

  @Override
  public void put(List<Put> puts) {
    if (puts == null || puts.isEmpty()) {
      return;
    }
    this.execute(table -> {
      table.put(puts);
      return null;
    }, true);
  }

  @Override
  public <E> void put(E entity) {
    this.put(buildPut(entity));
  }

  @Override
  public <E> void multiPut(List<E> entities) {
    this.put(buildPuts(entities));
  }

  private <E> Put buildPut(E entity) {
    if (null == entity) {
      return null;
    }
    this.initTableEntityClass(entity.getClass());
    return buildSinglePut(entity);
  }

  private <E> List<Put> buildPuts(List<E> entities) {
    if (null == entities || entities.isEmpty()) {
      return Collections.emptyList();
    }
    this.initTableEntityClass(entities.get(0).getClass());
    return entities.stream().map(this::buildSinglePut).collect(Collectors.toList());
  }

  private <E> Put buildSinglePut(E entity) {
    byte[] row = this.getTableInfo().getRowKey().getBytesValue(entity);
    Put put = new Put(row);
    this.getTableInfo().getQualifiers().forEach(qualifier -> {
      byte[] value = qualifier.getBytesValue(entity);
      put.addColumn(qualifier.getFamily(), qualifier.getQualifier(), value);
    });
    return put;
  }

  @Override
  public void delete(List<Delete> deletes) {
    if (deletes == null || deletes.isEmpty()) {
      return;
    }
    this.execute(table -> {
      table.delete(deletes);
      return null;
    }, true);
  }

  @Override
  public void mutate(Mutation mutation) {
    if (mutation == null) {
      return;
    }
    this.mutate(mutator -> mutator.mutate(mutation));
  }

  @Override
  public void mutate(List<? extends Mutation> mutations) {
    if (mutations == null || mutations.isEmpty()) {
      return;
    }
    this.mutate(mutator -> mutator.mutate(mutations));
  }

  private HedgedReadStrategy getHedgedReadStrategy() {
    return HedgedReadStrategyDefaultFactory.newInstance(this).create();
  }

  private <E> E execute(HTableAction<E, Table> action, boolean isWrite) {
    return this.getHedgedReadStrategy().execute(action, isWrite);
  }

  private void mutate(MutatorAction<WrapperBufferedMutator> action) {
    this.getHedgedReadStrategy().mutate(action);
  }

  @Override
  public void close() {
    if (null != connection) {
      try {
        connection.close();
      } catch (IOException e) {
        // Ignore. There's nothing that can be done at this point.
        LOG.warn("Unexpected exception on closing hydraql connection.  Cause: ", e);
      } finally {
        connection = null;
        closed = true;
      }
    }
  }

  @Override
  public boolean isClosed() {
    return closed;
  }

  private GetResultHandler<?> getGetResultHandler() {
    if (this.getResultHandler == null) {
      this.getResultHandler = new GetResultToEntityClassHandler<>(this.getTableInfo());
    }
    return this.getResultHandler;
  }

  private MultiGetResultToEntityClassHandler<MultiGetResult<?>> getMultiGetResultHandler() {
    if (this.multiGetResultHandler == null) {
      this.multiGetResultHandler = new MultiGetResultToEntityClassHandler<>(this.getTableInfo());
    }
    return this.multiGetResultHandler;
  }

  private ScanResultToEntityClassHandler<?> getScanResultHandler() {
    if (this.scanResultHandler == null) {
      this.scanResultHandler = new ScanResultToEntityClassHandler<>(this.getTableInfo());
    }
    return this.scanResultHandler;
  }
}
