package com.hydraql.template;

import com.hydraql.adapter.schema.ColumnFamilyDesc;
import com.hydraql.adapter.schema.HTableDesc;
import com.hydraql.common.util.SplitGoEnum;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * @author leojie@apache.org 2024/7/13 13:34
 */
public class HBaseAdminTemplateTest {
    private static HBaseAdminTemplate adminTemplate;
    private static Configuration configuration = new Configuration();

    @BeforeClass
    public static void setUpClass() {
        configuration.set(HConstants.ZOOKEEPER_QUORUM, "test_zk1,test_zk2,test_zk3");
        configuration.setInt(HConstants.ZOOKEEPER_CLIENT_PORT, 2181);
        configuration.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase2_test");
        adminTemplate = HBaseAdminTemplate.of(configuration);
    }

    @Test
    public void testGetTableDescList() {
        List<HTableDesc> tableDescList = adminTemplate.listTableDesc();
        Assert.assertFalse(tableDescList.isEmpty());
    }


    @Test
    public void disableTable() {
        //boolean res = adminTemplate.disableTable("leo_test", false);
        // System.out.println(res);
        boolean res2 = adminTemplate.disableTable("leo_test_copy", false);
        System.out.println(res2);
    }

    @Test
    public void deleteTable() {
        // boolean res = adminTemplate.deleteTable("leo_test", false);
        // System.out.println(res);
        boolean res2 = adminTemplate.deleteTable("leo_test_copy", false);
        System.out.println(res2);
    }

    @Test
    public void testCreateTable() {
        if (adminTemplate.tableExists("leo_test")) {
            adminTemplate.disableTable("leo_test", false);
            adminTemplate.deleteTable("leo_test", false);
        }

        HTableDesc tableDesc = HTableDesc.newBuilder("leo_test")
                .maxFileSize(10 * 1024 * 1024L)
                .memStoreFlushSize(256 * 1024 * 1024L)
                .readOnly(false)
                .regionSplitPolicyClassName("org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy")
                .addFamilyDesc(ColumnFamilyDesc.newBuilder("cf")
                        .maxVersions(2)
                        .compressionType("snappy")
                        .timeToLive(3600)
                        .blockSize(64 * 1024)
                        .setConfiguration("hbase.hstore.compaction.min", "3")
                        .setConfiguration("hbase.hstore.compaction.max", "5")
                        .setConfiguration("hbase.hstore.compaction.max.size", "1073741824")
                        .setConfiguration("hbase.hstore.blockingStoreFiles", "120")
                        .build())
                .addFamilyDesc(ColumnFamilyDesc.newBuilder("cf2")
                        .maxVersions(1)
                        .compressionType("snappy")
                        .timeToLive(36000)
                        .blockSize(128 * 1024)
                        .blockCacheEnabled(false)
                        .cacheDataOnWrite(false)
                        .cacheIndexesOnWrite(true)
                        .build())
                .build();
        boolean res = adminTemplate.createTable(tableDesc, SplitGoEnum.HEX_STRING_SPLIT, 10, false);
        Assert.assertTrue(res);
    }

    @Test
    public void testGetTableDesc() {
        HTableDesc tableDesc = adminTemplate.getTableDesc("leo_test");
        Assert.assertNotNull(tableDesc);
    }


    @Test
    public void testCreateTableFromCopy() {
        if (adminTemplate.tableExists("leo_test_copy")) {
            adminTemplate.disableTable("leo_test_copy", false);
            adminTemplate.deleteTable("leo_test_copy", false);
        }
        HTableDesc tableDesc = adminTemplate.getTableDesc("leo_test");
        HTableDesc tableDescCopy = HTableDesc.copyFrom("leo_test_copy", tableDesc).build();
        adminTemplate.createTable(tableDescCopy, SplitGoEnum.HEX_STRING_SPLIT, 10, false);
    }
}
