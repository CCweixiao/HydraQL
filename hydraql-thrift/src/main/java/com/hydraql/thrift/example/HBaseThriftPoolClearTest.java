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

package com.hydraql.thrift.example;

import com.hydraql.common.query.GetRowParam;
import com.hydraql.thrift.HBaseThriftTemplate;
import com.hydraql.thrift.HBaseThriftTemplateFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author leojie 2021/12/2 3:06 下午
 */
public class HBaseThriftPoolClearTest {
  static class Work implements Runnable {
    private final HBaseThriftTemplate thriftTemplate =
        HBaseThriftTemplateFactory.getInstance("internal_dev", 9091, 2);

    @Override
    public void run() {
      while (true) {
        try {
          System.out.println(Thread.currentThread().getName());
          System.out.println(thriftTemplate.getRow("LEO_USER", GetRowParam.of("a10001").build()));

          Thread.sleep(10 * 1000L);
          // thriftService.clearThriftPool();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        System.out.println(
          "################################################################################################");
      }
    }
  }

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    for (int i = 0; i < 20; i++) {
      executorService.execute(new Work());
    }
  }
}
