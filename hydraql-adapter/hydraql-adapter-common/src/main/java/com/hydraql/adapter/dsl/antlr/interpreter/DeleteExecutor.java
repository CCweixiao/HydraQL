package com.hydraql.adapter.dsl.antlr.interpreter;

import com.hydraql.adapter.HqlOpAdapter;
import com.hydraql.adapter.dsl.antlr.visitor.TableNameVisitor;
import com.hydraql.common.exception.HBaseSqlExecuteException;
import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.adapter.dsl.antlr.data.RowKeyRange;
import com.hydraql.adapter.dsl.antlr.visitor.DeleteColListVisitor;
import com.hydraql.adapter.dsl.antlr.visitor.RowKeyRangeVisitor;
import com.hydraql.dsl.client.rowkey.RowKey;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import com.hydraql.dsl.util.Util;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2023/9/27 19:22
 */
public class DeleteExecutor extends BaseHqlExecutor<Boolean> implements Interpreter {

    private final HqlOpAdapter sqlAdapter;

    private DeleteExecutor(DeleteExecutor.ExecutorBuilder builder) {
        super(builder.hql);
        this.sqlAdapter = builder.sqlAdapter;
    }

    @Override
    public Boolean execute() {
        HydraQLParser.Delete_commandContext deleteCommandContext =
                this.getSqlCommandContext().dml_command().delete_command();
        HydraQLParser.Delete_column_def_listContext deleteColumnDefListContext =
                deleteCommandContext.delete_column_def_list();
        String tableName = new TableNameVisitor().extractTableName(deleteCommandContext.table_ref());
        HBaseTableSchema tableSchema = sqlAdapter.getTableSchema(tableName);
        List<HBaseColumn> deleteColumns = new DeleteColListVisitor(tableSchema)
                .extractDeleteColumns(deleteColumnDefListContext);

        //Row in start row and end row
        RowKeyRangeVisitor rowKeyRangeVisitor = new RowKeyRangeVisitor(tableSchema);
        RowKeyRange rowKeyRange = rowKeyRangeVisitor.extractRowKeyRange(deleteCommandContext.whereRow());
        RowKey<?> startRowKey = rowKeyRange.getStart();
        RowKey<?> endRowKey = rowKeyRange.getStop();
        // delete one row key
        RowKey<?> eqRowKey = rowKeyRange.getEqRow();
        // delete in row key list
        List<RowKey<?>> inRowKeyList = rowKeyRange.getInSomeKeys();
        // filter
        Filter filter = sqlAdapter.parseFilter(deleteCommandContext.whereCol(), tableSchema);
        long ts = 0;
        //todo delete with time range
        /*HydraQLParser.Timestamp_range_clauseContext timestampRangeClauseContext = deleteCommandContext.timestamp_range_clause();

        if (timestampRangeClauseContext != null && timestampRangeClauseContext.op!= null) {
            ts = rowKeyRangeVisitor.extractTimeStamp(deleteStatementContext.tsExp());
        }*/

        // delete eq row key
        if (eqRowKey != null) {
            deleteEqRowKey(tableName, eqRowKey, deleteColumns, ts);
            return true;
        }
        // delete in row keys
        if (inRowKeyList != null && !inRowKeyList.isEmpty()) {
            deleteInRowKeys(tableName, inRowKeyList, deleteColumns, ts);
            return true;
        }
        if (startRowKey != null && endRowKey != null) {
            Util.checkRowKey(startRowKey);
            Util.checkRowKey(endRowKey);
            deleteWithScanFirst(this.getHql(), tableName, rowKeyRange, filter, deleteColumns, ts);
        }
        return true;
    }

    private void deleteEqRowKey(String tableName, RowKey<?> eqRowKey, List<HBaseColumn> deleteColumns, long ts) {
        if (eqRowKey == null || eqRowKey.toBytes() == null) {
            return;
        }
        Delete delete = sqlAdapter.constructDelete(eqRowKey, deleteColumns, ts);
        sqlAdapter.execute(tableName, table -> {
            table.delete(delete);
            return true;
        });
    }

    private void deleteInRowKeys(String tableName, List<RowKey<?>> rowKeys,
                                 List<HBaseColumn> deleteColumns, long ts) {
        List<Mutation> deletes = new ArrayList<>();
        for (RowKey<?> rowKey : rowKeys) {
            if (rowKey == null || rowKey.toBytes() == null) {
                continue;
            }
            deletes.add(sqlAdapter.constructDelete(rowKey, deleteColumns, ts));
        }
        sqlAdapter.execBatchDeletes(tableName, deletes);
    }

    private void deleteWithScanFirst(String hql, String tableName, RowKeyRange rowKeyRange,
                                     Filter filter, List<HBaseColumn> deleteColumns, long ts) {

        final int deleteBatch = sqlAdapter.getDeleteBatch(tableName);
        while (true) {
            Scan firstScan = sqlAdapter.constructScan(tableName, rowKeyRange, null, filter, null);
            firstScan.setFilter(new FirstKeyOnlyFilter());
            List<Mutation> deletes = new ArrayList<>(deleteBatch);
            try {
                sqlAdapter.execute(tableName, table -> {
                    try (ResultScanner scanner = table.getScanner(firstScan)) {
                        Result result;
                        while ((result = scanner.next()) != null) {
                            deletes.add(sqlAdapter.constructDelete(result, deleteColumns, ts));
                            if (deletes.size() >= deleteBatch) {
                                // 重置startKey
                                rowKeyRange.getStart().setValueBytes(result.getRow());
                                break;
                            }
                        }
                    }
                    return null;
                });
            } catch (Exception e) {
                throw new HBaseSqlExecuteException("Delete first scan error.", e);
            }

            final int deleteListSize = deletes.size();
            if (deleteListSize == 0) {
                return;
            }
            try {
                sqlAdapter.execBatchDeletes(tableName, deletes);
                deletes.clear();
            } catch (Exception e) {
                throw new HBaseSqlExecuteException("Delete error. hql=" + hql, e);
            }

            if (deleteListSize < deleteBatch) {
                return;
            }

        }
    }

    @Override
    public HQLType expectHqlType() {
        return HQLType.DELETE;
    }

    private static class ExecutorBuilder extends Builder<DeleteExecutor, Boolean> {
        private final String hql;
        private final HqlOpAdapter sqlAdapter;
        private ExecutorBuilder(String hql, HqlOpAdapter sqlAdapter) {
            this.hql = hql;
            this.sqlAdapter = sqlAdapter;
        }

        @Override
        public DeleteExecutor build() {
            return new DeleteExecutor(this);
        }
    }

    public static DeleteExecutor of(String hql, HqlOpAdapter sqlAdapter) {
        return new DeleteExecutor.ExecutorBuilder(hql, sqlAdapter).build();
    }
}
