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

package com.hydraql.adapter;

import com.hydraql.AbstractHqlTable;
import com.hydraql.conf.AbstractHqlConfiguration;
import com.hydraql.session.DefaultHqlSessionFactory;
import com.hydraql.session.HqlSession;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

/**
 * @author leojie@apache.org 2024/9/23 22:58
 */
public class HqlConfiguration extends AbstractHqlConfiguration {
  private HqlConfiguration(Configuration conf) {
    super(conf);
  }

  public static HqlConfiguration create() {
    Configuration conf = HBaseConfiguration.create();
    return new HqlConfiguration(conf);
  }

  public static HqlConfiguration create(Configuration hbaseConf) {
    return new HqlConfiguration(hbaseConf);
  }

  @Override
  public HqlSession getHqlSession(String tableName) {
    AbstractHqlTable table = getTable(tableName);
    return DefaultHqlSessionFactory.newInstance(table).openSession();
  }

  private AbstractHqlTable getTable(String tableName) {
    return new HqlTableImpl(tableName, this);
  }
}
