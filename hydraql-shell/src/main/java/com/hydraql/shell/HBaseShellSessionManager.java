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

package com.hydraql.shell;

import com.hydraql.common.constants.HydraQlClientConfigKeys;
import com.hydraql.common.util.DigestUtil;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.HConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author leojie 2023/7/5 18:01
 */
public class HBaseShellSessionManager {
  private final static Logger LOG = LoggerFactory.getLogger(HBaseShellCommands.class);
  private static Map<String, HBaseShellSession> shellSessionMap = null;

  public static HBaseShellSession getHBaseShellSession(Properties prop) {
    String sessionId = generateUniqueSessionId(prop);
    LOG.info(String.format("Starting get hbase shell session of %s ......", sessionId));
    if (shellSessionMap == null || !shellSessionMap.containsKey(sessionId)) {
      synchronized (HBaseShellSessionManager.class) {
        if (shellSessionMap == null || !shellSessionMap.containsKey(sessionId)) {
          if (shellSessionMap == null) {
            shellSessionMap = new HashMap<>(2);
          }
          if (!shellSessionMap.containsKey(sessionId)) {
            HBaseShellSession shellSession = HBaseShellSession.sessionBuilder().sessionId(sessionId)
                .sessionInitTimeoutMs(HBaseShellSessionConfig.shellSessionInitedTimeout(prop))
                .sessionInitMaxTimes(HBaseShellSessionConfig.shellSessionInitedRetryTimes(prop))
                .sessionInitRetryInterval(
                  HBaseShellSessionConfig.shellSessionInitedRetryInterval(prop))
                .sessionIdleMs(HBaseShellSessionConfig.shellSessionIdle(prop))
                .sessionDebugLog(HBaseShellSessionConfig.shellSessionDebugLog(prop))
                .properties(prop).build();
            shellSession.open();
            shellSessionMap.put(sessionId, shellSession);
            return shellSession;
          }
        }
      }
    }
    return shellSessionMap.get(sessionId);
  }

  private static String generateUniqueSessionId(Properties properties) {
    String zkQuorum = properties.getProperty(HConstants.ZOOKEEPER_QUORUM);
    if (StringUtil.isBlank(zkQuorum)) {
      throw new IllegalArgumentException("The zkQuorum must be specified.");
    }
    String zkClientPort = properties.getProperty(HConstants.ZOOKEEPER_CLIENT_PORT);
    if (StringUtil.isBlank(zkClientPort)) {
      throw new IllegalArgumentException("The zkClientPort must be specified.");
    }
    String proxyUser = properties.getProperty(HydraQlClientConfigKeys.KERBEROS_PROXY_USER);
    String connectionUniqueKey = DigestUtil.md5Hex(zkQuorum.concat(zkClientPort));
    if (StringUtil.isNotBlank(proxyUser)) {
      connectionUniqueKey = connectionUniqueKey + "#" + proxyUser;
    }
    String clusterId = properties.getProperty("hbase.shell.session.cluster");
    String sessionId = "";
    if (StringUtil.isNotBlank(clusterId)) {
      sessionId = clusterId + "#";
    }
    sessionId = sessionId + connectionUniqueKey;
    return sessionId;
  }

}
