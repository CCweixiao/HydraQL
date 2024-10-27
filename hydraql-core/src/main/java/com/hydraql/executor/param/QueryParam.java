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
import com.hydraql.rowkey.RowKeyGenerator;
import com.hydraql.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2023/9/6 20:03
 */
public abstract class QueryParam<T> implements Param {
  private final RowKeyGenerator rowKeyGenerator;
  private final String family;
  private final List<String> qualifiers;
  private final TimeRange timeRange;
  private final int readVersions;

  public QueryParam(Builder<T> builder) {
    this.rowKeyGenerator = builder.rowKeyGenerator;
    this.family = builder.family;
    this.qualifiers = builder.qualifiers;
    this.timeRange = builder.timeRange;
    this.readVersions = builder.readVersions;
  }

  public static class TimeRange {
    private final long minTimestamp;
    private final long maxTimestamp;

    public TimeRange(long minTimestamp, long maxTimestamp) {
      if (minTimestamp >= 0L && maxTimestamp >= 0L) {
        if (maxTimestamp < minTimestamp) {
          throw new IllegalArgumentException("maxTimestamp is smaller than minTimestamp");
        }
      } else {
        throw new IllegalArgumentException("Timestamp cannot be negative. minTimestamp:"
            + minTimestamp + ", maxTimestamp:" + maxTimestamp);
      }

      this.minTimestamp = minTimestamp;
      this.maxTimestamp = maxTimestamp;
    }

    public long getMinTimestamp() {
      return minTimestamp;
    }

    public long getMaxTimestamp() {
      return maxTimestamp;
    }

    @Override
    public String toString() {
      return "[" + "minTimestamp=" + minTimestamp + ", maxTimestamp=" + maxTimestamp + ']';
    }
  }

  public abstract static class Builder<T> {
    protected RowKeyGenerator rowKeyGenerator;
    private String family;
    private List<String> qualifiers;
    private TimeRange timeRange;
    private int readVersions;

    protected Builder() {
      this.readVersions = 1;
    }

    public Builder<T> setRowKeyGenerator(RowKeyGenerator rowKeyGenerator) {
      this.rowKeyGenerator = rowKeyGenerator;
      return this;
    }

    public Builder<T> setFamily(String family) {
      Assert.checkArgument(StringUtil.isNotBlank(family), "family must not be null or empty");
      this.family = family;
      return this;
    }

    public Builder<T> setColumn(String family, String qualifier) {
      Assert.checkArgument(StringUtil.isNotBlank(family), "family cannot be null or empty");
      Assert.checkArgument(StringUtil.isNotBlank(qualifier), "qualifier cannot be null or empty");
      return this;
    }

    public Builder<T> setQualifiers(List<String> qualifiers) {
      this.qualifiers = qualifiers;
      return this;
    }

    public Builder<T> setQualifier(String qualifier) {
      if (null == this.qualifiers) {
        this.qualifiers = new ArrayList<>();
      }

      this.qualifiers.add(qualifier);
      return this;
    }

    public Builder<T> setTimeRange(long min, long max) {
      this.timeRange = new TimeRange(min, max);
      return this;
    }

    public Builder<T> setTimestamp(long ts) {
      if (ts < 0 || ts == Long.MAX_VALUE) {
        throw new IllegalArgumentException("invalid ts:" + ts);
      }
      this.timeRange = new TimeRange(ts, ts + 1);
      return this;
    }

    public Builder<T> setReadVersions(int readVersions) {
      if (readVersions <= 0) {
        throw new IllegalArgumentException("versions must be positive.");
      }
      this.readVersions = readVersions;
      return this;
    }

    public abstract T build();
  }

  public RowKeyGenerator getRowKeyGenerator() {
    return rowKeyGenerator;
  }

  public String getFamily() {
    return family;
  }

  public List<String> getQualifiers() {
    return qualifiers;
  }

  public TimeRange getTimeRange() {
    return timeRange;
  }

  public int getReadVersions() {
    return readVersions;
  }
}
