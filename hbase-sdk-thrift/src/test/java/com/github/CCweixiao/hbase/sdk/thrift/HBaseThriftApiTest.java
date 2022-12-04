package com.github.CCweixiao.hbase.sdk.thrift;

import com.github.CCweixiao.hbase.sdk.common.query.ScanQueryParamsBuilder;
import com.github.CCweixiao.hbase.sdk.thrift.model.CityModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class HBaseThriftApiTest extends BaseHBaseThriftApiTest {

    @Test
    public void testSavePOJO() {
        CityModel cityModel = createDefaultCityModel();
        try (HBaseThrift hBaseThrift = thriftPool.getResource()) {
            CityModel data = hBaseThrift.save(cityModel);
            Assert.assertEquals("a10001", data.getCityId());
        }
    }

    @Test
    public void testGetPOJO() {
        try (HBaseThrift hBaseThrift = thriftPool.getResource()) {
            Optional<CityModel> cityModelO = hBaseThrift.getRow("a10001", CityModel.class);
            Assert.assertTrue(cityModelO.isPresent());
            Assert.assertEquals("a10001", cityModelO.orElse(new CityModel()).getCityId());
            Assert.assertTrue(cityModelO.orElse(new CityModel()).getCityTagList().size() > 0);
            Optional<CityModel> cityModel1 = hBaseThrift.getRow("a100011", CityModel.class);
            Assert.assertFalse(cityModel1.isPresent());
        }
    }

    @Test
    public void testSaveBatchPOJO() {
        List<CityModel> defaultCityModelList = createDefaultCityModelList();
        try (HBaseThrift hBaseThrift = thriftPool.getResource()) {
            int rows = hBaseThrift.saveBatch(defaultCityModelList);
            Assert.assertEquals(4, rows);
        }
    }

    @Test
    public void testGetRowsToPOJO() {
        try (HBaseThrift hBaseThrift = thriftPool.getResource()) {
            List<CityModel> rows = hBaseThrift.getRows(Arrays.asList("a11001", "a110011"),
                    "info", CityModel.class);
            Assert.assertEquals(1, rows.size());
            List<CityModel> rows2 = hBaseThrift.getRows(Arrays.asList("a11001", "a12002"),
                    "info", Arrays.asList("city_name", "city_address"), CityModel.class);
            Assert.assertEquals(2, rows2.size());
        }
    }

    @Test
    public void testScanToPOJO() {
        ScanQueryParamsBuilder params = new ScanQueryParamsBuilder.Builder()
                .familyName("")
                .limit(2)
                .reversed(true)
                .build();
        try (HBaseThrift hBaseThrift = thriftPool.getResource()) {
            List<CityModel> models = hBaseThrift.scan(params, CityModel.class);
            Assert.assertEquals(2, models.size());
        }
    }

    @Test
    public void testSaveMap() {
        String t1 = "t1";
        String id = "100011";
        Map<String, Object> data = createDefaultDataMap();
        try (HBaseThrift hBaseThrift = thriftPool.getResource()) {
            hBaseThrift.save(t1, id, data);
        }
    }

    @Test
    public void testSaveBatchMap() {
        String t1 = "t1";
        Map<String, Map<String, Object>> data = createDefaultListDataMap();
        try (HBaseThrift hBaseThrift = thriftPool.getResource()) {
            int rows = hBaseThrift.saveBatch(t1, data);
            Assert.assertEquals(4, rows);
        }
    }

    @Test
    public void testGetRowToMap() {
        try (HBaseThrift hBaseThrift = thriftPool.getResource()) {
            Map<String, String> data = hBaseThrift.getRowToMap("t1", "100011", false);
            Assert.assertEquals("18", data.get("f1:age"));
        }
    }

    @Test
    public void testGetRowsToMap() {
        try (HBaseThrift hBaseThrift = thriftPool.getResource()) {
            Map<String, Map<String, String>> t1 =
                    hBaseThrift.getRowsToMap("t1", Arrays.asList("a100011", "b120011"), true);
            Assert.assertEquals(2, t1.size());
        }
    }

    @Test
    public void testScanToMap() {
        ScanQueryParamsBuilder params = new ScanQueryParamsBuilder.Builder()
                .familyName("")
                .limit(10)
                .reversed(true)
                .build();

        try (HBaseThrift hBaseThrift = thriftPool.getResource()) {
            List<Map<String, Map<String, String>>> data = hBaseThrift.scan("t1", params);
            Assert.assertEquals(9, data.size());
        }
    }

    @Test
    public void testDelete() {
        try (HBaseThrift hBaseThrift = thriftPool.getResource()) {
            hBaseThrift.delete("t1", "100011", "f1", "pay", "name");
        }
    }
}