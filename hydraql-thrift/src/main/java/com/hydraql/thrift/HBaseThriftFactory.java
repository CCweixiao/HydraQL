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

package com.hydraql.thrift;

import com.hydraql.exceptions.HBaseThriftTSocketException;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leojie 2020/12/27 2:49 下午
 */
public class HBaseThriftFactory implements PooledObjectFactory<HBaseThrift> {
  private static final Logger LOG = LoggerFactory.getLogger(HBaseThriftFactory.class);

  private final String host;
  private final int port;
  private final int connectionTimeout;
  private final int socketTimeout;

  public HBaseThriftFactory(final String host, final int port, final int connectionTimeout,
      final int socketTimeout) {
    this.host = host;
    this.port = port;
    this.connectionTimeout = connectionTimeout;
    this.socketTimeout = socketTimeout;
  }

  @Override
  public PooledObject<HBaseThrift> makeObject() {
    final HBaseThrift hBaseThrift = new HBaseThrift(host, port, connectionTimeout, socketTimeout);
    try {
      hBaseThrift.connect();
      LOG.info("connected {}:{} successfully and created hbase thrift client successfully!", host,
        port);
      System.out.println("--------------------------------------------------------------------");
    } catch (HBaseThriftTSocketException e) {
      hBaseThrift.close();
      throw e;
    }
    return new DefaultPooledObject<>(hBaseThrift);
  }

  @Override
  public void destroyObject(PooledObject<HBaseThrift> pooledObject) {
    final HBaseThrift hbaseThrift = pooledObject.getObject();
    if (hbaseThrift.isConnected()) {
      hbaseThrift.disconnect();
    }
    LOG.debug("hbase thrift client connection destroyed successfully.");
    System.out.println("--------------------------------------------------------------------");

  }

  @Override
  public boolean validateObject(PooledObject<HBaseThrift> pooledObject) {
    final HBaseThrift hBaseThrift = pooledObject.getObject();
    try {
      boolean isValid = hBaseThrift.isConnected() && hBaseThrift.ping();
      LOG.debug("current hbase thrift client is valid or not, {}", isValid);
      System.out.println("--------------------------------------------------------------------");
      return isValid;
    } catch (final Exception e) {
      return false;
    }
  }

  @Override
  public void activateObject(PooledObject<HBaseThrift> pooledObject) {

  }

  @Override
  public void passivateObject(PooledObject<HBaseThrift> pooledObject) {

  }
}
