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

import com.hydraql.adapter.HBaseClientConfigKeys;
import com.hydraql.adapter.WrapperBufferedMutator;
import com.hydraql.adapter.context.HTableContext;
import com.hydraql.action.MutatorAction;
import com.hydraql.action.HTableAction;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.client.Table;

/**
 * @author leojie@apache.org 2024/4/8 17:29
 */
@Deprecated
public interface HedgedReadStrategy {
  enum Level {
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

    public static HedgedReadStrategy.Level find(String strategy) {
      if (StringUtil.isBlank(strategy)) {
        return NONE;
      }
      for (Level level : Level.values()) {
        if (strategy.equalsIgnoreCase(level.name())) {
          return level;
        }
      }
      throw new IllegalStateException(String.format("%s=%s is not supported yet",
        HBaseClientConfigKeys.HedgedRead.STRATEGY, strategy));
    }
  }

  <T> T execute(String tableName, HTableAction<T, Table> action);

  void mutate(HTableContext tableContext, MutatorAction<WrapperBufferedMutator> action);
}
