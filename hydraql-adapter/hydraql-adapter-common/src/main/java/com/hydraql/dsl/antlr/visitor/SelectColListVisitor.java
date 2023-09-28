package com.hydraql.dsl.antlr.visitor;

import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.util.StringUtil;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import com.hydraql.dsl.model.QueryHBaseColumn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leojie 2020/11/27 11:28 下午
 */
public class SelectColListVisitor extends BaseVisitor<List<QueryHBaseColumn>> {

    public SelectColListVisitor(HBaseTableSchema tableSchema) {
        super(tableSchema);
    }

    @Override
    public List<QueryHBaseColumn> visitSelectAllFamilyAndCol(HydraQLParser.SelectAllFamilyAndColContext ctx) {
        List<HBaseColumn> allColumns = this.getTableSchema().findAllColumns();
        return allColumns.stream().map(QueryHBaseColumn::column).collect(Collectors.toList());
    }

    @Override
    public List<QueryHBaseColumn> visitSelectOneFamilyAllCol(HydraQLParser.SelectOneFamilyAllColContext ctx) {
        HydraQLParser.NameContext nameContext = ctx.family_name().name();
        String family = getText(nameContext);
        if (StringUtil.isBlank(family)) {
            return new ArrayList<>();
        }
        List<HBaseColumn> allColumnsOfOneFamily = this.getTableSchema().findAllColumnsOfOneFamily(family);
        if (allColumnsOfOneFamily.isEmpty()) {
            throw new HBaseSqlAnalysisException(String.format("The column family [%s] is not defined.", family));
        }
        return allColumnsOfOneFamily.stream().map(QueryHBaseColumn::column).collect(Collectors.toList());
    }

    @Override
    public List<QueryHBaseColumn> visitSelectFamilyAndCol(HydraQLParser.SelectFamilyAndColContext ctx) {
        HydraQLParser.Family_nameContext familyNameContext = ctx.family_name();
        HydraQLParser.Column_nameContext columnNameContext = ctx.column_name();
        HBaseColumn hBaseColumn = this.extractColumn(familyNameContext, columnNameContext);
        HydraQLParser.Column_aliasContext columnAliasContext = ctx.column_alias();

        if (columnAliasContext != null && !columnAliasContext.isEmpty()) {
            return Collections.singletonList(QueryHBaseColumn.
                    column(hBaseColumn, getText(columnAliasContext.alias().name())));
        }
        return Collections.singletonList(QueryHBaseColumn.column(hBaseColumn));
    }

    @Override
    public List<QueryHBaseColumn> visitSelectWithFuncCall(HydraQLParser.SelectWithFuncCallContext ctx) {
        // todo 待实现筛选的列应用 function
        return super.visitSelectWithFuncCall(ctx);
    }


    public List<QueryHBaseColumn> extractSelectColumns(HydraQLParser.Select_column_defContext selectColumnDefContext) {
        return selectColumnDefContext.accept(this);
    }
}
