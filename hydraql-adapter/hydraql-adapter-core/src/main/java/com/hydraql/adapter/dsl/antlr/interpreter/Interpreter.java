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

package com.hydraql.adapter.dsl.antlr.interpreter;

import com.hydraql.exceptions.HydraQLTypeMissmatchException;
import com.hydraql.common.model.HQLType;

/**
 * @author leojie 2023/9/27 13:49
 */
public interface Interpreter {

  HQLType expectHqlType();

  default void checkParsedHqlTypeMatched(HQLType hqlType) {
    if (hqlType != expectHqlType()) {
      throw new HydraQLTypeMissmatchException(String.format(
        "The parsed hql type [%s] does not match the target execution hql type [%s].", hqlType,
        expectHqlType()));
    }
  }
}
