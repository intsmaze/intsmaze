package com.intsmaze.adapter.dao;

import com.intsmaze.adapter.util.AbstractDataBaseUtils;

/** 
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43 
 * @describe: 
 */
public class BaseDao {

	private AbstractDataBaseUtils dbUtils=null;

	public AbstractDataBaseUtils getDbUtils() {
		return dbUtils;
	}

	public void setDbUtils(AbstractDataBaseUtils dbUtils) {
		this.dbUtils = dbUtils;
	}

}
