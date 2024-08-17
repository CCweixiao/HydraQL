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

package com.hydraql.core.type;

import com.alibaba.fastjson2.JSON;
import com.hydraql.core.toolkit.Preconditions;
import com.hydraql.common.type.handler.*;
import com.hydraql.core.type.handler.FloatHandler;
import com.hydraql.core.type.handler.ext.HexBytes;
import com.hydraql.core.type.handler.ext.HexBytesHandler;
import com.hydraql.common.util.StringUtil;
import com.hydraql.core.type.handler.BigDecimalHandler;
import com.hydraql.core.type.handler.BigIntegerHandler;
import com.hydraql.core.type.handler.BooleanHandler;
import com.hydraql.core.type.handler.ByteHandler;
import com.hydraql.core.type.handler.CharHandler;
import com.hydraql.core.type.handler.DateHandler;
import com.hydraql.core.type.handler.DoubleHandler;
import com.hydraql.core.type.handler.EnumHandler;
import com.hydraql.core.type.handler.IntegerHandler;
import com.hydraql.core.type.handler.JsonHandler;
import com.hydraql.core.type.handler.LongHandler;
import com.hydraql.core.type.handler.ShortHandler;
import com.hydraql.core.type.handler.StringHandler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leojie 2022/12/2 23:58
 */
public enum ColumnType {
  /**
   * schema type
   */
  StringType("string", String.class, String.class, new StringHandler()),
  CharType("char", Character.class, char.class, new CharHandler()),
  ShortType("short", Short.class, short.class, new ShortHandler()),
  LongType("long", Long.class, long.class, new LongHandler()),
  MapType("map", Map.class, Map.class, new JsonHandler()),
  ListType("list", List.class, List.class, new JsonHandler()),
  IntegerType("int", Integer.class, int.class, new IntegerHandler()),
  FloatType("float", Float.class, float.class, new FloatHandler()),
  DoubleType("double", Double.class, double.class, new DoubleHandler()),
  DateType("date", Date.class, Date.class, new DateHandler()),
  ByteType("byte", Byte.class, byte.class, new ByteHandler()),
  BoolType("bool", Boolean.class, boolean.class, new BooleanHandler()),
  BigIntegerType("bigint", BigInteger.class, BigInteger.class, new BigIntegerHandler()),
  BigDecimalType("bigdecimal", BigDecimal.class, BigDecimal.class, new BigDecimalHandler()),
  HexByteType("hex", HexBytes.class, HexBytes.class, new HexBytesHandler()),
  JsonType("json", JSON.class, JSON.class, new JsonHandler()),
  EnumType("enum", Enum.class, Enum.class, new EnumHandler());

  private static final Map<String, String> JDBC_TYPE_MAPPING = new HashMap<>();

  static {
    // todo 完善JDBCType
    JDBC_TYPE_MAPPING.put("CHAR", "string");
    JDBC_TYPE_MAPPING.put("VARCHAR", "string");
    JDBC_TYPE_MAPPING.put("DECIMAL", "bigdecimal");
    JDBC_TYPE_MAPPING.put("TINYINT", "int");
    JDBC_TYPE_MAPPING.put("SMALLINT", "int");
    JDBC_TYPE_MAPPING.put("INTEGER", "int");
    JDBC_TYPE_MAPPING.put("BIGINT", "long");
    JDBC_TYPE_MAPPING.put("FLOAT", "float");
    JDBC_TYPE_MAPPING.put("DOUBLE", "double");
    JDBC_TYPE_MAPPING.put("TIMESTAMP", "");
    JDBC_TYPE_MAPPING.put("DATE", "date");
    JDBC_TYPE_MAPPING.put("TIME", "");
    JDBC_TYPE_MAPPING.put("BINARY", "");
    JDBC_TYPE_MAPPING.put("VARBINARY", "");
    // 无符号类型
    JDBC_TYPE_MAPPING.put("UNSIGNED_TIMESTAMP", "");
    JDBC_TYPE_MAPPING.put("UNSIGNED_DATE", "date");
    JDBC_TYPE_MAPPING.put("UNSIGNED_TIME", "");
    JDBC_TYPE_MAPPING.put("UNSIGNED_TINYINT", "int");
    JDBC_TYPE_MAPPING.put("UNSIGNED_SMALLINT", "int");
    JDBC_TYPE_MAPPING.put("UNSIGNED_INT", "int");
    JDBC_TYPE_MAPPING.put("UNSIGNED_LONG", "long");
    JDBC_TYPE_MAPPING.put("UNSIGNED_FLOAT", "float");
    JDBC_TYPE_MAPPING.put("UNSIGNED_DOUBLE", "double");
  }

  private final String typeName;
  private final Class<?> typeClass;

  private final Class<?> orTypeClass;
  private final TypeHandler<?> typeHandler;

  ColumnType(String typeName, Class<?> typeClass, Class<?> orTypeClass,
      AbstractTypeHandler<?> typeHandler) {
    this.typeName = typeName;
    this.typeClass = typeClass;
    this.orTypeClass = orTypeClass;
    this.typeHandler = typeHandler;
  }

  public String getTypeName() {
    return typeName;
  }

  public Class<?> getTypeClass() {
    return typeClass;
  }

  public Class<?> getOrTypeClass() {
    return orTypeClass;
  }

  public TypeHandler<?> getTypeHandler() {
    return typeHandler;
  }

  public static TypeHandler<?> findTypeHandler(Class<?> clazz) {
    Preconditions.checkState(clazz != null, "Class type is null");
    if (clazz.isEnum()) {
      return EnumType.getTypeHandler();
    }
    for (ColumnType columnType : ColumnType.values()) {
      if (columnType.getTypeClass() == clazz) {
        return columnType.getTypeHandler();
      }
      if (columnType.getOrTypeClass() == clazz) {
        return columnType.getTypeHandler();
      }
    }
    return JsonType.getTypeHandler();
  }

  public static Object toObject(Class<?> type, byte[] bytes) {
    TypeHandler<?> typeHandler = findTypeHandler(type);
    return typeHandler.toObject(type, bytes);
  }

  public static String toString(byte[] bytes) {
    return toObject(String.class, bytes).toString();
  }

  public static String toStrFromBuffer(ByteBuffer buffer) {
    Object val = StringType.getTypeHandler().toObject(String.class, buffer);
    if (val == null) {
      return "";
    }
    return val.toString();
  }

  public static ByteBuffer toByteBuffer(Object val) {
    TypeHandler<?> typeHandler = findTypeHandler(val.getClass());
    return typeHandler.toByteBuffer(val);
  }

  public static ByteBuffer toStrByteBuffer(Object val) {
    TypeHandler<?> typeHandler = findTypeHandler(val.getClass());
    String strVal = typeHandler.toString(val);
    return toByteBufferFromStr(strVal);
  }

  public static ByteBuffer toByteBufferFromStr(String val) {
    TypeHandler<?> typeHandler = findTypeHandler(String.class);
    return typeHandler.toByteBuffer(val);
  }

  public static ColumnType getColumnType(String typeName) {
    if (StringUtil.isBlank(typeName)) {
      return null;
    }
    String tmpTypeName = JDBC_TYPE_MAPPING.get(typeName.toUpperCase());
    if (StringUtil.isNotBlank(tmpTypeName)) {
      typeName = tmpTypeName;
    }
    for (ColumnType value : ColumnType.values()) {
      if (typeName.equalsIgnoreCase(value.getTypeName())) {
        return value;
      }
    }
    throw new IllegalArgumentException("Unsupported field type " + typeName);
  }
}
