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

package com.hydraql.example;

import com.hydraql.conf.HqlConfiguration;
import com.hydraql.generator.RowKeyGenerationStrategy;
import com.hydraql.result.GetResult;
import com.hydraql.session.HqlSession;
import com.hydraql.wrapper.GetWrapper;
import org.apache.hadoop.hbase.HConstants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author leojie@apache.org 2024/12/7 20:52
 */
public class HBaseConnectionManagerTest {
  private static final HqlConfiguration configuration = HqlConfiguration.create();

  static {
    configuration.set(HConstants.ZOOKEEPER_QUORUM, "docker-hbase");
    configuration.setInt(HConstants.ZOOKEEPER_CLIENT_PORT, 2181);
    configuration.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase");
  }

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 100; i++) {
      executor.execute(() -> {
        try (HqlSession session = configuration.getSession("hydraql_test_table")) {
          GetResult getResult =
              session.get(GetWrapper.create("11000", RowKeyGenerationStrategy.HASHING));
          System.out.println(getResult.getRowAsString());
        }
      });
    }

  }
}
