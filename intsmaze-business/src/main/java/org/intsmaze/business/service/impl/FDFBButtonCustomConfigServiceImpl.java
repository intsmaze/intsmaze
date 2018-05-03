package org.intsmaze.business.service.impl;

import java.util.List;

import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.service.IButtonCustomConfigService;
import org.intsmaze.business.vo.FDFBButtonCustomConfigVo;
import org.intsmaze.core.exception.FDFBexception;
import org.intsmaze.persistence.dao.FDFBButtonCustomConfigDao;
import org.intsmaze.persistence.pojos.FDFBButtonCustomConfig;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBButtonCustomConfigServiceImpl extends CommonService implements IButtonCustomConfigService{

	@Autowired
	private FDFBButtonCustomConfigDao buttonCustomConfigDao;
	
	public List getButtonConfigByUsernameAndPageUrl(String username,
			String pageUrl) {
		// TODO Auto-generated method stub
		return copyList(buttonCustomConfigDao.getButtonConfigByUsernameAndPageUrl(username, pageUrl), FDFBButtonCustomConfig.class, FDFBButtonCustomConfigVo.class);
	}

	public int delConfigByUsernameAndPageUrl(String username, String pageUrl) {
		// TODO Auto-generated method stub
		return buttonCustomConfigDao.delConfigByUsernameAndPageUrl(username, pageUrl);
	}

	public String insert(FDFBButtonCustomConfigVo record) {
		// TODO Auto-generated method stub
		FDFBButtonCustomConfig config = new FDFBButtonCustomConfig();
		config = (FDFBButtonCustomConfig)copyBean(record, config);
		int count = buttonCustomConfigDao.insert(config);
		if(count > 0)
		{
			return config.getSeqno();
		}
		else
		{
			try {
				throw new FDFBexception("页面字段显示/隐藏设置失败");
			} catch (FDFBexception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}

}
