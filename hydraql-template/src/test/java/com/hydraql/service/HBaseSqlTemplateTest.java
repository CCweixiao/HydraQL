package com.hydraql.service;

import com.hydraql.common.model.row.HBaseDataSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * @author leojie 2022/11/27 17:44
 */
public class HBaseSqlTemplateTest extends AbstractHBaseTemplateTest {
    @Before
    public void init() {
        initIHBaseSqlTemplate();
    }

    @Test
    public void testInsert() {
        List<String> hqlList = mockHql();
        for (String hql : hqlList) {
            sqlTemplate.insert(hql);
        }
    }

    @Test
    public void testInsertOne() {
        String hql = "insert into test:test_hql ( row_key , f1:id , f1:name , f1:age , f1:job , f1:pay , f2:address , f2:commuter )" +
                " values ('a10001', 'a10001' , 'leo_a100_01' , 18 , 'Coding' , 13333.33 , 'BeiJing' , 'Car' );";
        sqlTemplate.insert(hql);
    }

    @Test
    public void testInsertMultiValues() {
        String hql = " insert into test:test_hql ( row_key , f1:id , f1:name , f1:age , f1:job , f1:pay , f2:address , f2:commuter )" +
                "  values ('a10001', 'a10001' , 'leo_a100_01' , 18 , 'Coding' , 13333.33 , 'BeiJing' , 'Car' ), " +
                "('a10002', 'a10002' , 'leo_a100_02' , 19 , '外卖员' , 7333.33 , 'ShangHai' , '电动车' );";
        sqlTemplate.insert(hql);
    }

    @Test
    public void testDeleteOneColByRow() {
        String hql = "delete f1:id from test:test_hql where rowKey = 'a10001';";
        sqlTemplate.delete(hql);
    }

    @Test
    public void testInsertByRowFunction() {
        String hsql1 = "insert into test:test_sql ( f1:id , f1:name , f1:age , f2:address ) values ( '11111' , 'a_leo' , 15 , 'bj' ) where rowKey = md5 ( 'a1111' )";
        String hsql2 = "insert into test:test_sql ( f1:id , f1:name , f1:age , f2:address ) values ( '11111' , 'a_leo' , 15 , 'bj' ) where rowKey = md5_prefix ( 'a1111' )";
        sqlTemplate.insert(hsql1);
        sqlTemplate.insert(hsql2);

        String sql1 = "select * from test:test_sql where rowKey = md5 ( 'a1111' )";
        HBaseDataSet dataSet1 = sqlTemplate.select(sql1);
        dataSet1.show();

        System.out.println("============================================================");

        String sql2 = "select * from test:test_sql where rowKey = md5_prefix ( 'a1111' )";
        HBaseDataSet dataSet2 = sqlTemplate.select(sql2);
        dataSet2.show();

    }

    @Test
    public void testShowTables() {
        String hql = "show virtual tables";
        List<String> virtualTables = sqlTemplate.showVirtualTables(hql);
        System.out.println(virtualTables);
    }

    @Test
    public void testShowCreateTable() {
        String hql = "show create virtual table test:test_sql;";
        String s = sqlTemplate.showCreateVirtualTable(hql);
        System.out.println(s);
    }

    @Test
    public void testCreateVirtualTable() {
        String hql = "create virtual table if not exists " +
                "test:test_sql ( " +
                "userId varchar(100) not null primary key, " +
                "f1:name varchar(200) null default '12121', " +
                "f1:age smallint " +
                ") with properties " +
                "( \"hbase.client.scanner.caching\"=100 ," +
                " \"hbase.client.block.cache\"=false);";
        sqlTemplate.createVirtualTable(hql);
        List<String> tables = sqlTemplate.showVirtualTables("show virtual tables;");
        Assert.assertTrue(tables.contains("test:test_sql"));
        String tableDesc = sqlTemplate.showCreateVirtualTable("show create virtual table test:test_sql;");
        Assert.assertTrue(tableDesc.contains("test:test_sql"));
        sqlTemplate.dropVirtualTable("drop virtual table if exists test:test_sql;");
        List<String> tableList = sqlTemplate.showVirtualTables("show virtual tables;");
        Assert.assertTrue(tableList.isEmpty());
    }

