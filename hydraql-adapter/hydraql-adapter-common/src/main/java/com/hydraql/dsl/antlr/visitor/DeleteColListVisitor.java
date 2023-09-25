package com.hydraql.dsl.antlr.visitor;

import com.hydraql.common.exception.HBaseSqlAnalysisException;
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
    public List<HBaseColumn> visitDeleteOneFamilyAllCol(HydraQLParser.DeleteOneFamilyAllColContext ctx) {
        HydraQLParser.Family_nameContext familyNameContext = ctx.family_name();
        String family = getText(familyNameContext.name());
        List<HBaseColumn> allColumnsOfOneFamily = this.getTableSchema().findAllColumnsOfOneFamily(family);
        if (allColumnsOfOneFamily.isEmpty()) {
            throw new HBaseSqlAnalysisException(String.format("The column family [%s] is not defined.", family));
        }
        return allColumnsOfOneFamily;
    }

    @Override
    public List<HBaseColumn> visitDeleteFamilyAndCol(HydraQLParser.DeleteFamilyAndColContext ctx) {
        String family = "";
        String column = "";
        HydraQLParser.Family_nameContext familyNameContext = ctx.family_name();
        HydraQLParser.Column_nameContext columnNameContext = ctx.column_name();
        if (familyNameContext != null && !familyNameContext.isEmpty()) {
            family = getText(familyNameContext.name());
        }
        if (columnNameContext != null && !columnNameContext.isEmpty()) {
            column = getText(columnNameContext.name());
        }
        HBaseColumn hBaseColumn = this.getTableSchema().findColumn(family, column);
        return Collections.singletonList(hBaseColumn);
    }

    public List<HBaseColumn> extractDeleteColumns(HydraQLParser.Delete_column_def_listContext
                                                          deleteColumnDefListContext) {
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
            throw new HBaseSqlAnalysisException("Can't just delete rowKey " + hBaseColumns.get(0).getColumnName());
        }
        return new ArrayList<>(deleteColumnSet);
    }
}
