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
import com.hydraql.common.util.StringUtil;
import com.hydraql.enums.AuthType;
import com.hydraql.exceptions.HydraQLConnectionException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.security.UserGroupInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author leojie 2021/2/9 11:15 下午
 */
public class HBaseConnectionManager {
  private static final Logger LOG = LoggerFactory.getLogger(HBaseConnectionManager.class);
  private final ConcurrentMap<String, Connection> connections;
  private final ReentrantLock connLock = new ReentrantLock();
  private static final AtomicBoolean kerberosEnvInit = new AtomicBoolean(false);
  private static final int KERBEROS_RE_LOGIN_MAX_RETRY = 5;
  private static final long KERBEROS_RE_LOGIN_INTERVAL = 30 * 60 * 1000L;

  private static volatile HBaseConnectionManager instance = null;

  private HBaseConnectionManager() {
    connections = new ConcurrentHashMap<>();
  }

  public static HBaseConnectionManager getInstance() {
    if (instance == null) {
      synchronized (HBaseConnectionManager.class) {
        if (instance == null) {
          instance = new HBaseConnectionManager();
        }
      }
    }
    return instance;
  }

  public Connection getConnection(Configuration configuration) {
    if (configuration == null) {
      throw new HydraQLConnectionException("The configuration of cluster must not be null.");
    }
    String connKey = HBaseConnectionUtil.generateUniqueConnectionKey(configuration);
    LOG.debug("Start to get connection for unique key: {}.", connKey);
    Connection conn = connections.get(connKey);
    if (conn != null) {
      return conn;
    }

    try {
      connLock.lock();
      conn = connections.get(connKey);
      if (conn != null) {
        return conn;
      }

      if (isKerberosAuthType(configuration) && kerberosEnvInit.compareAndSet(false, true)) {
        doKerberosLogin(configuration);
      }

      Connection connection;
      if (HBaseConnectionUtil.isProxyUserEnabled(configuration)) {
        String proxyUser = HBaseConnectionUtil.proxyUser(configuration);
        UserGroupInformation ugi =
            UserGroupInformation.createProxyUser(proxyUser, UserGroupInformation.getLoginUser());
        connection = ugi.doAs((PrivilegedAction<Connection>) () -> {
          try {
            return ConnectionFactory.createConnection(configuration);
          } catch (IOException e) {
            LOG.error("Created hbase client connection error, the reason is ", e);
            throw new HydraQLConnectionException(e);
          }
        });
        LOG.debug("Created connection {} with proxy user {} successfully", connKey, proxyUser);
      } else {
        connection = ConnectionFactory.createConnection(configuration);
        LOG.debug("Created connection {} successfully.", connKey);
      }
      connections.put(connKey, connection);
      return connection;
    } catch (IOException e) {
      LOG.error("Created hbase client connection error, the reason is ", e);
      throw new HydraQLConnectionException(e);
    } finally {
      connLock.unlock();
    }
  }

  private void doKerberosLogin(Configuration configuration) {
    String principal = configuration.get(HydraQlClientConfigKeys.KERBEROS_PRINCIPAL);
    if (StringUtil.isBlank(principal)) {
      kerberosEnvInit.set(false);
      throw new HydraQLConnectionException("The kerberos principal is not empty.");
    }
    String keytab = configuration.get(HydraQlClientConfigKeys.KERBEROS_KEYTAB_FILE);
    if (StringUtil.isBlank(keytab)) {
      kerberosEnvInit.set(false);
      throw new HydraQLConnectionException("The keytab file path is not empty.");
    }
    File file = new File(keytab);
    if (!file.exists()) {
      kerberosEnvInit.set(false);
      throw new HydraQLConnectionException("The keytab file is not exists.");
    }
    if (!file.isFile()) {
      kerberosEnvInit.set(false);
      throw new HydraQLConnectionException("The keytab file is not a file.");
    }
    try {
      configuration.set(HydraQlClientConfigKeys.HADOOP_SECURITY_AUTH,
        AuthType.KERBEROS.getAuthType());
      UserGroupInformation.setConfiguration(configuration);
      UserGroupInformation.loginUserFromKeytab(principal, keytab);
      LOG.debug("Login successfully via keytab: {} and principal: {}", keytab, principal);
      doKerberosReLogin();
    } catch (IOException e) {
      kerberosEnvInit.set(false);
      throw new HydraQLConnectionException(e);
    }
  }

  private void doKerberosReLogin() {
    if (!UserGroupInformation.isSecurityEnabled()) {
      return;
    }

    Thread reLoginThread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
          try {
            Thread.sleep(KERBEROS_RE_LOGIN_INTERVAL);
          } catch (InterruptedException e) {
            LOG.warn("Ignore error", e);
          }
          int times = 0;
          while (times < KERBEROS_RE_LOGIN_MAX_RETRY) {
            if (runKerberosLogin()) {
              LOG.info("Ran kerberos re login command successfully.");
              break;
            } else {
              times++;
              LOG.info("Run kerberos re login failed for {} time(s).", times);
            }
          }
        }
      }
    });
    reLoginThread.setName("KerberosReLoginThread");
    reLoginThread.setDaemon(true);
    reLoginThread.start();
  }

  private boolean runKerberosLogin() {
    Configuration conf = new Configuration();
    conf.set("hadoop.security.authentication", AuthType.KERBEROS.getAuthType());
    UserGroupInformation.setConfiguration(conf);
    try {
      if (UserGroupInformation.isLoginKeytabBased()) {
        LOG.info("Trying re login from keytab.");
        UserGroupInformation.getLoginUser().reloginFromKeytab();
        return true;
      } else if (UserGroupInformation.isLoginTicketBased()) {
        LOG.info("Trying re login from ticket cache");
        UserGroupInformation.getLoginUser().reloginFromTicketCache();
        return true;
      }
    } catch (Exception e) {
      LOG.error("Unable to run kinit.", e);
    }
    return false;
  }

  private boolean isKerberosAuthType(Configuration configuration) {
    String authType = configuration.get(HydraQlClientConfigKeys.HBASE_SECURITY_AUTH, "");
    return AuthType.isKerberos(authType);
  }

  public void destroy() {
    try {
      connLock.lock();
      for (Connection connection : connections.values()) {
        if (null == connection) {
          continue;
        }
        connection.close();
      }
      connections.clear();
    } catch (IOException e) {
      LOG.warn("An exception occurred while destroy resources.", e);
    } finally {
      connLock.unlock();
    }
  }
}
