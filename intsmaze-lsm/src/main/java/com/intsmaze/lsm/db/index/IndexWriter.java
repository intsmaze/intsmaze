package com.intsmaze.lsm.db.index;

import com.intsmaze.lsm.db.io.AppendChannelFile;
import com.intsmaze.lsm.db.io.AppendFile;
import com.intsmaze.lsm.db.state.Paths;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Incrementally writes a B+tree database index file for a stream of sorted records.
 * 增量地为已排序记录流编写B+树数据库索引文件。
 */
public class IndexWriter {

    private final AppendFile indexFile;
    private final int maxIndexBlockSize;
    private final List<IndexBlock.Builder> indexBlockBuilders = new ArrayList<IndexBlock.Builder>();

    private IndexWriter(AppendFile indexFile, int maxIndexBlockSize) {
        this.indexFile = indexFile;
        this.maxIndexBlockSize = maxIndexBlockSize;//65536 初始化的时候指定的
        indexBlockBuilders.add(new IndexBlock.Builder());
    }

    public void write(IndexRecord indexRecord) throws IOException {
        Queue<IndexRecord> pendingIndexRecord = new LinkedList<IndexRecord>();
        pendingIndexRecord.add(indexRecord);

        for (int i = 0; i < indexBlockBuilders.size(); i++) {
            if (pendingIndexRecord.isEmpty()) {
                return;
            }

            IndexBlock.Builder levelBuilder = indexBlockBuilders.get(i);

            //levelBuilder.size()第一次是0，因为是new IndexBlock.Builder()出来的
            if (levelBuilder.size() >= maxIndexBlockSize) {
                IndexRecord metaRecord = writeIndexBlock(levelBuilder.build());

                IndexBlock.Builder newLevelBuilder = new IndexBlock.Builder();
                newLevelBuilder.addRecord(pendingIndexRecord.poll());
                indexBlockBuilders.set(i, newLevelBuilder);

                pendingIndexRecord.add(metaRecord);
            } else {
                levelBuilder.addRecord(pendingIndexRecord.poll());//将key添加进集合
            }
        }

        if (!pendingIndexRecord.isEmpty()) {
            IndexBlock.Builder newLevelBuilder = new IndexBlock.Builder();
            newLevelBuilder.addRecord(pendingIndexRecord.poll());
            indexBlockBuilders.add(newLevelBuilder);
        }
        //我添加一行 目前这个架构师一次写完了再生出数据，如果中途宕机则完了 
        //finish();
    }

    public void finish() throws IOException {
        Queue<IndexRecord> pendingIndexRecord = new LinkedList<IndexRecord>();

        for (int i = 0; i < indexBlockBuilders.size(); i++) {//这个里面好像又保存了所有的
            IndexBlock.Builder levelBuilder = indexBlockBuilders.get(i);

            if (!pendingIndexRecord.isEmpty()) {
                levelBuilder.addRecord(pendingIndexRecord.poll());
            }

            IndexRecord nextLevelRecord = writeIndexBlock(levelBuilder.build());
            pendingIndexRecord.add(nextLevelRecord);
        }

        //这里就是将索引写入磁盘
        IndexRecord rootIndexRecord = pendingIndexRecord.poll();
        indexFile.appendInt(rootIndexRecord.blockSize());
        indexFile.appendLong(rootIndexRecord.blockOffset());
        indexFile.close();
    }

    private IndexRecord writeIndexBlock(IndexBlock indexBlock) throws IOException {
        ByteBuffer indexBlockBuffer = indexBlock.memory().directBuffer();
        indexBlockBuffer.rewind();

        long indexBlockOffset = indexFile.append(indexBlockBuffer);

        IndexRecord metaIndexRecord = new IndexRecord(indexBlock.startRecord().startKey(), indexBlockOffset,
                indexBlockBuffer.capacity(), false);
        indexBlock.memory().release();
        return metaIndexRecord;
    }

    public static IndexWriter open(long tableId, Paths paths, int maxIndexBlockSize) throws IOException {
        AppendFile indexFile = AppendChannelFile.open(paths.indexPath(tableId));
        return new IndexWriter(indexFile, maxIndexBlockSize);
    }
}
