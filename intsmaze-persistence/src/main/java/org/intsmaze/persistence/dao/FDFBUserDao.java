package org.intsmaze.persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBUserMapper;
import org.intsmaze.persistence.pojos.FDFBUser;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBUserDao extends BaseDao<FDFBUser>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8210210156580849908L;

	@Autowired
	FDFBUserMapper userMapper;
	
	public FDFBUser selectByUsernameAndPassword(FDFBUser user)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.selectByUsernameAndPassword(user);
	}
    
	public List selectAllByPage(FDFBUser user)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.selectAllByPage(user);
	}
    
	public List selectAll()
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.selectAll();
	}
    
	public int getAllUsersCount(FDFBUser user)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.getAllUsersCount(user);
	}
    
	public List getUsersByRoleidAndDepid(String roleid, String depid) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.getUsersByRoleidAndDepid(roleid, depid);
	}
	
	public int getUserCountByUsername(String username) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.getUserCountByUsername(username);
	}
	
	public List getUserByUsername(String username) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.getUserByUsername(username);
	}

	public Map getUserOrgParentInfo(Map date) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.getUserOrgParentInfo(date);
	}

	public List<Map> getFirstOrgInfo(Map date) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.getFirstOrgInfo(date);
	}

	public Map getOrgInfo(Map date) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.getOrgInfo(date);
	}
	
	public Map getOrganInfo(String seqno) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.getOrganInfo(seqno);
	}
	
	public List getAllUserWithOrgByPage(FDFBUser user){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.getAllUserWithOrgByPage(user);
	}
	
	public int getAllUserWithCount(FDFBUser user){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.getAllUserWithCount(user);
	}
}
