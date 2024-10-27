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

import com.hydraql.common.util.StringUtil;
import com.hydraql.util.Assert;
import com.hydraql.util.Preconditions;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author leojie@apache.org 2024/9/8 18:47
 */
public class DeleteParam extends QueryParam<DeleteParam> {
  private final byte[] row;

  public DeleteParam(Builder builder) {
    super(builder);
    this.row = builder.row;
  }

  public static class Builder extends QueryParam.Builder<DeleteParam> {
    private byte[] row;

    private Builder(byte[] row) {
      this.row = row;
    }

    private Builder(String row) {
      Assert.checkArgument(StringUtil.isNotBlank(row), "row cannot be null or empty");
      this.row = Bytes.toBytes(row);
    }

    @Override
    public DeleteParam build() {
      if (null != rowKeyGenerator && !rowKeyGenerator.isDefault()) {
        String row = Bytes.toString(this.row);
        Preconditions.checkNotNull(row);
        this.row = Bytes.toBytes(rowKeyGenerator.apply(row));
      }
      return new DeleteParam(this);
    }
  }

  public byte[] getRow() {
    return row;
  }
}
