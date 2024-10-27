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

package com.hydraql.type.handler;

import com.hydraql.type.AbstractTypeHandler;
import com.hydraql.common.util.NumberUtil;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * @author leojie 2022/11/19 22:24
 */
public class BigIntegerHandler extends AbstractTypeHandler<BigInteger> {
  @Override
  protected boolean matchConverterType(Class<?> type) {
    return BigInteger.class == type;
  }

  @Override
  protected byte[] convertObjValToByteArr(Class<?> type, Object value) {
    BigInteger bi = (BigInteger) value;
    return bi.toString().getBytes(StandardCharsets.UTF_8);
  }

  @Override
  protected Object convertByteArrToObjVal(Class<?> type, byte[] bytes) {
    return NumberUtil.toBigInteger(new String(bytes, StandardCharsets.UTF_8));
  }

  @Override
  public String extractMatchTtypeValue(String value) {
    return toString(value, BigInteger::new);
  }
}
