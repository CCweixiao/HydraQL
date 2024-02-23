package com.hydraql.manager.plugins;

import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.query.GetRowParam;
import com.hydraql.connection.HBaseConnectionManager;
import com.hydraql.manager.core.conf.HydraqlHBaseConfiguration;
import com.hydraql.manager.core.hbase.schema.ColumnFamilyDesc;
import com.hydraql.manager.core.hbase.schema.HTableDesc;
import com.hydraql.manager.core.hbase.schema.NamespaceDesc;
import com.hydraql.manager.core.template.HydraqlTemplate;
import com.hydraql.schema.BaseColumnFamilyDesc;
import com.hydraql.schema.BaseHTableDesc;
import com.hydraql.template.HBaseAdminTemplate;
import com.hydraql.template.HBaseTableTemplate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leojie 2024/1/25 16:20
 */
public class HydraqlTemplateImpl implements HydraqlTemplate {

    private final HBaseAdminTemplate adminTemplate;
    private final HBaseTableTemplate tableTemplate;

    public static HydraqlTemplateImpl createInstance(HydraqlHBaseConfiguration conf) {
        return new HydraqlTemplateImpl(conf);
    }

    public HydraqlTemplateImpl(HydraqlHBaseConfiguration conf) {
        Configuration hbaseConf = new Configuration();
        conf.toMap().forEach((k, v) -> hbaseConf.set(k, v.toString()));
        Connection connection = HBaseConnectionManager.getInstance().getConnection(hbaseConf);
        adminTemplate = HBaseAdminTemplate.of(connection);
        tableTemplate = HBaseTableTemplate.of(connection);
    }

    @Override
    public boolean tableExists(String tableName) {
        return adminTemplate.tableExists(tableName);
    }

    @Override
    public boolean createNamespace(NamespaceDesc namespaceDesc) {
        return adminTemplate.createNamespace(convertTo(namespaceDesc), false);
    }

    @Override
    public boolean namespaceIsExists(String namespaceName) {
        return adminTemplate.namespaceIsExists(namespaceName);
    }

    @Override
    public boolean deleteNamespace(String namespaceName) {
        return adminTemplate.deleteNamespace(namespaceName, false);
    }

    @Override
    public List<NamespaceDesc> listNamespaceDesc() {
        List<com.hydraql.common.model.NamespaceDesc> namespaceDescList = adminTemplate.listNamespaceDesc();
        return namespaceDescList.stream().map(this::convertFrom).collect(Collectors.toList());
    }

    @Override
    public List<String> listNamespaceNames() {
        return adminTemplate.listNamespaceNames();
    }

    @Override
    public boolean createTable(HTableDesc tableDesc) {
        return adminTemplate.createTable(convertTo(tableDesc));
    }

    @Override
    public List<HTableDesc> listTableDesc(boolean includeSysTables) {
        List<com.hydraql.schema.HTableDesc> tableDescList = adminTemplate.listTableDesc(includeSysTables);
        if (tableDescList.isEmpty()) {
            return new ArrayList<>(0);
        }
        return tableDescList.stream().map(this::convertFrom).collect(Collectors.toList());
    }

    @Override
    public List<String> listTableNames() {
        return adminTemplate.listTableNames();
    }

    @Override
    public com.hydraql.manager.core.model.HBaseRowData getRow(String tableName, String rowKey) {
        HBaseRowData rowData = tableTemplate.getRow(tableName, GetRowParam.of(rowKey).build());
        com.hydraql.manager.core.model.HBaseRowData data = new com.hydraql.manager.core.model.HBaseRowData(rowKey);
        rowData.getColDataContainer().forEach((k, v) -> {
            data.setData(k, v.getValue());
        });
        return data;
    }

    private com.hydraql.common.model.NamespaceDesc convertTo(NamespaceDesc ns) {
        com.hydraql.common.model.NamespaceDesc namespaceDesc = new com.hydraql.common.model.NamespaceDesc();
        namespaceDesc.setNamespaceName(ns.getNamespaceName());
        namespaceDesc.setNamespaceProps(ns.getNamespaceProps());
        return namespaceDesc;
    }

    private com.hydraql.schema.HTableDesc convertTo(HTableDesc hd) {
        List<com.hydraql.schema.BaseColumnFamilyDesc> cfList =
                new ArrayList<>(hd.getColumnFamilyDescList().size());
        for (ColumnFamilyDesc cf : hd.getColumnFamilyDescList()) {
            cfList.add(convertTo(cf));
        }

        BaseHTableDesc.Builder<com.hydraql.schema.HTableDesc> builder =
                com.hydraql.schema.HTableDesc.newBuilder()
                .name(hd.getName())
                .maxFileSize(hd.getMaxFileSize())
                .readOnly(hd.isReadOnly())
                .memStoreFlushSize(hd.getMemStoreFlushSize())
                .compactionEnabled(hd.isCompactionEnabled())
                .regionSplitPolicyClassName(hd.getRegionSplitPolicyClassName())
                .columnFamilyDescList(cfList);
        if (!hd.getConfiguration().isEmpty()) {
            hd.getConfiguration().forEach(builder::setConfiguration);
        }
        if (!hd.getValues().isEmpty()) {
            hd.getValues().forEach(builder::setValue);
        }
        return builder.build();
    }

