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

import java.lang.reflect.Field;

/**
 * @author leojie@apache.org 2024/7/23 21:56
 */
public class FieldAccess {
  public final ClassAccess classAccess;
  public final ClassAccess.ClassAccessor classAccessor;

  protected FieldAccess(ClassAccess classAccess) {
    this.classAccess = classAccess;
    classAccessor = classAccess.classAccessor;
  }

  public int getIndex(String fieldName) {
    return classAccess.indexOfField(fieldName);
  }

  public void set(Object instance, String fieldName, Object value) {
    set(instance, getIndex(fieldName), value);
  }

  public Object get(Object instance, String fieldName) {
    return get(instance, getIndex(fieldName));
  }

  public String[] getFieldNames() {
    return classAccess.getFieldNames();
  }

  public Class<?>[] getFieldTypes() {
    return classAccess.getFieldTypes();
  }

  public int getFieldCount() {
    return classAccess.getFieldCount();
  }

  public Field[] getFields() {
    return classAccess.getFields();
  }

  public void set(Object instance, int fieldIndex, Object value) {
    classAccessor.set(instance, fieldIndex, value);
  }

  public void setBoolean(Object instance, int fieldIndex, boolean value) {
    classAccessor.setBoolean(instance, fieldIndex, value);
  }

  public void setByte(Object instance, int fieldIndex, byte value) {
    classAccessor.setByte(instance, fieldIndex, value);
  }

  public void setShort(Object instance, int fieldIndex, short value) {
    classAccessor.setShort(instance, fieldIndex, value);
  }

  public void setInt(Object instance, int fieldIndex, int value) {
    classAccessor.setInt(instance, fieldIndex, value);
  }

  public void setLong(Object instance, int fieldIndex, long value) {
    classAccessor.setLong(instance, fieldIndex, value);
  }

  public void setDouble(Object instance, int fieldIndex, double value) {
    classAccessor.setDouble(instance, fieldIndex, value);
  }

  public void setFloat(Object instance, int fieldIndex, float value) {
    classAccessor.setFloat(instance, fieldIndex, value);
  }

  public void setChar(Object instance, int fieldIndex, char value) {
    classAccessor.setChar(instance, fieldIndex, value);
  }

  public Object get(Object instance, int fieldIndex) {
    return classAccessor.get(instance, fieldIndex);
  }

  public char getChar(Object instance, int fieldIndex) {
    return classAccessor.getChar(instance, fieldIndex);
  }

  public boolean getBoolean(Object instance, int fieldIndex) {
    return classAccessor.getBoolean(instance, fieldIndex);
  }

  public byte getByte(Object instance, int fieldIndex) {
    return classAccessor.getByte(instance, fieldIndex);
  }

  public short getShort(Object instance, int fieldIndex) {
    return classAccessor.getShort(instance, fieldIndex);
  }

  public int getInt(Object instance, int fieldIndex) {
    return classAccessor.getInt(instance, fieldIndex);
  }

  public long getLong(Object instance, int fieldIndex) {
    return classAccessor.getLong(instance, fieldIndex);
  }

  public double getDouble(Object instance, int fieldIndex) {
    return classAccessor.getDouble(instance, fieldIndex);
  }

  public float getFloat(Object instance, int fieldIndex) {
    return classAccessor.getFloat(instance, fieldIndex);
  }

  public String getString(Object instance, int fieldIndex) {
    return (String) get(instance, fieldIndex);
  }

  public static FieldAccess get(Class<?> type) {
    return new FieldAccess(ClassAccess.get(type));
  }

  @Override
  public String toString() {
    return classAccess.toString();
  }

}
