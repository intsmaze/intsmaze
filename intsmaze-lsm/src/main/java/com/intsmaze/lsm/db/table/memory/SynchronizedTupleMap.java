package com.intsmaze.lsm.db.table.memory;
//
//import com.jordanwilliams.heftydb.data.Key;
//import com.jordanwilliams.heftydb.data.Tuple;
//import com.jordanwilliams.heftydb.data.Value;
//import com.jordanwilliams.heftydb.read.LatestTupleIterator;
//import com.jordanwilliams.heftydb.util.CloseableIterator;
//
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Map;
//import java.util.NoSuchElementException;
//import java.util.Queue;
//import java.util.TreeMap;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * A SortedTupleMap backed by a TreeMap with a ReentrantReadWrite lock guarding access. This class is safe to use
// * from multiple concurrent threads.
// */
//public class SynchronizedTupleMap implements SortedTupleMap {
//
//    private class LockingIterator implements CloseableIterator<Tuple> {
//
//        private final Queue<Tuple> next = new LinkedList<Tuple>();
//        private Key searchKey;
//
//        LockingIterator(Tuple startingTuple) {
//            if (startingTuple != null) {
//                this.next.add(startingTuple);
//                this.searchKey = startingTuple.key();
//            }
//        }
//
//        @Override
//        public boolean hasNext() {
//            if (!next.isEmpty()) {
//                return true;
//            }
//
//            if (searchKey == null) {
//                return false;
//            }
//
//            lock.lock();
//
//            try {
//                Map.Entry<Key, Value> nextEntry = nextEntry(searchKey);
//
//                if (nextEntry == null) {
//                    return false;
//                }
//
//                searchKey = nextEntry.getKey();
//                next.add(new Tuple(nextEntry.getKey(), nextEntry.getValue()));
//
//                return true;
//            } finally {
//                lock.unlock();
//            }
//        }
//
//        @Override
//        public Tuple next() {
//            if (next.isEmpty()) {
//                if (!hasNext()) {
//                    throw new NoSuchElementException();
//                }
//            }
//
//            return next.poll();
//        }
//
//        @Override
//        public void remove() {
//            throw new UnsupportedOperationException();
//        }
//
//        protected Map.Entry nextEntry(Key key) {
//            return tuples.higherEntry(key);
//        }
//
//        @Override
//        public void close() throws IOException {
//        }
//    }
//
//    private class DescendingLockingIterator extends LockingIterator {
//
//        DescendingLockingIterator(Tuple startingTuple) {
//            super(startingTuple);
//        }
//
//        @Override
//        protected Map.Entry nextEntry(Key key) {
//            return tuples.lowerEntry(key);
//        }
//    }
//
//    private final TreeMap<Key, Value> tuples = new TreeMap<Key, Value>();
//    private final ReentrantLock lock = new ReentrantLock();
//
//    @Override
//    public void put(Key key, Value value) {
//        lock.lock();
//
//        try {
//            tuples.put(key, value);
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    @Override
//    public Tuple get(Key key) {
//        lock.lock();
//
//        try {
//            Map.Entry<Key, Value> closestEntry = tuples.floorEntry(key);
//
//            if (closestEntry == null) {
//                return null;
//            }
//
//            return closestEntry.getKey().data().equals(key.data()) ? new Tuple(closestEntry.getKey(),
//                    closestEntry.getValue()) : null;
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    @Override
//    public CloseableIterator<Tuple> ascendingIterator(long snapshotId) {
//        lock.lock();
//
//        try {
//            Map.Entry<Key, Value> first = tuples.firstEntry();
//            Tuple start = first == null ? null : new Tuple(first.getKey(), first.getValue());
//            return new LatestTupleIterator(snapshotId, new LockingIterator(start));
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    @Override
//    public CloseableIterator<Tuple> descendingIterator(long snapshotId) {
//        lock.lock();
//
//        try {
//            Map.Entry<Key, Value> last = tuples.lastEntry();
//            Tuple start = last == null ? null : new Tuple(last.getKey(), last.getValue());
//            return new LatestTupleIterator(snapshotId, new DescendingLockingIterator(start));
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    @Override
//    public CloseableIterator<Tuple> ascendingIterator(Key key, long snapshotId) {
//        lock.lock();
//
//        try {
//            Map.Entry<Key, Value> ceiling = tuples.ceilingEntry(key);
//            Tuple start = ceiling == null ? null : new Tuple(ceiling.getKey(), ceiling.getValue());
//            return new LatestTupleIterator(snapshotId, new LockingIterator(start));
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    @Override
//    public CloseableIterator<Tuple> descendingIterator(Key key, long snapshotId) {
//        lock.lock();
//
//        try {
//            Map.Entry<Key, Value> floor = tuples.floorEntry(key);
//            Tuple start = floor == null ? null : new Tuple(floor.getKey(), floor.getValue());
//            return new LatestTupleIterator(snapshotId, new DescendingLockingIterator(start));
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    @Override
//    public Iterator<Tuple> iterator() {
//        lock.lock();
//
//        try {
//            Map.Entry<Key, Value> first = tuples.firstEntry();
//            Tuple start = first == null ? null : new Tuple(first.getKey(), first.getValue());
//            return new LockingIterator(start);
//        } finally {
//            lock.unlock();
//        }
//    }
//}
