package com.intsmaze.lsm.db.table.memory;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.data.Tuple;
import com.intsmaze.lsm.db.data.Value;
import com.intsmaze.lsm.db.read.LatestTupleIterator;
import com.intsmaze.lsm.db.util.CloseableIterator;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * A SortedTupleMap backed by a ConcurrentSkipListMap. This class is safe to use from multiple concurrent threads.
 */
public class SkipListTupleMap implements SortedTupleMap {

    private final ConcurrentNavigableMap<Key, Tuple> tuples = new ConcurrentSkipListMap<Key, Tuple>();

    @Override
    public void put(Key key, Value value) {
        tuples.put(key, new Tuple(key, value));
    }

    @Override
    public Tuple get(Key key) {
        Map.Entry<Key, Tuple> closestEntry = tuples.floorEntry(key);

        if (closestEntry == null) {
            return null;
        }

        return closestEntry.getKey().data().equals(key.data()) ? closestEntry.getValue() : null;
    }

    @Override
    public CloseableIterator<Tuple> ascendingIterator(long snapshotId) {
        return new LatestTupleIterator(snapshotId, new CloseableIterator.Wrapper<Tuple>(tuples.values().iterator()));
    }

    @Override
    public CloseableIterator<Tuple> descendingIterator(long snapshotId) {
        return new LatestTupleIterator(snapshotId, new CloseableIterator.Wrapper<Tuple>(tuples.descendingMap().values
                ().iterator()));
    }

    @Override
    public CloseableIterator<Tuple> ascendingIterator(Key key, long snapshotId) {
        return new LatestTupleIterator(snapshotId, new CloseableIterator.Wrapper<Tuple>(tuples.tailMap(key,
                true).values().iterator()));
    }

    @Override
    public CloseableIterator<Tuple> descendingIterator(Key key, long snapshotId) {
        return new LatestTupleIterator(snapshotId, new CloseableIterator.Wrapper<Tuple>(tuples.headMap(key,
                true).descendingMap().values().iterator()));
    }

    @Override
    public Iterator<Tuple> iterator() {
        return tuples.values().iterator();
    }
}
