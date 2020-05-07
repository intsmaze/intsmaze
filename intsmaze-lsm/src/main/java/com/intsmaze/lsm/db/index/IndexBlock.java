package com.intsmaze.lsm.db.index;

import com.googlecode.concurrentlinkedhashmap.Weigher;
import com.intsmaze.lsm.db.cache.TableBlockConcurrentLinkedHashMapCache;
import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.data.Value;
import com.intsmaze.lsm.db.offheap.MemoryPointer;
import com.intsmaze.lsm.db.offheap.Offheap;
import com.intsmaze.lsm.db.offheap.SortedByteMap;
import com.intsmaze.lsm.db.util.Sizes;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A SortedByteMap wrapper that represents a block of IndexRecords.
 * 索引记录块的SortedByteMap包装器
 * An IndexBlock is a page in the B+tree Index for a database file.
 * IndexBlock是数据库文件的B+树索引中的一个页面
 * SortedByteMap包装表示索引记录的块，一个索引块就是一页
 */
public class IndexBlock implements Iterable<IndexRecord>, Offheap {

    public static class Cache {

        private final TableBlockConcurrentLinkedHashMapCache<IndexBlock> cache;

        public Cache(long maxSize) {
            cache = new TableBlockConcurrentLinkedHashMapCache<IndexBlock>(maxSize, new Weigher<IndexBlock>() {
                @Override
                public int weightOf(IndexBlock indexRecord) {
                    return indexRecord.memory().size();
                }
            });

        }

        public IndexBlock get(long tableId, long offset) {
            return cache.get(tableId, offset);
        }

        public void put(long tableId, long offset, IndexBlock tupleBlock) {
            cache.put(tableId, offset, tupleBlock);
        }

        public void invalidate(long tableId) {
            cache.invalidate(tableId);
        }

        public void clear() {
            cache.clear();
        }
    }

    //*************************************************************


    public static class Builder {

        private final SortedByteMap.SortedByteMapBuilder byteMapBuilder = new SortedByteMap.SortedByteMapBuilder();

        private int size;//该集合中存储的key的大小，作为一个界限

        public void addRecord(IndexRecord indexRecord) {
            byteMapBuilder.add(indexRecord.startKey(), new Value(indexRecordValue(indexRecord)));
            size += indexRecord.size();
        }

        public int size() {
            return size;
        }

        public IndexBlock build() {
            return new IndexBlock(byteMapBuilder.buildSortedByteMap());
        }

        private ByteBuffer indexRecordValue(IndexRecord indexRecord) {
            ByteBuffer contentsBuffer = ByteBuffer.allocate(indexRecord.contentsSize());
            contentsBuffer.putLong(indexRecord.blockOffset());
            contentsBuffer.putInt(indexRecord.blockSize());
            contentsBuffer.put(indexRecord.isLeaf() ? (byte) 1 : (byte) 0);
            contentsBuffer.rewind();
            return contentsBuffer;
        }
    }


    //*************************************************************

    private class RecordIterator implements Iterator<IndexRecord> {

        private final Iterator<SortedByteMap.Entry> entryIterator;

        private RecordIterator(Iterator<SortedByteMap.Entry> entryIterator) {
            this.entryIterator = entryIterator;
        }

        @Override
        public boolean hasNext() {
            return entryIterator.hasNext();
        }

        @Override
        public IndexRecord next() {
            SortedByteMap.Entry nextEntry = entryIterator.next();
            return deserialize(nextEntry);
        }

        @Override
        public void remove() {
            entryIterator.remove();
        }
    }

    //*************************************************************




    private final SortedByteMap sortedByteMap;

    public IndexBlock(SortedByteMap sortedByteMap) {
        this.sortedByteMap = sortedByteMap;
    }

    public IndexRecord startRecord() {
        return deserialize(0);
    }

    /**
    * @author:YangLiu
    * @date:2018年10月18日 下午10:51:35 
    * @describe:二路搜索数据所在的位置
     */
    public IndexRecord get(Key key) {
        int closestIndex = sortedByteMap.floorIndex(key);

        if (closestIndex < 0) {
            return null;
        }

        if (closestIndex >= sortedByteMap.entryCount()) {
            closestIndex = sortedByteMap.entryCount() - 1;
        }

        return deserialize(closestIndex);
    }

    @Override
    public MemoryPointer memory() {
        return sortedByteMap.memory();
    }

    public Iterator<IndexRecord> ascendingIterator() {
        return new RecordIterator(sortedByteMap.ascendingIterator());
    }

    public Iterator<IndexRecord> ascendingIterator(Key key) {
        return new RecordIterator(sortedByteMap.ascendingIterator(key));
    }

    public Iterator<IndexRecord> descendingIterator() {
        return new RecordIterator(sortedByteMap.descendingIterator());
    }

    public Iterator<IndexRecord> descendingIterator(Key key) {
        return new RecordIterator(sortedByteMap.descendingIterator(key));
    }

    @Override
    public Iterator<IndexRecord> iterator() {
        return new RecordIterator(sortedByteMap.ascendingIterator());
    }

    @Override
    public String toString() {
        List<IndexRecord> records = new ArrayList<IndexRecord>();

        for (IndexRecord indexRecord : this) {
            records.add(indexRecord);
        }

        return "IndexBlock{records=" + records + "}";
    }

    private IndexRecord deserialize(int index) {
        SortedByteMap.Entry entry = sortedByteMap.get(index);
        return deserialize(entry);
    }

    private IndexRecord deserialize(SortedByteMap.Entry entry) {
        ByteBuffer entryValueBuffer = entry.value().data();
        long blockOffset = entryValueBuffer.getLong(0);
        int blockSize = entryValueBuffer.getInt(Sizes.LONG_SIZE);
        boolean isLeaf = entryValueBuffer.get(Sizes.LONG_SIZE + Sizes.INT_SIZE) == (byte) 1;
        return new IndexRecord(entry.key(), blockOffset, blockSize, isLeaf);
    }
}
