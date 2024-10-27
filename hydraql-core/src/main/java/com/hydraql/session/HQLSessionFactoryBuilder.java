package com.hydraql.session;

import com.hydraql.HQLTable;

/**
 * @author leojie@apache.org 2024/10/27 20:59
 */
public class HQLSessionFactoryBuilder {
    public HQlSessionFactory build(HQLTable table) {
        return new DefaultHQlSessionFactory(table);
    }
}
