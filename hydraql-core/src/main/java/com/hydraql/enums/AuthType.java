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

package com.hydraql.enums;

import com.hydraql.exceptions.HBaseSdkUnsupportedAuthTypeException;
import com.hydraql.common.util.StringUtil;

/**
 * @author leojie 2022/10/22 14:52
 */
public enum AuthType {
  /**
   * auth type
   */
  SIMPLE("simple"), KERBEROS("kerberos");

  private final String authType;

  AuthType(String authType) {
    this.authType = authType;
  }

  public String getAuthType() {
    return authType;
  }

  public static AuthType findAuthType(String authType) {
    if (StringUtil.isBlank(authType)) {
      return SIMPLE;
    }
    for (AuthType value : AuthType.values()) {
      if (authType.equalsIgnoreCase(value.getAuthType())) {
        return value;
      }
    }
    throw new IllegalArgumentException(String.format("Unsupported auth type %s", authType));
  }

  public static boolean isKerberos(String authType) {
    if (StringUtil.isBlank(authType)) {
      return false;
    }
    return KERBEROS.getAuthType().equalsIgnoreCase(authType);
  }
}
