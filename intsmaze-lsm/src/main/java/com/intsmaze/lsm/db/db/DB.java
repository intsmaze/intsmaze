package com.intsmaze.lsm.db.db;

import com.intsmaze.lsm.db.write.TableWriter;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * The public API for a database implementation.
 */
public interface DB {

    public boolean put(ByteBuffer key, ByteBuffer value) throws IOException;

    public Record get(ByteBuffer key) throws IOException;

//    public CloseableIterator<Record> ascendingIterator(Snapshot snapshot) throws IOException;
//
//    public CloseableIterator<Record> ascendingIterator(ByteBuffer key) throws IOException;
//
//    public CloseableIterator<Record> descendingIterator(Snapshot snapshot) throws IOException;
//
//    public CloseableIterator<Record> descendingIterator(ByteBuffer key, Snapshot snapshot) throws IOException;

//    public void retainSnapshot(Snapshot snapshot);
//
//    public void releaseSnapshot(Snapshot snapshot);

    public void close() throws IOException;

    /**
    * @Description: 額外添加
    * @Param:
    * @return:
    * @Author: intsmaze
    * @Date: 2019/1/19
    */
    public TableWriter getTableWriter();

}
