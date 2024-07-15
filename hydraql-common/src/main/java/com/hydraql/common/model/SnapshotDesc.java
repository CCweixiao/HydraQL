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

package com.hydraql.common.model;

/**
 * @author leojie 2020/9/25 11:05 下午
 */
public class SnapshotDesc {
  private String snapshotName;
  private String tableName;
  private long createTime;

  public String getSnapshotName() {
    return snapshotName;
  }

  public void setSnapshotName(String snapshotName) {
    this.snapshotName = snapshotName;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append('{');
    s.append("NAME");
    s.append(" => '");
    s.append(getSnapshotName());
    s.append(", ");
    s.append("TABLE_NAME");
    s.append(" => '");
    s.append(getTableName());
    s.append(", ");
    s.append("CREATE_TIME");
    s.append(" => '");
    s.append(getCreateTime());
    s.append("'");
    s.append("'");
    s.append('}');
    return s.toString();
  }
}
