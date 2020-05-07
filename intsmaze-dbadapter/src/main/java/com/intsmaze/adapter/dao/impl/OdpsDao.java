package com.intsmaze.adapter.dao.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.intsmaze.adapter.dao.BaseDao;
import com.intsmaze.adapter.dao.ComDao;
import com.intsmaze.adapter.util.impl.OdpsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.odps.Instance;
import com.aliyun.odps.Odps;
import com.aliyun.odps.OdpsException;
import com.aliyun.odps.Table;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.task.SQLTask;

/**
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43
 * @describe:
 */
public class OdpsDao extends BaseDao implements ComDao<Iterator> {

	private static final Logger logger = LoggerFactory.getLogger(OdpsDao.class);

	private Odps odps=null;

	public void init() {
		odps=((OdpsUtils)this.getDbUtils()).getOdps();
	}

	/**
	 * @throws Exception
	 * @throws OdpsException
	 */
	@Override
    public boolean insert(String sql) throws Exception
	{
		Instance i;
		try {
			i = SQLTask.run(odps, sql+";");
			i.waitForSuccess();
			return true;
		} catch (Exception e) {
			logger.error( "the sql: {} occur {} ", sql, e);
			logger.error("尝试第二次执行......");
		}

		Thread.sleep(10000);


		try {
			i = SQLTask.run(odps, sql+";");
			i.waitForSuccess();
			return true;
		} catch (Exception e) {
			logger.error("第二次执行失败，抛出异常......");
			logger.error( "the sql: {} occur {} ", sql, e);
			throw e;
		}
	}

	/**
	 * 要增加连接失败重试机制，发现经常连接不上
	 * @throws Exception
	 */
	@Override
    public Iterator<Record> select(String sql) throws Exception
	{
		Instance i;
		try {
			i = SQLTask.run(odps, sql+";");
			i.waitForSuccess();
			Iterator<Record> records = SQLTask.getResultSet(i);//迭代获取，不会OOM
			return records;
		} catch (Exception e) {
			logger.error("the sql: {} occur {}", sql, e);
			logger.error("尝试第二次执行......");
		}

		Thread.sleep(10000);

		try {
			i = SQLTask.run(odps, sql+";");
			i.waitForSuccess();
			Iterator<Record> records = SQLTask.getResultSet(i);//迭代获取，不会OOM
			return records;
		} catch (Exception e) {
			logger.error("第二次执行失败，抛出异常......");
			logger.error("the sql: {} occur {}", sql, e);
			throw e;
		}
	}


	@Override
    public List<String> getTables()
	{
		List<String> list=new LinkedList<String>();
	    for (Table t : odps.tables()) {
	    	list.add(t.getName());
	    }
	    return list;
	}


}
