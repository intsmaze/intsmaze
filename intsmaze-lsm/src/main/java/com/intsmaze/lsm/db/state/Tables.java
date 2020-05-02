package com.intsmaze.lsm.db.state;

import com.intsmaze.lsm.db.table.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A collection of all of the Tables in a database. Concurrent access from multiple threads is guarded by a
 * ReentrantReadWriteLock.
 */
public class Tables implements Iterable<Table> {

    public interface ChangeHandler {
        public void changed();
    }

    private final AtomicLong currentTableId = new AtomicLong();
    private final NavigableSet<Table> tables = new TreeSet<Table>();
    private final ReadWriteLock tableLock = new ReentrantReadWriteLock();
    private final List<ChangeHandler> changeHandlers = new ArrayList<ChangeHandler>();

    public Tables(Collection<Table> initialTables) {
        this.tables.addAll(initialTables);
        this.currentTableId.set(tables.isEmpty() ? 0 : tables.last().id());
    }

    public synchronized void addChangeHandler(ChangeHandler changeHandler) {
        changeHandlers.add(changeHandler);
    }

    public synchronized void removeChangeHandler(ChangeHandler changeHandler) {
        changeHandlers.remove(changeHandler);
    }

    public long incrementAndGet() {
        return currentTableId.incrementAndGet();
    }

    public long currentId() {
        return currentTableId.get();
    }

    public void readLock() {
        tableLock.readLock().lock();
    }

    public void readUnlock() {
        tableLock.readLock().unlock();
    }

    /**
     * 用treetSet数据结构存储对象
    * @author:YangLiu
    * @date:2018年10月29日 下午9:02:04 
    * @describe:
     */
    public void add(Table toAdd) {
        try {
            tableLock.writeLock().lock();
            tables.add(toAdd);
            notifyChanged();
        } finally {
            tableLock.writeLock().unlock();
        }
    }

    public void removeAll(List<Table> toRemove) {
        try {
            tableLock.writeLock().lock();
            for (Table table : toRemove) {
                tables.remove(table);
            }
            notifyChanged();
        } finally {
            tableLock.writeLock().unlock();
        }
    }

    public void remove(Table toRemove) {
        try {
            tableLock.writeLock().lock();
            tables.remove(toRemove);
            notifyChanged();
        } finally {
            tableLock.writeLock().unlock();
        }
    }

    public void swap(Table toAdd, Table toRemove) {
        try {
            tableLock.writeLock().lock();
            tables.remove(toRemove);
            tables.add(toAdd);
            notifyChanged();
        } finally {
            tableLock.writeLock().unlock();
        }
    }

    public int count() {
        return tables.size();
    }

    @Override
    public Iterator<Table> iterator() {
        return tables.iterator();
    }

    private synchronized void notifyChanged() {
        for (ChangeHandler changeHandler : changeHandlers) {
            changeHandler.changed();
        }
    }
}