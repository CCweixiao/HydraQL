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

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie@apache.org 2024/12/7 19:18
 */
public class MultiDeleteWrapper {
  private final RowKeyGenerationStrategy strategy;
  private final List<Delete> deletes;

  private MultiDeleteWrapper(List<Object> rowKeyList, RowKeyGenerationStrategy strategy) {
    this.deletes = new ArrayList<>();
    if (rowKeyList != null) {
      for (Object rowKey : rowKeyList) {
        this.deletes.add(DeleteWrapper.create(rowKey, strategy).unwrapper());
      }
    }
    this.strategy = strategy;
  }

  private MultiDeleteWrapper(List<byte[]> rowKeyList) {
    this.deletes = new ArrayList<>();
    if (rowKeyList != null) {
      for (byte[] rowKey : rowKeyList) {
        this.deletes.add(DeleteWrapper.create(rowKey).unwrapper());
      }
    }
    this.strategy = RowKeyGenerationStrategy.NOTHING;
  }

  public static MultiDeleteWrapper create(List<Object> rowKeyList,
      RowKeyGenerationStrategy strategy) {
    Assert.notNull(strategy, "The row generation strategy must not be null");
    if (strategy.isNotDefined()) {
      throw new IllegalArgumentException("The row generation strategy must be defined");
    }
    return new MultiDeleteWrapper(rowKeyList, strategy);
  }

  public static MultiDeleteWrapper create(List<byte[]> rowKeyList) {
    return new MultiDeleteWrapper(rowKeyList);
  }

  public MultiDeleteWrapper addDelete(Delete delete) {
    Assert.notNull(delete, "delete cannot be null");
    this.deletes.add(delete);
    return this;
  }

  public MultiDeleteWrapper addFamily(String family) {
    for (Delete delete : this.deletes) {
      delete.addFamily(Bytes.toBytes(family));
    }
    return this;
  }

  public MultiDeleteWrapper addFamily(byte[] row, String family) {
    if (row == null) {
      return this;
    }
    this.deletes.add(new Delete(row).addFamily(Bytes.toBytes(family)));
    return this;
  }

  public MultiDeleteWrapper addFamily(Object row, String family) {
    if (row == null) {
      return this;
    }
    this.deletes.add(DeleteWrapper.create(row, strategy).addFamily(family));
    return this;
  }

  public MultiDeleteWrapper addColumn(String family, String qualifier) {
    for (Delete delete : this.deletes) {
      delete.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
    }
    return this;
  }

  public MultiDeleteWrapper addColumn(byte[] row, String family, String qualifier) {
    if (row == null) {
      return this;
    }

    this.deletes.add(new Delete(row).addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier)));
    return this;
  }

  public MultiDeleteWrapper addColumn(Object row, String family, String qualifier) {
    if (row == null) {
      return this;
    }

    this.deletes.add(DeleteWrapper.create(row, strategy).addColumn(family, qualifier));
    return this;
  }

  public MultiDeleteWrapper addColumns(String family, String qualifier) {
    for (Delete delete : this.deletes) {
      delete.addColumns(Bytes.toBytes(family), Bytes.toBytes(qualifier));
    }
    return this;
  }

  public MultiDeleteWrapper addColumns(byte[] row, String family, String qualifier) {
    if (row == null) {
      return this;
    }

    this.deletes.add(new Delete(row).addColumns(Bytes.toBytes(family), Bytes.toBytes(qualifier)));
    return this;
  }

  public MultiDeleteWrapper addColumns(Object row, String family, String qualifier) {
    if (row == null) {
      return this;
    }

    this.deletes.add(DeleteWrapper.create(row, strategy).addColumns(family, qualifier));
    return this;
  }

  public List<Delete> unwrapper() {
    return this.deletes;
  }

}
