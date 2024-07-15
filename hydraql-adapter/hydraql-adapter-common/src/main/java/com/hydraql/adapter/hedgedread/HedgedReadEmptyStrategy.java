/**
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

/**
 * @author leojie@apache.org 2024/4/14 20:58
 */
public class HedgedReadEmptyStrategy extends AbstractHedgedReadStrategy {

  public HedgedReadEmptyStrategy(AbstractHTableService tableService) {
    super(tableService);
  }

  @Override
  public <T> T execute(String tableName, TableCallback<T, Table> action) {
    try {
      return executeOnPrefer(tableName, action);
    } catch (IOException e) {
      throw new HTableServiceException(e);
    }
  }

  @Override
  public void mutate(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action) {
    try {
      executeOnPreferWithBuffer(tableContext, action);
    } catch (IOException e) {
      throw new HTableServiceException(e);
    }
  }
}
