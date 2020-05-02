package com.intsmaze.adapter.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.intsmaze.adapter.dao.ComDao;
import com.intsmaze.adapter.util.impl.HiveUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.intsmaze.adapter.dao.BaseDao;

/**
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43 
 * @describe: 
 */
public class HiveDao extends BaseDao implements ComDao<ResultSet> {

	private static final Logger logger = LoggerFactory.getLogger(HiveDao.class);
	
	private HiveUtils instance = null;

	private Connection connection = null;
	
	public void init() throws SQLException {
		instance = (HiveUtils)this.getDbUtils();
		connection = instance.getConnection();
	}
	
	
	@Override
    public boolean insert(String sql) throws Exception {
		
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			instance.free(null, stmt, connection);
			logger.error("", e);
		} finally
		{
			instance.free(null, stmt);
		}
		
		// 第二次重试
		try {
			//必须重新建立连接
			connection = instance.getConnection();
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			logger.error(e.toString());
			instance.free(null, stmt, connection);
			throw e;
		}
		finally
		{
			instance.free(null, stmt);
		}
		
	}

	
	
	@Override
    public ResultSet select(String sql) throws Exception{
		Statement stmt = null;
		ResultSet res=null;
		try {
			stmt = connection.createStatement();
			res = stmt.executeQuery(sql);//优化，不会阻塞，直接返回，内部迭代获取，不会OOM
			return res;
		} catch (SQLException e) {
			instance.free(res, stmt, connection);
			logger.error("", e);
		} 
		
		// 第二次重试
		try {
			//必须重新建立连接
			connection = instance.getConnection();
			stmt = connection.createStatement();
			res = stmt.executeQuery(sql);
			return res;
		} catch (Exception e) {
			instance.free(res, stmt, connection);
			logger.error("", e);
			throw e;
		}
				
	}


	@Override
    public List<String> getTables() {
		// TODO Auto-generated method stub
		return null;
		 
	}

}
