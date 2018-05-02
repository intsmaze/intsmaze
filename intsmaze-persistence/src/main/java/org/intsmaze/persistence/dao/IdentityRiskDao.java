package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.IdentityRiskMapper;
import org.intsmaze.persistence.pojos.IdentityRisk;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class IdentityRiskDao extends BaseDao<IdentityRisk>{
	
	@Autowired
	IdentityRiskMapper identityRiskMapper;
	
	public int selectYearNatureCount(){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return identityRiskMapper.selectYearNatureCount();
	}

	public int selectYearNatureCounts(IdentityRisk identityRisk) {
		// TODO Auto-generated method stub
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return identityRiskMapper.selectYearNatureCounts(identityRisk);
	}
}
