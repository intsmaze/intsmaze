package org.intsmaze.persistence.pojos;

import java.sql.Timestamp;
import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class RiskGrade extends BasePojo{
    private String seqno;

    private String customerId;

    private String customerName;

    private String identifyType;

    private String identifyNumber;

    private String orgCode;

    private String previousGradetype;

    private String gradeType;

    private String previousScore;

    private Date scoreTime;

    private String previousGrade;

    private String presentGrade;

    private Date gradeTime;

    private String suggestion;

    private String status;

    private String returnSuggestion;

    private String cancleSuggestion;

    private Date nextReviewtime;

    private Date previousGradetime;

    private String modGrade;

    private Date firstTime;

    private String review;

    private String fromuser;

    private String createby;

    private Date createon;

    private String modifyby;

    private Date modifyon;
    
    private Integer totalAmount;
    
    private String unauditedCustomers;
    
    private String overdueAuditCustomers;
    
    private String extendedAuditRate;
    
	private String orgName;
	
	private String startEvaluationDate;
	
	private String endEvaluationDate;

	public String getStartEvaluationDate() {
		return startEvaluationDate;
	}

	public void setStartEvaluationDate(String startEvaluationDate) {
		this.startEvaluationDate = startEvaluationDate;
	}

	public String getEndEvaluationDate() {
		return endEvaluationDate;
	}

	public void setEndEvaluationDate(String endEvaluationDate) {
		this.endEvaluationDate = endEvaluationDate;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUnauditedCustomers() {
		return unauditedCustomers;
	}

	public void setUnauditedCustomers(String unauditedCustomers) {
		this.unauditedCustomers = unauditedCustomers;
	}

	public String getOverdueAuditCustomers() {
		return overdueAuditCustomers;
	}

	public void setOverdueAuditCustomers(String overdueAuditCustomers) {
		this.overdueAuditCustomers = overdueAuditCustomers;
	}

	public String getExtendedAuditRate() {
		return extendedAuditRate;
	}

	public void setExtendedAuditRate(String extendedAuditRate) {
		this.extendedAuditRate = extendedAuditRate;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(String identifyType) {
        this.identifyType = identifyType == null ? null : identifyType.trim();
    }

    public String getIdentifyNumber() {
        return identifyNumber;
    }

    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber == null ? null : identifyNumber.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getPreviousGradetype() {
        return previousGradetype;
    }

    public void setPreviousGradetype(String previousGradetype) {
        this.previousGradetype = previousGradetype == null ? null : previousGradetype.trim();
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType == null ? null : gradeType.trim();
    }

    public String getPreviousScore() {
        return previousScore;
    }

    public void setPreviousScore(String previousScore) {
        this.previousScore = previousScore == null ? null : previousScore.trim();
    }

    public Date getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(Date scoreTime) {
        this.scoreTime = scoreTime;
    }

    public String getPreviousGrade() {
        return previousGrade;
    }

    public void setPreviousGrade(String previousGrade) {
        this.previousGrade = previousGrade == null ? null : previousGrade.trim();
    }

    public String getPresentGrade() {
        return presentGrade;
    }

    public void setPresentGrade(String presentGrade) {
        this.presentGrade = presentGrade == null ? null : presentGrade.trim();
    }

    public Date getGradeTime() {
        return gradeTime;
    }

    public void setGradeTime(Date gradeTime) {
        this.gradeTime = gradeTime;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion == null ? null : suggestion.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getReturnSuggestion() {
        return returnSuggestion;
    }

    public void setReturnSuggestion(String returnSuggestion) {
        this.returnSuggestion = returnSuggestion == null ? null : returnSuggestion.trim();
    }

    public String getCancleSuggestion() {
        return cancleSuggestion;
    }

    public void setCancleSuggestion(String cancleSuggestion) {
        this.cancleSuggestion = cancleSuggestion == null ? null : cancleSuggestion.trim();
    }

    public Date getNextReviewtime() {
        return nextReviewtime;
    }

    public void setNextReviewtime(Date nextReviewtime) {
        this.nextReviewtime = nextReviewtime;
    }

    public Date getPreviousGradetime() {
        return previousGradetime;
    }

    public void setPreviousGradetime(Date previousGradetime) {
        this.previousGradetime = previousGradetime;
    }

    public String getModGrade() {
        return modGrade;
    }

    public void setModGrade(String modGrade) {
        this.modGrade = modGrade == null ? null : modGrade.trim();
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review == null ? null : review.trim();
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser == null ? null : fromuser.trim();
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreateon() {
        return createon;
    }

    public void setCreateon(Date createon) {
        this.createon = createon;
    }

    public String getModifyby() {
        return modifyby;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby == null ? null : modifyby.trim();
    }

    public Date getModifyon() {
        return modifyon;
    }

    public void setModifyon(Date modifyon) {
        this.modifyon = modifyon;
    }
}