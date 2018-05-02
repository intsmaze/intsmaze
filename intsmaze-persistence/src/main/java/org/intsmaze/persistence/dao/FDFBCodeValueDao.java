package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBCodeValueMapper;
import org.intsmaze.persistence.pojos.FDFBCodeValue;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBCodeValueDao  extends BaseDao<FDFBCodeValue>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7806299981817052360L;
	@Autowired
	FDFBCodeValueMapper codeValueMapper;
    
    public FDFBCodeValue getValueByKeyAndCode(String key, String code)
    {
    	DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
    	return codeValueMapper.getValueByKeyAndCode(key, code);
    }
    
    public FDFBCodeValue getCodeByKeyAndValue(String key, String value)
    {
    	DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
    	return codeValueMapper.getCodeByKeyAndValue(key, value);
    }
    
    public List<FDFBCodeValue> getAllCodeValue()
    {
    	DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
    	return codeValueMapper.getAllCodeValue();
    }
    
    public List<FDFBCodeValue> getAllCodeValueByKey(String key)
    {
    	DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
    	return codeValueMapper.getAllCodeValueByKey(key);
    }
    
    public FDFBCodeValue getCodevalueByKyeAndCode(String key, String code) {
		// TODO Auto-generated method stub
    	DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return codeValueMapper.getCodevalueByKyeAndCode(key, code);
	}
    
    public List getCodeValuesByParentid(String parentid) {
		// TODO Auto-generated method stub
    	DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return codeValueMapper.getCodeValuesByParentid(parentid);
	}
}