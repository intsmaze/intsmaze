package org.intsmaze.persistence.pojos;

import java.sql.Timestamp;
import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class RiskScore extends BasePojo{
    private String seqno;

    private String customerId;

    private String customerName;
    
    private String customerType;
    
    private String isAntCustomer;

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
    
    private Date startScoreTime;
    
    private Date endScoreTime;

    private Date startFirstTime;
    
    private Date endFirstTime;
    
    private String isList;
    
    private String startGradeTime;
    
    private String endGradeTime;
    
    private String startNextReviewtime;
    
    private String endNextReviewtime;
    
    private String startPreviousGradetime;
    
    private String endPreviousGradetime;
    
    
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getIsAntCustomer() {
		return isAntCustomer;
	}

	public void setIsAntCustomer(String isAntCustomer) {
		this.isAntCustomer = isAntCustomer;
	}

	public String getStartPreviousGradetime() {
		return startPreviousGradetime;
	}

	public void setStartPreviousGradetime(String startPreviousGradetime) {
		this.startPreviousGradetime = startPreviousGradetime;
	}

	public String getEndPreviousGradetime() {
		return endPreviousGradetime;
	}

	public void setEndPreviousGradetime(String endPreviousGradetime) {
		this.endPreviousGradetime = endPreviousGradetime;
	}
    
	public String getStartNextReviewtime() {
		return startNextReviewtime;
	}

	public void setStartNextReviewtime(String startNextReviewtime) {
		this.startNextReviewtime = startNextReviewtime;
	}

	public String getEndNextReviewtime() {
		return endNextReviewtime;
	}

	public void setEndNextReviewtime(String endNextReviewtime) {
		this.endNextReviewtime = endNextReviewtime;
	}

	public String getStartGradeTime() {
		return startGradeTime;
	}

	public void setStartGradeTime(String startGradeTime) {
		this.startGradeTime = startGradeTime;
	}

	public String getEndGradeTime() {
		return endGradeTime;
	}

	public void setEndGradeTime(String endGradeTime) {
		this.endGradeTime = endGradeTime;
	}
    
	public String getIsList() {
		return isList;
	}

	public void setIsList(String isList) {
		this.isList = isList;
	}
    
    public Date getStartFirstTime() {
		return startFirstTime;
	}

	public void setStartFirstTime(Date startFirstTime) {
		this.startFirstTime = startFirstTime;
	}

	public Date getEndFirstTime() {
		return endFirstTime;
	}

	public void setEndFirstTime(Date endFirstTime) {
		this.endFirstTime = endFirstTime;
	}

    public Date getStartScoreTime() {
		return startScoreTime;
	}

	public void setStartScoreTime(Date startScoreTime) {
		this.startScoreTime = startScoreTime;
	}

	public Date getEndScoreTime() {
		return endScoreTime;
	}

	public void setEndScoreTime(Date endScoreTime) {
		this.endScoreTime = endScoreTime;
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