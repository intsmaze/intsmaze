

package com.intsmaze.lsm.db.data;

import com.intsmaze.lsm.db.util.ByteBuffers;

import java.nio.ByteBuffer;

/**
 * A wrapper class around a ByteBuffer that encapsulates a database Value.
 */
public class Value implements Comparable<Value> {

    public static Value TOMBSTONE_VALUE = new Value(ByteBuffers.EMPTY_BUFFER);

    private final ByteBuffer value;

    public Value(ByteBuffer value) {
        this.value = value;
    }

    public ByteBuffer data() {
        return value;
    }

    public boolean isEmpty() {
        return value.capacity() == 0;
    }

    public int size() {
        return value.capacity();
    }

    public int compareTo(Value o) {
        return value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Value value1 = (Value) o;

        if (value != null ? !value.equals(value1.value) : value1.value != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Value{" +
                "data=" + new String(value.array()) +
                "}";
    }
}
