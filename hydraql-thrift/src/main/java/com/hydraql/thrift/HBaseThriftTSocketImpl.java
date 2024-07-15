/**
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

import com.hydraql.common.exception.HBaseThriftTSocketException;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * <p>
 * 默认的thrift tSocket的创建工厂
 * </p>
 * @author leojie 2020/12/27 2:44 下午
 */
public class HBaseThriftTSocketImpl implements IHBaseThriftTSocket {
  private final String host;
  private final int port;
  private final int connectionTimeout;
  private final int socketTimeout;

  private HBaseThriftTSocketImpl(Builder builder) {
    this.host = builder.host;
    this.port = builder.port;
    this.connectionTimeout = builder.connectionTimeout;
    this.socketTimeout = builder.socketTimeout;
  }

  public static class Builder {
    private final String host;
    private final int port;
    private int connectionTimeout;
    private int socketTimeout;

    public Builder(String host, int port) {
      this.host = host;
      this.port = port;
    }

    public Builder connectionTimeout(int connectionTimeout) {
      this.connectionTimeout = connectionTimeout;
      return this;
    }

    public Builder socketTimeout(int socketTimeout) {
      this.socketTimeout = socketTimeout;
      return this;
    }

    public HBaseThriftTSocketImpl build() {
      return new HBaseThriftTSocketImpl(this);
    }
  }

  @Override
  public TSocket createTSocket() throws HBaseThriftTSocketException {
    TSocket socket = null;
    try {
      socket = new TSocket(this.getHost(), this.getPort());
      socket.setConnectTimeout(this.getConnectionTimeout());
      socket.open();
      socket.setSocketTimeout(this.getSocketTimeout());
      return socket;
    } catch (TTransportException ex) {
      if (socket != null) {
        socket.close();
      }
      throw new HBaseThriftTSocketException(
          "The TSocket of hbase thrift is created or opened failed!", ex);
    }
  }

  @Override
  public String getDescription() {
    return this.host + ":" + this.port;
  }

  @Override
  public String getHost() {
    return this.host;
  }

  @Override
  public int getPort() {
    return this.port;
  }

  @Override
  public int getConnectionTimeout() {
    return this.connectionTimeout;
  }

  @Override
  public int getSocketTimeout() {
    return this.socketTimeout;
  }
}
