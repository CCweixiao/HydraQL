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

package com.hydraql.entity;

import com.hydraql.annotation.GeneratedValue;
import com.hydraql.annotation.HBaseField;
import com.hydraql.annotation.HBaseRowKey;
import com.hydraql.annotation.HBaseTable;
import com.hydraql.generator.RowKeyGenerationStrategy;

/**
 * @author leojie@apache.org 2024/7/22 22:23
 */
@HBaseTable(tableName = "hydraql_test_table", defaultFamily = "cf")
public class UserInfo {
  @HBaseRowKey
  @GeneratedValue(strategy = RowKeyGenerationStrategy.HASHING)
  private String userId;

  @HBaseField
  private String username;

  @HBaseField
  private Integer age;

  @HBaseField(qualifier = "is_student")
  private Boolean isStudent;

  @HBaseField
  private Double cost;

  @HBaseField
  private String address;

  @HBaseField
  private Long createdTime;

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

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Boolean getStudent() {
    return isStudent;
  }

  public void setStudent(Boolean student) {
    isStudent = student;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Long getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Long createdTime) {
    this.createdTime = createdTime;
  }
}
