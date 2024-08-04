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

import com.hydraql.common.exception.HBaseThriftException;
import com.hydraql.common.util.HBaseThriftProtocol;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * <p>
 * HBase thrift server 连接池的具体实现
 * </p>
 * @author leojie 2020/12/27 2:50 下午
 */
public class HBaseThriftPool extends HBaseThriftPoolAbstract {
  public HBaseThriftPool() {
    this(HBaseThriftProtocol.DEFAULT_HOST, HBaseThriftProtocol.DEFAULT_PORT);
  }

  public HBaseThriftPool(String host) {
    this(host, HBaseThriftProtocol.DEFAULT_PORT);
  }

  public HBaseThriftPool(String host, int port) {
    this(new GenericObjectPoolConfig(), host, port, HBaseThriftProtocol.DEFAULT_TIMEOUT);
  }

  public HBaseThriftPool(final GenericObjectPoolConfig<?> poolConfig, final String host, int port) {
    this(poolConfig, host, port, HBaseThriftProtocol.DEFAULT_TIMEOUT,
        HBaseThriftProtocol.DEFAULT_TIMEOUT);
  }

  public HBaseThriftPool(final GenericObjectPoolConfig<?> poolConfig, final String host, int port,
      int timeout) {
    this(poolConfig, host, port, timeout, timeout);
  }

  public HBaseThriftPool(final GenericObjectPoolConfig<?> poolConfig, final String host, int port,
      final int connectionTimeout, final int soTimeout) {
    super(poolConfig, new HBaseThriftFactory(host, port, connectionTimeout, soTimeout));
  }

  @Override
  public HBaseThrift getResource() {
    HBaseThrift hBaseThrift = super.getResource();
    hBaseThrift.setDataSource(this);
    return hBaseThrift;
  }

  @Override
  protected void returnBrokenResource(HBaseThrift resource) {
    if (resource != null) {
      returnBrokenResourceObject(resource);
    }
  }

  @Override
  protected void returnResource(HBaseThrift resource) {
    if (resource != null) {
      try {
        returnResourceObject(resource);
      } catch (Exception e) {
        returnBrokenResourceObject(resource);
        throw new HBaseThriftException("Resource is returned to the pool as broken", e);
      }
    }
  }
}
