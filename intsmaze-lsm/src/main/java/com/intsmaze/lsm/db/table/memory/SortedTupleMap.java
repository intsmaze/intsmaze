package com.intsmaze.lsm.db.table.memory;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.data.Tuple;
import com.intsmaze.lsm.db.data.Value;
import com.intsmaze.lsm.db.util.CloseableIterator;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * 提供MemoryTable的基础排序存储。
 * @date : 2020/5/27 9:50
 * @Param
 * @return
 * @throws
 */
public interface SortedTupleMap extends Iterable<Tuple> {

    public void put(Key key, Value value);

    public Tuple get(Key key);

    public CloseableIterator<Tuple> ascendingIterator(long snapshotId);

    public CloseableIterator<Tuple> descendingIterator(long snapshotId);

    public CloseableIterator<Tuple> ascendingIterator(Key key, long snapshotId);

    public CloseableIterator<Tuple> descendingIterator(Key key, long snapshotId);

}
