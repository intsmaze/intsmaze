package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.SystemFileMapper;
import org.intsmaze.persistence.pojos.SystemFile;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class SystemFileDao extends BaseDao<SystemFile> {
	@Autowired
	SystemFileMapper systemFileMapper;
	
	public List selectAllByPage(SystemFile systemFile){
    	DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
    	return systemFileMapper.selectAllByPage(systemFile);
    }
    
    public int getAllTrainsCount(SystemFile systemFile){
    	DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
    	return systemFileMapper.getAllTrainsCount(systemFile);
    }
	
}
