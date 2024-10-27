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

import com.hydraql.exceptions.HBaseSqlAnalysisException;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author leojie 2020/11/27 11:28 下午
 */
public class DeleteColListVisitor extends BaseVisitor<List<HBaseColumn>> {

  public DeleteColListVisitor(HBaseTableSchema tableSchema) {
    super(tableSchema);
  }

  @Override
  public List<HBaseColumn>
      visitDeleteOneFamilyAllCol(HydraQLParser.DeleteOneFamilyAllColContext ctx) {
    HydraQLParser.Family_nameContext familyNameContext = ctx.family_name();
    String family = getText(familyNameContext.name());
    List<HBaseColumn> allColumnsOfOneFamily =
        this.getTableSchema().findAllColumnsOfOneFamily(family);
    if (allColumnsOfOneFamily.isEmpty()) {
      throw new HBaseSqlAnalysisException(
          String.format("The column family [%s] is not defined.", family));
    }
    return allColumnsOfOneFamily;
  }

  @Override
  public List<HBaseColumn> visitDeleteFamilyAndCol(HydraQLParser.DeleteFamilyAndColContext ctx) {
    HydraQLParser.Family_nameContext familyNameContext = ctx.family_name();
    HydraQLParser.Column_nameContext columnNameContext = ctx.column_name();
    HBaseColumn hBaseColumn = this.extractColumn(familyNameContext, columnNameContext);
    return Collections.singletonList(hBaseColumn);
  }

  public List<HBaseColumn>
      extractDeleteColumns(HydraQLParser.Delete_column_def_listContext deleteColumnDefListContext) {
    if (deleteColumnDefListContext == null || deleteColumnDefListContext.isEmpty()) {
      return this.getTableSchema().findAllColumns();
    }
    List<HydraQLParser.Delete_column_defContext> columnDefContextList =
        deleteColumnDefListContext.delete_column_def();

    Set<HBaseColumn> deleteColumnSet = new HashSet<>();
    for (HydraQLParser.Delete_column_defContext columnDefContext : columnDefContextList) {
      List<HBaseColumn> columns = columnDefContext.accept(this);
      deleteColumnSet.addAll(columns);
    }
    ArrayList<HBaseColumn> hBaseColumns = new ArrayList<>(deleteColumnSet);
    if (hBaseColumns.size() == 1 && hBaseColumns.get(0).columnIsRow()) {
      throw new HBaseSqlAnalysisException(
          "Can't just delete rowKey " + hBaseColumns.get(0).getColumnName());
    }
    return new ArrayList<>(deleteColumnSet);
  }
}
