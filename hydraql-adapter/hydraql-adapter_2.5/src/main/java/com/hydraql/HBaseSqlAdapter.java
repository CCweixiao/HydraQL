package com.hydraql;

import com.hydraql.adapter.AbstractHBaseSqlAdapter;
import com.hydraql.common.constants.HMHBaseConstants;
import com.hydraql.common.exception.HBaseOperationsException;
import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.util.StringUtil;
import com.hydraql.dsl.antlr.data.InsertColData;
import com.hydraql.dsl.antlr.data.InsertRowData;
import com.hydraql.dsl.antlr.data.RowKeyRange;
import com.hydraql.hql.filter.QueryFilterVisitor;
import com.hydraql.dsl.antlr.HBaseSQLParser;
import com.hydraql.dsl.client.QueryExtInfo;
import com.hydraql.dsl.client.rowkey.RowKey;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import com.hydraql.dsl.util.Util;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.yetus.audience.InterfaceAudience;
import java.io.IOException;
import java.util.*;

/**
 * @author leojie 2020/11/28 8:36 下午
 */
@InterfaceAudience.Private
public class HBaseSqlAdapter extends AbstractHBaseSqlAdapter {

    public HBaseSqlAdapter(Configuration configuration) {
        super(configuration);
    }

    @Override
    protected void checkAndCreateHqlMetaTable() {
        this.execute(admin -> {
            if (admin.tableExists(HQL_META_DATA_TABLE_NAME)) {
                return true;
            }
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(HQL_META_DATA_TABLE_NAME);
            ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.
                    of(HQL_META_DATA_TABLE_FAMILY);
            tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
            admin.createTable(tableDescriptorBuilder.build());
            return true;
        });
    }

    @Override
    protected boolean saveTableSchemaMeta(HBaseTableSchema tableSchema, String hql) {
        String tableSchemaJson = tableSchema.toJson();
        String tableName = HMHBaseConstants.getFullTableName(tableSchema.getTableName());
        Get get = new Get(Bytes.toBytes(tableName));
        String res = this.execute(HQL_META_DATA_TABLE_NAME.getNameAsString(), table -> {
            Result result = table.get(get);
            if (result == null) {
                return "";
            }
            return Bytes.toString(result.getRow());
        });
        if (StringUtil.isNotBlank(res)) {
            throw new HBaseSqlAnalysisException(String.format("The schema of table %s has been created.", tableName));
        }
        Put put = new Put(Bytes.toBytes(tableName));
        put.addColumn(HQL_META_DATA_TABLE_FAMILY, HQL_META_DATA_TABLE_QUALIFIER, Bytes.toBytes(tableSchemaJson));
        put.addColumn(HQL_META_DATA_TABLE_FAMILY, HQL_META_DATA_CREATE_HQL_QUALIFIER, Bytes.toBytes(hql));
        this.executeSave(HQL_META_DATA_TABLE_NAME.getNameAsString(), put);
        return true;
    }

    @Override
    protected Filter parseFilter(HBaseSQLParser.WherecContext whereContext, HBaseTableSchema tableSchema) {
        if (whereContext == null) {
            return null;
        }
        if (whereContext.conditionc() == null) {
            return null;
        }
        QueryFilterVisitor filterVisitor = new QueryFilterVisitor(tableSchema, new HashMap<>(0));
        return whereContext.conditionc().accept(filterVisitor);
    }

    @Override
    protected Filter parseFilter(HBaseSQLParser.WherecContext whereContext, Map<String, Object> queryParams, HBaseTableSchema tableSchema) {
        if (whereContext == null) {
            return null;
        }
        if (whereContext.conditionc() == null) {
            return null;
        }
        QueryFilterVisitor filterVisitor = new QueryFilterVisitor(tableSchema, queryParams);
        return whereContext.conditionc().accept(filterVisitor);
    }

