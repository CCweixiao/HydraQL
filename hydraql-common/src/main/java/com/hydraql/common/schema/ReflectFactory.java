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

package com.hydraql.common.schema;

import com.hydraql.reflectasm.FieldAccess;
import com.hydraql.reflectasm.MethodAccess;
import com.hydraql.common.annotation.HBaseRowKey;
import com.hydraql.common.annotation.HBaseTable;
import com.hydraql.common.exception.InvalidTableModelClassException;
import com.hydraql.common.lang.Assert;
import com.hydraql.common.type.ColumnType;
import com.hydraql.common.util.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static com.hydraql.common.constants.HBaseConstants.DEFAULT_NAMESPACE_NAME;
import static com.hydraql.common.constants.HBaseConstants.FAMILY_QUALIFIER_SEPARATOR;

/**
 * @author leojie 2022/11/20 10:50
 */
public class ReflectFactory {

  private static volatile ReflectFactory factory;
  private final ConcurrentMap<Class<?>, HBaseTableSchema> tableMetas = new ConcurrentHashMap<>();
  private final Object lock = new Object();

  private ReflectFactory() {
  }

  public static ReflectFactory getInstance() {
    if (factory == null) {
      synchronized (ReflectFactory.class) {
        if (factory == null) {
          factory = new ReflectFactory();
        }
      }
    }
    return factory;
  }

  public void unregister(Class<?> clazz) {
    synchronized (lock) {
      HBaseTableSchema existsTableMeta = tableMetas.get(clazz);
      if (existsTableMeta != null) {
        tableMetas.remove(clazz);
      }
    }
  }

  public HBaseTableSchema register(Class<?> clazz) {
    HBaseTableSchema existsTableMeta = tableMetas.get(clazz);
    if (existsTableMeta != null) {
      return existsTableMeta;
    }

    synchronized (lock) {
      existsTableMeta = tableMetas.get(clazz);
      if (existsTableMeta != null) {
        return existsTableMeta;
      }

      String tableName = getTableName(clazz);
      MethodAccess methodAccess = MethodAccess.get(clazz);
      Map<String, Method> allMethodsMap = getAllMethodsMap(clazz);
      FieldAccess fieldAccess = FieldAccess.get(clazz);
      Field[] fields = fieldAccess.getFields();
      int rowCount = 0;
      int colCount = 0;

      List<HBaseField> fieldStructList = new LinkedList<>();
      Method getMethod;
      Method setMethod;

      for (Field field : fields) {
        if (isNotGeneralProperty(field)) {
          continue;
        }
        boolean fieldIsRow = field.isAnnotationPresent(HBaseRowKey.class);
        boolean filedIsCol =
            field.isAnnotationPresent(com.hydraql.common.annotation.HBaseColumn.class);
        if (!fieldIsRow && !filedIsCol) {
          continue;
        }

        HBaseField fieldStruct = new HBaseField();
        if (fieldIsRow) {
          fieldStruct.setRowKey(true);
          rowCount += 1;
        }
        if (rowCount > 1) {
          throw new InvalidTableModelClassException(
              String.format("The model class %s contains more than one row key.", clazz.getName()));
        }
        // field type
        fieldStruct.setType(field.getType());
        fieldStruct.setTypeHandler(ColumnType.findTypeHandler(field.getType()));

        // setter
        String setterMethodName = getSetterMethodName(field);
        setMethod = allMethodsMap.getOrDefault(setterMethodName, null);
        if (setMethod == null) {
          throw new InvalidTableModelClassException(String.format(
            "Define a standard setter method: %s for field: %s in table model class %s",
            setterMethodName, field.getName(), clazz.getName()));
        }
        if (isNotGeneralSetterMethod(setMethod)) {
          throw new InvalidTableModelClassException(String.format(
            "The setter method: %s for field: %s in table model class %s is not in standard format",
            setterMethodName, field.getName(), clazz.getName()));
        }
        fieldStruct.setSetterMethodName(setterMethodName);
        int setterMethodIndex = methodAccess.getIndex(setterMethodName);
        fieldStruct.setSetterMethodIndex(setterMethodIndex);

        // getter
        String getterMethodName = getGetterMethodName(field);
        getMethod = allMethodsMap.getOrDefault(getterMethodName, null);
        if (getMethod == null) {
          throw new InvalidTableModelClassException(String.format(
            "Define a standard getter method: %s for field: %s in table model class %s",
            getterMethodName, field.getName(), clazz.getName()));
        }
        if (isNotGeneralGetterMethod(getMethod)) {
          throw new InvalidTableModelClassException(String.format(
            "The getter method: %s for field: %s in table model class %s is not in standard format",
            getterMethodName, field.getName(), clazz.getName()));
        }
        fieldStruct.setGetterMethodName(getterMethodName);
        int getMethodIndex = methodAccess.getIndex(getterMethodName);
        fieldStruct.setGetterMethodIndex(getMethodIndex);

        if (fieldIsRow) {
          fieldStructList.add(0, fieldStruct);
          continue;
        }

        // family and qualifier
        String[] fqArr = getFamilyAndQualifier(clazz, field);
        fieldStruct.setFamily(fqArr[0]);
        fieldStruct.setQualifier(fqArr[1]);
        fieldStruct.setFamilyAndQualifier(String.format("%s:%s", fqArr[0], fqArr[1]));
        fieldStructList.add(fieldStruct);
        colCount += 1;
      }
      if (rowCount < 1) {
        throw new InvalidTableModelClassException(
            String.format("HBase table model class %s must have a row key.", clazz.getName()));
      }
      if (colCount < 1) {
        throw new InvalidTableModelClassException(String.format(
          "HBase table model class %s should contain at least one field definition.",
          clazz.getName()));
      }
      HBaseTableSchema tableMeta = new HBaseTableSchema();
      tableMeta.setTableName(tableName);
      tableMeta.setMethodAccess(methodAccess);
      tableMeta.setFieldAccess(fieldAccess);
      tableMeta.setFieldStructList(fieldStructList);
      tableMetas.put(clazz, tableMeta);
      return tableMeta;
    }
  }

