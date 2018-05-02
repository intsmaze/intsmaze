package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBAuditLog;

public interface FDFBAuditLogMapper {
	
	int deleteByPrimaryKey(String seqno);

    int insert(FDFBAuditLog record);

    int insertSelective(FDFBAuditLog record);

    FDFBAuditLog selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(FDFBAuditLog record);

    int updateByPrimaryKeyWithBLOBs(FDFBAuditLog record);

    int updateByPrimaryKey(FDFBAuditLog record);
    
	List<FDFBAuditLog> getAuditLogListByRefSeqno(String refrenceSeqno);
}