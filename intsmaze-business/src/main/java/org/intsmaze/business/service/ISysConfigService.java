package org.intsmaze.business.service;

import java.util.List;
import java.util.Map;

import org.intsmaze.business.vo.FDFBSysConfigVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface ISysConfigService {

	/**
	 * 根据key和code获取系统配置的值
	 * @param key
	 * @param code
	 * @return
	 */
	public String getValueByKeyAndCode(String key, String code);
	
	public List<FDFBSysConfigVo> getAllSysconfigByKey(String key);
	
	public int updateSysConfig(FDFBSysConfigVo record);
	
}
