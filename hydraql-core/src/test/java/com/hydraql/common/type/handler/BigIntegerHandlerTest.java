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

import com.hydraql.type.handler.BigIntegerHandler;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author leojie 2022/11/25 23:12
 */
public class BigIntegerHandlerTest {
  @Test
  public void testToBigInteger() {
    BigIntegerHandler bigIntegerHandler = new BigIntegerHandler();
    BigDecimal decimal = new BigDecimal("1000.11");
    String s = decimal.toString();
    // Assert.assertEquals(1000, bigIntegerHandler.to(s).intValue());
    // Assert.assertEquals(1000, bigIntegerHandler.toBigInteger("1000.1").intValue());
    BigInteger bigInteger = new BigInteger("1234567890000");
    // Assert.assertEquals(bigInteger, bigIntegerHandler.toBigInteger(bigInteger.toString()));
  }
}
