package com.intsmaze.lsm.db.write;

import com.intsmaze.lsm.db.io.AppendChannelFile;
import com.intsmaze.lsm.db.io.AppendFile;
import com.intsmaze.lsm.db.data.Tuple;
import com.intsmaze.lsm.db.state.Paths;
import com.intsmaze.lsm.db.util.XORShiftRandom;

import java.io.Closeable;
import java.io.IOException;

/**
 * Writes a CommitLog file. Each write can optionally be fsynced if required.
 */
public class CommitLogWriter implements Closeable {

    private final long tableId;
    private final XORShiftRandom pseudoRandom;
    private final AppendFile logFile;

    private CommitLogWriter(long tableId, AppendFile logFile) throws IOException {
        long seed = System.nanoTime();
        this.tableId = tableId;
        this.pseudoRandom = new XORShiftRandom(seed);
        this.logFile = logFile;

        logFile.appendLong(seed);
    }

    //    public void append(Tuple tuple, boolean fsync) throws IOException {
    public void append(Tuple tuple) throws IOException {
        int serializedSize = Tuple.SERIALIZER.size(tuple);

        // Serialize in place to avoid an extra copy
        tuple.rewind();
        long time = System.currentTimeMillis();
        logFile.appendInt(serializedSize);

        logFile.appendInt(tuple.key().size());
        logFile.append(tuple.key().data());
        logFile.appendLong(tuple.key().snapshotId());

        logFile.appendInt(tuple.value().size());
        logFile.append(tuple.value().data());
        logFile.appendInt(pseudoRandom.nextInt());
        tuple.rewind();

//            if (fsync) {
//        logFile.sync();//强制刷磁盘，可以保证系统停电操作系统缓存没有将数据写入磁盘
//            }
    }

    public long tableId() {
        return tableId;
    }

    @Override
    public void close() throws IOException {
        logFile.close();
    }

    public static CommitLogWriter open(long tableId, Paths paths) throws IOException {
        AppendFile logFile = AppendChannelFile.open(paths.logPath(tableId));
        return new CommitLogWriter(tableId, logFile);
    }
}