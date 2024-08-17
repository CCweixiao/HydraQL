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

package com.hydraql.core.metadata;

import com.hydraql.core.exceptions.InvalidTableModelClassException;
import com.hydraql.common.util.StringUtil;
import com.hydraql.core.annotations.GeneratedValue;
import com.hydraql.core.annotations.HBaseField;
import com.hydraql.core.annotations.HBaseRowKey;
import com.hydraql.core.annotations.HBaseTable;
import com.hydraql.core.rowkey.GenerationType;
import com.hydraql.core.rowkey.RowKeyGenerator;
import com.hydraql.core.toolkit.AnnotationUtils;
import com.hydraql.core.toolkit.TableNameUtils;
import com.hydraql.core.type.SimpleTypeRegistry;
import com.hydraql.reflectasm.Reflector;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author leojie 2022/11/20 10:50
 */
public class HBaseTableInfoHelper {

  private static final Map<Class<?>, HBaseTableInfo> TABLE_INFO_CACHE = new ConcurrentHashMap<>();

  private HBaseTableInfoHelper() {
  }

  public static void remove(Class<?> clazz) {
    TABLE_INFO_CACHE.remove(clazz);
  }

  public static HBaseTableInfo getTableInfo(Class<?> clazz) {
    if (clazz == null || clazz.isPrimitive() || SimpleTypeRegistry.isSimpleType(clazz) || clazz.isInterface()) {
      throw new InvalidTableModelClassException("Invalid table model class info.");
    }

    HBaseTableInfo tableInfo = TABLE_INFO_CACHE.get(clazz);
    if (null != tableInfo) {
      return tableInfo;
    }

    synchronized (HBaseTableInfoHelper.class) {
      tableInfo = TABLE_INFO_CACHE.get(clazz);
      if (null != tableInfo) {
        return tableInfo;
      }

      TableMeta tableMeta = extractTableMeta(clazz);
      Reflector reflector = new Reflector(clazz);
      tableInfo = HBaseTableInfo.newBuilder(clazz)
          .setDefaultConstructor(reflector.getDefaultConstructor())
          .setTableName(tableMeta.getTableName()).build();

      int colCount = 0;
      for (Field field : reflector.getFields()) {
        if (Reflector.isNotGeneralProperty(field)) {
          continue;
        }
        RowKeyMeta rowKeyMeta = extractRowKeyInfo(field);
        ColumnMeta columnMeta = extractColumnInfo(tableMeta, field);
        if (rowKeyMeta == null && columnMeta == null) {
          continue;
        }

        Class<?> fieldTypeClazz = reflector.getGetterType(field.getName());
        if (rowKeyMeta != null) {
          HBaseRowKeyInfo.Builder rowBuilder = HBaseRowKeyInfo.newBuilder(fieldTypeClazz, rowKeyMeta.getName());
          rowBuilder.setRowKeyGenerator(rowKeyMeta.getRowKeyGenerator());
          // 设置row key setter and getter
          rowBuilder.setGetMethodInvoker(reflector.getGetInvoker(field.getName()));
          rowBuilder.setSetMethodInvoker(reflector.getSetInvoker(field.getName()));
          tableInfo.appendColumnMeta(rowBuilder.build());
          continue;
        }

        HBaseQualifierInfo.Builder qualifierBuilder =
            HBaseQualifierInfo.newBuilder(fieldTypeClazz, columnMeta.getName());
        qualifierBuilder.setFamily(columnMeta.getFamily());
        qualifierBuilder.setQualifier(columnMeta.getQualifier());
        qualifierBuilder.setNullable(columnMeta.isNullable());
        // 设置column setter and getter
        qualifierBuilder.setGetMethodInvoker(reflector.getGetInvoker(field.getName()));
        qualifierBuilder.setSetMethodInvoker(reflector.getSetInvoker(field.getName()));
        tableInfo.appendColumnMeta(qualifierBuilder.build());
        colCount += 1;
      }

      if (colCount < 1) {
        throw new InvalidTableModelClassException(String.format(
          "The table model class %s should contain at least one field definition.",
          clazz.getName()));
      }
      TABLE_INFO_CACHE.put(clazz, tableInfo);
      return tableInfo;
    }
  }

