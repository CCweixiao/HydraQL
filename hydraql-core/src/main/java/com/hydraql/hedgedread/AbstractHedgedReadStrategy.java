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

package com.hydraql.hedgedread;

import com.hydraql.action.MutatorAction;
import com.hydraql.action.HTableAction;
import com.hydraql.executor.Executor;
import com.hydraql.mutator.WrapperBufferedMutator;
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
public abstract class AbstractHedgedReadStrategy implements HedgedReadStrategy {
  protected final Executor executor;

  protected AbstractHedgedReadStrategy(Executor executor) {
    this.executor = executor;
  }

  protected <T> T executeOnPrefer(HTableAction<T, Table> action) throws IOException {
    try {
      Table table = executor.getConnection().getTable();
      return action.execute(table);
    } catch (Throwable throwable) {
      throw new IOException(throwable);
    }
  }

  protected <T> T executeOnSpare(HTableAction<T, Table> action) throws IOException {
    try {
      Table table = executor.getConnection().getHedgedTable();
      return action.execute(table);
    } catch (Throwable throwable) {
      throw new IOException(throwable);
    }
  }

  protected void mutateOnPrefer(MutatorAction<WrapperBufferedMutator> action)
      throws IOException {
    try {
      WrapperBufferedMutator mutator = executor.getConnection().getWrapperBufferedMutator();
      action.execute(mutator);
    } catch (Throwable throwable) {
      throw new IOException(throwable);
    }
  }

  protected void mutateOnSpare(MutatorAction<WrapperBufferedMutator> action)
      throws IOException {
    try {
      WrapperBufferedMutator mutator = executor.getConnection().getHedgedReadWrapperBufferedMutator();
      action.execute(mutator);
    } catch (Throwable throwable) {
      throw new IOException(throwable);
    }
  }

  protected <T> CompletionService<T> getHedgedReadService(int maxThreads) {
    //TODO 是否考虑并发优化
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

  protected boolean disableHedgedReadAction(boolean isWrite) {
    if (!isWrite) {
      return false;
    }
    return executor.getConfiguration().getHedgedReadProperty().isHedgedReadWriteDisable();
  }
}
