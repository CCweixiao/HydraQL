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

import com.hydraql.common.constants.HydraQLConstants;
import com.hydraql.exceptions.HBaseMetaDataException;
import com.hydraql.metadata.HFieldInfo;
import com.hydraql.metadata.HTableInfoContainer;
import com.hydraql.metadata.HTableInfo;
import com.hydraql.type.ColumnType;
import com.hydraql.type.TypeHandler;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;
import java.util.Map;

/**
 * @author leojie 2023/7/20 19:32
 */
public interface UpsertService {

  default Put buildPut(String rowKey, Map<String, Object> data) {
    if (StringUtil.isBlank(rowKey)) {
      throw new IllegalArgumentException("Row key must not be empty.");
    }

    Put put = new Put(Bytes.toBytes(rowKey));
    data.forEach((fieldName, fieldValue) -> {
      String[] familyQualifierArr = fieldName.split(HydraQLConstants.FAMILY_QUALIFIER_SEPARATOR);
      TypeHandler<?> fieldTypeHandler = ColumnType.findTypeHandler(fieldValue.getClass());
      put.addColumn(Bytes.toBytes(familyQualifierArr[0]), Bytes.toBytes(familyQualifierArr[1]),
        ColumnType.StringType.getTypeHandler().toBytes(fieldTypeHandler.toString(fieldValue)));
    });
    return put;
  }

  default <T> Put buildPut(T t) throws HBaseMetaDataException {
    Class<?> clazz = t.getClass();
    HTableInfo tableInfo = HTableInfoContainer.getInstance().get(clazz);
    HFieldInfo.RowKey rowKey = tableInfo.getRowKey();
    List<HFieldInfo.Qualifier> qualifiers = tableInfo.getQualifiers();

    byte[] value = rowKey.getBytesValue(t);
    Put put = new Put(value);

    qualifiers.forEach(qualifier -> {
      byte[] valueOfCol = qualifier.getBytesValue(t);
      put.addColumn(qualifier.getFamily(), qualifier.getQualifier(), valueOfCol);
    });
    return put;
  }
}
