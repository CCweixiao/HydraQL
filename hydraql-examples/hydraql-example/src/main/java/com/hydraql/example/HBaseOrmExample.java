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

package com.hydraql.example;

import com.hydraql.common.model.example.CityModel;
import com.hydraql.common.query.GetRowParam;
import com.hydraql.template.BaseHBaseTableTemplate;
import com.hydraql.template.HBaseTableTemplate;
import java.util.Properties;

/**
 * @author leojie 2023/7/9 13:50
 */
public class HBaseOrmExample {
  public static void main(String[] args) {
    Properties properties = HBaseServiceExample.getProperties();
    BaseHBaseTableTemplate tableTemplate = HBaseTableTemplate.of(properties);
    tableTemplate.save(CityModel.createDefaultCityModel());
    CityModel result = tableTemplate.getRow(GetRowParam.of("a10001").build(), CityModel.class);
    System.out.println(result);
  }

}
