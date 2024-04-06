package com.hydraql.common.meta;

import com.hydraql.common.annotations.HBaseColumn;
import com.hydraql.common.annotations.HBaseRowKey;
import com.hydraql.common.annotations.HBaseTable;
import com.hydraql.common.exception.InvalidTableModelClassException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leojie@apache.org 2024/4/3 23:25
 */
public class ReflectFactoryTest {
    static class User {
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    @Test
    public void testGetHBaseTableMeta() {
        boolean throwErr = false;
        try {
            ReflectFactory.getInstance().register(User.class);
        } catch (InvalidTableModelClassException e) {
            throwErr = true;
        }
        Assert.assertTrue(throwErr);
    }

    static class People {
        @HBaseColumn
        private String country;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    @HBaseTable(tableName = "user_info", defaultFamily = "f")
    static class User2 extends People {
        @HBaseRowKey
        private String userId;

        @HBaseColumn(qualifier = "user_name")
        private String username;

        @HBaseColumn(family = "d", qualifier = "ADDRESS")
        private String address;

        @HBaseColumn
        private String detailInfo;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDetailInfo() {
            return detailInfo;
        }

        public void setDetailInfo(String detailInfo) {
            this.detailInfo = detailInfo;
        }
    }

    @Test
    public void testGetHBaseTableMeta2() {
        HBaseTableMeta tableMeta = ReflectFactory.getInstance().register(User2.class);
        Assert.assertNotNull(tableMeta);
        Assert.assertEquals(5, tableMeta.getFieldStructList().size());
        Assert.assertTrue(tableMeta.getFieldStructList().get(0).isRowKey());
    }
}
