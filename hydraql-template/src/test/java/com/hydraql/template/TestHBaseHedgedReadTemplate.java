package com.hydraql.template;

import com.hydraql.adapter.HBaseClientConf;
import com.hydraql.adapter.hedgedread.HedgedReadStrategy;
import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.query.ScanParams;
import org.apache.hadoop.conf.Configuration;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author leojie 2023/8/3 09:24
 */
public class TestHBaseHedgedReadTemplate {
    public static final String TEST_TABLE = "test_tab";
    @Test
    public void testHedgedConfiguration() {
        Configuration conf = createHedgedConfiguration("myhbase", "myhbase", true,
                HedgedReadStrategy.Level.THRESHOLD);
        HBaseClientConf clientConf = new HBaseClientConf(conf);
        Assert.assertTrue(clientConf.hedgedReadIsOpen());
        Assert.assertEquals(HedgedReadStrategy.Level.THRESHOLD, clientConf.getHedgedReadStrategy());
        Assert.assertEquals(10, clientConf.getHedgedReadThreadpoolSize());
        Assert.assertEquals(60000, clientConf.getHedgedReadOverallTimeoutMillis());
        Assert.assertEquals(60, clientConf.getHedgedReadThresholdMillis());
        Assert.assertTrue(clientConf.isHedgedReadWriteDisable());
    }

    public Configuration createHedgedConfiguration(String zk1, String zk2, boolean writeDisable,
                                                   HedgedReadStrategy.Level strategy) {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", zk1);
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        // 开启hedged read
        conf.set("hbase.client.hedged.read.strategy", strategy.name());
        conf.setInt("hbase.client.hedged.read.threadpool.size", 10);
        conf.setLong("hbase.client.hedged.read.overall.timeout.millis", 60000L);
        conf.setLong("hbase.client.hedged.read.threshold.millis", 60L);
        conf.setBoolean("hbase.client.hedged.read.write.disable", writeDisable);

        conf.set("hedged.read.hbase.zookeeper.quorum", zk2);
        conf.set("hedged.read.hbase.zookeeper.property.clientPort", "2181");
        return conf;
    }

    @Test
    public void testHedgedReadThresholdGet() {
        Configuration conf = createHedgedConfiguration("myhbase", "myhbase", true,
                HedgedReadStrategy.Level.THRESHOLD);
        HBaseTableTemplate tableTemplate = HBaseTableTemplate.of(conf);
        List<HBaseRowData> rowDataList = tableTemplate.scan(TEST_TABLE, ScanParams.of().build());
        long start = System.currentTimeMillis();
        List<HBaseRowData> rowDataList2 = tableTemplate.scan(TEST_TABLE, ScanParams.of().build());
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        System.out.println(rowDataList);
        System.out.println(rowDataList2);
    }

    @Test
    public void testHedgedReadFirstOne() {
        Configuration conf = createHedgedConfiguration("myhbase1", "myhbase", true,
                HedgedReadStrategy.Level.FIRST_ONE);

        HBaseTableTemplate tableTemplate = HBaseTableTemplate.of(conf);
        List<HBaseRowData> rowDataList = tableTemplate.scan(TEST_TABLE, ScanParams.of().build());
        long start = System.currentTimeMillis();
        List<HBaseRowData> rowDataList2 = tableTemplate.scan(TEST_TABLE, ScanParams.of().build());
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        System.out.println(rowDataList2);
    }

    @Test
    public void testHedgedReadConsistency() {
        Configuration conf = createHedgedConfiguration("myhbase1", "myhbase", true,
                HedgedReadStrategy.Level.CONSISTENCY);
        HBaseTableTemplate tableTemplate = HBaseTableTemplate.of(conf);
        long start = System.currentTimeMillis();
        List<HBaseRowData> rowDataList = tableTemplate.scan(TEST_TABLE, ScanParams.of().build());
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        System.out.println(rowDataList);
    }

    @Test
    public void testHedgedReadHash() {
        Configuration conf = createHedgedConfiguration("myhbase1", "myhbase", true,
                HedgedReadStrategy.Level.HASH);
        HBaseTableTemplate tableTemplate = HBaseTableTemplate.of(conf);
        long start = System.currentTimeMillis();
        List<HBaseRowData> rowDataList = tableTemplate.scan(TEST_TABLE, ScanParams.of().build());
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        System.out.println(rowDataList);

        start = System.currentTimeMillis();
        List<HBaseRowData> rowDataList2 = tableTemplate.scan(TEST_TABLE, ScanParams.of().build());
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        System.out.println(rowDataList2);
    }


}
