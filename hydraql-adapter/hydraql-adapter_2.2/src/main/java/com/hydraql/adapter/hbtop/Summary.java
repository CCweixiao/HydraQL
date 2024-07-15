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

package com.hydraql.adapter.hbtop;

import java.util.List;
import java.util.Objects;

/**
 * @author leojie 2021/1/16 9:56 下午
 */

public class Summary {
  private final String currentTime;
  private final String version;
  private final String clusterId;
  private final int servers;
  private final int liveServers;
  private final int deadServers;
  private final int namespaceCount;
  private final int tableCount;
  private final int snapshotCount;
  private final int regionCount;
  private final int ritCount;
  private final double averageLoad;
  private final long aggregateRequestPerSecond;
  private List<String> liveServerNames;
  private List<String> deadServerNames;

  public Summary(String currentTime, String version, String clusterId, int servers, int liveServers,
      int deadServers, int namespaceCount, int tableCount, int snapshotCount, int regionCount,
      int ritCount, double averageLoad, long aggregateRequestPerSecond) {
    this.currentTime = Objects.requireNonNull(currentTime);
    this.version = Objects.requireNonNull(version);
    this.clusterId = Objects.requireNonNull(clusterId);
    this.servers = servers;
    this.liveServers = liveServers;
    this.deadServers = deadServers;
    this.namespaceCount = namespaceCount;
    this.tableCount = tableCount;
    this.snapshotCount = snapshotCount;
    this.regionCount = regionCount;
    this.ritCount = ritCount;
    this.averageLoad = averageLoad;
    this.aggregateRequestPerSecond = aggregateRequestPerSecond;
  }

  public String getCurrentTime() {
    return currentTime;
  }

  public String getVersion() {
    return version;
  }

  public String getClusterId() {
    return clusterId;
  }

  public int getServers() {
    return servers;
  }

  public int getLiveServers() {
    return liveServers;
  }

  public int getDeadServers() {
    return deadServers;
  }

  public int getNamespaceCount() {
    return namespaceCount;
  }

  public int getTableCount() {
    return tableCount;
  }

  public int getSnapshotCount() {
    return snapshotCount;
  }

  public int getRegionCount() {
    return regionCount;
  }

  public int getRitCount() {
    return ritCount;
  }

  public double getAverageLoad() {
    return averageLoad;
  }

  public long getAggregateRequestPerSecond() {
    return aggregateRequestPerSecond;
  }

  public List<String> getLiveServerNames() {
    return liveServerNames;
  }

  public void setLiveServerNames(List<String> liveServerNames) {
    this.liveServerNames = liveServerNames;
  }

  public List<String> getDeadServerNames() {
    return deadServerNames;
  }

  public void setDeadServerNames(List<String> deadServerNames) {
    this.deadServerNames = deadServerNames;
  }
}
