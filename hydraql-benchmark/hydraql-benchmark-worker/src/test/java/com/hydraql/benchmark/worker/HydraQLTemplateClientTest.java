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

package com.hydraql.benchmark.worker;

import com.hydraql.benchmark.core.ByteIterator;
import com.hydraql.benchmark.core.DBException;
import com.hydraql.benchmark.core.Status;
import com.hydraql.benchmark.core.StringByteIterator;
import org.apache.hadoop.hbase.client.Scan;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leojie@apache.org 2024/4/11 09:13
 */
public class HydraQLTemplateClientTest {
  @Test
  public void testPut() throws DBException {
    Scan scan = new Scan();
    scan.setCacheBlocks(false);
    HydraQLTemplateClient client = new HydraQLTemplateClient();
    client.init();
    Map<String, ByteIterator> data = new HashMap<>();
    data.put("field0", new StringByteIterator("test"));
    Status status = client.update("usertable", "user1000000025861564284", data);
    System.out.println(status);
  }
}
