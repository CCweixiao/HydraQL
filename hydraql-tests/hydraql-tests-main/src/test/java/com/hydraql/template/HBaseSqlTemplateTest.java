package com.hydraql.template;

import com.hydraql.common.model.row.HBaseDataSet;
import com.hydraql.tests.common.HydraQlBaseTestTemplate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author leojie 2023/9/7 20:01
 */
public class HBaseSqlTemplateTest extends HydraQlBaseTestTemplate {
    private HBaseSqlTemplate sqlTemplate;

    @Before
    public void setup() throws Exception {
        startMiniCluster();
        sqlTemplate = HBaseSqlTemplate.of(getConfiguration());
        createTestTable();
        createTestMultiVersionTable();
    }

    @Test
    public void testCreateVirtualTable() {
        String hql = "create virtual table test_table (\n" +
                "    row_key string isrowkey,\n" +
                "    f1:id string nullable,\n" +
                "    f1:name string nullable,\n" +
                "    f1:age int nullable,\n" +
                "    f1:job string nullable,\n" +
                "    f1:pay double nullable,\n" +
                "    f2:address string nullable,\n" +
                "    f2:commuter string nullable\n" +
                ") with properties (\n" +
                "\"hbase.client.scanner.caching\"=\"100\"\n" +
                ",\"hbase.client.block.cache\"=\"false\"\n" +
                ");";
        sqlTemplate.createVirtualTable(hql);
        List<String> tables = sqlTemplate.showVirtualTables("show virtual tables;");
        String tableDesc = sqlTemplate.showCreateVirtualTable("show create virtual table test_table;");
        Assert.assertFalse(tables.isEmpty());
        Assert.assertNotNull(tableDesc);
    }

    @Test
    public void testInsert() {
        testCreateVirtualTable();
        String hql = "insert into test_table ( row_key , f1:id , f1:name , f1:age , f1:job , f1:pay , f2:address , f2:commuter )\n" +
                "  values ('a10001', 'a10001' , 'leo_a100_01' , 18 , 'Coding' , 13333.33 , 'BeiJing' , 'Car' ),\n" +
                "  ('a10002', 'a10002' , 'leo_a100_02' , 19 , '外卖员' , 7333.33 , 'ShangHai' , '电动车' );";
        sqlTemplate.insert(hql);
        hql = "select * from test_table where rowkey = 'a10001'";
        HBaseDataSet dataSet = sqlTemplate.select(hql);
        Assert.assertEquals(1, dataSet.getRowSet().size());
    }

    @Test
    public void testDelete() {
        testInsert();
        String hql = "delete * from test_table where rowkey = 'a10001'";
        sqlTemplate.delete(hql);
        hql = "select * from test_table where rowkey = 'a10001'";
        HBaseDataSet dataSet = sqlTemplate.select(hql);
        Assert.assertEquals(0, dataSet.getRowSet().size());
    }
}
