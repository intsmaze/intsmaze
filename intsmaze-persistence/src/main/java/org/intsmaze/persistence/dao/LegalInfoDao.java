package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.LegalInfoMapper;
import org.intsmaze.persistence.pojos.LegalInfo;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class LegalInfoDao extends BaseDao<LegalInfo>{
	
	@Autowired
	LegalInfoMapper legalInfoMapper;
	
	public int insert(LegalInfo legalInfo){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		legalInfoMapper.insert(legalInfo);
		return 1;
	}
	
	public List selectAllByPage(LegalInfo legalInfo){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return legalInfoMapper.selectAllByPage(legalInfo);
	}
	
	public List selectAll(LegalInfo legalInfo){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return legalInfoMapper.selectAll(legalInfo);
	}
	
	public int selectAllCount(LegalInfo legalInfo){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return legalInfoMapper.selectAllCount(legalInfo);
	}

}
