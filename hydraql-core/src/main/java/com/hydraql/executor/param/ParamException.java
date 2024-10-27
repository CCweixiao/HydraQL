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

package com.hydraql.executor.param;

import com.hydraql.exceptions.HydraQlException;

/**
 * @author leojie@apache.org 2024/9/5 23:16
 */
public class ParamException extends HydraQlException {
  private static final long serialVersionUID = -4242299980123646552L;

  public ParamException() {
  }

  public ParamException(String message) {
    super(message);
  }

  public ParamException(String message, Throwable cause) {
    super(message, cause);
  }

  public ParamException(Throwable cause) {
    super(cause);
  }
}