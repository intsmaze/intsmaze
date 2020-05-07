package com.intsmaze.lsm.db.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;

/**
 * An iterator that needs to be closed when it is no longer used so that off-heap memory or file handles can be
 * properly cleaned up.
 * @param <T>
 */
public interface CloseableIterator<T> extends Iterator<T>, Closeable {

    public static class Wrapper<T> implements CloseableIterator<T> {

        private final Iterator<T> delegate;

        public Wrapper(Iterator<T> delegate) {
            this.delegate = delegate;
        }

        @Override
        public void close() throws IOException {

        }

        @Override
        public boolean hasNext() {
            return delegate.hasNext();
        }

        @Override
        public T next() {
            return delegate.next();
        }

        @Override
        public void remove() {
            delegate.remove();
        }
    }

}