  /**
   * Get the table name based on the incoming HBase table model class,<br/>
   * If there is no {@link HBaseTable} annotation, throws exception
   * {@link com.hydraql.common.exception.InvalidTableModelClassException}<br/>
   * If the namespace is explicitly specified it is empty, then use the default namespace: default
   * @param clazz table model class
   * @return table name, including namespace
   */
  private static String getTableName(Class<?> clazz) {
    Assert.checkNotNull(clazz);

    if (clazz.isAnnotationPresent(HBaseTable.class)) {
      HBaseTable table = clazz.getAnnotation(HBaseTable.class);
      if (StringUtil.isNotBlank(table.tableName())) {
        return StringUtil.isBlank(table.namespace())
            ? String.format("%s:%s", DEFAULT_NAMESPACE_NAME, table.tableName())
            : String.format("%s:%s", table.namespace(), table.tableName());
      } else {
        throw new InvalidTableModelClassException(
            "Table name is not defined in table model class " + clazz.getName());
      }
    } else {
      throw new InvalidTableModelClassException(String.format(
        "The model class %s does not contain the HBaseTable annotation.", clazz.getName()));
    }
  }

  private static String getTableDefaultFamily(Class<?> clazz) {
    String defaultFamily = "";
    if (clazz.isAnnotationPresent(HBaseTable.class)) {
      defaultFamily = clazz.getAnnotation(HBaseTable.class).defaultFamily();
    }
    if (defaultFamily.contains(FAMILY_QUALIFIER_SEPARATOR)) {
      throw new InvalidTableModelClassException(
          "The default family name defined in the table model include ':'");
    }
    return defaultFamily;
  }

  private static String[] getFamilyAndQualifier(Class<?> clazz, Field field) {
    String qualifier = field.getName();
    String family = getTableDefaultFamily(clazz);
    boolean defaultFamilyIsEmpty = StringUtil.isBlank(family);

    if (field.isAnnotationPresent(com.hydraql.common.annotation.HBaseColumn.class)) {
      com.hydraql.common.annotation.HBaseColumn column =
          field.getAnnotation(com.hydraql.common.annotation.HBaseColumn.class);
      if (StringUtil.isNotBlank(column.qualifier())) {
        qualifier = column.qualifier();
      }
      if (StringUtil.isNotBlank(column.family())) {
        family = column.family();
      }
      if (defaultFamilyIsEmpty && StringUtil.isBlank(family)) {
        throw new InvalidTableModelClassException(String.format(
          "The HBase table model class %s does not contain a default family, "
              + "and the field %s definition does not specify a column family.",
          clazz.getName(), field.getName()));
      }
    }
    if (family.contains(FAMILY_QUALIFIER_SEPARATOR)) {
      throw new InvalidTableModelClassException(
          String.format("The family name %s cannot contain ':'.", family));
    }
    if (qualifier.contains(FAMILY_QUALIFIER_SEPARATOR)) {
      throw new InvalidTableModelClassException(
          String.format("The qualifier name %s cannot contain ':'.", qualifier));
    }
    return new String[] { family, qualifier };
  }

