package com.hydraql.adapter;

import com.hydraql.common.exception.HBaseMetaDataException;
import com.hydraql.common.exception.HBaseOperationsException;
import com.hydraql.common.lang.MyAssert;
import com.hydraql.common.meta.FieldStruct;
import com.hydraql.common.meta.HBaseTableMeta;
import com.hydraql.common.meta.ReflectFactory;
import com.hydraql.common.util.StringUtil;
import com.hydraql.common.query.FamilyQualifierUtil;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;

/**
 * @author leojie 2023/7/20 19:36
 */
public interface IHBaseTableDeleteAdapter {
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

    default  <T> Delete buildDelete(T t) throws HBaseMetaDataException {
        Class<?> clazz = t.getClass();
        HBaseTableMeta tableMeta = ReflectFactory.getInstance().register(clazz);
        List<FieldStruct> fieldStructList = tableMeta.getFieldStructList();
        FieldStruct rowFieldStruct = fieldStructList.get(0);
        if (!rowFieldStruct.isRowKey()) {
            throw new HBaseMetaDataException("The first field is not row key, please check hbase table mata data.");
        }
        Object value = tableMeta.getMethodAccess().invoke(t, rowFieldStruct.getGetterMethodIndex());
        MyAssert.checkArgument(value != null, "The value of row key must not be null.");
        return new Delete(rowFieldStruct.getTypeHandler().toBytes(rowFieldStruct.getType(), value));
    }
}
