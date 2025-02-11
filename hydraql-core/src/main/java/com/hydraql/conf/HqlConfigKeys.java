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

package com.hydraql.conf;

/**
 * @author leojie 2024/4/6 22:17
 */
public interface HqlConfigKeys {
  String HBASE_CLIENT_PREFIX = "hbase.client.";

  String HEDGED_READ_CLIENT_CONF_PREFIX = "hedged.read.";

  interface HedgedRead {
    String PREFIX = HBASE_CLIENT_PREFIX + HEDGED_READ_CLIENT_CONF_PREFIX;

    String STRATEGY = PREFIX + "strategy";

    String DEFAULT_STRATEGY = "NONE";

    String WRITE_ENABLE = PREFIX + "write.enable";

    boolean DEFAULT_WRITE_ENABLE = false;

    String OVERALL_TIMEOUT_MILLIS = PREFIX + "overall.timeout.millis";

    long DEFAULT_OVERALL_TIMEOUT_MILLIS = 1000;

    String THRESHOLD_MILLIS_KEY = PREFIX + "threshold.millis";

    long DEFAULT_THRESHOLD_MILLIS = 500;

    String THREADPOOL_SIZE_KEY = PREFIX + "threadpool.size";

    int DEFAULT_THREADPOOL_SIZE = 0;
  }
}
