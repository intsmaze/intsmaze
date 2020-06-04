package com.intsmaze.lsm.db.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;


public class ByteBuffers {

    public static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocate(0);

    public static ByteBuffer fromString(String string) {
        return ByteBuffer.wrap(string.getBytes(Charset.defaultCharset()));
    }

    public static String toString(ByteBuffer byteBuffer) {
        byte[] bytes = new byte[byteBuffer.capacity()];

        for (int i = 0; i < byteBuffer.capacity(); i++) {
            bytes[i] = byteBuffer.get(i);
        }

        return new String(bytes);
    }

    public static byte[] toBytes(ByteBuffer byteBuffer) {
        byte[] bytes = new byte[byteBuffer.capacity()];

        for (int i = 0; i < byteBuffer.capacity(); i++) {
            bytes[i] = byteBuffer.get(i);
        }

        return bytes;
    }
}
