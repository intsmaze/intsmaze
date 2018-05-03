package org.intsmaze.business.service.impl;

import org.intsmaze.business.service.ILoginfoService;
import org.intsmaze.persistence.dao.FDFBLoginfoDao;
import org.intsmaze.persistence.pojos.FDFBLoginfo;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBLoginfoServiceImpl implements ILoginfoService{

	@Autowired
	private FDFBLoginfoDao loginfoDao;
	
	 
	public int addInfo(String type, String errInfo, String createby, String modifyby) {
		// TODO Auto-generated method stub
		FDFBLoginfo fdfbLoginfo = new FDFBLoginfo();
		fdfbLoginfo.setType(type);
		fdfbLoginfo.setErrormsg(errInfo);
		fdfbLoginfo.setCreateby(createby);
		fdfbLoginfo.setModifyby(modifyby);
		return loginfoDao.insert(fdfbLoginfo);
	}

}
