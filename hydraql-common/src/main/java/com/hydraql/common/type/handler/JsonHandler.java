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

package com.hydraql.common.type.handler;

import com.alibaba.fastjson2.JSON;
import com.hydraql.common.exception.HBaseColumnTypeCastException;
import com.hydraql.common.type.AbstractTypeHandler;

import java.nio.charset.Charset;

/**
 * @author leojie 2022/11/20 18:50
 */
public class JsonHandler extends AbstractTypeHandler<Object> {
  @Override
  protected boolean matchConverterType(Class<?> type) {
    return true;
  }

  @Override
  protected byte[] convertObjValToByteArr(Class<?> type, Object value) {
    try {
      String jsonVal = JSON.toJSONString(value);
      return jsonVal.getBytes(Charset.defaultCharset());
    } catch (Exception e) {
      throw new HBaseColumnTypeCastException(e);
    }
  }

  @Override
  protected Object convertByteArrToObjVal(Class<?> type, byte[] bytes) {
    String jsonVal = new String(bytes, Charset.defaultCharset());
    return JSON.parseObject(jsonVal, type);
  }

  @Override
  public String toString(Object val) {
    return JSON.toJSONString(val);
  }

  @Override
  public String extractMatchTtypeValue(String value) {
    return toString(value, JSON::toJSONString);
  }
}
