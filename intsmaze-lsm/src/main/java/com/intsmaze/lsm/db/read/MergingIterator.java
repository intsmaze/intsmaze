package com.intsmaze.lsm.db.read;

import com.intsmaze.lsm.db.util.CloseableIterator;
import com.intsmaze.lsm.db.util.PeekableIterator;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A a generic Iterator that merges multiple sorted Iterators into a single sorted Iterator.
 * @param <T>
 */
public class MergingIterator<T extends Comparable> implements CloseableIterator<T> {

    private static class ComparableIterator<T extends Comparable> implements PeekableIterator<T>,
            Comparable<ComparableIterator<T>> {

        private final CloseableIterator<T> delegate;
        private T current;

        private ComparableIterator(CloseableIterator<T> delegate) {
            this.delegate = delegate;

            if (delegate.hasNext()) {
                current = delegate.next();
            }
        }

        @Override
        public T current() {
            return current;
        }

        @Override
        public boolean advance() {
            if (delegate.hasNext()) {
                current = delegate.next();
                return true;
            }

            return false;
        }

        public void close() throws IOException {
            delegate.close();
        }

        @Override
        public int compareTo(ComparableIterator<T> other) {
            return current.compareTo(other.current);
        }
    }

    private final Queue<T> next = new LinkedList<T>();
    private final PriorityQueue<ComparableIterator<T>> iteratorHeap;

    public MergingIterator(List<CloseableIterator<T>> iterators) {
        this.iteratorHeap = new PriorityQueue<ComparableIterator<T>>();
        buildIteratorHeap(iterators);
    }

    public MergingIterator(CloseableIterator<T>... iterators) {
        this(Arrays.asList(iterators));
    }

    public MergingIterator(boolean descending, CloseableIterator<T>... iterators) {
        this(descending, Arrays.asList(iterators));
    }

    public MergingIterator(boolean descending, List<CloseableIterator<T>> iterators) {
        this.iteratorHeap = descending ? new PriorityQueue<ComparableIterator<T>>(iterators.size(),
                Collections.reverseOrder()) : new PriorityQueue<ComparableIterator<T>>();
        buildIteratorHeap(iterators);
    }

    @Override
    public boolean hasNext() {
        if (!next.isEmpty()) {
            return true;
        }

        if (iteratorHeap.isEmpty()) {
            return false;
        }

        ComparableIterator<T> nextIterator = iteratorHeap.poll();
        T nextCandidate = nextIterator.current();

        if (nextCandidate != null) {
            next.add(nextCandidate);
        }

        if (nextIterator.advance()) {
            iteratorHeap.add(nextIterator);
        }

        if (next.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public T next() {
        if (next.isEmpty()) {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
        }

        return next.poll();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private void buildIteratorHeap(List<CloseableIterator<T>> iteratorList) {
        for (CloseableIterator<T> iterator : iteratorList) {
            if (iterator.hasNext()) {
                iteratorHeap.add(new ComparableIterator<T>(iterator));
            }
        }
    }

    @Override
    public void close() throws IOException {
        for (ComparableIterator<T> comparableIterator : iteratorHeap) {
            comparableIterator.close();
        }
    }
}
