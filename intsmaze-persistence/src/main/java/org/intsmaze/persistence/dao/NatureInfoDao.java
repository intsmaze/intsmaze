package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.NatureInfoMapper;
import org.intsmaze.persistence.pojos.NatureInfo;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class NatureInfoDao extends BaseDao<NatureInfo>{

	@Autowired
	NatureInfoMapper natureInfoMapper;
	
	public int insert(NatureInfo natureInfo){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		natureInfoMapper.insert(natureInfo);
		return 1;
	}
	
	public List selectAllByPage(NatureInfo natureInfo){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return natureInfoMapper.selectAllByPage(natureInfo);
	}
	
	public List selectAll(NatureInfo natureInfo){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return natureInfoMapper.selectAll(natureInfo);
	}
	
	public int selectAllCount(NatureInfo natureInfo){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return natureInfoMapper.selectAllCount(natureInfo);
	}
}
