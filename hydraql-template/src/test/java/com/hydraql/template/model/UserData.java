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

package com.hydraql.template.model;

import com.hydraql.common.annotation.HBaseColumn;
import com.hydraql.common.annotation.HBaseRowKey;
import com.hydraql.common.annotation.HBaseTable;

import java.util.Map;

/**
 * @author leojie@apache.org 2024/7/22 22:23
 */
@HBaseTable(tableName = "user_info", defaultFamily = "cf")
public class UserData {
  @HBaseRowKey
  private String userId;

  @HBaseColumn
  private String username;

  @HBaseColumn
  private int age;

  @HBaseColumn
  private boolean isStudent;

  @HBaseColumn
  private Double cost;

  @HBaseColumn
  private Map<String, String> info;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean isStudent() {
    return isStudent;
  }

  public void setStudent(boolean student) {
    isStudent = student;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public Map<String, String> getInfo() {
    return info;
  }

  public void setInfo(Map<String, String> info) {
    this.info = info;
  }
}
