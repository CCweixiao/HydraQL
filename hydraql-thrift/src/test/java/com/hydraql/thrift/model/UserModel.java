package com.hydraql.thrift.model;

import com.hydraql.common.annotations.HBaseColumn;
import com.hydraql.common.annotations.HBaseRowKey;
import com.hydraql.common.annotations.HBaseTable;

/**
 * @author leojie 2022/12/10 17:44
 */
@HBaseTable(namespaceName = "test", tableName = "t1", defaultFamilyName = "info")
public class UserModel {
    @HBaseRowKey
    private String userId;
    @HBaseColumn()
    private String nickName;
    @HBaseColumn(familyName = "detail", columnName = "detailAddress")
    private String detailAddress;
    @HBaseColumn(familyName = "detail", toUpperCase = true)
    private double detailPay;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public double getDetailPay() {
        return detailPay;
    }

    public void setDetailPay(double detailPay) {
        this.detailPay = detailPay;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId='" + userId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", detailPay=" + detailPay +
                '}';
    }
}
