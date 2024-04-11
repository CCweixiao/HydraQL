package com.hydraql.benchmark.worker;

import com.hydraql.benchmark.core.ByteIterator;
import com.hydraql.benchmark.core.DBException;
import com.hydraql.benchmark.core.Status;
import com.hydraql.benchmark.core.StringByteIterator;
import org.apache.hadoop.hbase.client.Scan;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leojie@apache.org 2024/4/11 09:13
 */
public class HydraQLTemplateClientTest {
    @Test
    public void testPut() throws DBException {
        Scan scan = new Scan();
        scan.setCacheBlocks(false);
        HydraQLTemplateClient client = new HydraQLTemplateClient();
        client.init();
        Map<String, ByteIterator> data = new HashMap<>();
        data.put("field0", new StringByteIterator("test"));
        Status status = client.update("usertable", "user1000000025861564284", data);
        System.out.println(status);
    }
}
