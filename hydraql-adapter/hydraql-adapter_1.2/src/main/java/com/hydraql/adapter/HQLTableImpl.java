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

import com.hydraql.HQLTable;
import com.hydraql.activerecord.Model;
import com.hydraql.conf.AbstractHQLConfiguration;
import com.hydraql.executor.BaseExecutor;

/**
 * @author leojie@apache.org 2024/10/27 12:37
 */
public class HQLTableImpl extends HQLTable {
  public HQLTableImpl(String tableName, AbstractHQLConfiguration configuration) {
    super(tableName, configuration);
  }

  public HQLTableImpl(Class<? extends Model<?>> tableEntityClass,
      AbstractHQLConfiguration configuration) {
    super(tableEntityClass, configuration);
  }

  @Override
  public BaseExecutor newExecutor() {
    return new HQlExecutor(this);
  }
}
