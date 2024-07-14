package com.hydraql.adapter;

import com.hydraql.adapter.hbtop.HBaseMetricOperations;
import com.hydraql.adapter.hbtop.Record;
import com.hydraql.adapter.hbtop.RecordFilter;
import com.hydraql.adapter.hbtop.Summary;
import com.hydraql.adapter.hbtop.field.Field;
import com.hydraql.adapter.hbtop.field.FieldValue;
import com.hydraql.adapter.hbtop.field.FieldValueType;
import com.hydraql.adapter.hbtop.mode.Mode;
import com.hydraql.adapter.schema.BaseColumnFamilyDesc;
import com.hydraql.adapter.schema.BaseHTableDesc;
import com.hydraql.adapter.schema.ColumnFamilyDesc;
import com.hydraql.adapter.schema.HTableDesc;
import com.hydraql.adapter.util.RegionSplitter;
import com.hydraql.adapter.util.SplitKeyUtil;
import com.hydraql.common.constants.HBaseConstants;
import com.hydraql.common.exception.HBaseFamilyHasExistsException;
import com.hydraql.common.exception.HBaseFamilyNotFoundException;
import com.hydraql.common.exception.HBaseOperationsException;
import com.hydraql.common.exception.NoSuchColumnFamilyException;
import com.hydraql.common.lang.Assert;
import com.hydraql.common.model.HBaseRegionRecord;
import com.hydraql.common.model.HBaseTableRecord;
import com.hydraql.common.model.NamespaceDesc;
import com.hydraql.common.model.SnapshotDesc;
import com.hydraql.common.util.DateAndTimeUtil;
import com.hydraql.common.util.SplitGoEnum;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.ClusterMetrics;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.ServerMetrics;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.Size;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.RegionInfo;
import org.apache.hadoop.hbase.client.SnapshotDescription;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.hydraql.common.constants.HBaseConstants.DISABLE_REPLICATION_SCOPE;
import static com.hydraql.common.constants.HBaseConstants.ENABLE_REPLICATION_SCOPE;

/**
 * @author leojie 2020/9/25 11:11 下午
 */

public class HBaseAdminAdapter extends AbstractHBaseAdminAdapter implements HBaseMetricOperations {
    public static final Logger LOG = LoggerFactory.getLogger(HBaseAdminAdapter.class);
    public static final Pattern REGION_COMPILE = Pattern.compile("\\.(\\w+)\\.");

