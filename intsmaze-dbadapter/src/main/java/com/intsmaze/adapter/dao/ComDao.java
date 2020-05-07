package com.intsmaze.adapter.dao;

import java.util.List;
/** 
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43 
 * @describe: 
 */
public interface ComDao<T> {
	
	public boolean insert(String sql) throws Exception ;
	
	public T select(String sql) throws Exception ;
	
	public List<String> getTables();
}



