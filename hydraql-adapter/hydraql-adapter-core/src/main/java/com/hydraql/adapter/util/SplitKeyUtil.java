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

package com.hydraql.adapter.util;

import org.apache.hadoop.hbase.util.Bytes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leojie 2021/2/2 5:53 下午
 */
public class SplitKeyUtil {
  public static byte[][] getSplitKeyBytes(String[] splitKeys) {
    final List<byte[]> bytes =
        Arrays.stream(splitKeys).distinct().map(Bytes::toBytes).collect(Collectors.toList());
    byte[][] splitKeyBytes = new byte[bytes.size()][];
    for (int i = 0; i < bytes.size(); i++) {
      splitKeyBytes[i] = bytes.get(i);
    }
    return splitKeyBytes;
  }

  public static void main(String[] args) {
    final byte[][] splitKeyBytes = getSplitKeyBytes(new String[] { "1", "1", "2" });
    System.out.println(Arrays.deepToString(splitKeyBytes));
  }
}
