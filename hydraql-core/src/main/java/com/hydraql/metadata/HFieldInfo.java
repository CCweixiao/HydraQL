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

package com.hydraql.metadata;

import com.hydraql.common.util.StringUtil;
import com.hydraql.generator.RowKeyGenerator;
import com.hydraql.reflectasm.invoker.Invoker;
import com.hydraql.type.ColumnType;
import com.hydraql.type.TypeHandler;
import com.hydraql.util.Preconditions;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author leojie 2022/11/20 16:49
 */
public abstract class HFieldInfo implements Serializable {
  private static final long serialVersionUID = -1453016266875621946L;

  protected static final Object[] NO_ARGUMENTS = {};
  private final String name;
  private final Class<?> type;
  private final TypeHandler<?> typeHandler;
  private final Invoker getMethodInvoker;
  private final Invoker setMethodInvoker;
  private final boolean nullable;

  protected HFieldInfo(Builder<?> builder) {
    this.name = builder.name;
    this.type = builder.type;
    this.typeHandler = builder.typeHandler;
    this.getMethodInvoker = builder.getMethodInvoker;
    this.setMethodInvoker = builder.setMethodInvoker;
    this.nullable = builder.nullable;
  }

  public abstract static class Builder<T extends HFieldInfo> {
    private final String name;
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

  public byte[] getBytesValue(Object object) {
    Object value = this.getBeanProperty(object);
    return this.getTypeHandler().toBytes(this.getType(), value);
  }

  public ByteBuffer getByteBufferValue(Object object) {
    Object value = this.getBeanProperty(object);
    return this.getTypeHandler().toByteBuffer(this.getType(), value);
  }

  public void setValue(Object object, Object value) {
    this.setBeanProperty(object, value);
  }

  public void setBytesValue(Object object, byte[] value) {
    Object val = this.getTypeHandler().toObject(this.getType(), value);
    this.setValue(object, val);
  }

  public void setByteBufferValue(Object object, ByteBuffer value) {
    Object val = this.getTypeHandler().toObject(this.getType(), value);
    this.setValue(object, val);
  }

  protected abstract Object getBeanProperty(Object object);

  protected abstract void setBeanProperty(Object object, Object value);

  public static class RowKey extends HFieldInfo {
    private static final long serialVersionUID = -1834902366369836357L;

    private final RowKeyGenerator rowKeyGenerator;

    private RowKey(Builder builder) {
      super(builder);
      this.rowKeyGenerator = builder.rowKeyGenerator;
    }

    public static RowKey.Builder newBuilder(Class<?> type, String rowName) {
      return new RowKey.Builder(type, rowName);
    }

    public static class Builder extends HFieldInfo.Builder<RowKey> {
      private RowKeyGenerator rowKeyGenerator;

      private Builder(Class<?> type, String name) {
        super(type, name);
        this.setNullable(false);
      }

      public void setRowKeyGenerator(RowKeyGenerator rowKeyGenerator) {
        this.rowKeyGenerator = rowKeyGenerator;
      }

      @Override
      RowKey build() {
        return new RowKey(this);
      }
    }

    @Override
    protected Object getBeanProperty(Object object) {
      Object value = this.getGetMethodInvoker().invoke(object, NO_ARGUMENTS);
      if (value == null) {
        throw new IllegalStateException(
            String.format("The value of column [%s] can not be null.", this.getName()));
      }
      if (rowKeyGenerator == null) {
        return value;
      }
      return rowKeyGenerator.apply(value);
    }

    @Override
    protected void setBeanProperty(Object object, Object value) {
      if (value == null) {
        return;
      }
      if (rowKeyGenerator == null) {
        this.getSetMethodInvoker().invoke(object, new Object[] { value });
        return;
      }
      Object originalRow = rowKeyGenerator.recover(value);
      this.getSetMethodInvoker().invoke(object, new Object[] { originalRow });
    }

