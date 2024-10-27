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

package com.hydraql.adapter.connection;

import com.hydraql.adapter.context.HTableContext;
import com.hydraql.common.constants.HydraQlClientConfigKeys;
import com.hydraql.exceptions.HydraQLConnectionException;
import com.hydraql.enums.AuthType;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.BufferedMutatorParams;
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
  private final ConcurrentMap<String, BufferedMutator> bufferedMutators;
  private final ReentrantLock connLock = new ReentrantLock();
  private final ReentrantLock bufferMutatorLock = new ReentrantLock();
  private static final AtomicBoolean kerberosEnvInit = new AtomicBoolean(false);
  private static final int KERBEROS_RE_LOGIN_MAX_RETRY = 5;
  private static final long KERBEROS_RE_LOGIN_INTERVAL = 30 * 60 * 1000L;

  private static volatile HBaseConnectionManager instance = null;

  private HBaseConnectionManager() {
    connections = new ConcurrentHashMap<>();
    bufferedMutators = new ConcurrentHashMap<>();
  }

  public static HBaseConnectionManager create() {
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

  /**
   * 获取支持批量写入的BufferedMutator对象<br/>
   * todo 增加BufferedMutator.ExceptionListener的使用场景<br/>
   * <p>
   * 即使在多线程场景中，集群中的同一张表也只会拥有一个BufferedMutator对象<br/>
   * <p>
   * <p>
   * BufferedMutator的使用场景及特点是：
   * </p>
   * <p>
   * 1. BufferedMutator拥有一块缓冲区，客户端提交的数据量大小超过缓冲区大小时会触发自动刷新，在大量数据写入场景中可以减少与服务端的RPC通信次数， 增大客户端写入数据的吞吐量
   * </p>
   * <p>
   * 2. 但如果写入qps较低，或者缓冲区设置较大时，也可以支持检测第一条数据的写入时间，按照一定周期触发强制flush， 但是，周期性刷新缓冲区的功能，只有HBase 2.x才能支持
   * </p>
   * <p>
   * 3. 在极端情况下，如果JVM异常崩溃，缓冲区中的数据可能来不及发送到服务端，会导致数据丢失，如果不能容忍，需要每次提交数据后强制触发flush
   * </p>
   * @param tableContext table configuration context
   * @param configuration hbase client configuration
   * @param connection hbase client connection
   * @return BufferedMutator object of one table
   */
  public BufferedMutator getBufferedMutator(HTableContext tableContext, Configuration configuration,
      Connection connection) {
    if (connection == null || connection.isClosed()) {
      throw new HydraQLConnectionException("The connection must not be null or closed.");
    }
    String tableName = tableContext.getTableName().getNameAsString();
    String uniqueKey = HBaseConnectionUtil.generateUniqueConnectionKey(configuration, tableName);
    LOG.debug("Start to get buffered mutator for unique key: {}.", uniqueKey);
    BufferedMutator bufferedMutator = bufferedMutators.get(uniqueKey);
    if (bufferedMutator != null) {
      return bufferedMutator;
    }
    try {
      bufferMutatorLock.lock();
      bufferedMutator = bufferedMutators.get(uniqueKey);
      if (bufferedMutator != null) {
        return bufferedMutator;
      }

      BufferedMutatorParams mutatorParams = new BufferedMutatorParams(tableContext.getTableName());
      mutatorParams.writeBufferSize(tableContext.getWriteBufferSize());
      mutatorParams.maxKeyValueSize(tableContext.getMaxKeyValueSize());
      mutatorParams.listener(tableContext.getExceptionListener());
      BufferedMutator mutator = connection.getBufferedMutator(mutatorParams);
      bufferedMutators.put(uniqueKey, mutator);
      LOG.debug("Created buffered mutator for table: {} successfully.", tableName);
      return mutator;
    } catch (IOException e) {
      LOG.error("Created buffered mutator for table: {} error, the reason is: ", tableName, e);
      throw new HydraQLConnectionException(e);
    } finally {
      bufferMutatorLock.unlock();
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
    Configuration conf = new org.apache.hadoop.conf.Configuration();
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
      bufferMutatorLock.lock();
      connLock.lock();

      for (BufferedMutator mutator : bufferedMutators.values()) {
        mutator.close();
      }
      bufferedMutators.clear();

      for (Connection connection : connections.values()) {
        connection.close();
      }
      connections.clear();
    } catch (IOException e) {
      LOG.warn("An exception occurred while destroy resources.", e);
    } finally {
      bufferMutatorLock.unlock();
      connLock.unlock();
    }
  }
}