  private static class TableMeta {
    private final String namespace;
    private final String tableName;
    private final String defaultFamily;

    public TableMeta(String namespace, String tableName, String defaultFamily) {
      this.namespace = namespace;
      this.tableName = tableName;
      this.defaultFamily = defaultFamily;
    }

    public String getTableName() {
      return TableNameUtils.format(namespace, tableName);
    }

    public boolean defaultFamilyIsSet() {
      return StringUtil.isNotBlank(this.getDefaultFamily());
    }

    public String getDefaultFamily() {
      return defaultFamily;
    }
  }

  /**
   * Get the table meta based on the incoming HBase table model class,<br/>
   * If there is no {@link HBaseTable} annotation, throws exception
   * {@link InvalidTableModelClassException}<br/>
   * If the namespace is explicitly specified it is empty, then use the default namespace: default
   * @return table meta info
   */
  private static TableMeta extractTableMeta(Class<?> clazz) {
    HBaseTable table = AnnotationUtils.getAnnotation(clazz, HBaseTable.class);
    if (table == null) {
      throw new InvalidTableModelClassException(String.format(
              "Use @HBaseTable annotation to define table info metadata for model class %s.", clazz.getName()));
    }
    String tabledName = table.tableName();
    if (StringUtil.isBlank(tabledName)) {
      throw new InvalidTableModelClassException(
          "Table name is not defined in the @HBaseTable annotation of model class " + clazz.getName());
    }
    String namespace = table.namespace();
    // todo 检查family的命名格式是否符合要求
    String defaultFamily = table.defaultFamily();
    return new TableMeta(namespace, tabledName, defaultFamily);
  }

  private static class RowKeyMeta {
    private final String name;
    private final RowKeyGenerator rowKeyGenerator;

    public RowKeyMeta(String name, RowKeyGenerator rowKeyGenerator) {
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

  private static RowKeyMeta extractRowKeyInfo(Field field) {
    boolean fieldIsRow = AnnotationUtils.isAnnotationPresent(field, HBaseRowKey.class);
    boolean fieldIsColumn = AnnotationUtils.isAnnotationPresent(field, HBaseField.class);
    if (!fieldIsRow) {
      return null;
    }
    if (fieldIsColumn) {
      throw new InvalidTableModelClassException(
          "The field of model class cannot be defined by @HBaseField and @HBaseRowKey at the same time.");
    }
    GeneratedValue rowKeyGenerator = AnnotationUtils.getAnnotation(field, GeneratedValue.class);
    if (null != rowKeyGenerator) {
      GenerationType strategy = rowKeyGenerator.strategy();
      if (strategy == null) {
        strategy = GenerationType.NOTHING;
      }
      return new RowKeyMeta(field.getName(), strategy.getRowKeyGenerator());
    }
    return new RowKeyMeta(field.getName(), GenerationType.NOTHING.getRowKeyGenerator());
  }

  private static class ColumnMeta {
    private final String name;
    private final String family;
    private final String qualifier;
    private final boolean nullable;

    public ColumnMeta(String name, String family, String qualifier, boolean nullable) {
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

  private static ColumnMeta extractColumnInfo(TableMeta tableMeta, Field field) {
    HBaseField column = AnnotationUtils.getAnnotation(field, HBaseField.class);

    if (null == column) {
      return null;
    }
    String family = tableMeta.getDefaultFamily();
    String qualifier = field.getName();

    if (AnnotationUtils.isAnnotationPresent(field, GeneratedValue.class)) {
      throw new InvalidTableModelClassException(
          "Row key generator can only be used to describe row key.");
    }

    if (StringUtil.isNotBlank(column.qualifier())) {
      qualifier = column.qualifier();
    }

    if (StringUtil.isNotBlank(column.family())) {
      family = column.family();
    }

    if (!tableMeta.defaultFamilyIsSet() && StringUtil.isBlank(family)) {
      throw new InvalidTableModelClassException(
          String.format("The hbase model class does not define a default column family, "
              + "and the field %s also does not define a column family.",
            field.getName()));
    }
    // todo 校验family是合法格式

    return new ColumnMeta(field.getName(), family, qualifier, column.nullable());
  }
}
