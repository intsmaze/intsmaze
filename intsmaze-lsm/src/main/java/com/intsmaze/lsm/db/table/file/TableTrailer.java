
package com.intsmaze.lsm.db.table.file;

import com.intsmaze.lsm.db.io.ImmutableFile;
import com.intsmaze.lsm.db.data.Tuple;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Encapsulates meta data stored at the end of a Table file.
 */
public class TableTrailer {

    public static final int SIZE = 28;

    public static class Builder {

        private final long tableId;
        private final int level;
        private long recordCount;
        private long maxSnapshotId;//我不晓得你是干嘛的

        public Builder(long tableId, int level) {
            this.tableId = tableId;
            this.level = level;
        }

        public void put(Tuple tuple) {
            maxSnapshotId = Math.max(tuple.key().snapshotId(), maxSnapshotId);
            recordCount++;
        }

        public TableTrailer build() {
            return new TableTrailer(serialize());
        }

        private ByteBuffer serialize() {
            ByteBuffer trailerBuffer = ByteBuffer.allocate(SIZE);
            trailerBuffer.putLong(tableId);
            trailerBuffer.putInt(level);
            trailerBuffer.putLong(recordCount);
            trailerBuffer.putLong(maxSnapshotId);
            trailerBuffer.rewind();
            return trailerBuffer;
        }
    }

    private final ByteBuffer buffer;
    private final long tableId;
    private final int level;
    private final long recordCount;
    private final long maxSnapshotId;

    public TableTrailer(ByteBuffer buffer) {
        this.tableId = buffer.getLong();
        this.level = buffer.getInt();
        this.recordCount = buffer.getLong();
        this.maxSnapshotId = buffer.getLong();
        buffer.rewind();
        this.buffer = buffer;
    }

    public long tableId() {
        return tableId;
    }

    public long maxSnapshotId() {
        return maxSnapshotId;
    }

    public long recordCount() {
        return recordCount;
    }

    public int level() {
        return level;
    }

    public ByteBuffer buffer() {
        return buffer;
    }

    public static TableTrailer read(ImmutableFile tableFile) throws IOException {
        ByteBuffer trailerBuffer = ByteBuffer.allocate(SIZE);
        tableFile.read(trailerBuffer, tableFile.size() - SIZE);
        trailerBuffer.rewind();
        return new TableTrailer(trailerBuffer);
    }
}
