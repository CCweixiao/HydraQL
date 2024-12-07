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

package com.hydraql.wrapper;

import com.hydraql.generator.RowKeyGenerationStrategy;
import com.hydraql.util.Assert;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author leojie@apache.org 2024/12/7 10:18
 */
public class GetWrapper extends Get {
  private final RowKeyGenerationStrategy strategy;

  private GetWrapper(byte[] row, RowKeyGenerationStrategy strategy) {
    super(row);
    this.strategy = strategy;
  }

  private GetWrapper(byte[] row) {
    super(row);
    this.strategy = RowKeyGenerationStrategy.NOTHING;
  }

  public static GetWrapper create(Object rowKey, RowKeyGenerationStrategy strategy) {
    Assert.notNull(strategy, "The row generation strategy must not be null");
    if (strategy.isNotDefined()) {
      throw new IllegalArgumentException("The row generation strategy must be defined");
    }
    byte[] row = strategy.apply(rowKey);
    return new GetWrapper(row, strategy);
  }

  public static GetWrapper create(String rowKey) {
    return new GetWrapper(Bytes.toBytes(rowKey));
  }

  public static GetWrapper create(byte[] row) {
    return new GetWrapper(row);
  }

  public GetWrapper addFamily(String family) {
    addFamily(Bytes.toBytes(family));
    return this;
  }

  public GetWrapper addColumn(String family, String qualifier) {
    addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
    return this;
  }

  public RowKeyGenerationStrategy rowGenerationStrategy() {
    return strategy;
  }

  public Get unwrapper() {
    return this;
  }
}
