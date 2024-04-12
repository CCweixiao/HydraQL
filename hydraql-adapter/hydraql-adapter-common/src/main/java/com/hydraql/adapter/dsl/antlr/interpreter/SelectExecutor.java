package com.hydraql.adapter.dsl.antlr.interpreter;

import com.hydraql.adapter.AbstractHBaseSqlAdapter;
import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.exception.HBaseSqlExecuteException;
import com.hydraql.common.model.HQLType;
import com.hydraql.common.model.row.HBaseDataRow;
import com.hydraql.common.model.row.HBaseDataSet;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.adapter.dsl.antlr.data.RowKeyRange;
import com.hydraql.adapter.dsl.antlr.visitor.RowKeyRangeVisitor;
import com.hydraql.adapter.dsl.antlr.visitor.SelectColListVisitor;
import com.hydraql.adapter.dsl.antlr.visitor.TableNameVisitor;
import com.hydraql.dsl.client.QueryExtInfo;
import com.hydraql.dsl.client.rowkey.RowKey;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import com.hydraql.dsl.model.QueryHBaseColumn;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leojie 2023/9/27 19:22
 */
public class SelectExecutor extends BaseHqlExecutor<HBaseDataSet> implements Interpreter {
    private final AbstractHBaseSqlAdapter sqlAdapter;

    private SelectExecutor(SelectExecutor.ExecutorBuilder builder) {
        super(builder.hql);
        this.sqlAdapter = builder.sqlAdapter;
    }

    @Override
    public HBaseDataSet execute() {
        return this.execute(new HashMap<>(0));
    }

    public HBaseDataSet execute(Map<String, Object> queryParams) {
        HydraQLParser.Select_commandContext selectCommandContext =
                this.getSqlCommandContext().dml_command().select_command();
        HydraQLParser.Select_statementContext selectStatementContext = selectCommandContext.select_statement();
        String tableName = new TableNameVisitor().extractTableName(selectStatementContext.table_ref());
        HBaseTableSchema tableSchema = sqlAdapter.getTableSchema(tableName);
        List<QueryHBaseColumn> selectColumns = getSelectColumns(selectStatementContext, tableSchema);
        if (selectColumns.isEmpty()) {
            throw new HBaseSqlAnalysisException(String.format("The list of field names " +
                    "to be selected cannot be parsed from hql [%s]", getHql()));
        }

        RowKeyRangeVisitor rowKeyRangeVisitor = new RowKeyRangeVisitor(tableSchema);

        RowKeyRange rowKeyRange = rowKeyRangeVisitor.extractRowKeyRange(selectCommandContext.select_statement().whereRow());
        QueryExtInfo queryExtInfo = rowKeyRangeVisitor.parseQueryExtInfo(selectCommandContext);
        // 解析字段或row key的filter条件
        Filter filter = sqlAdapter.parseFilter(selectCommandContext.select_statement().whereCol(), queryParams, tableSchema);
        // = rowKey 即：get row

        if (rowKeyRange.isMatchGet()) {
            Get get = sqlAdapter.constructGet(rowKeyRange.getEqRow(), queryExtInfo, filter, selectColumns);
            return sqlAdapter.executeGetOrScan(tableName, table -> {
                Result result = table.get(get);
                if (result == null) {
                    return null;
                }
                List<HBaseDataRow> rowList = this.convertResultToDataRow(result, tableSchema, queryExtInfo, selectColumns);
                return HBaseDataSet.of(tableName).appendRows(rowList);
            });
        }

        // in row keys; get rows
        if (rowKeyRange.isMatchGetRows()) {
            List<RowKey<?>> queryInRows = rowKeyRange.getInSomeKeys();
            int limit;
            if (queryExtInfo.isLimitSet()) {
                limit = queryExtInfo.getLimit();
            } else {
                limit = queryInRows.size();
            }
            if (limit > queryInRows.size()) {
                limit = queryInRows.size();
            }
            Get[] getArr = new Get[limit];
            for (int i = 0; i < getArr.length; i++) {
                getArr[i] = sqlAdapter.constructGet(queryInRows.get(i), queryExtInfo, filter, selectColumns);
            }
            return sqlAdapter.executeGetOrScan(tableName, table -> {
                HBaseDataSet dataSet = HBaseDataSet.of(tableName);
                final Result[] results = table.get(Arrays.asList(getArr));
                if (results != null) {
                    for (Result result : results) {
                        List<HBaseDataRow> rowList = this.convertResultToDataRow(result, tableSchema, queryExtInfo, selectColumns);
                        dataSet.appendRows(rowList);
                    }
                }
                return dataSet;
            });
        }
        Scan scan = sqlAdapter.constructScan(tableName, rowKeyRange, queryExtInfo, filter, selectColumns);
        // 构造scan查询条件
        if (rowKeyRange.isMatchScanByRowPrefix()) {
            scan = sqlAdapter.setScanRowPrefixFilter(scan, rowKeyRange.getRowPrefix());
        }

        try {
            return queryToDataSet(tableSchema, queryExtInfo, selectColumns, scan);
        } catch (Exception e) {
            throw new HBaseSqlExecuteException("Select error. hql=" + getHql(), e);
        }
    }

