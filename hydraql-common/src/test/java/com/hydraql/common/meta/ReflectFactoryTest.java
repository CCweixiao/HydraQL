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

package com.hydraql.common.meta;

import com.hydraql.common.meta.annotations.HBaseColumn;
import com.hydraql.common.meta.annotations.HBaseRowKey;
import com.hydraql.common.meta.annotations.HBaseTable;
import com.hydraql.common.exception.InvalidTableModelClassException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leojie@apache.org 2024/4/3 23:25
 */
public class ReflectFactoryTest {
  static class User {
    private String username;

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }
  }

  @Test
  public void testGetHBaseTableMeta() {
    boolean throwErr = false;
    try {
      HBaseMetaContainer.getInstance().stuff(User.class);
    } catch (InvalidTableModelClassException e) {
      throwErr = true;
    }
    Assert.assertTrue(throwErr);
  }

  static class People {
    @HBaseColumn
    private String country;

    public String getCountry() {
      return country;
    }

    public void setCountry(String country) {
      this.country = country;
    }
  }

  @HBaseTable(tableName = "user_info", defaultFamily = "f")
  static class User2 extends People {
    @HBaseRowKey
    private String userId;

    @HBaseColumn(qualifier = "user_name")
    private String username;

    @HBaseColumn(family = "d", qualifier = "ADDRESS")
    private String address;

    @HBaseColumn
    private String detailInfo;

    public String getUserId() {
      return userId;
    }

    public void setUserId(String userId) {
      this.userId = userId;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public String getDetailInfo() {
      return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
      this.detailInfo = detailInfo;
    }
  }

  @Test
  public void testGetHBaseTableMeta2() {
    HBaseTableSchema tableMeta = HBaseMetaContainer.getInstance().stuff(User2.class);
    Assert.assertNotNull(tableMeta);
    Assert.assertEquals(5, tableMeta.getFields().size());
    Assert.assertTrue(tableMeta.getFields().get(0).isRowKey());
  }
}
