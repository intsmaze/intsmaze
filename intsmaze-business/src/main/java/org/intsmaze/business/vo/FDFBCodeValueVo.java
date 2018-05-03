package org.intsmaze.business.vo;

import java.sql.Timestamp;
import java.util.Date;

public class FDFBCodeValueVo extends BaseVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 400941437034273692L;

	private String seqno;

	private String cKey;

	private String cValue;

	private String cDesc;

	private String processstatus;

	private Timestamp createon;

	private Timestamp modifyon;

	private String createby;

	private String modifyby;

	private String cCode;
	
	private String parentid;
	
	private String isleaf;

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno == null ? null : seqno.trim();
	}

	public String getcKey() {
		return cKey;
	}

	public void setcKey(String cKey) {
		this.cKey = cKey == null ? null : cKey.trim();
	}

	public String getcValue() {
		return cValue;
	}

	public void setcValue(String cValue) {
		this.cValue = cValue == null ? null : cValue.trim();
	}

	public String getcDesc() {
		return cDesc;
	}

	public void setcDesc(String cDesc) {
		this.cDesc = cDesc == null ? null : cDesc.trim();
	}

	public String getProcessstatus() {
		return processstatus;
	}

	public void setProcessstatus(String processstatus) {
		this.processstatus = processstatus == null ? null : processstatus
				.trim();
	}

	public Timestamp getCreateon() {
		return createon;
	}

	public void setCreateon(Timestamp createon) {
		this.createon = createon;
	}

	public Timestamp getModifyon() {
		return modifyon;
	}

	public void setModifyon(Timestamp modifyon) {
		this.modifyon = modifyon;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby == null ? null : createby.trim();
	}

	public String getModifyby() {
		return modifyby;
	}

	public void setModifyby(String modifyby) {
		this.modifyby = modifyby == null ? null : modifyby.trim();
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode == null ? null : cCode.trim();
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
}