package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.SusTransInfoMapper;
import org.intsmaze.persistence.pojos.SusTransInfo;
import org.intsmaze.persistence.util.DBContextHolder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

public class SusTransInfoDao  extends BaseDao<SusTransInfo>{
	
	private static final long serialVersionUID = -6651858615878505039L;
	
	@Autowired
	SusTransInfoMapper  susTransInfoMapper;
	
	public SusTransInfo selectBySuDataId(String suDataId){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susTransInfoMapper.selectBySuDataId(suDataId);
	}
	
	
	public List selectForC2T (SusTransInfo susTransInfo){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susTransInfoMapper.selectForC2T (susTransInfo);
	}
	
	

}
