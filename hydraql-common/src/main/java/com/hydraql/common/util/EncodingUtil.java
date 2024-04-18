package com.hydraql.common.util;

import com.hydraql.common.lang.Preconditions;

/**
 * @author leojie 2020/11/28 12:00 下午
 */
public class EncodingUtil {
    private static final String[] int2Hex = new String[] { "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", };

    /**
     * Convert bytes to hex string.
     * */
    public static String toHexString(byte[] bytes) {
        Preconditions.checkIsNotNull(bytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(toHexString(b)).append(" ");
        }
        return sb.toString();
    }

    /**
     * Convert byte to hex string.
     * */
    private static String toHexString(byte b) {
        int r1 = (b >>> 4) & 0xF;
        int r2 = b & 0xF;
        return int2Hex[r1] + int2Hex[r2];
    }

    /**
     * Convert hex string to bytes.
     * */
    public static byte[] parseBytesFromHexString(String hexStr) {
        if (StringUtil.isBlank(hexStr)) {
            return new byte[0];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < hexStr.length(); i++) {

            char c = hexStr.charAt(i);

            if (c >= '0' && c <= '9') {
                sb.append(c);
                continue;
            }
            if (c >= 'A' && c <= 'F') {
                sb.append(c);
                continue;
            }
            if (c >= 'a' && c <= 'f') {
                sb.append(c);
            }

            //only accept 0-9 A-Z a-z, drop other char.
        }

        return parseBytesFromHexString_0(sb.toString());
    }

    private static byte[] parseBytesFromHexString_0(String hexStr) {
        if (StringUtil.isBlank(hexStr)) {
            return new byte[0];
        }
        Preconditions.checkArgument(hexStr.length() % 2 == 0,
                "error hexStr [%s] length", hexStr);

        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < result.length; i++) {
            String s = hexStr.substring(i * 2, i * 2 + 2);
            int value = Integer.parseInt(s, 16);
            result[i] = (byte) value;
        }
        return result;
    }

    public static void main(String[] args) {
        byte[] bytes = parseBytesFromHexString("ffff");
        System.out.println(toHexString(bytes));
    }
}
