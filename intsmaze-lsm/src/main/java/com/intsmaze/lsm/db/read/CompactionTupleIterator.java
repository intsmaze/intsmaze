package com.intsmaze.lsm.db.read;

import com.intsmaze.lsm.db.data.Tuple;
import com.intsmaze.lsm.db.util.CloseableIterator;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An Iterator that filters a sorted stream of Tuples, and filters out all key versions older than a minimum snapshot
 * id, or passes along a key if there is only one version of it
 */
public class CompactionTupleIterator implements CloseableIterator<Tuple> {

    private final CloseableIterator<Tuple> tupleIterator;
    private final Queue<Tuple> nextTuples = new LinkedList<Tuple>();
    private final SortedSet<Tuple> currentKeyTuples = new TreeSet<Tuple>();
    private final long minSnapshotId;

    public CompactionTupleIterator(long minSnapshotId, CloseableIterator<Tuple> tupleIterator) {
        this.minSnapshotId = minSnapshotId;
        this.tupleIterator = tupleIterator;
    }

    @Override
    public boolean hasNext() {
        if (!nextTuples.isEmpty()) {
            return true;
        }

        if (!fetchNextTuples()){
            return false;
        }

        return true;
    }

    @Override
    public Tuple next() {
        if (nextTuples.isEmpty()) {
            hasNext();
        }

        return nextTuples.poll();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws IOException {
        tupleIterator.close();
    }

    private boolean fetchNextTuples() {
        while (tupleIterator.hasNext()) {
            Tuple next = tupleIterator.next();

            boolean nextKeyEqualCurrent = currentKeyTuples.isEmpty() || next.key().data().equals(currentKeyTuples
                    .last().key().data());

            if (nextKeyEqualCurrent) {
                currentKeyTuples.add(next);
                continue;
            }

            filterCurrentKeyTuples();
            currentKeyTuples.clear();
            currentKeyTuples.add(next);

            return true;
        }

        if (currentKeyTuples.isEmpty()) {
            return false;
        }

        filterCurrentKeyTuples();
        currentKeyTuples.clear();
        return true;
    }

    private void filterCurrentKeyTuples(){
        int count = 0;

        for (Tuple tuple : currentKeyTuples){
            if (tuple.key().snapshotId() >= minSnapshotId){
                nextTuples.add(tuple);
                count++;
            }
        }

        if (count == 0){
            nextTuples.add(currentKeyTuples.last());
        }
    }
}
