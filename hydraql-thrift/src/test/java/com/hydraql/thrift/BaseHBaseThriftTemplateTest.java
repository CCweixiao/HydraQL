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

package com.hydraql.thrift;

import com.hydraql.thrift.model.CityModel;
import com.hydraql.thrift.model.CityTag;
import org.junit.Before;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseHBaseThriftTemplateTest {
  protected HBaseThriftPool thriftPool;
  protected HBaseThriftTemplate thriftTemplate;

  @Before
  public void init() {
    thriftPool = new HBaseThriftPool("myhbase", 9090);
    thriftTemplate = HBaseThriftTemplateFactory.getInstance("myhbase", 9090);
  }

  protected Map<String, Object> createDefaultDataMap() {
    Map<String, Object> data = new HashMap<>(1);
    data.put("f1:id", UUID.randomUUID().toString());
    data.put("f1:name", "yyf");
    data.put("f1:age", 18);
    data.put("f1:pay", 1000000L);
    Map<String, Object> tagsMap = new HashMap<>();
    tagsMap.put("address", "上海市");
    tagsMap.put("cost", 1000);
    data.put("f1:tags", tagsMap);
    data.put("f1:login_time", System.currentTimeMillis());
    return data;
  }

  protected Map<String, Map<String, Object>> createDefaultListDataMap() {
    Map<String, Map<String, Object>> data = new HashMap<>(4);
    data.put("a100011", createDefaultDataMap());
    data.put("a110011", createDefaultDataMap());
    data.put("b110011", createDefaultDataMap());
    data.put("b120011", createDefaultDataMap());
    return data;
  }

  protected CityModel createDefaultCityModel() {
    return cityModel("a10001", "北京", "北京市", 1000000, 40000000, Arrays.asList("首都", "旅游城市"));
  }

  protected List<CityModel> createDefaultCityModelList() {
    List<CityModel> cityModels = new ArrayList<>(4);
    cityModels
        .add(cityModel("a11001", "北京", "北京市", 1000001, 40000000, Arrays.asList("首都", "旅游城市")));
    cityModels
        .add(cityModel("a12002", "上海", "上海市", 1000002, 20000000, Arrays.asList("魔都", "旅游城市")));
    cityModels
        .add(cityModel("b20001", "广州", "广州市", 1000003, 40000000, Arrays.asList("沿海城市", "旅游城市")));
    cityModels
        .add(cityModel("b20002", "深圳", "深圳市", 1000004, 40000000, Arrays.asList("沿海城市", "发达地区")));
    return cityModels;
  }

  private CityModel cityModel(String cityId, String cityName, String cityAddress, int cityArea,
      int totalPopulation, List<String> tagNameList) {
    CityModel cityModel = new CityModel();
    cityModel.setCityId(cityId);
    cityModel.setCountryName("中国");
    cityModel.setCityName(cityName);
    cityModel.setCityAddress(cityAddress);
    cityModel.setCityArea(cityArea);
    cityModel.setTotalPopulation(totalPopulation);
    cityModel.setCityTagList(tagNameList.stream().map(CityTag::new).collect(Collectors.toList()));
    return cityModel;
  }
}
