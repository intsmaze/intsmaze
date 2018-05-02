package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBColumnPermitMapper;
import org.intsmaze.persistence.pojos.FDFBColumnPermit;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBColumnPermitDao  extends BaseDao<FDFBColumnPermit>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8091951815886026413L;
	@Autowired
	FDFBColumnPermitMapper columnPermitMapper;
	
	public List getColumnPermitByRoleidAndDepidAndPageUrl(FDFBColumnPermit record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return columnPermitMapper.getColumnPermitByRoleidAndDepidAndPageUrl(record);
	}
}