package com.github.CCweixiao.thrift;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @author leojie 2020/12/27 2:59 下午
 */
public class HBaseThriftPoolSingleTests {

    private HBaseThriftPool hBaseThriftPool;

    @Before
    public void init() {
        HBaseThriftPoolConfig config = new HBaseThriftPoolConfig();
        hBaseThriftPool = new HBaseThriftPool(config, "localhost", 9090);
    }

    @Test
    public void testThriftPool() {
        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
        System.out.println(hBaseThrift.getTableNames());
        hBaseThrift.close();
    }

    @Test
    public void testThriftClient() {
        HBaseThrift hBaseThrift = new HBaseThrift();
        hBaseThrift.connect();
        System.out.println(hBaseThrift.getTableNames());
        hBaseThrift.close();
    }

    @Test
    public void testBatchPut() {
        Map<String, String> data1 = new HashMap<>();
        data1.put("g:name", "leo");
        data1.put("g:age", "18");
        data1.put("g:address", "上海");
        data1.put("g:tag", null);

        Map<String, String> data2 = new HashMap<>();
        data2.put("f:name", "leo2");
        data2.put("f:age", "18");
        data2.put("f:address", "上海2");
        data2.put("f:tag", "tag2");

        Map<String, Map<String, String>> data = new HashMap<>(2);
        data.put("a10001", data1);
        data.put("a10002", data2);

        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();

        hBaseThrift.saveBatch("LEO_USER", data);
        hBaseThrift.close();
    }

    @Test
    public void testPut() {
        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();

        Map<String, String> data3 = new HashMap<>();
        data3.put("g:name", "leo2g");
        data3.put("g:age", "18");
        data3.put("g:address", "上海2g");
        data3.put("g:tag", "tag2g");

        hBaseThrift.save("LEO_USER", "a10002", data3);
        hBaseThrift.close();
    }

    @Test
    public void testGet() {

        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();

        System.out.println(hBaseThrift.getByRowKeyWithFamilyAndQualifiersToMap("LEO_USER",
                "a10001", "g", Collections.singletonList("age")));

        System.out.println(hBaseThrift.getByRowKeyWithFamilyAndQualifiersToMap("LEO_USER",
                "a10001", "g", Arrays.asList("name", "age", "sds")));

        System.out.println(hBaseThrift.getByRowKeyWithFamilyToMap("LEO_USER",
                "a10002", "f"));

        System.out.println(hBaseThrift.getByRowKeyWithFamilyToMap("LEO_USER",
                "a10002", "g"));

        System.out.println(hBaseThrift.getByRowKeyToMap("LEO_USER",
                "a1002"));

        hBaseThrift.close();
    }

    @Test
    public void testDeleteRow() {

        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
        hBaseThrift.delete("LEO_USER", "a1002");
        hBaseThrift.close();
    }

    @Test
    public void testDeleteFamily() {
        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
        hBaseThrift.delete("LEO_USER", "a10001", "g", new ArrayList<>());
        hBaseThrift.close();
    }

    @Test
    public void testDeleteSomeColumns() {
        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
        hBaseThrift.delete("LEO_USER", "a10001", "g", "address");
        hBaseThrift.close();
    }

    @Test
    public void testDeleteBatch() {
        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
        hBaseThrift.deleteBatch("LEO_USER", Arrays.asList("a10001", "a10002"));
    }

    @Test
    public void testDeleteBatchWithFamily() {
        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
        hBaseThrift.deleteBatch("LEO_USER", Arrays.asList("a10001", "a10002"), "g");
    }

    @Test
    public void testDeleteBatchWithFamilyAndCol() {
        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
        hBaseThrift.deleteBatch("LEO_USER", Arrays.asList("a10001", "a10002"), "f", "address");
        hBaseThrift.deleteBatch("LEO_USER", Arrays.asList("a10001", "a10002"), "f", Arrays.asList("tag", "age"));

    }

    @Test
    public void testGetRows() {
        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
        final Map<String, Map<String, String>> res = hBaseThrift.getRowsByRowKeysToMap("LEO_USER", Arrays.asList("a10001", "a10002"));
        System.out.println(res);
    }

    @Test
    public void testGetRowsFamily() {
        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
        final Map<String, Map<String, String>> res = hBaseThrift.getRowsByRowKeysWithFamilyToMap("LEO_USER",
                Arrays.asList("a10001", "a10002"), "g");
        System.out.println(res);
    }

    @Test
    public void testGetRowsFamilyAndQualifier() {
        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
        final Map<String, Map<String, String>> res = hBaseThrift.getRowsByRowKeysWithFamilyAndQualifiersToMap
                ("LEO_USER", Arrays.asList("a10001", "a10002"), "f", Arrays.asList("name", "age"));
        System.out.println(res);
    }

    @Test
    public void testGetRowsQualifierAndNoFamily() {
        final HBaseThrift hBaseThrift = hBaseThriftPool.getResource();
        final Map<String, Map<String, String>> res = hBaseThrift.getRowsByRowKeysWithFamilyAndQualifiersToMap
                ("LEO_USER", Arrays.asList("a10001", "a10002"), "", Arrays.asList("name", "age"));
        System.out.println(res);
    }

}
