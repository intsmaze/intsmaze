package com.intsmaze.lsm.db.table.file;

import com.googlecode.concurrentlinkedhashmap.Weigher;
import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.offheap.MemoryPointer;
import com.intsmaze.lsm.db.offheap.Offheap;
import com.intsmaze.lsm.db.offheap.SortedByteMap;
import com.intsmaze.lsm.db.cache.TableBlockConcurrentLinkedHashMapCache;
import com.intsmaze.lsm.db.data.Tuple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A SortedByteMap wrapper that represents a sorted collection of Tuples. TupleBlocks are leaf pages in the B+tree
 * that makes up a Table.
 */
public class TupleBlock implements Iterable<Tuple>, Offheap {

    public static class Cache {

        private final TableBlockConcurrentLinkedHashMapCache<TupleBlock> cache;

        public Cache(long maxSize) {
            cache = new TableBlockConcurrentLinkedHashMapCache<TupleBlock>(maxSize, new Weigher<TupleBlock>() {
                @Override
                public int weightOf(TupleBlock tuple) {
                    return tuple.memory().size();
                }
            });

        }

        public TupleBlock get(long tableId, long offset) {
            return cache.get(tableId, offset);
        }

        public void put(long tableId, long offset, TupleBlock tupleBlock) {
            cache.put(tableId, offset, tupleBlock);
        }

        public void invalidate(long tableId) {
            cache.invalidate(tableId);
        }

        public void clear() {
            cache.clear();
        }
    }

    /**
    * @Description: 里面存储了一批的k-v数据，这一批数据存储在一个文件里面
    * @Param: 
    * @return: 
    * @Author: intsmaze
    * @Date: 2019/2/1
    */ 
    public static class TupleBlockBuilder {

        private final SortedByteMap.SortedByteMapBuilder sortedByteMapBuilder = new SortedByteMap.SortedByteMapBuilder();

        private int size;

        /**
         * @author:YangLiu
         * @date:2018年10月18日 下午8:00:23
         * @describe:存储k-v数据
         */
        public void addRecord(Tuple tuple) {
            sortedByteMapBuilder.add(new Key(tuple.key().data(), tuple.key().snapshotId()), tuple.value());
//        	sortedByteMapBuilder.add(new Key(tuple.key().data()), tuple.value());
            size += tuple.size();
        }

        public int size() {
            return size;
        }
        //
        /**
         * @author:YangLiu
         * @date:2018年10月18日 下午8:06:20
         * @describe:返回创建TupleBlock对象，里面sortedByteMap是排序好的k-v数据
         */
        public TupleBlock build() {
            return new TupleBlock(sortedByteMapBuilder.buildSortedByteMap());
        }
    }

    private class TupleIterator implements Iterator<Tuple> {

        private final Iterator<SortedByteMap.Entry> entryIterator;

        private TupleIterator(Iterator<SortedByteMap.Entry> entryIterator) {
            this.entryIterator = entryIterator;
        }

        @Override
        public boolean hasNext() {
            return entryIterator.hasNext();
        }

        @Override
        public Tuple next() {
            SortedByteMap.Entry nextEntry = entryIterator.next();
            return new Tuple(nextEntry.key(), nextEntry.value());
        }

        @Override
        public void remove() {
            entryIterator.remove();
        }
    }

    private final SortedByteMap sortedByteMap;

    public TupleBlock(SortedByteMap sortedByteMap) {
        this.sortedByteMap = sortedByteMap;
    }

    public Tuple get(Key key) {
        int closestIndex = sortedByteMap.floorIndex(key);

        if (closestIndex < 0 || closestIndex >= sortedByteMap.entryCount()) {
            return null;
        }

        Tuple closestTuple = deserialize(closestIndex);
        return closestTuple.key().data().equals(key.data()) ? closestTuple : null;
    }

    public Tuple first() {
        return deserialize(0);
    }

    public Iterator<Tuple> ascendingIterator() {
        return new TupleIterator(sortedByteMap.ascendingIterator());
    }

    public Iterator<Tuple> ascendingIterator(Key key) {
        return new TupleIterator(sortedByteMap.ascendingIterator(key));
    }

    public Iterator<Tuple> descendingIterator() {
        return new TupleIterator(sortedByteMap.descendingIterator());
    }

    public Iterator<Tuple> descendingIterator(Key key) {
        return new TupleIterator(sortedByteMap.descendingIterator(key));
    }

    @Override
    public Iterator<Tuple> iterator() {
        return new TupleIterator(sortedByteMap.ascendingIterator());
    }

    @Override
    public MemoryPointer memory() {
        return sortedByteMap.memory();
    }

    @Override
    public String toString() {
//        List<Tuple> tuples = new ArrayList<Tuple>();
//        for (Tuple tuple : this) {
//            tuples.add(tuple);
//        }
        List<Key> tuples = new ArrayList<Key>();
        for (Tuple tuple : this) {
            tuples.add(tuple.key());
        }

        return "TupleBlock{tuples=" + tuples + "}";
    }

    private Tuple deserialize(int index) {
        SortedByteMap.Entry entry = sortedByteMap.get(index);
        return new Tuple(entry.key(), entry.value());
    }
}
