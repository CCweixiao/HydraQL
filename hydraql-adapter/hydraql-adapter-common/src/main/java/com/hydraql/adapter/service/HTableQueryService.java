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

package com.hydraql.adapter.service;

import com.hydraql.adapter.hedgedread.HedgedReadConsistencyStrategy;
import com.hydraql.adapter.hedgedread.HedgedReadEmptyStrategy;
import com.hydraql.adapter.hedgedread.HedgedReadFirstOneStrategy;
import com.hydraql.adapter.hedgedread.HedgedReadHashStrategy;
import com.hydraql.adapter.hedgedread.HedgedReadStrategy;
import com.hydraql.adapter.hedgedread.HedgedReadThresholdStrategy;
import com.hydraql.common.callback.TableCallback;
import com.hydraql.adapter.hedgedread.UnsupportedHedgedReadStrategyException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leojie@apache.org 2024/4/12 19:45
 */
public abstract class HTableQueryService extends AbstractHTableService {
  private static final Logger LOG = LoggerFactory.getLogger(HTableQueryService.class);

  private final Configuration configuration;

  protected HTableQueryService(Configuration configuration) {
    this.configuration = configuration;
    this.warmUpConnection();
    LOG.info("The new connection is warmed up successfully!");
    if (hedgedReadIsOpen()) {
      this.warmUpHedgedReadConnection();
      LOG.info("The new hedged read connection is warmed up successfully!");
    }
  }

  @Override
  public Configuration getConfiguration() {
    return configuration;
  }

  public <T> T executeQuery(String tableName, TableCallback<T, Table> action) {
    return this.execute(tableName, action);
  }

  protected <T> T execute(String tableName, TableCallback<T, Table> action) {
    HedgedReadStrategy hedgedReadStrategy = createHedgedReadStrategy();
    return hedgedReadStrategy.execute(tableName, action);
  }

  protected HedgedReadStrategy createHedgedReadStrategy() {
    HedgedReadStrategy strategy;
    if (hedgedReadIsOpen()) {
      HedgedReadStrategy.Level level = getHBaseClientConf().getHedgedReadStrategy();
      switch (level) {
        case THRESHOLD:
          strategy = new HedgedReadThresholdStrategy(this);
          break;
        case FIRST_ONE:
          strategy = new HedgedReadFirstOneStrategy(this);
          break;
        case HASH:
          strategy = new HedgedReadHashStrategy(this);
          break;
        case CONSISTENCY:
          strategy = new HedgedReadConsistencyStrategy(this);
          break;
        case NONE:
          strategy = new HedgedReadEmptyStrategy(this);
          break;
        default:
          throw new UnsupportedHedgedReadStrategyException(
              "Illegal hedged read strategy level " + level);
      }
    } else {
      strategy = new HedgedReadEmptyStrategy(this);
    }
    return strategy;
  }

  protected boolean hedgedReadIsOpen() {
    return getHBaseClientConf().hedgedReadIsOpen();
  }

  protected boolean hedgedReadWriteDisable() {
    return getHBaseClientConf().isHedgedReadWriteDisable();
  }
}
