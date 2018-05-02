package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBButtonPermitMapper;
import org.intsmaze.persistence.pojos.FDFBButtonPermit;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBButtonPermitDao extends BaseDao<FDFBButtonPermit>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4723151016601911348L;
	
	@Autowired
	FDFBButtonPermitMapper buttonPermitMapper;
	
	public List getButtonPermitByRoleidAndDepidAndPageUrl(FDFBButtonPermit record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return buttonPermitMapper.getButtonPermitByRoleidAndDepidAndPageUrl(record);
	}

}
