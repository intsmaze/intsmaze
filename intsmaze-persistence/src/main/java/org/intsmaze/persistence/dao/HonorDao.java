package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.HonorMapper;
import org.intsmaze.persistence.pojos.Honor;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class HonorDao extends BaseDao<Honor>{
	
	@Autowired
	 HonorMapper honorMapper;
	
	public List selectAllByPage(Honor honor)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return honorMapper.selectAllByPage(honor);
	}
   
	public int getAllUsersCount(Honor honor)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return honorMapper.getAllUsersCount(honor);
	}
	
}