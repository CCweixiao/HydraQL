package com.hydraql.tests.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

import static com.hydraql.tests.common.HydraQlBaseTestConstants.*;

/**
 * @author leojie 2023/8/15 20:32
 */
public class HydraQlBaseTestTemplate {
    private static final HBaseTestingUtility TEST_UTILITY = new HBaseTestingUtility();

    protected void startMiniCluster() throws Exception {
        TEST_UTILITY.startMiniCluster(1);
    }

    protected void shutdownMiniCluster() throws Exception {
        TEST_UTILITY.shutdownMiniCluster();
    }

    protected void createTestTable() throws IOException {
        TEST_UTILITY.createTable(TableName.valueOf(TEST_TABLE), new String[]{F1, F2});
    }

    protected void createTestHqlTable() throws IOException {
        TEST_UTILITY.createTable(TableName.valueOf(TEST_HQL_TABLE), new String[]{F1, F2});
    }

    protected void createTestMultiVersionTable() throws IOException {
        TEST_UTILITY.createTable(TableName.valueOf(TEST_TABLE_WITH_MULTI_VERSION),
                Bytes.toBytes(F1), 5);
    }

    protected Configuration getConfiguration() {
        return TEST_UTILITY.getConfiguration();
    }
}
