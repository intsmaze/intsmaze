package com.intsmaze.lsm.db.offheap;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.util.MurmurHash3;
import com.intsmaze.lsm.db.util.Sizes;

import java.nio.ByteBuffer;

/**
 * An immutable bloom filter that is backed by an off-heap BitSet. Uses similar hashing techniques as the Guava bloom
 * filter, but is more efficient and creates less garbage.
 */
public class BloomFilter implements Offheap {

    public static class Builder {

        private final BitSet.Builder bitSetBuilder;
        private final int hashFunctionCount;

        public Builder(long approxElementCount, double falsePositiveProbability) {
            long bitCount = bitCount(approxElementCount, falsePositiveProbability);
            this.bitSetBuilder = new BitSet.Builder(bitCount, Sizes.INT_SIZE);
            this.hashFunctionCount = hashFunctionCount(approxElementCount, bitCount);
        }

        public void put(Key key) {
            long hash64 = MurmurHash3.MurmurHash3_x64_64(key.data().array());
            int hash1 = (int) hash64;
            int hash2 = (int) (hash64 >>> 32);

            for (int i = 1; i <= hashFunctionCount; i++) {
                int nextHash = hash1 + i * hash2;

                if (nextHash < 0) {
                    nextHash = ~nextHash;
                }

                bitSetBuilder.set(nextHash % bitSetBuilder.bitCount(), true);
            }
        }

        public BloomFilter build() {
            MemoryPointer bloomFilterPointer = serializeBloomFilter(bitSetBuilder.build(), hashFunctionCount);
            return new BloomFilter(bloomFilterPointer);
        }

        private static int hashFunctionCount(long approxElementCount, long bitCount) {
            return Math.max(1, (int) Math.round(bitCount / approxElementCount * Math.log(2)));
        }

        private static long bitCount(long approxElementCount, double falsePositiveProbability) {
            return (long) (-approxElementCount * Math.log(falsePositiveProbability) / (Math.log(2) * Math.log(2)));
        }

        private static MemoryPointer serializeBloomFilter(BitSet bitSet, int hashFunctionCount) {
            MemoryPointer bloomFilterPointer = bitSet.memory();
            bloomFilterPointer.directBuffer().putInt(bitSet.usableBytes(), hashFunctionCount);
            return bloomFilterPointer;
        }
    }

    private final MemoryPointer pointer;
    private final BitSet bitSet;
    private final int hashFunctionCount;

    public BloomFilter(MemoryPointer pointer) {
        this.pointer = pointer;
        this.bitSet = new BitSet(pointer, pointer.size() - Sizes.INT_SIZE);
        ByteBuffer directBuffer = pointer.directBuffer();
        this.hashFunctionCount = directBuffer.getInt(directBuffer.capacity() - Sizes.INT_SIZE);
    }

    public boolean mightContain(Key key) {
        long hash64 = MurmurHash3.MurmurHash3_x64_64(key.data().array());
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);

        for (int i = 1; i <= hashFunctionCount; i++) {
            int nextHash = hash1 + i * hash2;
            if (nextHash < 0) {
                nextHash = ~nextHash;
            }
            if (!bitSet.get(nextHash % bitSet.bitCount())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public MemoryPointer memory() {
        return pointer;
    }
}
