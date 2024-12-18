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

package com.hydraql.adapter.hedgedread;

import com.hydraql.adapter.HBaseClientConf;
import com.hydraql.adapter.WrapperBufferedMutator;
import com.hydraql.adapter.context.HTableContext;
import com.hydraql.adapter.service.AbstractHTableService;
import com.hydraql.action.MutatorAction;
import com.hydraql.action.HTableAction;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;

/**
 * @author leojie@apache.org 2024/4/12 22:31
 */
@Deprecated
public abstract class AbstractHedgedReadStrategy implements HedgedReadStrategy {
  private final AbstractHTableService tableService;

  protected AbstractHedgedReadStrategy(AbstractHTableService tableService) {
    this.tableService = tableService;
  }

  protected <T> T executeOnPrefer(String tableName, HTableAction<T, Table> action)
      throws IOException {
    try (Table table = this.tableService.getConnection().getTable(TableName.valueOf(tableName))) {
      return action.execute(table);
    } catch (Throwable throwable) {
      throw new IOException(throwable);
    }
  }

  protected <T> T executeOnSpare(String tableName, HTableAction<T, Table> action)
      throws IOException {
    try (Table table =
        this.tableService.getHedgedReadConnection().getTable(TableName.valueOf(tableName))) {
      return action.execute(table);
    } catch (Throwable throwable) {
      throw new IOException(throwable);
    }
  }

  protected void executeOnPreferWithBuffer(HTableContext tableContext,
      MutatorAction<WrapperBufferedMutator> action) throws IOException {
    try {
      WrapperBufferedMutator mutator = this.tableService.getWrapperBufferedMutator(tableContext);
      action.execute(mutator);
    } catch (Throwable throwable) {
      throw new IOException(throwable);
    }
  }

  protected void executeOnSpareWithBuffer(HTableContext tableContext,
      MutatorAction<WrapperBufferedMutator> action) throws IOException {
    try {
      WrapperBufferedMutator mutator =
          this.tableService.getHedgedReadWrapperBufferedMutator(tableContext);
      action.execute(mutator);
    } catch (Throwable throwable) {
      throw new IOException(throwable);
    }
  }

  protected <T> CompletionService<T> getHedgedReadService(int maxThreads) {
    return new ExecutorCompletionService<>(HedgedReadExecutor.create().getExecutor(maxThreads));
  }

  protected <T> T getFirstToComplete(CompletionService<T> hedgedService, List<Future<T>> futures)
      throws InterruptedException {
    if (futures.isEmpty()) {
      throw new InterruptedException("let's retry.");
    }

    Future<T> future = null;
    try {
      future = hedgedService.take();
      T t = future.get();
      futures.remove(future);
      return t;
    } catch (ExecutionException | CancellationException e) {
      futures.remove(future);
    }

    throw new InterruptedException("let's retry.");
  }

  protected <T> void cancelAll(List<Future<T>> futures) {
    for (Future<T> future : futures) {
      future.cancel(false);
    }
  }

  protected HBaseClientConf getHBaseClientConf() {
    return tableService.getHBaseClientConf();
  }
}
