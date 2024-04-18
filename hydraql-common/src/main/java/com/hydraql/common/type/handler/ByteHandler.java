package com.hydraql.common.type.handler;

import com.hydraql.common.exception.HBaseColumnTypeCastException;
import com.hydraql.common.lang.Assert;
import com.hydraql.common.type.AbstractTypeHandler;

/**
 * @author leojie 2020/11/28 7:55 下午
 */
public class ByteHandler extends AbstractTypeHandler<Byte> {
    @Override
    protected boolean matchConverterType(Class<?> type) {
        return type == byte.class || type == Byte.class;
    }

    @Override
    protected byte[] convertObjValToByteArr(Class<?> type, Object value) {
        return new byte[] {(Byte) value};
    }

    @Override
    protected Object convertByteArrToObjVal(Class<?> type, byte[] bytes) {
        return bytes[0];
    }

    @Override
    public String extractMatchTtypeValue(String value) {
        throw new HBaseColumnTypeCastException("The string value cast to byte is unsupported");
    }

    @Override
    public String toString(Object val) {
        Assert.checkArgument(this.matchConverterType(val.getClass()), "The type of value " + val + " is not Byte or byte.");

        byte b = (byte) val;
        return String.valueOf(b);
    }
}
