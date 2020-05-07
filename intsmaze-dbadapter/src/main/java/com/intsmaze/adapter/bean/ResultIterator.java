package com.intsmaze.adapter.bean;

import java.sql.SQLException;
import java.util.Iterator;
import com.aliyun.odps.data.Record;

/**
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43
 * @describe: ODPS的迭代对象，下标从0开始
 */
public class ResultIterator implements Result {

    //	Iterator<Record> res = (Iterator<Record>) result;
//	while (res.hasNext()) {
//		Record str = (Record) res.next();
//		str.get(0);
//	}
    private Iterator<Record> res;

    private Record record;

    public ResultIterator(Iterator res) {
        super();
        this.res = res;
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public boolean hasNext() throws SQLException {
        return res.hasNext();
    }

    @Override
    public Object next() throws SQLException {
        record = res.next();
        return record;
    }

    /**
     * @param num 下标从0开始
     * @return
     * @throws SQLException
     */
    @Override
    public Object getObject(int num) throws SQLException {

        return record.get(num);
    }

    @Override
    public Object getObjectByName(String columnName) throws SQLException {

        return record.get(columnName);
    }
}
