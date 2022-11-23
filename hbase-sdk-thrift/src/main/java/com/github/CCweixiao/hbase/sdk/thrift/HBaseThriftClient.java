package com.github.CCweixiao.hbase.sdk.thrift;

import com.github.CCweixiao.hbase.sdk.common.constants.HMHBaseConstants;
import com.github.CCweixiao.hbase.sdk.common.exception.HBaseThriftException;
import com.github.CCweixiao.hbase.sdk.common.lang.Assert;
import com.github.CCweixiao.hbase.sdk.common.mapper.RowMapper;
import com.github.CCweixiao.hbase.sdk.common.query.ScanQueryParamsBuilder;
import com.github.CCweixiao.hbase.sdk.common.reflect.HBaseTableMeta;
import com.github.CCweixiao.hbase.sdk.common.reflect.ReflectFactory;
import com.github.CCweixiao.hbase.sdk.common.type.TypeHandlerFactory;
import com.github.CCweixiao.hbase.sdk.common.util.ByteBufferUtil;
import com.github.CCweixiao.hbase.sdk.common.util.HBaseThriftProtocol;
import com.github.CCweixiao.hbase.sdk.common.util.StrUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.thrift.generated.*;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
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
public class HBaseThriftClient extends HBaseThriftConnection implements IHBaseThriftOperations {
    private static final Logger LOG = LoggerFactory.getLogger(HBaseThriftClient.class);

