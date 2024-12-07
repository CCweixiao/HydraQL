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

package com.hydraql.result;

import com.hydraql.convertor.ValueConvertor;

import java.util.HashMap;

/**
 * @author leojie@apache.org 2024/11/17 17:44
 */
public class MultiGetResult<E> extends HashMap<Row, E> {
  private static final long serialVersionUID = -4507545878111543637L;

  public MultiGetResult() {
  }

  public void appendResult(byte[] row, E result) {
    this.put(Row.of(row), result);
  }

  public E getResult(byte[] row) {
    return this.get(Row.of(row));
  }

  public E getResult(String row) {
    return getResult(ValueConvertor.toBytes(row));
  }

  public E getResult(Integer row) {
    return getResult(ValueConvertor.toBytes(row));
  }

  public E getResult(Long row) {
    return getResult(ValueConvertor.toBytes(row));
  }
}
