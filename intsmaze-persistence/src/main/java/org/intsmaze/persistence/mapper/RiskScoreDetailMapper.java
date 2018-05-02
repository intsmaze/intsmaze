package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.RiskScoreDetail;

public interface RiskScoreDetailMapper {
	
	public List selectAll(RiskScoreDetail riskScoreDetail);
	
	public int selectAllCount(RiskScoreDetail riskScoreDetail);
	
	void deleteByCustomerId(String customerId);
	
}