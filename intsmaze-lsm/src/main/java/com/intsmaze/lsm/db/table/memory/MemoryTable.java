package com.intsmaze.lsm.db.table.memory;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.data.Tuple;
import com.intsmaze.lsm.db.table.MutableTable;
import com.intsmaze.lsm.db.table.Table;
import com.intsmaze.lsm.db.util.CloseableIterator;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * 提供一个保存在内存中的MutableTable。 记录在插入时会保持排序。
 * @date : 2020/5/27 9:49
 * @Param
 * @return
 * @throws
 */
public class MemoryTable implements MutableTable {

    private final long id;//下一批fileter,index,table文件的编号，以及数据来源的log编号
    private final SortedTupleMap records = new SkipListTupleMap();
    private final AtomicLong maxSnapshotId = new AtomicLong();//不懂你的作用
    private final AtomicInteger recordCount = new AtomicInteger();//记录数量
    private final AtomicInteger size = new AtomicInteger();//记录数据的大小

    public MemoryTable(long id) {
        this.id = id;
    }

    @Override
    public void put(Tuple tuple) {
        records.put(tuple.key(), tuple.value());
        recordCount.incrementAndGet();
        size.addAndGet(tuple.size());
        maxSnapshotId.set(tuple.key().snapshotId());
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public boolean mightContain(Key key) {
        return get(key) != null;
    }

    @Override
    public Tuple get(Key key) {
//        System.out.println("内存中读取数据");
        return records.get(key);
    }

    /**
     * 这个是干啥不晓得啊
     */
    @Override
    public CloseableIterator<Tuple> ascendingIterator(long snapshotId) {
        return records.ascendingIterator(snapshotId);
    }

    @Override
    public CloseableIterator<Tuple> descendingIterator(long snapshotId) {
        return records.descendingIterator(snapshotId);
    }

    @Override
    public CloseableIterator<Tuple> ascendingIterator(Key key, long snapshotId) {
        return records.ascendingIterator(key, snapshotId);
    }

    @Override
    public CloseableIterator<Tuple> descendingIterator(Key key, long snapshotId) {
        return records.descendingIterator(key, snapshotId);
    }

    @Override
    public long tupleCount() {
        return recordCount.get();
    }

    @Override
    public long size() {
        return size.get();
    }

    @Override
    public int level() {
        return 0;
    }

    @Override
    public long maxSnapshotId() {
        return maxSnapshotId.get();
    }

    @Override
    public void close() {
    }

    @Override
    public boolean isPersistent() {
        return false;
    }

    @Override
    public Iterator<Tuple> iterator() {
        return records.iterator();
    }

    @Override
    public int compareTo(Table o) {
        return Long.compare(id, o.id());
    }
}
