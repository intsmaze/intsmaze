package org.intsmaze.persistence.dao;

import java.util.List;
import java.util.Map;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBRoleMapper;
import org.intsmaze.persistence.pojos.FDFBRole;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;


public class FDFBRoleDao extends BaseDao<FDFBRole>{

	@Autowired
	private FDFBRoleMapper roleMapper;
	
	public List selectAllByPage(FDFBRole record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return roleMapper.selectAllByPage(record);
	}

	public List selectAll()
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return roleMapper.selectAll();
	}

	public int getAllFDFBRolesCount(FDFBRole record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return roleMapper.getAllFDFBRolesCount(record);
	}
	
	public List selectRolesByRoleIds(FDFBRole record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return roleMapper.selectRolesByRoleIds(record);
	}

	public List<Map> selectUserList(Map mapBean) {
		// TODO Auto-generated method stub
		return roleMapper.selectUserList(mapBean);
	}
}
