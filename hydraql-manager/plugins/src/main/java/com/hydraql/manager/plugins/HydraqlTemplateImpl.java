package com.hydraql.manager.plugins;

import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.query.GetRowParam;
import com.hydraql.connection.HBaseConnectionManager;
import com.hydraql.manager.core.conf.HydraqlHBaseConfiguration;
import com.hydraql.manager.core.template.HydraqlTemplate;
import com.hydraql.template.HBaseAdminTemplate;
import com.hydraql.template.HBaseTableTemplate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;

import java.util.List;

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
}
