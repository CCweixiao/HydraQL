package com.hydraql.template;

import com.hydraql.common.mapper.RowMapper;
import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.model.data.HBaseRowDataWithMultiVersions;
import com.hydraql.common.query.GetRowParam;
import com.hydraql.common.query.GetRowsParam;
import com.hydraql.common.query.IHBaseFilter;
import com.hydraql.common.query.ScanParams;
import com.hydraql.template.model.CityModel;
import com.hydraql.template.model.CityModelUtil;
import com.hydraql.tests.common.HydraQlBaseTestTemplate;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hydraql.tests.common.HydraQlBaseTestConstants.*;

/**
 * @author leojie 2023/8/4 22:53
 */
public class HBaseTableTestTemplateTest extends HydraQlBaseTestTemplate {
    private HBaseTableTemplate tableTemplate;

    @Before
    public void setup() throws Exception {
        startMiniCluster();
        tableTemplate = HBaseTableTemplate.of(getConfiguration());
        createTestTable();
        createTestMultiVersionTable();
    }

    @After
    public void destroy() throws Exception {
        shutdownMiniCluster();
    }

    @Test
    public void testSaveMap() {
        Map<String, Object> data = new HashMap<>(2);
        data.put("f1:name", "leo");
        data.put("f1:age", 18);
        tableTemplate.save(TEST_TABLE, "1001", data);
        HBaseRowData rowData = tableTemplate.getRow(TEST_TABLE, GetRowParam.of("1001").build());
        Assert.assertEquals(2, rowData.getColDataContainer().size());
    }

    @Test
    public void testDelete() {
        testSaveMap();
        tableTemplate.delete(TEST_TABLE, "1001", F1, "name", "age");
        HBaseRowData row = tableTemplate.getRow(TEST_TABLE, GetRowParam.of("1001").build());
        Assert.assertTrue(row.getColDataContainer().isEmpty());
    }

    @Test
    public void testSaveBatchMap() {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("f1:name", "leo1");
        data1.put("f1:age", 17);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("f1:name", "leo2");
        data2.put("f1:age", 18);

        Map<String, Map<String, Object>> data = new HashMap<>(2);
        data.put("1001", data1);
        data.put("1002", data2);

        tableTemplate.saveBatch(TEST_TABLE, data);
        GetRowsParam getRowsParam = GetRowsParam.of().appendRowKey("1001")
                .appendRowKey("1002").build();
        List<HBaseRowData> rowDataList = tableTemplate.getRows(TEST_TABLE, getRowsParam);
        Assert.assertEquals(2, rowDataList.size());
    }

    @Test
    public void testDeleteBatch() {
        testSaveBatchMap();
        tableTemplate.deleteBatch(TEST_TABLE, Arrays.asList("1001", "1002"));
        GetRowsParam getRowsParam = GetRowsParam.of().appendRowKey("1001")
                .appendRowKey("1002").build();
        List<HBaseRowData> rowDataList = tableTemplate.getRows(TEST_TABLE, getRowsParam);
        Assert.assertEquals(0, rowDataList.size());
    }

    @Test
    public void testSave() {
        CityModel cityModel = CityModelUtil.createDefaultCityModel();
        tableTemplate.save(cityModel);
        GetRowParam getRowParam = GetRowParam.of(cityModel.getCityId()).build();
        CityModel cityModelRes = tableTemplate.getRow(getRowParam, CityModel.class);
        Assert.assertNotNull(cityModelRes);
    }

    @Test
    public void testSaveBatch() {
        List<CityModel> cityModelList = CityModelUtil.createDefaultCityModelList();
        tableTemplate.saveBatch(cityModelList);
        List<String> rowKeys = cityModelList.stream().map(CityModel::getCityId)
                .collect(Collectors.toList());

        GetRowsParam getRowsParam = GetRowsParam.of().rowKeyList(rowKeys).build();
        List<CityModel> rows = tableTemplate.getRows(getRowsParam, CityModel.class);
        Assert.assertEquals(rowKeys.size(), rows.size());
    }

