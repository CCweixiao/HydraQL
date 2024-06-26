package com.hydraql.common.type.handler;

import com.hydraql.common.lang.Assert;
import com.hydraql.common.type.AbstractTypeHandler;
import com.hydraql.common.util.BytesUtil;

import java.util.Date;

/**
 * @author leojie 2020/11/28 7:53 下午
 */
public class DateHandler extends AbstractTypeHandler<Date> {
    @Override
    protected boolean matchConverterType(Class<?> type) {
        return type == Date.class;
    }

    @Override
    protected byte[] convertObjValToByteArr(Class<?> type, Object value) {
        if (value == null) {
            return null;
        }
        long time = ((Date) value).getTime();
        return BytesUtil.toBytes(time);
    }

    @Override
    protected Object convertByteArrToObjVal(Class<?> type, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        long time = BytesUtil.toLong(bytes);
        return new Date(time);
    }

    @Override
    public String toString(Object val) {
        Assert.checkArgument(this.matchConverterType(val.getClass()), "The type of value " + val + " is not Date.");
        Date d = (Date) val;
        return String.valueOf(d.getTime());
    }
    @Override
    public String extractMatchTtypeValue(String value) {
        return toString(value, v -> new Date(Long.parseLong(v)));
    }
}
