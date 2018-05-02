package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.RiskScoreLog;

public interface RiskScoreLogMapper {

	public List selectAllByPage(RiskScoreLog riskScoreLog);
	
	public int selectAllCount(RiskScoreLog riskScoreLog);
	
}