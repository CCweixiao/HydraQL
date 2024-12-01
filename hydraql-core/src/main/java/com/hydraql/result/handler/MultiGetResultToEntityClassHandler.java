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

package com.hydraql.result.handler;

import com.hydraql.metadata.HTableInfo;
import org.apache.hadoop.hbase.client.Result;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leojie@apache.org 2024/9/4 22:01
 */
public class MultiGetResultToEntityClassHandler<E> extends GetResultToEntityClassHandler<E>
    implements MultiGetResultHandler<Map<byte[], E>> {

  public MultiGetResultToEntityClassHandler(HTableInfo tableInfo) {
    super(tableInfo);
  }

  @Override
  public <R> Map<byte[], E> handleResult(R[] rs) throws Exception {
    if (null == rs || rs.length == 0) {
      return Collections.emptyMap();
    }
    Map<byte[], E> resultMap = new HashMap<>(rs.length);
    for (R r : rs) {
      E e = handleResult(r);
      if (e == null) {
        continue;
      }
      Result result = (Result) r;
      resultMap.put(result.getRow(), e);
    }
    return resultMap;
  }
}
