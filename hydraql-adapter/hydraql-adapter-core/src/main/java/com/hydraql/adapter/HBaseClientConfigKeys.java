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

package com.hydraql.adapter;

/**
 * @author leojie 2024/4/6 22:17
 */
public interface HBaseClientConfigKeys {
  String PREFIX = "hbase.client.";

  String HEDGED_READ_CLUSTER_CONF_PREFIX = "hedged.read.";

  interface HedgedRead {
    String PREFIX = HBaseClientConfigKeys.PREFIX + "hedged.read.";

    String STRATEGY = PREFIX + "strategy";

    String STRATEGY_DEFAULT = "none";

    String WRITE_DISABLE = PREFIX + "write.disable";

    boolean WRITE_DISABLE_DEFAULT = true;

    String OVERALL_TIMEOUT_MILLIS = PREFIX + "overall.timeout.millis";

    long OVERALL_TIMEOUT_MILLIS_DEFAULT = 1000;

    String THRESHOLD_MILLIS_KEY = PREFIX + "threshold.millis";

    long THRESHOLD_MILLIS_DEFAULT = 500;

    String THREADPOOL_SIZE_KEY = PREFIX + "threadpool.size";

    int THREADPOOL_SIZE_DEFAULT = 0;
  }

  String HBASE_CLIENT_SCANNER_CACHING = PREFIX + "scanner.caching";
  int HBASE_CLIENT_SCANNER_CACHING_DEFAULT = 1000;

  String ZOOKEEPER_QUORUM = "hbase.zookeeper.quorum";
  String ZOOKEEPER_CLIENT_PORT = "hbase.zookeeper.property.clientPort";
  String ZOOKEEPER_NODE_PARENT = "zookeeper.znode.parent";
}
