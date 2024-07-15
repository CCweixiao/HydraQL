/**
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

package com.hydraql.dsl.model;

import com.hydraql.common.constants.HBaseConfigKeys;

/**
 * @author leojie 2022/12/3 11:13
 */
public class TableQueryProperties {
  private int scanCaching = HBaseConfigKeys.HBASE_CLIENT_DEFAULT_SCANNER_CACHING;
  private int scanBatch = HBaseConfigKeys.HBASE_CLIENT_DEFAULT_SCANNER_BATCH;
  private boolean scanCacheBlocks = HBaseConfigKeys.HBASE_CLIENT_DEFAULT_SCANNER_CACHE;
  private int deleteBatch = HBaseConfigKeys.HBASE_CLIENT_DEFAULT_DELETE_BATCH;

  public int getScanCaching() {
    return scanCaching;
  }

  public void setScanCaching(int scanCaching) {
    this.scanCaching = scanCaching;
  }

  public int getScanBatch() {
    return scanBatch;
  }

  public void setScanBatch(int scanBatch) {
    this.scanBatch = scanBatch;
  }

  public boolean isScanCacheBlocks() {
    return scanCacheBlocks;
  }

  public void setScanCacheBlocks(boolean scanCacheBlocks) {
    this.scanCacheBlocks = scanCacheBlocks;
  }

  public int getDeleteBatch() {
    return deleteBatch;
  }

  public void setDeleteBatch(int deleteBatch) {
    this.deleteBatch = deleteBatch;
  }
}
