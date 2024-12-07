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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie@apache.org 2024/12/7 13:45
 */
public class MultiPutWrapper {
  private final RowKeyGenerationStrategy strategy;
  private final List<Put> puts;

  private MultiPutWrapper(RowKeyGenerationStrategy strategy) {
    this.puts = new ArrayList<>();
    if (strategy == null) {
      this.strategy = RowKeyGenerationStrategy.NOTHING;
    } else {
      this.strategy = strategy;
    }
  }

  private MultiPutWrapper() {
    this(RowKeyGenerationStrategy.NOTHING);
  }

  public static MultiPutWrapper create(RowKeyGenerationStrategy generationType) {
    return new MultiPutWrapper(generationType);
  }

  public static MultiPutWrapper create() {
    return new MultiPutWrapper();
  }

  public MultiPutWrapper addPut(Put put) {
    Assert.notNull(put, "put cannot be null");
    this.puts.add(put);
    return this;
  }

  public MultiPutWrapper addColumn(byte[] row, String family, String qualifier, byte[] value) {
    Put put = PutWrapper.create(row).addColumn(family, qualifier, value).unwrapper();
    this.puts.add(put);
    return this;
  }

  public MultiPutWrapper addColumn(byte[] row, String family, String qualifier, String value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(byte[] row, String family, String qualifier, boolean value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(byte[] row, String family, String qualifier, int value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(byte[] row, String family, String qualifier, long value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(byte[] row, String family, String qualifier, BigDecimal value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(byte[] row, String family, String qualifier, float value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(byte[] row, String family, String qualifier, double value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(String row, String family, String qualifier, byte[] value) {
    Put put = PutWrapper.create(row, this.strategy).addColumn(family, qualifier, value).unwrapper();
    this.puts.add(put);
    return this;
  }

  public MultiPutWrapper addColumn(String row, String family, String qualifier, String value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(String row, String family, String qualifier, boolean value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(String row, String family, String qualifier, int value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(String row, String family, String qualifier, long value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(String row, String family, String qualifier, BigDecimal value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(String row, String family, String qualifier, float value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public MultiPutWrapper addColumn(String row, String family, String qualifier, double value) {
    return addColumn(row, family, qualifier, ValueConvertor.toBytes(value));
  }

  public List<Put> unwrapper() {
    return this.puts;
  }
}
