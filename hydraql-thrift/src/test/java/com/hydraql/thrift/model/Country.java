package com.hydraql.thrift.model;

import com.hydraql.common.annotation.HBaseColumn;

public class Country {
    @HBaseColumn(family = "info", qualifier = "countryName")
    private String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
