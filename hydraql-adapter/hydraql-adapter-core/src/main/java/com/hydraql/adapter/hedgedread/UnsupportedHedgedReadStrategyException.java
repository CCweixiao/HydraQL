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

package com.hydraql.adapter.hedgedread;

/**
 * @author leojie@apache.org 2024/4/14 21:05
 */
@Deprecated
public class UnsupportedHedgedReadStrategyException extends RuntimeException {

  private static final long serialVersionUID = 1607940322689035520L;

  public UnsupportedHedgedReadStrategyException(String message) {
    super(message);
  }

  public UnsupportedHedgedReadStrategyException(Throwable cause) {
    super(cause);
  }

  public UnsupportedHedgedReadStrategyException(String message, Throwable cause) {
    super(message, cause);
  }
}
