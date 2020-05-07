package com.intsmaze.hash.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/6 10:00
 * 
 */
public interface Hashing {
    public static final Hashing MURMUR_HASH = new MurmurHash();
    public ThreadLocal<MessageDigest> MD5_HOLDER = new ThreadLocal<MessageDigest>();

    public static final Hashing MD5 = new Hashing() {
        @Override
        public long hash(String key) {
            return hash(SafeEncoder.encode(key));
        }

        @Override
        public long hash(byte[] key) {
            try {
                if (MD5_HOLDER.get() == null) {
                    MD5_HOLDER.set(MessageDigest.getInstance("MD5"));
                }
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("++++ no md5 algorythm found");
            }
            MessageDigest md5 = MD5_HOLDER.get();

            md5.reset();
            md5.update(key);
            byte[] bKey = md5.digest();
            long res = ((long) (bKey[3] & 0xFF) << 24) | ((long) (bKey[2] & 0xFF) << 16)
                    | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF);
            return res;
        }

        @Override
        public void close()
        {
            MD5_HOLDER.remove();
        }
    };

    public long hash(String key);

    public long hash(byte[] key);

    public void close();
}
