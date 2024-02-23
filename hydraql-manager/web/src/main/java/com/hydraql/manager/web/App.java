package com.hydraql.manager.web;

import com.hydraql.manager.core.conf.PropertyKey;
import com.hydraql.manager.core.model.HBaseRowData;
import com.hydraql.manager.core.conf.HydraqlHBaseConfiguration;
import com.hydraql.manager.core.template.HydraqlTemplate;

/**
 * @author leojie 2024/1/26 10:51
 */
public class App {
    public static void main(String[] args) {
        HydraqlHBaseConfiguration conf = new HydraqlHBaseConfiguration();
        conf.set(PropertyKey.HYDRAQL_MANAGER_PLUGINS_DIR, "/Users/leojie/other_project/HydraQL/lib");

        conf.set(PropertyKey.HYDRAQL_HBASE_ZOOKEEPER_QUORUM, "myhbase");
        conf.set(PropertyKey.HYDRAQL_HBASE_ZOOKEEPER_CLIENT_PORT, "2181");
        conf.set(PropertyKey.HYDRAQL_HBASE_VERSION, "1.4.3");

        HydraqlTemplate hydraqlTemplate = HydraqlTemplate.Factory.create(conf);
        System.out.println(hydraqlTemplate.listTableNames());
        HBaseRowData j1009 = hydraqlTemplate.getRow("test:test_sql", "j1009");
        System.out.println(j1009);

        HydraqlHBaseConfiguration conf1 = new HydraqlHBaseConfiguration();
        conf1.set(PropertyKey.HYDRAQL_MANAGER_PLUGINS_DIR, "/Users/leojie/other_project/HydraQL/lib");

        conf1.set(PropertyKey.HYDRAQL_HBASE_ZOOKEEPER_QUORUM, "myhbase");
        conf1.set(PropertyKey.HYDRAQL_HBASE_ZOOKEEPER_CLIENT_PORT, "2181");
        conf1.set(PropertyKey.HYDRAQL_HBASE_VERSION, "1.4.3");

        HydraqlTemplate hydraqlTemplate1 = HydraqlTemplate.Factory.create(conf1);
        System.out.println(hydraqlTemplate1.listTableNames());
        HBaseRowData j10091 = hydraqlTemplate1.getRow("test:test_sql", "j1009");
        System.out.println(j10091);
    }
}
