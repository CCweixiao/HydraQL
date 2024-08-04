/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hydraql.template;

import com.hydraql.adapter.HBaseAdminAdapter;
import com.hydraql.common.model.HBaseRegionRecord;
import com.hydraql.common.model.HBaseTableRecord;
import com.hydraql.common.model.NamespaceDesc;
import com.hydraql.common.model.SnapshotDesc;
import com.hydraql.common.util.SplitGoEnum;
import com.hydraql.adapter.hbtop.Record;
import com.hydraql.adapter.hbtop.RecordFilter;
import com.hydraql.adapter.hbtop.Summary;
import com.hydraql.adapter.hbtop.field.Field;
import com.hydraql.adapter.hbtop.mode.Mode;
import com.hydraql.adapter.schema.ColumnFamilyDesc;
import com.hydraql.adapter.schema.HTableDesc;
import org.apache.hadoop.conf.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author leojie 2022/10/22 18:57
 */
public class HBaseAdminTemplate implements BaseHBaseAdminTemplate {
  private final Configuration configuration;
  private final HBaseAdminAdapter adminAdapter;

  private HBaseAdminTemplate(Builder builder) {
    this.configuration = builder.configuration;
    this.adminAdapter = new HBaseAdminAdapter(this.configuration);
  }

  @Override
  public boolean tableExists(String tableName) {
    return adminAdapter.tableExists(tableName);
  }

  @Override
  public List<HTableDesc> listTableDesc() {
    return adminAdapter.listTableDesc();
  }

  @Override
  public List<HTableDesc> listTableDesc(boolean includeSysTables) {
    return adminAdapter.listTableDesc(includeSysTables);
  }

  @Override
  public List<HTableDesc> listTableDesc(String regex, boolean includeSysTables) {
    return adminAdapter.listTableDesc(regex, includeSysTables);
  }

  @Override
  public List<HTableDesc> listTableDescByNamespace(String namespaceName) {
    return adminAdapter.listTableDescByNamespace(namespaceName);
  }

  @Override
  public List<String> listTableNames() {
    return adminAdapter.listTableNames();
  }

  @Override
  public List<String> listTableNames(boolean includeSysTables) {
    return adminAdapter.listTableNames(includeSysTables);
  }

  @Override
  public List<String> listTableNames(String regex, boolean includeSysTables) {
    return adminAdapter.listTableNames(regex, includeSysTables);
  }

  @Override
  public List<String> listTableNamesByNamespace(String namespaceName) {
    return adminAdapter.listTableNamesByNamespace(namespaceName);
  }

  @Override
  public List<ColumnFamilyDesc> listFamilyDescOfTable(String tableName) {
    return adminAdapter.listFamilyDescOfTable(tableName);
  }

  @Override
  public HTableDesc getTableDesc(String tableName) {
    return adminAdapter.getTableDesc(tableName);
  }

  @Override
  public boolean createTable(HTableDesc tableDesc) {
    return adminAdapter.createTable(tableDesc);
  }

  @Override
  public boolean createTable(HTableDesc tableDesc, String startKey, String endKey, int numRegions,
      boolean isAsync) {
    return adminAdapter.createTable(tableDesc, startKey, endKey, numRegions, isAsync);
  }

  @Override
  public boolean createTable(HTableDesc tableDesc, String[] splitKeys, boolean isAsync) {
    return adminAdapter.createTable(tableDesc, splitKeys, isAsync);
  }

  @Override
  public boolean createTable(HTableDesc tableDesc, SplitGoEnum splitGoEnum, int numRegions,
      boolean isAsync) {
    return adminAdapter.createTable(tableDesc, splitGoEnum, numRegions, isAsync);
  }

  @Override
  public boolean modifyTableAttributes(String tableName, Map<String, String> attributeKeys,
      boolean isAsync) {
    return adminAdapter.modifyTableAttributes(tableName, attributeKeys, isAsync);
  }

  @Override
  public boolean modifyTableAttributesAsync(String tableName, Map<String, String> attributeKeys) {
    return adminAdapter.modifyTableAttributesAsync(tableName, attributeKeys);
  }

