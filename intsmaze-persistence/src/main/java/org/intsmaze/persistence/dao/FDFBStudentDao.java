package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBStudentMapper;
import org.intsmaze.persistence.pojos.FDFBStudent;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBStudentDao extends BaseDao<FDFBStudent>{

	@Autowired
	FDFBStudentMapper studentMapper;
	
	public List selectAllByPage(FDFBStudent student)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return studentMapper.selectAllByPage(student);
	}
    
	public int getAllUsersCount(FDFBStudent user)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return studentMapper.getAllUsersCount(user);
	}
}
