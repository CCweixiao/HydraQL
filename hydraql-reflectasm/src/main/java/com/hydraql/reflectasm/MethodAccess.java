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

package com.hydraql.reflectasm;

/**
 * @author leojie@apache.org 2024/7/23 21:57
 */
public class MethodAccess {
  public final ClassAccess classAccess;
  public final ClassAccess.ClassAccessor classAccessor;

  protected MethodAccess(ClassAccess classAccess) {
    this.classAccess = classAccess;
    classAccessor = classAccess.classAccessor;
  }

  public Object invoke(Object object, int methodIndex, Object... args) {
    return classAccessor.invoke(object, methodIndex, args);
  }

  /**
   * Invokes the method with the specified name and the specified param types.
   */
  public Object invoke(Object object, String methodName, Class<?>[] paramTypes, Object... args) {
    return invoke(object, getIndex(methodName, paramTypes), args);
  }

  /**
   * Invokes the first method with the specified name and the specified number of arguments.
   */
  public Object invoke(Object object, String methodName, Object... args) {
    return invoke(object, getIndex(methodName, args == null ? 0 : args.length), args);
  }

  /**
   * Returns the index of the first method with the specified name.
   */
  public int getIndex(String methodName) {
    return classAccess.indexOfMethod(methodName);
  }

  /**
   * Returns the index of the first method with the specified name and param types.
   */
  public int getIndex(String methodName, Class<?>... paramTypes) {
    return classAccess.indexOfMethod(methodName, paramTypes);
  }

  /**
   * Returns the index of the first method with the specified name and the specified number of
   * arguments.
   */
  public int getIndex(String methodName, int paramsCount) {
    return classAccess.indexOfMethod(methodName, paramsCount);
  }

  public String[] getMethodNames() {
    return classAccess.getMethodNames();
  }

  public Class<?>[][] getParameterTypes() {
    return classAccess.getParameterTypes();
  }

  public Class<?>[] getReturnTypes() {
    return classAccess.getReturnTypes();
  }

  public static MethodAccess get(Class<?> type) {
    return new MethodAccess(ClassAccess.get(type));
  }

  @Override
  public String toString() {
    return classAccess.toString();
  }
}
