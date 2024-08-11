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

import com.hydraql.adapter.connection.HBaseConnectionManager;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.MetaTableAccessor;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RegionInfo;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;

/**
 * @author leojie@apache.org 2024/8/4 16:53
 */
public class HBaseTableTemplateTest {
  private static final HBaseTableTemplate tableTemplate;
  private static final Configuration configuration = new Configuration();

  static {
    configuration.set(HConstants.ZOOKEEPER_QUORUM, "test_zk1,test_zk2,test_zk3");
    configuration.setInt(HConstants.ZOOKEEPER_CLIENT_PORT, 2181);
    configuration.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase2_test");
    tableTemplate = HBaseTableTemplate.of(configuration);
  }

  @Test
  public void testSave() {
    Connection connection = HBaseConnectionManager.create().getConnection(configuration);
    try (Table table = connection.getTable(TableName.META_TABLE_NAME)) {
      Get get =
          new Get(Bytes.toBytes("user-tag,06,1702891662406.3165b49a01846726ee0953c72c2d60e3."));
      Result result = table.get(get);
      RegionInfo regionInfo =
          MetaTableAccessor.getRegionInfo(result, HConstants.REGIONINFO_QUALIFIER);
      long seqNumDuringOpen = getSeqNumDuringOpen(result);
      ServerName serverName = MetaTableAccessor.getServerName(result, 0);
      System.out.println(regionInfo);
      // st-int-hbase-arch-hbase-cluster-5,16020,1722764491477
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testPut() {
    Connection connection = HBaseConnectionManager.create().getConnection(configuration);
    try (Table table = connection.getTable(TableName.META_TABLE_NAME)) {
      Put put = new Put(
          Bytes.toBytes("leo_test_copy,7ffffffd,1720965343007.f27ae57d4bab9096dcc81e05e96978a5."));
      // work-arch-kv-redis-1,16020,1722764393804
      put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("serverstartcode"),
        Bytes.toBytes(1722764393804L));
      put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("sn"),
        Bytes.toBytes("work-arch-kv-redis-1,16020,1722764393804"));
      put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("state"), Bytes.toBytes("CLOSED"));
      table.put(put);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static long getSeqNumDuringOpen(final Result r) {
    Cell cell =
        r.getColumnLatestCell(HConstants.CATALOG_FAMILY, MetaTableAccessor.getSeqNumColumn(0));
    if (cell == null || cell.getValueLength() == 0) return HConstants.NO_SEQNUM;
    return Bytes.toLong(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
  }

}
