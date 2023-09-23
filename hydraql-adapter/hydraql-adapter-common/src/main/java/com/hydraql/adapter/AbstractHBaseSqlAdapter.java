package com.hydraql.adapter;

import com.hydraql.common.constants.HBaseConfigKeys;
import com.hydraql.common.constants.HMHBaseConstants;
import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.exception.HBaseSqlExecuteException;
import com.hydraql.common.exception.HBaseSqlTableSchemaMissingException;
import com.hydraql.common.lang.MyAssert;
import com.hydraql.common.model.HQLType;
import com.hydraql.common.model.row.HBaseDataRow;
import com.hydraql.common.model.row.HBaseDataSet;
import com.hydraql.common.util.StringUtil;
import com.hydraql.connection.HBaseConnectionUtil;
import com.hydraql.dsl.antlr.HBaseSQLErrorListener;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.antlr.data.InsertRowData;
import com.hydraql.dsl.antlr.parser.QueryExplainPlan;
import com.hydraql.dsl.antlr.visitor.CreateVirtualTableVisitor;
import com.hydraql.dsl.antlr.visitor.RowKeyRangeVisitor;
import com.hydraql.dsl.antlr.visitor.SelectColListVisitor;
import com.hydraql.dsl.antlr.visitor.TimeStampRangeVisitor;
import com.hydraql.dsl.antlr.visitor.UpsertValuesVisitor;
import com.hydraql.dsl.client.QueryExtInfo;
import com.hydraql.dsl.client.rowkey.RowKey;
import com.hydraql.dsl.context.HBaseSqlContext;
import com.hydraql.dsl.antlr.HBaseSQLStatementsLexer;
import com.hydraql.dsl.antlr.data.RowKeyRange;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import com.hydraql.dsl.model.QueryHBaseColumn;
import com.hydraql.dsl.model.TableQueryProperties;
import com.hydraql.dsl.util.Util;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.yetus.audience.InterfaceAudience;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author leojie 2020/11/28 8:34 下午
 */
@InterfaceAudience.Private
public abstract class AbstractHBaseSqlAdapter extends AbstractHBaseBaseAdapter implements IHBaseSqlAdapter {
    protected static final TableName HQL_META_DATA_TABLE_NAME = TableName.valueOf("HQL.META_DATA");
    protected static final byte[] HQL_META_DATA_TABLE_FAMILY = Bytes.toBytes( "f");
    protected static final byte[] HQL_META_DATA_TABLE_QUALIFIER = Bytes.toBytes( "schema");
    protected static final byte[] HQL_META_DATA_CREATE_HQL_QUALIFIER = Bytes.toBytes( "create_hql");

    public AbstractHBaseSqlAdapter(Configuration configuration) {
        super(configuration);
    }

    @Override
    public List<String> showVirtualTables(String hql) {
        HydraQLParser.Sql_commandContext sqlCommandContext = parseSqlCommandContext(hql);
        QueryExplainPlan explainPlan = new QueryExplainPlan(sqlCommandContext);
        HQLType hqlType = explainPlan.getSqlCommandParser().getHqlType();
        if (hqlType == HQLType.SHOW_VIRTUAL_TABLES) {
            List<String> allRegisteredVirtualTables = HBaseSqlContext.getInstance().getAllRegisteredVirtualTables();
            List<String> allVirtualTables = queryAllVirtualTables();
            Set<String> tableNames = new HashSet<>(allRegisteredVirtualTables);
            tableNames.addAll(allVirtualTables);
            return new ArrayList<>(tableNames);
        }
        return new ArrayList<>(0);
    }

    @Override
    public String showCreateVirtualTable(String hql) {
        HydraQLParser.Sql_commandContext sqlCommandContext = parseSqlCommandContext(hql);
        QueryExplainPlan explainPlan = new QueryExplainPlan(sqlCommandContext);
        String tableName = explainPlan.getSqlCommandParser().getTableName();
        HBaseTableSchema tableSchema = getTableSchema(tableName);
        String schema = tableSchema.toString();
        String createSql = tableSchema.getCreateSql();
        createSql = createSql.replaceAll("\n", "").replaceAll(",", ",\n");
        return createSql + "\n\n" + schema;
    }

