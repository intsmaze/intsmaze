package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class NatureInfo extends BasePojo{
    private String seqno;

    private String partyId;

    private Date gmtLastIssueTime;

    private Date gmtLastEffectEnd;

    private String customerName;

    private String gender;

    private String country;

    private String jobCode1;

    private String jobCode2;
    
    private String jobIsExpire;

    private String mailingCountry;

    private String mailingProvince;

    private String mailingCity;

    private String mailingRegion;

    private String mailingOther;

    private String mailingPostalCode;
    
    private String mailingIsExpire;

    private String contactPhone;

    private String mobilePhone;
    
    private String connectIsExpire;

    private String identifyType;

    private String identifyNumber;

    private Date certiStartDate;

    private Date certiEndDate;

    private String certiIsExpire;

    private String unit;

    private Date insertTime;
    
    private Date createon;
    
    private Date modifyon;
    
    private String startGmtLastIssueTime;
    
    private String endGmtLastIssueTime;
    
    public String getStartGmtLastIssueTime() {
		return startGmtLastIssueTime;
	}

	public void setStartGmtLastIssueTime(String startGmtLastIssueTime) {
		this.startGmtLastIssueTime = startGmtLastIssueTime;
	}

	public String getEndGmtLastIssueTime() {
		return endGmtLastIssueTime;
	}

	public void setEndGmtLastIssueTime(String endGmtLastIssueTime) {
		this.endGmtLastIssueTime = endGmtLastIssueTime;
	}
    public Date getCreateon() {
		return createon;
	}

	public void setCreateon(Date createon) {
		this.createon = createon;
	}

	public Date getModifyon() {
		return modifyon;
	}

	public void setModifyon(Date modifyon) {
		this.modifyon = modifyon;
	}

	public String getJobIsExpire() {
		return jobIsExpire;
	}

	public void setJobIsExpire(String jobIsExpire) {
		this.jobIsExpire = jobIsExpire;
	}

	public String getMailingIsExpire() {
		return mailingIsExpire;
	}

	public void setMailingIsExpire(String mailingIsExpire) {
		this.mailingIsExpire = mailingIsExpire;
	}

	public String getConnectIsExpire() {
		return connectIsExpire;
	}

	public void setConnectIsExpire(String connectIsExpire) {
		this.connectIsExpire = connectIsExpire;
	}

	public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId == null ? null : partyId.trim();
    }

    public Date getGmtLastIssueTime() {
        return gmtLastIssueTime;
    }

    public void setGmtLastIssueTime(Date gmtLastIssueTime) {
        this.gmtLastIssueTime = gmtLastIssueTime;
    }

    public Date getGmtLastEffectEnd() {
        return gmtLastEffectEnd;
    }

    public void setGmtLastEffectEnd(Date gmtLastEffectEnd) {
        this.gmtLastEffectEnd = gmtLastEffectEnd;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getJobCode1() {
        return jobCode1;
    }

    public void setJobCode1(String jobCode1) {
        this.jobCode1 = jobCode1 == null ? null : jobCode1.trim();
    }

    public String getJobCode2() {
        return jobCode2;
    }

    public void setJobCode2(String jobCode2) {
        this.jobCode2 = jobCode2 == null ? null : jobCode2.trim();
    }

    public String getMailingCountry() {
        return mailingCountry;
    }

    public void setMailingCountry(String mailingCountry) {
        this.mailingCountry = mailingCountry == null ? null : mailingCountry.trim();
    }

    public String getMailingProvince() {
        return mailingProvince;
    }

    public void setMailingProvince(String mailingProvince) {
        this.mailingProvince = mailingProvince == null ? null : mailingProvince.trim();
    }

    public String getMailingCity() {
        return mailingCity;
    }

    public void setMailingCity(String mailingCity) {
        this.mailingCity = mailingCity == null ? null : mailingCity.trim();
    }

    public String getMailingRegion() {
        return mailingRegion;
    }

    public void setMailingRegion(String mailingRegion) {
        this.mailingRegion = mailingRegion == null ? null : mailingRegion.trim();
    }

    public String getMailingOther() {
        return mailingOther;
    }

    public void setMailingOther(String mailingOther) {
        this.mailingOther = mailingOther == null ? null : mailingOther.trim();
    }

    public String getMailingPostalCode() {
        return mailingPostalCode;
    }

    public void setMailingPostalCode(String mailingPostalCode) {
        this.mailingPostalCode = mailingPostalCode == null ? null : mailingPostalCode.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
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

    public Date getCertiStartDate() {
        return certiStartDate;
    }

    public void setCertiStartDate(Date certiStartDate) {
        this.certiStartDate = certiStartDate;
    }

    public Date getCertiEndDate() {
        return certiEndDate;
    }

    public void setCertiEndDate(Date certiEndDate) {
        this.certiEndDate = certiEndDate;
    }

    public String getCertiIsExpire() {
        return certiIsExpire;
    }

    public void setCertiIsExpire(String certiIsExpire) {
        this.certiIsExpire = certiIsExpire == null ? null : certiIsExpire.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}