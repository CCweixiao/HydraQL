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

import com.hydraql.adapter.hedgedread.HedgedReadStrategy;
import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.query.ScanParams;
import com.hydraql.template.HBaseTableTemplate;
import org.apache.hadoop.conf.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie@apache.org 2024/4/11 21:42
 */
public class HBaseHedgedReadTemplateThreads {
  public static Configuration createHedgedConfiguration(String zk1, String zk2,
      boolean writeDisable, HedgedReadStrategy.Level strategy) {
    Configuration conf = new Configuration();
    conf.set("hbase.zookeeper.quorum", zk1);
    conf.set("hbase.zookeeper.property.clientPort", "2181");

    // 开启hedged read
    conf.set("hbase.client.hedged.read.strategy", strategy.name());
    conf.setInt("hbase.client.hedged.read.threadpool.size", 400);
    conf.setLong("hbase.client.hedged.read.overall.timeout.millis", 60000L);
    conf.setLong("hbase.client.hedged.read.threshold.millis", 100000L);
    conf.setBoolean("hbase.client.hedged.read.write.disable", writeDisable);

    conf.set("hedged.read.hbase.zookeeper.quorum", zk2);
    conf.set("hedged.read.hbase.zookeeper.property.clientPort", "2181");
    return conf;
  }

  public static void main(String[] args) throws InterruptedException {
    Configuration conf =
        createHedgedConfiguration("myhbase", "myhbase", true, HedgedReadStrategy.Level.THRESHOLD);
    final HBaseTableTemplate tableTemplate = HBaseTableTemplate.of(conf);

    List<Thread> threads = new ArrayList<>(200);
    for (int i = 0; i < 200; i++) {
      threads.add(new Thread(() -> {
        long start = System.currentTimeMillis();
        List<HBaseRowData> rowDataList = tableTemplate.scan("test_tab", ScanParams.of().build());
      }));
    }
    for (Thread thread : threads) {
      thread.start();
    }

    while (true) {
      Thread.sleep(1000);
    }

  }
}
