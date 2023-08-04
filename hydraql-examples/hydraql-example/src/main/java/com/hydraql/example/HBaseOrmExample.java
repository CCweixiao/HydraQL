package com.hydraql.example;

import com.hydraql.common.model.example.CityModel;
import com.hydraql.common.query.GetRowParam;
import com.hydraql.template.BaseHBaseTableTemplate;
import com.hydraql.template.HBaseTableTemplate;
import java.util.Optional;
import java.util.Properties;

/**
 * @author leojie 2023/7/9 13:50
 */
public class HBaseOrmExample {
    public static void main(String[] args) {
        Properties properties = HBaseServiceExample.getProperties();
        BaseHBaseTableTemplate tableTemplate = HBaseTableTemplate.of(properties);
        tableTemplate.save(CityModel.createDefaultCityModel());
        Optional<CityModel> result = tableTemplate.getRow(GetRowParam.of("a10001").build(), CityModel.class);
        System.out.println(result.orElse(new CityModel()));
    }


}
