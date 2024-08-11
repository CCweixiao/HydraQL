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

package com.hydraql.common.model.example;

import com.hydraql.common.meta.annotations.HBaseColumn;
import com.hydraql.common.meta.annotations.HBaseRowKey;
import com.hydraql.common.meta.annotations.HBaseTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leojie 2022/11/5 13:56
 */
@HBaseTable(namespace = "default", tableName = "t2", defaultFamily = "info")
public class CityModel {
  @HBaseRowKey
  private String cityId;
  private String cityName;
  private String cityAddress;

  @HBaseColumn(family = "detail")
  private Integer cityArea;
  @HBaseColumn(family = "detail", qualifier = "TOTAL_POPULATION")
  private Integer totalPopulation;
  @HBaseColumn(family = "detail", qualifier = "cityTagList")
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

  public static CityModel createDefaultCityModel() {
    return cityModel("a10001", "北京", "北京市", 1000000, 40000000, Arrays.asList("首都", "旅游城市"));
  }

  public static List<CityModel> createDefaultCityModelList() {
    List<CityModel> cityModels = new ArrayList<>(4);
    cityModels
        .add(cityModel("a10001", "北京", "北京市", 1000000, 40000000, Arrays.asList("首都", "旅游城市")));
    cityModels
        .add(cityModel("a10002", "上海", "上海市", 1000000, 20000000, Arrays.asList("魔都", "旅游城市")));
    cityModels
        .add(cityModel("b20001", "广州", "广州市", 1000000, 40000000, Arrays.asList("沿海城市", "旅游城市")));
    cityModels
        .add(cityModel("b20002", "深圳", "深圳市", 1000000, 40000000, Arrays.asList("沿海城市", "发达地区")));
    return cityModels;
  }

  private static CityModel cityModel(String cityId, String cityName, String cityAddress,
      int cityArea, int totalPopulation, List<String> tagNameList) {
    CityModel cityModel = new CityModel();
    cityModel.setCityId(cityId);
    cityModel.setCityName(cityName);
    cityModel.setCityAddress(cityAddress);
    cityModel.setCityArea(cityArea);
    cityModel.setTotalPopulation(totalPopulation);
    cityModel.setCityTagList(tagNameList.stream().map(CityTag::new).collect(Collectors.toList()));
    return cityModel;
  }
}
