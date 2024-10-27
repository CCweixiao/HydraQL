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

import com.hydraql.metadata.HTableInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author leojie@apache.org 2024/9/4 22:01
 */
public class DefaultResultListHandler<E> extends DefaultResultHandler<E>
    implements ResultListHandler<E> {

  public DefaultResultListHandler(HTableInfo tableInfo) {
    super(tableInfo);
  }

  @Override
  public <R> List<E> handleResult(R[] rs) throws Exception {
    if (null == rs || rs.length == 0) {
      return Collections.emptyList();
    }
    List<E> results = new ArrayList<>(rs.length);
    for (R r : rs) {
      results.add(handleResult(r));
    }
    return results;
  }
}
