package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBSysConfig;

public interface FDFBSysConfigMapper {
	
	FDFBSysConfig getValueByKeyAndCode(String key, String code);
	
	List<FDFBSysConfig> getAllSysconfigByKey(String key);
	
}