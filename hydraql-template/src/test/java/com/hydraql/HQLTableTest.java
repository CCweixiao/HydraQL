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

package com.hydraql;

import com.hydraql.adapter.HQlConfiguration;
import com.hydraql.session.HQLSessionFactoryBuilder;
import com.hydraql.session.HQlSession;
import com.hydraql.session.HQlSessionFactory;
import com.hydraql.session.HQlSessionManager;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

/**
 * @author leojie@apache.org 2024/10/27 13:29
 */
public class HQLTableTest {
  @Test
  public void testHQlSessionFactory() {
    HQlConfiguration configuration = new HQlConfiguration();
    HQLTable table = configuration.getTable("test_table");
    HQlSessionFactory sessionFactory = new HQLSessionFactoryBuilder().build(table);
    HQlSession session = sessionFactory.openSession();
    session.get(new Get(Bytes.toBytes("10001")));
  }

  @Test
  public void testHQlSessionManager() {
    HQlConfiguration configuration = new HQlConfiguration();
    HQLTable table = configuration.getTable("test_table");
    HQlSessionManager sessionManager = HQlSessionManager.newInstance(table);
    sessionManager.startManagedSession();
    sessionManager.get(new Get(Bytes.toBytes("10001")));
  }
}
