package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.RiskScoreMapper;
import org.intsmaze.persistence.pojos.RiskScore;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class RiskScoreDao extends BaseDao<RiskScore>{
	
	@Autowired
	RiskScoreMapper riskScoreMapper;
	
	public List selectAllByPage(RiskScore riskScore){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return riskScoreMapper.selectAllByPage(riskScore);
	}
	
	public int selectAllCount(RiskScore riskScore){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return riskScoreMapper.selectAllCount(riskScore);
	}
	
	public void insertFromOdps(RiskScore riskScore){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		riskScoreMapper.insertFromOdps(riskScore);
	}
	
	public List selectAll(RiskScore riskScore){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return riskScoreMapper.selectAll(riskScore);
	}
	
	public void deleteRiskScoreByCustomerId(String customerId){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		riskScoreMapper.deleteRiskScoreByCustomerId(customerId);
	}
	
}
