package com.intsmaze.hash.util;

import java.io.UnsupportedEncodingException;


/**
 * The only reason to have this is to be able to compatible with java 1.5 :(
 */
public class SafeEncoder {
    public static byte[][] encodeMany(final String... strs) {
        byte[][] many = new byte[strs.length][];
        for (int i = 0; i < strs.length; i++) {
            many[i] = encode(strs[i]);
        }
        return many;
    }

    public static byte[] encode(final String str) {
        try {
            if (str == null) {
                return null;
            }
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String encode(final byte[] data) {
        try {
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
