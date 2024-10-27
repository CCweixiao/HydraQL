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

package com.hydraql.hedgedread.factory;

import com.hydraql.executor.Executor;
import com.hydraql.hedgedread.HedgedReadStrategy;
import com.hydraql.hedgedread.HedgedReadConsistencyStrategy;
import com.hydraql.hedgedread.HedgedReadEmptyStrategy;
import com.hydraql.hedgedread.HedgedReadFirstOneStrategy;
import com.hydraql.hedgedread.HedgedReadHashStrategy;
import com.hydraql.hedgedread.HedgedReadThresholdStrategy;
import com.hydraql.hedgedread.UnsupportedHedgedReadStrategyException;

/**
 * @author leojie@apache.org 2024/9/6 21:15
 */
public class HedgedReadStrategyDefaultFactory implements HedgedReadStrategyFactory {
  private final Executor executor;

  public HedgedReadStrategyDefaultFactory(Executor executor) {
    this.executor = executor;
  }

  @Override
  public HedgedReadStrategy create() {
    HedgedReadStrategy hedgedRead;
    if (executor.getConfiguration().getHedgedReadProperty().isActivate()) {
      HedgedReadStrategy.Option strategy =
          executor.getConfiguration().getHedgedReadProperty().getHedgedReadStrategyOption();

      switch (strategy) {
        case THRESHOLD:
          hedgedRead = new HedgedReadThresholdStrategy(executor);
          break;
        case FIRST_ONE:
          hedgedRead = new HedgedReadFirstOneStrategy(executor);
          break;
        case HASH:
          hedgedRead = new HedgedReadHashStrategy(executor);
          break;
        case CONSISTENCY:
          hedgedRead = new HedgedReadConsistencyStrategy(executor);
          break;
        case NONE:
          hedgedRead = new HedgedReadEmptyStrategy(executor);
          break;
        default:
          throw new UnsupportedHedgedReadStrategyException(
              "Illegal hedged read strategy " + strategy);
      }

    } else {
      hedgedRead = new HedgedReadEmptyStrategy(executor);
    }
    return hedgedRead;
  }
}
