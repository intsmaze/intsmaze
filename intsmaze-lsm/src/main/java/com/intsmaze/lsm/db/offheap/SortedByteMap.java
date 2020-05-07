package com.intsmaze.lsm.db.offheap;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.data.Value;
import com.intsmaze.lsm.db.util.Sizes;

import sun.misc.Unsafe;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A sorted block of key-value entries optimized for efficient binary search and backed by off-heap memory.
 * 一种经过排序的键值条目块，经过优化以实现高效的二进制搜索，并得到堆外内存的支持。
 * 在SortedByteMap上进行二进制搜索不需要对象分配，因此非常快。
 * A binary search over a SortedByteMap requires no object allocations, and is thus quite fast.
 */
public class SortedByteMap implements Offheap, Iterable<SortedByteMap.Entry> {

    private static final Unsafe unsafe = JVMUnsafe.unsafe;
    //4096  在许多操作系统中，页得大小通常为4k
    private static final int PAGE_SIZE = unsafe.pageSize();

    public static void main(String[] args) {
        System.out.println( unsafe.pageSize());
    }

    public static class SortedByteMapBuilder {

        private final List<Entry> entries = new LinkedList<Entry>();

        
        /**
        * @Description: 所有的k-v数据会存入这个list集合里面
        * @Param: 
        * @return: 
        * @Author: intsmaze
        * @Date: 2019/2/1
        */ 
        public void add(Key key, Value value) {
            entries.add(new Entry(key, value));
        }

        
        //之前只是向里面添加元素，没有拍下，这是将元素排序返回
        /**
        * @author:YangLiu
        * @date:2018年10月18日 下午8:03:47 
        * @describe:Builder里面存储的k-v数据用list保存，
         * 当缓存一批k-v数据后，将这些数据进行排序？然后返回？？serializeEntries()
        * 这里将list里面的数据排序存储在SortedByteMap中，list的里面的顺序没有改变
         */
        public SortedByteMap buildSortedByteMap() {
            return new SortedByteMap(serializeEntries());
        }

        private MemoryPointer serializeEntries() {
            //Allocate pointer
            int memorySize = 0;
            //每一个元素代表每一个k-v记录的某个大小值
            int[] entryOffsets = new int[entries.size()];

            memorySize += Sizes.INT_SIZE; //MemoryPointer count
            memorySize += Sizes.INT_SIZE * entries.size(); //Pointers

            //Compute pointer size
            int counter = 0;

            for (Entry entry : entries) {
                entryOffsets[counter] = memorySize;
                memorySize += Sizes.INT_SIZE;
                memorySize += entry.key().size();
                memorySize += Sizes.LONG_SIZE;
                memorySize += Sizes.INT_SIZE;
                memorySize += entry.value().size();
                counter++;
            }

            //分配大小  创建一个指定大小的ByteBuffer
            MemoryPointer pointer = MemoryAllocator.allocate(memorySize, PAGE_SIZE);
            ByteBuffer memoryBuffer = pointer.directBuffer();

            //Pack pointers  写入一个int值到ByteBuffer中
            memoryBuffer.putInt(entries.size());

            for (int i = 0; i < entryOffsets.length; i++) {
                memoryBuffer.putInt(entryOffsets[i]);
            }

            //Pack entries
            for (Entry entry : entries) {
                Key key = entry.key();
                Value value = entry.value();

                key.data().rewind();
                value.data().rewind();

                //Key
                memoryBuffer.putInt(key.size());

                ByteBuffer keyData = key.data();
                for (int i = 0; i < key.size(); i++) {
                    memoryBuffer.put(keyData.get(i));
                }

                memoryBuffer.putLong(key.snapshotId());

                //Value
                memoryBuffer.putInt(value.size());

                ByteBuffer valueData = value.data();
                for (int i = 0; i < value.size(); i++) {
                    memoryBuffer.put(valueData.get(i));
                }

                key.data().rewind();
                value.data().rewind();
            }

            memoryBuffer.rewind();

            return pointer;
        }
    }

    private class AscendingIterator implements Iterator<Entry> {

        private int currentEntryIndex;

        public AscendingIterator(int startIndex) {
            this.currentEntryIndex = startIndex;
        }

        @Override
        public boolean hasNext() {
            return currentEntryIndex < entryCount;
        }

