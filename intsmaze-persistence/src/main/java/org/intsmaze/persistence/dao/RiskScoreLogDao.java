package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.RiskScoreLogMapper;
import org.intsmaze.persistence.pojos.RiskScoreLog;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class RiskScoreLogDao extends BaseDao<RiskScoreLog> {
	
	@Autowired
	RiskScoreLogMapper riskScoreLogMapper;
	
	public List selectAllByPage(RiskScoreLog riskScoreLog){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return riskScoreLogMapper.selectAllByPage(riskScoreLog);
	}
	
	public int selectAllCount(RiskScoreLog riskScoreLog){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return riskScoreLogMapper.selectAllCount(riskScoreLog);
	}
	
}
