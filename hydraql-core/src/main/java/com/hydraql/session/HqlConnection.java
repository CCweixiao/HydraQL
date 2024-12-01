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

import com.hydraql.AbstractHqlTable;
import com.hydraql.conf.BufferedMutatorOptions;
import com.hydraql.mutator.WrapperBufferedMutator;
import com.hydraql.mutator.WrapperBufferedMutatorImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.BufferedMutatorParams;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author leojie@apache.org 2024/9/4 22:59
 */
public class HqlConnection {
  private static final Logger LOG = LoggerFactory.getLogger(HqlConnection.class);

  private final BufferedMutatorOptions bufferedMutatorOptions;
  private final String tableName;
  private final Configuration configuration;
  private final Configuration hedgedConfiguration;
  private Table table;
  private Table hedgedTable;
  private WrapperBufferedMutator wrapperBufferedMutator;
  private WrapperBufferedMutator hedgedReadWrapperBufferedMutator;

  public HqlConnection(AbstractHqlTable table) throws IOException {
    this.bufferedMutatorOptions = table.getBufferedMutatorOptions();
    this.tableName = table.getTableName();
    this.configuration = table.getConfiguration().getHBaseConfiguration();
    this.hedgedConfiguration = table.getConfiguration().getHBaseHedgedReadConfiguration();
    this.openHydraQlConnection();
    this.openHedgedHydraQlConnection();
  }

  private void openHydraQlConnection() throws IOException {
    Connection connection = HBaseConnectionManager.getInstance().getConnection(configuration);
    if (null == connection || connection.isClosed()) {
      throw new IOException("Failed to initialize the hbase connection.");
    }

    this.table = connection.getTable(TableName.valueOf(tableName));
    BufferedMutator bufferedMutator = createBufferedMutator(connection);
    this.wrapperBufferedMutator = new WrapperBufferedMutatorImpl(this, bufferedMutator);
    LOG.info("Opened HydraQL connection.");
  }

  private void openHedgedHydraQlConnection() throws IOException {
    if (null == this.hedgedConfiguration) {
      LOG.info("The hedged read configuration is null, and will not to create hbase connection.");
      return;
    }

    Connection connection = HBaseConnectionManager.getInstance().getConnection(hedgedConfiguration);
    if (null == connection || connection.isClosed()) {
      throw new IOException("Failed to initialize the hbase hedged read connection.");
    }

    this.hedgedTable = connection.getTable(TableName.valueOf(tableName));
    BufferedMutator bufferedMutator = createBufferedMutator(connection);
    this.hedgedReadWrapperBufferedMutator = new WrapperBufferedMutatorImpl(this, bufferedMutator);
    LOG.info("Opened HydraQL hedged read connection.");
  }

  private BufferedMutator createBufferedMutator(Connection connection) throws IOException {
    BufferedMutatorParams mutatorParams = new BufferedMutatorParams(TableName.valueOf(tableName));
    mutatorParams.writeBufferSize(this.bufferedMutatorOptions.getWriteBufferSize());
    mutatorParams.maxKeyValueSize(this.bufferedMutatorOptions.getMaxKeyValueSize());
    mutatorParams.listener(this.bufferedMutatorOptions.getExceptionListener());
    return connection.getBufferedMutator(mutatorParams);
  }

  public void close() throws IOException {
    if (null != this.table) {
      this.table.close();
    }
    if (null != this.wrapperBufferedMutator) {
      this.wrapperBufferedMutator.close();
    }
    if (null != this.hedgedTable) {
      this.hedgedTable.close();
    }
    if (null != this.hedgedReadWrapperBufferedMutator) {
      this.hedgedReadWrapperBufferedMutator.close();
    }
  }

  public String getTableName() {
    return this.tableName;
  }

  public Table getTable() {
    return this.table;
  }

  public Table getHedgedTable() {
    return this.hedgedTable;
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
   * @return BufferedMutator object of one table
   */
  public WrapperBufferedMutator getWrapperBufferedMutator() {
    return wrapperBufferedMutator;
  }

  public WrapperBufferedMutator getHedgedReadWrapperBufferedMutator() {
    return hedgedReadWrapperBufferedMutator;
  }

  public BufferedMutatorOptions getBufferedMutatorOptions() {
    return bufferedMutatorOptions;
  }
}
