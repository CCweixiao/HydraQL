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

package com.hydraql.session;

import com.hydraql.common.constants.HydraQlClientConfigKeys;
import com.hydraql.common.util.DigestUtil;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;

/**
 * @author leojie 2023/7/6 08:18
 */
public class HBaseConnectionUtil {

  public static String generateUniqueConnectionKey(Configuration configuration, String tableName) {
    return generateUniqueConnectionKey(configuration).concat("#").concat(tableName);
  }

  public static String generateUniqueConnectionKey(Configuration configuration) {
    String zkQuorum = configuration.get(HydraQlClientConfigKeys.ZOOKEEPER_QUORUM);
    String zkClientPort = configuration.get(HydraQlClientConfigKeys.ZOOKEEPER_CLIENT_PORT);
    String zkNodeParent = configuration.get(HydraQlClientConfigKeys.ZOOKEEPER_NODE_PARENT);
    String uniqueKey = generateUniqueConnectionKey(zkQuorum, zkClientPort, zkNodeParent);
    if (isProxyUserEnabled(configuration)) {
      uniqueKey = uniqueKey + "#" + proxyUser(configuration);
    }
    return uniqueKey;
  }

  public static boolean isProxyUserEnabled(Configuration configuration) {
    String val = proxyUser(configuration);
    return StringUtil.isNotBlank(val);
  }

  public static String proxyUser(Configuration configuration) {
    return configuration.get(HydraQlClientConfigKeys.KERBEROS_PROXY_USER);
  }

  private static String generateUniqueConnectionKey(String zkQuorum, String zkClientPort,
      String zkNodeParent) {
    if (StringUtil.isBlank(zkQuorum)) {
      throw new IllegalStateException(HydraQlClientConfigKeys.ZOOKEEPER_QUORUM + " must be set.");
    }
    if (StringUtil.isBlank(zkClientPort)) {
      throw new IllegalStateException(
          HydraQlClientConfigKeys.ZOOKEEPER_CLIENT_PORT + " must be set.");
    }
    if (StringUtil.isBlank(zkNodeParent)) {
      throw new IllegalStateException(
          HydraQlClientConfigKeys.ZOOKEEPER_NODE_PARENT + " must be set.");
    }
    return DigestUtil.md5Hex(zkQuorum.concat(zkClientPort).concat(zkNodeParent));
  }
}