    @Override
    protected Get constructGet(RowKey<?> rowKey, QueryExtInfo queryExtInfo, Filter filter, List<HBaseColumn> columnList) {
        Util.checkRowKey(rowKey);
        Get get = new Get(rowKey.toBytes());
        if (queryExtInfo != null) {
            if (queryExtInfo.isMaxVersionSet()) {
                try {
                    get.readVersions(queryExtInfo.getMaxVersions());
                } catch (IOException e) {
                    throw new HBaseOperationsException("should never happen.", e);
                }
            }
            if (queryExtInfo.isTimeRangeSet()) {
                try {
                    get.setTimeRange(queryExtInfo.getMinStamp(), queryExtInfo.getMaxStamp());
                } catch (IOException e) {
                    throw new HBaseOperationsException("should never happen.", e);
                }
            }
        }
        if (filter != null) {
            get.setFilter(filter);
        }
        if (columnList != null && !columnList.isEmpty()) {
            for (HBaseColumn column : columnList) {
                if (column.columnIsRow()) {
                    continue;
                }
                get.addColumn(column.getFamilyNameBytes(), column.getColumnNameBytes());
            }
        }
        return get;
    }

    @Override
    protected Scan constructScan(String tableName, RowKeyRange rowKeyRange, QueryExtInfo queryExtInfo, Filter filter, List<HBaseColumn> columnList) {
        Scan scan = new Scan();
        RowKey<?> startRowKey = rowKeyRange.getStart();
        if (startRowKey != null && startRowKey.toBytes() != null) {
            scan.withStartRow(startRowKey.toBytes(), rowKeyRange.isIncludeStart());
        }
        RowKey<?> endRowKey = rowKeyRange.getStop();
        if (endRowKey != null && endRowKey.toBytes() != null) {
            scan.withStopRow(endRowKey.toBytes(), rowKeyRange.isIncludeStop());
        }
        if (queryExtInfo.isMaxVersionSet()) {
            scan.readVersions(queryExtInfo.getMaxVersions());
        }
        if (queryExtInfo.isTimeRangeSet()) {
            try {
                scan.setTimeRange(queryExtInfo.getMinStamp(), queryExtInfo.getMaxStamp());
            } catch (IOException e) {
                throw new IllegalArgumentException("Shouldn't happen.", e);
            }
        }
        if (queryExtInfo.isLimitSet()) {
            scan.setLimit(queryExtInfo.getLimit());
        }
        scan.setCaching(getScanCaching(tableName));
        scan.setCacheBlocks(scanCacheBlocks(tableName));
        if (filter != null) {
            scan.setFilter(filter);
        } else {
            scan.setBatch(getScanBatch(tableName));
        }
        if (columnList != null && !columnList.isEmpty()) {
            for (HBaseColumn column : columnList) {
                if (column.columnIsRow()) {
                    continue;
                }
                scan.addColumn(column.getFamilyNameBytes(), column.getColumnNameBytes());
            }
        }
        return scan;
    }

    @Override
    protected Scan setScanRowPrefixFilter(Scan scan, RowKey<?> rowPrefixKey) {
        scan.setRowPrefixFilter(rowPrefixKey.toBytes());
        return scan;
    }

    @Override
    protected Put constructPut(InsertRowData rowData, long ts) {
        Put put = new Put(rowData.getRows());
        for (InsertColData colData : rowData.getColDataList()) {
            if (ts > 0) {
                put.addColumn(colData.getFamily(), colData.getQualifier(), ts, colData.getValue());
            } else {
                put.addColumn(colData.getFamily(), colData.getQualifier(), colData.getValue());
            }
        }
        return put;
    }

    @Override
    protected Delete constructDelete(RowKey<?> rowKey, List<HBaseColumn> columnSchemaList, long ts) {
        return constructDelete(rowKey.toBytes(), columnSchemaList, ts);
    }

    @Override
    protected Delete constructDelete(Result result, List<HBaseColumn> columnSchemaList, long ts) {
        return constructDelete(result.getRow(), columnSchemaList, ts);
    }

    private Delete constructDelete(byte[] row, List<HBaseColumn> columnSchemaList, long ts) {
        Delete delete = new Delete(row);
        if (columnSchemaList == null || columnSchemaList.isEmpty()) {
            return delete;
        }
        for (HBaseColumn hBaseColumnSchema : columnSchemaList) {
            if (hBaseColumnSchema.columnIsRow()) {
                continue;
            }
            byte[] familyBytes = hBaseColumnSchema.getFamilyNameBytes();
            byte[] qualifierBytes = hBaseColumnSchema.getColumnNameBytes();
            if (ts < 1) {
                delete.addColumn(familyBytes, qualifierBytes);
            } else {
                delete.addColumn(familyBytes, qualifierBytes, ts);
            }
        }
        return delete;
    }
}