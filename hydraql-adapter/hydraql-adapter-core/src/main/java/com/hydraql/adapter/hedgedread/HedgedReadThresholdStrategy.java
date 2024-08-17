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

import com.hydraql.adapter.WrapperBufferedMutator;
import com.hydraql.adapter.context.HTableContext;
import com.hydraql.adapter.service.AbstractHTableService;
import com.hydraql.core.callback.MutatorCallback;
import com.hydraql.core.callback.TableCallback;
import com.hydraql.core.exceptions.HTableServiceException;
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

  public HedgedReadThresholdStrategy(AbstractHTableService tableService) {
    super(tableService);
    this.maxThreads = this.getHBaseClientConf().getHedgedReadThreadpoolSize();
    this.thresholdMillis = this.getHBaseClientConf().getHedgedReadThresholdMillis();
  }

  protected HedgedReadThresholdStrategy(long thresholdMillis, AbstractHTableService tableService) {
    super(tableService);
    this.maxThreads = this.getHBaseClientConf().getHedgedReadThreadpoolSize();
    this.thresholdMillis = thresholdMillis;
  }

  @Override
  public <T> T execute(String tableName, TableCallback<T, Table> action) {
    Callable<T> prefer = () -> executeOnPrefer(tableName, action);
    Callable<T> spare = () -> executeOnSpare(tableName, action);
    try {
      return execute(prefer, spare);
    } catch (RejectedExecutionException e) {
      try {
        return executeOnPrefer(tableName, action);
      } catch (IOException ex) {
        throw new HTableServiceException(ex);
      }
    } catch (IOException e) {
      throw new HTableServiceException(e);
    }
  }

  @Override
  public void mutate(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) {
    if (this.getHBaseClientConf().isHedgedReadWriteDisable()) {
      try {
        executeOnPreferWithBuffer(tableContext, action);
      } catch (IOException e) {
        throw new HTableServiceException(e);
      }
      return;
    }
    Callable<Void> prefer = () -> {
      executeOnPreferWithBuffer(tableContext, action);
      return null;
    };
    Callable<Void> spare = () -> {
      executeOnSpareWithBuffer(tableContext, action);
      return null;
    };
    try {
      execute(prefer, spare);
    } catch (RejectedExecutionException e) {
      try {
        executeOnPreferWithBuffer(tableContext, action);
      } catch (IOException ex) {
        throw new HTableServiceException(ex);
      }
    } catch (IOException e) {
      throw new HTableServiceException(e);
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
