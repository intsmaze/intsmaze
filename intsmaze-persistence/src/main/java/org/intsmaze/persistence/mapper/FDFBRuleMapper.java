package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBRule;
import org.intsmaze.persistence.pojos.FDFBRuleOperate;
import org.intsmaze.persistence.pojos.FDFBRuleParam;
import org.intsmaze.persistence.pojos.FDFBRuleTable;

public interface FDFBRuleMapper {
    List<FDFBRuleTable> selectColumnSource(List list);
    
    List selectAllColumns();

    int insert(FDFBRule record);

    int insertParam(FDFBRuleParam record);
    
    int insertOperate(FDFBRuleOperate record);

    FDFBRule selectByPrimaryKey(String ruleid);

    int updateByPrimaryKeySelective(FDFBRule record);

    int updateByPrimaryKey(FDFBRule record);
    
    List<FDFBRule> selectAllRules();
    
    public List selectRuleParam(String ruleid);
	
	public List selectRuleOperate(String ruleid);
	
	public List selectRuleCustomerOperate(String ruleid);
}