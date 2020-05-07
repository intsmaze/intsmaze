package com.intsmaze.lsm.db.io;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * An immutable file.
 */
public interface ImmutableFile extends Closeable {

    public long read(ByteBuffer bufferToRead, long position) throws IOException;

    public int readInt(long position) throws IOException;

    public long readLong(long position) throws IOException;

    public long size() throws IOException;
}
