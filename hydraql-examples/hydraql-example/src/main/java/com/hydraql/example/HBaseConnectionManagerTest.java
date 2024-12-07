package com.hydraql.example;

import com.hydraql.conf.HqlConfiguration;
import com.hydraql.generator.RowKeyGenerationStrategy;
import com.hydraql.result.GetResult;
import com.hydraql.session.HqlSession;
import com.hydraql.wrapper.GetWrapper;
import org.apache.hadoop.hbase.HConstants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author leojie@apache.org 2024/12/7 20:52
 */
public class HBaseConnectionManagerTest {
    private static final HqlConfiguration configuration = HqlConfiguration.create();

    static {
        configuration.set(HConstants.ZOOKEEPER_QUORUM, "docker-hbase");
        configuration.setInt(HConstants.ZOOKEEPER_CLIENT_PORT, 2181);
        configuration.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase");
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                try(HqlSession session = configuration.getSession("hydraql_test_table")) {
                    GetResult getResult = session.get(GetWrapper.create("11000", RowKeyGenerationStrategy.HASHING));
                    System.out.println(getResult.getRowAsString());
                }
            });
        }

    }
}
