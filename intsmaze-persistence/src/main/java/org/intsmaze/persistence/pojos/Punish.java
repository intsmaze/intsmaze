package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class Punish extends BasePojo {
    private String seqno;

    private Date insertTime;

    private Date punishTime;

    private String punishOrgan;

    private String organId;

    private String punishReason;

    private String punishContent;

    private String punishWay;

    private String punishMeasure;

    private String upload;

    private String remark;

    private String path;
    
	private Date startInsertTime;
    
    private Date endInsertTime;
    
    private Date startPunishTime;
    
    private Date endPunishTime;
    
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

	public Date getStartPunishTime() {
		return startPunishTime;
	}

	public void setStartPunishTime(Date startPunishTime) {
		this.startPunishTime = startPunishTime;
	}

	public Date getEndPunishTime() {
		return endPunishTime;
	}

	public void setEndPunishTime(Date endPunishTime) {
		this.endPunishTime = endPunishTime;
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

    public Date getPunishTime() {
        return punishTime;
    }

    public void setPunishTime(Date punishTime) {
        this.punishTime = punishTime;
    }

    public String getPunishOrgan() {
        return punishOrgan;
    }

    public void setPunishOrgan(String punishOrgan) {
        this.punishOrgan = punishOrgan == null ? null : punishOrgan.trim();
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId == null ? null : organId.trim();
    }

    public String getPunishReason() {
        return punishReason;
    }

    public void setPunishReason(String punishReason) {
        this.punishReason = punishReason == null ? null : punishReason.trim();
    }

    public String getPunishContent() {
        return punishContent;
    }

    public void setPunishContent(String punishContent) {
        this.punishContent = punishContent == null ? null : punishContent.trim();
    }

    public String getPunishWay() {
        return punishWay;
    }

    public void setPunishWay(String punishWay) {
        this.punishWay = punishWay == null ? null : punishWay.trim();
    }

    public String getPunishMeasure() {
        return punishMeasure;
    }

    public void setPunishMeasure(String punishMeasure) {
        this.punishMeasure = punishMeasure == null ? null : punishMeasure.trim();
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