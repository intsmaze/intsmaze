package org.intsmaze.business.service;

import java.util.List;

import org.intsmaze.business.vo.FDFBButtonCustomConfigVo;



public interface IButtonCustomConfigService {
	
	/**
	 * 根据用户名和页面地址获取用户的字段显示/隐藏设置
	 * @param username
	 * @param pageUrl
	 * @return
	 */
	public List getButtonConfigByUsernameAndPageUrl(String username, String pageUrl);
	
	/**
	 * 根据用户名和页面地址删除用户的字段显示/隐藏设置
	 * @param username
	 * @param pageUrl
	 * @return
	 */
	public int delConfigByUsernameAndPageUrl(String username, String pageUrl);
	
	public String insert(FDFBButtonCustomConfigVo record);

}
