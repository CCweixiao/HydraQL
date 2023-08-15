package com.hydraql.template.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leojie 2023/8/4 23:49
 */
public class CityModelUtil {
    public static CityModel createDefaultCityModel() {
        return cityModel("a10001", "北京", "北京市", 1000000, 40000000,
                Arrays.asList("首都", "旅游城市"));
    }

    public static List<CityModel> createDefaultCityModelList() {
        List<CityModel> cityModels = new ArrayList<>(4);
        cityModels.add(cityModel("a10001", "北京", "北京市", 1000000, 40000000,
                Arrays.asList("首都", "旅游城市")));
        cityModels.add(cityModel("a10002", "上海", "上海市", 1000000, 20000000,
                Arrays.asList("魔都", "旅游城市")));
        cityModels.add(cityModel("b20001", "广州", "广州市", 1000000, 40000000,
                Arrays.asList("沿海城市", "旅游城市")));
        cityModels.add(cityModel("b20002", "深圳", "深圳市", 1000000, 40000000,
                Arrays.asList("沿海城市", "发达地区")));
        return cityModels;
    }

    public static List<CityModel> createDefaultMultiVersionsCityModelList() {
        List<CityModel> cityModels = new ArrayList<>(4);
        cityModels.add(cityModel("a1000112", "北京", "北京市", 1000001, 40000001,
                Arrays.asList("首都", "旅游城市")));
        cityModels.add(cityModel("a1000112", "上海", "上海市", 1000002, 20000002,
                Arrays.asList("魔都", "旅游城市")));
        cityModels.add(cityModel("a1000112", "广州", "广州市", 1000003, 40000003,
                null));
        cityModels.add(cityModel("a1000112", "深圳", "深圳市", 1000004, 40000004,
                Arrays.asList("沿海城市", "发达地区")));
        return cityModels;
    }

    public static CityModel cityModel(String cityId, String cityName, String cityAddress, int cityArea, int totalPopulation,
                                List<String> tagNameList) {
        CityModel cityModel = new CityModel();
        cityModel.setCityId(cityId);
        cityModel.setCityName(cityName);
        cityModel.setCityAddress(cityAddress);
        cityModel.setCityArea(cityArea);
        cityModel.setTotalPopulation(totalPopulation);
        if (tagNameList != null) {
            cityModel.setCityTagList(tagNameList.stream().map(CityTag::new).collect(Collectors.toList()));
        }
        return cityModel;
    }
}
