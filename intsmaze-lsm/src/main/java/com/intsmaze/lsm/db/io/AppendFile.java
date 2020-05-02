package com.intsmaze.lsm.db.io;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * An append only file.
 */
public interface AppendFile extends Closeable {

    public long append(ByteBuffer buffer) throws IOException;

    public long appendLong(long value) throws IOException;

    public long appendInt(int value) throws IOException;

    public long size() throws IOException;

    public void sync() throws IOException;
}
