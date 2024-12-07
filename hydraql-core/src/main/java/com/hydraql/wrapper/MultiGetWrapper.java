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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie@apache.org 2024/12/7 10:54
 */
public class MultiGetWrapper {
  private final RowKeyGenerationStrategy strategy;
  private final List<Get> gets;

  private MultiGetWrapper(List<Object> rowKeyList, RowKeyGenerationStrategy strategy) {
    this.gets = new ArrayList<>();
    if (rowKeyList != null) {
      for (Object rowKey : rowKeyList) {
        this.gets.add(GetWrapper.create(rowKey, strategy).unwrapper());
      }
    }
    this.strategy = strategy;
  }

  private MultiGetWrapper(List<byte[]> rowKeyList) {
    this.gets = new ArrayList<>();
    if (rowKeyList != null) {
      for (byte[] rowKey : rowKeyList) {
        this.gets.add(GetWrapper.create(rowKey).unwrapper());
      }
    }
    this.strategy = RowKeyGenerationStrategy.NOTHING;
  }

  public static MultiGetWrapper create(List<Object> rowKeyList, RowKeyGenerationStrategy strategy) {
    Assert.notNull(strategy, "The row generation strategy must not be null");
    if (strategy.isNotDefined()) {
      throw new IllegalArgumentException("The row generation strategy must be defined");
    }
    return new MultiGetWrapper(rowKeyList, strategy);
  }

  public static MultiGetWrapper create(List<byte[]> rowKeyList) {
    return new MultiGetWrapper(rowKeyList);
  }

  public MultiGetWrapper addGet(Get get) {
    Assert.notNull(get, "get cannot be null");
    this.gets.add(get);
    return this;
  }

  public MultiGetWrapper addFamily(String family) {
    for (Get get : this.gets) {
      get.addFamily(Bytes.toBytes(family));
    }
    return this;
  }

  public MultiGetWrapper addColumn(String family, String qualifier) {
    for (Get get : this.gets) {
      get.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
    }
    return this;
  }

  public MultiGetWrapper setMaxVersions(int maxVersions) {
    if (maxVersions <= 0) {
      throw new IllegalArgumentException("maxVersions must be positive");
    }
    try {
      for (Get get : this.gets) {
        get.setMaxVersions(maxVersions);
      }
    } catch (IOException e) {
      throw new IllegalStateException("Can't happen error", e);
    }
    return this;
  }

  public MultiGetWrapper setCacheBlocks(boolean cacheBlocks) {
    for (Get get : this.gets) {
      get.setCacheBlocks(cacheBlocks);
    }
    return this;
  }

  public RowKeyGenerationStrategy getStrategy() {
    return strategy;
  }

  public List<Get> unwrapper() {
    return this.gets;
  }
}
