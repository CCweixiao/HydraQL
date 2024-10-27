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

import com.hydraql.adapter.service.ScanService;
import com.hydraql.HTableService;
import com.hydraql.handler.RowMapper;
import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.model.data.HBaseRowDataWithMultiVersions;
import org.apache.hadoop.hbase.client.Get;
import java.util.List;

/**
 * @author leojie 2023/7/20 22:12
 */
public abstract class BaseHBaseTableTemplate implements HTableService, ScanService {
  abstract <T> T get(Get get, Class<T> clazz);

  abstract <T> T get(String tableName, Get get, RowMapper<T> rowMapper);

  abstract HBaseRowData get(String tableName, Get get);

  abstract <T> List<T> getWithMultiVersions(Get get, int versions, Class<T> clazz);

  abstract <T> List<T> getWithMultiVersions(String tableName, Get get, int versions,
      RowMapper<T> rowMapper);

  abstract HBaseRowDataWithMultiVersions getWithMultiVersions(String tableName, Get get,
      int versions);

  abstract <T> List<T> gets(String tableName, List<Get> gets, Class<T> clazz);

  abstract <T> List<T> gets(String tableName, List<Get> gets, RowMapper<T> rowMapper);

  abstract List<HBaseRowData> gets(String tableName, List<Get> gets);
}
