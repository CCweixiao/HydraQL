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

/**
 * A generator that is capable of generating numeric values.
 */
public abstract class NumberGenerator extends Generator<Number> {
  private Number lastVal;

  /**
   * Set the last value generated. NumberGenerator subclasses must use this call to properly set the
   * last value, or the {@link #lastValue()} calls won't work.
   */
  protected void setLastValue(Number last) {
    lastVal = last;
  }

  @Override
  public Number lastValue() {
    return lastVal;
  }

  /**
   * Return the expected value (mean) of the values this generator will return.
   */
  public abstract double mean();
}
