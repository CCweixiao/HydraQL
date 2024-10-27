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

import com.hydraql.common.util.StringUtil;
import com.hydraql.action.MutatorAction;
import com.hydraql.action.HTableAction;
import com.hydraql.conf.HQLConfigKeys;
import com.hydraql.mutator.WrapperBufferedMutator;
import org.apache.hadoop.hbase.client.Table;

/**
 * @author leojie@apache.org 2024/4/8 17:29
 */
public interface HedgedReadStrategy {
  enum Option {
    /**
     * Based on time thresholds
     */
    THRESHOLD,

    /**
     * Two requests are initiated at the same time, and the first one returns a result
     */
    FIRST_ONE,

    /**
     * Multiple requests must all be processed
     */
    CONSISTENCY,

    /**
     * For multiple clusters, the request is made in hash mode
     */
    HASH,

    /**
     * Do not use hedged
     */
    NONE;

    public static Option find(String strategyName) {
      if (StringUtil.isBlank(strategyName)) {
        return NONE;
      }
      for (Option strategy : Option.values()) {
        if (strategyName.equalsIgnoreCase(strategy.name())) {
          return strategy;
        }
      }
      throw new UnsupportedHedgedReadStrategyException(String.format("%s=%s is not supported yet",
        HQLConfigKeys.HedgedRead.STRATEGY, strategyName));
    }

    public boolean isActivate() {
      return this != NONE;
    }
  }

  <T> T execute(HTableAction<T, Table> action, boolean isWrite);

  void mutate(MutatorAction<WrapperBufferedMutator> action);
}
