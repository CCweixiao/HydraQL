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

package com.hydraql.adapter.hbtop.mode;

/**
 * @author leojie 2021/1/16 9:14 下午
 */

public class RequestCountPerSecond {
  private long previousLastReportTimestamp;
  private long previousReadRequestCount;
  private long previousFilteredReadRequestCount;
  private long previousWriteRequestCount;
  private long readRequestCountPerSecond;
  private long filteredReadRequestCountPerSecond;
  private long writeRequestCountPerSecond;

  public void refresh(long lastReportTimestamp, long readRequestCount,
      long filteredReadRequestCount, long writeRequestCount) {
    if (previousLastReportTimestamp == 0) {
      previousLastReportTimestamp = lastReportTimestamp;
      previousReadRequestCount = readRequestCount;
      previousFilteredReadRequestCount = filteredReadRequestCount;
      previousWriteRequestCount = writeRequestCount;
    } else if (previousLastReportTimestamp != lastReportTimestamp) {
      readRequestCountPerSecond = (readRequestCount - previousReadRequestCount)
          / ((lastReportTimestamp - previousLastReportTimestamp) / 1000);
      filteredReadRequestCountPerSecond =
          (filteredReadRequestCount - previousFilteredReadRequestCount)
              / ((lastReportTimestamp - previousLastReportTimestamp) / 1000);
      writeRequestCountPerSecond = (writeRequestCount - previousWriteRequestCount)
          / ((lastReportTimestamp - previousLastReportTimestamp) / 1000);

      previousLastReportTimestamp = lastReportTimestamp;
      previousReadRequestCount = readRequestCount;
      previousFilteredReadRequestCount = filteredReadRequestCount;
      previousWriteRequestCount = writeRequestCount;
    }
  }

  public long getReadRequestCountPerSecond() {
    return readRequestCountPerSecond < 0 ? 0 : readRequestCountPerSecond;
  }

  public long getFilteredReadRequestCountPerSecond() {
    return filteredReadRequestCountPerSecond < 0 ? 0 : filteredReadRequestCountPerSecond;
  }

  public long getWriteRequestCountPerSecond() {
    return writeRequestCountPerSecond < 0 ? 0 : writeRequestCountPerSecond;
  }

  public long getRequestCountPerSecond() {
    return getReadRequestCountPerSecond() + getWriteRequestCountPerSecond();
  }
}
