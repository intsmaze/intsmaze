package com.intsmaze.lsm.db.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;


/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * 一个迭代器当不再使用被关闭时，可以正确清理堆外内存或文件句柄。
 * @date : 2020/5/27 9:46
 * @Param
 * @return  
 * @throws 
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
