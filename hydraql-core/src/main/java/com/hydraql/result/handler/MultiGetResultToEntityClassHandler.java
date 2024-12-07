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

import com.hydraql.generator.RowKeyGenerationStrategy;
import com.hydraql.metadata.HTableInfo;
import com.hydraql.result.MultiGetResult;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author leojie@apache.org 2024/9/4 22:01
 */
public class MultiGetResultToEntityClassHandler<E> extends GetResultToEntityClassHandler<E>
    implements MultiGetResultHandler<MultiGetResult<E>> {

  public MultiGetResultToEntityClassHandler(HTableInfo tableInfo) {
    super(tableInfo);
  }

  @Override
  public <R> MultiGetResult<E> handleResult(R[] rs) throws Exception {
    if (null == rs || rs.length == 0) {
      return new MultiGetResult<>();
    }

    MultiGetResult<E> multiGetResult = new MultiGetResult<>();
    for (R r : rs) {
      E e = handleResult(r);
      if (e == null) {
        continue;
      }

      Result result = (Result) r;
      multiGetResult.appendResult(result.getRow(), e);
    }
    return multiGetResult;
  }

  @Override
  public <R> MultiGetResult<E> handleResult(R[] rs, RowKeyGenerationStrategy strategy)
      throws Exception {
    if (strategy == null || strategy.isNotDefined()) {
      return handleResult(rs);
    }

    MultiGetResult<E> multiGetResult = new MultiGetResult<>();
    for (R r : rs) {
      E e = handleResult(r);
      if (e == null) {
        continue;
      }

      Result result = (Result) r;
      byte[] row = strategy.getRowKeyGenerator().recoverToBytes(result.getRow());
      multiGetResult.appendResult(row, e);
    }
    return multiGetResult;
  }
}
