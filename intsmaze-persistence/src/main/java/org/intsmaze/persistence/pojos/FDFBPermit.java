package org.intsmaze.persistence.pojos;

import java.sql.Timestamp;

import org.intsmaze.persistence.common.BasePojo;

public class FDFBPermit extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -510872095554052109L;

	private String seqno;

	private String permittype;

	private String permitname;

	private String permitresource;

	private String parentseqno;

	private Integer permitorder;

	private String isleaf;

	private String status;

	private Timestamp createon;

	private Timestamp modifyon;

	private String createby;

	private String modifyby;

	private String remark;

	private String roleId;

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno == null ? null : seqno.trim();
	}

	public String getPermittype() {
		return permittype;
	}

	public void setPermittype(String permittype) {
		this.permittype = permittype == null ? null : permittype.trim();
	}

	public String getPermitname() {
		return permitname;
	}

	public void setPermitname(String permitname) {
		this.permitname = permitname == null ? null : permitname.trim();
	}

	public String getPermitresource() {
		return permitresource;
	}

	public void setPermitresource(String permitresource) {
		this.permitresource = permitresource == null ? null : permitresource
				.trim();
	}

	public String getParentseqno() {
		return parentseqno;
	}

	public void setParentseqno(String parentseqno) {
		this.parentseqno = parentseqno == null ? null : parentseqno.trim();
	}

	public Integer getPermitorder() {
		return permitorder;
	}

	public void setPermitorder(Integer permitorder) {
		this.permitorder = permitorder;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf == null ? null : isleaf.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}