  @Override
  public boolean removeTableAttributes(String tableName, List<String> attributeKeys,
      boolean isAsync) {
    return adminAdapter.removeTableAttributes(tableName, attributeKeys, isAsync);
  }

  @Override
  public boolean removeTableAttributesAsync(String tableName, List<String> attributeKeys) {
    return adminAdapter.removeTableAttributesAsync(tableName, attributeKeys);
  }

  @Override
  public boolean modifyTableConfiguration(String tableName, Map<String, String> configuration,
      boolean isAsync) {
    return adminAdapter.modifyTableConfiguration(tableName, configuration, isAsync);
  }

  @Override
  public boolean modifyTableConfigurationAsync(String tableName,
      Map<String, String> configuration) {
    return adminAdapter.modifyTableConfigurationAsync(tableName, configuration);
  }

  @Override
  public boolean removeTableConfiguration(String tableName, List<String> configKeys,
      boolean isAsync) {
    return adminAdapter.removeTableConfiguration(tableName, configKeys, isAsync);
  }

  @Override
  public boolean removeTableConfigurationAsync(String tableName, List<String> configKeys) {
    return adminAdapter.removeTableConfigurationAsync(tableName, configKeys);
  }

  @Override
  public boolean modifyFamilyAttributes(String tableName, String familyName,
      Map<String, String> attributes, boolean isAsync) {
    return adminAdapter.modifyFamilyAttributes(tableName, familyName, attributes, isAsync);
  }

  @Override
  public boolean modifyFamilyAttributesAsync(String tableName, String familyName,
      Map<String, String> attributes) {
    return adminAdapter.modifyFamilyAttributesAsync(tableName, familyName, attributes);
  }

  @Override
  public boolean removeFamilyAttributes(String tableName, String familyName,
      List<String> attributeKeys, boolean isAsync) {
    return adminAdapter.removeFamilyAttributes(tableName, familyName, attributeKeys, isAsync);
  }

  @Override
  public boolean removeFamilyAttributesAsync(String tableName, String familyName,
      List<String> attributeKeys) {
    return adminAdapter.removeFamilyAttributesAsync(tableName, familyName, attributeKeys);
  }

  @Override
  public boolean modifyFamilyConfiguration(String tableName, String familyName,
      Map<String, String> configs, boolean isAsync) {
    return adminAdapter.modifyFamilyConfiguration(tableName, familyName, configs, isAsync);
  }

  @Override
  public boolean modifyFamilyConfigurationAsync(String tableName, String familyName,
      Map<String, String> configs) {
    return adminAdapter.modifyFamilyConfigurationAsync(tableName, familyName, configs);
  }

  @Override
  public boolean removeFamilyConfiguration(String tableName, String familyName,
      List<String> configKeys, boolean isAsync) {
    return adminAdapter.removeFamilyConfiguration(tableName, familyName, configKeys, isAsync);
  }

  @Override
  public boolean removeFamilyConfigurationAsync(String tableName, String familyName,
      List<String> configKeys) {
    return adminAdapter.removeFamilyConfigurationAsync(tableName, familyName, configKeys);
  }

  @Override
  public boolean renameTable(String oldTableName, String newTableName, boolean deleteOldTable,
      boolean isAsync) {
    return adminAdapter.renameTable(oldTableName, newTableName, deleteOldTable, isAsync);
  }

  @Override
  public boolean deleteTable(String tableName, boolean isAsync) {
    return adminAdapter.deleteTable(tableName, isAsync);
  }

  @Override
  public boolean deleteTableAsync(String tableName) {
    return adminAdapter.deleteTableAsync(tableName);
  }

  @Override
  public boolean truncateTable(String tableName, boolean preserveSplits, boolean isAsync) {
    return adminAdapter.truncateTable(tableName, preserveSplits, isAsync);
  }

  @Override
  public boolean truncateTableAsync(String tableName, boolean preserveSplits) {
    return adminAdapter.truncateTableAsync(tableName, preserveSplits);
  }

