package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBUserRoleMapper;
import org.intsmaze.persistence.pojos.FDFBUserRole;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBUserRoleDao extends BaseDao<FDFBUserRole>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9067875325366325L;
	
	@Autowired
	private FDFBUserRoleMapper userRoleMapper;
	
	public List<FDFBUserRole> getRolesByUserid(String userid) {
		// TODO Auto-generated method stub
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		FDFBUserRole fur = new FDFBUserRole();
		fur.setUserid(userid);
		return userRoleMapper.getRolesByUserid(fur);
	}
	
	public int deleteUserRolesByUserid(String userid)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		return userRoleMapper.deleteUserRolesByUserid(userid);
	}
	
	public int getCountByUseridAndRoleid(String userid, String roleid) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		int count = userRoleMapper.getCountByUseridAndRoleid(userid, roleid);
		return count;
	}
}
