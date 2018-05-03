package org.intsmaze.business.service;

import java.util.List;
import java.util.Map;

import org.intsmaze.business.vo.FDFBCodeValueVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface ICodeValueService {
	
	/**
	 * 对于有条件的查询不能用spring cache，可以在mapper。xml中配置cache
	 * @param key
	 * @param code
	 * @return
	 */
	public String getValueByKeyAndCode(String key, String code);
	
	@Cacheable(value="FDFB.application.cache",key="'AllCodeList'")
	public Map<String,String> getAllCodeValue();
	
	public List<FDFBCodeValueVo> getAllCodeValueByKey(String key);
	
	@CacheEvict(key="'AllCodeList'", value = "FDFB.application.cache")
	public void removeAllCodeListCache();
	
	public Map getAllCodeValueMapByKey(String key);
	
	/**
	 * 对于有条件的查询不能用spring cache，可以在mapper。xml中配置cache
	 * @param key
	 * @param code
	 * @return
	 */
	public String getCodeByKeyAndValue(String key, String value);
	
	public FDFBCodeValueVo getCodevalueByKyeAndCode(String key, String code);
	
	public List getCodeValuesByParentid(String parentid);
	
	public FDFBCodeValueVo getCodevalueBySeqno(String seqno);
	
	public String addCodevalue(FDFBCodeValueVo record);
	
	public int modifyCodevalue(FDFBCodeValueVo record);
}
