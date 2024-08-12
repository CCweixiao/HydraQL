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
  private static final long serialVersionUID = 7543769973218535462L;

  protected static final Object[] NO_ARGUMENTS = {};

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

  public Object getValue(Object object) {
    return this.getBeanProperty(object);
  }

  public byte[] toBytes(Object object) {
    Object value = this.getBeanProperty(object);
    return this.getTypeHandler().toBytes(this.getType(), value);
  }

  public ByteBuffer toByteBuffer(Object object) {
    Object value = this.getBeanProperty(object);
    return this.getTypeHandler().toByteBuffer(this.getType(), value);
  }

  protected abstract Object getBeanProperty(Object object);

  protected abstract void setBeanProperty(Object object, Object value);

  public void setValue(Object object, byte[] value) {
    Object val = this.getTypeHandler().toObject(this.getType(), value);
    this.setValue(object, val);
  }

  public void setValue(Object object, ByteBuffer value) {
    Object val = this.getTypeHandler().toObject(this.getType(), value);
    this.setValue(object, val);
  }

  public void setValue(Object object, Object value) {
    this.setBeanProperty(object, value);
  }

  public byte[] getRow(Object value) {
    return new byte[0];
  }

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
