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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;

import java.util.Properties;

/**
 * @author leojie 2023/7/21 17:15
 */
public abstract class BaseTemplateBuilder<T> {
  protected Configuration configuration;
  protected Connection connection;

  public BaseTemplateBuilder() {
  }

  public BaseTemplateBuilder<T> connection(Connection connection) {
    this.connection = connection;
    return this;
  }

  public BaseTemplateBuilder<T> configuration(Configuration configuration) {
    this.configuration = configuration;
    return this;
  }

  public BaseTemplateBuilder<T> configuration(Properties properties) {
    if (properties == null || properties.isEmpty()) {
      this.configuration = HBaseConfiguration.create();
      return this;
    }
    if (this.configuration == null) {
      this.configuration = HBaseConfiguration.create();
      for (String k : properties.stringPropertyNames()) {
        this.configuration.set(k, properties.getProperty(k));
      }
    }
    return this;
  }

  public BaseTemplateBuilder<T> configuration(String key, String value) {
    if (this.configuration == null) {
      this.configuration = HBaseConfiguration.create();
    }
    this.configuration.set(key, value);
    return this;
  }

  abstract T build();
}
