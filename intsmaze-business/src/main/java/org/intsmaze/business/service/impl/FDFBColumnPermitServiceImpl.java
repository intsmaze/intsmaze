package org.intsmaze.business.service.impl;

import java.util.List;

import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.service.IColumnPermitService;
import org.intsmaze.business.vo.FDFBColumnPermitVo;
import org.intsmaze.persistence.dao.FDFBColumnPermitDao;
import org.intsmaze.persistence.mapper.FDFBColumnPermitMapper;
import org.intsmaze.persistence.pojos.FDFBColumnPermit;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBColumnPermitServiceImpl extends CommonService implements IColumnPermitService{

	@Autowired
	FDFBColumnPermitDao columnPermitDao;
	public List getColumnPermitByRoleidAndDepidAndPageUrl(String roleid, String pageUrl, String depid) {
		// TODO Auto-generated method stub
		FDFBColumnPermit cp = new FDFBColumnPermit();
		cp.setRoleid(roleid);
		cp.setPageUrl(pageUrl);
		cp.setDepid(depid);
		List list = columnPermitDao.getColumnPermitByRoleidAndDepidAndPageUrl(cp);
		return copyList(list, FDFBColumnPermit.class, FDFBColumnPermitVo.class);
	}

}
