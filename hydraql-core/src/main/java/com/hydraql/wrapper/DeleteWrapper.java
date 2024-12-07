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
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author leojie@apache.org 2024/12/7 19:12
 */
public class DeleteWrapper extends Delete {
  private DeleteWrapper(byte[] row) {
    super(row);
  }

  public static DeleteWrapper create(Object rowKey, RowKeyGenerationStrategy strategy) {
    Assert.notNull(strategy, "The row generation strategy must not be null");
    if (strategy.isNotDefined()) {
      throw new IllegalArgumentException("The row generation strategy must be defined");
    }
    byte[] row = strategy.apply(rowKey);
    return new DeleteWrapper(row);
  }

  public static DeleteWrapper create(String rowKey) {
    return new DeleteWrapper(Bytes.toBytes(rowKey));
  }

  public static DeleteWrapper create(byte[] row) {
    return new DeleteWrapper(row);
  }

  public DeleteWrapper addFamily(String family) {
    addFamily(Bytes.toBytes(family));
    return this;
  }

  public DeleteWrapper addColumn(String family, String qualifier) {
    addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
    return this;
  }

  public DeleteWrapper addColumns(String family, String qualifier) {
    addColumns(Bytes.toBytes(family), Bytes.toBytes(qualifier));
    return this;
  }

  public Delete unwrapper() {
    return this;
  }
}