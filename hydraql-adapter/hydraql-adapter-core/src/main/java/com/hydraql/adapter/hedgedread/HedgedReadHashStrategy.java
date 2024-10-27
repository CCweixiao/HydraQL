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
import com.hydraql.action.MutatorAction;
import com.hydraql.action.HTableAction;
import com.hydraql.exceptions.HTableServiceException;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author leojie@apache.org 2024/4/8 20:07
 */
public class HedgedReadHashStrategy extends AbstractHedgedReadStrategy {
  private final static LongAdder COUNTER = new LongAdder();

  public HedgedReadHashStrategy(AbstractHTableService tableService) {
    super(tableService);
  }

  @Override
  public <T> T execute(String tableName, HTableAction<T, Table> action) {
    if (this.getHBaseClientConf().isHedgedReadWriteDisable()) {
      try {
        return executeOnPrefer(tableName, action);
      } catch (IOException e) {
        throw new HTableServiceException(e);
      }
    } else {
      try {
        COUNTER.increment();
        if ((COUNTER.longValue() & 1) == 0) {
          return executeOnPrefer(tableName, action);
        } else {
          return executeOnSpare(tableName, action);
        }
      } catch (IOException e) {
        throw new HTableServiceException(e);
      }
    }
  }

  @Override
  public void mutate(HTableContext tableContext, MutatorAction<WrapperBufferedMutator> action) {
    if (this.getHBaseClientConf().isHedgedReadWriteDisable()) {
      try {
        executeOnPreferWithBuffer(tableContext, action);
      } catch (IOException e) {
        throw new HTableServiceException(e);
      }
      return;
    }

    COUNTER.increment();
    try {
      if ((COUNTER.longValue() & 1) == 0) {
        executeOnPreferWithBuffer(tableContext, action);
      } else {
        executeOnPreferWithBuffer(tableContext, action);
      }
    } catch (IOException e) {
      throw new HTableServiceException(e);
    }
  }
}
