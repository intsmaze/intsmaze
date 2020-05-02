package com.intsmaze.lsm.db.read;

import com.intsmaze.lsm.db.data.Tuple;
import com.intsmaze.lsm.db.util.CloseableIterator;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An Iterator that filters a sorted stream of Tuples, and returns only a single Tuple for each unique Key in the
 * stream that is the latest version of that particular Tuple according to the snapshot id associated with each key.
 */
public class LatestTupleIterator implements CloseableIterator<Tuple> {

    private final CloseableIterator<Tuple> tupleIterator;
    private final Queue<Tuple> nextTuple = new LinkedList<Tuple>();
    private final long maxSnapshotId;
    private final SortedSet<Tuple> currentKeyTuples = new TreeSet<Tuple>();

    public LatestTupleIterator(long maxSnapshotId, CloseableIterator<Tuple> tupleIterator) {
        this.maxSnapshotId = maxSnapshotId;
        this.tupleIterator = tupleIterator;
    }

    @Override
    public boolean hasNext() {
        if (!nextTuple.isEmpty()) {
            return true;
        }

        Tuple tuple = fetchNextTuple();

        if (tuple == null) {
            return false;
        }

        nextTuple.add(tuple);

        return true;
    }

    @Override
    public Tuple next() {
        if (nextTuple.isEmpty()) {
            hasNext();
        }

        return nextTuple.poll();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private Tuple fetchNextTuple() {
        while (tupleIterator.hasNext()) {
            Tuple next = tupleIterator.next();

            if (next.key().snapshotId() > maxSnapshotId) {
                continue;
            }

            boolean nextKeyEqualCurrent = currentKeyTuples.isEmpty() || next.key().data().equals(currentKeyTuples
                    .last().key().data());

            if (nextKeyEqualCurrent) {
                currentKeyTuples.add(next);
                continue;
            }

            Tuple newest = currentKeyTuples.last();
            currentKeyTuples.clear();
            currentKeyTuples.add(next);
            return newest;
        }

        if (currentKeyTuples.isEmpty()) {
            return null;
        }

        Tuple newest = currentKeyTuples.last();
        currentKeyTuples.clear();
        return newest;
    }

    @Override
    public void close() throws IOException {
        tupleIterator.close();
    }
}
