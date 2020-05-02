package com.intsmaze.adapter.bean;

import java.sql.SQLException;
/** 
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43 
 * @describe: 
 */
public interface Result {

	public boolean hasNext() throws SQLException;
	
	public Object next() throws SQLException;
	
	/**
	* @author:YangLiu
	* @date:2017年12月21日 上午11:26:11 
	* @describe:下标从0开始
	 */
	public Object getObject(int num) throws SQLException;
	
	public Object getObjectByName(String columnName) throws SQLException;

	public void close() throws SQLException;

}