    @Test
    public void testGetByRowMapper() {
        testSave();
        GetRowParam getRowParam = GetRowParam.of("a10001").build();
        Map<String, String> data = tableTemplate.getRow(TEST_TABLE, getRowParam, new RowMapper<Map<String, String>>() {
            @Override
            public <R> Map<String, String> mapRow(R r, int rowNum) throws Exception {
                Result result = (Result) r;
                if (result == null) {
                    return new HashMap<>(0);
                }
                Map<String, String> data = new HashMap<>(2);
                for (Cell cell : result.listCells()) {
                    data.put(Bytes.toString(CellUtil.cloneFamily(cell)) + ":" +
                                    Bytes.toString(CellUtil.cloneQualifier(cell)),
                            Bytes.toString(CellUtil.cloneValue(cell)));
                }
                return data;
            }
        });
        Assert.assertEquals(5, data.size());
    }


    @Test
    public void testScanWithCustomFilter() {
        testSaveBatch();
        ScanParams scanParams = ScanParams.of()
                .filter(new IHBaseFilter<Filter>() {
                    @Override
                    public Filter customFilter() {
                        List<Filter> filters = new ArrayList<>(2);
                        // 筛选row key 大于b20001的数据
                        Filter rowFilter = new RowFilter(CompareFilter.CompareOp.GREATER,
                                new BinaryComparator("b20001".getBytes()));
                        // 筛选列前缀city_address的数据
                        ColumnPrefixFilter columnPrefixFilter = new ColumnPrefixFilter(Bytes.toBytes("city_address"));
                        // 筛选列值与深圳市相等的数据
                        ValueFilter valueFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("深圳市")));
                        // 多过滤器，注意顺序
                        filters.add(rowFilter);
                        filters.add(columnPrefixFilter);
                        filters.add(valueFilter);
                        // 需所有条件全部通过
                        FilterList andFilterList = new FilterList(FilterList.Operator.MUST_PASS_ALL, filters);
                        // 满足其中一个条件即可
                        FilterList orFilterList = new FilterList(FilterList.Operator.MUST_PASS_ONE, filters);
                        return andFilterList;
                    }
                })
                .build();
        List<CityModel> cityModels = tableTemplate.scan(scanParams, CityModel.class);
        Assert.assertEquals(1, cityModels.size());
    }


    @Test
    public void testGetRowToList() {
        tableTemplate.delete(TEST_TABLE, "a1000112");
        List<CityModel> defaultCityModelList = CityModelUtil.createDefaultMultiVersionsCityModelList();
        for (CityModel cityModel : defaultCityModelList) {
            tableTemplate.save(cityModel);
        }
        GetRowParam getRowParam = GetRowParam.of("a1000112").versions(5).build();
        List<CityModel> cityModels = tableTemplate.getWithMultiVersions(getRowParam, CityModel.class);
        Assert.assertEquals(5, cityModels.size());
    }

    @Test
    public void testGetMultiVersions() throws InterruptedException {
        Map<String, Object> data = new HashMap<>();
        data.put("f1:name", "leo");
        data.put("f1:age", 17);
        for (int i = 0; i < 5; i++) {
            tableTemplate.save(TEST_TABLE_WITH_MULTI_VERSION,"1001", data);
            Thread.sleep(500);
        }

        GetRowParam getRowParam = GetRowParam.of("1001").versions(5).build();
        HBaseRowDataWithMultiVersions rowData = tableTemplate.getWithMultiVersions(TEST_TABLE_WITH_MULTI_VERSION, getRowParam);

        Assert.assertEquals(2, rowData.getColDataWithMultiVersions().size());
        Assert.assertEquals(5, rowData.getColDataWithMultiVersions().get("f1:name").size());
    }

    @Test
    public void testScan() {
        testSaveBatchMap();
        ScanParams scanParams = ScanParams.of()
                .startRow("1001")
                .inclusiveStartRow(true)
                .stopRow("1002")
                .inclusiveStopRow(true)
                .caching(100)
                .build();
        List<HBaseRowData> rowDataList = tableTemplate.scan(TEST_TABLE, scanParams);
        Assert.assertFalse(rowDataList.isEmpty());
    }

    @Test
    public void testScanWithMultiVersions() throws InterruptedException {
        Map<String, Object> data = new HashMap<>();
        data.put("f1:name", "leo");
        data.put("f1:age", 17);
        for (int i = 0; i < 5; i++) {
            tableTemplate.save(TEST_TABLE,"1001", data);
            Thread.sleep(500);
        }

        ScanParams scanParams = ScanParams.of().startRow("b1").stopRow("b1").versions(5).build();
        List<HBaseRowDataWithMultiVersions> hBaseRowDataWithMultiVersions =
                tableTemplate.scanWithMultiVersions(TEST_TABLE, scanParams);
        Assert.assertEquals(1, hBaseRowDataWithMultiVersions.size());
    }

}
