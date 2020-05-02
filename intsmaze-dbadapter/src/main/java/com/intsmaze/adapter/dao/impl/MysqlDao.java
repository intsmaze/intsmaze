package com.intsmaze.adapter.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.intsmaze.adapter.dao.ComDao;
import com.intsmaze.adapter.util.impl.MysqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intsmaze.adapter.dao.BaseDao;

/** 
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43 
 * @describe: 
 */
public class MysqlDao extends BaseDao implements ComDao<ResultSet> {

	private static final Logger logger = LoggerFactory.getLogger(MysqlDao.class);

	private MysqlUtils instance=null;
	
	private Connection connection = null;
	
	public void init() throws SQLException {
		instance=(MysqlUtils)this.getDbUtils();
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
			logger.error("mysql 连接失败", e);
		} 
		finally
		{
			instance.free(null, stmt);
		}
		
		
		Thread.sleep(3000);
		
		// 第二次重试
		try {
			//必须重新建立连接
			logger.error("mysql 进行第二次连接");
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
			res = stmt.executeQuery(sql);
			return res;
		} catch (SQLException e) {
			logger.error("mysql 连接失败", e);
			instance.free(res, stmt, connection);
		} 
		
		Thread.sleep(3000);
		
		// 第二次重试
		try {
			//必须重新建立连接
			connection = instance.getConnection();
			stmt = connection.createStatement();
			res = stmt.executeQuery(sql);
			return res;
		} catch (Exception e) {
			logger.error("", e);
			instance.free(res, stmt, connection);
			throw e;
		}
	}

	public Connection getConnection() throws SQLException  {
		if(connection==null||connection.isClosed())
		{
			connection = instance.getConnection();
			return connection;
		}
		else {
            return connection;
        }
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public MysqlUtils getInstance() {
		return instance;
	}

	public void setInstance(MysqlUtils instance) {
		this.instance = instance;
	}

	@Override
	public List<String> getTables() {
		// TODO Auto-generated method stub
		return null;
		 
	}
	
}
