package com.intsmaze.lsm.db.table.file;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.index.Index;
import com.intsmaze.lsm.db.index.IndexRecord;
import com.intsmaze.lsm.db.io.ImmutableChannelFile;
import com.intsmaze.lsm.db.io.ImmutableFile;
import com.intsmaze.lsm.db.offheap.MemoryAllocator;
import com.intsmaze.lsm.db.offheap.MemoryPointer;
import com.intsmaze.lsm.db.offheap.SortedByteMap;
import com.intsmaze.lsm.db.state.Paths;
import com.intsmaze.lsm.db.data.Tuple;
import com.intsmaze.lsm.db.index.IndexBlock;
import com.intsmaze.lsm.db.read.LatestTupleIterator;
import com.intsmaze.lsm.db.table.Table;
import com.intsmaze.lsm.db.util.CloseableIterator;
import com.intsmaze.lsm.db.util.Sizes;
//import com.jordanwilliams.heftydb.metrics.Metrics;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides a read-only view on a Table file. A Table file is a doubly linked list of TupleBlocks to allow for
 * efficient iteration. These blocks make up the leaves of the B+tree provided by the Index file.
 */
public class FileTable implements Table {

    private class AscendingBlockIterator implements Iterator<TupleBlock> {

        private final long maxOffset;
        private long fileOffset = 0;

        public AscendingBlockIterator(long startOffset) {
            this.fileOffset = startOffset;
            this.maxOffset = fileSize - TableTrailer.SIZE - Sizes.INT_SIZE;
        }

        @Override
        public boolean hasNext() {
            return fileOffset < maxOffset;
        }

