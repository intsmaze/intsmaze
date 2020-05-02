package com.intsmaze.lsm.db.offheap.allocator;

/**
 * An off-heap memory allocator.
 */
public interface Allocator {

    public long allocate(long bytes);

    public void deallocate(long address);
}
