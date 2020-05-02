package com.intsmaze.lsm.db.state;

import com.intsmaze.lsm.db.index.IndexBlock;
import com.intsmaze.lsm.db.table.file.TupleBlock;

/**
 * Encapsulates the BlockCaches used in a database.
 */
public class BlockCaches {

    private final TupleBlock.Cache recordBlockCache;
    private final IndexBlock.Cache indexBlockCache;

    public BlockCaches(TupleBlock.Cache recordBlockCache, IndexBlock.Cache indexBlockCache) {
        this.recordBlockCache = recordBlockCache;
        this.indexBlockCache = indexBlockCache;
    }

    public TupleBlock.Cache recordBlockCache() {
        return recordBlockCache;
    }

    public IndexBlock.Cache indexBlockCache() {
        return indexBlockCache;
    }
}
