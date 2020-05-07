package com.intsmaze.lsm.db.offheap;

import com.intsmaze.lsm.db.offheap.allocator.Allocator;
import com.intsmaze.lsm.db.offheap.allocator.UnsafeAllocator;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * A class that allocates off-heap memory and returns reference counted pointers to these blocks of memory.
 */
public class MemoryAllocator {

    private static final Unsafe unsafe = JVMUnsafe.unsafe;
    private static final Allocator allocator = new UnsafeAllocator();
    private static final Class<?> directByteBufferClass;
    private static final long addressOffset;
    private static final long capacityOffset;
    private static final long limitOffset;


    static {
        try {
            Class<?> bufferClass = Class.forName("java.nio.Buffer");
            Field address = bufferClass.getDeclaredField("address");
            Field capacity = bufferClass.getDeclaredField("capacity");
            Field limit = bufferClass.getDeclaredField("limit");

            addressOffset = unsafe.objectFieldOffset(address);
            capacityOffset = unsafe.objectFieldOffset(capacity);
            limitOffset = unsafe.objectFieldOffset(limit);
            directByteBufferClass = Class.forName("java.nio.DirectByteBuffer");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static MemoryPointer wrap(long address, int size) {
        return new MemoryPointer(address, size, rawDirectBuffer(address, size));
    }

    public static MemoryPointer allocate(int size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }

        long address = allocator.allocate(size);
        return new MemoryPointer(address, size, rawDirectBuffer(address, size));
    }

    public static MemoryPointer allocateAndZero(int size) {
        MemoryPointer pointer = allocate(size);
        zeroMemory(pointer);
        return pointer;
    }

    public static MemoryPointer allocate(int size, int align) {
        return allocate(pageAlignedSize(size, align));
    }

    public static MemoryPointer allocateAndZero(int size, int align) {
        MemoryPointer pointer = allocate(size, align);
        zeroMemory(pointer);
        return pointer;
    }

    public static void deallocate(long address, int size) {
        allocator.deallocate(address);
    }

    private static void zeroMemory(MemoryPointer pointer) {
        unsafe.setMemory(pointer.address(), pointer.size(), (byte) 0);
    }

    private static int pageAlignedSize(int memorySize, int pageSize) {
        int pageCount = memorySize / pageSize;

        if (memorySize % pageSize != 0) {
            pageCount++;
        }

        return pageCount * pageSize;
    }

    private static ByteBuffer rawDirectBuffer(long address, int size) {
        try {
            ByteBuffer newBuffer = (ByteBuffer) unsafe.allocateInstance(directByteBufferClass);
            unsafe.putLong(newBuffer, addressOffset, address);
            unsafe.putInt(newBuffer, capacityOffset, size);
            unsafe.putInt(newBuffer, limitOffset, size);
            newBuffer.order(ByteOrder.nativeOrder());
            return newBuffer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
