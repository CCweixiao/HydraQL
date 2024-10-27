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

import com.hydraql.HQLTable;
import com.hydraql.conf.AbstractHQLConfiguration;
import com.hydraql.handler.ResultHandler;
import com.hydraql.handler.ResultListHandler;
import com.hydraql.handler.ResultSetHandler;
import com.hydraql.session.HQlConnection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author leojie@apache.org 2024/8/18 23:10
 */
public abstract class BaseExecutor implements Executor {
  private static final Logger LOG = LoggerFactory.getLogger(BaseExecutor.class);

  protected final AbstractHQLConfiguration configuration;
  protected HQlConnection connection;
  protected final HQLTable table;
  private boolean closed;

  protected BaseExecutor(HQLTable table) {
    this.table = table;
    this.configuration = table.getConfiguration();
    this.connection = table.getConnection();
    closed = false;
  }

  @Override
  public AbstractHQLConfiguration getConfiguration() {
    return configuration;
  }

  @Override
  public HQlConnection getConnection() {
    if (closed) {
      throw new ExecutorException("HydraQL executor was closed.");
    }
    return connection;
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

  public abstract <E> Put buildPut(E entity);

  public abstract <E> List<Put> buildPuts(List<E> entities);

  @Override
  public <E> E get(Get get) {
    if (null == get) {
      return null;
    }
    ResultHandler<E> resultHandler = this.table.getResultHandler();
    return this.execute(table -> resultHandler.handleResult(table.get(get)), false);
  }

  @Override
  public void put(Put put) {
    if (null == put) {
      return;
    }
    this.execute(table -> {
      table.put(put);
      return null;
    }, true);
  }

  @Override
  public void delete(Delete delete) {
    if (null == delete) {
      return;
    }
    this.execute(table -> {
      table.delete(delete);
      return null;
    }, true);
  }

  @Override
  public <E> List<E> gets(List<Get> gets) {
    if (null == gets || gets.isEmpty()) {
      return Collections.emptyList();
    }
    ResultListHandler<E> resultListHandler = this.table.getResultListHandler();
    return this.execute(table -> resultListHandler.handleResult(table.get(gets)), false);
  }

  @Override
  public void puts(List<Put> puts) {
    if (null == puts || puts.isEmpty()) {
      return;
    }
    this.execute(table -> {
      table.put(puts);
      return null;
    }, true);
  }

  @Override
  public void deletes(List<Delete> deletes) {
    if (null == deletes || deletes.isEmpty()) {
      return;
    }
    this.execute(table -> {
      table.delete(deletes);
      return null;
    }, true);
  }

  @Override
  public void mutate(Mutation mutation) {
    if (null == mutation) {
      return;
    }
    this.mutate(mutator -> mutator.mutate(mutation));
  }

  @Override
  public void mutates(List<? extends Mutation> mutations) {
    if (null == mutations || mutations.isEmpty()) {
      return;
    }
    this.mutate(mutator -> mutator.mutate(mutations));
  }

  @Override
  public <E> List<E> scan(Scan scan) {
    return this.execute(table -> {
      if (null == scan) {
        return null;
      }
      ResultSetHandler<E> resultSetHandler = this.table.getResultSetHandler();
      return resultSetHandler.handleResult(resultSetHandler);
    }, false);
  }
}
