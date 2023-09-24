package com.hydraql.hql.filter;

import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.antlr.visitor.BaseVisitor;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.exceptions.DeserializationException;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author leojie 2023/7/26 20:44
 */
public class QueryFilterVisitor extends BaseVisitor<Filter> {
    private final Map<String, Object> queryParams;

    public QueryFilterVisitor(HBaseTableSchema tableSchema, Map<String, Object> queryParams) {
        super(tableSchema);
        this.queryParams = queryParams;
    }

    public Map<String, Object> getQueryParams() {
        if (this.queryParams == null || this.queryParams.isEmpty()) {
            throw new HBaseSqlAnalysisException("The parameter list cannot be empty.");
        }
        return queryParams;
    }

    @Override
    public Filter visitColConditionWrapper(HydraQLParser.ColConditionWrapperContext ctx) {
        return ctx.colCondition().accept(this);
    }

    @Override
    public Filter visitColConditionAnd(HydraQLParser.ColConditionAndContext ctx) {
        List<HydraQLParser.ColConditionContext> colConditionContextList = ctx.colCondition();
        if (colConditionContextList.isEmpty()) {
            return null;
        }
        List<Filter> filters = new ArrayList<>();
        for (HydraQLParser.ColConditionContext colConditionContext : colConditionContextList) {
            filters.add(colConditionContext.accept(this));
        }
        return new FilterList(FilterList.Operator.MUST_PASS_ALL, filters);
    }

    @Override
    public Filter visitColConditionOr(HydraQLParser.ColConditionOrContext ctx) {
        List<HydraQLParser.ColConditionContext> colConditionContextList = ctx.colCondition();
        if (colConditionContextList.isEmpty()) {
            return null;
        }
        List<Filter> filters = new ArrayList<>();
        for (HydraQLParser.ColConditionContext colConditionContext : colConditionContextList) {
            filters.add(colConditionContext.accept(this));
        }
        return new FilterList(FilterList.Operator.MUST_PASS_ONE, filters);
    }

    @Override
    public Filter visitColConditionCompOp(HydraQLParser.ColConditionCompOpContext ctx) {
        HBaseColumn column = this.extractColumn(ctx.column());
        final Object conditionVal = this.extractConditionVal(ctx.conditionVal(), column, this.getQueryParams(), false);
        if (ctx.comp_op().EQ() != null) {
            return constructFilter(column, CompareOperator.EQUAL, conditionVal);
        } else if (ctx.comp_op().GE() != null) {
            return constructFilter(column, CompareOperator.GREATER_OR_EQUAL, conditionVal);
        } else if (ctx.comp_op().GT() != null) {
            return constructFilter(column, CompareOperator.GREATER, conditionVal);
        } else if (ctx.comp_op().LT() != null) {
            return constructFilter(column, CompareOperator.LESS, conditionVal);
        } else if (ctx.comp_op().LE() != null) {
            return constructFilter(column, CompareOperator.LESS_OR_EQUAL, conditionVal);
        } else if (ctx.comp_op().NE() != null || ctx.comp_op().NE2() != null) {
            return constructFilter(column, CompareOperator.NOT_EQUAL, conditionVal);
        } else {
            throw new HBaseSqlAnalysisException("Unsupported operator " + ctx.comp_op().getText());
        }
    }

    @Override
    public Filter visitColConditionLikeOrNot(HydraQLParser.ColConditionLikeOrNotContext ctx) {
        HBaseColumn column = this.extractColumn(ctx.column());
        final Object conditionVal = this.extractConditionVal(ctx.conditionVal(), column, this.getQueryParams(), false);
        byte[] value = column.getColumnType().getTypeHandler()
                .toBytes(column.getColumnType().getTypeClass(), conditionVal);
        try {
            RegexStringComparator regexStringComparator = RegexStringComparator.parseFrom(value);
            return new SingleColumnValueFilter(column.getFamilyNameBytes(),
                    column.getColumnNameBytes(), CompareOperator.EQUAL, regexStringComparator);
        } catch (DeserializationException e) {
            throw new HBaseSqlAnalysisException(e);
        }
    }

    @Override
    public Filter visitColConditionIsNullOrNot(HydraQLParser.ColConditionIsNullOrNotContext ctx) {
        HBaseColumn column = this.extractColumn(ctx.column());
        if (ctx.NOT() != null) {
            return constructFilter(column, CompareOperator.NOT_EQUAL, new byte[0]);
        } else {
            return constructFilter(column, CompareOperator.EQUAL, new byte[0]);
        }
    }

    @Override
    public Filter visitColConditionInOrNotIn(HydraQLParser.ColConditionInOrNotInContext ctx) {
        HBaseColumn column = this.extractColumn(ctx.column());
        HydraQLParser.ConditionValListContext conditionValListContext = ctx.conditionValList();
        List<Object> constantValList = this.extractConstantValList(conditionValListContext, column,
                this.getQueryParams());
        if (ctx.NOT() != null) {
            return constructFilterForContain(column, CompareOperator.NOT_EQUAL,
                    constantValList, FilterList.Operator.MUST_PASS_ALL);
        }
        return constructFilterForContain(column, CompareOperator.EQUAL,
                constantValList, FilterList.Operator.MUST_PASS_ONE);
    }

    @Override
    public Filter visitColConditionBetweenOrNot(HydraQLParser.ColConditionBetweenOrNotContext ctx) {
        HBaseColumn column = this.extractColumn(ctx.column());
        List<HydraQLParser.ConditionValContext> conditionValContextList = ctx.conditionVal();
        Object start = this.extractConditionVal(conditionValContextList.get(0), column, this.getQueryParams(), false);
        Object end = this.extractConditionVal(conditionValContextList.get(1), column, this.getQueryParams(), false);

        if (ctx.NOT() != null) {
            Filter startFilter = constructFilter(column, CompareOperator.LESS, start);
            Filter endFilter = constructFilter(column, CompareOperator.GREATER, end);
            return new FilterList(FilterList.Operator.MUST_PASS_ONE, Arrays.asList(startFilter, endFilter));
        }

        Filter startFilter = constructFilter(column, CompareOperator.GREATER_OR_EQUAL, start);
        Filter endFilter = constructFilter(column, CompareOperator.LESS_OR_EQUAL, end);
        return new FilterList(FilterList.Operator.MUST_PASS_ALL, Arrays.asList(startFilter, endFilter));
    }

    private Filter constructFilter(HBaseColumn column, CompareOperator compareOp, Object val) {
        byte[] value = column.getColumnType().getTypeHandler().toBytes(column.getColumnType().getTypeClass(), val);
        return constructFilter(column, compareOp, value);
    }

    private Filter constructFilterForContain(HBaseColumn hbaseColumnSchema, CompareOperator compareOp,
                                             List<Object> valList, FilterList.Operator operator) {
        if (valList == null || valList.isEmpty()) {
            return null;
        }
        List<Filter> filters = new ArrayList<>(valList.size());
        for (Object val : valList) {
            Filter filter = constructFilter(hbaseColumnSchema, compareOp, val);
            if (filter != null) {
                filters.add(filter);
            }
        }
        return new FilterList(operator, filters);
    }

    private Filter constructFilter(HBaseColumn column, CompareOperator compareOp, byte[] value) {
        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter(column.getFamilyNameBytes(),
                column.getColumnNameBytes(), compareOp, value);
        singleColumnValueFilter.setFilterIfMissing(true);
        return singleColumnValueFilter;
    }

    public Filter extractFilter(HydraQLParser.WhereColContext whereColContext) {
        if (whereColContext == null) {
            return null;
        }
        return whereColContext.accept(this);
    }
}
