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

package com.hydraql.conf;

import com.hydraql.hedgedread.HedgedReadStrategy;
import org.apache.hadoop.hbase.HConstants;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leojie@apache.org 2024/12/5 22:47
 */
public class HqlConfigurationTest {
  @Test
  public void testCreate() {
    HqlConfiguration configuration = HqlConfiguration.create();
    configuration.set(HConstants.ZOOKEEPER_QUORUM, "zk1,zk2,zk3");
    configuration.setInt(HConstants.ZOOKEEPER_CLIENT_PORT, 2181);
    configuration.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase");
    Assert.assertNull(configuration.getHedgedReadConfiguration());
    Assert.assertNotNull(configuration.getHedgedReadProperty());
    Assert.assertFalse(configuration.getHedgedReadProperty().isActivate());
    Assert.assertEquals("zk1,zk2,zk3", configuration.get(HConstants.ZOOKEEPER_QUORUM));
  }

  @Test
  public void testCreateHedgedReadConfiguration() {
    HqlConfiguration configuration = HqlConfiguration.create();
    configuration.set("hbase.client.hedged.read.strategy",
      HedgedReadStrategy.Option.FIRST_ONE.name());
    configuration.setInt("hbase.client.hedged.read.threadpool.size", 10);
    configuration.setLong("hbase.client.hedged.read.threshold.millis", 100L);
    configuration.setLong("hbase.client.hedged.read.overall.timeout.millis", 1000L);
    configuration.setBoolean("hbase.client.hedged.read.write.enable", true);

    configuration.set("hedged.read.hbase.zookeeper.quorum", "zk4,zk5,zk6");
    configuration.set("hedged.read.hbase.zookeeper.property.clientPort", "2181");
    configuration.set("hedged.read.zookeeper.znode.parent", "/hbase2");

    Assert.assertTrue(configuration.getHedgedReadProperty().isActivate());
    Assert.assertEquals(1000L,
      configuration.getHedgedReadProperty().getHedgedReadOverallTimeoutMillis());
    Assert.assertEquals("zk4,zk5,zk6",
      configuration.getHedgedReadConfiguration().get(HConstants.ZOOKEEPER_QUORUM));
  }
}
