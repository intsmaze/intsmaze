package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.UnitMapper;
import org.intsmaze.persistence.pojos.Unit;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class UnitDao extends BaseDao<Unit> {

	@Autowired
	UnitMapper unitMapper;
	
	public List selectAllByPage(Unit unit){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return unitMapper.selectAllByPage(unit);
	}
	
}