    @Test
    public void testSelectByStartAndEndRow() {
        String sql2 = "select * from test:test_sql where startKey > 'a1000' and endKey < 'a1002'";
        HBaseDataSet dataSet2 = sqlTemplate.select(sql2);
        dataSet2.show();
    }

    @Test
    public void testSelectInRows() {
        String sql3 = "select * from" +
                " test:test_sql where " +
                "rowKey " +
                "in('a1000','a1002')";
        HBaseDataSet dataSet3 = sqlTemplate.select(sql3);
        dataSet3.show();
    }

    @Test
    public void testSelectTimeRange() {
        String sql1 = "select * from test:test_sql where " +
                "-- rowKey = 222" +
                "startkey >= 'ewew', endKey < 'dsdsd'" +
                "and (f1:name = 'ds' or f1:age < 12 or (f1:pay between 10 and 20))" +
                "and ( startTs > 1212 , endTs <= 23 )" +
                "-- and startTs >= 1212 " +
                "limit 10";
        HBaseDataSet dataSet1 = sqlTemplate.select(sql1);
    }

    @Test
    public void testSelectRowFunction() {
        String sql1 = "select * from test:test_sql where " +
                "rowKey=md5('a1001') and  maxVersion = 3 and ts = 3231 limit 10e";
        HBaseDataSet dataSet1 = sqlTemplate.select(sql1);

//        String sql2 = "select * from test:test_sql where " +
//                "startKey=md5('a1001') and endKey = '123' ";
//        HBaseDataSet dataSet2 = sqlTemplate.select(sql2);
//
//        String sql3 = "select * from test:test_sql where " +
//                "rowKey in (md5('a1001'), md5('a1002'), 'ewe') ";
//        HBaseDataSet dataSet3 = sqlTemplate.select(sql3);
    }

    @Test
    public void testSelectFilter() {
        String sql = "select * from test:test_sql where startkey > 'a1000' and endkey <= 'g1005' and " +
                "f1:age > 18 and ( (f1:pay > 10000 and f1:job is not null) and (f2:commuter is null " +
                "or f2:commuter != 'subway' ))";

        HBaseDataSet dataSet = sqlTemplate.select(sql);
        dataSet.show();
    }

    @Test
    public void testInsertMaxVersionData() {
        String hsql1 = "insert into test:test_sql (row_key, f1:id , f1:name , f1:age )" +
                " values ('r1', 'id1_v1' , 'leo1_v1' , 11 )," +
                " ('r2', 'id2_v1' , 'leo2_v1' , 21 )," +
                " ('r3', 'id3_v1' , 'leo3_v1' , 31 )";
        sqlTemplate.insert(hsql1);
        String hsql2 = "insert into test:test_sql (row_key, f1:id , f1:name , f1:age )" +
                " values ('r1', 'id2' , 'leo1_v2' , 12 )," +
                " ('r2', 'id2' , 'leo2_v2' , 22 )," +
                " ('r3', 'id2' , 'leo2_v2' , 32 )";
        sqlTemplate.insert(hsql2);
        String hsql3 = "insert into test:test_sql (row_key, f1:id , f1:name , f1:age )" +
                " values ('r1', 'id3' , 'leo1_v3' , 13 )," +
                " ('r2', 'id3' , 'leo2_v3' , 23 )," +
                " ('r3', 'id3' , 'leo3_v3' , 33 )";
        sqlTemplate.insert(hsql3);
    }

    @Test
    public void testDelete() {
        String hql = "delete * from test:test_sql where rowkey in ('r1', 'r2', 'r3')";
        sqlTemplate.delete(hql);
    }
    @Test
    public void testSelectMaxVersion() {
        String hql = "select f1:id,f1:name,f1:age from test:test_sql where rowKey in ('r1','r2','r3') " +
                "and maxversion = 3";
        HBaseDataSet dataSet = sqlTemplate.select(hql);
        dataSet.show(true);
    }



