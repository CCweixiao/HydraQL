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

package com.hydraql.adapter.service;

import com.hydraql.core.callback.RowMapper;
import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.model.data.HBaseRowDataWithMultiVersions;
import com.hydraql.common.query.ScanParams;
import org.apache.hadoop.hbase.client.Scan;

import java.util.List;

/**
 * @author leojie 2023/7/20 19:36
 */
public interface ScanService {
  Scan buildScan(ScanParams scanParams);

  <T> List<T> scan(Scan scan, Class<T> clazz);

  <T> List<T> scan(String tableName, Scan scan, RowMapper<T> rowMapper);

  List<HBaseRowData> scan(String tableName, Scan scan);

  List<HBaseRowDataWithMultiVersions> scanWithMultiVersions(String tableName, Scan scan,
      int versions);
}
