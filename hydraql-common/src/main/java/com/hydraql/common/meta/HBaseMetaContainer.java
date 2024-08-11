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

package com.hydraql.common.meta;

import com.hydraql.common.constants.HBaseConstants;
import com.hydraql.common.exception.InvalidTableModelClassException;
import com.hydraql.common.lang.Assert;
import com.hydraql.common.meta.annotations.GeneratedValue;
import com.hydraql.common.meta.annotations.GenerationType;
import com.hydraql.common.meta.annotations.HBaseColumn;
import com.hydraql.common.meta.annotations.HBaseRowKey;
import com.hydraql.common.meta.annotations.HBaseTable;
import com.hydraql.common.row.RowKeyGenerator;
import com.hydraql.common.util.StringUtil;
import com.hydraql.reflectasm.Reflector;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author leojie 2022/11/20 10:50
 */
public class HBaseMetaContainer {
  private final ConcurrentHashMap<Class<?>, HBaseTableSchema> tableMetas = new ConcurrentHashMap<>();
  private final Object lock = new Object();

  private HBaseMetaContainer() {
  }

  private static class MetaFactoryHolder {
    private static final HBaseMetaContainer INSTANCE = new HBaseMetaContainer();
  }

  public static HBaseMetaContainer getInstance() {
    return MetaFactoryHolder.INSTANCE;
  }

  public void abandon(Class<?> clazz) {
    synchronized (lock) {
      HBaseTableSchema existsTableMeta = tableMetas.get(clazz);
      if (existsTableMeta != null) {
        tableMetas.remove(clazz);
      }
    }
  }

  public HBaseTableSchema stuff(Class<?> clazz) {
    HBaseTableSchema existsTableMeta = tableMetas.get(clazz);
    if (existsTableMeta != null) {
      return existsTableMeta;
    }

    synchronized (lock) {
      existsTableMeta = tableMetas.get(clazz);
      if (existsTableMeta != null) {
        return existsTableMeta;
      }
      TableMeta tableMetaInfo = extractTableInfo(clazz);
      Reflector reflector = new Reflector(clazz);
      HBaseTableSchema tableMeta = HBaseTableSchema.newBuilder(clazz)
              .setDefaultConstructor(reflector.getDefaultConstructor())
              .setTableName(tableMetaInfo.getTableName()).build();

      int colCount = 0;
      for (Field field : reflector.getFields()) {
        if (Reflector.isNotGeneralProperty(field)) {
          continue;
        }
        RowKeyMeta rowKeyMeta = extractRowKeyInfo(field);
        ColumnMeta columnMeta = extractColumnInfo(tableMetaInfo, field);
        if (rowKeyMeta == null && columnMeta == null) {
          continue;
        }
        Class<?> fieldTypeClazz = reflector.getGetterType(field.getName());
        if (rowKeyMeta != null) {
          HBaseRow.Builder rowBuilder = HBaseRow.newBuilder(fieldTypeClazz, rowKeyMeta.getName());
          rowBuilder.setRowKeyGenerator(rowKeyMeta.getRowKeyGenerator());
          // 设置row key setter and getter
          rowBuilder.setGetMethodInvoker(reflector.getGetInvoker(field.getName()));
          rowBuilder.setSetMethodInvoker(reflector.getSetInvoker(field.getName()));
          tableMeta.appendColumnMeta(rowBuilder.build());
          continue;
        }

        HBaseQualifier.Builder qualifierBuilder = HBaseQualifier.newBuilder(fieldTypeClazz, columnMeta.getName());
        qualifierBuilder.setFamily(columnMeta.getFamily());
        qualifierBuilder.setQualifier(columnMeta.getQualifier());
        qualifierBuilder.setNullable(columnMeta.isNullable());
        // 设置column setter and getter
        qualifierBuilder.setGetMethodInvoker(reflector.getGetInvoker(field.getName()));
        qualifierBuilder.setSetMethodInvoker(reflector.getSetInvoker(field.getName()));
        tableMeta.appendColumnMeta(qualifierBuilder.build());
        colCount += 1;
      }

      if (colCount < 1) {
        throw new InvalidTableModelClassException(String.format(
          "HBase table model class %s should contain at least one field definition.",
          clazz.getName()));
      }
      tableMetas.put(clazz, tableMeta);
      return tableMeta;
    }
  }

  static class TableMeta {
    private final String namespace;
    private final String tableName;
    private final String defaultFamily;

    TableMeta(String namespace, String tableName, String defaultFamily) {
      this.namespace = namespace;
      this.tableName = tableName;
      this.defaultFamily = defaultFamily;
    }

