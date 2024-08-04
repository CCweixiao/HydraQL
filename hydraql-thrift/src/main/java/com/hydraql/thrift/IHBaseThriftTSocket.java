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

import com.hydraql.common.exception.HBaseThriftTSocketException;
import org.apache.thrift.transport.TSocket;

/**
 * <p>
 * HBase thrift TSocket的初始化工厂接口
 * </p>
 * @author leojie 2020/12/27 2:38 下午
 */
public interface IHBaseThriftTSocket {
  /**
   * 创建连接HBase thrift 的TSocket对象
   * @return TSocket对象
   * @throws HBaseThriftTSocketException 抛出HBase Thrift TSocket相关的异常
   */
  TSocket createTSocket() throws HBaseThriftTSocketException;

  /**
   * description
   * @return description
   */
  String getDescription();

  /**
   * HBase thrift host
   * @return host
   */
  String getHost();

  /**
   * HBase thrift port
   * @return HBase thrift port
   */
  int getPort();

  /**
   * 获取连接HBase thrift server的超时时间
   * @return 连接的超时时间
   */
  int getConnectionTimeout();

  /**
   * 获取TSocket的超时时间
   * @return TSocket的超时时间
   */
  int getSocketTimeout();
}
