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

import com.hydraql.adapter.schema.ColumnFamilyDesc;
import com.hydraql.adapter.schema.HTableDesc;
import com.hydraql.common.util.SplitGoEnum;
import com.hydraql.entity.UserInfo;
import com.hydraql.template.HBaseAdminTemplate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;

/**
 * @author leojie@apache.org 2024/12/6 23:30
 */
public class BaseTestUtil {
  private static final HBaseAdminTemplate adminTemplate;
  private static final Configuration configuration = new Configuration();
  private static final String TEST_TABLE = "hydraql_test_table";

  static {
    configuration.set(HConstants.ZOOKEEPER_QUORUM, "docker-hbase");
    configuration.setInt(HConstants.ZOOKEEPER_CLIENT_PORT, 2181);
    configuration.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase");
    adminTemplate = HBaseAdminTemplate.of(configuration);
  }

  protected static void createTestTable() {
    if (adminTemplate.tableExists(TEST_TABLE)) {
      return;
    }
    ColumnFamilyDesc cf = ColumnFamilyDesc.newBuilder("cf").build();
    HTableDesc tableDesc = HTableDesc.newBuilder(TEST_TABLE).addFamilyDesc(cf).build();
    adminTemplate.createTable(tableDesc, SplitGoEnum.HEX_STRING_SPLIT, 10, true);
  }

  protected static void disableTable() {
    if (!adminTemplate.tableExists(TEST_TABLE)) {
      return;
    }
    adminTemplate.disableTable(TEST_TABLE, false);
  }

  public static void deleteTable() {
    if (!adminTemplate.tableExists(TEST_TABLE)) {
      return;
    }
    if (!adminTemplate.isTableDisabled(TEST_TABLE)) {
      return;
    }
    adminTemplate.deleteTable(TEST_TABLE, false);
  }

  public UserInfo mockUserInfo() {
    UserInfo userInfo = new UserInfo();
    userInfo.setUserId("11000");
    userInfo.setUsername("路人甲");
    userInfo.setStudent(false);
    userInfo.setAge(18);
    userInfo.setCost(100.5d);
    userInfo.setAddress("上海市浦东新区");
    userInfo.setCreatedTime(System.currentTimeMillis());
    return userInfo;
  }
}