    public HBaseAdminAdapter(Configuration configuration) {
        super(configuration);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HTableDesc> listTableDesc(String regex, boolean includeSysTables) {
        return this.execute(admin -> {
            List<TableDescriptor> tableDescriptors;
            if (StringUtil.isBlank(regex)) {
                tableDescriptors = admin.listTableDescriptors(null, includeSysTables);
            } else {
                tableDescriptors = admin.listTableDescriptors(Pattern.compile(regex), includeSysTables);
            }
            if (tableDescriptors == null || tableDescriptors.isEmpty()) {
                return new ArrayList<>();
            }
            return tableDescriptors.stream().map(tableDescriptor ->
                    HTableDesc.createDefault(tableDescriptor.getTableName().getNameAsString())
                            .convertFrom(tableDescriptor)).collect(Collectors.toList());
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HTableDesc> listTableDesc() {
        return listTableDesc("", false);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HTableDesc> listTableDesc(boolean includeSysTables) {
        return listTableDesc("", includeSysTables);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HTableDesc> listTableDescByNamespace(String namespaceName) {
        return this.execute(admin -> {
            final List<TableDescriptor> tableDescriptors = admin.listTableDescriptorsByNamespace(Bytes.toBytes(namespaceName));
            if (tableDescriptors == null || tableDescriptors.isEmpty()) {
                return new ArrayList<>();
            }
            return tableDescriptors.stream()
                    .map(t -> HTableDesc.createDefault(t.getTableName().getNameAsString())
                            .convertFrom(t)).collect(Collectors.toList());
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ColumnFamilyDesc> listFamilyDescOfTable(String tableName) {
        return this.execute(admin -> {
            tableNotExistsThrowError(admin, tableName);
            TableDescriptor tableDescriptor = admin.getDescriptor(TableName.valueOf(tableName));
            final ColumnFamilyDescriptor[] families = tableDescriptor.getColumnFamilies();
            return Arrays.stream(families).map(c -> ColumnFamilyDesc.createDefault(c.getNameAsString())
                    .convertFrom(c)).collect(Collectors.toList());
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public HTableDesc getTableDesc(String tableName) {
        return this.execute(admin -> {
            tableNotExistsThrowError(admin, tableName);
            TableDescriptor tableDescriptor = admin.getDescriptor(TableName.valueOf(tableName));
            return HTableDesc.createDefault(tableName).convertFrom(tableDescriptor);
        });
    }

    @Override
    public List<String> listTableNamesByNamespace(String namespaceName) {
        return listTableDescByNamespace(namespaceName).stream()
                .map(HTableDesc::getName).collect(Collectors.toList());
    }

    @Override
    public <HTD extends BaseHTableDesc> boolean createTable(HTD tableDesc) {
        Assert.checkNotNull(tableDesc);
        return this.execute(admin -> {
            TableDescriptor tableDescriptor = ((HTableDesc) tableDesc).convertTo();
            tableExistsThrowError(admin, tableDescriptor.getTableName().getNameAsString());
            admin.createTable(tableDescriptor);
            return true;
        });
    }

    @Override
    public <HTD extends BaseHTableDesc> boolean createTable(HTD tableDesc, String startKey, String endKey, int numRegions, boolean isAsync) {
        Assert.checkNotNull(tableDesc);
        return this.execute(admin -> {
            TableDescriptor tableDescriptor = ((HTableDesc) tableDesc).convertTo();
            tableExistsThrowError(admin, tableDescriptor.getTableName().getNameAsString());
            boolean preSplit = (StringUtil.isNotBlank(startKey) && StringUtil.isNotBlank(endKey) && numRegions > 0);

            if (preSplit) {
                final byte[] startKeyBytes = Bytes.toBytes(startKey);
                final byte[] endKeyBytes = Bytes.toBytes(endKey);

                if (numRegions < 3) {
                    throw new HBaseOperationsException("请至少指定3个分区");
                }

                if (Bytes.compareTo(startKeyBytes, endKeyBytes) >= 0) {
                    throw new HBaseOperationsException("预分区开始的key必须要小于预分区结束的key");
                }

                if (numRegions == 3) {
                    if (isAsync) {
                        admin.createTableAsync(tableDescriptor, new byte[][]{startKeyBytes, endKeyBytes});
                    } else {
                        admin.createTable(tableDescriptor, new byte[][]{startKeyBytes, endKeyBytes});
                    }
                    return true;
                }

                byte[][] splitKeys = Bytes.split(startKeyBytes, endKeyBytes, numRegions - 3);

                if (splitKeys == null || splitKeys.length != numRegions - 1) {
                    throw new HBaseOperationsException("无法将预分区的起止键范围分割成足够的region");
                }

                if (isAsync) {
                    admin.createTableAsync(tableDescriptor, splitKeys);
                } else {
                    admin.createTable(tableDescriptor, splitKeys);
                }

            } else {
                admin.createTable(tableDescriptor);
            }
            return true;
        });
    }

    @Override
    public <HTD extends BaseHTableDesc> boolean createTable(HTD tableDesc, String[] splitKeys, boolean isAsync) {
        Assert.checkNotNull(tableDesc);
        return this.execute(admin -> {
            TableDescriptor tableDescriptor = ((HTableDesc) tableDesc).convertTo();
            tableExistsThrowError(admin, tableDescriptor.getTableName().getNameAsString());
            boolean preSplit = splitKeys != null && splitKeys.length > 0;

            if (preSplit) {
                if (isAsync) {
                    admin.createTableAsync(tableDescriptor, SplitKeyUtil.getSplitKeyBytes(splitKeys));
                } else {
                    admin.createTable(tableDescriptor, SplitKeyUtil.getSplitKeyBytes(splitKeys));
                }
            } else {
                admin.createTable(tableDescriptor);
            }

            return true;
        });
    }

    @Override
    public <HTD extends BaseHTableDesc> boolean createTable(HTD tableDesc, SplitGoEnum splitGoEnum, int numRegions, boolean isAsync) {
        Assert.checkNotNull(tableDesc);
        return this.execute(admin -> {
            TableDescriptor tableDescriptor = ((HTableDesc) tableDesc).convertTo();
            tableExistsThrowError(admin, tableDescriptor.getTableName().getNameAsString());
            boolean preSplit = splitGoEnum != null && numRegions > 0;

            if (preSplit) {
                byte[][] splits;
                if (splitGoEnum == SplitGoEnum.HEX_STRING_SPLIT) {
                    splits = new RegionSplitter.HexStringSplit().split(numRegions);
                } else if (splitGoEnum == SplitGoEnum.DECIMAL_STRING_SPLIT) {
                    splits = new RegionSplitter.DecimalStringSplit().split(numRegions);
                } else if (splitGoEnum == SplitGoEnum.UNIFORM_SPLIT) {
                    splits = new RegionSplitter.UniformSplit().split(numRegions);
                } else {
                    throw new HBaseOperationsException("暂不支持的预分区策略:" + splitGoEnum.getSplitGo());
                }
                if (isAsync) {
                    admin.createTableAsync(tableDescriptor, splits);
                } else {
                    admin.createTable(tableDescriptor, splits);
                }
            } else {
                admin.createTable(tableDescriptor);
            }
            return true;
        });
    }

    @Override
    public boolean modifyTableAttributes(String tableName, Map<String, String> attributes, boolean isAsync) {
        if (attributes == null || attributes.isEmpty()) {
            return true;
        }
        return this.execute(admin -> {
            final TableDescriptor tableDescriptor = admin.getDescriptor(TableName.valueOf(tableName));
            boolean isNeedModify = false;
            Map<Bytes, Bytes> values = tableDescriptor.getValues();
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableDescriptor);
            for (String attributeKey : attributes.keySet()) {
                String attributeValue = attributes.get(attributeKey);
                if (StringUtil.isBlank(attributeValue)) {
                    tableDescriptorBuilder.setValue(attributeKey, null);
                } else {
                    Bytes originalValue = values.get(new Bytes(Bytes.toBytes(attributeKey)));
                    if (new Bytes(Bytes.toBytes(attributeValue)).equals(originalValue)) {
                        continue;
                    }
                    tableDescriptorBuilder.setValue(attributeKey, attributeValue);
                }
                isNeedModify = true;
            }

            if (!isNeedModify) {
                return true;
            }

            if (isAsync) {
                admin.modifyTableAsync(tableDescriptorBuilder.build());
            } else {
                admin.modifyTable(tableDescriptorBuilder.build());
            }
            return true;
        });
    }

    @Override
    public boolean modifyTableConfiguration(final String tableName, Map<String, String> configuration, boolean isAsync) {
        throw new UnsupportedOperationException("Unsupported function modifyTableConfiguration.");
    }

    @Override
    public boolean removeTableConfiguration(String tableName, List<String> configKeys, boolean isAsync) {
        throw new UnsupportedOperationException("Unsupported function removeTableConfiguration.");
    }

    @Override
    public boolean modifyFamilyAttributes(String tableName, String familyName, Map<String, String> attributes, boolean isAsync) {
        if (attributes == null || attributes.isEmpty()) {
            return true;
        }

        return this.execute(admin -> {
            final TableDescriptor tableDescriptor = admin.getDescriptor(TableName.valueOf(tableName));
            ColumnFamilyDescriptor originalColumnDescriptor = null;
            for (ColumnFamilyDescriptor family : tableDescriptor.getColumnFamilies()) {
                if (family.getNameAsString().equals(familyName)) {
                    originalColumnDescriptor = family;
                    break;
                }
            }
            if (originalColumnDescriptor == null) {
                throw new NoSuchColumnFamilyException(familyName);
            }
            ColumnFamilyDescriptorBuilder modifyColumnDescriptorBuilder =
                    ColumnFamilyDescriptorBuilder.newBuilder(originalColumnDescriptor);

            boolean isNeedModify = false;
            for (String attributeKey : attributes.keySet()) {
                byte[] attributeKeyBytes = Bytes.toBytes(attributeKey);

                byte[] originalAttributeValBytes = originalColumnDescriptor.getValue(attributeKeyBytes);
                String modifyAttributeVal = attributes.get(attributeKey);
                byte[] modifyAttributeValBytes =
                        StringUtil.isBlank(modifyAttributeVal) ? null : Bytes.toBytes(modifyAttributeVal);

                if (originalAttributeValBytes != null &&
                        Arrays.equals(originalAttributeValBytes, modifyAttributeValBytes)) {
                    continue;
                }
                modifyColumnDescriptorBuilder.setValue(attributeKeyBytes, modifyAttributeValBytes);
                isNeedModify = true;
            }

            if (!isNeedModify) {
                return true;
            }

            if (isAsync) {
                admin.modifyColumnFamilyAsync(TableName.valueOf(tableName), modifyColumnDescriptorBuilder.build());
            } else {
                admin.modifyColumnFamily(TableName.valueOf(tableName), modifyColumnDescriptorBuilder.build());
            }
            return true;
        });
    }

    @Override
    public boolean modifyFamilyConfiguration(String tableName, String familyName, Map<String, String> configs, boolean isAsync) {
        if (configs == null || configs.isEmpty()) {
            return true;
        }

        return this.execute(admin -> {
            final TableDescriptor tableDescriptor = admin.getDescriptor(TableName.valueOf(tableName));
            ColumnFamilyDescriptor originalColumnDescriptor = null;
            for (ColumnFamilyDescriptor family : tableDescriptor.getColumnFamilies()) {
                if (family.getNameAsString().equals(familyName)) {
                    originalColumnDescriptor = family;
                    break;
                }
            }

            if (originalColumnDescriptor == null) {
                throw new NoSuchColumnFamilyException(familyName);
            }

            ColumnFamilyDescriptorBuilder modifyColumnDescriptorBuilder =
                    ColumnFamilyDescriptorBuilder.newBuilder(originalColumnDescriptor);

            boolean isNeedModify = false;
            for (String configKey : configs.keySet()) {
                String originalConfigVal = originalColumnDescriptor.getConfigurationValue(configKey);
                String modifyConfigVal = configs.get(configKey);
                if (StringUtil.isNotBlank(originalConfigVal) &&
                        StringUtil.isNotBlank(modifyConfigVal) && originalConfigVal.equals(modifyConfigVal)) {
                    continue;
                }
                if (StringUtil.isBlank(modifyConfigVal)) {
                    modifyColumnDescriptorBuilder.setConfiguration(configKey, null);
                } else {
                    modifyColumnDescriptorBuilder.setConfiguration(configKey, modifyConfigVal);
                }
                isNeedModify = true;
            }

            if (!isNeedModify) {
                return true;
            }

            if (isAsync) {
                admin.modifyColumnFamilyAsync(TableName.valueOf(tableName), modifyColumnDescriptorBuilder.build());
            } else {
                admin.modifyColumnFamily(TableName.valueOf(tableName), modifyColumnDescriptorBuilder.build());
            }
            return true;
        });
    }

    @Override
    public boolean renameTable(String oldTableName, String newTableName, boolean deleteOldTable, boolean isAsync) {

        if (isTableEnabled(oldTableName)) {
            throw new HBaseOperationsException("表[" + oldTableName + "]重命名之前请先禁用");
        }

        return this.execute(admin -> {
            tableNotExistsThrowError(admin, oldTableName);
            tableExistsThrowError(admin, newTableName);
            final String tempSnapShotName = oldTableName.replace(":", "_") +
                    "_SNAPSHOT_RENAME_" + UUID.randomUUID().toString().replaceAll("-", "");
            admin.snapshot(tempSnapShotName, TableName.valueOf(oldTableName));
            admin.cloneSnapshot(tempSnapShotName, TableName.valueOf(newTableName));
            admin.deleteSnapshot(tempSnapShotName);
            if (deleteOldTable) {
                admin.deleteTable(TableName.valueOf(oldTableName));
            } else {
                admin.enableTableAsync(TableName.valueOf(oldTableName));
            }
            return true;
        });
    }

    @Override
    public boolean deleteTable(String tableName, boolean isAsync) {
        return this.execute(admin -> {
            tableNotExistsThrowError(admin, tableName);
            tableNotDisableThrowError(admin, tableName);

            if (isAsync) {
                admin.deleteTableAsync(TableName.valueOf(tableName));
            } else {
                admin.deleteTable(TableName.valueOf(tableName));
            }
            return true;
        });
    }

    @Override
    public boolean truncateTable(String tableName, boolean preserveSplits, boolean isAsync) {
        return this.execute(admin -> {
            tableNotExistsThrowError(admin, tableName);
            tableNotDisableThrowError(admin, tableName);
            if (isAsync) {
                admin.truncateTableAsync(TableName.valueOf(tableName), preserveSplits);
            } else {
                admin.truncateTable(TableName.valueOf(tableName), preserveSplits);
            }
            return true;
        });
    }

    @Override
    public <CF extends BaseColumnFamilyDesc> boolean addFamily(String tableName, CF familyDesc, boolean isAsync) {
        Assert.checkNotNull(familyDesc);
        return this.execute(admin -> {
            tableNotExistsThrowError(admin, tableName);
            ColumnFamilyDesc columnFamilyDesc = (ColumnFamilyDesc) familyDesc;
            final TableDescriptor tableDescriptor = admin.getDescriptor(TableName.valueOf(tableName));
            if (tableDescriptor.hasColumnFamily(Bytes.toBytes(columnFamilyDesc.getName()))) {
                throw new HBaseFamilyHasExistsException(String.format("The family %s in the table %s has created.",
                        columnFamilyDesc.getName(), tableName));
            }
            if (isAsync) {
                admin.addColumnFamilyAsync(TableName.valueOf(tableName), columnFamilyDesc.convertTo());
            } else {
                admin.addColumnFamily(TableName.valueOf(tableName), columnFamilyDesc.convertTo());
            }
            return true;
        });
    }

    @Override
    public <CF extends BaseColumnFamilyDesc> boolean addFamilyAsync(String tableName, CF familyDesc) {
        return addFamily(tableName, familyDesc, true);
    }

    @Override
    public boolean deleteFamily(String tableName, String familyName, boolean isAsync) {
        return this.execute(admin -> {
            tableNotExistsThrowError(admin, tableName);
            final TableDescriptor tableDescriptor = admin.getDescriptor(TableName.valueOf(tableName));
            if (!tableDescriptor.hasColumnFamily(Bytes.toBytes(familyName))) {
                throw new HBaseFamilyNotFoundException(String.format("The family %s in the table %s is not exists.",
                        familyName, tableName));
            }
            if (isAsync) {
                admin.deleteColumnFamilyAsync(TableName.valueOf(tableName), Bytes.toBytes(familyName));
            } else {
                admin.deleteColumnFamily(TableName.valueOf(tableName), Bytes.toBytes(familyName));
            }

            return true;
        });
    }

    @Override
    public <CF extends BaseColumnFamilyDesc> boolean modifyFamily(String tableName, CF familyDesc, boolean isAsync) {
        Assert.checkNotNull(familyDesc);
        return this.execute(admin -> {
            tableNotExistsThrowError(admin, tableName);
            ColumnFamilyDesc columnFamilyDesc = (ColumnFamilyDesc) familyDesc;
            final TableDescriptor tableDescriptor = admin.getDescriptor(TableName.valueOf(tableName));
            if (!tableDescriptor.hasColumnFamily(Bytes.toBytes(columnFamilyDesc.getName()))) {
                throw new HBaseFamilyNotFoundException(String.format("The family %s in the table %s is not exists.",
                        columnFamilyDesc.getName(), tableName));
            }
            ColumnFamilyDescriptor originalColumnDescriptor =
                    tableDescriptor.getColumnFamily(Bytes.toBytes(columnFamilyDesc.getName()));
            ColumnFamilyDescriptor modifyColumnDescriptor = columnFamilyDesc.convertTo();
            boolean same = ColumnFamilyDescriptorBuilder.ModifyableColumnFamilyDescriptor.COMPARATOR.compare(originalColumnDescriptor,
                    modifyColumnDescriptor) == 0;

            if (!same) {
                if (isAsync) {
                    admin.modifyColumnFamilyAsync(TableName.valueOf(tableName), modifyColumnDescriptor);
                } else {
                    admin.modifyColumnFamily(TableName.valueOf(tableName), modifyColumnDescriptor);
                }
            }
            return true;
        });
    }

    @Override
    public <CF extends BaseColumnFamilyDesc> boolean modifyFamilyAsync(String tableName, CF familyDesc) {
        return modifyFamily(tableName, familyDesc, true);
    }

    @Override
    public boolean enableReplicationScope(String tableName, List<String> families, boolean isAsync) {
        return modifyTableReplicationScope(tableName, ENABLE_REPLICATION_SCOPE, families);
    }

    @Override
    public boolean disableReplicationScope(String tableName, List<String> families, boolean isAsync) {
        return modifyTableReplicationScope(tableName, DISABLE_REPLICATION_SCOPE, families);
    }

    @Override
    public boolean createNamespace(NamespaceDesc namespaceDesc, boolean isAsync) {
        if (namespaceIsExists(namespaceDesc.getNamespaceName())) {
            throw new HBaseOperationsException("命名空间[" + namespaceDesc.getNamespaceName() + "]已经存在");
        }
        return this.execute(admin -> {
            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(namespaceDesc.getNamespaceName()).build();
            final Map<String, String> namespaceProps = namespaceDesc.getNamespaceProps();
            if (namespaceProps != null && !namespaceProps.isEmpty()) {
                namespaceProps.forEach(namespaceDescriptor::setConfiguration);
            }
            if (isAsync) {
                admin.createNamespaceAsync(namespaceDescriptor);
            } else {
                admin.createNamespace(namespaceDescriptor);
            }
            return true;
        });
    }

    @Override
    public boolean deleteNamespace(String namespaceName, boolean isAsync) {
        return this.execute(admin -> {
            final List<TableDescriptor> tableDescriptors = admin.listTableDescriptorsByNamespace(Bytes.toBytes(namespaceName));
            if (tableDescriptors != null && !tableDescriptors.isEmpty()) {
                throw new HBaseOperationsException("命名空间[" + namespaceName + "]下存在表，不能被删除");
            }
            if (isAsync) {
                admin.deleteNamespaceAsync(namespaceName);
            } else {
                admin.deleteNamespace(namespaceName);
            }
            return true;
        });
    }

    @Override
    public List<SnapshotDesc> listSnapshots() {
        return this.execute(admin -> {
            final List<SnapshotDescription> listSnapshots = admin.listSnapshots();
            if (listSnapshots == null || listSnapshots.isEmpty()) {
                return new ArrayList<>();
            } else {
                return listSnapshots.stream().map(snapshotDescription -> {
                    SnapshotDesc snapshotDesc = new SnapshotDesc();
                    snapshotDesc.setSnapshotName(snapshotDescription.getName());
                    snapshotDesc.setTableName(snapshotDescription.getTableName().getNameAsString());
                    snapshotDesc.setCreateTime(snapshotDescription.getCreationTime());
                    return snapshotDesc;
                }).collect(Collectors.toList());
            }
        });

    }

    @Override
    public boolean snapshot(SnapshotDesc snapshotDesc, boolean isAsync) {
        return this.execute(admin -> {
            tableNotExistsThrowError(admin, snapshotDesc.getTableName());
            admin.snapshot(snapshotDesc.getSnapshotName(), TableName.valueOf(snapshotDesc.getTableName()));
            return true;
        });
    }

    @Override
    public boolean restoreSnapshot(String snapshotName, boolean isAsync) {
        return this.execute(admin -> {
            admin.restoreSnapshot(snapshotName);
            return true;
        });
    }

    @Override
    public boolean cloneSnapshot(String snapshotName, String tableName, boolean isAsync) {
        return this.execute(admin -> {
            tableExistsThrowError(admin, tableName);
            admin.cloneSnapshot(snapshotName, TableName.valueOf(tableName));
            return true;
        });
    }

    @Override
    public boolean mergeRegions(byte[] firstRegion, byte[] secondRegion, boolean force) {
        return this.execute(admin -> {
            admin.mergeRegionsAsync(firstRegion, secondRegion, force);
            return true;
        });
    }

    @Override
    public boolean mergeMultipleRegions(byte[][] regions, boolean force) {
        return this.execute(admin -> {
            int mergeRegionsNum = regions.length;
            if (mergeRegionsNum % 2 != 0) {
                mergeRegionsNum = mergeRegionsNum - 1;
            }
            for (int i = 0, j = 1; i < mergeRegionsNum - 1; i += 2, j += 2) {
                byte[] firstByteRegionName = regions[i];
                byte[] secondByteRegionName = regions[j];
                admin.mergeRegionsAsync(firstByteRegionName, secondByteRegionName, force);
            }
            return true;
        });
    }

    @Override
    public boolean mergeTableSmallRegions(String tableName, int limitRegionsNum, int limitRegionSize) {
        return this.execute(admin -> {
            final ClusterMetrics clusterStatus = admin.getClusterMetrics();
            final Map<ServerName, ServerMetrics> serverStatus = clusterStatus.getLiveServerMetrics();
            List<String> biggerRegions = new ArrayList<>();
            serverStatus.forEach(((serverName, serverMetrics) ->
                    serverMetrics.getRegionMetrics().forEach((region, regionMetric) -> {
                        final String regionStrName = regionMetric.getNameAsString();
                        if (regionStrName.startsWith(tableName)) {
                            Matcher regionNameMatcher = REGION_COMPILE.matcher(regionStrName);
                            String regionEncodedName = "";
                            if (regionNameMatcher.find()) {
                                regionEncodedName = regionNameMatcher.group(1);
                            }
                            if (StringUtil.isBlank(regionEncodedName)) {
                                throw new HBaseOperationsException("无法获取region[" + regionStrName + "]的加密名称");
                            }
                            final double regionStoreFileSize = regionMetric.getStoreFileSize().get();
                            if (regionStoreFileSize > limitRegionSize) {
                                biggerRegions.add(regionEncodedName);
                            }
                        }
                    })));
            List<RegionInfo> mergedRegions = new ArrayList<>();
            admin.getRegions(TableName.valueOf(tableName)).forEach(regionInfo -> {
                if (biggerRegions.contains(regionInfo.getEncodedName())) {
                    // 把大region设置成null，使之不会参与合并
                    mergedRegions.add(null);
                } else {
                    mergedRegions.add(regionInfo);
                }
            });
            List<RegionInfo> subMergedRegions;
            int mergedRegionsSize = mergedRegions.size();
            if (mergedRegionsSize > limitRegionsNum) {
                subMergedRegions = mergedRegions.subList(0, limitRegionsNum);
            } else {
                subMergedRegions = mergedRegions;
            }
            // 保证region相邻
            for (int i = 0, j = 1; i < subMergedRegions.size() - 1; i += 2, j += 2) {
                RegionInfo firstRegionInfo = subMergedRegions.get(i);
                RegionInfo secondRegionInfo = subMergedRegions.get(j);
                if (firstRegionInfo == null) {
                    continue;
                }
                if (secondRegionInfo == null) {
                    continue;
                }
                if (firstRegionInfo.isOffline() || firstRegionInfo.isSplit()) {
                    continue;
                }
                if (secondRegionInfo.isOffline() || secondRegionInfo.isSplit()) {
                    continue;
                }
                if (!RegionInfo.areAdjacent(firstRegionInfo, secondRegionInfo)) {
                    continue;
                }
                admin.mergeRegionsAsync(firstRegionInfo.getEncodedNameAsBytes(), secondRegionInfo.getEncodedNameAsBytes(), false);
                LOG.info("表:" + tableName + ", Region:" + firstRegionInfo.getEncodedName() + ", " + secondRegionInfo.getEncodedName() + " 异步合并指令运行成功");
            }
            return true;
        });
    }

    /**
     * 修改表和列簇的REPLICATION_SCOPE属性
     *
     * @param tableName        表名
     * @param replicationScope 0 或 1，1 表示禁用replication
     * @param families         列簇
     * @return 修改REPLICATION_SCOPE的熟悉是否成功
     */
    private boolean modifyTableReplicationScope(String tableName, int replicationScope, List<String> families) {

        if (families == null || families.isEmpty()) {
            throw new HBaseOperationsException("请传入一个或多个待修改的列簇");
        }

        return this.execute(admin -> {
            tableNotExistsThrowError(admin, tableName);
            TableDescriptor tableDescriptor = admin.getDescriptor(TableName.valueOf(tableName));
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableDescriptor);
            boolean change = false;
            for (String family : families) {
                final ColumnFamilyDescriptor hd = tableDescriptor.getColumnFamily(Bytes.toBytes(family));
                ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(hd);
                final int currentScope = hd.getScope();
                if (currentScope != replicationScope) {
                    columnFamilyDescriptorBuilder.setScope(replicationScope);
                    tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptorBuilder.build());
                    change = true;
                }
            }
            if (change) {
                admin.modifyTable(tableDescriptorBuilder.build());
            }
            return true;
        });
    }

    @Override
    public Summary refreshSummary() {
        return this.execute(admin -> {
            final ClusterMetrics clusterMetrics = admin.getClusterMetrics();
            String currentTime = DateAndTimeUtil.parseTimestampToTimeStr(System.currentTimeMillis());

            String version = clusterMetrics.getHBaseVersion();
            String clusterId = clusterMetrics.getClusterId();
            int liveServers = clusterMetrics.getLiveServerMetrics().size();
            int deadServers = clusterMetrics.getDeadServerNames().size();
            int regionCount = clusterMetrics.getRegionCount();
            int ritCount = clusterMetrics.getRegionStatesInTransition().size();
            int namespaceCount = admin.listNamespaceDescriptors().length;
            int tableCount = admin.listTableNames().length;
            int snapshotCount = admin.listSnapshots().size();

            double averageLoad = clusterMetrics.getAverageLoad();

            long aggregateRequestPerSecond = clusterMetrics.getLiveServerMetrics().values().stream()
                    .mapToLong(ServerMetrics::getRequestCountPerSecond).sum();

            Summary clusterSummary = new Summary(currentTime, version, clusterId, liveServers + deadServers,
                    liveServers, deadServers, namespaceCount, tableCount, snapshotCount, regionCount, ritCount, averageLoad, aggregateRequestPerSecond);
            List<String> liveServerNames = new ArrayList<>(liveServers);

            clusterMetrics.getLiveServerMetrics().forEach(((serverName, serverMetrics) -> liveServerNames.add(serverName.getServerName())));
            List<String> deadServerNames = clusterMetrics.getDeadServerNames().stream().map(ServerName::getServerName).collect(Collectors.toList());
            clusterSummary.setLiveServerNames(liveServerNames);
            clusterSummary.setDeadServerNames(deadServerNames);

            return clusterSummary;
        });
    }

    @Override
    public List<Record> refreshRecords(Mode currentMode, List<RecordFilter> filters, Field currentSortField, boolean ascendingSort) {
        return this.execute(admin -> {
            final ClusterMetrics clusterMetrics = admin.getClusterMetrics();
            List<Record> records = currentMode.getRecords(clusterMetrics);

            return filterAndSortRecords(records, filters, currentSortField, ascendingSort);

        });
    }

    @Override
    public List<HBaseTableRecord> refreshTableRecords(Field currentSortField, boolean ascendingSort) {
        return this.execute(admin -> {
            List<RecordFilter> recordFilters = new ArrayList<>();
            RecordFilter recordFilter = RecordFilter.newBuilder(Field.NAMESPACE, false)
                    .notEqual(new FieldValue(HBaseConstants.DEFAULT_SYS_TABLE_NAMESPACE, FieldValueType.STRING));
            recordFilters.add(recordFilter);
            final ClusterMetrics clusterMetrics = admin.getClusterMetrics();
            List<Record> records = Mode.TABLE.getRecords(clusterMetrics);
            records = filterAndSortRecords(records, recordFilters, currentSortField, ascendingSort);
            return records.stream().map(this::convertRecordToHBaseTableRecord).collect(Collectors.toList());
        });
    }

    private HBaseTableRecord convertRecordToHBaseTableRecord(Record record) {
        HBaseTableRecord tableRecord = new HBaseTableRecord();
        tableRecord.setNamespaceName(record.get(Field.NAMESPACE).asString());
        tableRecord.setTableName(record.get(Field.TABLE).asString());
        tableRecord.setStoreFileSizeTag(record.get(Field.STORE_FILE_SIZE).asString());
        tableRecord.setStoreFileSize(record.get(Field.STORE_FILE_SIZE).asSize().get(Size.Unit.GIGABYTE));
        tableRecord.setUncompressedStoreFileSizeTag(record.get(Field.UNCOMPRESSED_STORE_FILE_SIZE).asString());
        tableRecord.setUncompressedStoreFileSize(record.get(Field.UNCOMPRESSED_STORE_FILE_SIZE).asSize().get(Size.Unit.GIGABYTE));
        tableRecord.setNumStoreFiles(record.get(Field.NUM_STORE_FILES).asInt());
        tableRecord.setMemStoreSizeTag(record.get(Field.MEM_STORE_SIZE).asString());
        tableRecord.setMemStoreSize(record.get(Field.MEM_STORE_SIZE).asSize().get(Size.Unit.MEGABYTE));
        tableRecord.setRegionCount(record.get(Field.REGION_COUNT).asInt());
        return tableRecord;
    }

    @Override
    public HBaseTableRecord refreshTableRecord(String fullTableName) {
        return this.execute(admin -> {
            List<RecordFilter> recordFilters = createTableRecordFilters(fullTableName);
            final ClusterMetrics clusterMetrics = admin.getClusterMetrics();
            List<Record> records = Mode.TABLE.getRecords(clusterMetrics);
            if (records.isEmpty()) {
                return null;
            }
            records = filterAndSortRecords(records, recordFilters, null, true);
            Record record = records.get(0);
            return convertRecordToHBaseTableRecord(record);
        });
    }

    @Override
    public List<HBaseRegionRecord> refreshRegionRecords(String tableName, Field currentSortField, boolean ascendingSort) {
        return this.execute(admin -> {
            final ClusterMetrics clusterMetrics = admin.getClusterMetrics();
            List<Record> records = Mode.REGION.getRecords(clusterMetrics);
            List<RecordFilter> recordFilters = createTableRecordFilters(tableName);
            records = filterAndSortRecords(records, recordFilters, currentSortField, ascendingSort);

            List<HBaseRegionRecord> regionRecords = new ArrayList<>(records.size());

            for (int i = 0, j = 1; i < records.size(); i += 1, j += 1) {
                Record firstRegionRecord = records.get(i);
                String endKey;
                if (j > records.size() - 1) {
                    endKey = "";
                } else {
                    Record secondRegionRecord = records.get(j);
                    endKey = secondRegionRecord.getOrDefault(Field.START_KEY, new FieldValue("", FieldValueType.STRING)).asString();
                }
                HBaseRegionRecord regionRecord = new HBaseRegionRecord();

                regionRecord.setNamespaceName(firstRegionRecord.get(Field.NAMESPACE).asString());
                regionRecord.setTableName(firstRegionRecord.get(Field.TABLE).asString());
                regionRecord.setRegionName(firstRegionRecord.get(Field.REGION_NAME).asString());
                regionRecord.setEncodedRegionName(firstRegionRecord.get(Field.REGION).asString());

                regionRecord.setStartCode(DateAndTimeUtil.parseTimestampToTimeStr(Long.parseLong(firstRegionRecord.get(Field.START_CODE).asString())));
                regionRecord.setRegionServer(firstRegionRecord.get(Field.REGION_SERVER).asString());

                regionRecord.setStoreFileSizeTag(firstRegionRecord.get(Field.STORE_FILE_SIZE).asString());
                regionRecord.setStoreFileSize(firstRegionRecord.get(Field.STORE_FILE_SIZE).asSize().get(Size.Unit.GIGABYTE));
                regionRecord.setUncompressedStoreFileSizeTag(firstRegionRecord.get(Field.UNCOMPRESSED_STORE_FILE_SIZE).asString());
                regionRecord.setUncompressedStoreFileSize(firstRegionRecord.get(Field.UNCOMPRESSED_STORE_FILE_SIZE).asSize().get(Size.Unit.GIGABYTE));
                regionRecord.setNumStoreFiles(firstRegionRecord.get(Field.NUM_STORE_FILES).asInt());
                regionRecord.setMemStoreSizeTag(firstRegionRecord.get(Field.MEM_STORE_SIZE).asString());
                regionRecord.setMemStoreSize(firstRegionRecord.get(Field.MEM_STORE_SIZE).asSize().get(Size.Unit.MEGABYTE));

                regionRecord.setLocality(firstRegionRecord.get(Field.LOCALITY).asFloat());
                regionRecord.setStartKey(firstRegionRecord.get(Field.START_KEY).asString());
                regionRecord.setEndKey(endKey);
                regionRecords.add(regionRecord);

            }
            return regionRecords;
        });
    }

    private List<Record> filterAndSortRecords(List<Record> records, List<RecordFilter> recordFilters,
                                              Field currentSortField, boolean ascendingSort) {
        // Filter and sort
        List<Record> sortAndFilterRecords;
        if (currentSortField != null) {
            sortAndFilterRecords = records.stream()
                    .filter(r -> recordFilters.stream().allMatch(f -> f.execute(r)))
                    .sorted((recordLeft, recordRight) -> {
                        FieldValue left = recordLeft.get(currentSortField);
                        FieldValue right = recordRight.get(currentSortField);
                        return (ascendingSort ? 1 : -1) * left.compareTo(right);
                    }).collect(Collectors.toList());
        } else {
            sortAndFilterRecords = records.stream()
                    .filter(r -> recordFilters.stream().allMatch(f -> f.execute(r)))
                    .collect(Collectors.toList());
        }

        return Collections.unmodifiableList(sortAndFilterRecords);
    }

    private List<RecordFilter> createTableRecordFilters(String tableName) {
        List<RecordFilter> recordFilters = new ArrayList<>();

        RecordFilter namespaceFilter = RecordFilter.newBuilder(Field.NAMESPACE, false)
                .equal(new FieldValue(HBaseConstants.getNamespaceName(tableName), FieldValueType.STRING));
        RecordFilter tableFilter = RecordFilter.newBuilder(Field.TABLE, false)
                .equal(new FieldValue(HBaseConstants.getTableName(tableName), FieldValueType.STRING));
        recordFilters.add(namespaceFilter);
        recordFilters.add(tableFilter);
        return recordFilters;
    }
}