    @Override
    public void createVirtualTable(String hql) {
        HydraQLParser.Sql_commandContext sqlCommandContext = parseSqlCommandContext(hql);
        QueryExplainPlan explainPlan = new QueryExplainPlan(sqlCommandContext);
        String tableName = explainPlan.getTableName();
        HydraQLParser.Create_virtual_table_commandContext virtualTableCommand
                = explainPlan.getDdlCommandContext().create_virtual_table_command();
        CreateVirtualTableVisitor createVirtualTableVisitor = new CreateVirtualTableVisitor(tableName,null);
        HBaseTableSchema tableSchema = createVirtualTableVisitor.extractHBaseTableSchema(virtualTableCommand);
        tableSchema.setCreateSql(hql);
        checkAndCreateHqlMetaTable();
        boolean res = saveTableSchemaMeta(tableSchema, hql);
        if (res) {
            registerTableSchema(tableSchema);
        }
    }

    @Override
    public void dropVirtualTable(String hql) {
        HydraQLParser.Sql_commandContext sqlCommandContext = parseSqlCommandContext(hql);
        QueryExplainPlan explainPlan = new QueryExplainPlan(sqlCommandContext);
        String virtualTableName = explainPlan.getTableName();

        Get get = new Get(Bytes.toBytes(virtualTableName));
        boolean virtualTableExists = this.execute(HQL_META_DATA_TABLE_NAME.getNameAsString(), table -> {
            Result result = table.get(get);
            if (result == null) {
                return false;
            }
            byte[] value = result.getValue(HQL_META_DATA_TABLE_FAMILY, HQL_META_DATA_TABLE_QUALIFIER);
            return value != null && StringUtil.isNotBlank(Bytes.toString(value));
        });
        if (!virtualTableExists) {
            throw new HBaseSqlAnalysisException(String.format("The virtual table %s does not exist.", virtualTableName));
        }
        Delete delete = new Delete(Bytes.toBytes(virtualTableName));
        boolean deleteRes = this.execute(HQL_META_DATA_TABLE_NAME.getNameAsString(), table -> {
            table.delete(delete);
            return true;
        });
        if (!deleteRes) {
            throw new HBaseSqlAnalysisException(String.format("The virtual table %s failed to be deleted.", virtualTableName));
        }
        removeTableSchema(virtualTableName);
    }

    protected abstract void checkAndCreateHqlMetaTable();

    protected abstract boolean saveTableSchemaMeta(HBaseTableSchema tableSchema, String hql);

    protected List<String> queryAllVirtualTables() {
        Scan scan = new Scan();
        return this.execute(HQL_META_DATA_TABLE_NAME.getNameAsString(), table -> {
            ResultScanner scanner = table.getScanner(scan);
            List<String> tables = new ArrayList<>();
            for (Result result : scanner) {
                tables.add(Bytes.toString(result.getRow()));
            }
            return tables;
        });
    }

    protected HBaseTableSchema getTableSchema(String tableName) {
        String uniqueKey = HBaseConnectionUtil.generateUniqueConnectionKey(this.getConfiguration());
        uniqueKey = uniqueKey + "#" + HMHBaseConstants.getFullTableName(tableName);
        HBaseTableSchema tableSchema = HBaseSqlContext.getInstance().getTableSchema(uniqueKey);
        if (tableSchema != null) {
            return tableSchema;
        }
        Get get = new Get(Bytes.toBytes(tableName));

        String[] tableSchemaMataData = this.execute(HQL_META_DATA_TABLE_NAME.getNameAsString(), table -> {
            Result result = table.get(get);
            if (result == null) {
                return null;
            }
            byte[] schemaValue = result.getValue(HQL_META_DATA_TABLE_FAMILY, HQL_META_DATA_TABLE_QUALIFIER);
            byte[] sqlValue = result.getValue(HQL_META_DATA_TABLE_FAMILY, HQL_META_DATA_CREATE_HQL_QUALIFIER);
            if (schemaValue == null || sqlValue == null) {
                return null;
            }
            return new String[] {Bytes.toString(schemaValue), Bytes.toString(sqlValue)};
        });
        if (tableSchemaMataData == null) {
            throw new HBaseSqlTableSchemaMissingException(
                    String.format("The table [%s] has no table schema, please create first.", tableName));
        }
        tableSchema = HBaseTableSchema.empty().build();
        tableSchema = tableSchema.convert(tableSchemaMataData[0]);
        tableSchema.setCreateSql(tableSchemaMataData[1]);
        this.registerTableSchema(tableSchema);
        return tableSchema;
    }