    public String getTableName() {
      if (StringUtil.isBlank(namespace)) {
        return tableName;
      }
      return HBaseConstants.formatTableName(namespace, tableName);
    }

    public boolean defaultFamilyHasSet() {
      return StringUtil.isNotBlank(this.getDefaultFamily());
    }

    public String getDefaultFamily() {
      return defaultFamily;
    }
  }

  /**
   * Get the table meta based on the incoming HBase table model class,<br/>
   * If there is no {@link HBaseTable} annotation, throws exception
   * {@link com.hydraql.common.exception.InvalidTableModelClassException}<br/>
   * If the namespace is explicitly specified it is empty, then use the default namespace: default
   * @return table meta info
   */
  private TableMeta extractTableInfo(Class<?> clazz) {
    Assert.checkNotNull(clazz);
    if (!clazz.isAnnotationPresent(HBaseTable.class)) {
      throw new InvalidTableModelClassException(String.format(
              "The model class %s does not contain the HBaseTable annotation.", clazz.getName()));
    }
    HBaseTable table = clazz.getAnnotation(HBaseTable.class);
    String tabledName = table.tableName();
    if (StringUtil.isBlank(tabledName)) {
      throw new InvalidTableModelClassException(
              "Table name is not defined in the model class " + clazz.getName());
    }
    String namespace = table.namespace();
    //todo 检查family的命名格式是否符合要求
    String defaultFamily = table.defaultFamily();
    return new TableMeta(namespace, tabledName, defaultFamily);
  }

  static class RowKeyMeta {
    private final String name;
    private final RowKeyGenerator rowKeyGenerator;

    RowKeyMeta(String name, RowKeyGenerator rowKeyGenerator) {
      this.name = name;
      this.rowKeyGenerator = rowKeyGenerator;
    }

    public String getName() {
      return name;
    }

    public RowKeyGenerator getRowKeyGenerator() {
      return rowKeyGenerator;
    }
  }

  private RowKeyMeta extractRowKeyInfo(Field field) {
    boolean fieldIsRow = field.isAnnotationPresent(HBaseRowKey.class);
    boolean fieldIsColumn = field.isAnnotationPresent(HBaseColumn.class);
    if (!fieldIsRow) {
      return null;
    }
    if (fieldIsColumn) {
      throw new InvalidTableModelClassException("A field cannot be defined by HBaseColumn and HBaseRow at the same time.");
    }
    boolean rowKeyGenerator = field.isAnnotationPresent(GeneratedValue.class);
    if (rowKeyGenerator) {
      GenerationType strategy = field.getAnnotation(GeneratedValue.class).strategy();
      if (strategy == null) {
        strategy = GenerationType.NOTHING;
      }
      return new RowKeyMeta(field.getName(), strategy.getRowKeyGenerator());
    }
    return new RowKeyMeta(field.getName(), GenerationType.NOTHING.getRowKeyGenerator());
  }

  static class ColumnMeta {
    private final String name;
    private final String family;
    private final String qualifier;
    private final boolean nullable;

    ColumnMeta(String name, String family, String qualifier, boolean nullable) {
      this.name = name;
      this.family = family;
      this.qualifier = qualifier;
      this.nullable = nullable;
    }

    public String getName() {
      return name;
    }

    public String getFamily() {
      return family;
    }

    public String getQualifier() {
      return qualifier;
    }

    public boolean isNullable() {
      return nullable;
    }
  }

  private ColumnMeta extractColumnInfo(TableMeta tableMeta, Field field) {
    if (!field.isAnnotationPresent(HBaseColumn.class)) {
      return null;
    }
    String family = tableMeta.getDefaultFamily();
    String qualifier = field.getName();

    HBaseColumn column = field.getAnnotation(HBaseColumn.class);
    boolean rowKeyGenerator = field.isAnnotationPresent(GeneratedValue.class);
    if (rowKeyGenerator) {
      throw new InvalidTableModelClassException("Row key generator can only be used to describe row key.");
    }
    if (StringUtil.isNotBlank(column.qualifier())) {
      qualifier = column.qualifier();
    }
    if (StringUtil.isNotBlank(column.family())) {
      family = column.family();
    }
    if (!tableMeta.defaultFamilyHasSet() && StringUtil.isBlank(family)) {
      throw new InvalidTableModelClassException(String.format(
        "The hbase model class does not define a default column family, "
            + "and the field %s also does not define a column family.", field.getName()));
    }
    //todo 校验family是合法格式

    return new ColumnMeta(field.getName(), family, qualifier, column.nullable());
  }
}
