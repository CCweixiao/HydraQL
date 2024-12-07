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

import com.hydraql.adapter.schema.ColumnFamilyDesc;
import com.hydraql.adapter.schema.HTableDesc;
import com.hydraql.common.util.SplitGoEnum;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * @author leojie@apache.org 2024/7/13 13:34
 */
public class HBaseAdminTemplateTest {
  private static HBaseAdminTemplate adminTemplate;
  private static Configuration configuration = new Configuration();

  @BeforeClass
  public static void setUpClass() {
    configuration.set(HConstants.ZOOKEEPER_QUORUM, "test_zk1,test_zk2,test_zk3");
    configuration.setInt(HConstants.ZOOKEEPER_CLIENT_PORT, 2181);
    configuration.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase2_test");
    adminTemplate = HBaseAdminTemplate.of(configuration);
  }

  @Test
  public void testGetTableDescList() {
    List<HTableDesc> tableDescList = adminTemplate.listTableDesc();
    Assert.assertFalse(tableDescList.isEmpty());
  }

  @Test
  public void disableTable() {
    // boolean res = adminTemplate.disableTable("leo_test", false);
    // System.out.println(res);
    boolean res2 = adminTemplate.disableTable("leo_test_copy", false);
    System.out.println(res2);
  }

  @Test
  public void deleteTable() {
    // boolean res = adminTemplate.deleteTable("leo_test", false);
    // System.out.println(res);
    boolean res2 = adminTemplate.deleteTable("leo_test_copy", false);
    System.out.println(res2);
  }

  @Test
  public void testHTableDesc() {
    HTableDesc tableDesc = HTableDesc.newBuilder("leo_test").setMaxFileSize(10 * 1024 * 1024L)
        .setMemStoreFlushSize(256 * 1024 * 1024L).setReadOnly(false)
        .setRegionSplitPolicyClassName(
          "org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy")
        .addFamilyDesc(ColumnFamilyDesc.newBuilder("cf").setMaxVersions(2)
            .setCompressionType("snappy").setTimeToLive(3600).setBlockSize(64 * 1024)
            .setConfiguration("hbase.hstore.compaction.min", "3")
            .setConfiguration("hbase.hstore.compaction.max", "5")
            .setConfiguration("hbase.hstore.compaction.max.size", "1073741824")
            .setConfiguration("hbase.hstore.blockingStoreFiles", "120")
            .setValue("CUSTOM_KEY", "custom_value").build())
        .setValue("CUSTOM_KEY", "custom_value").build();
    // TableDescriptor tableDescriptor = tableDesc.convertTo();
    // String customKey = tableDescriptor.getValue("CUSTOM_KEY");
    // String stringCustomizedValues = tableDescriptor.toStringCustomizedValues();
    String humanReadableTTL = tableDesc.getColumnFamilyDescList().get(0).getHumanReadableTTL();
    System.out.println(humanReadableTTL);
  }

  @Test
  public void testCreateTable() {
    if (adminTemplate.tableExists("leo_test")) {
      adminTemplate.disableTable("leo_test", false);
      adminTemplate.deleteTable("leo_test", false);
    }

    HTableDesc tableDesc = HTableDesc.newBuilder("leo_test").setMaxFileSize(10 * 1024 * 1024L)
        .setMemStoreFlushSize(256 * 1024 * 1024L).setReadOnly(false)
        .setRegionSplitPolicyClassName(
          "org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy")
        .addFamilyDesc(ColumnFamilyDesc.newBuilder("cf").setMaxVersions(2)
            .setCompressionType("snappy").setTimeToLive(3600).setBlockSize(64 * 1024)
            .setConfiguration("hbase.hstore.compaction.min", "3")
            .setConfiguration("hbase.hstore.compaction.max", "5")
            .setConfiguration("hbase.hstore.compaction.max.size", "1073741824")
            .setConfiguration("hbase.hstore.blockingStoreFiles", "120").build())
        .addFamilyDesc(
          ColumnFamilyDesc.newBuilder("cf2").setMaxVersions(1).setCompressionType("snappy")
              .setTimeToLive(36000).setBlockSize(128 * 1024).setBlockCacheEnabled(false)
              .setCacheDataOnWrite(false).setCacheIndexesOnWrite(true).build())
        .build();
    boolean res = adminTemplate.createTable(tableDesc, SplitGoEnum.HEX_STRING_SPLIT, 10, false);
    Assert.assertTrue(res);
  }

  @Test
  public void testGetTableDesc() {
    HTableDesc tableDesc = adminTemplate.getTableDesc("leo_test");
    Assert.assertNotNull(tableDesc);
  }

  @Test
  public void testCreateTableFromCopy() {
    if (adminTemplate.tableExists("leo_test_copy")) {
      adminTemplate.disableTable("leo_test_copy", false);
      adminTemplate.deleteTable("leo_test_copy", false);
    }
    HTableDesc tableDesc = adminTemplate.getTableDesc("leo_test");
    HTableDesc tableDescCopy = HTableDesc.copyFrom("leo_test_copy", tableDesc).build();
    adminTemplate.createTable(tableDescCopy, SplitGoEnum.HEX_STRING_SPLIT, 10, false);
  }
}
