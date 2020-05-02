package com.intsmaze.lsm.db.read;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.state.Tables;
import com.intsmaze.lsm.db.table.Table;
import com.intsmaze.lsm.db.data.Tuple;

import java.io.IOException;

/**
 * Handles read operations across all Tables in a database.
 */
public class TableReader {//implements Iterable<Tuple> {

//    private final TableAggregationIterator.Source ascendingIteratorSource = new TableAggregationIterator.Source() {
//        @Override
//        public CloseableIterator<Tuple> refresh(Key key, long snapshotId) {
//            tables.readLock();
//
//            try {
//                List<CloseableIterator<Tuple>> tableIterators = new ArrayList<CloseableIterator<Tuple>>();
//
//                for (Table table : tables) {
//                    tableIterators.add(key == null ? table.ascendingIterator(snapshotId) : table.ascendingIterator(key,
//                            snapshotId));
//                }
//
//                return new LatestTupleIterator(snapshotId, new MergingIterator<Tuple>(tableIterators));
//            } finally {
//                tables.readUnlock();
//            }
//        }
//    };
//
//    private final TableAggregationIterator.Source descendingIteratorSource = new TableAggregationIterator.Source() {
//        @Override
//        public CloseableIterator<Tuple> refresh(Key key, long snapshotId) {
//            tables.readLock();
//
//            try {
//                List<CloseableIterator<Tuple>> tableIterators = new ArrayList<CloseableIterator<Tuple>>();
//
//                for (Table table : tables) {
//                    tableIterators.add(key == null ? table.descendingIterator(snapshotId) : table.descendingIterator(key,
//                            snapshotId));
//                }
//
//                return new LatestTupleIterator(snapshotId, new MergingIterator<Tuple>(true, tableIterators));
//            } finally {
//                tables.readUnlock();
//            }
//        }
//    };

    private final Tables tables;

    public TableReader(Tables tables) {
        this.tables = tables;
    }

    public Tuple get(Key key) {
        Tuple closestTuple = null;

        //对tables加锁，是因为写操作会更新tables里面的元素，向里面添加元素，导致这个遍历出现问题
        tables.readLock();//tables.add()方法里面也加锁了

        try {
            //遍历所有的table找出最近的哪个key  这个倒叙更好
            for (Table table : tables) {//子类为FileTable
                if (table.mightContain(key)) {
                    Tuple tableTuple = table.get(key);
                    if (tableTuple != null) {
                        if (closestTuple == null || tableTuple.key().snapshotId() > closestTuple.key().snapshotId()) {
                            closestTuple = tableTuple;
                        }
                    }
                }
            }
        } finally {
            tables.readUnlock();
        }
        return closestTuple;
    }

//    public CloseableIterator<Tuple> ascendingIterator(Key key, long snapshotId) {
//        tables.readLock();
//
//        try {
//            List<CloseableIterator<Tuple>> tableIterators = new ArrayList<CloseableIterator<Tuple>>();
//
//            for (Table table : tables) {
//                tableIterators.add(table.ascendingIterator(key, snapshotId));
//            }
//
//            TableAggregationIterator tableAggregationIterator = new TableAggregationIterator(new MergingIterator<Tuple>
//                    (tableIterators), snapshotId, tables, ascendingIteratorSource);
//
//            return new LatestTupleIterator(snapshotId, tableAggregationIterator);
//        } finally {
//            tables.readUnlock();
//        }
//    }
//
//    public CloseableIterator<Tuple> ascendingIterator(long snapshotId) {
//        tables.readLock();
//
//        try {
//            List<CloseableIterator<Tuple>> tableIterators = new ArrayList<CloseableIterator<Tuple>>();
//
//            for (Table table : tables) {
//                tableIterators.add(table.ascendingIterator(snapshotId));
//            }
//
//            TableAggregationIterator tableAggregationIterator = new TableAggregationIterator(new MergingIterator<Tuple>
//                    (tableIterators), snapshotId, tables, ascendingIteratorSource);
//
//            return new LatestTupleIterator(snapshotId, tableAggregationIterator);
//        } finally {
//            tables.readUnlock();
//        }
//    }

//    public CloseableIterator<Tuple> descendingIterator(long snapshotId) {
//        tables.readLock();
//
//        try {
//            List<CloseableIterator<Tuple>> tableIterators = new ArrayList<CloseableIterator<Tuple>>();
//
//            for (Table table : tables) {
//                tableIterators.add(table.descendingIterator(snapshotId));
//            }
//
//            TableAggregationIterator tableAggregationIterator = new TableAggregationIterator(new MergingIterator<Tuple>
//                    (true, tableIterators), snapshotId, tables, descendingIteratorSource);
//
//            return new LatestTupleIterator(snapshotId, tableAggregationIterator);
//        } finally {
//            tables.readUnlock();
//        }
//    }



//    public CloseableIterator<Tuple> descendingIterator(Key key, long snapshotId) {
//        tables.readLock();
//
//        try {
//            List<CloseableIterator<Tuple>> tableIterators = new ArrayList<CloseableIterator<Tuple>>();
//
//            for (Table table : tables) {
//                tableIterators.add(table.descendingIterator(key, snapshotId));
//            }
//
//            TableAggregationIterator tableAggregationIterator = new TableAggregationIterator(new MergingIterator<Tuple>
//                    (true, tableIterators), snapshotId, tables, descendingIteratorSource);
//
//            return new LatestTupleIterator(snapshotId, tableAggregationIterator);
//        } finally {
//            tables.readUnlock();
//        }
//    }

    public synchronized void close() throws IOException {
        tables.readLock();

        try {
            for (Table table : tables) {
                table.close();
            }
        } finally {
            tables.readUnlock();
        }
    }

//    @Override
//    public Iterator<Tuple> iterator() {
//        return ascendingIterator(Long.MAX_VALUE);
//    }
}
