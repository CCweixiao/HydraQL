package com.github.CCweixiao.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import static com.github.CCweixiao.util.HBaseThriftProtocol.CHAR_SET;

/**
 * @author leojie 2020/12/27 2:36 下午
 */
public class ByteBufferUtil {
    /**
     * 字节数组数组转字符串
     *
     * @param buffer 字节数组
     * @return 字符串
     */
    public static String byteBufferToString(ByteBuffer buffer) {
        CharBuffer charBuffer;
        try {
            Charset charset = Charset.forName(CHAR_SET);
            CharsetDecoder decoder = charset.newDecoder();
            charBuffer = decoder.decode(buffer);
            buffer.flip();
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ByteBuffer getByteBufferFromString(String str){
        if(str == null){
            str = "";
        }
        return ByteBuffer.wrap(str.getBytes());
    }


}