  @Override
  public boolean enableTable(String tableName, boolean isAsync) {
    return adminAdapter.enableTable(tableName, isAsync);
  }

  @Override
  public boolean enableTableAsync(String tableName) {
    return adminAdapter.enableTableAsync(tableName);
  }

  @Override
  public boolean disableTable(String tableName, boolean isAsync) {
    return adminAdapter.disableTable(tableName, isAsync);
  }

  @Override
  public boolean disableTableAsync(String tableName) {
    return adminAdapter.disableTableAsync(tableName);
  }

  @Override
  public boolean isTableEnabled(String tableName) {
    return adminAdapter.isTableEnabled(tableName);
  }

  @Override
  public boolean isTableDisabled(String tableName) {
    return adminAdapter.isTableDisabled(tableName);
  }

  @Override
  public boolean isTableAvailable(String tableName) {
    return adminAdapter.isTableAvailable(tableName);
  }

  @Override
  public boolean addFamily(String tableName, ColumnFamilyDesc familyDesc, boolean isAsync) {
    return adminAdapter.addFamily(tableName, familyDesc, isAsync);
  }

  @Override
  public boolean addFamilyAsync(String tableName, ColumnFamilyDesc familyDesc) {
    return adminAdapter.addFamilyAsync(tableName, familyDesc);
  }

  @Override
  public boolean deleteFamily(String tableName, String familyName, boolean isAsync) {
    return adminAdapter.deleteFamily(tableName, familyName, isAsync);
  }

  @Override
  public boolean deleteFamilyAsync(String tableName, String familyName) {
    return adminAdapter.deleteFamilyAsync(tableName, familyName);
  }

  @Override
  public boolean modifyFamily(String tableName, ColumnFamilyDesc familyDesc, boolean isAsync) {
    return adminAdapter.modifyFamily(tableName, familyDesc, isAsync);
  }

  @Override
  public boolean modifyFamilyAsync(String tableName, ColumnFamilyDesc familyDesc) {
    return adminAdapter.modifyFamilyAsync(tableName, familyDesc);
  }

  @Override
  public boolean enableReplicationScope(String tableName, List<String> families, boolean isAsync) {
    return adminAdapter.enableReplicationScope(tableName, families, isAsync);
  }

  @Override
  public boolean enableReplicationScopeAsync(String tableName, List<String> families) {
    return adminAdapter.enableReplicationScopeAsync(tableName, families);
  }

  @Override
  public boolean disableReplicationScope(String tableName, List<String> families, boolean isAsync) {
    return adminAdapter.disableReplicationScope(tableName, families, isAsync);
  }

  @Override
  public boolean disableReplicationScopeAsync(String tableName, List<String> families) {
    return adminAdapter.disableReplicationScopeAsync(tableName, families);
  }

  @Override
  public boolean flush(String tableName) {
    return adminAdapter.flush(tableName);
  }

  @Override
  public boolean compact(String tableName) {
    return adminAdapter.compact(tableName);
  }

  @Override
  public boolean majorCompact(String tableName) {
    return adminAdapter.majorCompact(tableName);
  }

  @Override
  public boolean createNamespace(NamespaceDesc namespaceDesc, boolean isAsync) {
    return adminAdapter.createNamespace(namespaceDesc, isAsync);
  }

  @Override
  public boolean createNamespaceAsync(NamespaceDesc namespaceDesc) {
    return adminAdapter.createNamespaceAsync(namespaceDesc);
  }

  @Override
  public boolean namespaceIsExists(String namespaceName) {
    return adminAdapter.namespaceIsExists(namespaceName);
  }

  @Override
  public boolean deleteNamespace(String namespaceName, boolean isAsync) {
    return adminAdapter.deleteNamespace(namespaceName, isAsync);
  }

  @Override
  public boolean deleteNamespaceAsync(String namespaceName) {
    return adminAdapter.deleteNamespaceAsync(namespaceName);
  }

  @Override
  public NamespaceDesc getNamespaceDesc(String namespaceName) {
    return adminAdapter.getNamespaceDesc(namespaceName);
  }

