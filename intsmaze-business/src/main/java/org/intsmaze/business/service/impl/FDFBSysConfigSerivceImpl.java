package org.intsmaze.business.service.impl;

import java.util.List;

import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.service.ISysConfigService;
import org.intsmaze.business.vo.FDFBCodeValueVo;
import org.intsmaze.business.vo.FDFBSysConfigVo;
import org.intsmaze.core.util.StringUtil;
import org.intsmaze.persistence.dao.FDFBSysConfigDao;
import org.intsmaze.persistence.pojos.FDFBCodeValue;
import org.intsmaze.persistence.pojos.FDFBSysConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBSysConfigSerivceImpl extends CommonService implements
		ISysConfigService {

	@Autowired
	private FDFBSysConfigDao sysConfigDao;
	
	 
	public String getValueByKeyAndCode(String key, String code) {
		// TODO Auto-generated method stub
		FDFBSysConfig config = sysConfigDao.getValueByKeyAndCode(key, code);
		if(config == null)
		{
			return "";
		}
		else
		{
			return StringUtil.isNullString(config.getSysvalue());
		}
		
	}
	
	 
	public List<FDFBSysConfigVo> getAllSysconfigByKey(String key) {
		// TODO Auto-generated method stub
		List list = sysConfigDao.getAllSysconfigByKey(key);
		return copyList(list, FDFBSysConfig.class, FDFBSysConfigVo.class);
	}

	 
	public int updateSysConfig(FDFBSysConfigVo record) {
		// TODO Auto-generated method stub
		FDFBSysConfig config = new FDFBSysConfig();
		config = (FDFBSysConfig)copyBean(record, config);
		return sysConfigDao.updateByPrimaryKey(config);
	}

}
