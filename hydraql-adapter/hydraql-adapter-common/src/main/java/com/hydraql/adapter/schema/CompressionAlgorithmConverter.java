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

package com.hydraql.adapter.schema;

import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.io.compress.Compression;

/**
 * @author leojie@apache.org 2024/7/13 23:48
 */
public final class CompressionAlgorithmConverter {
  private CompressionAlgorithmConverter() {
  }

  public static Compression.Algorithm apply(String compressionAlgorithm) {
    if (StringUtil.isBlank(compressionAlgorithm)) {
      return Compression.Algorithm.NONE;
    }
    for (Compression.Algorithm value : Compression.Algorithm.values()) {
      if (compressionAlgorithm.equalsIgnoreCase(value.getName())) {
        return value;
      }
    }
    throw new IllegalArgumentException("Unsupported compression algorithm " + compressionAlgorithm);
  }
}
