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

package com.hydraql.session;

import com.hydraql.AbstractHqlTable;
import com.hydraql.exceptions.HydraQlException;
import com.hydraql.executor.BaseExecutor;

import java.io.IOException;

/**
 * @author leojie@apache.org 2024/9/7 22:20
 */
public class DefaultHqlSessionFactory implements HqlSessionFactory {
  private final AbstractHqlTable table;

  private DefaultHqlSessionFactory(AbstractHqlTable table) {
    this.table = table;
  }

  public static DefaultHqlSessionFactory newInstance(AbstractHqlTable table) {
    return new DefaultHqlSessionFactory(table);
  }

  @Override
  public HqlSession openSession() {
    return openSessionInternal();
  }

  private HqlSession openSessionInternal() {
    HqlConnection connection = null;
    try {
      connection = DefaultHqlConnectionFactory.create().newConnection(table);
      // bind hydraql connection for hydraql table
      table.bindConnection(connection);
      final BaseExecutor executor = table.newExecutor();
      return createSession(executor);
    } catch (Exception e) {
      closeConnection(connection);
      throw new HydraQlException("Error opening hydraql session.  Cause: " + e);
    }
  }

  private HqlSession createSession(BaseExecutor executor) {
    return new DefaultHqlSession(executor);
  }

  private void closeConnection(HqlConnection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (IOException ignore) {
        // Intentionally ignore. Prefer previous error.
      }
    }
  }
}