    @Override
    public HBaseDataSet select(String hql, Map<String, Object> params) {
        HydraQLParser.Sql_commandContext sqlCommandContext = parseSqlCommandContext(hql);
        QueryExplainPlan explainPlan = new QueryExplainPlan(sqlCommandContext);
        String tableName = explainPlan.getTableName();
        HBaseTableSchema tableSchema = this.getTableSchema(tableName);
        explainPlan.setTableSchema(tableSchema);
        List<QueryHBaseColumn> selectColumns = explainPlan.getSqlCommandParser().getSelectColumns();
        if (selectColumns == null || selectColumns.isEmpty()) {
            throw new HBaseSqlAnalysisException(String.format("The list of field names " +
                    "to be selected cannot be parsed from hql [%s]", hql));
        }

        HydraQLParser.Select_commandContext selectCommandContext =
                explainPlan.getDmlCommandContext().select_command();
        RowKeyRangeVisitor rowKeyRangeVisitor = new RowKeyRangeVisitor(tableSchema);

        RowKeyRange rowKeyRange = rowKeyRangeVisitor.extractRowKeyRange(selectCommandContext.select_statement().whereRow());
        QueryExtInfo queryExtInfo = rowKeyRangeVisitor.parseQueryExtInfo(selectCommandContext);
        // 解析字段或row key的filter条件
        Filter filter = this.parseFilter(selectCommandContext.select_statement().whereCol(), params, tableSchema);
        // = rowKey 即：get row

        if (rowKeyRange.isMatchGet()) {
            Get get = constructGet(rowKeyRange.getEqRow(), queryExtInfo, filter, selectColumns);
            return this.execute(tableName, table -> {
                Result result = table.get(get);
                if (result == null) {
                    return null;
                }
                List<HBaseDataRow> rowList = convertResultToDataRow(result, tableSchema, queryExtInfo);
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
                getArr[i] = constructGet(queryInRows.get(i), queryExtInfo, filter, selectColumns);
            }
            return this.execute(tableName, table -> {
                HBaseDataSet dataSet = HBaseDataSet.of(tableName);
                final Result[] results = table.get(Arrays.asList(getArr));
                if (results != null) {
                    for (Result result : results) {
                        List<HBaseDataRow> rowList = convertResultToDataRow(result, tableSchema, queryExtInfo);
                        dataSet.appendRows(rowList);
                    }
                }
                return dataSet;
            });
        }
        Scan scan = constructScan(tableName, rowKeyRange, queryExtInfo, filter, selectColumns);
        // 构造scan查询条件
       if (rowKeyRange.isMatchScanByRowPrefix()) {
            scan = setScanRowPrefixFilter(scan, rowKeyRange.getRowPrefix());
        }

        try {
            return queryToDataSet(tableSchema, queryExtInfo, scan);
        } catch (Exception e) {
            throw new HBaseSqlExecuteException("Select error. hql=" + hql, e);
        }
    }

    private HBaseDataSet queryToDataSet(HBaseTableSchema tableSchema, QueryExtInfo queryExtInfo, Scan scan) {
        String tableName = tableSchema.getTableName();
        return this.execute(tableName, table -> {
            int limit = Integer.MAX_VALUE;

            if (queryExtInfo.isLimitSet()) {
                limit = queryExtInfo.getLimit();
            }

            HBaseDataSet dataSet = HBaseDataSet.of(tableName);

            try (ResultScanner scanner = table.getScanner(scan)) {
                long resultCounter = 0L;
                Result result;
                while ((result = scanner.next()) != null) {
                    List<HBaseDataRow> rowList = convertResultToDataRow(result, tableSchema, queryExtInfo);
                    dataSet.appendRows(rowList);
                    if (++resultCounter >= limit) {
                        break;
                    }
                }
                return dataSet;
            }
        });
    }

    @Override
    public HBaseDataSet select(String hql) {
        return select(hql, new HashMap<>(0));
    }

    @Override
    public void insert(String hql) {
        HydraQLParser.Sql_commandContext sqlCommandContext = parseSqlCommandContext(hql);
        QueryExplainPlan explainPlan = new QueryExplainPlan(sqlCommandContext);
        String tableName = explainPlan.getTableName();
        HBaseTableSchema tableSchema = this.getTableSchema(tableName);
        explainPlan.setTableSchema(tableSchema);
        List<HBaseColumn> upsertColumns = explainPlan.getSqlCommandParser().getUpsertColumns();
        UpsertValuesVisitor upsertValuesVisitor = new UpsertValuesVisitor(tableSchema, upsertColumns);
        HydraQLParser.Upsert_values_commandContext upsertValuesCommandContext =
                explainPlan.getDmlCommandContext().upsert_values_command();
        List<InsertRowData> rowDataList = new ArrayList<>(upsertValuesCommandContext.insert_values().size());
        for (HydraQLParser.Insert_valuesContext insertValuesContext :
                upsertValuesCommandContext.insert_values()) {
            InsertRowData insertRowData = upsertValuesVisitor.extractInsertRowData(insertValuesContext);
            rowDataList.add(insertRowData);
        }

        if (rowDataList.size() == 1) {
            // todo timestamp处理
            Put put = this.constructPut(rowDataList.get(0), 0L);
            this.executeSave(tableName, put);
            return;
        }

        List<Mutation> puts = rowDataList.stream().map(rowData -> this.constructPut(rowData, 0L))
                .collect(Collectors.toList());
        this.executeSaveBatch(tableName, puts);
    }

    @Override
    public void delete(String hql) {
        HydraQLParser.Sql_commandContext sqlCommandContext = parseSqlCommandContext(hql);
        QueryExplainPlan explainPlan = new QueryExplainPlan(sqlCommandContext);
        String tableName = explainPlan.getTableName();
        HBaseTableSchema tableSchema = this.getTableSchema(tableName);
        explainPlan.setTableSchema(tableSchema);
        List<HBaseColumn> deleteColumns = explainPlan.getSqlCommandParser().getDeleteColumns();

        if (deleteColumns == null || deleteColumns.isEmpty()) {
            throw new HBaseSqlAnalysisException(String.format("The list of field names to be deleted cannot be parsed from hql [%s]", hql));
        }
        HydraQLParser.Delete_commandContext deleteCommandContext =
                explainPlan.getDmlCommandContext().delete_command();
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
        Filter filter = this.parseFilter(deleteCommandContext.whereCol(), tableSchema);
        long ts = 0;
        //todo delete with time range
        /*HydraQLParser.Timestamp_range_clauseContext timestampRangeClauseContext = deleteCommandContext.timestamp_range_clause();

        if (timestampRangeClauseContext != null && timestampRangeClauseContext.op!= null) {
            ts = rowKeyRangeVisitor.extractTimeStamp(deleteStatementContext.tsExp());
        }*/

        // delete eq row key
        if (eqRowKey != null) {
            deleteEqRowKey(tableName, eqRowKey, deleteColumns, ts);
            return;
        }
        // delete in row keys
        if (inRowKeyList != null && !inRowKeyList.isEmpty()) {
            deleteInRowKeys(tableName, inRowKeyList, deleteColumns, ts);
            return;
        }
        if (startRowKey != null && endRowKey != null) {
            Util.checkRowKey(startRowKey);
            Util.checkRowKey(endRowKey);
            deleteWithScanFirst(hql, tableName, rowKeyRange, filter, deleteColumns, ts);
        }
    }

    private void deleteEqRowKey(String tableName, RowKey<?> eqRowKey, List<HBaseColumn> deleteColumns, long ts) {
        if (eqRowKey == null || eqRowKey.toBytes() == null) {
            return;
        }
        Delete delete = constructDelete(eqRowKey, deleteColumns, ts);
        this.execute(tableName, table -> {
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
            deletes.add(constructDelete(rowKey, deleteColumns, ts));
        }
        this.executeDeleteBatch(tableName, deletes);
    }

    private void deleteWithScanFirst(String hql, String tableName, RowKeyRange rowKeyRange,
                                     Filter filter, List<HBaseColumn> deleteColumns, long ts) {

        final int deleteBatch = getDeleteBatch(tableName);
        while (true) {
            List<QueryHBaseColumn> queryHBaseColumns = deleteColumns.stream()
                    .map(QueryHBaseColumn::column).collect(Collectors.toList());
            Scan firstScan = constructScan(tableName, rowKeyRange, null, filter, queryHBaseColumns);
            // 只扫描row
            firstScan.addFamily(null);
            List<Mutation> deletes = new ArrayList<>(deleteBatch);
            try {
                this.execute(tableName, table -> {
                    try (ResultScanner scanner = table.getScanner(firstScan)) {
                        Result result;
                        while ((result = scanner.next()) != null) {
                            deletes.add(constructDelete(result, deleteColumns, ts));
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
                this.executeDeleteBatch(tableName, deletes);
                deletes.clear();
            } catch (Exception e) {
                throw new HBaseSqlExecuteException("Delete error. hql=" + hql, e);
            }

            if (deleteListSize < deleteBatch) {
                return;
            }

        }
    }

    protected int getScanCaching(String tableName) {
        return getTableQueryProperties(tableName).getScanCaching();
    }

    protected int getScanBatch(String tableName) {
        return getTableQueryProperties(tableName).getScanBatch();
    }

    protected int getDeleteBatch(String tableName) {
        return getTableQueryProperties(tableName).getDeleteBatch();
    }

    protected boolean scanCacheBlocks(String tableName) {
        return getTableQueryProperties(tableName).isScanCacheBlocks();
    }

    protected abstract Filter parseFilter(HydraQLParser.WhereColContext whereColContext, HBaseTableSchema tableSchema);
    protected abstract Filter parseFilter(HydraQLParser.WhereColContext whereColContext, Map<String, Object> queryParams,
                                          HBaseTableSchema tableSchema);

    protected abstract Get constructGet(RowKey<?> rowKey, QueryExtInfo queryExtInfo, Filter filter, List<QueryHBaseColumn> columnList);

    protected abstract Scan constructScan(String tableName, RowKeyRange rowKeyRange, QueryExtInfo queryExtInfo,
                                          Filter filter, List<QueryHBaseColumn> columnList);

    protected abstract Scan setScanRowPrefixFilter(Scan scan, RowKey<?> rowPrefixKey);

    protected abstract Put constructPut(InsertRowData rowData, long ts);

    protected abstract Delete constructDelete(RowKey<?> rowKey, List<HBaseColumn> columnSchemaList, long ts);

    protected abstract Delete constructDelete(Result result, List<HBaseColumn> columnSchemaList, long ts);

    private List<HBaseDataRow> convertResultToDataRow(Result result, HBaseTableSchema tableSchema, QueryExtInfo queryExtInfo) {
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
            for (HBaseColumn columnSchema : tableSchema.findAllColumns()) {
                if (columnSchema.columnIsRow()) {
                    continue;
                }
                List<Cell> cells = result.getColumnCells(columnSchema.getFamilyNameBytes(), columnSchema.getColumnNameBytes());
                if (cells.isEmpty()) {
                    dataRows.get(i).appendColumn(columnSchema.getFamily(), columnSchema.getColumnName(), columnSchema.getColumnType(),
                            null, 0);
                    continue;
                }
                if (i < cells.size()) {
                    Cell cell = cells.get(i);
                    Object value = columnSchema.getColumnType().getTypeHandler().
                            toObject(columnSchema.getColumnType().getTypeClass(), CellUtil.cloneValue(cell));
                    dataRows.get(i).appendColumn(columnSchema.getFamily(), columnSchema.getColumnName(), columnSchema.getColumnType(),
                            value, cell.getTimestamp());
                } else {
                    dataRows.get(i).appendColumn(columnSchema.getFamily(), columnSchema.getColumnName(), columnSchema.getColumnType(),
                            null, 0);
                }
            }
        }
        return dataRows;
    }

    private TableQueryProperties getTableQueryProperties(String tableName) {
        return this.getTableSchema(tableName).getTableQueryProperties();
    }

    protected HydraQLParser.Sql_commandContext parseSqlCommandContext(String hql) {
        if (StringUtil.isBlank(hql)) {
            throw new HBaseSqlAnalysisException("Please enter hql.");
        }
        try {
            ANTLRInputStream input = new ANTLRInputStream(new StringReader(hql));
            HBaseSQLStatementsLexer lexer = new HBaseSQLStatementsLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            HydraQLParser parser = new HydraQLParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new HBaseSQLErrorListener());
            HydraQLParser.RootContext root = parser.root();
            List<HydraQLParser.BatchContext> batchList = root.batch();
            if (batchList.size() > 1) {
                throw new HBaseSqlAnalysisException("The execution of multi-segment SQL is not currently supported.");
            }
            HydraQLParser.BatchContext batchContext = batchList.get(0);
            return batchContext.sql_command();
        } catch (Exception e) {
            throw new HBaseSqlAnalysisException(String.format("The hql %s was parsed failed.", hql), e);
        }
    }

    public String parseTableNameFromHql(String hql) {
        HydraQLParser.Sql_commandContext sqlCommandContext = parseSqlCommandContext(hql);
        return new QueryExplainPlan(sqlCommandContext).getTableName();
    }

    public HQLType parseHQLType(String hql) {
        HydraQLParser.Sql_commandContext sqlCommandContext = parseSqlCommandContext(hql);
        return new QueryExplainPlan(sqlCommandContext).getHqlType();
    }

    private void checkTableNameIsNotEmpty(String tableName) {
        MyAssert.checkArgument(StringUtil.isNotBlank(tableName), "The table name is not empty.");
    }

    @Override
    public void registerTableSchema(HBaseTableSchema tableSchema) {
        String uniqueKey = HBaseConnectionUtil.generateUniqueConnectionKey(this.getConfiguration());
        uniqueKey = uniqueKey + "#" + tableSchema.getTableName();
        int caching = this.getConfiguration().getInt(HBaseConfigKeys.HBASE_CLIENT_SCANNER_CACHING,
                HBaseConfigKeys.HBASE_CLIENT_DEFAULT_SCANNER_CACHING);
        TableQueryProperties tableQueryProperties = tableSchema.getTableQueryProperties();
        if (tableQueryProperties.getScanCaching() < 1) {
            tableQueryProperties.setScanCaching(caching);
        }
        tableSchema.setTableQuerySetting(tableQueryProperties);
        HBaseSqlContext.getInstance().registerTableSchema(uniqueKey, tableSchema);
    }

    private void removeTableSchema(String virtualTableName) {
        String uniqueKey = HBaseConnectionUtil.generateUniqueConnectionKey(this.getConfiguration());
        uniqueKey = uniqueKey + "#" + virtualTableName;
        HBaseSqlContext.getInstance().removeTableSchema(uniqueKey);
    }


    @Override
    public void printTableSchema(String tableName) {
        HBaseTableSchema tableSchema = this.getTableSchema(tableName);
        if (tableSchema == null) {
            return;
        }
        tableSchema.printSchema();
    }
}
