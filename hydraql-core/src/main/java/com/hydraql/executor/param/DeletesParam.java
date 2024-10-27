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

package com.hydraql.executor.param;

import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie@apache.org 2024/9/8 12:27
 */
public class DeletesParam extends QueryParam<DeletesParam> {
  private final List<byte[]> rows;

  private DeletesParam(Builder builder) {
    super(builder);
    this.rows = builder.rows;
  }

  public static DeletesParam.Builder created(List<byte[]> rows) {
    if (null == rows) {
      throw new ParamException("rows is null");
    }
    return new Builder(rows);
  }

  public static DeletesParam.Builder create() {
    return new Builder();
  }

  public static class Builder extends QueryParam.Builder<DeletesParam> {
    private List<byte[]> rows;

    private Builder(List<byte[]> rows) {
      this.rows = rows;
    }

    private Builder() {
      this(new ArrayList<>());
    }

    public Builder appendRow(byte[] row) {
      this.rows.add(row);
      return this;
    }

    public Builder appendRow(String row) {
      this.rows.add(Bytes.toBytes(row));
      return this;
    }

    @Override
    public DeletesParam build() {
      if (null != rowKeyGenerator && !rowKeyGenerator.isDefault()) {
        List<byte[]> rows = new ArrayList<>(this.rows.size());
        for (byte[] row : this.rows) {
          String _row = Bytes.toString(row);
          rows.add(Bytes.toBytes(rowKeyGenerator.apply(_row)));
        }
        this.rows = rows;
      }
      return new DeletesParam(this);
    }
  }

  public List<byte[]> getRows() {
    return rows;
  }
}
