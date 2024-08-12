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

import com.hydraql.reflectasm.Reflector;
import com.hydraql.reflectasm.exceptions.ReflectionException;

import java.lang.reflect.Field;

/**
 * @author leojie@apache.org 2024/8/11 18:07
 */
public class SetFieldInvoker implements Invoker {
  private final Field field;

  public SetFieldInvoker(Field field) {
    this.field = field;
  }

  @Override
  public Object invoke(Object target, Object[] args) {
    try {
      field.set(target, args[0]);
    } catch (IllegalAccessException e) {
      if (!Reflector.canControlMemberAccessible()) {
        throw new ReflectionException(e);
      }
      field.setAccessible(true);
      try {
        field.set(target, args[0]);
      } catch (IllegalAccessException ex) {
        throw new ReflectionException(ex);
      }
    }
    return null;
  }

  @Override
  public Class<?> getType() {
    return field.getType();
  }
}
