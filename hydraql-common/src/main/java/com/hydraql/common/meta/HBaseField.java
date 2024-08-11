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

import com.hydraql.common.type.ColumnType;
import com.hydraql.common.type.TypeHandler;
import com.hydraql.reflectasm.invoker.Invoker;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author leojie 2022/11/20 16:49
 */
public abstract class HBaseField implements Serializable {
  private static final long serialVersionUID = -7793261339902482728L;

  private final String name;
  private final boolean isRowKey;
  private final Class<?> type;
  private final TypeHandler<?> typeHandler;
  private final Invoker getMethodInvoker;
  private final Invoker setMethodInvoker;
  private final boolean nullable;

  protected HBaseField(Builder<?> builder) {
    this.name = builder.name;
    this.isRowKey = builder.isRowKey;
    this.type = builder.type;
    this.typeHandler = builder.typeHandler;
    this.getMethodInvoker = builder.getMethodInvoker;
    this.setMethodInvoker = builder.setMethodInvoker;
    this.nullable = builder.nullable;
  }

  public abstract static class Builder<T extends HBaseField> {
    private final String name;
    private boolean isRowKey;
    private final Class<?> type;
    private final TypeHandler<?> typeHandler;
    private Invoker getMethodInvoker;
    private Invoker setMethodInvoker;
    private boolean nullable;

    protected Builder(Class<?> type, String name) {
      this.type = type;
      this.name = name;
      this.typeHandler = ColumnType.findTypeHandler(type);
    }

    protected Builder(Class<?> type) {
      this(type, "");
    }

    public void setIsRowKey(boolean isRowKey) {
      this.isRowKey = isRowKey;
    }

    public void setGetMethodInvoker(Invoker getMethodInvoker) {
      this.getMethodInvoker = getMethodInvoker;
    }

    public void setSetMethodInvoker(Invoker getMethodInvoker) {
      this.setMethodInvoker = getMethodInvoker;
    }

    public void setNullable(boolean nullable) {
      this.nullable = nullable;
    }

    abstract T build();
  }

  public String getName() {
    return name;
  }

  public boolean isRowKey() {
    return isRowKey;
  }

  public Class<?> getType() {
    return type;
  }

  public boolean isNullable() {
    return nullable;
  }

  protected TypeHandler<?> getTypeHandler() {
    return typeHandler;
  }

  protected Invoker getGetMethodInvoker() {
    return getMethodInvoker;
  }

  protected Invoker getSetMethodInvoker() {
    return setMethodInvoker;
  }

  public Object getValue(Object target) {
    Object value = this.getGetMethodInvoker().invoke(target, new Object[]{});
    if (value == null && !this.isNullable()) {
      throw new IllegalStateException(String.format("The value of column [%s] can not be null.", this.getName()));
    }
    return value;
  }

  public byte[] getByteValue(Object target) {
    Object value = this.getGetMethodInvoker().invoke(target, new Object[]{});
    if (value == null && !this.isNullable()) {
      throw new IllegalStateException(String.format("The value of column [%s] can not be null.", this.getName()));
    }
    return this.getTypeHandler().toBytes(this.getType(), value);
  }

  public ByteBuffer getByteBufferValue(Object target) {
    Object value = this.getGetMethodInvoker().invoke(target, new Object[]{});
    if (value == null && !this.isNullable()) {
      throw new IllegalStateException(String.format("The value of column [%s] can not be null.", this.getName()));
    }
    return this.getTypeHandler().toByteBuffer(this.getType(), value);
  }

  public abstract void setByteValue(Object target, byte[] value);

  public abstract void setByteBufferValue(Object target, ByteBuffer value);

  public abstract void setValue(Object target, Object value);

  public byte[] getFamilyBytes() {
    return new byte[0];
  }

  public byte[] getQualifierBytes() {
    return new byte[0];
  }

  public String getFamilyAndQualifier() {
    return "";
  }
}
