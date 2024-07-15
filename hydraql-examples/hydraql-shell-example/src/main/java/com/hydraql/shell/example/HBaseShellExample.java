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

package com.hydraql.shell.example;

import com.hydraql.shell.HBaseShellSession;
import com.hydraql.shell.HBaseShellSessionManager;
import com.hydraql.shell.Result;

import java.util.Properties;

/**
 * @author leojie 2023/7/4 19:55
 */
public class HBaseShellExample {
  public static void main(String[] args) throws InterruptedException {
    Properties p = new Properties();
    p.setProperty("hbase.shell.session.debug.log", "true");
    p.setProperty("hbase.zookeeper.quorum", "myhbase");
    p.setProperty("hbase.zookeeper.property.clientPort", "2181");

    HBaseShellSession shellSession = HBaseShellSessionManager.getHBaseShellSession(p);
    // Result res2 = shellSession.execute("list");
    // System.out.println(res2);
    // Result res = shellSession.execute("list_namespace");
    // System.out.println(res);
    //
    // Result res2 = shellSession.execute("list");
    // System.out.println(res2);
    //
    // Result res3 = shellSession.execute("put 't1', '1001', 'f', 'leo'");
    // System.out.println(res3);

    Result res4 = shellSession.execute("desc 'test_tab'");
    // Result res5 = shellSession.execute("a = 1 + 1");
    // Result res6 = shellSession.execute("puts a");
    System.out.println(res4.getResult());

  }
}
