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

import com.hydraql.HqlTable;
import com.hydraql.hedgedread.HedgedReadStrategy;
import com.hydraql.session.DefaultHqlSessionFactory;
import com.hydraql.session.HqlSession;
import com.hydraql.util.Preconditions;
import org.apache.hadoop.conf.Configuration;

import java.util.Map;

/**
 * @author leojie@apache.org 2024/9/23 22:18
 */
public class HqlConfiguration extends Configuration {
  private HqlConfiguration() {
  }

  public static HqlConfiguration create(final Configuration that) {
    HqlConfiguration conf = create();
    merge(conf, that);
    return conf;
  }

  public static HqlConfiguration create() {
    HqlConfiguration conf = new HqlConfiguration();
    conf.addResource("hbase-default.xml");
    conf.addResource("hbase-site.xml");
    return conf;
  }

  public static class HedgedReadProperty {
    private final boolean isActivate;
    private final HedgedReadStrategy.Option hedgedReadStrategyOption;
    private final boolean hedgedReadWriteEnable;
    private final int hedgedReadThreadpoolSize;
    private final long hedgedReadThresholdMillis;
    private final long hedgedReadOverallTimeoutMillis;

    public static HedgedReadProperty create(Configuration conf) {
      return new HedgedReadProperty(conf);
    }

    private HedgedReadProperty(Configuration conf) {
      Preconditions.checkNotNull(conf,
        "The configuration for connecting to the hbase cluster cannot be empty.");
      this.hedgedReadStrategyOption = HedgedReadStrategy.Option.find(
        conf.get(HqlConfigKeys.HedgedRead.STRATEGY, HqlConfigKeys.HedgedRead.DEFAULT_STRATEGY));
      this.hedgedReadWriteEnable = conf.getBoolean(HqlConfigKeys.HedgedRead.WRITE_ENABLE,
        HqlConfigKeys.HedgedRead.DEFAULT_WRITE_ENABLE);
      this.hedgedReadThreadpoolSize = conf.getInt(HqlConfigKeys.HedgedRead.THREADPOOL_SIZE_KEY,
        HqlConfigKeys.HedgedRead.DEFAULT_THREADPOOL_SIZE);
      this.isActivate =
          this.hedgedReadStrategyOption.isActivate() && this.hedgedReadThreadpoolSize > 0;
      this.hedgedReadThresholdMillis = conf.getLong(HqlConfigKeys.HedgedRead.THRESHOLD_MILLIS_KEY,
        HqlConfigKeys.HedgedRead.DEFAULT_THRESHOLD_MILLIS);
      if (this.hedgedReadThresholdMillis <= 0) {
        throw new IllegalStateException(
            HqlConfigKeys.HedgedRead.THRESHOLD_MILLIS_KEY + " must > 0");
      }
      this.hedgedReadOverallTimeoutMillis =
          conf.getLong(HqlConfigKeys.HedgedRead.OVERALL_TIMEOUT_MILLIS,
            HqlConfigKeys.HedgedRead.DEFAULT_OVERALL_TIMEOUT_MILLIS);
      if (this.hedgedReadOverallTimeoutMillis <= 0) {
        throw new IllegalStateException(
            HqlConfigKeys.HedgedRead.OVERALL_TIMEOUT_MILLIS + " must > 0");
      }
    }

    public boolean isActivate() {
      return isActivate;
    }

    public HedgedReadStrategy.Option getHedgedReadStrategyOption() {
      return hedgedReadStrategyOption;
    }

    public boolean isHedgedReadWriteEnable() {
      return hedgedReadWriteEnable;
    }

    public int getHedgedReadThreadpoolSize() {
      return hedgedReadThreadpoolSize;
    }

    public long getHedgedReadThresholdMillis() {
      return hedgedReadThresholdMillis;
    }

    public long getHedgedReadOverallTimeoutMillis() {
      return hedgedReadOverallTimeoutMillis;
    }
  }

  public BufferedMutatorOptions createDefaultBufferedMutatorOptions() {
    // todo 从configuration中填充默认值
    return BufferedMutatorOptions.builder().autoFlush(true).build();
  }

  public HedgedReadProperty getHedgedReadProperty() {
    return this.initHedgedReadProperty();
  }

  public Configuration getHedgedReadConfiguration() {
    return this.initHedgedReadConfiguration();
  }

  public HqlSession getSession(String tableName) {
    HqlTable table = getTable(tableName);
    return DefaultHqlSessionFactory.newInstance(table).openSession();
  }

  private HqlTable getTable(String tableName) {
    return new HqlTable(tableName, this);
  }

  private static void merge(Configuration destConf, Configuration srcConf) {
    for (Map.Entry<String, String> e : srcConf) {
      destConf.set(e.getKey(), e.getValue());
    }
  }

  private HedgedReadProperty initHedgedReadProperty() {
    return HedgedReadProperty.create(this);
  }

  private Configuration initHedgedReadConfiguration() {
    if (!this.getHedgedReadProperty().isActivate()) {
      return null;
    }
    Configuration hedgedReadConf = create();

    for (Map.Entry<String, String> entry : this) {
      String hedgedReadKey = entry.getKey();
      if (hedgedReadKey.startsWith(HqlConfigKeys.HedgedRead.PREFIX)) {
        continue;
      }
      if (hedgedReadKey.startsWith(HqlConfigKeys.HEDGED_READ_CLIENT_CONF_PREFIX)) {
        String hedgedReadClientKey = getHedgedReadClientKey(hedgedReadKey);
        if (hedgedReadClientKey.startsWith(HqlConfigKeys.HedgedRead.PREFIX)) {
          throw new IllegalStateException(
              "The hedged read cluster can no longer support the hedged read strategy.");
        }
        hedgedReadConf.set(hedgedReadClientKey, entry.getValue());
      }
    }
    return hedgedReadConf;
  }

  private String getHedgedReadClientKey(String hedgedReadKey) {
    return hedgedReadKey
        .substring(hedgedReadKey.indexOf(HqlConfigKeys.HEDGED_READ_CLIENT_CONF_PREFIX)
            + HqlConfigKeys.HEDGED_READ_CLIENT_CONF_PREFIX.length());
  }
}
