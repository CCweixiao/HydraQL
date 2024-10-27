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

import com.hydraql.HQLTable;
import com.hydraql.activerecord.Model;
import com.hydraql.hedgedread.HedgedReadStrategy;
import com.hydraql.util.Preconditions;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

import java.util.Map;

/**
 * @author leojie@apache.org 2024/9/23 22:18
 */
public abstract class AbstractHQLConfiguration {
  private final Configuration configuration;
  private final HedgedReadProperty hedgedReadProperty;
  private Configuration hedgedReadConfiguration;

  public AbstractHQLConfiguration(Configuration configuration) {
    this.configuration = configuration;
    this.hedgedReadProperty = HedgedReadProperty.create(configuration);
    this.hedgedReadConfiguration = createHedgedReadConfiguration();
  }

  public AbstractHQLConfiguration() {
    this(HBaseConfiguration.create());
  }

  public Configuration getHBaseConfiguration() {
    return configuration;
  }

  public Configuration getHBaseHedgedReadConfiguration() {
    return hedgedReadConfiguration;
  }

  public HedgedReadProperty getHedgedReadProperty() {
    return hedgedReadProperty;
  }

  public static class HedgedReadProperty {
    private final boolean isActivate;
    private final HedgedReadStrategy.Option hedgedReadStrategyOption;
    private final boolean hedgedReadWriteDisable;
    private final int hedgedReadThreadpoolSize;
    private final long hedgedReadThresholdMillis;
    private final long hedgedReadOverallTimeoutMillis;

    private static HedgedReadProperty create(Configuration configuration) {
      return new HedgedReadProperty(configuration);
    }

    private HedgedReadProperty(Configuration configuration) {
      Preconditions.checkNotNull(configuration, "The configuration of hbase client is null");
      this.hedgedReadStrategyOption =
          HedgedReadStrategy.Option.find(configuration.get(HQLConfigKeys.HedgedRead.STRATEGY,
            HQLConfigKeys.HedgedRead.DEFAULT_STRATEGY));
      this.hedgedReadWriteDisable =
              configuration.getBoolean(HQLConfigKeys.HedgedRead.WRITE_DISABLE,
                      HQLConfigKeys.HedgedRead.DEFAULT_WRITE_DISABLE);
      this.hedgedReadThreadpoolSize =
          configuration.getInt(HQLConfigKeys.HedgedRead.THREADPOOL_SIZE_KEY,
            HQLConfigKeys.HedgedRead.DEFAULT_THREADPOOL_SIZE);
      this.isActivate = this.hedgedReadStrategyOption.isActivate() && this.hedgedReadThreadpoolSize > 0;
      this.hedgedReadThresholdMillis =
          configuration.getLong(HQLConfigKeys.HedgedRead.THRESHOLD_MILLIS_KEY,
            HQLConfigKeys.HedgedRead.DEFAULT_THRESHOLD_MILLIS);
      if (this.hedgedReadThresholdMillis <= 0) {
        throw new IllegalStateException(
            HQLConfigKeys.HedgedRead.THRESHOLD_MILLIS_KEY + " must be greater than 0");
      }
      this.hedgedReadOverallTimeoutMillis =
          configuration.getLong(HQLConfigKeys.HedgedRead.OVERALL_TIMEOUT_MILLIS,
            HQLConfigKeys.HedgedRead.DEFAULT_OVERALL_TIMEOUT_MILLIS);
      if (this.hedgedReadOverallTimeoutMillis <= 0) {
        throw new IllegalStateException(
            HQLConfigKeys.HedgedRead.OVERALL_TIMEOUT_MILLIS + " must be greater than 0");
      }
    }

    public boolean isActivate() {
      return isActivate;
    }

    public HedgedReadStrategy.Option getHedgedReadStrategyOption() {
      return hedgedReadStrategyOption;
    }

    public boolean isHedgedReadWriteDisable() {
      return hedgedReadWriteDisable;
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

  private Configuration createHedgedReadConfiguration() {
    if (!this.hedgedReadProperty.isActivate()) {
      return null;
    }

    this.hedgedReadConfiguration = HBaseConfiguration.create();

    for (Map.Entry<String, String> entry : this.getHBaseConfiguration()) {
      String hedgedReadKey = entry.getKey();
      if (hedgedReadKey.startsWith(HQLConfigKeys.HedgedRead.PREFIX)) {
        continue;
      }
      if (hedgedReadKey.startsWith(HQLConfigKeys.HEDGED_READ_CLIENT_CONF_PREFIX)) {
        String hedgedReadClientKey = getHedgedReadClientKey(hedgedReadKey);
        if (hedgedReadClientKey.startsWith(HQLConfigKeys.HedgedRead.PREFIX)) {
          throw new IllegalStateException(
              "The hedged read cluster can no longer support the hedged read function.");
        }
        this.hedgedReadConfiguration.set(hedgedReadClientKey, entry.getValue());
      } else {
        this.hedgedReadConfiguration.set(entry.getKey(), entry.getValue());
      }
    }
    return this.hedgedReadConfiguration;
  }

  private String getHedgedReadClientKey(String hedgedReadKey) {
    return hedgedReadKey
        .substring(hedgedReadKey.indexOf(HQLConfigKeys.HEDGED_READ_CLIENT_CONF_PREFIX)
            + HQLConfigKeys.HEDGED_READ_CLIENT_CONF_PREFIX.length());
  }

  public BufferedMutatorOptions createDefaultBufferedMutatorOptions() {
    // todo 从configuration中填充默认值
    return BufferedMutatorOptions.builder().autoFlush(true).build();
  }

  public abstract HQLTable getTable(String tableName);

  public abstract <T extends Model<T>> HQLTable getTable(Class<T> entityClass);
}
