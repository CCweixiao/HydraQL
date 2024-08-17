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

package com.hydraql.example;

import com.hydraql.core.annotations.HBaseField;
import com.hydraql.core.annotations.HBaseRowKey;
import com.hydraql.core.annotations.HBaseTable;

/**
 * @author leojie@apache.org 2024/4/11 11:17
 */
@HBaseTable(tableName = "usertable", defaultFamily = "cf")
public class UserTestData {
  @HBaseRowKey
  private String userId;

  @HBaseField(qualifier = "field0")
  private String field0;

  @HBaseField(qualifier = "field1")
  private String field1;

  @HBaseField(qualifier = "field2")
  private String field2;

  @HBaseField(qualifier = "field3")
  private String field3;

  @HBaseField(qualifier = "field4")
  private String field4;

  @HBaseField(qualifier = "field5")
  private String field5;

  @HBaseField(qualifier = "field6")
  private String field6;

  @HBaseField(qualifier = "field7")
  private String field7;

  @HBaseField(qualifier = "field8")
  private String field8;

  @HBaseField(qualifier = "field9")
  private String field9;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getField0() {
    return field0;
  }

  public void setField0(String field0) {
    this.field0 = field0;
  }

  public String getField1() {
    return field1;
  }

  public void setField1(String field1) {
    this.field1 = field1;
  }

  public String getField2() {
    return field2;
  }

  public void setField2(String field2) {
    this.field2 = field2;
  }

  public String getField3() {
    return field3;
  }

  public void setField3(String field3) {
    this.field3 = field3;
  }

  public String getField4() {
    return field4;
  }

  public void setField4(String field4) {
    this.field4 = field4;
  }

  public String getField5() {
    return field5;
  }

  public void setField5(String field5) {
    this.field5 = field5;
  }

  public String getField6() {
    return field6;
  }

  public void setField6(String field6) {
    this.field6 = field6;
  }

  public String getField7() {
    return field7;
  }

  public void setField7(String field7) {
    this.field7 = field7;
  }

  public String getField8() {
    return field8;
  }

  public void setField8(String field8) {
    this.field8 = field8;
  }

  public String getField9() {
    return field9;
  }

  public void setField9(String field9) {
    this.field9 = field9;
  }

  @Override
  public String toString() {
    return "UserTestData{" + "userId='" + userId + '\'' + ", field0='" + field0 + '\''
        + ", field1='" + field1 + '\'' + ", field2='" + field2 + '\'' + ", field3='" + field3 + '\''
        + ", field4='" + field4 + '\'' + ", field5='" + field5 + '\'' + ", field6='" + field6 + '\''
        + ", field7='" + field7 + '\'' + ", field8='" + field8 + '\'' + ", field9='" + field9 + '\''
        + '}';
  }
}
