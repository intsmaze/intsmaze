package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBButtonCustomConfigMapper;
import org.intsmaze.persistence.pojos.FDFBButtonCustomConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBButtonCustomConfigDao extends BaseDao<FDFBButtonCustomConfig> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9164794878838003094L;
	
	@Autowired
	private FDFBButtonCustomConfigMapper configMapper;
	
	public List getButtonConfigByUsernameAndPageUrl(String username,
			String pageUrl) {
		// TODO Auto-generated method stub
		return configMapper.getButtonConfigByUsernameAndPageUrl(username, pageUrl);
	}
	
	public int delConfigByUsernameAndPageUrl(String username, String pageUrl) {
		// TODO Auto-generated method stub
		return configMapper.delConfigByUsernameAndPageUrl(username, pageUrl);
	}

}
