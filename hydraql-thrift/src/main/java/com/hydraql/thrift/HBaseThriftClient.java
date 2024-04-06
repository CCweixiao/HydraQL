package com.hydraql.thrift;

import com.hydraql.common.constants.HMHBaseConstants;
import com.hydraql.common.exception.HBaseSdkUnsupportedFunctionException;
import com.hydraql.common.exception.HBaseThriftException;
import com.hydraql.common.lang.MyAssert;
import com.hydraql.common.mapper.RowMapper;
import com.hydraql.common.model.data.HBaseRowData;
import com.hydraql.common.model.data.HBaseRowDataWithMultiVersions;
import com.hydraql.common.query.GetRowParam;
import com.hydraql.common.query.GetRowsParam;
import com.hydraql.common.query.ScanParams;
import com.hydraql.common.meta.HBaseTableMeta;
import com.hydraql.common.meta.ReflectFactory;
import com.hydraql.common.type.ColumnType;
import com.hydraql.common.util.HBaseThriftProtocol;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.thrift.generated.*;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <p>HBase thrift client</p>
 *
 * @author leojie 2020/12/27 2:46 下午
 */
public class HBaseThriftClient extends BaseHBaseThriftClient implements IHBaseThriftOperations {
    private static final Logger LOG = LoggerFactory.getLogger(HBaseThriftClient.class);

    public HBaseThriftClient() {
        this(HBaseThriftProtocol.DEFAULT_HOST);
    }

    public HBaseThriftClient(final String host) {
        this(host, HBaseThriftProtocol.DEFAULT_PORT);
    }

    public HBaseThriftClient(final String host, final int port) {
        this(host, port, HBaseThriftProtocol.DEFAULT_TIMEOUT, HBaseThriftProtocol.DEFAULT_TIMEOUT);
    }

    public HBaseThriftClient(final String host, final int port, final int connectionTimeout, int socketTimeout) {
        this(new HBaseThriftTSocketImpl.Builder(host, port)
                .connectionTimeout(connectionTimeout)
                .socketTimeout(socketTimeout)
                .build());
    }

    public HBaseThriftClient(final IHBaseThriftTSocket thriftTSocketFactory) {
        super(thriftTSocketFactory);
    }

    @Override
    public void save(String tableName, String rowKey, Map<String, Object> data) {
        MyAssert.checkArgument(StringUtil.isNotBlank(tableName), "The table name must not be empty.");
        MyAssert.checkArgument(StringUtil.isNotBlank(rowKey), "The row key must not be empty.");
        if (data == null || data.isEmpty()) {
            return;
        }
        List<Mutation> mutations = new ArrayList<>(data.size());
        data.forEach((key, value) -> {
            checkFamilyAndQualifierName(key);
            mutations.add(new Mutation(false, ColumnType.toByteBufferFromStr(key),
                            ColumnType.toStrByteBuffer(value), true));
        });
        this.save(tableName, rowKey, mutations);
    }

    @Override
    public void saveBatch(String tableName, Map<String, Map<String, Object>> data) {
        MyAssert.checkArgument(StringUtil.isNotBlank(tableName), "The table name must not be empty.");
        if (data == null || data.isEmpty()) {
            return;
        }
        List<BatchMutation> batchMutations = new ArrayList<>(data.size());
        data.forEach((rowKey, colAndValMap) -> {
            MyAssert.checkArgument(StringUtil.isNotBlank(rowKey), "The row key must not be empty.");
            if (null != colAndValMap && !colAndValMap.isEmpty()) {
                List<Mutation> mutations = new ArrayList<>(colAndValMap.size());
                colAndValMap.forEach((col, value) -> {
                    checkFamilyAndQualifierName(col);
                    mutations.add(new Mutation(false,
                            ColumnType.toByteBuffer(col),
                            ColumnType.toStrByteBuffer(value), true));
                });

                batchMutations.add(new BatchMutation(ColumnType.toByteBuffer(rowKey), mutations));
            }
        });
        this.saveBatch(tableName, batchMutations);
    }

    @Override
    public <T> void save(T t) {
        this.pSave(t);
    }

    @Override
    public <T> void saveBatch(List<T> lst) {
        if (lst == null || lst.isEmpty()) {
            return;
        }
        final Class<?> clazz0 = lst.get(0).getClass();
        HBaseTableMeta tableMeta = ReflectFactory.getInstance().register(clazz0);
        List<BatchMutation> batchMutationList = this.createBatchMutationList(lst, tableMeta);
        this.saveBatch(tableMeta.getTableName(), batchMutationList);
    }

