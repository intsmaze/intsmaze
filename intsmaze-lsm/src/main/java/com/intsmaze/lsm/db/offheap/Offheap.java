package com.intsmaze.lsm.db.offheap;

/**
 * Indicates that the implementing class is backed by off-heap memory.
 */
public interface Offheap {

    public MemoryPointer memory();

}
