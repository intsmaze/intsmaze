package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.PunishMapper;
import org.intsmaze.persistence.pojos.Punish;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;


public class PunishDao extends BaseDao<Punish>{
	
	@Autowired
	 PunishMapper punishMapper;
	
	public List selectAllByPage(Punish punish)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return punishMapper.selectAllByPage(punish);
	}
    
	public int getAllUsersCount(Punish punish)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return punishMapper.getAllUsersCount(punish);
	}

}