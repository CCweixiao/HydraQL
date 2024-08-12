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

package com.hydraql.template;

import com.hydraql.common.query.GetRowParam;
import com.hydraql.template.model.UserData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;
import org.junit.Test;

import java.util.Collections;

/**
 * @author leojie@apache.org 2024/8/4 16:53
 */
public class HBaseTableTemplateTest {
  private static final HBaseTableTemplate tableTemplate;
  private static final Configuration configuration = new Configuration();

  static {
    configuration.set(HConstants.ZOOKEEPER_QUORUM, "zk1,zk2,zk3");
    configuration.setInt(HConstants.ZOOKEEPER_CLIENT_PORT, 2181);
    configuration.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase2_test");
    tableTemplate = HBaseTableTemplate.of(configuration);
  }

  @Test
  public void testSave() {
    UserData userData = new UserData();
    userData.setUserId("aaaa1111");
    userData.setUsername("leojie");
    userData.setAge(18);
    userData.setStudent(true);
    userData.setCost(10000d);
    tableTemplate.save(userData);
  }

  @Test
  public void testBatchSave() {
    UserData userData = new UserData();
    userData.setUserId("aaaa1111222");
    userData.setUsername("leojie222");
    userData.setAge(18);
    userData.setStudent(true);
    userData.setCost(10000d);
    tableTemplate.saveBatch(Collections.singletonList(userData));
  }

  @Test
  public void testGet() {
    UserData userData = tableTemplate.getRow(GetRowParam.of("aaaa1111").build(), UserData.class);
    System.out.println(userData);
  }

  @Test
  public void testQuery() {
    Query<UserData> query = Query.of(UserData.class).config(configuration).config("a", "b")
        .withRow("aaaa1111").addFamily("cf").build();
    UserData userData = query.get();
    System.out.println(userData);
  }

}
