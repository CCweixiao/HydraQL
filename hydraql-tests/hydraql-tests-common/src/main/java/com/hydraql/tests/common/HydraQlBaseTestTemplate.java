package com.hydraql.tests.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;

/**
 * @author leojie 2023/8/4 23:00
 */
public class HydraQlBaseTestTemplate {
    private static final HBaseTestingUtility TEST_UTILITY = new HBaseTestingUtility();
    public static final String TEST_TABLE = "test_table";
    public static final String TEST_HQL_TABLE = "test_hql_table";
    public static final String F1 = "f1";
    public static final String F2 = "f2";

    public void startMiniCluster() throws Exception {
        TEST_UTILITY.startMiniCluster(1);
    }

    public void shutdownMiniCluster() throws Exception {
        TEST_UTILITY.shutdownMiniCluster();
    }

    public void createTestTable() throws IOException {
        TEST_UTILITY.createTable(TableName.valueOf(TEST_TABLE), new String[]{F1, F2});
    }

    public void createTestHqlTable() throws IOException {
        TEST_UTILITY.createTable(TableName.valueOf(TEST_HQL_TABLE), new String[]{F1, F2});
    }

    public Configuration getConfiguration() {
        return TEST_UTILITY.getConfiguration();
    }

}
