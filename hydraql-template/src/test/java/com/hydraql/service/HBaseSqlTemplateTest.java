package com.hydraql.service;

import com.hydraql.common.model.row.HBaseDataSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

/**
 * @author leojie 2022/11/27 17:44
 */
public class HBaseSqlTemplateTest extends AbstractHBaseTemplateTest {
    @Before
    public void init() {
        initIHBaseSqlTemplate();
    }

    @Test
    public void testInsertMockData() {
        List<String> hqlList = mockHql();
        for (String hql : hqlList) {
            sqlTemplate.insert(hql);
        }
    }

    @Test
    public void testInsertNullAndEmptyStr() {
        String hql = String.format("upsert into test:test_sql ( row_key , f1:id , f1:name , f1:age , f1:job , f1:pay , f2:address , f2:commuter ) " +
                        "values ('%s', '%s' , '%s' , null , '%s' , %.2f , '北京' , '%s' )", "10001", "10001",
                "leojie" , "程序员", 20000 * new Random().nextInt(10) * 0.1 / 3 + 10000, "");
        System.out.println(hql);
        // sqlTemplate.insert(hql);
        HBaseDataSet dataSet = sqlTemplate.select("select * from test:test_sql where rowkey = '10001'");
        dataSet.show();
    }

    @Test
    public void testInsertOne() {
        testCreateVirtualTable();
        String hql = "upsert into test:test_sql ( userId , f1:name, f1:age, f2:pay )" +
                " values ('a10001', 'leo', 18, 1000.5);";
        sqlTemplate.insert(hql);
    }



    @Test
    public void testInsertMultiValues() {
        testCreateVirtualTable();
        String hql = "upsert into test:test_sql ( userId , f1:name, f1:age, f2:pay )" +
                " values ('a10001', 'leo', 18, 1000.5),('b10002', 'yyf', 17, 2000.5);";
        sqlTemplate.insert(hql);
    }

    @Test
    public void testDeleteOneColByRow() {
        String hql = "delete f1:id from test:test_hql where rowKey = 'a10001';";
        sqlTemplate.delete(hql);
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
        sqlTemplate.dropVirtualTable("drop virtual table if exists test:test_sql;");
        String hql = "create virtual table if not exists " +
                "test:test_sql ( " +
                "row_key varchar(100) not null primary key, " +
                "f1:id varchar(200) not null , " +
                "f1:name varchar(200) not null, " +
                "f1:age smallint null," +
                "f1:job varchar(200) not null, " +
                "f1:pay float," +
                "f2:address varchar(200) not null, " +
                "f2:commuter varchar(200) not null " +
                ") with properties " +
                "( \"hbase.client.scanner.caching\"=100 ," +
                " \"hbase.client.block.cache\"=false);";
        sqlTemplate.createVirtualTable(hql);
        List<String> tables = sqlTemplate.showVirtualTables("show virtual tables;");
        Assert.assertTrue(tables.contains("test:test_sql"));
        String tableDesc = sqlTemplate.showCreateVirtualTable("show create virtual table test:test_sql;");
        Assert.assertTrue(tableDesc.contains("test:test_sql"));

    }

    @Test
    public void testDropVirtualTable() {
        this.testCreateVirtualTable();
        sqlTemplate.dropVirtualTable("drop virtual table if exists test:test_sql;");
        List<String> tableList = sqlTemplate.showVirtualTables("show virtual tables;");
        Assert.assertTrue(tableList.isEmpty());
    }

    @Test
    public void selectOne() {
        String hql = "select * from test:test_sql where rowKey = 'a1005'";
        HBaseDataSet dataSet = sqlTemplate.select(hql);
        dataSet.show();
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
        String hql = "select * from test:test_sql where " +
                "rowKey = '10001'" +
                " ( startTs > 1695558386198 , endTs <= 1695558386199 )";
        HBaseDataSet dataSet = sqlTemplate.select(hql);
        dataSet.show();
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
        String hsql1 = "upsert into test:test_sql ( row_key , f1:id , f1:name , f1:age , f1:job , f1:pay , f2:address , f2:commuter )" +
                " values ('10011', '10001v1' , 'leojie' , null , '程序员' , 10666.67 , '北京' , '' )";
        sqlTemplate.insert(hsql1);
        String hsql2 = "upsert into test:test_sql ( row_key , f1:id , f1:name , f1:age , f1:job , f1:pay , f2:address , f2:commuter )" +
                " values ('10011', '10001v2' , 'leojie' , null , '程序员' , 10666.67 , '北京' , '' )";
        sqlTemplate.insert(hsql2);
        String hsql3 = "upsert into test:test_sql ( row_key , f1:id , f1:name , f1:age , f1:job , f1:pay , f2:address , f2:commuter )" +
                " values ('10011', '10001v3' , 'leojie' , null , '程序员' , 10666.67 , '北京' , '' )";
        sqlTemplate.insert(hsql3);
        String hsql4 = "upsert into test:test_sql ( row_key , f1:id , f1:name , f1:age , f1:job , f1:pay , f2:address , f2:commuter )" +
                " values ('10011', '10001v4' , 'leojie' , null , '程序员' , 10666.67 , '北京' , '' )";
        sqlTemplate.insert(hsql4);
        String hsql5 = "upsert into test:test_sql ( row_key , f1:id , f1:name , f1:age , f1:job , f1:pay , f2:address , f2:commuter )" +
                " values ('10011', '10001v5' , 'leojie' , null , '程序员' , 10666.67 , '北京' , '' )";
        sqlTemplate.insert(hsql5);
    }

    @Test
    public void testSelectMaxVersions() {
        String hql = "select f1:id from test:test_sql where rowkey = '10011' versions 5 (startTs>=1695564244983, endTs <= 1695564245041)";
        HBaseDataSet dataSet = sqlTemplate.select(hql);
        dataSet.show();
    }

    @Test
    public void testSelectLike() {
        String hql = "select f1:id as user_id from test:test_sql where rowkey like '100' versions 6 ";
        HBaseDataSet dataSet = sqlTemplate.select(hql);
        dataSet.show();
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