    private com.hydraql.schema.BaseColumnFamilyDesc convertTo(ColumnFamilyDesc cf) {
        return com.hydraql.schema.ColumnFamilyDesc.newBuilder()
                .name(cf.getName())
                .replicationScope(cf.getReplicationScope())
                .maxVersions(cf.getMaxVersions())
                .minVersions(cf.getMinVersions())
                .compressionType(cf.getCompressionType())
                .storagePolicy(cf.getStoragePolicy())
                .bloomFilterType(cf.getBloomFilterType())
                .timeToLive(cf.getTimeToLive())
                .blockSize(cf.getBlockSize())
                .blockCacheEnabled(cf.isBlockCacheEnabled())
                .inMemory(cf.isInMemory())
                .keepDeletedCells(cf.getKeepDeletedCells())
                .dataBlockEncoding(cf.getDataBlockEncoding())
                .cacheDataOnWrite(cf.isCacheDataOnWrite())
                .cacheDataInL1(cf.isCacheDataInL1())
                .cacheIndexesOnWrite(cf.isCacheIndexesOnWrite())
                .cacheBloomsOnWrite(cf.isCacheBloomsOnWrite())
                .evictBlocksOnClose(cf.isEvictBlocksOnClose())
                .prefetchBlocksOnOpen(cf.isPrefetchBlocksOnOpen())
                .setConfiguration(cf.getConfiguration())
                .setValue(cf.getValues())
                .build();
    }

    private NamespaceDesc convertFrom(com.hydraql.common.model.NamespaceDesc ns) {
        NamespaceDesc namespaceDesc = new NamespaceDesc();
        namespaceDesc.setNamespaceName(ns.getNamespaceName());
        namespaceDesc.setNamespaceProps(ns.getNamespaceProps());
        return namespaceDesc;
    }

    private HTableDesc convertFrom(com.hydraql.schema.HTableDesc hd) {
        return HTableDesc.of(hd.getTableNameWithNamespace())
                .maxFileSize(hd.getMaxFileSize())
                .readOnly(hd.isReadOnly())
                .memStoreFlushSize(hd.getMemStoreFlushSize())
                .compactionEnabled(hd.isCompactionEnabled())
                .regionSplitPolicyClassName(hd.getRegionSplitPolicyClassName())
                .setConfiguration(hd.getConfiguration())
                .setValue(hd.getValues())
                .addFamilyDesc(convertFrom(hd.getColumnFamilyDescList()))
                .build();
    }

    private List<ColumnFamilyDesc> convertFrom(List<com.hydraql.schema.BaseColumnFamilyDesc> cfList) {
        List<ColumnFamilyDesc> columnFamilyDescList = new ArrayList<>(cfList.size());
        for (BaseColumnFamilyDesc cf : cfList) {
            columnFamilyDescList.add(convertFrom(cf));
        }
        return columnFamilyDescList;
    }

    private ColumnFamilyDesc convertFrom(com.hydraql.schema.BaseColumnFamilyDesc cf) {
        return ColumnFamilyDesc.of(cf.getNameAsString())
                .replicationScope(cf.getReplicationScope())
                .maxVersions(cf.getMaxVersions())
                .minVersions(cf.getMinVersions())
                .compressionType(cf.getCompressionType())
                .storagePolicy(cf.getStoragePolicy())
                .bloomFilterType(cf.getBloomFilterType())
                .timeToLive(cf.getTimeToLive())
                .blockSize(cf.getBlockSize())
                .blockCacheEnabled(cf.isBlockCacheEnabled())
                .inMemory(cf.isInMemory())
                .keepDeletedCells(cf.getKeepDeletedCells())
                .dataBlockEncoding(cf.getDataBlockEncoding())
                .cacheDataOnWrite(cf.isCacheDataOnWrite())
                .cacheDataInL1(cf.isCacheDataInL1())
                .cacheIndexesOnWrite(cf.isCacheIndexesOnWrite())
                .cacheBloomsOnWrite(cf.isCacheBloomsOnWrite())
                .evictBlocksOnClose(cf.isEvictBlocksOnClose())
                .prefetchBlocksOnOpen(cf.isPrefetchBlocksOnOpen())
                .setConfiguration(cf.getConfiguration())
                .setValue(cf.getValues())
                .build();
    }
}
