package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;


public class Cordoned extends BasePojo {
    private String seqno;

    private Date insertTime;

    private Date cordonedTime;

    private String outorgan;

    private String organId;

    private String prono;

    private String customType;

    private String custom;

    private String parpersNo;

    private String content;

    private String returnResult;

    private String cordonedFile;

    private String remark;

    private Double caseMoney;

    private String workPosition;

    private String cordonedFileno;

    private String presentGrade;

    private String ontimeSend;

    private String issendBig;

    private String issendSus;

    private String checkReason;

    private String papersType;

    private String payTime;

    private String payWay;

    private String productType;

    private String isIdentify;

    private String isFreeze;

    private String caseStatus;

    private String policyStatus;

    private String path;
	
	private Date startInsertTime;
    
    private Date endInsertTime;
    
    private Date startCordonedTime;
    
    private Date endCordonedTime;
    
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

	public Date getStartCordonedTime() {
		return startCordonedTime;
	}

	public void setStartCordonedTime(Date startCordonedTime) {
		this.startCordonedTime = startCordonedTime;
	}

	public Date getEndCordonedTime() {
		return endCordonedTime;
	}

	public void setEndCordonedTime(Date endCordonedTime) {
		this.endCordonedTime = endCordonedTime;
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

    public Date getCordonedTime() {
        return cordonedTime;
    }

    public void setCordonedTime(Date cordonedTime) {
        this.cordonedTime = cordonedTime;
    }

    public String getOutorgan() {
        return outorgan;
    }

    public void setOutorgan(String outorgan) {
        this.outorgan = outorgan == null ? null : outorgan.trim();
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId == null ? null : organId.trim();
    }

    public String getProno() {
        return prono;
    }

    public void setProno(String prono) {
        this.prono = prono == null ? null : prono.trim();
    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType == null ? null : customType.trim();
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom == null ? null : custom.trim();
    }

    public String getParpersNo() {
        return parpersNo;
    }

    public void setParpersNo(String parpersNo) {
        this.parpersNo = parpersNo == null ? null : parpersNo.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult == null ? null : returnResult.trim();
    }

    public String getCordonedFile() {
        return cordonedFile;
    }

    public void setCordonedFile(String cordonedFile) {
        this.cordonedFile = cordonedFile == null ? null : cordonedFile.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Double getCaseMoney() {
        return caseMoney;
    }

    public void setCaseMoney(Double caseMoney) {
        this.caseMoney = caseMoney;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition == null ? null : workPosition.trim();
    }

    public String getCordonedFileno() {
        return cordonedFileno;
    }

    public void setCordonedFileno(String cordonedFileno) {
        this.cordonedFileno = cordonedFileno == null ? null : cordonedFileno.trim();
    }

    public String getPresentGrade() {
        return presentGrade;
    }

    public void setPresentGrade(String presentGrade) {
        this.presentGrade = presentGrade == null ? null : presentGrade.trim();
    }

    public String getOntimeSend() {
        return ontimeSend;
    }

    public void setOntimeSend(String ontimeSend) {
        this.ontimeSend = ontimeSend == null ? null : ontimeSend.trim();
    }

    public String getIssendBig() {
        return issendBig;
    }

    public void setIssendBig(String issendBig) {
        this.issendBig = issendBig == null ? null : issendBig.trim();
    }

    public String getIssendSus() {
        return issendSus;
    }

    public void setIssendSus(String issendSus) {
        this.issendSus = issendSus == null ? null : issendSus.trim();
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason == null ? null : checkReason.trim();
    }

    public String getPapersType() {
        return papersType;
    }

    public void setPapersType(String papersType) {
        this.papersType = papersType == null ? null : papersType.trim();
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime == null ? null : payTime.trim();
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getIsIdentify() {
        return isIdentify;
    }

    public void setIsIdentify(String isIdentify) {
        this.isIdentify = isIdentify == null ? null : isIdentify.trim();
    }

    public String getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(String isFreeze) {
        this.isFreeze = isFreeze == null ? null : isFreeze.trim();
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus == null ? null : caseStatus.trim();
    }

    public String getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus == null ? null : policyStatus.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}