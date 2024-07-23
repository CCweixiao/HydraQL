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

package com.hydraql.common.schema;

import com.hydraql.reflectasm.FieldAccess;
import com.hydraql.reflectasm.MethodAccess;

import java.util.List;

/**
 * @author leojie 2022/11/20 11:07
 */
public class HBaseTableSchema {
  private String tableName;
  private MethodAccess methodAccess;
  private FieldAccess fieldAccess;
  private List<HBaseField> fieldStructList;

  public HBaseTableSchema() {
  }

  public MethodAccess getMethodAccess() {
    return methodAccess;
  }

  public void setMethodAccess(MethodAccess methodAccess) {
    this.methodAccess = methodAccess;
  }

  public FieldAccess getFieldAccess() {
    return fieldAccess;
  }

  public void setFieldAccess(FieldAccess fieldAccess) {
    this.fieldAccess = fieldAccess;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public List<HBaseField> getFieldStructList() {
    return fieldStructList;
  }

  public void setFieldStructList(List<HBaseField> fieldStructList) {
    this.fieldStructList = fieldStructList;
  }
}
