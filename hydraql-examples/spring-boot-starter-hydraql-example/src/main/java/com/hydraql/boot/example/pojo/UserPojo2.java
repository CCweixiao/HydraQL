package com.hydraql.boot.example.pojo;


import com.hydraql.common.annotation.HBaseRowKey;
import com.hydraql.common.annotation.HBaseTable;

/**
 * <p>User2 Pojo</p>
 *
 * @author leo.jie (leojie1314@gmail.com)
 */
@HBaseTable(tableName = "user2", defaultFamily = "info")
public class UserPojo2 {
    @HBaseRowKey
    private String rowKey;
    private boolean isVip;
    //private String address;

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

 /*   public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }*/
}
