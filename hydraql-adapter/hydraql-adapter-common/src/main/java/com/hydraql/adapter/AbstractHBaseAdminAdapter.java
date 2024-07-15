/**
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

package com.hydraql.adapter;

import com.hydraql.adapter.context.ConnectionContext;
import com.hydraql.adapter.service.AdminService;
import com.hydraql.common.callback.AdminCallback;
import com.hydraql.common.exception.HydraQLAdminOpException;
import com.hydraql.common.model.NamespaceDesc;
import com.hydraql.common.model.SnapshotDesc;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableExistsException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.TableNotDisabledException;
import org.apache.hadoop.hbase.TableNotEnabledException;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author leojie 2020/11/14 2:26 下午
 */
abstract class AbstractHBaseAdminAdapter implements ConnectionContext, AdminService {
  private final Configuration configuration;

  public AbstractHBaseAdminAdapter(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public Configuration getConfiguration() {
    return configuration;
  }

  @Override
  public boolean tableExists(String tableName) {
    return this.execute(admin -> admin.tableExists(TableName.valueOf(tableName)));
  }

  @Override
  public boolean tableExists(TableName tableName) {
    return this.execute(admin -> admin.tableExists(tableName));
  }

  @Override
  public List<String> listTableNames() {
    return listTableNames("", false);
  }

  @Override
  public List<String> listTableNames(boolean includeSysTables) {
    return listTableNames("", includeSysTables);
  }

  @Override
  public List<String> listTableNames(String regex, boolean includeSysTables) {
    return this.execute(admin -> {
      TableName[] tableNames;
      if (StringUtil.isBlank(regex)) {
        tableNames = admin.listTableNames((Pattern) null, includeSysTables);
      } else {
        tableNames = admin.listTableNames(Pattern.compile(regex), includeSysTables);
      }
      if (tableNames == null || tableNames.length == 0) {
        return new ArrayList<>();
      }
      return Arrays.stream(tableNames).map(TableName::getNameAsString).collect(Collectors.toList());
    });
  }

  @Override
  public boolean modifyTableAttributesAsync(String tableName, Map<String, String> attributes) {
    return modifyTableAttributes(tableName, attributes, true);
  }

  @Override
  public boolean removeTableAttributes(String tableName, List<String> attributeKeys,
      boolean isAsync) {
    if (attributeKeys == null || attributeKeys.isEmpty()) {
      return true;
    }
    Map<String, String> attributes = new HashMap<>();
    for (String attributeKey : attributeKeys) {
      attributes.put(attributeKey, null);
    }
    return modifyTableAttributes(tableName, attributes, isAsync);
  }

  @Override
  public boolean removeTableAttributesAsync(String tableName, List<String> attributeKeys) {
    return removeTableAttributes(tableName, attributeKeys, true);
  }

  @Override
  public boolean modifyTableConfigurationAsync(final String tableName,
      Map<String, String> configuration) {
    return modifyTableConfiguration(tableName, configuration, true);
  }

  @Override
  public boolean removeTableConfiguration(String tableName, List<String> configKeys,
      boolean isAsync) {
    if (configKeys == null || configKeys.isEmpty()) {
      return true;
    }
    Map<String, String> configs = new HashMap<>();
    for (String configKey : configKeys) {
      configs.put(configKey, null);
    }
    return modifyTableConfiguration(tableName, configs, isAsync);
  }

  @Override
  public boolean removeTableConfigurationAsync(String tableName, List<String> configKeys) {
    return removeTableConfiguration(tableName, configKeys, true);
  }

  @Override
  public boolean modifyFamilyAttributesAsync(String tableName, String familyName,
      Map<String, String> attributes) {
    return modifyFamilyAttributes(tableName, familyName, attributes, true);
  }

  @Override
  public boolean removeFamilyAttributes(String tableName, String familyName,
      List<String> attributeKeys, boolean isAsync) {
    if (attributeKeys == null || attributeKeys.isEmpty()) {
      return true;
    }
    Map<String, String> attributes = new HashMap<>();
    for (String attributeKey : attributeKeys) {
      attributes.put(attributeKey, null);
    }
    return modifyFamilyAttributes(tableName, familyName, attributes, isAsync);
  }

  @Override
  public boolean removeFamilyAttributesAsync(String tableName, String familyName,
      List<String> attributeKeys) {
    return removeFamilyAttributes(tableName, familyName, attributeKeys, true);
  }

  @Override
  public boolean modifyFamilyConfigurationAsync(String tableName, String familyName,
      Map<String, String> configs) {
    return modifyFamilyConfiguration(tableName, familyName, configs, true);
  }

  @Override
  public boolean removeFamilyConfiguration(String tableName, String familyName,
      List<String> configKeys, boolean isAsync) {
    if (configKeys == null || configKeys.isEmpty()) {
      return true;
    }
    Map<String, String> configs = new HashMap<>();
    for (String configKey : configKeys) {
      configs.put(configKey, "");
    }
    return modifyFamilyConfiguration(tableName, familyName, configs, isAsync);
  }

  @Override
  public boolean removeFamilyConfigurationAsync(String tableName, String familyName,
      List<String> configKeys) {
    return removeFamilyConfiguration(tableName, familyName, configKeys, true);
  }

  @Override
  public boolean deleteTableAsync(String tableName) {
    return deleteTable(tableName, true);
  }

  @Override
  public boolean truncateTableAsync(String tableName, boolean preserveSplits) {
    return truncateTable(tableName, preserveSplits, true);
  }

  @Override
  public boolean enableTable(String tableName, boolean isAsync) {
    return this.execute(admin -> {
      tableNotExistsThrowError(admin, tableName);

      if (admin.isTableEnabled(TableName.valueOf(tableName))) {
        return true;
      }
      if (isAsync) {
        admin.enableTableAsync(TableName.valueOf(tableName));
      } else {
        admin.enableTable(TableName.valueOf(tableName));
      }
      return true;
    });
  }

  @Override
  public boolean enableTableAsync(String tableName) {
    return enableTable(tableName, true);
  }

  @Override
  public boolean disableTable(String tableName, boolean isAsync) {
    return this.execute(admin -> {
      tableNotExistsThrowError(admin, tableName);

      if (admin.isTableDisabled(TableName.valueOf(tableName))) {
        return true;
      }
      if (isAsync) {
        admin.disableTableAsync(TableName.valueOf(tableName));
      } else {
        admin.disableTable(TableName.valueOf(tableName));
      }
      return true;
    });
  }

  @Override
  public boolean disableTableAsync(String tableName) {
    return disableTable(tableName, true);
  }

  @Override
  public boolean isTableEnabled(String tableName) {
    return this.execute(admin -> {
      tableNotExistsThrowError(admin, tableName);
      return admin.isTableEnabled(TableName.valueOf(tableName));
    });
  }

  @Override
  public boolean isTableDisabled(String tableName) {
    return this.execute(admin -> {
      tableNotExistsThrowError(admin, tableName);
      return admin.isTableDisabled(TableName.valueOf(tableName));
    });
  }

  @Override
  public boolean isTableAvailable(String tableName) {
    return this.execute(admin -> {
      tableNotExistsThrowError(admin, tableName);
      return admin.isTableAvailable(TableName.valueOf(tableName));
    });
  }

  @Override
  public boolean deleteFamilyAsync(String tableName, String familyName) {
    return deleteFamily(tableName, familyName, true);
  }

  @Override
  public boolean enableReplicationScopeAsync(String tableName, List<String> families) {
    return enableReplicationScope(tableName, families, true);
  }

  @Override
  public boolean disableReplicationScopeAsync(String tableName, List<String> families) {
    return disableReplicationScope(tableName, families, true);
  }

  @Override
  public boolean flush(String tableName) {
    return this.execute(admin -> {
      tableNotExistsThrowError(admin, tableName);
      admin.flush(TableName.valueOf(tableName));
      return true;
    });
  }

  @Override
  public boolean compact(String tableName) {
    return this.execute(admin -> {
      tableNotExistsThrowError(admin, tableName);
      admin.compact(TableName.valueOf(tableName));
      return true;
    });
  }

  @Override
  public boolean majorCompact(String tableName) {
    return this.execute(admin -> {
      tableNotExistsThrowError(admin, tableName);
      admin.majorCompact(TableName.valueOf(tableName));
      return true;
    });
  }

  @Override
  public boolean createNamespaceAsync(NamespaceDesc namespaceDesc) {
    return createNamespace(namespaceDesc, true);
  }

  @Override
  public boolean namespaceIsExists(String namespaceName) {
    final List<String> namespaces = listNamespaceNames();
    if (namespaces == null || namespaces.isEmpty()) {
      return false;
    }
    return namespaces.contains(namespaceName);
  }

  @Override
  public boolean deleteNamespaceAsync(String namespaceName) {
    return deleteNamespace(namespaceName, true);
  }

  @Override
  public NamespaceDesc getNamespaceDesc(String namespaceName) {
    return this.execute(admin -> {
      final NamespaceDescriptor namespaceDescriptor = admin.getNamespaceDescriptor(namespaceName);
      NamespaceDesc namespaceDesc = new NamespaceDesc();
      namespaceDesc.setNamespaceName(namespaceDescriptor.getName());
      namespaceDesc.setNamespaceProps(namespaceDescriptor.getConfiguration());
      return namespaceDesc;
    });
  }

  @Override
  public List<NamespaceDesc> listNamespaceDesc() {
    return this.execute(
      admin -> Arrays.stream(admin.listNamespaceDescriptors()).map(namespaceDescriptor -> {
        NamespaceDesc namespaceDesc = new NamespaceDesc();
        namespaceDesc.setNamespaceName(namespaceDescriptor.getName());
        namespaceDesc.setNamespaceProps(namespaceDescriptor.getConfiguration());
        return namespaceDesc;
      }).collect(Collectors.toList()));
  }

  @Override
  public List<String> listNamespaceNames() {
    return listNamespaceDesc().stream().map(NamespaceDesc::getNamespaceName)
        .collect(Collectors.toList());

  }

  @Override
  public long getLastMajorCompactionTimestamp(String tableName) {
    return this.execute(admin -> {
      tableNotExistsThrowError(admin, tableName);
      return admin.getLastMajorCompactionTimestamp(TableName.valueOf(tableName));
    });
  }

  @Override
  public long getLastMajorCompactionTimestampForRegion(String regionName) {
    return this.execute(
      admin -> admin.getLastMajorCompactionTimestampForRegion(Bytes.toBytes(regionName)));
  }

  @Override
  public boolean snapshotAsync(SnapshotDesc snapshotDesc) {
    return snapshot(snapshotDesc, true);
  }

  @Override
  public boolean restoreSnapshotAsync(String snapshotName) {
    return restoreSnapshot(snapshotName, true);
  }

  @Override
  public boolean cloneSnapshotAsync(String snapshotName, String tableName) {
    return cloneSnapshot(snapshotName, tableName, true);
  }

  @Override
  public boolean deleteSnapshot(String snapshotName) {
    return this.execute(admin -> {
      admin.deleteSnapshot(snapshotName);
      return true;
    });
  }

  @Override
  public boolean deleteSnapshots(String regex) {
    return this.execute(admin -> {
      admin.deleteSnapshot(regex);
      return true;
    });
  }

  protected void tableNotExistsThrowError(Admin admin, String tableName) throws IOException {
    if (!admin.tableExists(TableName.valueOf(tableName))) {
      throw new TableNotFoundException(tableName);
    }
  }

  protected void tableExistsThrowError(Admin admin, String tableName) throws IOException {
    if (admin.tableExists(TableName.valueOf(tableName))) {
      throw new TableExistsException(tableName);
    }
  }

  protected void tableNotDisableThrowError(Admin admin, String tableName) throws IOException {
    if (admin.isTableEnabled(TableName.valueOf(tableName))) {
      throw new TableNotDisabledException(tableName);
    }
  }

  protected void tableNotEnableThrowError(Admin admin, String tableName) throws IOException {
    if (admin.isTableDisabled(TableName.valueOf(tableName))) {
      throw new TableNotEnabledException(tableName);
    }
  }

  protected <T> T execute(AdminCallback<T, Admin> action) {
    try (Admin admin = this.getConnection().getAdmin()) {
      return action.doInAdmin(admin);
    } catch (Throwable e) {
      throw new HydraQLAdminOpException(e);
    }
  }
}