    @Test
    public void testSelect() {


        System.out.println("============================================================");



        System.out.println("============================================================");




        String sql4 = "select * from test:test_sql where ( startKey = 'a1000' , endKey = 'j1009' ) and ( f1:age >= 25 and ( f1:job IS NOT NULL ) ) ";
        HBaseDataSet dataSet4 = sqlTemplate.select(sql4);
        dataSet4.show();
        System.out.println("============================================================");


        String sql5 = "select * from test:test_sql where ( startKey = 'a1000' , endKey = 'j1009' ) and ( f1:age >= 25 and ( f1:job IS NULL or f1:pay > 15000 ) )";
        HBaseDataSet dataSet5 = sqlTemplate.select(sql5);
        dataSet5.show();
        System.out.println("============================================================");


//
//        System.out.println("============================================================");
//        String sql32 = "select f1:name , f1:age from test:test_sql where rowKey = 'row_1000' and maxVersion = 3";
//        HBaseDataSet dataSet32 = sqlTemplate.select(sql32);
//        dataSet32.show();
    }

    @Test
    public void testSelectMaxVersion2() {
        String hsql1 = "insert into test:test_sql ( f1:id ) values ( '11111_v1' ) where rowKey = 'row_10002'";
        String hsql2 = "insert into test:test_sql ( f1:id , f1:age ) values ( '11111_v2' , 14 ) where rowKey = 'row_10002'";
        sqlTemplate.insert(hsql1);
        sqlTemplate.insert(hsql2);

        String sql32 = "select * from test:test_sql where rowKey = 'row_10002' and maxVersion = 2";
        HBaseDataSet dataSet32 = sqlTemplate.select(sql32);
        dataSet32.show();
    }

    @Test
    public void testSelectByFilter() {

        String sql3 = "select * from test:test_sql where rowKey in ( 'a10001' , 'a10002' , 'a10003' )";
        HBaseDataSet dataSet2 = sqlTemplate.select(sql3);
        dataSet2.show();

        System.out.println("============================================================");

        String sql = "select * from test:test_sql where ( startKey = 'a10001' , endKey = 'a10006' ) and f1:age <= 18";
        HBaseDataSet dataSet = sqlTemplate.select(sql);
        dataSet.show();

        System.out.println("============================================================");

        String sql2 = "select * from test:test_sql where ( startKey = 'a10001' , endKey = 'a10006' ) and f1:age <= 18 limit 2";
        HBaseDataSet dataSet1 = sqlTemplate.select(sql2);
        dataSet1.show();


    }

    @Test
    public void testSelectOne() {
        String sql = "select * from test:test_sql where rowKey = 'row_10002'";
        HBaseDataSet dataSet = sqlTemplate.select(sql);
        dataSet.show();
    }

    @Test
    public void testDeleteOne() {
        // String sql = "delete f1:age from test:test_sql where rowKey = 'b20004'";
        // sqlTemplate.delete(sql);

        String sql2 = "delete f1:age from test:test_sql where rowKey = 'row_10001' and ts = 1670579504803";
        sqlTemplate.delete(sql2);
    }

    @Test
    public void printDefaultTableSchema() {
        System.out.println(defaultTableSchemaJsonFormat());
    }
    public String defaultTableSchemaJsonFormat() {
        return "{" +
                "\t\"tableName\": \"test_table\"," +
                "\t\"defaultFamily\": \"cf\"," +
                "\t\"columnList\": [{" +
                "\t    \"familyName\": \"\"," +
                "\t    \"columnName\": \"rowKey\"," +
                "\t    \"columnType\": \"string\"," +
                "\t    \"columnIsRow\": \"true\"" +
                "\t},{" +
                "\t    \"familyName\": \"cf\"," +
                "\t    \"columnName\": \"name\"," +
                "\t    \"columnType\": \"string\"," +
                "\t    \"columnIsRow\": \"true\"" +
                "\t},]" +
                "}";
    }

}
