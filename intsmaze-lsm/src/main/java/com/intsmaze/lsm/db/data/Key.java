package com.intsmaze.lsm.db.data;

import java.nio.ByteBuffer;

/**
 * Encapsulates a database Key. Contains both a ByteBuffer with the Key data as well as a long that represents the snapshot that the Key was written at.
 */
public class Key implements Comparable<Key> {

    private final ByteBuffer data;
    private final long snapshotId;//这个其实没有什么用，只是标识这个数据接收请求的顺序而已  no no标识每一个key的版本，可以获取最新版本

    public Key(ByteBuffer data, long snapshotId) {
        this.data = data;
        this.snapshotId = snapshotId;
    }
    
    public ByteBuffer data() {
        return data;
    }

    public long snapshotId() {
        return snapshotId;
    }

    public int size() {
        return data.capacity();
    }

    public int compareTo(Key o) {
        int compared = data.compareTo(o.data);

        if (compared != 0) {
            return compared;
        }
        return Long.compare(snapshotId, o.snapshotId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        if (snapshotId != key.snapshotId) return false;
        if (data != null ? !data.equals(key.data) : key.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    @Override
    public String toString() {
        byte[] keyArray = new byte[data.capacity()];
        data.rewind();
        data.get(keyArray);
        data.rewind();

        return "Key{" +
                "data=" + new String(keyArray) +
                ", snapshotId=" + snapshotId +
                '}';
    }
}