package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.RiskScore;

public interface RiskScoreMapper {
	
	public List selectAllByPage(RiskScore riskScore);
	
	public int selectAllCount(RiskScore riskScore);
	
	public void insertFromOdps(RiskScore riskScore);
	
	public List selectAll(RiskScore riskScore);
	
	public void deleteRiskScoreByCustomerId(String customerId);
	
}