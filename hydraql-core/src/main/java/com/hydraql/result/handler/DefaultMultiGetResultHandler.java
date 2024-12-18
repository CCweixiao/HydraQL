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
import com.hydraql.result.GetResult;
import com.hydraql.result.MultiGetResult;

/**
 * @author leojie@apache.org 2024/11/17 18:46
 */
public class DefaultMultiGetResultHandler extends DefaultGetResultHandler
    implements MultiGetResultHandler<MultiGetResult<GetResult>> {

  @Override
  public <R> MultiGetResult<GetResult> handleResult(R[] rs) throws Exception {
    if (null == rs || rs.length == 0) {
      return new MultiGetResult<>();
    }

    MultiGetResult<GetResult> multiGetResult = new MultiGetResult<>();
    for (R r : rs) {
      GetResult result = handleResult(r);
      if (result == null) {
        continue;
      }
      multiGetResult.appendResult(result.getRow(), result);
    }
    return multiGetResult;
  }

  @Override
  public <R> MultiGetResult<GetResult> handleResult(R[] rs, RowKeyGenerationStrategy strategy)
      throws Exception {
    if (strategy == null || strategy.isNotDefined()) {
      return handleResult(rs);
    }

    if (null == rs || rs.length == 0) {
      return new MultiGetResult<>();
    }
    MultiGetResult<GetResult> multiGetResult = new MultiGetResult<>();
    for (R r : rs) {
      GetResult result = handleResult(r, strategy);
      if (result == null) {
        continue;
      }
      multiGetResult.appendResult(result.getRow(), result);
    }
    return multiGetResult;
  }
}
