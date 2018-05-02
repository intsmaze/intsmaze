package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.SusTradeMapper;
import org.intsmaze.persistence.pojos.SusTrade;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class SusTradeDao extends BaseDao<SusTrade>{
	
	@Autowired
	SusTradeMapper susTradeMapper;
	
	public List selectAllByPage(SusTrade susTrade){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susTradeMapper.selectAllByPage(susTrade);
	}
	
	public int selectAllCount(SusTrade susTrade){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susTradeMapper.selectAllCount(susTrade);
	}
	
	public List selectSusTradeList(SusTrade susTrade){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susTradeMapper.selectSusTradeList(susTrade);
	}
	
	public int selectSusTradeListCount(SusTrade susTrade){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susTradeMapper.selectSusTradeListCount(susTrade);
	}
}
