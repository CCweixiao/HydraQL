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

package com.hydraql.common.meta;

import com.hydraql.common.exception.InvalidTableModelClassException;
import com.hydraql.common.row.RowKeyGenerator;
import com.hydraql.common.util.BytesUtil;

/**
 * @author leojie@apache.org 2024/8/11 21:53
 */
public class HBaseRow extends HBaseField {
  private static final long serialVersionUID = -8565736009457376872L;

  private final RowKeyGenerator rowKeyGenerator;

  private HBaseRow(HBaseRow.Builder builder) {
    super(builder);
    this.rowKeyGenerator = builder.rowKeyGenerator;
  }

  public static Builder newBuilder(Class<?> type, String rowName) {
    return new Builder(type, rowName);
  }

  @Override
  protected Object getBeanProperty(Object object) {
    Object value = this.getGetMethodInvoker().invoke(object, NO_ARGUMENTS);
    if (value == null) {
      throw new IllegalStateException(
          String.format("The value of column [%s] can not be null.", this.getName()));
    }
    if (rowKeyGenerator == null || rowKeyGenerator.isDefault()) {
      return value;
    }
    if (this.getType() != String.class) {
      throw new InvalidTableModelClassException(
          "RowKeyGenerator cannot be used for non-string type row key.");
    }
    return rowKeyGenerator.apply(value.toString());
  }

  @Override
  protected void setBeanProperty(Object object, Object value) {
    if (value == null) {
      return;
    }
    if (rowKeyGenerator == null || rowKeyGenerator.isDefault()) {
      this.getSetMethodInvoker().invoke(object, new Object[] { value });
      return;
    }
    if (this.getType() != String.class) {
      throw new InvalidTableModelClassException(
          "RowKeyGenerator cannot be used for non-string type row key.");
    }
    String originalRow = rowKeyGenerator.recover(value.toString());
    this.getSetMethodInvoker().invoke(object, new Object[] { originalRow });
  }

  public static class Builder extends HBaseField.Builder<HBaseRow> {
    private RowKeyGenerator rowKeyGenerator;

    private Builder(Class<?> type, String name) {
      super(type, name);
      this.setIsRowKey(true);
      this.setNullable(false);
    }

    public void setRowKeyGenerator(RowKeyGenerator rowKeyGenerator) {
      this.rowKeyGenerator = rowKeyGenerator;
    }

    @Override
    HBaseRow build() {
      return new HBaseRow(this);
    }
  }

  @Override
  public byte[] getRow(Object value) {
    if (value == null) {
      throw new IllegalStateException("The value can not be null.");
    }
    if (rowKeyGenerator == null || rowKeyGenerator.isDefault()) {
      return this.getTypeHandler().toBytes(this.getType(), value);
    }
    if (this.getType() != String.class) {
      throw new InvalidTableModelClassException(
          "RowKeyGenerator cannot be used for non-string type row key.");
    }
    return BytesUtil.toBytes(rowKeyGenerator.apply(value.toString()));
  }
}
