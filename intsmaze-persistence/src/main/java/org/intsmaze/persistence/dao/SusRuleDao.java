package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.SusRuleMapper;

import org.intsmaze.persistence.pojos.SusRule;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class SusRuleDao extends BaseDao<SusRule>{

	private static final long serialVersionUID = -226471690338013305L;
	
	@Autowired
	SusRuleMapper  susRuleMapper;
	
	public SusRule selectByTradeFeature(String tradeFeature){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susRuleMapper.selectByTradeFeature(tradeFeature);
	}
}