    private HBaseDataSet queryToDataSet(HBaseTableSchema tableSchema, QueryExtInfo queryExtInfo,
                                        List<QueryHBaseColumn> selectColumns, Scan scan) {
        String tableName = tableSchema.getTableName();
        return sqlAdapter.executeGetOrScan(tableName, table -> {
            int limit = Integer.MAX_VALUE;

            if (queryExtInfo.isLimitSet()) {
                limit = queryExtInfo.getLimit();
            }

            HBaseDataSet dataSet = HBaseDataSet.of(tableName);

            try (ResultScanner scanner = table.getScanner(scan)) {
                long resultCounter = 0L;
                Result result;
                while ((result = scanner.next()) != null) {
                    List<HBaseDataRow> rowList = this.convertResultToDataRow(result, tableSchema, queryExtInfo, selectColumns);
                    dataSet.appendRows(rowList);
                    if (++resultCounter >= limit) {
                        break;
                    }
                }
                return dataSet;
            }
        });
    }

    private List<QueryHBaseColumn> getSelectColumns(HydraQLParser.Select_statementContext selectStatementContext,
                                                    HBaseTableSchema tableSchema) {
        List<HydraQLParser.Select_column_defContext> selectColumnDefContexts =
                selectStatementContext.select_column_def();
        SelectColListVisitor selectColListVisitor = new SelectColListVisitor(tableSchema);
        List<QueryHBaseColumn> queryHBaseColumns = new ArrayList<>();
        for (HydraQLParser.Select_column_defContext selectColumnDefContext : selectColumnDefContexts) {
            List<QueryHBaseColumn> columns =
                    selectColListVisitor.extractSelectColumns(selectColumnDefContext);
            queryHBaseColumns.addAll(columns);
        }
        return queryHBaseColumns;
    }

    private List<HBaseDataRow> convertResultToDataRow(Result result, HBaseTableSchema tableSchema,
                                                     QueryExtInfo queryExtInfo, List<QueryHBaseColumn> selectColumns) {
        int maxVersion = 1;
        if (queryExtInfo.isMaxVersionSet()) {
            maxVersion = queryExtInfo.getMaxVersions();
        }
        if (result == null || result.isEmpty()) {
            return new ArrayList<>(0);
        }
        List<HBaseDataRow> dataRows = new ArrayList<>(maxVersion);
        Object rowKey = tableSchema.findRow().convertBytesToVal(result.getRow());

        for (int i = 0; i < maxVersion; i++) {
            dataRows.add(HBaseDataRow.of(rowKey));
            for (QueryHBaseColumn queryColumn : selectColumns) {
                HBaseColumn columnSchema = queryColumn.getColumn();
                if (columnSchema.columnIsRow()) {
                    continue;
                }
                List<Cell> cells = result.getColumnCells(columnSchema.getFamilyNameBytes(), columnSchema.getColumnNameBytes());
                if (cells.isEmpty()) {
                    dataRows.get(i).appendColumn(columnSchema.getFamily(), columnSchema.getColumnName(),
                            queryColumn.getAlias(), columnSchema.getColumnType(), null, 0);
                    continue;
                }
                if (i < cells.size()) {
                    Cell cell = cells.get(i);
                    Object value = columnSchema.getColumnType().getTypeHandler().
                            toObject(columnSchema.getColumnType().getTypeClass(), CellUtil.cloneValue(cell));
                    dataRows.get(i).appendColumn(columnSchema.getFamily(), columnSchema.getColumnName(),
                            queryColumn.getAlias(), columnSchema.getColumnType(), value, cell.getTimestamp());
                } else {
                    dataRows.get(i).appendColumn(columnSchema.getFamily(), columnSchema.getColumnName(),
                            queryColumn.getAlias(), columnSchema.getColumnType(), null, 0);
                }
            }
        }
        return dataRows;
    }

    @Override
    public HQLType expectHqlType() {
        return HQLType.SELECT;
    }

    private static class ExecutorBuilder extends Builder<SelectExecutor, HBaseDataSet> {
        private final String hql;
        private final AbstractHBaseSqlAdapter sqlAdapter;
        private ExecutorBuilder(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
            this.hql = hql;
            this.sqlAdapter = sqlAdapter;
        }

        @Override
        public SelectExecutor build() {
            return new SelectExecutor(this);
        }
    }

    public static SelectExecutor of(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
        return new SelectExecutor.ExecutorBuilder(hql, sqlAdapter).build();
    }
}