  private static boolean booleanType(Class<?> type) {
    return type == boolean.class || type == Boolean.class;
  }

  /**
   * Generate the standard getter method name of a field
   * @param field field obj
   * @return standard getter method name
   */
  private static String getGetterMethodName(Field field) {
    String fieldName = field.getName();
    StringBuilder sb = new StringBuilder();
    if (booleanType(field.getType()) && fieldName.startsWith("is")) {
      // If the field is of type boolean
      sb.append("is");
      sb.append(fieldName.substring(2).substring(0, 1).toUpperCase());
      sb.append(fieldName.substring(2).substring(1));

    } else {
      sb.append("get");
      sb.append(fieldName.substring(0, 1).toUpperCase());
      sb.append(fieldName.substring(1));
    }
    return sb.toString();
  }

  /**
   * Generate the standard setter method name of a field
   * @param field field obj
   * @return standard setter method name
   */
  private static String getSetterMethodName(Field field) {
    String fieldName = field.getName();
    StringBuilder sb = new StringBuilder();
    if (booleanType(field.getType()) && fieldName.startsWith("is")) {
      // If the field is of type boolean
      sb.append("set");
      sb.append(fieldName.substring(2).substring(0, 1).toUpperCase());
      sb.append(fieldName.substring(2).substring(1));

    } else {
      sb.append("set");
      sb.append(fieldName.substring(0, 1).toUpperCase());
      sb.append(fieldName.substring(1));
    }
    return sb.toString();
  }

  /**
   * Determine whether a field type is a regular attribute<br/>
   * Fields modified with static and final do not belong to a regular field for the time being.
   * @param field field object
   * @return true or false
   */
  private static boolean isNotGeneralProperty(Field field) {
    if (field == null) {
      return true;
    }
    return Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers());
  }

  /**
   * Determine whether a method conforms to the definition of a standard getter method.<br/>
   * The definition of the standard getter method is: <br/>
   * 1. Property getter method cannot be modified with private<br/>
   * 2. The return value type of the getter method is not void
   * @param method method
   * @return true or false
   */
  private static boolean isNotGeneralGetterMethod(Method method) {
    if (method == null) {
      return true;
    }
    return Modifier.isPrivate(method.getModifiers()) || method.getReturnType() == void.class;
  }

  /**
   * Determine whether a method conforms to the definition of a standard setter method.<br/>
   * The definition of the standard setter method is: <br/>
   * 1. Property setter method cannot be modified with private<br/>
   * 2. The return value type of the setter method is void
   * @param method method
   * @return true or false
   */
  private static boolean isNotGeneralSetterMethod(Method method) {
    if (method == null) {
      return true;
    }
    return Modifier.isPrivate(method.getModifiers()) || method.getReturnType() != void.class;
  }

  private static Map<String, Method> getAllMethodsMap(Class<?> clazz) {
    Method[] methods = getAllMethods(clazz);
    return Arrays.stream(methods).collect(Collectors.toMap(Method::getName, field -> field));
  }

  /**
   * Get all methods of a class, including methods of all parent classes.
   * @param clazz class type
   * @return method array
   */
  private static Method[] getAllMethods(Class<?> clazz) {
    Method[] methods = clazz.getDeclaredMethods();
    Class<?> superClazz = clazz.getSuperclass();
    if (superClazz.equals(Object.class)) {
      return methods;
    }
    Method[] tableSuperMethods = superClazz.getDeclaredMethods();
    Method[] superMethods = new Method[methods.length + tableSuperMethods.length];
    System.arraycopy(methods, 0, superMethods, 0, methods.length);
    System.arraycopy(tableSuperMethods, 0, superMethods, methods.length, tableSuperMethods.length);
    return getSuperClassMethods(superMethods, superClazz);
  }

  /**
   * Method to recursively obtain all parent classes of the passed parameter class.
   * @param methods method array
   * @param clazz class type
   * @return method array
   */
  private static Method[] getSuperClassMethods(Method[] methods, Class<?> clazz) {
    Class<?> superClazz = clazz.getSuperclass();
    if (superClazz.equals(Object.class)) {
      return methods;
    }
    Method[] superMethods = superClazz.getDeclaredMethods();
    Method[] c = new Method[methods.length + superMethods.length];
    System.arraycopy(methods, 0, c, 0, methods.length);
    System.arraycopy(superMethods, 0, c, methods.length, superMethods.length);
    getSuperClassMethods(c, superClazz);
    return c;
  }
}
