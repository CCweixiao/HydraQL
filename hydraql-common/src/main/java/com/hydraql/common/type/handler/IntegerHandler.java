package com.hydraql.common.type.handler;

import com.hydraql.common.type.AbstractTypeHandler;
import com.hydraql.common.util.BytesUtil;

/**
 * @author leojie 2020/11/28 7:51 下午
 */
public class IntegerHandler extends AbstractTypeHandler<Integer> {
    @Override
    protected boolean matchConverterType(Class<?> type) {
        return type == int.class || type == Integer.class;
    }

    @Override
    protected byte[] convertObjValToByteArr(Class<?> type, Object value) {
        if (value == null) {
            return null;
        }
        int val = convertByteArrToObjVal(value.toString(), Integer::parseInt);
        return BytesUtil.toBytes(val);
    }

    @Override
    protected Object convertByteArrToObjVal(Class<?> type, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return BytesUtil.toInt(bytes);
    }

    @Override
    public String extractMatchTtypeValue(String value) {
        return toString(value, Integer::parseInt);
    }
}