    @Override
    public <T> T getRow(GetRowParam getRowParam, Class<T> clazz) {
        String tableName = ReflectFactory.getInstance().register(clazz).getTableName();
        return this.execute(thriftClient -> {
            List<TRowResult> results = getToRowResultList(thriftClient, tableName, getRowParam);
            if (results == null || results.isEmpty()) {
                return null;
            }
            return mapperRowToT(results.get(0), clazz);
        });
    }


    @Override
    public <T> T getRow(String tableName, GetRowParam getRowParam, RowMapper<T> rowMapper) {
        return this.execute(thriftClient -> {
            List<TRowResult> results = getToRowResultList(thriftClient, tableName, getRowParam);
            if (results == null || results.isEmpty()) {
                return null;
            }
            return rowMapper.mapRow(results.get(0), 0);
        });
    }

    @Override
    public HBaseRowData getRow(String tableName, GetRowParam getRowParam) {
        return this.execute(thriftClient -> {
            List<TRowResult> results = getToRowResultList(thriftClient, tableName, getRowParam);
            return convertResultToHBaseColData(results.get(0));
        });
    }

    @Override
    public <T> List<T> getWithMultiVersions(GetRowParam getRowParam, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> List<T> getWithMultiVersions(String tableName, GetRowParam getRowParam, RowMapper<T> rowMapper) {
        return null;
    }

    @Override
    public HBaseRowDataWithMultiVersions getWithMultiVersions(String tableName, GetRowParam getRowParam) {
        return null;
    }

    @Override
    public <T> List<T> getRows(GetRowsParam getRowsParam, Class<T> clazz) {
        String tableName = ReflectFactory.getInstance().register(clazz).getTableName();
        return this.execute(thriftClient -> {
            List<TRowResult> results = getToRowsResultList(thriftClient, tableName, getRowsParam);
            return mapperRowToTList(results, clazz);
        });
    }

    @Override
    public <T> List<T> getRows(String tableName, GetRowsParam getRowsParams, RowMapper<T> rowMapper) {
        return this.execute(thriftClient -> {
            List<TRowResult> results = getToRowsResultList(thriftClient, tableName, getRowsParams);
            List<T> data = new ArrayList<>(results.size());
            for (TRowResult result : results) {
                data.add(rowMapper.mapRow(result, 0));
            }
            return data;
        });
    }

    @Override
    public List<HBaseRowData> getRows(String tableName, GetRowsParam getRowsParam) {
        return null;
    }

    @Override
    public <T> List<T> scan(ScanParams scanQueryParams, Class<T> clazz) {
        String tableName = ReflectFactory.getInstance().register(clazz).getTableName();
        int scannerId = scannerOpen(tableName, scanQueryParams, new HashMap<>(0));
        int limit = scanQueryParams.getLimit();

        AtomicInteger nReturned = new AtomicInteger();
        int nFetched = 0;
        int howMany;
        List<T> results = new ArrayList<>(scanQueryParams.getLimit());

        try {
            while (true) {
                if (limit <= 0) {
                    howMany = scanQueryParams.getBatch();
                } else {
                    howMany = Math.min(scanQueryParams.getBatch(), limit - nReturned.get());
                }
                final List<TRowResult> items = hbaseClient.scannerGetList(scannerId, howMany);
                if (items != null && !items.isEmpty()) {
                    nFetched += items.size();
                    for (TRowResult result : items) {
                        T t = mapperRowToT(result, clazz);
                        results.add(t);
                        nReturned.addAndGet(1);
                    }
                    if (nReturned.get() == limit) {
                        break;
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            throw new HBaseThriftException(e);
        } finally {
            try {
                hbaseClient.scannerClose(scannerId);
                LOG.debug("Closed scanner (id={}) on '{}' ({} returned, {} fetched)", scannerId, tableName, nReturned, nFetched);
            } catch (TException e) {
                LOG.error("close scanner id failed. ", e);
            }
        }
        return results;
    }

    @Override
    public <T> List<T> scan(String tableName, ScanParams scanQueryParams, RowMapper<T> rowMapper) {
        int scannerId = scannerOpen(tableName, scanQueryParams, new HashMap<>(0));
        int limit = scanQueryParams.getLimit();

        AtomicInteger nReturned = new AtomicInteger();
        int nFetched = 0;
        int howMany;
        List<T> results = new ArrayList<>(scanQueryParams.getLimit());

        try {
            while (true) {
                if (limit <= 0) {
                    howMany = scanQueryParams.getBatch();
                } else {
                    howMany = Math.min(scanQueryParams.getBatch(), limit - nReturned.get());
                }
                final List<TRowResult> items = hbaseClient.scannerGetList(scannerId, howMany);
                if (items != null && !items.isEmpty()) {
                    nFetched += items.size();
                    for (TRowResult result : items) {
                        T t = rowMapper.mapRow(result, nReturned.get());
                        results.add(t);
                        nReturned.addAndGet(1);
                    }
                    if (nReturned.get() == limit) {
                        break;
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            throw new HBaseThriftException(e);
        } finally {
            try {
                hbaseClient.scannerClose(scannerId);
                LOG.debug("Closed scanner (id={}) on '{}' ({} returned, {} fetched)", scannerId, tableName, nReturned, nFetched);
            } catch (TException e) {
                LOG.error("close scanner id failed. ", e);
            }
        }
        return results;
    }

    @Override
    public List<HBaseRowData> scan(String tableName, ScanParams scanQueryParams) {
        Map<String, String> attributes = new HashMap<>(0);

        int scannerId = scannerOpen(tableName, scanQueryParams, attributes);
        int limit = scanQueryParams.getLimit();

        AtomicInteger nReturned = new AtomicInteger();
        int nFetched = 0;
        int howMany;
        List<HBaseRowData> rowDataList = new ArrayList<>();

        try {
            while (true) {
                if (limit <= 0) {
                    howMany = scanQueryParams.getBatch();
                } else {
                    howMany = Math.min(scanQueryParams.getBatch(), limit - nReturned.get());
                }
                final List<TRowResult> items = hbaseClient.scannerGetList(scannerId, howMany);
                if (items != null && !items.isEmpty()) {
                    nFetched += items.size();
                    items.forEach(result -> {
                        rowDataList.add(convertResultToHBaseColData(result));
                        nReturned.addAndGet(1);
                    });
                    if (nReturned.get() == limit) {
                        break;
                    }
                } else {
                    break;
                }
            }
        } catch (TException e) {
            throw new HBaseThriftException(e);
        } finally {
            try {
                hbaseClient.scannerClose(scannerId);
                LOG.debug("Closed scanner (id={}) on '{}' ({} returned, {} fetched)", scannerId, tableName, nReturned, nFetched);
            } catch (TException e) {
                LOG.error("close scanner id failed. ", e);
            }
        }
        return rowDataList;
    }

    @Override
    public List<HBaseRowDataWithMultiVersions> scanWithMultiVersions(String tableName, ScanParams scanParams) {
        // todo
        return null;
    }

    @Override
    public <T> void delete(T t) {
        throw new HBaseSdkUnsupportedFunctionException("Unsupported function in hbase sdk.");
    }

    @Override
    public void delete(String tableName, String rowKey) {
        delete(tableName, rowKey, null, new ArrayList<>());
    }

    @Override
    public void delete(String tableName, String rowKey, String familyName) {
        delete(tableName, rowKey, familyName, new ArrayList<>());
    }

    @Override
    public void delete(String tableName, String rowKey, String familyName, List<String> qualifiers) {
        MyAssert.checkArgument(StringUtil.isNotBlank(tableName), "The table name must not be empty.");
        MyAssert.checkArgument(StringUtil.isNotBlank(rowKey), "The row key must not be empty.");
        if (StringUtil.isNotBlank(familyName)) {
            if (qualifiers != null && !qualifiers.isEmpty()) {
                List<Mutation> mutations = new ArrayList<>(qualifiers.size());
                for (String qualifier : qualifiers) {
                    mutations.add(new Mutation(true, ColumnType.toByteBuffer(familyName + ":" + qualifier),
                            null, true));
                }
                try {
                    hbaseClient.mutateRow(ColumnType.toByteBuffer(tableName),
                            ColumnType.toByteBuffer(rowKey),
                            mutations, getAttributesMap(new HashMap<>()));
                } catch (TException e) {
                    throw new HBaseThriftException(e);
                }
            } else {
                try {
                    hbaseClient.deleteAll(ColumnType.toByteBuffer(tableName),
                            ColumnType.toByteBuffer(rowKey),
                            ColumnType.toByteBuffer(familyName),
                            getAttributesMap(new HashMap<>()));
                } catch (TException e) {
                    throw new HBaseThriftException(e);
                }

            }
        } else {
            try {
                hbaseClient.deleteAllRow(ColumnType.toByteBuffer(tableName),
                        ColumnType.toByteBuffer(rowKey),
                        getAttributesMap(new HashMap<>()));
            } catch (TException e) {
                throw new HBaseThriftException(e);
            }
        }
    }

    @Override
    public void delete(String tableName, String rowKey, String familyName, String... qualifiers) {
        if (qualifiers != null && qualifiers.length > 0) {
            delete(tableName, rowKey, familyName, Arrays.asList(qualifiers));
        } else {
            delete(tableName, rowKey, familyName, new ArrayList<>());
        }
    }

    @Override
    public <T> void deleteBatch(List<T> list) {
        throw new HBaseSdkUnsupportedFunctionException("Unsupported function in hbase sdk.");
    }

    @Override
    public void deleteBatch(String tableName, List<String> rowKeys) {
        deleteBatch(tableName, rowKeys, null);
    }

    @Override
    public void deleteBatch(String tableName, List<String> rowKeys, String familyName) {
        deleteBatch(tableName, rowKeys, familyName, new ArrayList<>());
    }

    @Override
    public void deleteBatch(String tableName, List<String> rowKeys, String familyName, List<String> qualifiers) {
        MyAssert.checkArgument(StringUtil.isNotBlank(tableName), "The table name must not be empty.");
        MyAssert.checkArgument(rowKeys != null && !rowKeys.isEmpty(), "The row key list must not be empty.");

        List<BatchMutation> rowBatches = new ArrayList<>(rowKeys.size());

        if (StringUtil.isNotBlank(familyName)) {
            if (qualifiers != null && !qualifiers.isEmpty()) {
                rowKeys.forEach(rowKey -> {
                    List<Mutation> mutations = new ArrayList<>(rowKeys.size());
                    for (String qualifier : qualifiers) {
                        mutations.add(new Mutation(true, ColumnType.toByteBuffer(familyName + ":" + qualifier),
                                null, true));
                    }
                    BatchMutation batchMutation = new BatchMutation(ColumnType.toByteBuffer(rowKey), mutations);
                    rowBatches.add(batchMutation);
                });
            } else {
                rowKeys.forEach(rowKey -> {
                    List<Mutation> mutations = new ArrayList<>(rowKeys.size());
                    mutations.add(new Mutation(true, ColumnType.toByteBuffer(familyName), null, true));
                    BatchMutation batchMutation = new BatchMutation(ColumnType.toByteBuffer(rowKey), mutations);
                    rowBatches.add(batchMutation);
                });
            }
            try {
                hbaseClient.mutateRows(ColumnType.toByteBuffer(tableName), rowBatches, getAttributesMap(new HashMap<>()));
            } catch (TException e) {
                throw new HBaseThriftException(e);
            }
        } else {
            rowKeys.forEach(rowKey -> {
                try {
                    hbaseClient.deleteAllRow(ColumnType.toByteBuffer(tableName),
                            ColumnType.toByteBuffer(rowKey), getAttributesMap(new HashMap<>()));
                } catch (TException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void deleteBatch(String tableName, List<String> rowKeys, String familyName, String... qualifiers) {
        if (qualifiers != null && qualifiers.length > 0) {
            deleteBatch(tableName, rowKeys, familyName, Arrays.asList(qualifiers));
        } else {
            deleteBatch(tableName, rowKeys, familyName, new ArrayList<>());
        }
    }


    private int scannerOpen(String tableName, ScanParams scanQueryParams, Map<String, String> attributes) {
        MyAssert.checkArgument(StringUtil.isNotBlank(tableName), "The table name must not be empty.");
        TScan scan = buildScan(scanQueryParams);
        ByteBuffer tableNameByte = ColumnType.toByteBuffer(tableName);
        try {
            return hbaseClient.scannerOpenWithScan(tableNameByte, scan, getAttributesMap(attributes));
        } catch (TException e) {
            throw new HBaseThriftException(e);
        }
    }


    public List<String> getMetaTableRegions() {
        try {
            List<TRegionInfo> regions = hbaseClient.getTableRegions(ColumnType.toByteBufferFromStr(HMHBaseConstants.META_TABLE_NAME));
            return regions.stream().map(r -> ColumnType.toStrFromBuffer(r.bufferForName()))
                    .collect(Collectors.toList());
        } catch (TException e) {
            throw new HBaseThriftException(e);
        }
    }


    @Override
    public boolean ping() {
        return getMetaTableRegions().size() > 0;
    }


}

