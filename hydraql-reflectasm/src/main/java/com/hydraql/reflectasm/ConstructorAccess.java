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
 * @author leojie@apache.org 2024/7/23 21:56
 */
public class ConstructorAccess<T> {
  public final ClassAccess classAccess;
  public final ClassAccess.ClassAccessor classAccessor;

  protected ConstructorAccess(ClassAccess classAccess) {
    this.classAccess = classAccess;
    classAccessor = classAccess.classAccessor;
  }

  public boolean isNonStaticMemberClass() {
    return classAccess.isNonStaticMemberClass();
  }

  /**
   * Constructor for top-level classes and static nested classes.
   * <p/>
   * If the underlying class is a inner (non-static nested) class, a new instance will be created
   * using <code>null</code> as the this$0 synthetic reference. The instantiated object will work as
   * long as it actually don't use any member variable or method fron the enclosing instance.
   */
  @SuppressWarnings("unchecked")
  public T newInstance() {
    return (T) classAccessor.newInstance();
  }

  @SuppressWarnings("unchecked")
  public T newInstance(Object enclosingInstance) {
    return (T) classAccessor.newInstance(0, enclosingInstance);
  }

  public static <T> ConstructorAccess<T> get(Class<T> type) {
    return new ConstructorAccess<>(ClassAccess.get(type));
  }

  @Override
  public String toString() {
    return classAccess.toString();
  }
}
