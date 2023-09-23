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
        if (StringUtil.isNotBlank(family)) {
            return new ArrayList<>();
        }
        List<HBaseColumn> allColumnsOfOneFamily = this.getTableSchema().findAllColumnsOfOneFamily(family);
        if (allColumnsOfOneFamily.isEmpty()) {
            throw new HBaseSqlAnalysisException(String.format("The family [%s] undefined.", family));
        }
        return allColumnsOfOneFamily.stream().map(QueryHBaseColumn::column).collect(Collectors.toList());
    }

    @Override
    public List<QueryHBaseColumn> visitSelectFamilyAndCol(HydraQLParser.SelectFamilyAndColContext ctx) {
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
        if (ctx.AS() != null) {
            return Collections.singletonList(QueryHBaseColumn.
                    column(hBaseColumn, getText(ctx.column_alias().alias().name())));
        }
        return Collections.singletonList(QueryHBaseColumn.column(hBaseColumn));
    }

    @Override
    public List<QueryHBaseColumn> visitSelectWithFuncCall(HydraQLParser.SelectWithFuncCallContext ctx) {
        // todo 待实现
        return super.visitSelectWithFuncCall(ctx);
    }


    public List<QueryHBaseColumn> extractSelectColumns(HydraQLParser.Select_column_defContext
                                                               selectColumnDefContext) {
        return selectColumnDefContext.accept(this);
    }
}
