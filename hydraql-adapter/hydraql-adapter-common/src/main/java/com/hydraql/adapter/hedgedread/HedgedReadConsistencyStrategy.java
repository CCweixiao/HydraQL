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
import com.hydraql.common.callback.MutatorCallback;
import com.hydraql.common.callback.TableCallback;
import com.hydraql.common.exception.HTableServiceException;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author leojie@apache.org 2024/4/8 19:43
 */
public class HedgedReadConsistencyStrategy extends AbstractHedgedReadStrategy {
  private final int maxThreads;
  private final long overallTimeoutMillis;

  public HedgedReadConsistencyStrategy(AbstractHTableService tableService) {
    super(tableService);
    // todo 优化时间定义
    this.maxThreads = getHBaseClientConf().getHedgedReadThreadpoolSize();
    this.overallTimeoutMillis = getHBaseClientConf().getHedgedReadOverallTimeoutMillis();
  }

  @Override
  public <T> T execute(String tableName, TableCallback<T, Table> action) {
    Callable<T> prefer = () -> executeOnPrefer(tableName, action);
    Callable<T> spare = () -> executeOnSpare(tableName, action);
    try {
      return execute(prefer, spare);
    } catch (IOException e) {
      throw new HTableServiceException(e);
    }
  }

  @Override
  public void mutate(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) {
    // todo 完善
    try {
      if (!this.getHBaseClientConf().isHedgedReadWriteDisable()) {
        Callable<Void> prefer = () -> {
          executeOnPreferWithBuffer(tableContext, action);
          return null;
        };
        Callable<Void> spare = () -> {
          executeOnSpareWithBuffer(tableContext, action);
          return null;
        };
        execute(prefer, spare);
      } else {
        executeOnPreferWithBuffer(tableContext, action);
      }
    } catch (IOException e) {
      throw new HTableServiceException(e);
    }
  }

  private <T> T execute(Callable<T> prefer, Callable<T> spare) throws IOException {
    ArrayList<Future<T>> futures = new ArrayList<>();
    ThreadPoolExecutor executor = HedgedReadExecutor.create().getExecutor(maxThreads);

    Future<T> preferRequest = executor.submit(prefer);
    futures.add(preferRequest);
    Future<T> spareRequest = executor.submit(spare);
    futures.add(spareRequest);

    List<T> resultList = new ArrayList<>(2);
    long timeoutMillis = overallTimeoutMillis / 2;
    if (timeoutMillis < 0) {
      timeoutMillis = 0;
    }
    while (!futures.isEmpty()) {
      Iterator<Future<T>> iterator = futures.iterator();
      while (iterator.hasNext()) {
        Future<T> future = iterator.next();
        try {
          if (timeoutMillis > 0) {
            resultList.add(future.get(timeoutMillis, TimeUnit.MILLISECONDS));
          } else {
            resultList.add(future.get());
          }
        } catch (InterruptedException e) {
          throw new IOException("Interrupted while waiting for reading/writing task", e);
        } catch (TimeoutException e) {
          throw new IOException(String.format(
            "Reading/writing task execution timeout,"
                + " the timeout is %sms, and overall timeout is %sms",
            timeoutMillis, overallTimeoutMillis), e);
        } catch (ExecutionException e) {
          throw new IOException(
              "Reading/writing task execution failed," + " and the error reason is", e);
        }
        iterator.remove();
      }
    }

    if (resultList.isEmpty()) {
      return null;
    }

    return resultList.get(0);
  }
}
