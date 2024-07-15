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

import com.hydraql.common.exception.HBaseColumnTypeCastException;
import com.hydraql.common.lang.Assert;
import com.hydraql.common.type.AbstractTypeHandler;

import java.nio.charset.Charset;

/**
 * @author leojie 2020/11/28 7:40 下午
 */
public class EnumHandler extends AbstractTypeHandler<Enum<?>> {
  @Override
  protected boolean matchConverterType(Class<?> type) {
    return type.isEnum();
  }

  @Override
  protected byte[] convertObjValToByteArr(Class<?> type, Object value) {
    String name = ((Enum<?>) value).name();
    return (name).getBytes(Charset.defaultCharset());
  }

  @Override
  public String extractMatchTtypeValue(String value) {
    throw new HBaseColumnTypeCastException("The string value cast to enum is unsupported");
  }

  @Override
  @SuppressWarnings("unchecked")
  protected Object convertByteArrToObjVal(Class type, byte[] bytes) {
    String name = new String(bytes, Charset.defaultCharset());
    return Enum.valueOf(type, name);
  }

  @Override
  public String toString(Object val) {
    Assert.checkArgument(this.matchConverterType(val.getClass()),
      "The type of value " + val + " is not Enum.");
    return val.toString();
  }
}
