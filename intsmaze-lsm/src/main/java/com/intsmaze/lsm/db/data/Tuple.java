

package com.intsmaze.lsm.db.data;

import com.intsmaze.lsm.db.util.Serializer;
import com.intsmaze.lsm.db.util.Sizes;

import java.nio.ByteBuffer;

/**
 * A wrapper class that groups a Key and Value together.
 */
public class Tuple implements Comparable<Tuple> {

    public static Serializer<Tuple> SERIALIZER = new Serializer<Tuple>() {
        public int size(Tuple tuple) {
            int size = 0;

            //Key
            size += Sizes.INT_SIZE;
            size += tuple.key().size();
            size += Sizes.LONG_SIZE;

            //Value
            size += Sizes.INT_SIZE;
            size += tuple.value().size();

            return size;
        }

        public void serialize(Tuple tuple, ByteBuffer recordBuffer) {
            //Key
            recordBuffer.putInt(tuple.key.size());
            recordBuffer.put(tuple.key.data());
            recordBuffer.putLong(tuple.key.snapshotId());
            tuple.key().data().rewind();

            //Value
            recordBuffer.putInt(tuple.value.size());
            recordBuffer.put(tuple.value().data());
            tuple.value().data().rewind();

            recordBuffer.rewind();
        }

        public Tuple deserialize(ByteBuffer recordBuffer) {
            //Key
            int keySize = recordBuffer.getInt();
            ByteBuffer keyBuffer = ByteBuffer.allocate(keySize);
            recordBuffer.get(keyBuffer.array());
            long snapshotId = recordBuffer.getLong();
            Key key = new Key(keyBuffer, snapshotId);

            //Value
            int valueSize = recordBuffer.getInt();
            ByteBuffer valueBuffer = ByteBuffer.allocate(valueSize);
            recordBuffer.get(valueBuffer.array());
            Value value = new Value(valueBuffer);

            return new Tuple(key, value);
        }
    };

    private final Key key;
    private final Value value;

    public Tuple(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key key() {
        return key;
    }

    public Value value() {
        return value;
    }

    public int size() {
        return key.size() + value().size();
    }

    public void rewind() {
        key.data().rewind();
        value.data().rewind();
    }

    public int compareTo(Tuple o) {
        return key.compareTo(o.key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (key != null ? !key.equals(tuple.key) : tuple.key != null) return false;
        if (value != null ? !value.equals(tuple.value) : tuple.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
