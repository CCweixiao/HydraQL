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

package com.hydraql.adapter;

import com.hydraql.adapter.hedgedread.HedgedReadStrategy;
import org.apache.hadoop.conf.Configuration;

/**
 * @author leojie@apache.org 2024/4/6 22:26
 */
public class HBaseClientConf {
  private final HedgedReadStrategy.Level hedgedReadStrategy;
  private final int hedgedReadThreadpoolSize;
  private final long hedgedReadThresholdMillis;
  private final long hedgedReadOverallTimeoutMillis;
  private final boolean hedgedReadWriteDisable;

  public HBaseClientConf(Configuration conf) {
    if (conf != null) {
      hedgedReadStrategy =
          HedgedReadStrategy.Level.find(conf.get(HBaseClientConfigKeys.HedgedRead.STRATEGY,
            HBaseClientConfigKeys.HedgedRead.STRATEGY_DEFAULT));
      hedgedReadThreadpoolSize = conf.getInt(HBaseClientConfigKeys.HedgedRead.THREADPOOL_SIZE_KEY,
        HBaseClientConfigKeys.HedgedRead.THREADPOOL_SIZE_DEFAULT);
      hedgedReadOverallTimeoutMillis =
          conf.getLong(HBaseClientConfigKeys.HedgedRead.OVERALL_TIMEOUT_MILLIS,
            HBaseClientConfigKeys.HedgedRead.OVERALL_TIMEOUT_MILLIS_DEFAULT);
      hedgedReadThresholdMillis =
          conf.getLong(HBaseClientConfigKeys.HedgedRead.THRESHOLD_MILLIS_KEY,
            HBaseClientConfigKeys.HedgedRead.THRESHOLD_MILLIS_DEFAULT);
      hedgedReadWriteDisable = conf.getBoolean(HBaseClientConfigKeys.HedgedRead.WRITE_DISABLE,
        HBaseClientConfigKeys.HedgedRead.WRITE_DISABLE_DEFAULT);
    } else {
      hedgedReadThreadpoolSize = HBaseClientConfigKeys.HedgedRead.THREADPOOL_SIZE_DEFAULT;
      hedgedReadOverallTimeoutMillis =
          HBaseClientConfigKeys.HedgedRead.OVERALL_TIMEOUT_MILLIS_DEFAULT;
      hedgedReadThresholdMillis = HBaseClientConfigKeys.HedgedRead.THRESHOLD_MILLIS_DEFAULT;
      hedgedReadWriteDisable = HBaseClientConfigKeys.HedgedRead.WRITE_DISABLE_DEFAULT;
      hedgedReadStrategy = HedgedReadStrategy.Level.NONE;
    }
  }

  public HedgedReadStrategy.Level getHedgedReadStrategy() {
    return hedgedReadStrategy;
  }

  public int getHedgedReadThreadpoolSize() {
    return hedgedReadThreadpoolSize;
  }

  public long getHedgedReadOverallTimeoutMillis() {
    return hedgedReadOverallTimeoutMillis;
  }

  public long getHedgedReadThresholdMillis() {
    return hedgedReadThresholdMillis;
  }

  public boolean isHedgedReadWriteDisable() {
    return hedgedReadWriteDisable;
  }

  public boolean hedgedReadIsOpen() {
    return this.getHedgedReadStrategy() != HedgedReadStrategy.Level.NONE
        && this.getHedgedReadThreadpoolSize() > 0;
  }
}
