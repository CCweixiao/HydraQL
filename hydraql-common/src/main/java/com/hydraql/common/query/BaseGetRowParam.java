/**
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

package com.hydraql.common.query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2023/9/6 20:03
 */
public abstract class BaseGetRowParam<T> {
  private final String family;
  private final List<String> qualifiers;
  private final TimeRange timeRange;
  private final int versions;

  public BaseGetRowParam(Builder<T> builder) {
    this.family = builder.family;
    this.qualifiers = builder.qualifiers;
    this.timeRange = builder.timeRange;
    this.versions = builder.versions;
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
    private String family;
    private List<String> qualifiers;
    private TimeRange timeRange;
    private int versions;

    protected Builder() {
      this.versions = 1;
    }

    public Builder<T> family(String family) {
      this.family = family;
      return this;
    }

    public Builder<T> qualifiers(List<String> qualifiers) {
      this.qualifiers = qualifiers;
      return this;
    }

    public Builder<T> qualifier(String qualifier) {
      if (this.qualifiers == null) {
        this.qualifiers = new ArrayList<>();
      }
      this.qualifiers.add(qualifier);
      return this;
    }

    public Builder<T> withTimeRange(long min, long max) {
      this.timeRange = new TimeRange(min, max);
      return this;
    }

    public Builder<T> withTimestamp(long ts) {
      if (ts < 0 || ts == Long.MAX_VALUE) {
        throw new IllegalArgumentException("invalid ts:" + ts);
      }
      this.timeRange = new TimeRange(ts, ts + 1);
      return this;
    }

    public Builder<T> versions(int versions) {
      if (versions <= 0) {
        throw new IllegalArgumentException("versions must be positive.");
      }
      this.versions = versions;
      return this;
    }

    public abstract T build();
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

  public int getVersions() {
    return versions;
  }

  public boolean onlyFamily() {
    return FamilyQualifierUtil.familyNameOnly(this.getFamily(), this.getQualifiers());
  }

  public boolean familyWithQualifiers() {
    return FamilyQualifierUtil.familyAndColumnNames(this.getFamily(), this.getQualifiers());
  }
}
