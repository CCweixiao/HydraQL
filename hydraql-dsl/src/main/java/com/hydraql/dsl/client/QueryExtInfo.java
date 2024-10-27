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

package com.hydraql.dsl.client;

import com.hydraql.util.Assert;

import java.util.Date;

/**
 * @author leojie 2020/11/28 9:58 上午
 */
public class QueryExtInfo {
  private boolean isMaxVersionSet;
  private int maxVersions;

  private boolean isTimeRangeSet;
  private long minStamp;
  private long maxStamp;

  private boolean isLimitSet;
  private int limit;

  public QueryExtInfo() {
  }

  public void setMaxVersions(int maxVersions) {
    Assert.checkArgument(maxVersions > 0, String
        .format("The max version must > 0, but the " + "current max version is %s.", maxVersions));
    this.maxVersions = maxVersions;
    this.isMaxVersionSet = true;
  }

  public void setTimeStamp(long ts) {
    setTimeRange(ts, ts + 1);
  }

  public void setTimeRange(Date minStamp, Date maxStamp) {
    Assert.checkNotNull(minStamp);
    Assert.checkNotNull(maxStamp);
    setTimeRange(minStamp.getTime(), maxStamp.getTime());
  }

  public void setTimeRange(long minStamp, long maxStamp) {
    Assert.checkArgument(minStamp > 0, "Minimum timestamp must be a positive number.");
    Assert.checkArgument(maxStamp > 0, "Maximum timestamp must be a positive number.");
    Assert.checkArgument(minStamp <= maxStamp,
      String.format("Minimum timestamp must be <= maximum timestamp,"
          + " but current max timestamp is %s, min timestamp is %s.",
        maxStamp, minStamp));
    this.minStamp = minStamp;
    this.maxStamp = maxStamp;
    this.isTimeRangeSet = true;
  }

  public void setLimit(int limit) {
    Assert.checkArgument(limit > 0,
      "The value of limit must be > 0," + " but the current limit is " + limit);
    this.limit = limit;
    this.isLimitSet = true;
  }

  public boolean isLimitSet() {
    return isLimitSet;
  }

  public int getLimit() {
    return limit;
  }

  public boolean isMaxVersionSet() {
    return isMaxVersionSet;
  }

  public int getMaxVersions() {
    return maxVersions;
  }

  public boolean isTimeRangeSet() {
    return isTimeRangeSet;
  }

  public long getMinStamp() {
    return minStamp;
  }

  public long getMaxStamp() {
    return maxStamp;
  }
}
