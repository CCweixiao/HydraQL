package com.hydraql;

import com.hydraql.adapter.HQlConfiguration;
import com.hydraql.session.HQLSessionFactoryBuilder;
import com.hydraql.session.HQlSession;
import com.hydraql.session.HQlSessionFactory;
import com.hydraql.session.HQlSessionManager;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

/**
 * @author leojie@apache.org 2024/10/27 13:29
 */
public class HQLTableTest {
    @Test
    public void testHQlSessionFactory() {
        HQlConfiguration configuration = new HQlConfiguration();
        HQLTable table = configuration.getTable("test_table");
        HQlSessionFactory sessionFactory = new HQLSessionFactoryBuilder().build(table);
        HQlSession session = sessionFactory.openSession();
        session.get(new Get(Bytes.toBytes("10001")));
    }

    @Test
    public void testHQlSessionManager() {
        HQlConfiguration configuration = new HQlConfiguration();
        HQLTable table = configuration.getTable("test_table");
        HQlSessionManager sessionManager = HQlSessionManager.newInstance(table);
        sessionManager.startManagedSession();
        sessionManager.get(new Get(Bytes.toBytes("10001")));
    }
}
