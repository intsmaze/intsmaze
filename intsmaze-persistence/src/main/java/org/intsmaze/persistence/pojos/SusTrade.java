package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class SusTrade extends BasePojo {
    private String seqno;

    private String transSpot;

    private String stcr;

    private String sdgr;

    private String tkms;

    private String ssds;

    private String insertOrganId;

    private String insertOper;

    private Date insertTime;

    private String status;

    private Date reportTime;

    private String reportId;

    private String sscat;

    private String conditionCode;

    private String explication;

    private String firstId;

    private Date firstEndTime;

    private String secondId;

    private Date secondEndTime;

    private String finalId;

    private Date finalEndTime;

    private String secondInspectStatus;

    private String approveFinishId;

    private Date approveFinishTime;

    private String policyType;

    private String ctid;

    private String crnm;

    private String crit;

    private String crid;

    private String approveId;

    private Date approveTime;

    private String agreeSuggestion;

    private String cTransMrk;

    private Date tTransTm;

    private String firstInspectOperate;

    private String secondInspectOperate;

    private String approveOperate;

    private String review;

    private Date enterAuditTime;

    private String urgencyDegree;

    private String submissionDirection;

    private String involvedCrimeType;

    private String showStatus;

    private Date updateTime;

    private String modIdentifying;
    
    private Integer totalAmount;
    
    private Integer unauditCount1;
    
    private Integer overdueAuditCount1;
    
    private Integer rejectCount1;
    
	private double rejectRate1;
	
    private Integer unauditCount2;
    
    private Integer overdueAuditCount2;
    
    private Integer rejectCount2;
    
	private double rejectRate2;
	
	private String customerName;
	
	private Date startSusTradeDate;
    
	private Date endSusTradeDate;
	
	public Date getStartSusTradeDate() {
		return startSusTradeDate;
	}

	public void setStartSusTradeDate(Date startSusTradeDate) {
		this.startSusTradeDate = startSusTradeDate;
	}

	public Date getEndSusTradeDate() {
		return endSusTradeDate;
	}

	public void setEndSusTradeDate(Date endSusTradeDate) {
		this.endSusTradeDate = endSusTradeDate;
	}

    public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getUnauditCount1() {
		return unauditCount1;
	}

	public void setUnauditCount1(Integer unauditCount1) {
		this.unauditCount1 = unauditCount1;
	}

	public Integer getOverdueAuditCount1() {
		return overdueAuditCount1;
	}

	public void setOverdueAuditCount1(Integer overdueAuditCount1) {
		this.overdueAuditCount1 = overdueAuditCount1;
	}

	public Integer getRejectCount1() {
		return rejectCount1;
	}

	public void setRejectCount1(Integer rejectCount1) {
		this.rejectCount1 = rejectCount1;
	}

	public double getRejectRate1() {
		return rejectRate1;
	}

	public void setRejectRate1(double rejectRate1) {
		this.rejectRate1 = rejectRate1;
	}

	public Integer getUnauditCount2() {
		return unauditCount2;
	}

	public void setUnauditCount2(Integer unauditCount2) {
		this.unauditCount2 = unauditCount2;
	}

	public Integer getOverdueAuditCount2() {
		return overdueAuditCount2;
	}

	public void setOverdueAuditCount2(Integer overdueAuditCount2) {
		this.overdueAuditCount2 = overdueAuditCount2;
	}

	public Integer getRejectCount2() {
		return rejectCount2;
	}

	public void setRejectCount2(Integer rejectCount2) {
		this.rejectCount2 = rejectCount2;
	}

	public double getRejectRate2() {
		return rejectRate2;
	}

	public void setRejectRate2(double rejectRate2) {
		this.rejectRate2 = rejectRate2;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getTransSpot() {
        return transSpot;
    }

    public void setTransSpot(String transSpot) {
        this.transSpot = transSpot == null ? null : transSpot.trim();
    }

    public String getStcr() {
        return stcr;
    }

    public void setStcr(String stcr) {
        this.stcr = stcr == null ? null : stcr.trim();
    }

    public String getSdgr() {
        return sdgr;
    }

    public void setSdgr(String sdgr) {
        this.sdgr = sdgr == null ? null : sdgr.trim();
    }

    public String getTkms() {
        return tkms;
    }

    public void setTkms(String tkms) {
        this.tkms = tkms == null ? null : tkms.trim();
    }

    public String getSsds() {
        return ssds;
    }

    public void setSsds(String ssds) {
        this.ssds = ssds == null ? null : ssds.trim();
    }

    public String getInsertOrganId() {
        return insertOrganId;
    }

    public void setInsertOrganId(String insertOrganId) {
        this.insertOrganId = insertOrganId == null ? null : insertOrganId.trim();
    }

    public String getInsertOper() {
        return insertOper;
    }

    public void setInsertOper(String insertOper) {
        this.insertOper = insertOper == null ? null : insertOper.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    public String getSscat() {
        return sscat;
    }

    public void setSscat(String sscat) {
        this.sscat = sscat == null ? null : sscat.trim();
    }

    public String getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode == null ? null : conditionCode.trim();
    }

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication == null ? null : explication.trim();
    }

    public String getFirstId() {
        return firstId;
    }

    public void setFirstId(String firstId) {
        this.firstId = firstId == null ? null : firstId.trim();
    }

    public Date getFirstEndTime() {
        return firstEndTime;
    }

    public void setFirstEndTime(Date firstEndTime) {
        this.firstEndTime = firstEndTime;
    }

    public String getSecondId() {
        return secondId;
    }

    public void setSecondId(String secondId) {
        this.secondId = secondId == null ? null : secondId.trim();
    }

    public Date getSecondEndTime() {
        return secondEndTime;
    }

    public void setSecondEndTime(Date secondEndTime) {
        this.secondEndTime = secondEndTime;
    }

    public String getFinalId() {
        return finalId;
    }

    public void setFinalId(String finalId) {
        this.finalId = finalId == null ? null : finalId.trim();
    }

    public Date getFinalEndTime() {
        return finalEndTime;
    }

    public void setFinalEndTime(Date finalEndTime) {
        this.finalEndTime = finalEndTime;
    }

    public String getSecondInspectStatus() {
        return secondInspectStatus;
    }

    public void setSecondInspectStatus(String secondInspectStatus) {
        this.secondInspectStatus = secondInspectStatus == null ? null : secondInspectStatus.trim();
    }

    public String getApproveFinishId() {
        return approveFinishId;
    }

    public void setApproveFinishId(String approveFinishId) {
        this.approveFinishId = approveFinishId == null ? null : approveFinishId.trim();
    }

    public Date getApproveFinishTime() {
        return approveFinishTime;
    }

    public void setApproveFinishTime(Date approveFinishTime) {
        this.approveFinishTime = approveFinishTime;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType == null ? null : policyType.trim();
    }

    public String getCtid() {
        return ctid;
    }

    public void setCtid(String ctid) {
        this.ctid = ctid == null ? null : ctid.trim();
    }

    public String getCrnm() {
        return crnm;
    }

    public void setCrnm(String crnm) {
        this.crnm = crnm == null ? null : crnm.trim();
    }

    public String getCrit() {
        return crit;
    }

    public void setCrit(String crit) {
        this.crit = crit == null ? null : crit.trim();
    }

    public String getCrid() {
        return crid;
    }

    public void setCrid(String crid) {
        this.crid = crid == null ? null : crid.trim();
    }

    public String getApproveId() {
        return approveId;
    }

    public void setApproveId(String approveId) {
        this.approveId = approveId == null ? null : approveId.trim();
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getAgreeSuggestion() {
        return agreeSuggestion;
    }

    public void setAgreeSuggestion(String agreeSuggestion) {
        this.agreeSuggestion = agreeSuggestion == null ? null : agreeSuggestion.trim();
    }

    public String getcTransMrk() {
        return cTransMrk;
    }

    public void setcTransMrk(String cTransMrk) {
        this.cTransMrk = cTransMrk == null ? null : cTransMrk.trim();
    }

    public Date gettTransTm() {
        return tTransTm;
    }

    public void settTransTm(Date tTransTm) {
        this.tTransTm = tTransTm;
    }

    public String getFirstInspectOperate() {
        return firstInspectOperate;
    }

    public void setFirstInspectOperate(String firstInspectOperate) {
        this.firstInspectOperate = firstInspectOperate == null ? null : firstInspectOperate.trim();
    }

    public String getSecondInspectOperate() {
        return secondInspectOperate;
    }

    public void setSecondInspectOperate(String secondInspectOperate) {
        this.secondInspectOperate = secondInspectOperate == null ? null : secondInspectOperate.trim();
    }

    public String getApproveOperate() {
        return approveOperate;
    }

    public void setApproveOperate(String approveOperate) {
        this.approveOperate = approveOperate == null ? null : approveOperate.trim();
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review == null ? null : review.trim();
    }

    public Date getEnterAuditTime() {
        return enterAuditTime;
    }

    public void setEnterAuditTime(Date enterAuditTime) {
        this.enterAuditTime = enterAuditTime;
    }

    public String getUrgencyDegree() {
        return urgencyDegree;
    }

    public void setUrgencyDegree(String urgencyDegree) {
        this.urgencyDegree = urgencyDegree == null ? null : urgencyDegree.trim();
    }

    public String getSubmissionDirection() {
        return submissionDirection;
    }

    public void setSubmissionDirection(String submissionDirection) {
        this.submissionDirection = submissionDirection == null ? null : submissionDirection.trim();
    }

    public String getInvolvedCrimeType() {
        return involvedCrimeType;
    }

    public void setInvolvedCrimeType(String involvedCrimeType) {
        this.involvedCrimeType = involvedCrimeType == null ? null : involvedCrimeType.trim();
    }

    public String getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus == null ? null : showStatus.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getModIdentifying() {
        return modIdentifying;
    }

    public void setModIdentifying(String modIdentifying) {
        this.modIdentifying = modIdentifying == null ? null : modIdentifying.trim();
    }
}