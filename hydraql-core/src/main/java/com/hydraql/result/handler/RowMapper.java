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

import java.util.List;

/**
 * <p>
 * HBase's data query result set maps the field properties of POJO objects.
 * </p>
 * @author leo.jie (leojie1314@gmail.com)
 */
@Deprecated
public interface RowMapper<T> {
  /**
   * convert function
   * @param r result
   * @param rowNum row number
   * @return pojo object
   * @throws Exception throw error
   */
  <R> T mapRow(R r, int rowNum) throws Exception;

  default <R> List<T> mapRowWithVersions(R r, int rowNum) throws Exception {
    return null;
  }
}
