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

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HBaseThriftPoolTest {
  @Test
  public void testPut() {
    // 声明连接池的配置对象
    HBaseThriftPoolConfig config = new HBaseThriftPoolConfig();
    // 创建HBase Thrift连接池
    HBaseThriftPool hBaseThriftPool = new HBaseThriftPool(config, "localhost", 9090);
    // 从连接池中获取到HBaseThrift对象，HBaseThrift中封装了对HBase的读写操作
    final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
    Map<String, Object> data = new HashMap<>();
    data.put("info:name", "leo");
    data.put("info:age", 18);
    data.put("info:address", "shanghai");
    // 保存数据
    hBaseThrift.save("leo_test", "a10002", data);
    // 关闭hBaseThrift对象，关闭即把该对象放回连接池中
    hBaseThrift.close();
  }

  @Test
  public void testPut2() {
    HBaseThriftTemplate thriftTemplate = HBaseThriftTemplateFactory.getInstance("localhost", 9090);
    Map<String, Object> data = new HashMap<>();
    data.put("info:name", "leo");
    data.put("info:age", 18);
    data.put("info:address", "shanghai");
    // 保存数据
    thriftTemplate.save("leo_test", "a10003", data);
  }

  @Test
  public void testGet() {

  }
}
