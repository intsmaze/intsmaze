package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBAuditLogMapper;
import org.intsmaze.persistence.pojos.FDFBAuditLog;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBAuditLogDao extends BaseDao<FDFBAuditLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5369416648050006360L;
	
	@Autowired
	FDFBAuditLogMapper auditLogMapper;
	
	public List getAuditLogListByRefSeqno(String refrenceSeqno) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return auditLogMapper.getAuditLogListByRefSeqno(refrenceSeqno);
	}

}