    private Hbase.Client hbaseClient;

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
        this(new DefaultHBaseThriftTSocketFactory(host, port, connectionTimeout, socketTimeout));
    }

    public HBaseThriftClient(final HBaseThriftTSocketFactory thriftTSocketFactory) {
        super(thriftTSocketFactory);
    }

    @Override
    public void connect() {
        super.connect();
        TSocket socket = getSocket();
        LOG.info("connecting hbase thrift server {}:{}, and local port is {} ", getHost(), getPort(), socket.getSocket().getLocalPort());
        TProtocol protocol = new TBinaryProtocol(socket, true, true);
        hbaseClient = new Hbase.Client(protocol);
    }

    @Override
    public Hbase.Client getHbaseThriftClient() {
        return hbaseClient;
    }

    @Override
    public void save(String tableName, String rowKey, Map<String, Object> data) {
        Assert.checkArgument(StrUtil.isNotBlank(tableName), "The table name must not be empty.");
        Assert.checkArgument(StrUtil.isNotBlank(rowKey), "Row key must not be empty.");
        if (data == null || data.isEmpty()) {
            return;
        }
        List<Mutation> mutations = new ArrayList<>(data.size());
        data.forEach((key, value) -> mutations.add(
                new Mutation(false, ByteBufferUtil.toByterBufferFromStr(key),
                        ByteBufferUtil.toStrByteBuffer(value), true)));
        this.save(tableName, rowKey, mutations);
    }

    @Override
    public int saveBatch(String tableName, Map<String, Map<String, Object>> data) {
        Assert.checkArgument(StrUtil.isNotBlank(tableName), "The table name must not be empty.");
        if (data == null || data.isEmpty()) {
            return 0;
        }
        List<BatchMutation> batchMutations = new ArrayList<>(data.size());
        data.forEach((rowKey, colAndValMap) -> {
            Assert.checkArgument(StrUtil.isNotBlank(rowKey), "Row key must not be empty.");
            if (null != colAndValMap && !colAndValMap.isEmpty()) {
                List<Mutation> mutations = new ArrayList<>(colAndValMap.size());
                colAndValMap.forEach((col, value) -> mutations.add(new Mutation(false,
                        ByteBufferUtil.toByteBuffer(col),
                        ByteBufferUtil.toStrByteBuffer(value), true)));

                batchMutations.add(new BatchMutation(ByteBufferUtil.toByteBuffer(rowKey), mutations));
            }
        });
        return this.saveBatch(tableName, batchMutations);
    }

    @Override
    public <T> T save(T t) throws Exception {
        return this.pSave(t);
    }

    @Override
    public <T> int saveBatch(List<T> lst) throws Exception {
        if (lst == null || lst.isEmpty()) {
            return 0;
        }
        final Class<?> clazz0 = lst.get(0).getClass();
        HBaseTableMeta tableMeta = ReflectFactory.getHBaseTableMeta(clazz0);
        List<BatchMutation> batchMutationList = this.createBatchMutationList(lst, tableMeta);
        return saveBatch(tableMeta.getTableName(), batchMutationList);
    }

    @Override
    public <T> Optional<T> getRow(String rowKey, Class<T> clazz) {
        return getRow(rowKey, "", new ArrayList<>(0), clazz);
    }

    @Override
    public <T> Optional<T> getRow(String rowKey, String familyName, Class<T> clazz) {
        return getRow(rowKey, familyName, new ArrayList<>(0), clazz);
    }

    @Override
    public <T> Optional<T> getRow(String rowKey, String familyName, List<String> qualifiers, Class<T> clazz) {
        String tableName = ReflectFactory.getHBaseTableMeta(clazz).getTableName();
        return this.execute(tableName, thriftClient -> {
            List<TRowResult> results = getToRowResultList(thriftClient, tableName, rowKey, familyName, qualifiers);
            return mapperRowToT(results.get(0), clazz);
        });
    }

    @Override
    public <T> Optional<T> getRow(String tableName, String rowKey, RowMapper<T> rowMapper) {
        return getRow(tableName, rowKey, "", new ArrayList<>(0), rowMapper);
    }

    @Override
    public <T> Optional<T> getRow(String tableName, String rowKey, String familyName, RowMapper<T> rowMapper) {
        return getRow(tableName, rowKey, familyName, new ArrayList<>(0), rowMapper);
    }

    @Override
    public <T> Optional<T> getRow(String tableName, String rowKey, String familyName, List<String> qualifiers, RowMapper<T> rowMapper) {
        return this.execute(tableName, thriftClient -> {
            List<TRowResult> results = getToRowResultList(thriftClient, tableName, rowKey, familyName, qualifiers);
            if (results == null || results.isEmpty()) {
                return null;
            }
            return rowMapper.mapRow(results.get(0), 0);
        });
    }

    @Override
    public Map<String, String> getRowToMap(String tableName, String rowKey, boolean withTimestamp) {
        return getRowToMap(tableName, rowKey, "", new ArrayList<>(0), withTimestamp);
    }

    @Override
    public Map<String, String> getRowToMap(String tableName, String rowKey, String familyName, boolean withTimestamp) {
        return getRowToMap(tableName, rowKey, familyName, new ArrayList<>(0), withTimestamp);
    }

    @Override
    public Map<String, String> getRowToMap(String tableName, String rowKey, String familyName, List<String> qualifiers, boolean withTimestamp) {
        return this.execute(tableName, thriftClient -> {
            List<TRowResult> results = getToRowResultList(thriftClient, tableName, rowKey, familyName, qualifiers);
            return parseResultsToMap(results.get(0));
        }).orElse(new HashMap<>(0));
    }

    @Override
    public <T> List<T> getRows(List<String> rowKeys, Class<T> clazz) {
        return getRows(rowKeys, "", new ArrayList<>(0), clazz);
    }

    @Override
    public <T> List<T> getRows(List<String> rowKeys, String familyName, Class<T> clazz) {
        return getRows(rowKeys, familyName, new ArrayList<>(0), clazz);
    }

    @Override
    public <T> List<T> getRows(List<String> rowKeys, String familyName, List<String> qualifiers, Class<T> clazz) {
        String tableName = ReflectFactory.getHBaseTableMeta(clazz).getTableName();
        return this.execute(tableName, thriftClient -> {
            List<TRowResult> results = getToRowsResultList(thriftClient, tableName, rowKeys, familyName, qualifiers);
            return mapperRowToTList(results, clazz);
        }).orElse(new ArrayList<>(0));
    }

    @Override
    public <T> List<T> getRows(String tableName, List<String> rowKeys, RowMapper<T> rowMapper) {
        return getRows(tableName, rowKeys, "", new ArrayList<>(0), rowMapper);
    }

    @Override
    public <T> List<T> getRows(String tableName, List<String> rowKeys, String familyName, RowMapper<T> rowMapper) {
        return getRows(tableName, rowKeys, familyName, new ArrayList<>(0), rowMapper);
    }

    @Override
    public <T> List<T> getRows(String tableName, List<String> rowKeys, String familyName, List<String> qualifiers, RowMapper<T> rowMapper) {
        return this.execute(tableName, thriftClient -> {
            List<TRowResult> results = getToRowsResultList(thriftClient, tableName, rowKeys, familyName, qualifiers);
            List<T> data = new ArrayList<>(results.size());
            for (TRowResult result : results) {
                data.add(rowMapper.mapRow(result, 0));
            }
            return data;
        }).orElse(new ArrayList<>(0));
    }

    @Override
    public Map<String, Map<String, String>> getRowsToMap(String tableName, List<String> rowKeys, boolean withTimestamp) {
        return getRowsToMap(tableName, rowKeys, "", new ArrayList<>(0), withTimestamp);
    }

    @Override
    public Map<String, Map<String, String>> getRowsToMap(String tableName, List<String> rowKeys, String familyName, boolean withTimestamp) {
        return getRowsToMap(tableName, rowKeys, familyName, new ArrayList<>(0), withTimestamp);
    }

    @Override
    public Map<String, Map<String, String>> getRowsToMap(String tableName, List<String> rowKeys, String familyName, List<String> qualifiers, boolean withTimestamp) {
        return this.execute(tableName, thriftClient -> {
            List<TRowResult> results = getToRowsResultList(thriftClient, tableName, rowKeys, familyName, qualifiers);
            return parseResultsToMap(results);
        }).orElse(new HashMap<>(0));
    }

    @Override
    public <T> List<T> scan(ScanQueryParamsBuilder scanQueryParams, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> List<T> scan(String tableName, ScanQueryParamsBuilder scanQueryParams, RowMapper<T> rowMapper) {
        return null;
    }

    @Override
    public List<Map<String, Map<String, String>>> scan(String tableName, ScanQueryParamsBuilder scanQueryParams) {
        return null;
    }

    @Override
    public List<Map<String, Map<String, String>>> scan(String tableName, String startRow, String stopRow,
                                                       String rowPrefix, String familyName,
                                                       List<String> qualifiers, String filterStr,
                                                       Long timestamp, Integer batchSize,
                                                       Integer scanBatching, boolean reverse, Integer limit) {
        Map<String, String> attributes = new HashMap<>();

        int scannerId = scannerOpen(tableName, startRow, stopRow, rowPrefix, familyName,
                qualifiers, filterStr, timestamp, batchSize, scanBatching, reverse, attributes);
        LOG.debug("Opened scanner (id={}) on '{}'", scannerId, tableName);
        if (limit != null && limit < 0) {
            throw new HBaseThriftException("'limit' must be >= 0");
        }

        AtomicInteger nReturned = new AtomicInteger();
        int nFetched = 0;
        int howMany;
        List<Map<String, Map<String, String>>> results = new ArrayList<>();

        try {
            while (true) {
                if (null == limit) {
                    howMany = batchSize;
                } else {
                    howMany = Math.min(batchSize, limit - nReturned.get());
                }
                final List<TRowResult> items = hbaseClient.scannerGetList(scannerId, howMany);
                if (items != null && !items.isEmpty()) {
                    nFetched += items.size();
                    items.forEach(scannerResult -> {
                        Map<String, Map<String, String>> data = new HashMap<>();
                        Map<String, String> tmpValue = new HashMap<>();
                        scannerResult.columns.forEach((colName, value) -> tmpValue.put(ByteBufferUtil.byteBufferToString(colName), ByteBufferUtil.byteBufferToString(value.value)));
                        data.put(ByteBufferUtil.byteBufferToString(scannerResult.row), tmpValue);
                        results.add(data);
                        nReturned.addAndGet(1);
                    });
                    if (limit != null && nReturned.get() == limit) {
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
        return results;
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
        if (StrUtil.isBlank(tableName)) {
            throw new HBaseThriftException("table name is blank");
        }
        if (StrUtil.isBlank(rowKey)) {
            throw new HBaseThriftException("row key is blank");
        }
        if (StrUtil.isNotBlank(familyName)) {
            if (qualifiers != null && !qualifiers.isEmpty()) {
                List<Mutation> mutations = new ArrayList<>(qualifiers.size());
                for (String qualifier : qualifiers) {
                    mutations.add(new Mutation(true, ByteBufferUtil.toByteBuffer(familyName + ":" + qualifier),
                            null, true));
                }
                try {
                    hbaseClient.mutateRow(ByteBufferUtil.toByteBuffer(tableName),
                            ByteBufferUtil.toByteBuffer(rowKey),
                            mutations, getAttributesMap(new HashMap<>()));
                } catch (TException e) {
                    throw new HBaseThriftException(e);
                }
            } else {
                try {
                    hbaseClient.deleteAll(ByteBufferUtil.toByteBuffer(tableName),
                            ByteBufferUtil.toByteBuffer(rowKey),
                            ByteBufferUtil.toByteBuffer(familyName),
                            getAttributesMap(new HashMap<>()));
                } catch (TException e) {
                    throw new HBaseThriftException(e);
                }

            }
        } else {
            try {
                hbaseClient.deleteAllRow(ByteBufferUtil.toByteBuffer(tableName),
                        ByteBufferUtil.toByteBuffer(rowKey),
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
    public void deleteBatch(String tableName, List<String> rowKeys) {
        deleteBatch(tableName, rowKeys, null);
    }

    @Override
    public void deleteBatch(String tableName, List<String> rowKeys, String familyName) {
        deleteBatch(tableName, rowKeys, familyName, new ArrayList<>());
    }

    @Override
    public void deleteBatch(String tableName, List<String> rowKeys, String familyName, List<String> qualifiers) {
        if (StrUtil.isBlank(tableName)) {
            throw new HBaseThriftException("table name is blank");
        }
        if (rowKeys == null || rowKeys.isEmpty()) {
            throw new HBaseThriftException("row keys are empty");
        }

        List<BatchMutation> rowBatches = new ArrayList<>(rowKeys.size());

        if (StrUtil.isNotBlank(familyName)) {
            if (qualifiers != null && !qualifiers.isEmpty()) {
                rowKeys.forEach(rowKey -> {
                    List<Mutation> mutations = new ArrayList<>(rowKeys.size());
                    for (String qualifier : qualifiers) {
                        mutations.add(new Mutation(true, ByteBufferUtil.toByteBuffer(familyName + ":" + qualifier),
                                null, true));
                    }
                    BatchMutation batchMutation = new BatchMutation(ByteBufferUtil.toByteBuffer(rowKey), mutations);
                    rowBatches.add(batchMutation);
                });
            } else {
                rowKeys.forEach(rowKey -> {
                    List<Mutation> mutations = new ArrayList<>(rowKeys.size());
                    mutations.add(new Mutation(true, ByteBufferUtil.toByteBuffer(familyName), null, true));
                    BatchMutation batchMutation = new BatchMutation(ByteBufferUtil.toByteBuffer(rowKey), mutations);
                    rowBatches.add(batchMutation);
                });
            }
            try {
                hbaseClient.mutateRows(ByteBufferUtil.toByteBuffer(tableName), rowBatches, getAttributesMap(new HashMap<>()));
            } catch (TException e) {
                throw new HBaseThriftException(e);
            }
        } else {
            rowKeys.forEach(rowKey -> {
                try {
                    hbaseClient.deleteAllRow(ByteBufferUtil.toByteBuffer(tableName),
                            ByteBufferUtil.toByteBuffer(rowKey), getAttributesMap(new HashMap<>()));
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


    private int scannerOpen(String tableName, ScanQueryParamsBuilder scanQueryParams, Map<String, String> attributes) {
        if (StrUtil.isBlank(tableName)) {
            throw new HBaseThriftException("table name is not empty.");
        }

        if (batchSize != null && batchSize < 1) {
            throw new HBaseThriftException("'batchSize' must be >= 1");
        }

        if (scanBatching != null && scanBatching < 1) {
            throw new HBaseThriftException("'scanBatching' must be >= 1");
        }

        if (StrUtil.isNotBlank(rowPrefix)) {
            if (StrUtil.isNotBlank(startRow) || StrUtil.isNotBlank(stopRow)) {
                throw new HBaseThriftException("'rowPrefix' cannot be combined with 'startRow' or 'stopRow'");
            }

            if (reverse) {
                startRow = ByteBufferUtil.bytesIncrement(rowPrefix);
                stopRow = rowPrefix;
            } else {
                startRow = rowPrefix;
                stopRow = ByteBufferUtil.bytesIncrement(rowPrefix);
            }
        }

        TScan scan = new TScan();
        if (StrUtil.isNotBlank(startRow)) {
            scan.setStartRow(ByteBufferUtil.toByteBuffer(startRow));
        }
        if (StrUtil.isNotBlank(stopRow)) {
            scan.setStopRow(ByteBufferUtil.toByteBuffer(stopRow));
        }

        if (StrUtil.isNotBlank(familyName)) {
            if (qualifiers != null && !qualifiers.isEmpty()) {
                final List<ByteBuffer> columns = qualifiers.stream()
                        .filter(StrUtil::isNotBlank)
                        .map(qualifier -> ByteBufferUtil.toByteBuffer(familyName + ":" + qualifier))
                        .collect(Collectors.toList());
                scan.setColumns(columns);
            } else {
                scan.setColumns(Collections.singletonList(ByteBufferUtil.toByteBuffer(familyName)));
            }
        }

        if (timestamp != null && timestamp > 0) {
            scan.setTimestamp(timestamp);
        }

        if (scanQueryParams.getFilter() != null && scanQueryParams.getFilter().customFilter() instanceof String) {
            scan.setFilterString(ByteBufferUtil.toStrByteBuffer(scanQueryParams.getFilter().customFilter()));
        }

        if (batchSize != null) {
            scan.setCaching(batchSize);
        }

        if (scanBatching != null) {
            scan.setBatchSize(scanBatching);
        }

        scan.setReversed(reverse);

        ByteBuffer tableNameByte = ByteBufferUtil.toByteBuffer(tableName);
        try {
            return hbaseClient.scannerOpenWithScan(tableNameByte, scan, getAttributesMap(attributes));
        } catch (TException e) {
            throw new HBaseThriftException(e);
        }
    }


    public List<String> getMetaTableRegions() {
        try {
            List<TRegionInfo> regions = hbaseClient.getTableRegions(ByteBufferUtil.toByterBufferFromStr(HMHBaseConstants.META_TABLE_NAME));
            return regions.stream().map(r -> TypeHandlerFactory.toStrFromBuffer(r.bufferForName()))
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
