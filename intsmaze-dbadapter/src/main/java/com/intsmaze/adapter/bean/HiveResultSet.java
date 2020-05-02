package com.intsmaze.adapter.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
/** 
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43 
 * @describe: jdbc的结果下标从1开始
 */
public class HiveResultSet implements Result{
	
	private ResultSet res;

	public HiveResultSet(ResultSet res) {
		super();
		this.res = res;
	}
	
	@Override
    public void close() throws SQLException
	{
		res.close();
	}
	
	@Override
    public boolean hasNext() throws SQLException
	{
		return res.next();
	}
	
	@Override
    public Object next() throws SQLException
	{
		return res;
	}

	/**
	 * 下标从1开始
	 * @param num
	 * @return
	 * @throws SQLException
     */
	@Override
    public Object getObject(int num) throws SQLException
	{
		return res.getObject(num+1);
	}

	@Override
    public Object getObjectByName(String columnName) throws SQLException
	{
		
		return res.getObject(columnName);
	}
	
}
