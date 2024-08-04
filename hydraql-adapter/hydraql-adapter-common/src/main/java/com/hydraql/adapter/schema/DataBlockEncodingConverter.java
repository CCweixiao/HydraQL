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
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;

/**
 * @author leojie@apache.org 2024/7/13 23:53
 */
public final class DataBlockEncodingConverter {
  private DataBlockEncodingConverter() {
  }

  public static DataBlockEncoding apply(String dataBlockEncoding) {
    if (StringUtil.isBlank(dataBlockEncoding)) {
      return DataBlockEncoding.NONE;
    }
    for (DataBlockEncoding value : DataBlockEncoding.values()) {
      if (dataBlockEncoding.equals(value.name())) {
        return value;
      }
    }
    throw new IllegalArgumentException("Unsupported dataBlockEncoding " + dataBlockEncoding);
  }
}
