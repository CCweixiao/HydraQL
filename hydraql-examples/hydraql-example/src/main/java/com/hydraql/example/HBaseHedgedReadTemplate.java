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

package com.hydraql.example;

import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.query.ScanParams;
import com.hydraql.template.BaseHBaseTableTemplate;
import com.hydraql.template.HBaseTableTemplate;

import java.util.List;
import java.util.Properties;

/**
 * @author leojie 2023/8/3 09:38
 */
public class HBaseHedgedReadTemplate {
  public static final String TEST_TABLE = "t1";
  private final BaseHBaseTableTemplate tableTemplate;

  public HBaseHedgedReadTemplate() {
    Properties c1 = new Properties();
    c1.setProperty("hbase.zookeeper.quorum", "localhost");
    c1.setProperty("hbase.zookeeper.property.clientPort", "2181");
    // 开启hedged read
    c1.setProperty("hbase.client.hedged.read.open", "true");
    c1.setProperty("hbase.zookeeper.quorum.hedged.read", "myhbase");
    c1.setProperty("hbase.zookeeper.property.clientPort.hedged.read", "2181");
    c1.setProperty("hbase.client.hedged.read.timeout", "50");
    c1.setProperty("hbase.client.hedged.thread.pool.size", "10");
    tableTemplate = HBaseTableTemplate.of(c1);
  }

  public void testGet() {
    List<HBaseRowData> rowDataList = tableTemplate.scan(TEST_TABLE, ScanParams.of().build());
    System.out.println(rowDataList);
  }

  public static void main(String[] args) {
    HBaseHedgedReadTemplate template = new HBaseHedgedReadTemplate();
    template.testGet();
  }
}
