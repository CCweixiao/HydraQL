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
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author leojie 2020/11/27 11:28 下午
 */
public class UpsertColListVisitor extends BaseVisitor<List<HBaseColumn>> {

  public UpsertColListVisitor(HBaseTableSchema tableSchema) {
    super(tableSchema);
  }

  @Override
  public List<HBaseColumn>
      visitUpsert_column_def_list(HydraQLParser.Upsert_column_def_listContext ctx) {
    if (ctx == null || ctx.isEmpty()) {
      return tableSchema.findAllColumns();
    }
    List<HydraQLParser.Column_refContext> columnRefContexts = ctx.column_ref();
    Map<String, HBaseColumn> columnSchemaMap = tableSchema.getColumnSchemaMap();
    List<HBaseColumn> columns = new ArrayList<>(columnSchemaMap.size());
    for (HydraQLParser.Column_refContext columnRefContext : columnRefContexts) {
      HBaseColumn column = this.extractColumn(columnRefContext);
      columns.add(column);
    }
    return columns;
  }

  private HBaseColumn extractColumn(HydraQLParser.Column_refContext columnRefContext) {
    HydraQLParser.Family_nameContext familyNameContext = columnRefContext.family_name();
    HydraQLParser.Column_nameContext columnNameContext = columnRefContext.column_name();
    return extractColumn(familyNameContext, columnNameContext);
  }

  public List<HBaseColumn> extractUpsertColumns(HydraQLParser.Upsert_column_def_listContext ctx) {
    return ctx.accept(this);
  }
}
