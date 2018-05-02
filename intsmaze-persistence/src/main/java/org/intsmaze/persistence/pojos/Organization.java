package org.intsmaze.persistence.pojos;

import java.sql.Timestamp;
import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class Organization extends BasePojo{
    private String seqno;

    private String orgInnerCoding;

    private String orgNo;

    private String orgName;

    private String parentCompnayId;

    private String pOrgInncoding;

    private String status;
    
    private String newOrgNo;
    
    private Date modifyon;
    
    private Date createon;

	public String getNewOrgNo() {
		return newOrgNo;
	}

	public void setNewOrgNo(String newOrgNo) {
		this.newOrgNo = newOrgNo;
	}

	public Date getModifyon() {
		return modifyon;
	}

	public void setModifyon(Date modifyon) {
		this.modifyon = modifyon;
	}

	public Date getCreateon() {
		return createon;
	}

	public void setCreateon(Date createon) {
		this.createon = createon;
	}

	public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getOrgInnerCoding() {
        return orgInnerCoding;
    }

    public void setOrgInnerCoding(String orgInnerCoding) {
        this.orgInnerCoding = orgInnerCoding == null ? null : orgInnerCoding.trim();
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getParentCompnayId() {
        return parentCompnayId;
    }

    public void setParentCompnayId(String parentCompnayId) {
        this.parentCompnayId = parentCompnayId == null ? null : parentCompnayId.trim();
    }

    public String getpOrgInncoding() {
        return pOrgInncoding;
    }

    public void setpOrgInncoding(String pOrgInncoding) {
        this.pOrgInncoding = pOrgInncoding == null ? null : pOrgInncoding.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}