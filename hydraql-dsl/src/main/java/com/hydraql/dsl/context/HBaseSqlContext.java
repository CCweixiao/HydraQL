package com.hydraql.dsl.context;

import com.hydraql.common.util.StringUtil;
import com.hydraql.dsl.model.HBaseTableSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;


/**
 * @author leojie 2022/12/4 00:03
 */
public class HBaseSqlContext {
    private volatile static HBaseSqlContext sqlContext;
    private final ConcurrentMap<String, HBaseTableSchema> tableSchemaMap;

    private HBaseSqlContext() {
        tableSchemaMap = new ConcurrentHashMap<>();
    }

    public static HBaseSqlContext getInstance() {
        if (sqlContext == null) {
            synchronized (HBaseSqlContext.class) {
                if (sqlContext == null) {
                    sqlContext = new HBaseSqlContext();
                }
            }
        }
        return sqlContext;
    }

    public void registerTableSchema(String schemaUniqueKey, HBaseTableSchema tableSchema) {
        if (StringUtil.isBlank(schemaUniqueKey)) {
            return;
        }
        if (tableSchema == null) {
            return;
        }
        tableSchemaMap.remove(schemaUniqueKey);
        tableSchemaMap.put(schemaUniqueKey, tableSchema);
    }

    public void removeTableSchema(String schemaUniqueKey) {
        if (StringUtil.isBlank(schemaUniqueKey)) {
            return;
        }
        tableSchemaMap.remove(schemaUniqueKey);
    }

    public HBaseTableSchema getTableSchema(String schemaUniqueKey) {
        if (StringUtil.isBlank(schemaUniqueKey)) {
            throw new IllegalArgumentException("The schemaUniqueKey is not empty.");
        }
        return tableSchemaMap.get(schemaUniqueKey);
    }

    public List<String> getAllRegisteredVirtualTables() {
        if (tableSchemaMap.isEmpty()) {
            return new ArrayList<>(0);
        }
        return tableSchemaMap.values().stream().map(HBaseTableSchema::getTableName).collect(Collectors.toList());
    }


}
