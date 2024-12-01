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
 * Defines the types of hbase row key generation strategies.
 * @author leojie@apache.org 2024/8/10 22:46
 */
public enum GenerationType {
  /**
   * reverse row key, for example: 123 -> 321
   */
  REVERSING,
  /**
   * Take the row key md5 value to compute the hash value, and splice it in front of the original
   * key.
   */
  HASHING,
  /**
   * do nothing.
   */
  NOTHING;

  public RowKeyGenerator getRowKeyGenerator() {
    if (this == GenerationType.HASHING) {
      return new RowKeyHashingGenerator();
    } else if (this == GenerationType.REVERSING) {
      return new RowKeyReverseGenerator();
    } else {
      return new RowKeyNothingGenerator();
    }
  }
}
