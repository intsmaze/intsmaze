package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.RiskGradeMapper;
import org.intsmaze.persistence.pojos.RiskGrade;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class RiskGradeDao extends BaseDao<RiskGrade>{
	
	@Autowired
	RiskGradeMapper riskGradeMapper;
	
	public List selectAllByPage(RiskGrade riskScore){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return riskGradeMapper.selectAllByPage(riskScore);
	}
	
	public int selectAllCount(RiskGrade riskScore){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return riskGradeMapper.selectAllCount(riskScore);
	}
	
	public List selectRiskAssessmentTimeManagementList(RiskGrade riskScore){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return riskGradeMapper.selectRiskAssessmentTimeManagementList(riskScore);
	}
	
	public int selectRiskAssessmentTimeManagementListCount(RiskGrade riskScore){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return riskGradeMapper.selectRiskAssessmentTimeManagementListCount(riskScore);
	}
}
