package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class Honor extends BasePojo {
    private String seqno;

    private Date insertTime;

    private Date honorTime;

    private String honorOrgan;

    private String honorDept;

    private String honorReason;

    private String honorWay;

    private String honorMode;

    private String upload;

    private String remark;

    private String path;
    
	private Date startInsertTime;
    
    private Date endInsertTime;
    
    private Date startHonorTime;
    
    private Date endHonorTime;
    
    public Date getStartInsertTime() {
		return startInsertTime;
	}

	public void setStartInsertTime(Date startInsertTime) {
		this.startInsertTime = startInsertTime;
	}

	public Date getEndInsertTime() {
		return endInsertTime;
	}

	public void setEndInsertTime(Date endInsertTime) {
		this.endInsertTime = endInsertTime;
	}

	public Date getStartHonorTime() {
		return startHonorTime;
	}

	public void setStartHonorTime(Date startHonorTime) {
		this.startHonorTime = startHonorTime;
	}

	public Date getEndHonorTime() {
		return endHonorTime;
	}

	public void setEndHonorTime(Date endHonorTime) {
		this.endHonorTime = endHonorTime;
	}

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getHonorTime() {
        return honorTime;
    }

    public void setHonorTime(Date honorTime) {
        this.honorTime = honorTime;
    }

    public String getHonorOrgan() {
        return honorOrgan;
    }

    public void setHonorOrgan(String honorOrgan) {
        this.honorOrgan = honorOrgan == null ? null : honorOrgan.trim();
    }

    public String getHonorDept() {
        return honorDept;
    }

    public void setHonorDept(String honorDept) {
        this.honorDept = honorDept == null ? null : honorDept.trim();
    }

    public String getHonorReason() {
        return honorReason;
    }

    public void setHonorReason(String honorReason) {
        this.honorReason = honorReason == null ? null : honorReason.trim();
    }

    public String getHonorWay() {
        return honorWay;
    }

    public void setHonorWay(String honorWay) {
        this.honorWay = honorWay == null ? null : honorWay.trim();
    }

    public String getHonorMode() {
        return honorMode;
    }

    public void setHonorMode(String honorMode) {
        this.honorMode = honorMode == null ? null : honorMode.trim();
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload == null ? null : upload.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}