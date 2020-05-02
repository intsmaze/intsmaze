package com.intsmaze.lsm.db.offheap;

import java.lang.reflect.Field;

/**
 * Provides sane access to sun.misc.Unsafe
 */
public class JVMUnsafe {

    public static final sun.misc.Unsafe unsafe;

    static {
        try {
            Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (sun.misc.Unsafe) field.get(null);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
