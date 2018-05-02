package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.RiskGrade;

public interface RiskGradeMapper {
	
	public List selectAllByPage(RiskGrade riskScore);
	
	public int selectAllCount(RiskGrade riskScore);
	
	public List selectRiskAssessmentTimeManagementList(RiskGrade riskScore);
	
	public int selectRiskAssessmentTimeManagementListCount(RiskGrade riskScore);
	
}