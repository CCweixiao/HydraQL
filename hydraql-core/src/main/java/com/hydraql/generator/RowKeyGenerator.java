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

package com.hydraql.generator;

/**
 * @author leojie@apache.org 2024/8/10 23:24
 */
public interface RowKeyGenerator {
  /**
   * The original row key gets a new row key after applying the generation strategy.
   * @param originalRow original row key
   * @return new row key after applying the row generation strategy
   */
  Object apply(Object originalRow);

  /**
   * The original row key gets a new bytes[] row key after applying the generation strategy.
   * @param originalRow original row key
   * @return new bytes[] row key after applying the row generation strategy
   */
  byte[] applyToBytes(Object originalRow);

  /**
   * Recover the generated key
   * @param generatedRow generated row key
   * @return original row key
   */
  Object recover(Object generatedRow);

  /**
   * Recover the generated row, return byte[]
   * @param generatedRow generated row key
   * @return original row key, byte[]
   */
  byte[] recoverToBytes(byte[] generatedRow);
}
