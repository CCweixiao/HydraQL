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

package com.hydraql.adapter.dsl.antlr.data;

/**
 * @author leojie 2023/7/28 19:58
 */
public class InsertColData {
  private final byte[] family;
  private final byte[] qualifier;
  private final byte[] value;

  public InsertColData(byte[] family, byte[] qualifier, byte[] value) {
    this.family = family;
    this.qualifier = qualifier;
    this.value = value;
  }

  public byte[] getFamily() {
    return family;
  }

  public byte[] getQualifier() {
    return qualifier;
  }

  public byte[] getValue() {
    return value;
  }
}
