package org.intsmaze.business.service.impl;

import java.util.List;

import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.service.IAuditLogService;
import org.intsmaze.business.vo.FDFBAuditLogVo;
import org.intsmaze.persistence.dao.FDFBAuditLogDao;
import org.intsmaze.persistence.pojos.FDFBAuditLog;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBAuditLogServiceImpl extends CommonService implements IAuditLogService{

	@Autowired
	FDFBAuditLogDao auditLogDao;
	
	public List getAuditLogListByRefSeqno(String refSerqno) {
		// TODO Auto-generated method stub
		List list = auditLogDao.getAuditLogListByRefSeqno(refSerqno);
		return copyList(list, FDFBAuditLog.class, FDFBAuditLogVo.class);
	}

}
