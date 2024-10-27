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

import com.hydraql.exceptions.HBaseColumnTypeCastException;
import com.hydraql.util.Assert;
import com.hydraql.type.AbstractTypeHandler;

/**
 * @author leojie 2020/11/28 7:55 下午
 */
public class ByteHandler extends AbstractTypeHandler<Byte> {
  @Override
  protected boolean matchConverterType(Class<?> type) {
    return type == byte.class || type == Byte.class;
  }

  @Override
  protected byte[] convertObjValToByteArr(Class<?> type, Object value) {
    return new byte[] { (Byte) value };
  }

  @Override
  protected Object convertByteArrToObjVal(Class<?> type, byte[] bytes) {
    return bytes[0];
  }

  @Override
  public String extractMatchTtypeValue(String value) {
    throw new HBaseColumnTypeCastException("The string value cast to byte is unsupported");
  }

  @Override
  public String toString(Object val) {
    Assert.checkArgument(this.matchConverterType(val.getClass()),
      "The type of value " + val + " is not Byte or byte.");

    byte b = (byte) val;
    return String.valueOf(b);
  }
}