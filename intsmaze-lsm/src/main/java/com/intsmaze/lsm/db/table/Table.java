package com.intsmaze.lsm.db.table;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.data.Tuple;
import com.intsmaze.lsm.db.util.CloseableIterator;

/**
 * 表示数据库中排序记录的不可变集合。
 */
public interface Table extends Iterable<Tuple>, Comparable<Table> {

    public long id();

    public boolean mightContain(Key key);

    public Tuple get(Key key);

    public CloseableIterator<Tuple> ascendingIterator(long snapshotId);

    public CloseableIterator<Tuple> descendingIterator(long snapshotId);

    public CloseableIterator<Tuple> ascendingIterator(Key key, long snapshotId);

    public CloseableIterator<Tuple> descendingIterator(Key key, long snapshotId);

    public long tupleCount();

    public long size();

    public int level();

    public long maxSnapshotId();

    public void close();

    public boolean isPersistent();
}