        @Override
        public TupleBlock next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            try {
                int nextBlockSize = tableFile.readInt(fileOffset);
                long nextBlockOffset = fileOffset + Sizes.INT_SIZE;

                fileOffset += Sizes.INT_SIZE;
                fileOffset += nextBlockSize;
                fileOffset += Sizes.INT_SIZE;

                return readTupleBlock(nextBlockOffset, nextBlockSize);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class DescendingBlockIterator implements Iterator<TupleBlock> {

        private long fileOffset;

        public DescendingBlockIterator(long startOffset) {
            this.fileOffset = startOffset;
        }

        @Override
        public boolean hasNext() {
            return fileOffset >= 0;
        }

        @Override
        public TupleBlock next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            try {
                int nextBlockSize = tableFile.readInt(fileOffset);
                long nextBlockOffset = fileOffset - nextBlockSize;

                fileOffset -= Sizes.INT_SIZE;
                fileOffset -= nextBlockSize;
                fileOffset -= Sizes.INT_SIZE;

                return readTupleBlock(nextBlockOffset, nextBlockSize);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class AscendingIterator implements CloseableIterator<Tuple> {

        protected final Iterator<TupleBlock> recordBlockIterator;
        protected Iterator<Tuple> recordIterator;
        protected TupleBlock tupleBlock;

        private AscendingIterator(Iterator<TupleBlock> recordBlockIterator, Iterator<Tuple> startIterator,
                                  TupleBlock startTupleBlock) {
            this.recordBlockIterator = recordBlockIterator;
            this.recordIterator = startIterator;
            this.tupleBlock = startTupleBlock;
        }

        private AscendingIterator(Iterator<TupleBlock> recordBlockIterator) {
            this.recordBlockIterator = recordBlockIterator;
        }

        @Override
        public boolean hasNext() {
            try {
                if (recordIterator == null || !recordIterator.hasNext()) {
                    if (!nextRecordBlock()) {
                        return false;
                    }
                }

                if (recordIterator == null || !recordIterator.hasNext()) {
                    return false;
                }

                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Tuple next() {
            if (recordIterator == null || !recordIterator.hasNext()) {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
            }

            return recordIterator.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void close() throws IOException {
            if (tupleBlock != null && !tupleBlock.memory().isFree()) {
                tupleBlock.memory().free();
            }
        }

        protected boolean nextRecordBlock() throws IOException {
            if (tupleBlock != null) {
                tupleBlock.memory().free();
            }

            if (!recordBlockIterator.hasNext()) {
                return false;
            }

            tupleBlock = recordBlockIterator.next();
            recordIterator = tupleBlock.ascendingIterator();

            return true;
        }
    }

    private class DescendingIterator extends AscendingIterator {

        private DescendingIterator(Iterator<TupleBlock> recordBlockIterator, Iterator<Tuple> startIterator,
                                   TupleBlock startTupleBlock) {
            super(recordBlockIterator, startIterator, startTupleBlock);
        }

        private DescendingIterator(Iterator<TupleBlock> recordBlockIterator) {
            super(recordBlockIterator);
        }

        @Override
        protected boolean nextRecordBlock() throws IOException {
            if (tupleBlock != null) {
                tupleBlock.memory().free();
            }

            if (!recordBlockIterator.hasNext()) {
                return false;
            }

            tupleBlock = recordBlockIterator.next();
            recordIterator = tupleBlock.descendingIterator();

            return true;
        }
    }

    private final long tableId;
    private final long fileSize;
    private final Index index;
    private final TableBloomFilter tableBloomFilter;
    private final TableTrailer trailer;
    private final TupleBlock.Cache recordCache;
    private final ImmutableFile tableFile;

    private FileTable(long tableId, Index index, TableBloomFilter tableBloomFilter, ImmutableFile tableFile,
                      TableTrailer trailer, TupleBlock.Cache recordCache) throws IOException {
        this.tableId = tableId;
        this.recordCache = recordCache;
        this.index = index;
        this.tableBloomFilter = tableBloomFilter;
        this.tableFile = tableFile;
        this.trailer = trailer;
        this.fileSize = tableFile.size();

    }

    @Override
    public boolean mightContain(Key key) {
        return tableBloomFilter.mightContain(key);
    }

    @Override
    public Tuple get(Key key) {
//        System.out.println("文件中读取数据");
        try {
            IndexRecord indexRecord = index.get(key);

            if (indexRecord == null) {
                return null;
            }
            //参数一就是这一批数据在文件里面的小标，参数二就是数据的大小。 索引的问题基本理解了，下面就是块大小了，所有的块大小都一样吗？不一样，索引块里面会记录的。
            System.out.println(indexRecord.blockOffset()+"::"+indexRecord.blockSize());
            TupleBlock tupleBlock = getTupleBlock(indexRecord.blockOffset(), indexRecord.blockSize());
            //检索key所在的节点内数据都检索出来了，这个时候对tupleBlock进行二叉快速检索
            Tuple read = tupleBlock.get(key);
            tupleBlock.memory().release();

            return read;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CloseableIterator<Tuple> ascendingIterator(long snapshotId) {
        return new LatestTupleIterator(snapshotId, new AscendingIterator(new AscendingBlockIterator(0)));
    }

    @Override
    public CloseableIterator<Tuple> descendingIterator(long snapshotId) {
        try {
            long startOffset = tableFile.size() - TableTrailer.SIZE - Sizes.INT_SIZE;
            return new LatestTupleIterator(snapshotId, new DescendingIterator(new DescendingBlockIterator
                    (startOffset)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CloseableIterator<Tuple> ascendingIterator(Key key, long snapshotId) {
        try {
            IndexRecord indexRecord = index.get(key);

            if (indexRecord == null) {
                return new CloseableIterator.Wrapper<Tuple>(Collections.<Tuple>emptyIterator());
            }

            TupleBlock startTupleBlock = readTupleBlock(indexRecord.blockOffset(), indexRecord.blockSize());
            Iterator<Tuple> startRecordIterator = startTupleBlock.ascendingIterator(key);
            long nextBlockOffset = indexRecord.blockOffset() + indexRecord.blockSize() + Sizes.INT_SIZE;
            return new LatestTupleIterator(snapshotId, new AscendingIterator(new AscendingBlockIterator
                    (nextBlockOffset), startRecordIterator, startTupleBlock));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CloseableIterator<Tuple> descendingIterator(Key key, long snapshotId) {
        try {
            IndexRecord indexRecord = index.get(key);

            if (indexRecord == null) {
                return new CloseableIterator.Wrapper<Tuple>(Collections.<Tuple>emptyIterator());
            }

            TupleBlock startTupleBlock = readTupleBlock(indexRecord.blockOffset(), indexRecord.blockSize());
            Iterator<Tuple> startRecordIterator = startTupleBlock.descendingIterator(key);
            long nextBlockOffset = indexRecord.blockOffset() - Sizes.LONG_SIZE;
            return new LatestTupleIterator(snapshotId, new DescendingIterator(new DescendingBlockIterator
                    (nextBlockOffset), startRecordIterator, startTupleBlock));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long id() {
        return tableId;
    }

    @Override
    public long tupleCount() {
        return trailer.recordCount();
    }

    @Override
    public long size() {
        return fileSize;
    }

    @Override
    public int level() {
        return trailer.level();
    }

    @Override
    public long maxSnapshotId() {
        return trailer.maxSnapshotId();
    }

    @Override
    public void close() {
        try {
            index.close();
            tableFile.close();
            tableBloomFilter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isPersistent() {
        return true;
    }

    @Override
    public Iterator<Tuple> iterator() {
        return new AscendingIterator(new AscendingBlockIterator(0));
    }

    @Override
    public int compareTo(Table o) {
        return Long.compare(tableId, o.id());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileTable fileTable = (FileTable) o;

        if (tableId != fileTable.tableId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (tableId ^ (tableId >>> 32));
    }

    @Override
    public String toString() {
        return "FileTable{" +
                "tableId=" + tableId +
                ", level=" + trailer.level() +
                '}';
    }

    /**
    * @Description: index的文件里面，其实是由很多个节点组成，每个节点里面包含部分key的数据，确定某个key在哪个节点后，就会把这个节点里面的所有数据读取出来 tupleBlock里面还包含data数据，这一点从哪里读了？
    * @Param: 
    * @return: 
    * @Author: intsmaze
    * @Date: 2019/2/1
    */ 
    private TupleBlock getTupleBlock(long offset, int size) throws IOException {
        TupleBlock tupleBlock = recordCache.get(tableId, offset);
//        tableCacheHitRate.sample(tupleBlock != null);

        if (tupleBlock == null) {
            tupleBlock = readTupleBlock(offset, size);//这尼玛，是把这个块全部读出来呀
            recordCache.put(tableId, offset, tupleBlock);
        }

        return tupleBlock;
    }
    
    /**
    * @Description: 根据索引节点指定读取这个节点内一批数据，从table文件中读取
    * @Param: 
    * @return: 
    * @Author: intsmaze
    * @Date: 2019/2/1
    */ 
    private TupleBlock readTupleBlock(long offset, int size) throws IOException {
        MemoryPointer recordBlockPointer = MemoryAllocator.allocate(size);

        try {
            ByteBuffer recordBlockBuffer = recordBlockPointer.directBuffer();
            tableFile.read(recordBlockBuffer, offset);
            recordBlockBuffer.rewind();
//           String demo= intsmazeUtils.getString(recordBlockBuffer);
            return new TupleBlock(new SortedByteMap(recordBlockPointer));
        } catch (IOException e) {
            recordBlockPointer.release();
            throw e;
        }
    }

    //    public static FileTable open(long tableId, Paths paths, TupleBlock.Cache recordCache,
//                                 IndexBlock.Cache indexCache, Metrics metrics) throws IOException {
    public static FileTable open(long tableId, Paths paths, TupleBlock.Cache recordCache,
                                 IndexBlock.Cache indexCache) throws IOException {
        Index index = Index.open(tableId, paths, indexCache);
        TableBloomFilter tableBloomFilter = TableBloomFilter.read(tableId, paths);
        ImmutableFile tableFile = ImmutableChannelFile.open(paths.tablePath(tableId));
        TableTrailer trailer = TableTrailer.read(tableFile);
        return new FileTable(tableId, index, tableBloomFilter, tableFile, trailer, recordCache);
    }
}