package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBPermitMapper;
import org.intsmaze.persistence.pojos.FDFBPermit;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBPermitDao extends BaseDao<FDFBPermit> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1353755373356335821L;

	@Autowired
	FDFBPermitMapper permitMapper;

	public List<FDFBPermit> getPermitByParentSeqno(FDFBPermit record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return permitMapper.getPermitByParentSeqno(record);
	}

	public List<FDFBPermit> getRootPermit(FDFBPermit record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return permitMapper.getRootPermit(record);
	}
	
	public List<FDFBPermit> getLeafPermit(FDFBPermit record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return permitMapper.getLeafPermit(record);
	}
}
