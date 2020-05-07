package com.intsmaze.lsm.db.table;

import com.intsmaze.lsm.db.data.Tuple;

/**
 * Represents a mutable Table.
 */
public interface MutableTable extends Table {

    public void put(Tuple tuple);
}
