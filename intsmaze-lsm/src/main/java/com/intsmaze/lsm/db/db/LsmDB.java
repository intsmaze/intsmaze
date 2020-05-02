package com.intsmaze.lsm.db.db;

import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.data.Tuple;
import com.intsmaze.lsm.db.read.TableReader;
import com.intsmaze.lsm.db.state.BlockCaches;
import com.intsmaze.lsm.db.state.Paths;
import com.intsmaze.lsm.db.state.Snapshots;
import com.intsmaze.lsm.db.state.Tables;
import com.intsmaze.lsm.db.write.TableWriter;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * The main database API implementation
 */
public class LsmDB implements DB {

//	private class InstrumentedScanIterator implements CloseableIterator<Record> {
//
//		private final CloseableIterator<Record> delegate;
//
//		private InstrumentedScanIterator(CloseableIterator<Record> delegate) {
//			this.delegate = delegate;
//		}
//
//		@Override
//		public boolean hasNext() {
//			boolean hasNext = delegate.hasNext();
//			return hasNext;
//		}
//
//		@Override
//		public Record next() {
//			Record record = delegate.next();
//			return record;
//		}
//
//		@Override
//		public void remove() {
//			delegate.remove();
//		}
//
//		@Override
//		public void close() throws IOException {
//			delegate.close();
//		}
//	}

    private final TableWriter tableWriter;
    private final TableReader tableReader;
    // private final Compactor compactor;
    private final Snapshots snapshots;

    public LsmDB(Config config, Paths paths, Tables tables,
                 Snapshots snapshots, BlockCaches caches) {
        this.snapshots = snapshots;
        this.tableWriter = new TableWriter(config, paths, tables, snapshots,
                caches);
        this.tableReader = new TableReader(tables);
    }

    //	@Override
//	public Snapshot put(ByteBuffer key, ByteBuffer value) throws IOException {
//		return tableWriter.write(key, value);
//	}
    @Override
    public boolean put(ByteBuffer key, ByteBuffer value) throws IOException {
        return tableWriter.write(key, value);
    }

    @Override
    public Record get(ByteBuffer key) throws IOException {
        Tuple tuple = tableReader.get(new Key(key, snapshots.currentId()));
        // Tuple tuple = tableReader.get(new Key(key));
        return tuple == null || tuple.value().isEmpty() ? null : new Record(
                tuple);
    }

    ////Scan
//	@Override
//	public CloseableIterator<Record> ascendingIterator(Snapshot snapshot)
//			throws IOException {
//		return new InstrumentedScanIterator(new Record.TupleIterator(
//				tableReader.ascendingIterator(snapshot.id())));
//	}
//
//	@Override
//	public CloseableIterator<Record> ascendingIterator(ByteBuffer key) throws IOException {
//		return new InstrumentedScanIterator(new Record.TupleIterator(
//				tableReader.ascendingIterator(new Key(key, snapshots.currentId()),
//						snapshots.currentId())));
//		// return new InstrumentedScanIterator(new Record.TupleIterator(
//		// tableReader.ascendingIterator(new Key(key), snapshot.id())));
//	}
//	@Override
//	public CloseableIterator<Record> descendingIterator(Snapshot snapshot)
//			throws IOException {
//		return new InstrumentedScanIterator(new Record.TupleIterator(
//				tableReader.descendingIterator(snapshot.id())));
//	}

//	@Override
//	public CloseableIterator<Record> descendingIterator(ByteBuffer key,
//			Snapshot snapshot) throws IOException {
//		return new InstrumentedScanIterator(new Record.TupleIterator(
//				tableReader.descendingIterator(new Key(key, snapshot.id()),
//						snapshot.id())));
//		// return new InstrumentedScanIterator(new Record.TupleIterator(
//		// tableReader.descendingIterator(new Key(key), snapshot.id())));
//	}

//	 @Override
//	 public void retainSnapshot(Snapshot snapshot) {
//	    snapshots.retain(snapshot.id());
//	 }
//
//	@Override
//	public void releaseSnapshot(Snapshot snapshot) {
//		snapshots.release(snapshot.id());
//	}

    @Override
    public synchronized void close() throws IOException {
        // compactor.close();
        tableWriter.close();
        tableReader.close();
    }

    public static DB open(Config config) throws IOException {
        return new DBInitializer(config).initialize();
    }

    @Override
    public TableWriter getTableWriter() {
        return tableWriter;
    }
}