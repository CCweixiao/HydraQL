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

package com.hydraql.common.query;

import com.hydraql.common.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2022/11/5 10:31
 */
public class ScanParams {
  private final String familyName;
  private final List<String> columnNames;
  private final String rowPrefix;
  private final String startRow;
  private final boolean inclusiveStartRow;
  private final String stopRow;
  private final boolean inclusiveStopRow;
  private final long timestamp;
  private final long minTimestamp;
  private final long maxTimestamp;
  private final int versions;
  private final boolean reversed;
  private final int caching;
  private final int batch;
  private final long maxResultSize;
  private final int limit;
  private final IHBaseFilter<?> filter;
  private final boolean cacheBlocks;

  public ScanParams(Builder builder) {
    this.familyName = builder.familyName;
    this.columnNames = builder.columnNames;
    this.rowPrefix = builder.rowPrefix;
    this.startRow = builder.startRow;
    this.inclusiveStartRow = builder.inclusiveStartRow;
    this.stopRow = builder.stopRow;
    this.inclusiveStopRow = builder.inclusiveStopRow;
    this.timestamp = builder.timestamp;
    this.minTimestamp = builder.minTimestamp;
    this.maxTimestamp = builder.maxTimestamp;
    this.versions = builder.versions;
    this.reversed = builder.reversed;
    this.caching = builder.caching;
    this.batch = builder.batch;
    this.maxResultSize = builder.maxResultSize;
    this.limit = builder.limit;
    this.filter = builder.filter;
    this.cacheBlocks = builder.cacheBlocks;
  }

  public static class Builder {
    private String familyName;
    private List<String> columnNames;
    private String rowPrefix;
    private String startRow;
    private boolean inclusiveStartRow;
    private String stopRow;
    private boolean inclusiveStopRow;
    private long timestamp;
    private long minTimestamp;
    private long maxTimestamp;
    private int versions;
    private boolean reversed;
    private int caching;
    private int batch;
    private long maxResultSize;
    private int limit;
    private IHBaseFilter<?> filter;
    private boolean cacheBlocks;

    private Builder() {
    }

    private Builder of() {
      this.inclusiveStartRow = true;
      this.inclusiveStopRow = false;
      this.versions = 1;
      this.cacheBlocks = false;
      return this;
    }

    public Builder familyName(String familyName) {
      this.familyName = familyName;
      return this;
    }

    public Builder columnNames(List<String> columnNames) {
      this.columnNames = columnNames;
      return this;
    }

    public Builder columnName(String colName) {
      if (this.columnNames == null) {
        this.columnNames = new ArrayList<>();
      }
      this.columnNames.add(colName);
      return this;
    }

    public Builder rowPrefix(String rowPrefix) {
      this.rowPrefix = rowPrefix;
      return this;
    }

    public Builder startRow(String startRow) {
      this.startRow = startRow;
      return this;
    }

    public Builder inclusiveStartRow(boolean inclusiveStartRow) {
      this.inclusiveStartRow = inclusiveStartRow;
      return this;
    }

    public Builder stopRow(String stopRow) {
      this.stopRow = stopRow;
      return this;
    }

    public Builder inclusiveStopRow(boolean inclusiveStopRow) {
      this.inclusiveStopRow = inclusiveStopRow;
      return this;
    }

    public Builder timestamp(long timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public Builder minTimestamp(long minTimestamp) {
      this.minTimestamp = minTimestamp;
      return this;
    }

    public Builder maxTimestamp(long maxTimestamp) {
      this.maxTimestamp = maxTimestamp;
      return this;
    }

    public Builder versions(int versions) {
      if (versions < 1) {
        throw new IllegalArgumentException("The version must be a positive number.");
      }
      this.versions = versions;
      return this;
    }

    public Builder reversed(boolean reversed) {
      this.reversed = reversed;
      return this;
    }

    public Builder caching(int caching) {
      this.caching = caching;
      return this;
    }

    public Builder batch(int batch) {
      this.batch = batch;
      return this;
    }

    public Builder limit(int limit) {
      this.limit = limit;
      return this;
    }

    public Builder maxResultSize(long maxResultSize) {
      this.maxResultSize = maxResultSize;
      return this;
    }

    public Builder filter(IHBaseFilter<?> filter) {
      this.filter = filter;
      return this;
    }

    public Builder cacheBlocks(boolean cacheBlocks) {
      this.cacheBlocks = cacheBlocks;
      return this;
    }

    public ScanParams build() {
      return new ScanParams(this);
    }
  }

  public String getFamilyName() {
    return familyName;
  }

  public List<String> getColumnNames() {
    return columnNames;
  }

  public String getRowPrefix() {
    return rowPrefix;
  }

  public String getStartRow() {
    return startRow;
  }

  public boolean isInclusiveStartRow() {
    return inclusiveStartRow;
  }

  public String getStopRow() {
    return stopRow;
  }

  public boolean isInclusiveStopRow() {
    return inclusiveStopRow;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public long getMinTimestamp() {
    return minTimestamp;
  }

  public long getMaxTimestamp() {
    return maxTimestamp;
  }

  public int getVersions() {
    return versions;
  }

  public boolean isReversed() {
    return reversed;
  }

  public int getCaching() {
    if (caching <= 0) {
      return 1000;
    }
    return caching;
  }

  public int getBatch() {
    return batch;
  }

  public long getMaxResultSize() {
    return maxResultSize;
  }

  public int getLimit() {
    return limit;
  }

  public IHBaseFilter<?> getFilter() {
    return filter;
  }

  public boolean isCacheBlocks() {
    return cacheBlocks;
  }

  public static Builder of() {
    return new Builder().of();
  }

  public boolean onlyFamily() {
    return FamilyQualifierUtil.familyNameOnly(this.getFamilyName(), this.getColumnNames());
  }

  public boolean familyWithQualifiers() {
    return FamilyQualifierUtil.familyAndColumnNames(this.getFamilyName(), this.getColumnNames());
  }

  public boolean startRowIsSet() {
    return StringUtil.isNotBlank(this.getStartRow());
  }

  public boolean endRowIsSet() {
    return StringUtil.isNotBlank(this.getStopRow());
  }

  public boolean timeRangeIsSet() {
    return this.getMinTimestamp() > 0 && this.getMaxTimestamp() > 0;
  }

  public boolean timestampIsSet() {
    return this.getTimestamp() > 0;
  }

  @Override
  public String toString() {
    return "ScanParams{" + "familyName='" + familyName + '\'' + ", columnNames=" + columnNames
        + ", startRow='" + startRow + '\'' + ", inclusiveStartRow=" + inclusiveStartRow
        + ", stopRow='" + stopRow + '\'' + ", inclusiveStopRow=" + inclusiveStopRow + ", timestamp="
        + timestamp + ", minTimestamp=" + minTimestamp + ", maxTimestamp=" + maxTimestamp
        + ", versions=" + versions + ", reversed=" + reversed + ", caching=" + caching + ", batch="
        + batch + ", maxResultSize=" + maxResultSize + ", limit=" + limit + ", cacheBlocks="
        + cacheBlocks + '}';
  }
}
