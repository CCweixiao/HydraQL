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

package com.hydraql.reflectasm.invoker;

import com.hydraql.reflectasm.MethodAccess;

import java.lang.reflect.Method;

/**
 * @author leojie@apache.org 2024/8/11 14:41
 */
public class MethodInvoker implements Invoker {
  private final Class<?> type;
  private final MethodAccess methodAccess;
  private final int methodIndex;

  public MethodInvoker(MethodAccess methodAccess, int methodIndex) {
    this.methodAccess = methodAccess;
    this.methodIndex = methodIndex;
    Method method = methodAccess.getMethod(methodIndex);
    if (method.getParameterTypes().length == 1) {
      type = method.getParameterTypes()[0];
    } else {
      type = method.getReturnType();
    }
  }

  @Override
  public Object invoke(Object target, Object[] args) {
    return methodAccess.invoke(target, methodIndex, args);
  }

  @Override
  public Class<?> getType() {
    return type;
  }
}
