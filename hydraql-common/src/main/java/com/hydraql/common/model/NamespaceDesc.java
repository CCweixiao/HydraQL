/**
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

package com.hydraql.common.model;

import com.hydraql.common.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leojie 2020/9/9 9:48 下午
 */
public class NamespaceDesc {
  private String namespaceName;
  private Map<String, String> namespaceProps;

  public String getNamespaceName() {
    return namespaceName;
  }

  public void setNamespaceName(String namespaceName) {
    this.namespaceName = namespaceName;
  }

  public Map<String, String> getNamespaceProps() {
    return namespaceProps;
  }

  public void setNamespaceProps(Map<String, String> namespaceProps) {
    this.namespaceProps = namespaceProps;
  }

  public NamespaceDesc addNamespaceProp(final String key, String value) {
    if (this.namespaceProps == null) {
      this.namespaceProps = new HashMap<>();
    }
    if (StringUtil.isBlank(key)) {
      return this;
    }
    if (value == null) {
      value = "";
    }
    this.namespaceProps.put(key, value);
    return this;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append('{');
    s.append("NAME");
    s.append(" => '");
    s.append(this.namespaceName);
    s.append("'");

    this.namespaceProps.forEach((key, value) -> {
      if (key != null) {
        s.append(", ");
        s.append(key);
        s.append(" => '");
        s.append(value);
        s.append("'");
      }
    });

    s.append('}');
    return s.toString();
  }

}
