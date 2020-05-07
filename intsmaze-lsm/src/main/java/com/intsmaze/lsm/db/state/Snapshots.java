package com.intsmaze.lsm.db.state;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Keeps track of Snapshot ids in a database.
 */
public class Snapshots {

    private final AtomicLong currentSnapshotId = new AtomicLong();
    private final SortedSet<Long> retainedSnapshots = new TreeSet<Long>();

    public Snapshots(long startingSnapshotId) {
        this.currentSnapshotId.set(startingSnapshotId);
        retainedSnapshots.add(Long.MAX_VALUE);
    }

    public long incrementAndGet() {
        return currentSnapshotId.incrementAndGet();
    }

    public long currentId() {
        return currentSnapshotId.get();
    }

    public long minimumRetainedId(){
        return retainedSnapshots.first();
    }

    public synchronized void retain(long snapshotId){
        retainedSnapshots.add(snapshotId);
    }

    public synchronized void release(long snapshotId){
        retainedSnapshots.remove(snapshotId);
    }
}
