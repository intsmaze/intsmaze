package com.intsmaze.lsm.db.table.file;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.io.ImmutableChannelFile;
import com.intsmaze.lsm.db.io.ImmutableFile;
import com.intsmaze.lsm.db.offheap.BloomFilter;
import com.intsmaze.lsm.db.offheap.MemoryAllocator;
import com.intsmaze.lsm.db.offheap.MemoryPointer;
import com.intsmaze.lsm.db.offheap.Offheap;
import com.intsmaze.lsm.db.state.Paths;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Reads an immutable BloomFilter from a file.
 */
public class TableBloomFilter implements Offheap {

    private final BloomFilter bloomFilter;

    private TableBloomFilter(BloomFilter bloomFilter) throws IOException {
        this.bloomFilter = bloomFilter;
    }

    public boolean mightContain(Key key) {
        return bloomFilter.mightContain(key);
    }

    public void close() {
        bloomFilter.memory().release();
    }

    @Override
    public MemoryPointer memory() {
        return bloomFilter.memory();
    }

    public static TableBloomFilter read(long tableId, Paths paths) throws IOException {
        ImmutableFile filterFile = ImmutableChannelFile.open(paths.filterPath(tableId));
        MemoryPointer filterPointer = MemoryAllocator.allocate((int) filterFile.size());
        ByteBuffer filterBuffer = filterPointer.directBuffer();
        filterFile.read(filterBuffer, 0);
        filterFile.close();
        return new TableBloomFilter(new BloomFilter(filterPointer));
    }
}
