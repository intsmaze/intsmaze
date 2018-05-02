package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBSysConfigMapper;
import org.intsmaze.persistence.pojos.FDFBSysConfig;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBSysConfigDao extends BaseDao<FDFBSysConfig>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3798736974974221194L;
	
	@Autowired
	private FDFBSysConfigMapper sysConfigMapper;

	public FDFBSysConfig getValueByKeyAndCode(String key, String code)
	{
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return sysConfigMapper.getValueByKeyAndCode(key, code);
	}
	
	public List<FDFBSysConfig> getAllSysconfigByKey(String key) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return sysConfigMapper.getAllSysconfigByKey(key);
	}
}
