package com.hydraql.template;

import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.query.ScanParams;
import org.apache.hadoop.conf.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * @author leojie 2023/8/3 09:24
 */
public class TestHBaseHedgedReadTemplate {
    public static final String TEST_TABLE = "t1";
    protected static BaseHBaseTableTemplate tableTemplate;

    @BeforeClass
    public static void setUp() throws Exception {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "myhbase1");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        // 开启hedged read
        conf.set("hbase.client.hedged.read.threadpool.size", "10");
        conf.set("hbase.client.hedged.read.threshold.millis", "60");
        conf.set("hbase.client.hedged.read.write.disable", "true");

        conf.set("hedged.read.hbase.zookeeper.quorum", "myhbase");
        conf.set("hedged.read.hbase.zookeeper.property.clientPort", "2181");
        tableTemplate = HBaseTableTemplate.of(conf);
    }

    @Test
    public void testGet() {
        List<HBaseRowData> rowDataList = tableTemplate.scan(TEST_TABLE, ScanParams.of().build());
        System.out.println(rowDataList);
    }


}
