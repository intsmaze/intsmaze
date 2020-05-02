package com.intsmaze.lsm.db.index;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.io.ImmutableChannelFile;
import com.intsmaze.lsm.db.io.ImmutableFile;
import com.intsmaze.lsm.db.offheap.MemoryAllocator;
import com.intsmaze.lsm.db.offheap.MemoryPointer;
import com.intsmaze.lsm.db.offheap.SortedByteMap;
import com.intsmaze.lsm.db.state.Paths;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Represents a read-only view of a B+tree database index file.
 */
public class Index {

    private static final int ROOT_INDEX_BLOCK_OFFSET = 8;
    private static final int ROOT_INDEX_BLOCK_SIZE_OFFSET = 12;

    private final long tableId;
    private final ImmutableFile indexFile;
    private final IndexBlock.Cache cache;//充当缓存
    private final IndexBlock rootIndexBlock;

    private Index(long tableId, ImmutableFile indexFile, IndexBlock.Cache cache) throws IOException {
        this.tableId = tableId;
        this.indexFile = indexFile;
        this.cache = cache;//不晓得你有毛用

        long rootBlockOffset = indexFile.readLong(indexFile.size() - ROOT_INDEX_BLOCK_OFFSET);
        int rootBlockSize = indexFile.readInt(indexFile.size() - ROOT_INDEX_BLOCK_SIZE_OFFSET);
        this.rootIndexBlock = readIndexBlock(rootBlockOffset, rootBlockSize);

    }

    /**
     * @author:YangLiu
     * @date:2018年10月18日 下午10:41:28
     * @describe:B+树的检索
     */
    public IndexRecord get(Key key) throws IOException {
        IndexRecord currentIndexRecord = rootIndexBlock.get(key);
        int searchLevels = 1;

        while (currentIndexRecord != null && !currentIndexRecord.isLeaf()) {//如果不是叶子节点则往下找
            IndexBlock currentIndexBlock = getIndexBlock(currentIndexRecord.blockOffset(),
                    currentIndexRecord.blockSize());//获取下一层节点
            currentIndexRecord = currentIndexBlock.get(key);
            currentIndexBlock.memory().release();
            searchLevels++;//该索引所在的层数
        }

        return currentIndexRecord;
    }

    public void close() throws IOException {
        rootIndexBlock.memory().release();
        indexFile.close();
        cache.clear();
    }

    private IndexBlock getIndexBlock(long blockOffset, int blockSize) throws IOException {
        IndexBlock indexBlock = cache.get(tableId, blockOffset);

        if (indexBlock == null) {
            indexBlock = readIndexBlock(blockOffset, blockSize);
            cache.put(tableId, blockOffset, indexBlock);
        }

        return indexBlock;
    }

    private IndexBlock readIndexBlock(long blockOffset, int blockSize) throws IOException {
        MemoryPointer indexPointer = MemoryAllocator.allocate(blockSize);

        try {
            ByteBuffer indexBuffer = indexPointer.directBuffer();
            indexFile.read(indexBuffer, blockOffset);//会不会是把数据读到indexBuffer里面
            indexBuffer.rewind();
            return new IndexBlock(new SortedByteMap(indexPointer));
        } catch (IOException e) {
            indexPointer.release();
            throw e;
        }
    }

    public static Index open(long tableId, Paths paths, IndexBlock.Cache cache) throws IOException {
        ImmutableFile indexFile = ImmutableChannelFile.open(paths.indexPath(tableId));
        return new Index(tableId, indexFile, cache);
    }
}
