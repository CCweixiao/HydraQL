package com.hydraql.template.model;

import com.hydraql.common.annotation.HBaseColumn;
import com.hydraql.common.annotation.HBaseRowKey;
import com.hydraql.common.annotation.HBaseTable;

import java.util.Map;

/**
 * @author leojie@apache.org 2024/7/22 22:23
 */
@HBaseTable(tableName = "leojie_test", defaultFamily = "cf")
public class UserData {
    @HBaseRowKey
    private String userId;

    @HBaseColumn
    private String username;

    @HBaseColumn
    private boolean student;

    @HBaseColumn
    private Double cost;

    @HBaseColumn
    private Map<String, String> info;

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

    public boolean getStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }
}