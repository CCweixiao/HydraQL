package com.hydraql.dsl.antlr.visitor;

import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.util.StringUtil;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.client.rowkey.RowKey;
import com.hydraql.dsl.antlr.data.RowKeyRange;
import com.hydraql.dsl.client.rowkey.RowKeyFactory;
import com.hydraql.dsl.model.HBaseTableSchema;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2020/11/28 11:00 上午
 */
public class RowKeyRangeVisitor extends BaseVisitor<RowKeyRange> {
    public RowKeyRangeVisitor(HBaseTableSchema tableSchema) {
        super(tableSchema);
    }

    @Override
    public RowKeyRange visitRowKeyStartAndEnd(HydraQLParser.RowKeyStartAndEndContext ctx) {
        RowKeyRange rowKeyRange = new RowKeyRange();
        List<HydraQLParser.RowKeyContext> rowKeyContextList = ctx.rowKey();
        RowKey<?> start = extractRowKey(rowKeyContextList.get(0));
        RowKey<?> stop = extractRowKey(rowKeyContextList.get(1));
        if (ctx.gtOper().GE() != null) {
            rowKeyRange.setIncludeStart(true);
        }
        if (ctx.leOper().LE() != null) {
            rowKeyRange.setIncludeStop(true);
        }
        rowKeyRange.setStart(start);
        rowKeyRange.setStop(stop);
        rowKeyRange.setMatchScanByStartAndEnd(true);
        return rowKeyRange;
    }

    private RowKey<?> extractRowKey(HydraQLParser.RowKeyContext rowKeyContext) {
        // todo 类型
        String row = rowKeyContext.string().STRING_LITERAL().getText();
        if (StringUtil.isBlank(row)) {
            throw new HBaseSqlAnalysisException("The value of rowKey could not be resolved.");
        }
        return RowKeyFactory.getRowKeyByTableSchema(row, this.getTableSchema());
    }

    @Override
    public RowKeyRange visitRowKeyStart(HydraQLParser.RowKeyStartContext ctx) {
        RowKeyRange rowKeyRange = new RowKeyRange();
        HydraQLParser.RowKeyContext rowKeyContext = ctx.rowKey();
        RowKey<?> start = extractRowKey(rowKeyContext);
        rowKeyRange.setStart(start);
        if (ctx.gtOper().GE() != null) {
            rowKeyRange.setIncludeStart(true);
        }
        rowKeyRange.setMatchScanByStart(true);
        return rowKeyRange;
    }

    @Override
    public RowKeyRange visitRowKeyEnd(HydraQLParser.RowKeyEndContext ctx) {
        RowKeyRange rowKeyRange = new RowKeyRange();
        HydraQLParser.RowKeyContext rowKeyContext = ctx.rowKey();
        RowKey<?> stop = extractRowKey(rowKeyContext);
        if (ctx.leOper().LE() != null) {
            rowKeyRange.setIncludeStop(true);
        }
        rowKeyRange.setStop(stop);
        return rowKeyRange;
    }


    @Override
    public RowKeyRange visitRowKeyEqOne(HydraQLParser.RowKeyEqOneContext ctx) {
        RowKeyRange rowKeyRange = new RowKeyRange();
        HydraQLParser.RowKeyContext rowKeyContext = ctx.rowKey();
        RowKey<?> rowKey = extractRowKey(rowKeyContext);
        rowKeyRange.setEqRow(rowKey);
        rowKeyRange.setMatchGet(true);
        return rowKeyRange;
    }

    @Override
    public RowKeyRange visitRowKeyInSomeKeys(HydraQLParser.RowKeyInSomeKeysContext ctx) {
        RowKeyRange rowKeyRange = new RowKeyRange();
        List<HydraQLParser.RowKeyContext> rowKeyContexts = ctx.rowKey();
        List<RowKey<?>> rowKeyList = new ArrayList<>(rowKeyContexts.size());
        for (HydraQLParser.RowKeyContext rowKeyContext : rowKeyContexts) {
            rowKeyList.add(extractRowKey(rowKeyContext));
        }
        rowKeyRange.setInSomeKeys(rowKeyList);
        rowKeyRange.setMatchGetRows(true);
        return rowKeyRange;
    }

    @Override
    public RowKeyRange visitRowKeyLike(HydraQLParser.RowKeyLikeContext ctx) {
        RowKeyRange rowKeyRange = new RowKeyRange();
        HydraQLParser.RowKeyContext rowKeyContext = ctx.rowKey();
        RowKey<?> rowKey = extractRowKey(rowKeyContext);
        rowKeyRange.setRowPrefix(rowKey);
        rowKeyRange.setMatchScanByRowPrefix(true);
        return rowKeyRange;
    }

    public RowKeyRange extractRowKeyRange(HydraQLParser.WhereRowContext whereRowContext) {
        return whereRowContext.accept(this);
    }
}
