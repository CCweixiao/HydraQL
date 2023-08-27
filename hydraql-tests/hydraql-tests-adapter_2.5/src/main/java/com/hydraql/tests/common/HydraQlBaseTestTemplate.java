package com.hydraql.tests.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;

import static com.hydraql.tests.common.HydraQlBaseTestConstants.F1;
import static com.hydraql.tests.common.HydraQlBaseTestConstants.F2;
import static com.hydraql.tests.common.HydraQlBaseTestConstants.TEST_HQL_TABLE;
import static com.hydraql.tests.common.HydraQlBaseTestConstants.TEST_TABLE;

/**
 * @author leojie 2023/8/4 23:00
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

    protected Configuration getConfiguration() {
        return TEST_UTILITY.getConfiguration();
    }

}
