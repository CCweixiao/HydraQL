package com.github.CCweixiao.hbase.sdk.adapter;

import com.github.CCweixiao.hbase.sdk.common.IHBaseTableOperations;
import com.github.CCweixiao.hbase.sdk.common.constants.HMHBaseConstants;
import com.github.CCweixiao.hbase.sdk.common.model.data.HBaseColData;
import com.github.CCweixiao.hbase.sdk.common.model.data.HBaseRowData;
import com.github.CCweixiao.hbase.sdk.common.model.data.HBaseRowDataWithMultiVersions;
import com.github.CCweixiao.hbase.sdk.common.query.GetParams;
import com.github.CCweixiao.hbase.sdk.common.reflect.FieldStruct;
import com.github.CCweixiao.hbase.sdk.common.reflect.HBaseTableMeta;
import com.github.CCweixiao.hbase.sdk.common.reflect.ReflectFactory;
import com.github.CCweixiao.hbase.sdk.common.util.StringUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 定义HBase的数据操作接口
 *
 * @author leojie 2020/9/26 11:04 上午
 */
public interface IHBaseTableGetAdapter {
    /**
     * 传入GetParams对象，生成Get的接口方法，由具体的HBaseTableAdapterImpl类去实现
     *
     * @param getParams GetParams对象
     * @return Get
     */
    Get buildGet(GetParams getParams);

    HBaseRowData getToRowData(String tableName, Get get);


    /**
     * 利用反射，绑定查询结果集到定义的实体对象
     *
     * @param result {@link org.apache.hadoop.hbase.client.Result}
     * @param clazz  数据实体类的class
     * @param <T>    泛型类型
     * @return 绑定数据实体对象后的查询结果
     * @throws Exception 异常抛出
     */
    default <T> T mapperRowToT(Result result, Class<T> clazz) throws Exception {
        //TODO 这里的反射调用构造函数是否可以再优化
        T t = clazz.getDeclaredConstructor().newInstance();
        HBaseTableMeta hBaseTableMeta = ReflectFactory.getHBaseTableMeta(clazz);
        List<FieldStruct> fieldColStructMap = hBaseTableMeta.getFieldStructList();
        fieldColStructMap.forEach(fieldStruct -> {
            if (fieldStruct.isRowKey()) {
                Object rowKey = fieldStruct.getTypeHandler().toObject(fieldStruct.getType(), result.getRow());
                hBaseTableMeta.getMethodAccess().invoke(t, fieldStruct.getSetterMethodIndex(), rowKey);
            } else {
                byte[] valBytes = result.getValue(Bytes.toBytes(fieldStruct.getFamily()), Bytes.toBytes(fieldStruct.getQualifier()));
                Object value = fieldStruct.getTypeHandler().toObject(fieldStruct.getType(), valBytes);
                hBaseTableMeta.getMethodAccess().invoke(t, fieldStruct.getSetterMethodIndex(), value);
            }
        });
        return t;
    }

    default HBaseRowDataWithMultiVersions convertResultsToHBaseColDataListWithMultiVersion(Result result, int versions) {
        List<Cell> cells = result.listCells();
        if (cells == null || cells.isEmpty()) {
            return HBaseRowDataWithMultiVersions.empty();
        }

        HBaseRowDataWithMultiVersions.Builder colDataBuilder = HBaseRowDataWithMultiVersions.of(Bytes.toString(result.getRow()));
        List<HBaseColData> colDataList = new ArrayList<>(versions);
        StringBuilder preFieldSb = new StringBuilder();
        StringBuilder currentFieldSb = new StringBuilder();
        for (int i = 0; i < cells.size(); i++) {
            preFieldSb.delete(0, preFieldSb.length());
            currentFieldSb.delete(0, currentFieldSb.length());

            if (i > 0) {
                preFieldSb.append(Bytes.toString(CellUtil.cloneFamily(cells.get(i - 1))));
                preFieldSb.append(HMHBaseConstants.FAMILY_QUALIFIER_SEPARATOR);
                preFieldSb.append(Bytes.toString(CellUtil.cloneQualifier(cells.get(i - 1))));
            }
            currentFieldSb.append(Bytes.toString(CellUtil.cloneFamily(cells.get(i))));
            currentFieldSb.append(HMHBaseConstants.FAMILY_QUALIFIER_SEPARATOR);
            currentFieldSb.append(Bytes.toString(CellUtil.cloneQualifier(cells.get(i))));
            String value = Bytes.toString(cells.get(i).getValueArray(), cells.get(i).getValueOffset(), cells.get(i).getValueLength());
            HBaseColData colData = new HBaseColData(value, cells.get(i).getTimestamp());
            boolean fieldChange = StringUtil.isNotBlank(preFieldSb.toString()) &&
                    !currentFieldSb.toString().equals(preFieldSb.toString());
            if (fieldChange) {
                colDataBuilder = colDataBuilder.appendColData(preFieldSb.toString(), colDataList);
                colDataList = new ArrayList<>(versions);
            }
            colDataList.add(colData);

            if (i == cells.size() - 1) {
                colDataBuilder = colDataBuilder.appendColData(currentFieldSb.toString(), colDataList);
            }
        }

        return colDataBuilder.build();
    }

