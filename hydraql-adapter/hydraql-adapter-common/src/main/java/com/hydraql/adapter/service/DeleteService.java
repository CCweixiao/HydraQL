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
import com.hydraql.common.meta.HBaseField;
import com.hydraql.common.meta.HBaseMetaContainer;
import com.hydraql.common.meta.HBaseTableSchema;
import com.hydraql.common.query.FamilyQualifierUtil;
import com.hydraql.common.util.StringUtil;
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
    HBaseTableSchema tableMeta = HBaseMetaContainer.getInstance().stuff(clazz);
    List<HBaseField> fields = tableMeta.getFields();
    HBaseField field = fields.get(0);
    if (!field.isRowKey()) {
      throw new HBaseMetaDataException(
          "The first field is not row key, please check hbase table mata data.");
    }
    return new Delete(field.getByteValue(t));
  }
}
