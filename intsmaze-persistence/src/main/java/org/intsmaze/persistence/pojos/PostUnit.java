package org.intsmaze.persistence.pojos;

import java.sql.Timestamp;
import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class PostUnit extends BasePojo{
    private String seqno;

    private Integer version;

    private String postInnerCoding;

    private String unitInnerCoding;

    private String status;
    
    private Date modifyon;
    
    private Date createon;
    
    

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getPostInnerCoding() {
        return postInnerCoding;
    }

    public void setPostInnerCoding(String postInnerCoding) {
        this.postInnerCoding = postInnerCoding == null ? null : postInnerCoding.trim();
    }

    public String getUnitInnerCoding() {
        return unitInnerCoding;
    }

    public void setUnitInnerCoding(String unitInnerCoding) {
        this.unitInnerCoding = unitInnerCoding == null ? null : unitInnerCoding.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}