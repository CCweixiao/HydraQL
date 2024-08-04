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

package com.hydraql.adapter.service;

import com.hydraql.common.exception.HBaseMetaDataException;
import com.hydraql.common.exception.HBaseOperationsException;
import com.hydraql.common.lang.Assert;
import com.hydraql.common.schema.HBaseField;
import com.hydraql.common.schema.HBaseTableSchema;
import com.hydraql.common.schema.ReflectFactory;
import com.hydraql.common.util.StringUtil;
import com.hydraql.common.query.FamilyQualifierUtil;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;

/**
 * @author leojie 2023/7/20 19:36
 */
public interface DeleteService {
  default Delete buildDeleteCondition(String rowKey, String familyName, List<String> qualifiers) {
    if (StringUtil.isBlank(rowKey)) {
      throw new HBaseOperationsException("RowKey must not be empty.");
    }
    Delete delete = new Delete(Bytes.toBytes(rowKey));
    if (FamilyQualifierUtil.familyNameOnly(familyName, qualifiers)) {
      delete.addFamily(Bytes.toBytes(familyName));
    }
    if (FamilyQualifierUtil.familyAndColumnNames(familyName, qualifiers)) {
      qualifiers.forEach(qualifier -> {
        if (StringUtil.isNotBlank(qualifier)) {
          delete.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(qualifier));
        }
      });
    }
    return delete;
  }

  default <T> Delete buildDelete(T t) throws HBaseMetaDataException {
    Class<?> clazz = t.getClass();
    HBaseTableSchema tableMeta = ReflectFactory.getInstance().register(clazz);
    List<HBaseField> fieldStructList = tableMeta.getFieldStructList();
    HBaseField rowFieldStruct = fieldStructList.get(0);
    if (!rowFieldStruct.isRowKey()) {
      throw new HBaseMetaDataException(
          "The first field is not row key, please check hbase table mata data.");
    }
    Object value = tableMeta.getMethodAccess().invoke(t, rowFieldStruct.getGetterMethodIndex());
    Assert.checkArgument(value != null, "The value of row key must not be null.");
    return new Delete(rowFieldStruct.getTypeHandler().toBytes(rowFieldStruct.getType(), value));
  }
}
