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

import com.hydraql.common.query.GetRowParam;
import com.hydraql.thrift.HBaseThriftTemplate;
import com.hydraql.thrift.HBaseThriftTemplateFactory;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author leojie 2020/12/27 11:59 下午
 */
public class HBaseThriftPoolThreadTest {

  static class Work implements Runnable {
    private final HBaseThriftTemplate thriftTemplate =
        HBaseThriftTemplateFactory.getInstance("localhost", 9090, 1);

    @Override
    public void run() {
      Random random = new Random();
      System.out.println(thriftTemplate.getRow("LEO_USER", GetRowParam.of("a10001").build()));

      while (true) {
        try {
          int r = random.nextInt(10) + 1;
          System.out.println("Thread-" + Thread.currentThread().getName() + "即将等待：" + r + "分钟");
          Thread.sleep(r * 60 * 1000L);
          System.out.println(thriftTemplate.getRow("LEO_USER", GetRowParam.of("a10001").build()));
          System.out.println("Thread-" + Thread.currentThread().getName() + "等待时间：" + r + "分钟");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        System.out.println(
          "################################################################################################");
      }
    }
  }

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 10; i++) {
      executorService.execute(new Work());
    }
  }
}
