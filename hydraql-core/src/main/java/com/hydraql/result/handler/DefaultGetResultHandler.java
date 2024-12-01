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

import com.hydraql.result.GetResult;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;

/**
 * @author leojie@apache.org 2024/11/17 18:42
 */
public class DefaultGetResultHandler implements GetResultHandler<GetResult> {
  @Override
  public <R> GetResult handleResult(R r) throws Exception {
    if (r == null) {
      return null;
    }
    Result result = (Result) r;
    if (result.isEmpty()) {
      return null;
    }
    GetResult getResult = new GetResult(result.getRow());
    for (Cell cell : result.rawCells()) {
      getResult.appendValue(CellUtil.cloneFamily(cell), CellUtil.cloneQualifier(cell),
        CellUtil.cloneValue(cell), cell.getTimestamp());
    }
    return getResult;
  }
}
