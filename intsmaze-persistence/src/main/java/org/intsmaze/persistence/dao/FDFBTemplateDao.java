package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBTemplateMapper;
import org.intsmaze.persistence.pojos.FDFBTemplate;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBTemplateDao extends BaseDao<FDFBTemplate>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9042413746585131604L;
	@Autowired
	FDFBTemplateMapper templateMapper;
	
	public List selectAllByPage(FDFBTemplate user)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return templateMapper.selectAllByPage(user);
	}
    
	public int getAllTemplatesCount(FDFBTemplate user)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return templateMapper.getAllTemplatesCount(user);
	}
	
	public FDFBTemplate getTemplateStrByType(String type) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return templateMapper.getTemplateStrByType(type);
	}

}
