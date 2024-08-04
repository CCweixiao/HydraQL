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

package com.hydraql.thrift.example;

import com.hydraql.common.type.ColumnType;
import org.apache.hadoop.hbase.thrift.generated.Hbase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leojie 2021/1/6 8:28 下午
 */
public class HBaseThriftApiTest {
  public static void main(String[] args) throws TTransportException {
    test2();
  }

  /**
   * 同一连接闲置失效异常
   */
  public static void test1() throws TTransportException {
    TSocket socket = new TSocket("myhbase", 9090);
    List<String> allTableNames;
    Hbase.Client hbaseClient;

    TProtocol protocol = new TBinaryProtocol(socket);
    hbaseClient = new Hbase.Client(protocol);

    try {
      socket.open();
      allTableNames = hbaseClient.getTableNames().stream().map(t -> ColumnType.toString(t.array()))
          .collect(Collectors.toList());
      System.out.println(allTableNames);
      Thread.sleep(60000L);
      System.out.println("此处停顿了一分钟......");
      allTableNames = hbaseClient.getTableNames().stream().map(t -> ColumnType.toString(t.array()))
          .collect(Collectors.toList());
      System.out.println(allTableNames);
      Thread.sleep(120000L);
      System.out.println("此处停顿了两分钟......");
      allTableNames = hbaseClient.getTableNames().stream().map(t -> ColumnType.toString(t.array()))
          .collect(Collectors.toList());
      System.out.println(allTableNames);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      socket.close();
    }
  }

  /**
   * 连接频繁创建的开销与短连接问题
   */
  public static void test2() throws TTransportException {
    int x = 0;
    while (true) {
      TSocket socket = new TSocket("myhbase", 9090);

      TProtocol protocol = new TBinaryProtocol(socket, true, true);
      Hbase.Client hbaseClient = new Hbase.Client(protocol);

      try {
        socket.open();
        List<String> allTableNames = hbaseClient.getTableNames().stream()
            .map(t -> ColumnType.toString(t.array())).collect(Collectors.toList());
        System.out.println(allTableNames);
        System.out.println(x);
      } catch (TException e) {
        e.printStackTrace();
      }
      try {
        Thread.sleep(500L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      x += 1;
    }
  }

}
