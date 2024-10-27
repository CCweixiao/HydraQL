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
import com.hydraql.exceptions.HydraQlException;
import com.hydraql.executor.Executor;
import com.hydraql.mutator.WrapperBufferedMutator;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author leojie@apache.org 2024/4/8 17:44
 */
public class HedgedReadThresholdStrategy extends AbstractHedgedReadStrategy {
  private final int maxThreads;
  private final long thresholdMillis;

  public HedgedReadThresholdStrategy(Executor executor) {
    super(executor);
    this.maxThreads =
        executor.getConfiguration().getHedgedReadProperty().getHedgedReadThreadpoolSize();
    this.thresholdMillis =
        executor.getConfiguration().getHedgedReadProperty().getHedgedReadThresholdMillis();
  }

  protected HedgedReadThresholdStrategy(long thresholdMillis, Executor executor) {
    super(executor);
    this.maxThreads =
        executor.getConfiguration().getHedgedReadProperty().getHedgedReadThreadpoolSize();
    this.thresholdMillis = thresholdMillis;
  }

  @Override
  public <T> T execute(HTableAction<T, Table> action, boolean isWrite) {
    if (disableHedgedReadAction(isWrite)) {
      try {
        return executeOnPrefer(action);
      } catch (IOException e) {
        throw new HydraQlException(e);
      }
    }

    Callable<T> prefer = () -> executeOnPrefer(action);
    Callable<T> spare = () -> executeOnSpare(action);
    try {
      return execute(prefer, spare);
    } catch (RejectedExecutionException e) {
      try {
        return executeOnPrefer(action);
      } catch (IOException ex) {
        throw new HydraQlException(ex);
      }
    } catch (IOException e) {
      throw new HydraQlException(e);
    }
  }

  @Override
  public void mutate(MutatorAction<WrapperBufferedMutator> action) {
    if (disableHedgedReadAction(true)) {
      try {
        mutateOnPrefer(action);
      } catch (IOException e) {
        throw new HydraQlException(e);
      }
    }

    Callable<Void> prefer = () -> {
      mutateOnPrefer(action);
      return null;
    };
    Callable<Void> spare = () -> {
      mutateOnSpare(action);
      return null;
    };

    try {
      execute(prefer, spare);
    } catch (RejectedExecutionException e) {
      try {
        mutateOnPrefer(action);
      } catch (IOException ex) {
        throw new HydraQlException(ex);
      }
    } catch (IOException e) {
      throw new HydraQlException(e);
    }
  }

  private <T> T execute(Callable<T> prefer, Callable<T> spare) throws IOException {
    ArrayList<Future<T>> futures = new ArrayList<>();
    CompletionService<T> hedgedService = this.getHedgedReadService(maxThreads);

    Future<T> preferRequest = hedgedService.submit(prefer);
    futures.add(preferRequest);

    Future<T> future = null;
    try {
      if (thresholdMillis <= 0) {
        future = hedgedService.poll();
      } else {
        future = hedgedService.poll(thresholdMillis, TimeUnit.MILLISECONDS);
      }
      if (future != null) {
        return future.get();
      }
    } catch (ExecutionException e) {
      futures.remove(future);
    } catch (InterruptedException e) {
      throw new IOException("Interrupted while waiting for reading/writing task", e);
    }

    Future<T> spareRequest = hedgedService.submit(spare);
    futures.add(spareRequest);

    try {
      T result = getFirstToComplete(hedgedService, futures);
      cancelAll(futures);
      return result;
    } catch (InterruptedException e) {
      // todo Ignore and retry
    }
    return null;
  }
}
