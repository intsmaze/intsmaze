package com.intsmaze.lsm.db.table.memory;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.data.Tuple;
import com.intsmaze.lsm.db.data.Value;
import com.intsmaze.lsm.db.util.CloseableIterator;

/**
 * Provides the underlying sorted storage for a MemoryTable.
 */
public interface SortedTupleMap extends Iterable<Tuple> {

    public void put(Key key, Value value);

    public Tuple get(Key key);

    public CloseableIterator<Tuple> ascendingIterator(long snapshotId);

    public CloseableIterator<Tuple> descendingIterator(long snapshotId);

    public CloseableIterator<Tuple> ascendingIterator(Key key, long snapshotId);

    public CloseableIterator<Tuple> descendingIterator(Key key, long snapshotId);

}
