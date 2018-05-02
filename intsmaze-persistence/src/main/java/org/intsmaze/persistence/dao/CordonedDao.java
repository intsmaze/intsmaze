package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.CordonedMapper;
import org.intsmaze.persistence.pojos.Cordoned;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class CordonedDao extends BaseDao<Cordoned>{
	
	@Autowired
	private CordonedMapper cordonedMapper;
	
	public String selectByPrimaryId(Cordoned cordoned){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return cordonedMapper.selectByPrimaryId(cordoned);
	}
	
	/*
	
	public List selectAll()
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return cordonedMapper.selectAll();
	}

*/
	public List selectAllByPage(Cordoned record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return cordonedMapper.selectAllByPage(record);
	}
    
	public int getAllUsersCount(Cordoned user)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return cordonedMapper.getAllUsersCount(user);
	}
	
}
