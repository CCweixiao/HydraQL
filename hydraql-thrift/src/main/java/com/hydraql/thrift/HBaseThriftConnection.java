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
import com.hydraql.common.util.HBaseThriftProtocol;
import org.apache.thrift.transport.TSocket;

import java.io.Closeable;

/**
 * <p>
 * HBase thrift connection
 * </p>
 * @author leojie 2020/12/27 2:45 下午
 */
public class HBaseThriftConnection implements Closeable {
  private final IHBaseThriftTSocket hbaseThriftTSocket;
  private TSocket socket = null;
  private boolean broken = false;

  public HBaseThriftConnection() {
    this(HBaseThriftProtocol.DEFAULT_HOST);
  }

  public HBaseThriftConnection(final String host) {
    this(host, HBaseThriftProtocol.DEFAULT_PORT);
  }

  public HBaseThriftConnection(final String host, final int port) {
    this(host, port, HBaseThriftProtocol.DEFAULT_TIMEOUT, HBaseThriftProtocol.DEFAULT_TIMEOUT);
  }

  public HBaseThriftConnection(final String host, final int port, final int connectionTimeout,
      final int socketTimeout) {
    this(new HBaseThriftTSocketImpl.Builder(host, port).connectionTimeout(connectionTimeout)
        .socketTimeout(socketTimeout).build());
  }

  public HBaseThriftConnection(final IHBaseThriftTSocket hbaseThriftTSocket) {
    this.hbaseThriftTSocket = hbaseThriftTSocket;
  }

  public TSocket getSocket() {
    return socket;
  }

  public int getConnectionTimeout() {
    return hbaseThriftTSocket.getConnectionTimeout();
  }

  public int getSocketTimeout() {
    return hbaseThriftTSocket.getSocketTimeout();
  }

  public String getHost() {
    return hbaseThriftTSocket.getHost();
  }

  public int getPort() {
    return hbaseThriftTSocket.getPort();
  }

  public void connect() {
    if (!isConnected()) {
      try {
        socket = hbaseThriftTSocket.createTSocket();
      } catch (HBaseThriftTSocketException e) {
        broken = true;
        throw new HBaseThriftTSocketException(
            "Failed connecting to " + hbaseThriftTSocket.getDescription());
      }
    }
  }

  public boolean isBroken() {
    return broken;
  }

  public boolean isConnected() {
    return socket != null && socket.isOpen();
  }

  public void disconnect() {
    if (isConnected()) {
      socket.close();
    }
  }

  @Override
  public void close() {
    disconnect();
  }

}
