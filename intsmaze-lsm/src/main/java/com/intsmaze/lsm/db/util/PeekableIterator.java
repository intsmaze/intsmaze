package com.intsmaze.lsm.db.util;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * 一个允许查看当前值的迭代器。
 * @date : 2020/5/27 9:47
 * @Param
 * @return
 * @throws
 */
public interface PeekableIterator<T> {

    public T current();

    public boolean advance();

}
