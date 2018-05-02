package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class Inspect extends BasePojo{
    private String seqno;

    private Date insertTime;

    private Date inspectTime;

    private String inspectOrgan;

    private String organId;

    private String inspectContent;

    private String resultType;

    private String questionType;

    private String inspectResut;

    private String upload;

    private String remark;

    private Date inspectTime1;

    private Double organFine;

    private Double punishNum;

    private Double personFine;

    private String improveCondition;

    private String noticeId;

    private String inspectUnit;

    private String checkPeriod;

    private String path;
    
	private Date startInsertTime;
    
    private Date endInsertTime;
    
    private Date startInspectTime;
    
    private Date endInspectTime;
    
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

	public Date getStartInspectTime() {
		return startInspectTime;
	}

	public void setStartInspectTime(Date startInspectTime) {
		this.startInspectTime = startInspectTime;
	}

	public Date getEndInspectTime() {
		return endInspectTime;
	}

	public void setEndInspectTime(Date endInspectTime) {
		this.endInspectTime = endInspectTime;
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

    public Date getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(Date inspectTime) {
        this.inspectTime = inspectTime;
    }

    public String getInspectOrgan() {
        return inspectOrgan;
    }

    public void setInspectOrgan(String inspectOrgan) {
        this.inspectOrgan = inspectOrgan == null ? null : inspectOrgan.trim();
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId == null ? null : organId.trim();
    }

    public String getInspectContent() {
        return inspectContent;
    }

    public void setInspectContent(String inspectContent) {
        this.inspectContent = inspectContent == null ? null : inspectContent.trim();
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType == null ? null : resultType.trim();
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    public String getInspectResut() {
        return inspectResut;
    }

    public void setInspectResut(String inspectResut) {
        this.inspectResut = inspectResut == null ? null : inspectResut.trim();
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

    public Date getInspectTime1() {
        return inspectTime1;
    }

    public void setInspectTime1(Date inspectTime1) {
        this.inspectTime1 = inspectTime1;
    }

    public Double getOrganFine() {
        return organFine;
    }

    public void setOrganFine(Double organFine) {
        this.organFine = organFine;
    }

    public Double getPunishNum() {
        return punishNum;
    }

    public void setPunishNum(Double punishNum) {
        this.punishNum = punishNum;
    }

    public Double getPersonFine() {
        return personFine;
    }

    public void setPersonFine(Double personFine) {
        this.personFine = personFine;
    }

    public String getImproveCondition() {
        return improveCondition;
    }

    public void setImproveCondition(String improveCondition) {
        this.improveCondition = improveCondition == null ? null : improveCondition.trim();
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId == null ? null : noticeId.trim();
    }

    public String getInspectUnit() {
        return inspectUnit;
    }

    public void setInspectUnit(String inspectUnit) {
        this.inspectUnit = inspectUnit == null ? null : inspectUnit.trim();
    }

    public String getCheckPeriod() {
        return checkPeriod;
    }

    public void setCheckPeriod(String checkPeriod) {
        this.checkPeriod = checkPeriod == null ? null : checkPeriod.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
    
}