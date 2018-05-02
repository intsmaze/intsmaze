package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBRolePermitMapper;
import org.intsmaze.persistence.pojos.FDFBRolePermit;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBRolePermitDao extends BaseDao<FDFBRolePermit>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6029503513801364275L;

	@Autowired
	FDFBRolePermitMapper rolePermitMapper;
	
	public List selectAll()
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return rolePermitMapper.selectAll();
	}
    
	public int delRolePermitByRoleId(String roleid)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		return rolePermitMapper.delRolePermitByRoleId(roleid);
	}
    
	public List getRolePermitListByRoleid(String roleid)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return rolePermitMapper.getRolePermitListByRoleid(roleid);
	}
}
