package com.intsmaze.lsm.db.index;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.util.Sizes;

/**
 * Represents a single entry in an IndexBlock.
 */
public class IndexRecord {

    private final Key startKey;
    private final long blockOffset;
    private final int blockSize;
    private final boolean isLeaf;//是否是叶子，索引采用B+树结构，所以叶子的概率代表叶子节点呗。

    public IndexRecord(Key startKey, long blockOffset, int blockSize, boolean isLeaf) {
        this.startKey = startKey;
        this.blockOffset = blockOffset;
        this.blockSize = blockSize;
        this.isLeaf = isLeaf;
    }

    public IndexRecord(Key startKey, long blockOffset, int blockSize) {
        this(startKey, blockOffset, blockSize, true);
    }

    public Key startKey() {
        return startKey;
    }

    public long blockOffset() {
        return blockOffset;
    }

    public int blockSize() {
        return blockSize;
    }

    public int size() {
        return Sizes.INT_SIZE + //Key blockSize
                startKey.size() + //Key
                Sizes.LONG_SIZE + //Offset
                Sizes.INT_SIZE +
                1; //Leaf flag
    }

    public int contentsSize() {
        return Sizes.LONG_SIZE + //Offset
                Sizes.INT_SIZE +
                1; //Leaf flag
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexRecord that = (IndexRecord) o;

        if (blockOffset != that.blockOffset) return false;
        if (blockSize != that.blockSize) return false;
        if (isLeaf != that.isLeaf) return false;
        if (startKey != null ? !startKey.equals(that.startKey) : that.startKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startKey != null ? startKey.hashCode() : 0;
        result = 31 * result + (int) (blockOffset ^ (blockOffset >>> 32));
        result = 31 * result + blockSize;
        result = 31 * result + (isLeaf ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IndexRecord{" +
                "startKey=" + startKey +
                ", blockOffset=" + blockOffset +
                ", blockSize=" + blockSize +
                ", isLeaf=" + isLeaf +
                '}';
    }
}
