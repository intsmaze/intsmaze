package com.intsmaze.lsm.db.table;

import com.intsmaze.lsm.db.data.Tuple;

/**
 * 表示可变表。
 */
public interface MutableTable extends Table {

    public void put(Tuple tuple);
}
