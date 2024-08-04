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

import com.hydraql.common.util.StringUtil;

import java.util.Properties;

/**
 * @author leojie 2023/7/6 22:17
 */
public class HBaseShellSessionConfig {
  private static final String HBASE_SHELL_SESSION_INIT_TIMEOUT_MS =
      "hbase.shell.session.init.timeout.ms";
  private static final long DEFAULT_SHELL_SESSION_INIT_TIMEOUT_MS = 2 * 60 * 1000L;

  private static final String HBASE_SHELL_SESSION_INIT_MAX_TIMES =
      "hbase.shell.session.init.max.times";
  private static final int DEFAULT_SHELL_SESSION_INIT_MAX_TIMES = 10;

  private static final String HBASE_SHELL_SESSION_INIT_RETRY_INTERVAL_MS =
      "hbase.shell.session.init.retry.interval";
  private static final long DEFAULT_SHELL_SESSION_INIT_RETRY_INTERVAL_MS = 500L;

  private static final String HBASE_SHELL_SESSION_IDLE_MS = "hbase.shell.session.idle.ms";
  private static final long DEFAULT_SHELL_SESSION_IDLE_MS = 2 * 60 * 60 * 1000L;

  private static final String HBASE_SHELL_DEBUG_LOG = "hbase.shell.session.debug.log";
  private static final boolean DEFAULT_SHELL_DEBUG_LOG = false;

  public static long shellSessionInitedTimeout(Properties properties) {
    String val = properties.getProperty(HBASE_SHELL_SESSION_INIT_TIMEOUT_MS);
    if (StringUtil.isBlank(val)) {
      return DEFAULT_SHELL_SESSION_INIT_TIMEOUT_MS;
    }

    try {
      long timeout = Long.parseLong(val);
      if (timeout < 1) {
        return DEFAULT_SHELL_SESSION_INIT_TIMEOUT_MS;
      }
      return timeout;
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static int shellSessionInitedRetryTimes(Properties properties) {
    String val = properties.getProperty(HBASE_SHELL_SESSION_INIT_MAX_TIMES);
    if (StringUtil.isBlank(val)) {
      return DEFAULT_SHELL_SESSION_INIT_MAX_TIMES;
    }
    try {
      int times = Integer.parseInt(val);
      if (times < 1) {
        return DEFAULT_SHELL_SESSION_INIT_MAX_TIMES;
      }
      return times;
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static long shellSessionInitedRetryInterval(Properties properties) {
    String val = properties.getProperty(HBASE_SHELL_SESSION_INIT_RETRY_INTERVAL_MS);
    if (StringUtil.isBlank(val)) {
      return DEFAULT_SHELL_SESSION_INIT_RETRY_INTERVAL_MS;
    }
    try {
      long interval = Long.parseLong(val);
      if (interval < 1) {
        return DEFAULT_SHELL_SESSION_INIT_RETRY_INTERVAL_MS;
      }
      return interval;
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static long shellSessionIdle(Properties properties) {
    String val = properties.getProperty(HBASE_SHELL_SESSION_IDLE_MS);
    if (StringUtil.isBlank(val)) {
      return DEFAULT_SHELL_SESSION_IDLE_MS;
    }
    try {
      long idle = Long.parseLong(val);
      if (idle < 1) {
        return DEFAULT_SHELL_SESSION_IDLE_MS;
      }
      return idle;
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static boolean shellSessionDebugLog(Properties properties) {
    String val = properties.getProperty(HBASE_SHELL_DEBUG_LOG);
    if (StringUtil.isBlank(val)) {
      return DEFAULT_SHELL_DEBUG_LOG;
    }
    return "true".equalsIgnoreCase(val);
  }
}
