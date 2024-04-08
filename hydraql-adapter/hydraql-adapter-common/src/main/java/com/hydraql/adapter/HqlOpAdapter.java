package com.hydraql.adapter;

import com.hydraql.adapter.executor.HTableMutatorExecutor;
import com.hydraql.common.constants.HBaseConfigKeys;
import com.hydraql.common.constants.HMHBaseConstants;
import com.hydraql.common.exception.HBaseSqlTableSchemaMissingException;
import com.hydraql.common.model.row.HBaseDataSet;
import com.hydraql.adapter.connection.HBaseConnectionUtil;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.adapter.dsl.antlr.data.InsertRowData;
import com.hydraql.adapter.dsl.antlr.data.RowKeyRange;
import com.hydraql.adapter.dsl.antlr.interpreter.CreateVirtualTableExecutor;
import com.hydraql.adapter.dsl.antlr.interpreter.DeleteExecutor;
import com.hydraql.adapter.dsl.antlr.interpreter.DropVirtualTableExecutor;
import com.hydraql.adapter.dsl.antlr.interpreter.SelectExecutor;
import com.hydraql.adapter.dsl.antlr.interpreter.ShowCreateVirtualTableExecutor;
import com.hydraql.adapter.dsl.antlr.interpreter.ShowVirtualTablesExecutor;
import com.hydraql.adapter.dsl.antlr.interpreter.UpsetExecutor;
import com.hydraql.dsl.client.QueryExtInfo;
import com.hydraql.dsl.client.rowkey.RowKey;
import com.hydraql.dsl.context.HBaseSqlContext;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import com.hydraql.dsl.model.QueryHBaseColumn;
import com.hydraql.dsl.model.TableQueryProperties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.yetus.audience.InterfaceAudience;

import java.util.List;
import java.util.Map;

/**
 * @author leojie 2020/11/28 8:34 下午
 */
@InterfaceAudience.Private
public abstract class HqlOpAdapter implements HTableMutatorExecutor, IHBaseSqlAdapter {
    public static final TableName HQL_META_DATA_TABLE_NAME = TableName.valueOf("HQL.META_DATA");
    public static final byte[] HQL_META_DATA_TABLE_FAMILY = Bytes.toBytes( "f");
    public static final byte[] HQL_META_DATA_TABLE_QUALIFIER = Bytes.toBytes( "schema");
    public static final byte[] HQL_META_DATA_CREATE_HQL_QUALIFIER = Bytes.toBytes( "create_hql");

    private final Configuration configuration;

    public HqlOpAdapter(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public List<String> showVirtualTables(String hql) {
        return ShowVirtualTablesExecutor.of(hql, this).execute();
    }

    @Override
    public String showCreateVirtualTable(String hql) {
        return ShowCreateVirtualTableExecutor.of(hql, this).execute();
    }

    @Override
    public void createVirtualTable(String hql) {
        CreateVirtualTableExecutor.of(hql, this).execute();
    }

    @Override
    public void dropVirtualTable(String hql) {
        DropVirtualTableExecutor.of(hql, this).execute();
    }

    public abstract void checkAndCreateHqlMetaTable();

    public abstract boolean saveTableSchemaMeta(HBaseTableSchema tableSchema, String hql, boolean ifNotExists);

    public HBaseTableSchema getTableSchema(String tableName) {
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
        return SelectExecutor.of(hql, this).execute(params);
    }

    @Override
    public HBaseDataSet select(String hql) {
        return SelectExecutor.of(hql, this).execute();
    }

    @Override
    public void insert(String hql) {
        UpsetExecutor.of(hql, this).execute();
    }

    @Override
    public void delete(String hql) {
        DeleteExecutor.of(hql, this).execute();
    }

    public int getScanCaching(String tableName) {
        return getTableQueryProperties(tableName).getScanCaching();
    }

    public int getScanBatch(String tableName) {
        return getTableQueryProperties(tableName).getScanBatch();
    }

    public int getDeleteBatch(String tableName) {
        return getTableQueryProperties(tableName).getDeleteBatch();
    }

    public boolean scanCacheBlocks(String tableName) {
        return getTableQueryProperties(tableName).isScanCacheBlocks();
    }

    public abstract Filter parseFilter(HydraQLParser.WhereColContext whereColContext, HBaseTableSchema tableSchema);
    public abstract Filter parseFilter(HydraQLParser.WhereColContext whereColContext, Map<String, Object> queryParams,
                                          HBaseTableSchema tableSchema);

    public abstract Get constructGet(RowKey<?> rowKey, QueryExtInfo queryExtInfo, Filter filter, List<QueryHBaseColumn> columnList);

    public abstract Scan constructScan(String tableName, RowKeyRange rowKeyRange, QueryExtInfo queryExtInfo,
                                          Filter filter, List<QueryHBaseColumn> columnList);

    public abstract Scan setScanRowPrefixFilter(Scan scan, RowKey<?> rowPrefixKey);

    public abstract Put constructPut(InsertRowData rowData, long ts);

    public abstract Delete constructDelete(RowKey<?> rowKey, List<HBaseColumn> columnSchemaList, long ts);

    public abstract Delete constructDelete(Result result, List<HBaseColumn> columnSchemaList, long ts);

    private TableQueryProperties getTableQueryProperties(String tableName) {
        return this.getTableSchema(tableName).getTableQueryProperties();
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
