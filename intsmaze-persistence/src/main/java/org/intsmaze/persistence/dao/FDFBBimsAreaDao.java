package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBBimsAreaMapper;
import org.intsmaze.persistence.pojos.FDFBBimsArea;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBBimsAreaDao  extends BaseDao<FDFBBimsArea>{
	
	@Autowired
	private FDFBBimsAreaMapper bimsAreaMapper;
	
	public List selectAllByPage(FDFBBimsArea record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return bimsAreaMapper.selectAllByPage(record);
	}
    
	public List selectAll()
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return bimsAreaMapper.selectAll();
	}
    
	public int getAllBimsAreasCount(FDFBBimsArea record)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return bimsAreaMapper.getAllBimsAreasCount(record);
	}

}
