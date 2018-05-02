package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.SusReportLogMapper;
import org.intsmaze.persistence.pojos.SusCustomer;
import org.intsmaze.persistence.pojos.SusReportLog;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class SusReportLogDao extends BaseDao<SusReportLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7877406729550973181L;

	@Autowired
	SusReportLogMapper  susReportLogMapper;
	
	public int getSelectiveCount(SusReportLog susReportLog){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susReportLogMapper.getSelectiveCount(susReportLog);
	}
	
	public SusReportLog selectByPrimaryKey(String seqno){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susReportLogMapper.selectByPrimaryKey(seqno);
	}
	
	public int insertSelective(SusReportLog susReportLog){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susReportLogMapper.insertSelective(susReportLog);
	}
	
	
	public List selectAllByPage(SusReportLog susReportLog){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susReportLogMapper.selectAllByPage(susReportLog);
	}
	

	
	
}
