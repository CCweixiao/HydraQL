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

package com.hydraql.adapter.dsl.antlr.visitor;

import com.hydraql.dsl.antlr.HydraQLParser;

/**
 * @author leojie 2023/9/10 21:07
 */
public class TableNameVisitor extends BaseVisitor<String> {
  public TableNameVisitor() {
    super(null);
  }

  @Override
  public String visitTable_ref(HydraQLParser.Table_refContext ctx) {
    String namespace = "";
    String tableName = "";
    HydraQLParser.Namespace_nameContext namespaceNameContext = ctx.namespace_name();
    if (namespaceNameContext != null && !namespaceNameContext.isEmpty()) {
      namespace = getText(namespaceNameContext.name());
    }
    HydraQLParser.Table_nameContext tableNameContext = ctx.table_name();
    if (tableNameContext != null && !tableNameContext.isEmpty()) {
      tableName = getText(tableNameContext.name());
    }
    return namespace.concat(":").concat(tableName);
  }

  public String extractTableName(HydraQLParser.Table_refContext tableRefContext) {
    return tableRefContext.accept(this);
  }
}
