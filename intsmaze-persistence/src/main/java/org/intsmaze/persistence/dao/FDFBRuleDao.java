package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBRuleMapper;
import org.intsmaze.persistence.pojos.FDFBRule;
import org.intsmaze.persistence.pojos.FDFBRuleOperate;
import org.intsmaze.persistence.pojos.FDFBRuleParam;
import org.intsmaze.persistence.pojos.FDFBRuleTable;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBRuleDao extends BaseDao<FDFBRule> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5369416648050006360L;
	
	@Autowired
	FDFBRuleMapper ruleMapper;
	
	public int insert(FDFBRule record){
		return ruleMapper.insert(record);
	}
	
	public int insertParam(FDFBRuleParam record){
		return ruleMapper.insertParam(record);
	}
	
	public int insertOperate(FDFBRuleOperate record){
		return ruleMapper.insertOperate(record);
	}
	
	public List<FDFBRuleTable> selectColumnSource(List list){
		return ruleMapper.selectColumnSource(list);
	}
	
	public List selectAllColumns(){
		return ruleMapper.selectAllColumns();
	}
	
	public List<FDFBRule> selectAllRules(){
		return ruleMapper.selectAllRules();
	}
	
	public FDFBRule selectByPrimaryKey(String ruleid){
		return ruleMapper.selectByPrimaryKey(ruleid);
	};
	
	public List selectRuleParam(String ruleid){
		return ruleMapper.selectRuleParam(ruleid);
	};
	
	public List selectRuleOperate(String ruleid){
		return ruleMapper.selectRuleOperate(ruleid);
	};
	
	public List selectRuleCustomerOperate(String ruleid){
		return ruleMapper.selectRuleCustomerOperate(ruleid);
	};
}