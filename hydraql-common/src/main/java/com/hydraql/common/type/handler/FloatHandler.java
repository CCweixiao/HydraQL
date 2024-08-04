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

package com.hydraql.common.type.handler;

import com.hydraql.common.type.AbstractTypeHandler;
import com.hydraql.common.util.BytesUtil;

/**
 * @author leojie 2020/11/28 7:52 下午
 */
public class FloatHandler extends AbstractTypeHandler<Float> {
  @Override
  protected boolean matchConverterType(Class<?> type) {
    return type == float.class || type == Float.class;
  }

  @Override
  protected byte[] convertObjValToByteArr(Class<?> type, Object value) {
    if (value == null) {
      return null;
    }
    float val = convertByteArrToObjVal(value.toString(), Float::parseFloat);
    return BytesUtil.toBytes(val);
  }

  @Override
  protected Object convertByteArrToObjVal(Class<?> type, byte[] bytes) {
    if (bytes == null) {
      return null;
    }
    return BytesUtil.toFloat(bytes);
  }

  @Override
  public String extractMatchTtypeValue(String value) {
    return toString(value, Float::parseFloat);
  }
}
