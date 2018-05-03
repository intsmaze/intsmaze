package org.intsmaze.business.vo;

import java.sql.Timestamp;

public class FDFBAuditLogVo extends BaseVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2132772831574024028L;

	private String seqno;

    private String logType;

    private String refrenceSeqno;

    private String operEntity;

    private String operUser;

    private Timestamp createon;

    private String createby;

    private Timestamp modifyon;

    private String modifyby;

    private String logInfo;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public String getRefrenceSeqno() {
        return refrenceSeqno;
    }

    public void setRefrenceSeqno(String refrenceSeqno) {
        this.refrenceSeqno = refrenceSeqno == null ? null : refrenceSeqno.trim();
    }

    public String getOperEntity() {
        return operEntity;
    }

    public void setOperEntity(String operEntity) {
        this.operEntity = operEntity == null ? null : operEntity.trim();
    }

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser == null ? null : operUser.trim();
    }

    public Timestamp getCreateon() {
        return createon;
    }

    public void setCreateon(Timestamp createon) {
        this.createon = createon;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Timestamp getModifyon() {
        return modifyon;
    }

    public void setModifyon(Timestamp modifyon) {
        this.modifyon = modifyon;
    }

    public String getModifyby() {
        return modifyby;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby == null ? null : modifyby.trim();
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo == null ? null : logInfo.trim();
    }
}