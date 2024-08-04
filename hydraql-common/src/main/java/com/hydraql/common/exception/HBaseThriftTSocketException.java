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

package com.hydraql.common.exception;

/**
 * @author leojie 2020/12/27 2:42 下午
 */
public class HBaseThriftTSocketException extends HBaseThriftException {

  private static final long serialVersionUID = 1L;

  public HBaseThriftTSocketException(String message) {
    super(message);
  }

  public HBaseThriftTSocketException(Throwable e) {
    super(e);
  }

  public HBaseThriftTSocketException(String message, Throwable cause) {
    super(message, cause);
  }
}
