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

import com.hydraql.core.annotations.HBaseField;
import com.hydraql.core.annotations.HBaseRowKey;
import com.hydraql.core.annotations.HBaseTable;

import java.util.List;

/**
 * @author leojie 2022/11/5 13:56
 */
@HBaseTable(namespace = "default", tableName = "test_table", defaultFamily = "info")
public class CityModel {
  @HBaseRowKey
  private String cityId;
  private String cityName;
  private String cityAddress;

  @HBaseField(family = "detail")
  private Integer cityArea;
  @HBaseField(family = "detail", qualifier = "TOTAL_POPULATION")
  private Integer totalPopulation;
  @HBaseField(family = "detail", qualifier = "cityTagList")
  private List<CityTag> cityTagList;

  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getCityAddress() {
    return cityAddress;
  }

  public void setCityAddress(String cityAddress) {
    this.cityAddress = cityAddress;
  }

  public Integer getCityArea() {
    return cityArea;
  }

  public void setCityArea(Integer cityArea) {
    this.cityArea = cityArea;
  }

  public Integer getTotalPopulation() {
    return totalPopulation;
  }

  public void setTotalPopulation(Integer totalPopulation) {
    this.totalPopulation = totalPopulation;
  }

  public List<CityTag> getCityTagList() {
    return cityTagList;
  }

  public void setCityTagList(List<CityTag> cityTagList) {
    this.cityTagList = cityTagList;
  }

  @Override
  public String toString() {
    return "CityModel{" + "cityId='" + cityId + '\'' + ", cityName='" + cityName + '\''
        + ", cityAddress='" + cityAddress + '\'' + ", cityArea=" + cityArea + ", totalPopulation="
        + totalPopulation + ", cityTagList=" + cityTagList + '}';
  }
}
