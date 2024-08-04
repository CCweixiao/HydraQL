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

import java.util.Random;

/**
 * @author leojie 2020/12/27 11:59 下午
 */
public class HBaseThriftPoolTest {
  public static void main(String[] args) {
    // HBaseThriftService hBaseThriftService = HBaseThriftServiceHolder.getInstance("localhost",
    // 9090);
    Random random = new Random();

    while (true) {

      try {
        int r = random.nextInt(10) + 1;
        // int r = 4;
        System.out.println("即将等待：" + r + "分钟");
        Thread.sleep(r * 60 * 1000L);
        // System.out.println("getNumIdle=" + hBaseThriftPool.getNumIdle());
        // System.out.println("getNumActive=" + hBaseThriftPool.getNumActive());
        System.out.println("等待时间：" + r + "分钟");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(
        "################################################################################################");
    }

  }
}
