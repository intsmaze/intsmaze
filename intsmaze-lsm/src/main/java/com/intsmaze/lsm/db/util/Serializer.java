package com.intsmaze.lsm.db.util;


import java.nio.ByteBuffer;

public interface Serializer<V> {

    public int size(V item);

    public void serialize(V item, ByteBuffer buffer);

    public V deserialize(ByteBuffer buffer);

}
