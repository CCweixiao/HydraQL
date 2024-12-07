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

package com.hydraql.session;

import com.hydraql.BaseTestUtil;
import com.hydraql.conf.HqlConfiguration;
import com.hydraql.entity.UserInfo;
import com.hydraql.generator.RowKeyGenerationStrategy;
import com.hydraql.result.GetResult;
import com.hydraql.result.MultiGetResult;
import com.hydraql.wrapper.DeleteWrapper;
import com.hydraql.wrapper.GetWrapper;
import com.hydraql.wrapper.MultiGetWrapper;
import org.apache.hadoop.hbase.HConstants;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

/**
 * @author leojie@apache.org 2024/10/27 13:29
 */
public class HqlSessionTest extends BaseTestUtil {

  private static final HqlConfiguration configuration = HqlConfiguration.create();

  static {
    configuration.set(HConstants.ZOOKEEPER_QUORUM, "docker-hbase");
    configuration.setInt(HConstants.ZOOKEEPER_CLIENT_PORT, 2181);
    configuration.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase");
  }

  @BeforeClass
  public static void setUpClass() {
    createTestTable();
  }

  @Test
  public void testPut() {
    try (HqlSession session = configuration.getSession("hydraql_test_table")) {
      UserInfo userInfo = mockUserInfo();
      session.put(userInfo);
    }
  }

  @Test
  public void testGet() {
    try (HqlSession session = configuration.getSession("hydraql_test_table")) {
      UserInfo userInfo =
          session.get(GetWrapper.create("11000", RowKeyGenerationStrategy.HASHING), UserInfo.class);
      Assert.assertNotNull(userInfo);
      GetResult getResult =
          session.get(GetWrapper.create("11000", RowKeyGenerationStrategy.HASHING));
      Assert.assertNotNull(getResult);
      String rowKey = getResult.getRowAsString();
      Assert.assertEquals("11000", rowKey);
      String username = getResult.getValueAsString("cf", "username");
      Assert.assertEquals("路人甲", username);
    }
  }

  @Test
  public void testMultiGet() {
    try (HqlSession session = configuration.getSession("hydraql_test_table")) {
      MultiGetResult<UserInfo> userInfoMultiGetResult =
          session.multiGet(MultiGetWrapper.create(Collections.singletonList("11000"),
            RowKeyGenerationStrategy.HASHING), UserInfo.class);
      UserInfo userInfo = userInfoMultiGetResult.getResult("11000");
      Assert.assertNotNull(userInfo);
      MultiGetResult<GetResult> multiGetResult = session.multiGet(MultiGetWrapper
          .create(Collections.singletonList("11000"), RowKeyGenerationStrategy.HASHING));

      GetResult getResult = multiGetResult.getResult("11000");
      String rowKey = getResult.getRowAsString();
      Assert.assertEquals("11000", rowKey);
      String username = getResult.getValueAsString("cf", "username");
      Assert.assertEquals("路人甲", username);
    }
  }

  @Test
  public void testDelete() {
    try (HqlSession session = configuration.getSession("hydraql_test_table")) {
      session.delete(DeleteWrapper.create("11000", RowKeyGenerationStrategy.HASHING));
    }
  }

  @Test
  public void testDeleteWithColumns() {
    try (HqlSession session = configuration.getSession("hydraql_test_table")) {
      UserInfo userInfo = mockUserInfo();
      session.put(userInfo);
      session.delete(DeleteWrapper.create("11000", RowKeyGenerationStrategy.HASHING)
          .addColumns("cf", "username"));
      UserInfo userInfo1 =
          session.get(GetWrapper.create("11000", RowKeyGenerationStrategy.HASHING), UserInfo.class);
      Assert.assertNull(userInfo1.getUsername());
    }
  }
}