    // public byte[] getRow(Object value) {
    // if (value == null) {
    // throw new IllegalStateException("The value can not be null.");
    // }
    // if (rowKeyGenerator == null || rowKeyGenerator.isDefault()) {
    // return this.getTypeHandler().toBytes(this.getType(), value);
    // }
    // if (this.getType() != String.class) {
    // throw new InvalidTableModelClassException(
    // "RowKeyGenerator cannot be used for non-string type row key.");
    // }
    // return rowKeyGenerator.applyToBytes(value);
    // }
  }

  public static class Qualifier extends HFieldInfo implements Comparable<Qualifier> {
    private static final long serialVersionUID = 4085744902721269505L;

    private final String familyString;
    private final byte[] family;
    private final String qualifierString;
    private final byte[] qualifier;
    private final String familyAndQualifier;
    private final int hashCode;

    private Qualifier(Builder builder) {
      super(builder);
      this.familyString = builder.familyString;
      this.family = builder.family;
      this.qualifierString = builder.qualifierString;
      this.qualifier = builder.qualifier;
      this.familyAndQualifier = builder.familyAndQualifier;
      this.hashCode = builder.hashcode;
    }

    public static Qualifier.Builder newBuilder(Class<?> type, String rowName) {
      return new Qualifier.Builder(type, rowName);
    }

    public static class Builder extends HFieldInfo.Builder<Qualifier> {
      private String familyString;
      private byte[] family;
      private String qualifierString;
      private byte[] qualifier;
      private String familyAndQualifier;
      private int hashcode;

      private Builder(Class<?> type, String name) {
        super(type, name);
      }

      public void setFamily(String family) {
        this.familyString = family;
        this.family = Bytes.toBytes(family);
      }

      public void setQualifier(String qualifier) {
        this.qualifierString = qualifier;
        this.qualifier = Bytes.toBytes(qualifier);
      }

      @Override
      Qualifier build() {
        Preconditions.checkState(StringUtil.isNotBlank(familyString),
          "The family cannot be null or empty.");
        Preconditions.checkState(StringUtil.isNotBlank(qualifierString),
          "The qualifier cannot be null or empty.");
        this.familyAndQualifier = familyString + ":" + qualifierString;
        this.hashcode = this.familyAndQualifier.hashCode();
        return new Qualifier(this);
      }
    }

    @Override
    protected Object getBeanProperty(Object object) {
      Object value = this.getGetMethodInvoker().invoke(object, NO_ARGUMENTS);
      if (value == null && !this.isNullable()) {
        throw new IllegalStateException(
            String.format("The value of column [%s] can not be null.", this.getName()));
      }
      return value;
    }

    @Override
    protected void setBeanProperty(Object object, Object value) {
      if (value == null) {
        return;
      }
      this.getSetMethodInvoker().invoke(object, new Object[] { value });
    }

    public String getFamilyAsString() {
      return familyString;
    }

    public byte[] getFamily() {
      return family;
    }

    public String getQualifierAsString() {
      return qualifierString;
    }

    public byte[] getQualifier() {
      return qualifier;
    }

    public String getFamilyAndQualifier() {
      return familyAndQualifier;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null) {
        return false;
      }

      if (!(o instanceof Qualifier)) {
        return false;
      }
      Qualifier qualifier = (Qualifier) o;
      return o.hashCode() == hashCode && familyAndQualifier.equals(qualifier.familyAndQualifier);
    }

    @Override
    public int hashCode() {
      return hashCode;
    }

    @Override
    public int compareTo(Qualifier qualifier) {
      // For performance reasons, the ordering is not lexicographic.
      if (this == qualifier) {
        return 0;
      }
      if (this.hashCode < qualifier.hashCode()) {
        return -1;
      }
      if (this.hashCode > qualifier.hashCode()) {
        return 1;
      }
      return this.familyAndQualifier.compareTo(qualifier.getFamilyAndQualifier());
    }
  }
}
