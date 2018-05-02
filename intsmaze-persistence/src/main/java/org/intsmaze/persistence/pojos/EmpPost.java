package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;


public class EmpPost extends BasePojo{
    private String seqno;

    private Integer version;

    private String empInnerCoding;

    private String postInnerCoding;

    private String status;

    private String mainPost;
    
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

    public String getEmpInnerCoding() {
        return empInnerCoding;
    }

    public void setEmpInnerCoding(String empInnerCoding) {
        this.empInnerCoding = empInnerCoding == null ? null : empInnerCoding.trim();
    }

    public String getPostInnerCoding() {
        return postInnerCoding;
    }

    public void setPostInnerCoding(String postInnerCoding) {
        this.postInnerCoding = postInnerCoding == null ? null : postInnerCoding.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMainPost() {
        return mainPost;
    }

    public void setMainPost(String mainPost) {
        this.mainPost = mainPost == null ? null : mainPost.trim();
    }
}