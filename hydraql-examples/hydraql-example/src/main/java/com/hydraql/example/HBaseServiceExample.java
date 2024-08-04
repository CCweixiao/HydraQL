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

import com.hydraql.common.query.ScanParams;
import com.hydraql.template.BaseHBaseAdminTemplate;
import com.hydraql.template.BaseHBaseTableTemplate;
import com.hydraql.template.HBaseAdminTemplate;
import com.hydraql.template.HBaseTableTemplate;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author leojie 2022/10/22 18:29
 */
public class HBaseServiceExample {
  public static Properties getProperties() {
    Properties properties = new Properties();
    properties.setProperty("hbase.zookeeper.quorum", "myhbase");
    properties.setProperty("hbase.zookeeper.property.clientPort", "2181");
    return properties;
  }

  static class Work implements Runnable {
    public Work() {
    }

    @Override
    public void run() {
      Random random = new Random();

      while (true) {
        try {
          BaseHBaseAdminTemplate adminTemplate = HBaseAdminTemplate.of(getProperties());
          int r = random.nextInt(10) + 1;
          System.out.println(adminTemplate.listTableNames());
          System.out.println("Thread-" + Thread.currentThread().getName() + "即将等待：" + r + "秒钟");
          Thread.sleep((long) r * 6 * 1000);
          System.out.println("Thread-" + Thread.currentThread().getName() + "等待时间：" + r + "秒钟");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        System.out.println(
          "################################################################################################");
      }
    }
  }

  public static void main(String[] args) {
    BaseHBaseTableTemplate hBaseTemplate = HBaseTableTemplate.of(getProperties());
    ScanParams scanQueryParamsBuilder = ScanParams.of().familyName("info")
        .columnNames(Arrays.asList("city_name", "city_address", "cityTagList")).startRow("a10001")
        .stopRow("a10002").build();
    System.out.println(hBaseTemplate.scan("t2", scanQueryParamsBuilder));
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 10; i++) {
      executorService.execute(new HBaseServiceExample.Work());
    }
  }
}
