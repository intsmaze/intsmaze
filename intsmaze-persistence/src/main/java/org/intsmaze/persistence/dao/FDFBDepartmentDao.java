package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBDepartmentMapper;
import org.intsmaze.persistence.pojos.FDFBDepartment;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBDepartmentDao  extends BaseDao<FDFBDepartment>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3061363707997813606L;
	@Autowired
	FDFBDepartmentMapper departmentMapper;
    
	public List selectAllByPage(FDFBDepartment record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return departmentMapper.selectAllByPage(record);
	}

	public List selectAll()
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return departmentMapper.selectAll();
	}

	public int getAllFDFBDepsCount(FDFBDepartment record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return departmentMapper.getAllFDFBDepsCount(record);
	}
	
}