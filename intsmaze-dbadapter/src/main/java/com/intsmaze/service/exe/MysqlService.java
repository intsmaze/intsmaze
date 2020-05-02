package com.intsmaze.service.exe;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import com.intsmaze.service.util.AmlException;
import com.intsmaze.service.util.FilesNameUtils;
import com.intsmaze.adapter.dao.impl.MysqlDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author:YangLiu
 * @date:2017年12月26日 上午9:51:04
 * @describe:
 */
public class MysqlService<T> {

	private static final Logger logger = LoggerFactory
			.getLogger(MysqlService.class);

	private MysqlDao mysqlDao;

	public MysqlDao getMysqlDao() {
		return mysqlDao;
	}

	public void setMysqlDao(MysqlDao mysqlDao) {
		this.mysqlDao = mysqlDao;
	}

	public void assembleBeantoPS(PreparedStatement ps, int number,
			String fileName, Object bean) throws Exception {
		Type fileType = FilesNameUtils.getFieldType(fileName, bean);
		System.out.println(fileType+"-------------");
		if (fileType == String.class) {
			logger.debug((number + 1)+"...."+fileName+"....."+FilesNameUtils.getFieldValueByName(fileName, bean));
			ps.setString(number + 1,
					(String) FilesNameUtils.getFieldValueByName(fileName, bean));
		}
		else if ("long".equals(fileType.toString()+"")) {
			logger.debug((number + 1)+"...."+fileName+"....."+FilesNameUtils.getFieldValueByName(fileName, bean));
			ps.setLong(number + 1,(Long) FilesNameUtils.getFieldValueByName(fileName, bean));
		}
		else if (fileType == Long.class) {
			logger.debug((number + 1)+"...."+fileName+"....."+FilesNameUtils.getFieldValueByName(fileName, bean));
			ps.setLong(number + 1,(Long) FilesNameUtils.getFieldValueByName(fileName, bean));
		}
		else if (fileType == Date.class) {
			logger.debug((number + 1)+"...."+fileName+"....."+FilesNameUtils.getFieldValueByName(fileName, bean));
			ps.setDate(number + 1,new java.sql.Date(((Date)FilesNameUtils.getFieldValueByName(fileName, bean)).getTime()));
		}
	}

	/**
	 * @author:YangLiu
	 * @date:2017年12月25日 下午3:52:20
	 * @describe:
	 */
	public void insert(String sql, List<T> list,
			String[] names) throws Exception {
		boolean iserror=false;
		PreparedStatement ps = null;
		try {
			ps = mysqlDao.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < list.size(); i++) {
				T bigamount = list.get(i);
				try{
					for (int j = 0; j < names.length; j++) {
						assembleBeantoPS(ps, j, names[j], bigamount);
					}
					ps.executeUpdate();
				}catch (Exception e) {
					iserror=true;
					logger.error("插入数据发生错误， occur {} ", e);
					logger.error("异常数据 {} ", bigamount);
				}
			}
		} catch (Exception e) {
			mysqlDao.getInstance().free(null, ps, mysqlDao.getConnection());
			logger.error("the sql: {} occur {} ", sql, e);
			throw new AmlException("mysql建立连接时发生异常");
		} finally {
			mysqlDao.getInstance().free(null, ps);
			if(iserror)
			{
				throw new AmlException("向mysql中导入数据时发生异常");
			}
		}
	}
	
	/**
	 * @author:YangLiu
	 * @date:2017年12月25日 下午3:52:20
	 * @describe:
	 */
	public void insertBatch(String sql, List<T> list,
			String[] names) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = mysqlDao.getConnection().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < list.size(); i++) {
				T bigamount = list.get(i);
				for (int j = 0; j < names.length; j++) {
					assembleBeantoPS(ps, j, names[j], bigamount);
				}
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (Exception e) {
			mysqlDao.getInstance().free(null, ps, mysqlDao.getConnection());
			logger.error("the sql: {} occur {} ", sql, e);
			throw new AmlException("向mysql中导入数据时发生异常");
		} finally {
			// 连接不关闭，关闭其他资源即可
			mysqlDao.getInstance().free(null, ps);
		}
	}



}
