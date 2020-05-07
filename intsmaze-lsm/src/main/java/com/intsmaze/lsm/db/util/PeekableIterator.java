package com.intsmaze.lsm.db.util;

/**
 * An iterator that allows peeking at the current value.
 * @param <T>
 */
public interface PeekableIterator<T> {

    public T current();

    public boolean advance();

}
