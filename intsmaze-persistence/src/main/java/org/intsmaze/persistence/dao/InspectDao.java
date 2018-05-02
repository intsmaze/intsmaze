package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.InspectMapper;
import org.intsmaze.persistence.pojos.Inspect;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class InspectDao extends BaseDao<Inspect> {
	
	@Autowired
	 InspectMapper inspectMapper;
	
	public List selectAllByPage(Inspect inspect)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return inspectMapper.selectAllByPage(inspect);
	}
    
	public int getAllUsersCount(Inspect inspect)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return inspectMapper.getAllUsersCount(inspect);
	}

}
