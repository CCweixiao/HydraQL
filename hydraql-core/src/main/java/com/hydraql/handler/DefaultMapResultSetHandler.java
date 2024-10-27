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

package com.hydraql.handler;

import com.hydraql.util.Constants;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leojie@apache.org 2024/9/4 21:52
 */
public class DefaultMapResultSetHandler implements ResultSetHandler<Map<String, byte[]>> {
  @Override
  public <RS> List<Map<String, byte[]>> handleResult(RS rs) throws Exception {
    if (rs == null) {
      return Collections.emptyList();
    }
    List<Map<String, byte[]>> results = new ArrayList<>();
    StringBuilder colNameSb = new StringBuilder();

    try (ResultScanner resultScanner = (ResultScanner) rs) {
      for (Result result : resultScanner) {
        Map<String, byte[]> tmpResult = new HashMap<>();
        for (Cell cell : result.rawCells()) {
          colNameSb.delete(0, colNameSb.length());
          colNameSb.append(Bytes.toString(CellUtil.cloneFamily(cell)));
          colNameSb.append(Constants.FAMILY_QUALIFIER_SEPARATOR);
          colNameSb.append(Bytes.toString(CellUtil.cloneQualifier(cell)));
          byte[] value = CellUtil.cloneValue(cell);
          tmpResult.put(colNameSb.toString(), value);
        }
        results.add(tmpResult);
      }
    }

    return results;
  }
}
