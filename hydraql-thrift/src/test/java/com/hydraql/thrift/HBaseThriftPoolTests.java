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

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @author leojie 2020/12/27 2:59 下午
 */
public class HBaseThriftPoolTests {

  private HBaseThriftPool hBaseThriftPool;
  private HBaseThriftTemplate hBaseThriftService;

  @Before
  public void init() {
    HBaseThriftPoolConfig config = new HBaseThriftPoolConfig();
    hBaseThriftPool = new HBaseThriftPool(config, "localhost", 9090);
    hBaseThriftService = new HBaseThriftTemplate("localhost", 9090);
  }

  @Test
  public void testThriftPool() {
    final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
    System.out.println(hBaseThrift.ping());
    hBaseThrift.close();
  }

  @Test
  public void testThriftClient() {
    HBaseThrift hBaseThrift = new HBaseThrift();
    hBaseThrift.connect();
    System.out.println(hBaseThrift.ping());
    hBaseThrift.close();
  }

  @Test
  public void testBatchPut() {
    Map<String, Object> data1 = new HashMap<>();
    data1.put("g:name", "leo");
    data1.put("g:age", 18);
    data1.put("g:address", "上海");
    data1.put("g:tag", null);

    Map<String, Object> data2 = new HashMap<>();
    data2.put("f:name", "leo2");
    data2.put("f:age", 18);
    data2.put("f:address", "上海2");
    data2.put("f:tag", "tag2");

    Map<String, Map<String, Object>> data = new HashMap<>(2);
    data.put("a10001", data1);
    data.put("a10002", data2);
    hBaseThriftService.saveBatch("LEO_USER", data);
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void testPut() {
    final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();

    Map<String, Object> data3 = new HashMap<>();
    data3.put("g:name", "leo2g");
    data3.put("g:age", 18);
    data3.put("g:address", "上海2g");
    data3.put("g:tag", "tag2g");

    hBaseThrift.save("LEO_USER", "a10002", data3);
    hBaseThrift.close();
  }

  @Test
  public void testGet() {

    // final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
    //
    // System.out.println(hBaseThrift.getToRowData("LEO_USER",
    // "a10001", "g", Collections.singletonList("age")));
    //
    // System.out.println(hBaseThrift.getToRowData("LEO_USER",
    // "a10001", "g", Arrays.asList("name", "age", "sds")));
    //
    // System.out.println(hBaseThrift.getToRowData("LEO_USER",
    // "a10002", "f"));
    //
    // System.out.println(hBaseThrift.getToRowData("LEO_USER",
    // "a10002", "g"));
    //
    // System.out.println(hBaseThrift.getToRowData("LEO_USER",
    // "a1002"));
    //
    // hBaseThrift.close();
  }

  @Test
  public void testDeleteRow() {

    final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
    hBaseThrift.delete("LEO_USER", "a1002");
    hBaseThrift.close();
  }

  @Test
  public void testDeleteFamily() {
    final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
    hBaseThrift.delete("LEO_USER", "a10001", "g", new ArrayList<>());
    hBaseThrift.close();
  }

  @Test
  public void testDeleteSomeColumns() {
    final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
    hBaseThrift.delete("LEO_USER", "a10001", "g", "address");
    hBaseThrift.close();
  }

  @Test
  public void testDeleteBatch() {
    final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
    hBaseThrift.deleteBatch("LEO_USER", Arrays.asList("a10001", "a10002"));
  }

  @Test
  public void testDeleteBatchWithFamily() {
    final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
    hBaseThrift.deleteBatch("LEO_USER", Arrays.asList("a10001", "a10002"), "g");
  }

  @Test
  public void testDeleteBatchWithFamilyAndCol() {
    final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
    hBaseThrift.deleteBatch("LEO_USER", Arrays.asList("a10001", "a10002"), "f", "address");
    hBaseThrift.deleteBatch("LEO_USER", Arrays.asList("a10001", "a10002"), "f",
      Arrays.asList("tag", "age"));

  }

  @Test
  public void testScan() {
    final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
  }

}
