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

package com.hydraql.convertor;

import org.apache.hadoop.hbase.util.Bytes;

import java.math.BigDecimal;

/**
 * @author leojie@apache.org 2024/12/7 12:09
 */
public class ValueConvertor {
  private interface Convert<T> {
    byte[] get(T V);
  }

  private static <T> byte[] toBytes(T v, Convert<T> convert) {
    if (v == null) {
      return null;
    }
    return convert.get(v);
  }

  public static byte[] toBytes(String value) {
    return toBytes(value, Bytes::toBytes);
  }

  public static byte[] toBytes(Boolean value) {
    return toBytes(value, Bytes::toBytes);
  }

  public static byte[] toBytes(Integer value) {
    return toBytes(value, Bytes::toBytes);
  }

  public static byte[] toBytes(Long value) {
    return toBytes(value, Bytes::toBytes);
  }

  public static byte[] toBytes(BigDecimal value) {
    return toBytes(value, Bytes::toBytes);
  }

  public static byte[] toBytes(Float value) {
    return toBytes(value, Bytes::toBytes);
  }

  public static byte[] toBytes(Double value) {
    return toBytes(value, Bytes::toBytes);
  }

  private interface Recover<T> {
    T get(byte[] V);
  }

  private static <T> T getValue(byte[] value, Recover<T> recover) {
    if (value == null) {
      return null;
    }
    return recover.get(value);
  }

  public static String getValueAsString(byte[] value) {
    return getValue(value, Bytes::toString);
  }

  public static Boolean getValueAsBoolean(byte[] value) {
    return getValue(value, Bytes::toBoolean);
  }

  public static Integer getValueAsInteger(byte[] value) {
    return getValue(value, Bytes::toInt);
  }

  public static Long getValueAsLong(byte[] value) {
    return getValue(value, Bytes::toLong);
  }

  public static BigDecimal getValueAsBigDecimal(byte[] value) {
    return getValue(value, Bytes::toBigDecimal);
  }

  public static Float getValueAsFloat(byte[] value) {
    return getValue(value, Bytes::toFloat);
  }

  public static Double getValueAsDouble(byte[] value) {
    return getValue(value, Bytes::toDouble);
  }

}
