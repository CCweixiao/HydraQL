package com.hydraql.template;

import org.apache.hadoop.conf.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leojie@apache.org 2024/4/10 10:54
 */
public class TestTemplate {
    private static HBaseAdminTemplate adminTemplate;


    @BeforeClass
    public static void setUp() {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "myhbase");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        adminTemplate = HBaseAdminTemplate.of(conf);
    }

    @Test
    public void modifyTableAttributes() {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("hbase.hstore.block.storage.policy", "ONE_SSD");
        attributes.put("TEST_KEY", "LEO");
        attributes.put("MAX_FILESIZE", "107374182400");
        boolean res = adminTemplate.modifyTableAttributesAsync("test_tab", attributes);
        System.out.println(res);
    }

    @Test
    public void removeTableAttributes() {
        boolean res = adminTemplate.removeTableAttributesAsync("test_tab",
                Arrays.asList("hbase.hstore.block.storage.policy", "TEST_KEY", "MAX_FILESIZE"));
        System.out.println(res);
    }

    @Test
    public void modifyTableConfiguration() {
        Map<String, String> configuration = new HashMap<>();
        configuration.put("hbase.hstore.block.storage.policy", "ONE_SSD");
        configuration.put("TEST_KEY", "LEO2");
        boolean res = adminTemplate.modifyTableConfigurationAsync("test_tab", configuration);
        System.out.println(res);
    }

    @Test
    public void removeTableConfiguration() {
        boolean res = adminTemplate.removeTableConfigurationAsync("test_tab",
                Arrays.asList("hbase.hstore.block.storage.policy", "TEST_KEY", "MAX_FILESIZE"));
        System.out.println(res);
    }
}
