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
import java.util.concurrent.atomic.LongAdder;

/**
 * @author leojie@apache.org 2024/4/8 20:07
 */
public class HedgedReadHashStrategy extends AbstractHedgedReadStrategy {
  private final static LongAdder COUNTER = new LongAdder();

  public HedgedReadHashStrategy(Executor executor) {
    super(executor);
  }

  @Override
  public <T> T execute(HTableAction<T, Table> action, boolean isWrite) {
    if (disableHedgedReadAction(isWrite)) {
      try {
        return executeOnPrefer(action);
      } catch (IOException e) {
        throw new HydraQlException(e);
      }
    } else {
      try {
        COUNTER.increment();
        if ((COUNTER.longValue() & 1) == 1) {
          return executeOnPrefer(action);
        } else {
          return executeOnSpare(action);
        }
      } catch (IOException e) {
        throw new HydraQlException(e);
      }
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
      return;
    }

    COUNTER.increment();
    try {
      if ((COUNTER.longValue() & 1) == 1) {
        mutateOnPrefer(action);
      } else {
        mutateOnSpare(action);
      }
    } catch (IOException e) {
      throw new HydraQlException(e);
    }
  }
}
