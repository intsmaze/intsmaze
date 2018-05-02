package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.EmpPostMapper;
import org.intsmaze.persistence.pojos.EmpPost;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class EmpPostDao extends BaseDao<EmpPost> {

	@Autowired
	EmpPostMapper empPostMapper;
	
	public List selectAllByPage(EmpPost empPost){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return empPostMapper.selectAllByPage(empPost);
	}
	
	public void setMainPost(String empInnerCoding){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		empPostMapper.setMainPost(empInnerCoding);
	}
}
