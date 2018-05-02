package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.RiskScoreDetailMapper;
import org.intsmaze.persistence.pojos.RiskScoreDetail;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class RiskScoreDetailDao extends BaseDao<RiskScoreDetail>{

	@Autowired
	RiskScoreDetailMapper riskScoreDetailMapper;
	
	public List selectAll(RiskScoreDetail riskScoreDetail){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return riskScoreDetailMapper.selectAll(riskScoreDetail);
	}
	
	public int selectAllCount(RiskScoreDetail riskScoreDetail){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return riskScoreDetailMapper.selectAllCount(riskScoreDetail);
	}
	
	public void deleteByCustomerId(String customerId){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		riskScoreDetailMapper.deleteByCustomerId(customerId);
	}
	
}
