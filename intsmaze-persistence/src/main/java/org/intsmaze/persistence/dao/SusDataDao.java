package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.SusDataMapper;
import org.intsmaze.persistence.pojos.FDFBUser;
import org.intsmaze.persistence.pojos.SusData;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class SusDataDao extends BaseDao<SusData> {

	private static final long serialVersionUID = -9016717656910144547L;
	@Autowired
	SusDataMapper susDataMapper;
	

	public List selectAllByPage(SusData susDataPo)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susDataMapper.selectAllByPage(susDataPo);
	}

	public int getAllSusDataCount(SusData susDataPo)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susDataMapper.getAllSusDataCount(susDataPo);
	}

	public List selectAllFirstInspectByPage(SusData susDataPo)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susDataMapper.selectAllFirstInspectByPage(susDataPo);
		
	/*	List list =susDataMapper.selectAllFirstInspectByPage(susDataPo);
		System.out.println("dao detail count=========="+list.size());
		for(int i=0;i<list.size();i++){
			System.out.println("dao detail=========="+((SusData)list.get(i)).getSeqno());
			
		}
		return list;*/
	}
	public int getAllFirstInspectCount(SusData susDataPo)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susDataMapper.getAllFirstInspectCount(susDataPo);
	}
	
	public List selectAllSecondInspectByPage(SusData susDataPo)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susDataMapper.selectAllSecondInspectByPage(susDataPo);
		
	}
	public int getAllSecondInspectCount(SusData susDataPo)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susDataMapper.getAllSecondInspectCount(susDataPo);
	}
	
	public List selectAllFinalInspectByPage(SusData susDataPo)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susDataMapper.selectAllFinalInspectByPage(susDataPo);
		
	}
	public int getAllFinalInspectCount(SusData susDataPo)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susDataMapper.getAllFinalInspectCount(susDataPo);
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	public SusData  updateByPrimaryKeySelectiveForInsert(SusData susDataPo)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susDataMapper.updateByPrimaryKeySelectiveForInsert(susDataPo);
	}
	

	

	
	
	
}



