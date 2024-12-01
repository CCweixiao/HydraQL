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

package com.hydraql.result;

import org.apache.hadoop.hbase.util.Bytes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author leojie@apache.org 2024/11/17 16:40
 */
public abstract class KVResult {
  private final Map<ColumnInfo, ResultData> valueHolder;

  protected KVResult() {
    this.valueHolder = new HashMap<>();
  }

  private static class ColumnInfo {
    private final byte[] family;
    private final byte[] qualifier;

    public ColumnInfo(byte[] family, byte[] qualifier) {
      this.family = family;
      this.qualifier = qualifier;
    }

    public byte[] getFamily() {
      return family;
    }

    public byte[] getQualifier() {
      return qualifier;
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) return true;
      if (!(object instanceof ColumnInfo)) return false;
      ColumnInfo fieldData = (ColumnInfo) object;
      return Objects.deepEquals(getFamily(), fieldData.getFamily())
          && Objects.deepEquals(getQualifier(), fieldData.getQualifier());
    }

    @Override
    public int hashCode() {
      return Objects.hash(Arrays.hashCode(getFamily()), Arrays.hashCode(getQualifier()));
    }
  }

  private static class ResultData {
    private final byte[] value;
    private final long timestamp;

    public ResultData(byte[] value, long timestamp) {
      this.value = value;
      this.timestamp = timestamp;
    }

    public ResultData(byte[] value) {
      this(value, -1L);
    }

    public long getTimestamp() {
      return timestamp;
    }

    public byte[] getValue() {
      return value;
    }

    interface Supplier<T> {
      T get(byte[] V);
    }
  }

  public byte[] getValue(String family, String qualifier) {
    ResultData resultData =
        this.valueHolder.get(new ColumnInfo(Bytes.toBytes(family), Bytes.toBytes(qualifier)));
    if (resultData == null) {
      return null;
    }
    return resultData.getValue();
  }

  public void appendValue(byte[] family, byte[] qualifier, byte[] value, long timestamp) {
    this.valueHolder.put(new ColumnInfo(family, qualifier), new ResultData(value, timestamp));
  }

  public String getValueAsString(String family, String qualifier) {
    return getValue(family, qualifier, Bytes::toString);
  }

  public Integer getValueAsInt(String family, String qualifier) {
    return getValue(family, qualifier, Bytes::toInt);
  }

  public Long getValueAsLong(String family, String qualifier) {
    return getValue(family, qualifier, Bytes::toLong);
  }

  public Double getValueAsDouble(String family, String qualifier) {
    return getValue(family, qualifier, Bytes::toDouble);
  }

  public Float getValueAsFloat(String family, String qualifier) {
    return getValue(family, qualifier, Bytes::toFloat);
  }

  public Boolean getValueAsBoolean(String family, String qualifier) {
    return getValue(family, qualifier, Bytes::toBoolean);
  }

  public BigDecimal getValueAsBigDecimal(String family, String qualifier) {
    return getValue(family, qualifier, Bytes::toBigDecimal);
  }

  private <T> T getValue(String family, String qualifier, ResultData.Supplier<T> valueParser) {
    byte[] value = getValue(family, qualifier);
    if (value == null) {
      return null;
    }
    return valueParser.get(value);
  }
}