        @Override
        public Entry next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Entry entry = get(currentEntryIndex);
            currentEntryIndex++;
            return entry;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class DescendingIterator implements Iterator<Entry> {

        private int currentEntryIndex;

        public DescendingIterator(int startIndex) {
            this.currentEntryIndex = startIndex;
        }

        @Override
        public boolean hasNext() {
            return currentEntryIndex > -1;
        }

        @Override
        public Entry next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Entry entry = get(currentEntryIndex);
            currentEntryIndex--;
            return entry;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private final MemoryPointer pointer;
    private final ByteBuffer directBuffer;
    private final int entryCount;

    public SortedByteMap(MemoryPointer pointer) {
        this.pointer = pointer;
        this.directBuffer = pointer.directBuffer();
        this.entryCount = unsafe.getInt(pointer.address());
    }

    public Entry get(int index) {
        return getEntry(index);
    }

    /**
    * @author:YangLiu
    * @date:2018年10月18日 下午10:50:43 
    * @describe:二路搜索数据所在的位置
     */
    public int floorIndex(Key key) {
        if (pointer.isFree()) {
            throw new IllegalStateException("Memory was already freed");
        }

        int low = 0;
        int high = entryCount - 1;

        //Binary search 二路搜索
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int compare = compareKeys(key, mid);

            if (compare < 0) {
                low = mid + 1;
            } else if (compare > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return low - 1;
    }

    public int ceilingIndex(Key key) {
        if (pointer.isFree()) {
            throw new IllegalStateException("Memory was already freed");
        }

        int low = 0;
        int high = entryCount - 1;

        //Binary search
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int compare = compareKeys(key, mid);

            if (compare < 0) {
                low = mid + 1;
            } else if (compare > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return low;
    }

    public int entryCount() {
        return entryCount;
    }

    public Iterator<Entry> ascendingIterator() {
        return new AscendingIterator(0);
    }

    public Iterator<Entry> ascendingIterator(Key key) {
        Key versionedKey = new Key(key.data(), 0);
        int startIndex = ceilingIndex(versionedKey);
        return new AscendingIterator(startIndex);
    }

    public Iterator<Entry> descendingIterator() {
        return new DescendingIterator(entryCount() - 1);
    }

    public Iterator<Entry> descendingIterator(Key key) {
        Key versionedKey = new Key(key.data(), Long.MAX_VALUE);
        int startIndex = floorIndex(versionedKey);
        return new DescendingIterator(startIndex);
    }

    @Override
    public Iterator<Entry> iterator() {
        return new AscendingIterator(0);
    }

    @Override
    public MemoryPointer memory() {
        return pointer;
    }

    @Override
    public String toString() {
        List<Entry> entries = new ArrayList<Entry>();
        for (Entry entry : this) {
            entries.add(entry);
        }

        return "SortedByteMap{entries=" + entries + "}";
    }

    private Entry getEntry(int index) {
        if (index < 0 || index >= entryCount) {
            throw new IndexOutOfBoundsException("Requested Index: " + index + " Max: " + (entryCount - 1));
        }

        if (pointer.isFree()) {
            throw new IllegalStateException("Memory was already freed");
        }

        int entryOffset = entryOffset(index);
        long startAddress = pointer.address();

        //Key
        int keySize = unsafe.getInt(startAddress + entryOffset);

        ByteBuffer keyBuffer = ByteBuffer.allocate(keySize);
        int keyOffset = entryOffset + Sizes.INT_SIZE;
        byte[] keyArray = keyBuffer.array();
        long keyAddress = startAddress + keyOffset;

        for (int i = 0; i < keySize; i++) {
            keyArray[i] = unsafe.getByte(keyAddress++);
        }

        long snapshotId = unsafe.getLong(startAddress + keyOffset + keySize);

        keyBuffer.rewind();

        //Value
        int valueOffset = keyOffset + keySize + Sizes.LONG_SIZE;
        int valueSize = directBuffer.getInt(valueOffset);
        ByteBuffer valueBuffer = ByteBuffer.allocate(valueSize);
        valueOffset += Sizes.INT_SIZE;
        byte[] valueArray = valueBuffer.array();
        long valueAddress = startAddress + valueOffset;

        for (int i = 0; i < valueSize; i++) {
            valueArray[i] = unsafe.getByte(valueAddress++);
        }

        valueBuffer.rewind();

        return new Entry(new Key(keyBuffer, snapshotId), new Value(valueBuffer));
    }

    private int compareKeys(Key compareKey, int bufferKeyIndex) {
        int entryOffset = entryOffset(bufferKeyIndex);
        long startAddress = pointer.address();

        int keySize = unsafe.getInt(startAddress + entryOffset);
        entryOffset += Sizes.INT_SIZE;

        int bufferKeyRemaining = keySize;
        int compareKeyRemaining = compareKey.data().remaining();
        int compareCount = Math.min(bufferKeyRemaining, compareKeyRemaining);
        byte[] compareKeyArray = compareKey.data().array();

        //Compare key bytes
        for (int i = 0; i < compareCount; i++) {
            byte bufferKeyVal = unsafe.getByte(startAddress + entryOffset + i);
            byte compareKeyVal = compareKeyArray[i];
            bufferKeyRemaining--;
            compareKeyRemaining--;

            if (bufferKeyVal == compareKeyVal) {
                continue;
            }

            if (bufferKeyVal < compareKeyVal) {
                return -1;
            }

            return 1;
        }

        int remainingDifference = bufferKeyRemaining - compareKeyRemaining;

        //If key bytes are equal, compare snapshot ids
        if (remainingDifference == 0) {
            long bufferSnapshotId = unsafe.getLong(startAddress + entryOffset + compareCount);
            return Long.compare(bufferSnapshotId, compareKey.snapshotId());
        }

        return remainingDifference;
    }

    private int entryOffset(int index) {
        return unsafe.getInt(pointer.address() + (Sizes.INT_SIZE + (index * Sizes.INT_SIZE)));
    }


    public static class Entry {

        private final Key key;
        private final Value value;

        public Entry(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Key key() {
            return key;
        }

        public Value value() {
            return value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