  @Override
  public List<NamespaceDesc> listNamespaceDesc() {
    return adminAdapter.listNamespaceDesc();
  }

  @Override
  public List<String> listNamespaceNames() {
    return adminAdapter.listNamespaceNames();
  }

  @Override
  public long getLastMajorCompactionTimestamp(String tableName) {
    return adminAdapter.getLastMajorCompactionTimestamp(tableName);
  }

  @Override
  public long getLastMajorCompactionTimestampForRegion(String regionName) {
    return adminAdapter.getLastMajorCompactionTimestampForRegion(regionName);
  }

  @Override
  public List<SnapshotDesc> listSnapshots() {
    return adminAdapter.listSnapshots();
  }

  @Override
  public boolean snapshot(SnapshotDesc snapshotDesc, boolean isAsync) {
    return adminAdapter.snapshot(snapshotDesc, isAsync);
  }

  @Override
  public boolean snapshotAsync(SnapshotDesc snapshotDesc) {
    return adminAdapter.snapshotAsync(snapshotDesc);
  }

  @Override
  public boolean restoreSnapshot(String snapshotName, boolean isAsync) {
    return adminAdapter.restoreSnapshot(snapshotName, isAsync);
  }

  @Override
  public boolean restoreSnapshotAsync(String snapshotName) {
    return adminAdapter.restoreSnapshotAsync(snapshotName);
  }

  @Override
  public boolean cloneSnapshot(String snapshotName, String tableName, boolean isAsync) {
    return adminAdapter.cloneSnapshot(snapshotName, tableName, isAsync);
  }

  @Override
  public boolean cloneSnapshotAsync(String snapshotName, String tableName) {
    return adminAdapter.cloneSnapshotAsync(snapshotName, tableName);
  }

  @Override
  public boolean deleteSnapshot(String snapshotName) {
    return adminAdapter.deleteSnapshots(snapshotName);
  }

  @Override
  public boolean deleteSnapshots(String regex) {
    return adminAdapter.deleteSnapshots(regex);
  }

  @Override
  public boolean mergeRegions(byte[] firstRegion, byte[] secondRegion, boolean force) {
    return adminAdapter.mergeRegions(firstRegion, secondRegion, force);
  }

  @Override
  public boolean mergeMultipleRegions(byte[][] regions, boolean force) {
    return adminAdapter.mergeMultipleRegions(regions, force);
  }

  @Override
  public boolean mergeTableSmallRegions(String tableName, int limitRegionsNum,
      int limitRegionSize) {
    return adminAdapter.mergeTableSmallRegions(tableName, limitRegionsNum, limitRegionSize);
  }

  @Override
  public Summary refreshSummary() {
    return adminAdapter.refreshSummary();
  }

  @Override
  public List<Record> refreshRecords(Mode currentMode, List<RecordFilter> filters,
      Field currentSortField, boolean ascendingSort) {
    return adminAdapter.refreshRecords(currentMode, filters, currentSortField, ascendingSort);
  }

  @Override
  public List<HBaseTableRecord> refreshTableRecords(Field currentSortField, boolean ascendingSort) {
    return adminAdapter.refreshTableRecords(currentSortField, ascendingSort);
  }

  @Override
  public HBaseTableRecord refreshTableRecord(String fullTableName) {
    return adminAdapter.refreshTableRecord(fullTableName);
  }

  @Override
  public List<HBaseRegionRecord> refreshRegionRecords(String tableName, Field currentSortField,
      boolean ascendingSort) {
    return null;
  }

  public static class Builder extends BaseTemplateBuilder<HBaseAdminTemplate> {
    @Override
    public HBaseAdminTemplate build() {
      return new HBaseAdminTemplate(this);
    }
  }

  public static HBaseAdminTemplate of(Configuration configuration) {
    return new HBaseAdminTemplate.Builder().configuration(configuration).build();
  }

  public static HBaseAdminTemplate of(Properties properties) {
    return new HBaseAdminTemplate.Builder().configuration(properties).build();
  }

  public Configuration getConfiguration() {
    return configuration;
  }
}
