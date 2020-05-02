package com.intsmaze.lsm.db.write;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.data.Value;
import com.intsmaze.lsm.db.db.Config;
import com.intsmaze.lsm.db.table.memory.MemoryTable;
import com.intsmaze.lsm.db.data.Tuple;
//import com.intsmaze.lsm.db.db.Snapshot;
import com.intsmaze.lsm.db.state.BlockCaches;
import com.intsmaze.lsm.db.state.Paths;
import com.intsmaze.lsm.db.state.Snapshots;
import com.intsmaze.lsm.db.state.Tables;
import com.intsmaze.lsm.db.table.Table;
import com.intsmaze.lsm.db.table.file.FileTable;
import com.intsmaze.lsm.db.table.file.FileTableWriter;
//import com.jordanwilliams.heftydb.io.Throttle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.file.Files;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Handles all write operations to a database. Each write first goes into a MemoryTable,
 * which is written to disk on a background thread once it is full. Writes are serialized so that only one writer
 * thread may proceed at a time.
 */
public class TableWriter {

    private static final Logger logger = LoggerFactory.getLogger(TableWriter.class);

    private final Config config;
    private final Snapshots snapshots;
    private final ThreadPoolExecutor tableExecutor;
    private final Tables tables;//不能改变这个引用了，但是可以向里面添加值
    private final Paths paths;
    private final BlockCaches caches;

    private MemoryTable memoryTable;
    private CommitLogWriter commitLogWriter;


    public TableWriter(Config config, Paths paths, Tables tables, Snapshots snapshots, BlockCaches caches) {
        this.config = config;
        this.paths = paths;
        this.tables = tables;
        this.snapshots = snapshots;
        this.caches = caches;

        //启动后台线程
        this.tableExecutor = new ThreadPoolExecutor(config.tableWriterThreads(), config.tableWriterThreads(),
                Long.MAX_VALUE, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable>(config.tableWriterThreads()),
                new ThreadFactoryBuilder().setNameFormat("Table writer thread %d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }


//    public synchronized Snapshot write(ByteBuffer key, ByteBuffer value, boolean fsync) throws IOException {
        public synchronized boolean write(ByteBuffer key, ByteBuffer value) throws IOException {
        //数据一开始存在memoryTable内存中，当容量超过刷入磁盘
        if (memoryTable == null || memoryTable.size() >= config.memoryTableSize()) {
            rotateMemoryTable();
        }
        long nextSnapshotId = snapshots.incrementAndGet();//这是干嘛用的？？
        key.rewind();//把指针挪回位置1
        if (value != null) {
            value.rewind();
        }
        Key recordKey = new Key(key, nextSnapshotId);
//        Key recordKey = new Key(key);
        Value recordValue = value == null ? Value.TOMBSTONE_VALUE : new Value(value);
        Tuple tuple = new Tuple(recordKey, recordValue);
        commitLogWriter.append(tuple);
//            commitLogWriter.append(tuple, fsync);
        memoryTable.put(tuple);//数据格式
//{Key{data=0, snapshotId=1}=Tuple{key=Key{data=0, snapshotId=1}, value=Value{data=0}},
//Key{data=1, snapshotId=2}=Tuple{key=Key{data=1, snapshotId=2}, value=Value{data=1}}}
//        return new Snapshot(nextSnapshotId);
        return true;
    }

    public void close() throws IOException {
        if (memoryTable != null) {
            commitLogWriter.close();
        }
        tableExecutor.shutdownNow();
    }

    /**
     * 刷磁盘吗？？？？
     * @throws IOException
     */
    private void rotateMemoryTable() throws IOException {
        if (memoryTable != null) {
            commitLogWriter.close();
            writeMemoryTable(memoryTable);//这个是关键呦，将之前的memoryTable刷走，然后创建一个新的memoryTable，里面会将tables里面的memoryTable移除
        }

        long nextTableId = tables.incrementAndGet();//一开始是0，通过这个来依次生成日志文件的编号
        memoryTable = new MemoryTable(nextTableId);//创建一个内存表
        commitLogWriter = CommitLogWriter.open(nextTableId, paths);//生成日志文件
        tables.add(memoryTable);
    }

    /**
     * 将内存数据刷磁盘，看看有没有建索引
     * @param tableToWrite
     */
    private void writeMemoryTable(final Table tableToWrite) {
//        final FileTableWriter.Task task = new FileTableWriter.Task.TupleBlockBuilder().tableId(tableToWrite.id()).level(1)
//                .paths(paths).config(config).source(tableToWrite.ascendingIterator(snapshots.minimumRetainedId())).tupleCount
//                        (tableToWrite.tupleCount())//.throttle(Throttle.MAX)
//                .callback(new FileTableWriter.Task.Callback
//                        () {
//                    @Override
//                    public void finish() {
//                        try {
//                            tables.swap(FileTable.open(tableToWrite.id(), paths, caches.recordBlockCache(),
//                                    caches.indexBlockCache()), tableToWrite);//这个open里面有点6，创建索引，布隆什么一堆
//                            Files.deleteIfExists(paths.logPath(tableToWrite.id()));//成功刷硬盘后会将日志文件清楚，是这个意思吗
//                        } catch (ClosedChannelException e) {
//                            logger.debug("File table was only partially written " + tableToWrite.id());
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }).buildSortedByteMap();
        final FileTableWriter.Task task = new FileTableWriter.Task(tableToWrite.id(),1,paths,config,
                tableToWrite.ascendingIterator(snapshots.minimumRetainedId()),tableToWrite.tupleCount(),
                new FileTableWriter.Task.Callback
                        () {
                    @Override
                    public void finish() {
                        try {
                            //这个地方是将tables里面的MemoryTable移除换为FileTable对象，用处见com.intsmaze.lsm.db.read.TableReader.get()这一处
                            tables.swap(FileTable.open(tableToWrite.id(), paths, caches.recordBlockCache(),
                                    caches.indexBlockCache()), tableToWrite);//这个open里面有点6，创建索引，布隆什么一堆
                            Files.deleteIfExists(paths.logPath(tableToWrite.id()));//成功刷硬盘后会将日志文件清楚，是这个意思吗
                        } catch (ClosedChannelException e) {
                            logger.debug("File table was only partially written " + tableToWrite.id());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        //后台线程会异步将数据刷入磁盘中
//        tableExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                task.run();//关键步骤
//            }
//        });
        task.run();

    }
}
