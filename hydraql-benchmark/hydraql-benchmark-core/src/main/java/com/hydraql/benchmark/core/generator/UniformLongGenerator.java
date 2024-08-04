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

package com.hydraql.benchmark.core.generator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates longs randomly uniform from an interval.
 */
public class UniformLongGenerator extends NumberGenerator {
  private final long lb, ub, interval;

  /**
   * Creates a generator that will return longs uniformly randomly from the interval [lb,ub]
   * inclusive (that is, lb and ub are possible values) (lb and ub are possible values).
   * @param lb the lower bound (inclusive) of generated values
   * @param ub the upper bound (inclusive) of generated values
   */
  public UniformLongGenerator(long lb, long ub) {
    this.lb = lb;
    this.ub = ub;
    interval = this.ub - this.lb + 1;
  }

  @Override
  public Long nextValue() {
    long ret = Math.abs(ThreadLocalRandom.current().nextLong()) % interval + lb;
    setLastValue(ret);

    return ret;
  }

  @Override
  public double mean() {
    return ((lb + (long) ub)) / 2.0;
  }
}
