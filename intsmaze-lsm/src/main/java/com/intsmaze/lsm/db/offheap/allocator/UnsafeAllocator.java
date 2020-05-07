package com.intsmaze.lsm.db.offheap.allocator;


import com.intsmaze.lsm.db.offheap.JVMUnsafe;

import sun.misc.Unsafe;

/**
 * An off-heap memory allocator that uses sun.misc.Unsafe
 */
public class UnsafeAllocator implements Allocator {

    private static final Unsafe unsafe = JVMUnsafe.unsafe;

    /**
    * @Description: 分配size字节大小的内存，返回起始地址偏移量
    * @Param: 
    * @return: 
    * @Author: intsmaze
    * @Date: 2019/2/1
    */ 
    @Override
    public long allocate(long size) {
        return unsafe.allocateMemory(size);
    }

    @Override
    public void deallocate(long address) {
        unsafe.freeMemory(address);
    }
}
