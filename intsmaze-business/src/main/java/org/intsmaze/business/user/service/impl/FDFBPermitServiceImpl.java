package org.intsmaze.business.user.service.impl;

import java.util.List;

import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.user.service.IPermitService;
import org.intsmaze.business.vo.FDFBPermitVo;
import org.intsmaze.persistence.dao.FDFBPermitDao;
import org.intsmaze.persistence.pojos.FDFBPermit;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBPermitServiceImpl extends CommonService implements IPermitService {
	
	private static Logger log = Logger.getLogger(FDFBPermitServiceImpl.class);

	@Autowired
	FDFBPermitDao permitDao;
	
	 
	public List<FDFBPermitVo> getPermitByParentSeqno(String seqno, String permittype, String orderByStr, String roleId) {
		// TODO Auto-generated method stub
		log.debug("get Permit without cache");
		FDFBPermit pt = new FDFBPermit();
		pt.setParentseqno(seqno);
		pt.setPermittype(permittype);
		pt.setOrderByStr(orderByStr);
		pt.setRoleId(roleId);
		List list = permitDao.getPermitByParentSeqno(pt);
		return copyList(list, FDFBPermit.class ,FDFBPermitVo.class);
	}

	 
	public List<FDFBPermitVo> getRootPermit(String permittype,  String orderByStr, String roleId) {
		// TODO Auto-generated method stub
		log.debug("get Permit without cache2");
		FDFBPermit pt = new FDFBPermit();
		pt.setPermittype(permittype);
		pt.setOrderByStr(orderByStr);
		pt.setRoleId(roleId);
		List list = permitDao.getRootPermit(pt);
		return copyList(list, FDFBPermit.class ,FDFBPermitVo.class);
	}

	 
	public List<FDFBPermitVo> getLeafPermit(String permittype,
			String orderByStr, String roleId) {
		// TODO Auto-generated method stub
		FDFBPermit pt = new FDFBPermit();
		pt.setPermittype(permittype);
		pt.setOrderByStr(orderByStr);
		pt.setRoleId(roleId);
		List list = permitDao.getLeafPermit(pt);
		return copyList(list, FDFBPermit.class ,FDFBPermitVo.class);
	}

}
