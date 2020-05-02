package com.intsmaze.lsm.db.offheap;

import com.intsmaze.lsm.db.util.Sizes;

import java.nio.ByteBuffer;

/**
 * An immutable bit set that is backed by off-heap memory.
 */
public class BitSet implements Offheap {

    public static class Builder {

        private final MemoryPointer pointer;
        private final ByteBuffer directBuffer;
        private final int usableBytes;

        public Builder(long bitCount, int paddingBytes) {
            this.usableBytes = memoryOffset(bitCount) + Sizes.LONG_SIZE;
            this.pointer = MemoryAllocator.allocateAndZero(usableBytes + paddingBytes);
            this.directBuffer = pointer.directBuffer();
        }

        public void set(long bitIndex, boolean value) {
            int offset = memoryOffset(bitIndex);

            if (value) {
                //Set
                directBuffer.putLong(offset, directBuffer.getLong(offset) | (1L << bitIndex));
            } else {
                //Clear
                directBuffer.putLong(offset, directBuffer.getLong(offset) & ~(1L << bitIndex));
            }
        }

        public BitSet build() {
            return new BitSet(pointer, usableBytes);
        }

        public int usableBytes() {
            return usableBytes;
        }

        public long bitCount() {
            return usableBytes * 8;
        }
    }

    private static final int ADDRESS_BITS_PER_WORD = 6;

    private final MemoryPointer pointer;
    private final ByteBuffer directBuffer;
    private final int usableBytes;

    public BitSet(MemoryPointer pointer, int usableBytes) {
        this.pointer = pointer;
        this.directBuffer = pointer.directBuffer();
        this.usableBytes = usableBytes;
    }

    public boolean get(long index) {
        int offset = memoryOffset(index);
        long currentValue = directBuffer.getLong(offset);
        return (currentValue & (1L << index)) != 0;
    }

    public int usableBytes() {
        return usableBytes;
    }

    public long bitCount() {
        return usableBytes * 8;
    }

    @Override
    public MemoryPointer memory() {
        return pointer;
    }

    private static int memoryOffset(long bitIndex) {
        return (int) (bitIndex >> ADDRESS_BITS_PER_WORD) * Sizes.LONG_SIZE;
    }
}
