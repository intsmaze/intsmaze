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
