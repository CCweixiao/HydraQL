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

package com.hydraql.common.type;

import java.nio.ByteBuffer;

/**
 * @author leojie 2022/11/13 12:57
 */
public interface TypeHandler<T> {

  byte[] toBytes(Class<?> type, Object value);

  byte[] toBytes(Object value);

  String toString(Object value);

  ByteBuffer toByteBuffer(Class<?> type, Object value);

  ByteBuffer toByteBuffer(Object value);

  Object toObject(Class<?> type, byte[] bytes);

  String toString(Class<?> type, byte[] bytes);

  Object toObject(Class<?> type, ByteBuffer buffer);

  String toString(String value, TypeConverter<T> typeConverter);

  String extractMatchTtypeValue(String value);
}
