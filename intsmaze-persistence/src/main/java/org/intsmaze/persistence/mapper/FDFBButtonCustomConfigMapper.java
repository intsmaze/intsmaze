package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBButtonCustomConfig;

public interface FDFBButtonCustomConfigMapper {
	
	List getButtonConfigByUsernameAndPageUrl(String username,
			String pageUrl);
	
	int delConfigByUsernameAndPageUrl(String username, String pageUrl);
}