package org.intsmaze.business.service.impl;

import java.util.List;

import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.service.IButtonPermitService;
import org.intsmaze.business.vo.FDFBButtonPermitVo;
import org.intsmaze.persistence.dao.FDFBButtonPermitDao;
import org.intsmaze.persistence.pojos.FDFBButtonPermit;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBButtonPermitServiceImpl extends CommonService implements IButtonPermitService{

	@Autowired
	FDFBButtonPermitDao buttonPermitDao;
	
	public List getButtonPermitByRoleidAndDepidAndPageUrl(String roleid,
			String pageUrl, String depid) {
		// TODO Auto-generated method stub
		FDFBButtonPermit cp = new FDFBButtonPermit();
		cp.setRoleid(roleid);
		cp.setPageUrl(pageUrl);
		cp.setDepid(depid);
		List list = buttonPermitDao.getButtonPermitByRoleidAndDepidAndPageUrl(cp);
		return copyList(list, FDFBButtonPermit.class, FDFBButtonPermitVo.class);
	}

}