    default HBaseRowData convertResultToHBaseColData(Result result) {
        List<Cell> cells = result.listCells();
        if (cells == null || cells.isEmpty()) {
            return HBaseRowData.empty();
        }
        HBaseRowData.Builder builder = HBaseRowData.of(Bytes.toString(result.getRow()));
        StringBuilder colNameSb = new StringBuilder();
        for (Cell cell : cells) {
            colNameSb.delete(0, colNameSb.length());
            colNameSb.append(Bytes.toString(CellUtil.cloneFamily(cell)));
            colNameSb.append(HMHBaseConstants.FAMILY_QUALIFIER_SEPARATOR);
            colNameSb.append(Bytes.toString(CellUtil.cloneQualifier(cell)));
            String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
            builder = builder.appendColData(colNameSb.toString(), value, cell.getTimestamp());
        }
        return builder.build();
    }

    default Result checkGetAndReturnResult(Get get, Table table) throws IOException {
        if (get == null) {
            return null;
        }
        Result result = table.get(get);
        if (result == null || result.getRow() == null) {
            return null;
        }
        return result;
    }

    default Result[] checkBatchGetAndReturnResult(List<Get> gets, Table table) throws IOException {
        if (gets == null || gets.isEmpty()) {
            return null;
        }
        Result[] results = table.get(gets);
        if (results == null || results.length == 0) {
            return null;
        }
        return results;
    }

    default List<Get> buildBatchGetCondition(List<String> rowKeys, String familyName, List<String> qualifiers) {
        if (null == rowKeys || rowKeys.isEmpty()) {
            return new ArrayList<>();
        }
        return rowKeys.stream().distinct().map(rowKey -> buildGetCondition(rowKey, familyName, qualifiers, 1))
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    default Get buildGetCondition(String rowKey, String familyName, List<String> qualifiers) {
        return this.buildGetCondition(rowKey, familyName, qualifiers, 1, 0, 0, 0);
    }

    default Get buildGetCondition(String rowKey, String familyName, List<String> qualifiers, int versions) {
        return this.buildGetCondition(rowKey, familyName, qualifiers, versions, 0, 0, 0);
    }

    default Get buildGetCondition(String rowKey, String familyName, List<String> qualifiers, long ts) {
        return this.buildGetCondition(rowKey, familyName, qualifiers, 1, ts, 0, 0);
    }

    default Get buildGetCondition(String rowKey, String familyName, List<String> qualifiers,
                                  int versions, long ts, long minTs, long maxTs) {
        GetParams.Builder builder = GetParams.builder(rowKey);
        if (ts > 0) {
            builder.withTimestamp(ts);
        }
        if (minTs > 0 && maxTs > 0) {
            builder.withTimeRange(minTs, maxTs);
        }
        if (versions <= 0) {
            versions = 1;
        }
        GetParams getParams = builder
                .familyName(familyName)
                .columnNames(qualifiers)
                .versions(versions)
                .build();
        return this.buildGet(getParams);
    }
}
