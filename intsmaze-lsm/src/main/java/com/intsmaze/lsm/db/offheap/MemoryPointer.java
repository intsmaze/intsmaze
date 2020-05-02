package com.intsmaze.lsm.db.offheap;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a reference counted pointer to a block of off-heap memory.
 * Each MemoryPointer has a DirectByteBuffer instance that can be used to safely access the memory the pointer points to. Reference counting operations can be
 * done safely from multiple concurrent threads.
 */
public class MemoryPointer {

    private final AtomicInteger retainCount = new AtomicInteger(1);
    private final int size;
    private final ByteBuffer directBuffer;

    private long address;

    MemoryPointer(long address, int size, ByteBuffer directBuffer) {
        this.address = address;
        this.size = size;
        this.directBuffer = directBuffer;
        directBuffer.rewind();
    }

    public long address() {
        return address;
    }

    public ByteBuffer directBuffer() {
        return directBuffer;
    }

    public boolean isFree() {
        return address == 0;
    }

    public void free() {
        MemoryAllocator.deallocate(address, size);
        retainCount.set(0);
        address = 0;
    }

    public int size() {
        return directBuffer.limit();
    }

    public boolean retain() {
        while (true) {
            int retainValue = retainCount.get();

            if (retainValue <= 0) {
                return false;
            }

            if (retainCount.compareAndSet(retainValue, retainValue + 1)) {
                return true;
            }
        }
    }

    public void release() {
        if (retainCount.decrementAndGet() == 0) {
            free();
        }
    }

    @Override
    public String toString() {
        return "MemoryPointer{" +
                "retainCount=" + retainCount +
                ", size=" + size +
                ", address=" + address +
                ", contents=" + toHexString(directBuffer) +
                '}';
    }

    private static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String toHexString(ByteBuffer byteBuffer) {
        char[] hexChars = new char[byteBuffer.capacity() * 2];
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            int v = byteBuffer.get(i) & 0xFF;
            hexChars[i * 2] = hexArray[v >>> 4];
            hexChars[i * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
