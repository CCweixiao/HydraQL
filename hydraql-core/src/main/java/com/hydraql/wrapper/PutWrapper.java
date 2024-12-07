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

import com.hydraql.convertor.ValueConvertor;
import com.hydraql.generator.RowKeyGenerationStrategy;
import com.hydraql.util.Assert;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.math.BigDecimal;

/**
 * @author leojie@apache.org 2024/12/7 11:48
 */
public class PutWrapper extends Put {
  private PutWrapper(byte[] row) {
    super(row);
  }

  public static PutWrapper create(Object rowKey, RowKeyGenerationStrategy strategy) {
    Assert.notNull(strategy, "The row generation strategy must not be null");
    if (strategy.isNotDefined()) {
      throw new IllegalArgumentException("The row generation strategy must be defined");
    }
    byte[] row = strategy.apply(rowKey);
    return new PutWrapper(row);
  }

  public static PutWrapper create(String rowKey) {
    byte[] row = Bytes.toBytes(rowKey);
    return create(row);
  }

  public static PutWrapper create(byte[] row) {
    return new PutWrapper(row);
  }

  public PutWrapper addColumn(String family, String qualifier, byte[] value) {
    addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), value);
    return this;
  }

  public PutWrapper addColumn(String family, String qualifier, String value) {
    return addColumn(family, qualifier, ValueConvertor.toBytes(value));
  }

  public PutWrapper addColumn(String family, String qualifier, boolean value) {
    return addColumn(family, qualifier, ValueConvertor.toBytes(value));
  }

  public PutWrapper addColumn(String family, String qualifier, int value) {
    return addColumn(family, qualifier, ValueConvertor.toBytes(value));
  }

  public PutWrapper addColumn(String family, String qualifier, long value) {
    return addColumn(family, qualifier, ValueConvertor.toBytes(value));
  }

  public PutWrapper addColumn(String family, String qualifier, BigDecimal value) {
    return addColumn(family, qualifier, ValueConvertor.toBytes(value));
  }

  public PutWrapper addColumn(String family, String qualifier, float value) {
    return addColumn(family, qualifier, ValueConvertor.toBytes(value));
  }

  public PutWrapper addColumn(String family, String qualifier, double value) {
    return addColumn(family, qualifier, ValueConvertor.toBytes(value));
  }

  public Put unwrapper() {
    return this;
  }

}
