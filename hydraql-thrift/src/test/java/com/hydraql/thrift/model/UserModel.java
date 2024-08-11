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

package com.hydraql.thrift.model;

import com.hydraql.common.meta.annotations.HBaseColumn;
import com.hydraql.common.meta.annotations.HBaseRowKey;
import com.hydraql.common.meta.annotations.HBaseTable;

/**
 * @author leojie 2022/12/10 17:44
 */
@HBaseTable(namespace = "test", tableName = "t1", defaultFamily = "info")
public class UserModel {
  @HBaseRowKey
  private String userId;
  @HBaseColumn()
  private String nickName;
  @HBaseColumn(family = "detail", qualifier = "detailAddress")
  private String detailAddress;
  @HBaseColumn(family = "detail")
  private double detailPay;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getDetailAddress() {
    return detailAddress;
  }

  public void setDetailAddress(String detailAddress) {
    this.detailAddress = detailAddress;
  }

  public double getDetailPay() {
    return detailPay;
  }

  public void setDetailPay(double detailPay) {
    this.detailPay = detailPay;
  }

  @Override
  public String toString() {
    return "UserModel{" + "userId='" + userId + '\'' + ", nickName='" + nickName + '\''
        + ", detailAddress='" + detailAddress + '\'' + ", detailPay=" + detailPay + '}';
  }
}
