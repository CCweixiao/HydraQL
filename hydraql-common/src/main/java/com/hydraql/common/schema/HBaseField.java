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

package com.hydraql.common.schema;

import com.hydraql.common.type.TypeHandler;

/**
 * @author leojie 2022/11/20 16:49
 */
public class HBaseField {
  private boolean isRowKey;
  private String family;
  private String qualifier;
  private String familyAndQualifier;
  private int setterMethodIndex;
  private String setterMethodName;
  private int getterMethodIndex;
  private String getterMethodName;

  private Class<?> type;

  private TypeHandler<?> typeHandler;

  public HBaseField() {
  }

  public boolean isRowKey() {
    return isRowKey;
  }

  public void setRowKey(boolean rowKey) {
    isRowKey = rowKey;
  }

  public String getFamily() {
    return family;
  }

  public void setFamily(String family) {
    this.family = family;
  }

  public String getQualifier() {
    return qualifier;
  }

  public void setQualifier(String qualifier) {
    this.qualifier = qualifier;
  }

  public String getFamilyAndQualifier() {
    return familyAndQualifier;
  }

  public void setFamilyAndQualifier(String familyAndQualifier) {
    this.familyAndQualifier = familyAndQualifier;
  }

  public int getSetterMethodIndex() {
    return setterMethodIndex;
  }

  public void setSetterMethodIndex(int setterMethodIndex) {
    this.setterMethodIndex = setterMethodIndex;
  }

  public String getSetterMethodName() {
    return setterMethodName;
  }

  public void setSetterMethodName(String setterMethodName) {
    this.setterMethodName = setterMethodName;
  }

  public int getGetterMethodIndex() {
    return getterMethodIndex;
  }

  public void setGetterMethodIndex(int getterMethodIndex) {
    this.getterMethodIndex = getterMethodIndex;
  }

  public String getGetterMethodName() {
    return getterMethodName;
  }

  public void setGetterMethodName(String getterMethodName) {
    this.getterMethodName = getterMethodName;
  }

  public TypeHandler<?> getTypeHandler() {
    return typeHandler;
  }

  public void setTypeHandler(TypeHandler<?> typeHandler) {
    this.typeHandler = typeHandler;
  }

  public Class<?> getType() {
    return type;
  }

  public void setType(Class<?> type) {
    this.type = type;
  }
}
