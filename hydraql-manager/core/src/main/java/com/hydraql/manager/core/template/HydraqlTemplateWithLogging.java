package com.hydraql.manager.core.template;

import com.hydraql.manager.core.hbase.schema.HTableDesc;
import com.hydraql.manager.core.hbase.schema.NamespaceDesc;
import com.hydraql.manager.core.model.HBaseRowData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author leojie 2024/1/26 10:41
 */
public class HydraqlTemplateWithLogging implements HydraqlTemplate {
    private static final Logger LOG = LoggerFactory.getLogger(HydraqlTemplateWithLogging.class);

    private final HydraqlTemplate template;

    HydraqlTemplateWithLogging(HydraqlTemplate template) {
        this.template = template;
    }

    @Override
    public boolean tableExists(String tableName) {
        return template.tableExists(tableName);
    }

    @Override
    public boolean createNamespace(NamespaceDesc namespaceDesc) {
        return template.createNamespace(namespaceDesc);
    }

    @Override
    public boolean namespaceIsExists(String namespaceName) {
        return template.namespaceIsExists(namespaceName);
    }

    @Override
    public boolean deleteNamespace(String namespaceName) {
        return template.deleteNamespace(namespaceName);
    }

    @Override
    public List<NamespaceDesc> listNamespaceDesc() {
        return template.listNamespaceDesc();
    }

    @Override
    public List<String> listNamespaceNames() {
        return template.listNamespaceNames();
    }

    @Override
    public boolean createTable(HTableDesc tableDesc) {
        return template.createTable(tableDesc);
    }

    @Override
    public List<HTableDesc> listTableDesc(boolean includeSysTables) {
        return template.listTableDesc(includeSysTables);
    }

    @Override
    public List<String> listTableNames() {
        return template.listTableNames();
    }

    @Override
    public HBaseRowData getRow(String tableName, String rowKey) {
        return template.getRow(tableName, rowKey);
    }
}
