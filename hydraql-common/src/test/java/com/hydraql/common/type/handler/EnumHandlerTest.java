package com.hydraql.common.type.handler;

import com.hydraql.common.util.BytesUtil;
import org.junit.Test;

/**
 * @author leojie@apache.org 2024/7/19 23:21
 */
public class EnumHandlerTest {
    enum TestEnum {
        SUCCESS(1, "success"),
        FAILED(2, "failed");

        private final int code;
        private final String msg;


        TestEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    @Test
    public void test() {
        EnumHandler enumHandler = new EnumHandler();
        byte[] bytes = enumHandler.toBytes(TestEnum.SUCCESS);
        System.out.println(BytesUtil.toString(bytes));
        TestEnum testEnum =(TestEnum) enumHandler.toObject(TestEnum.class, bytes);
        System.out.println(testEnum.getMsg());
        System.out.println(testEnum);
    }
}
