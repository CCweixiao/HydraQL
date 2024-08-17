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

import com.hydraql.common.util.BytesUtil;
import com.hydraql.core.type.handler.EnumHandler;
import org.junit.Test;

/**
 * @author leojie@apache.org 2024/7/19 23:21
 */
public class EnumHandlerTest {
  enum TestEnum {
    SUCCESS(1, "success"), FAILED(2, "failed");

    private final int code;
    private final String msg;

    TestEnum(int code, String msg) {
      this.code = code;
      this.msg = msg;
    }

    public int getCode() {
      return code;
    }

    public String getMsg() {
      return msg;
    }
  }

  @Test
  public void test() {
    EnumHandler enumHandler = new EnumHandler();
    byte[] bytes = enumHandler.toBytes(TestEnum.SUCCESS);
    System.out.println(BytesUtil.toString(bytes));
    TestEnum testEnum = (TestEnum) enumHandler.toObject(TestEnum.class, bytes);
    System.out.println(testEnum.getMsg());
    System.out.println(testEnum);
  }
}
