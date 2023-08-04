package com.hydraql.boot.example.pojo;


import com.hydraql.common.annotations.HBaseRowKey;

/**
 * <p>People pojo</p>
 *
 * @author leo.jie (leojie1314@gmail.com)
 */
public class PeoplePojo {
    @HBaseRowKey
    private String rowKey;

    private Integer userStatus;

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}
