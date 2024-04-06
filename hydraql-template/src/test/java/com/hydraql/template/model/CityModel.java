package com.hydraql.template.model;

import com.hydraql.common.annotations.HBaseColumn;
import com.hydraql.common.annotations.HBaseRowKey;
import com.hydraql.common.annotations.HBaseTable;

import java.util.List;

/**
 * @author leojie 2022/11/5 13:56
 */
@HBaseTable(namespace = "default", tableName = "test_table", defaultFamily = "info")
public class CityModel {
    @HBaseRowKey
    private String cityId;
    private String cityName;
    private String cityAddress;

    @HBaseColumn(family = "detail")
    private Integer cityArea;
    @HBaseColumn(family = "detail", qualifier = "TOTAL_POPULATION")
    private Integer totalPopulation;
    @HBaseColumn(family = "detail", qualifier = "cityTagList")
    private List<CityTag> cityTagList;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public Integer getCityArea() {
        return cityArea;
    }

    public void setCityArea(Integer cityArea) {
        this.cityArea = cityArea;
    }

    public Integer getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(Integer totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public List<CityTag> getCityTagList() {
        return cityTagList;
    }

    public void setCityTagList(List<CityTag> cityTagList) {
        this.cityTagList = cityTagList;
    }

    @Override
    public String toString() {
        return "CityModel{" +
                "cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityAddress='" + cityAddress + '\'' +
                ", cityArea=" + cityArea +
                ", totalPopulation=" + totalPopulation +
                ", cityTagList=" + cityTagList +
                '}';
    }
}
