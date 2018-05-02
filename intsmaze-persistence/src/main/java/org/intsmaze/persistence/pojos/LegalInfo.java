package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class LegalInfo extends BasePojo{
    private String seqno;

    private String partyId;

    private Date gmtLastIssueTime;

    private Date gmtLastEffectEnd;

    private String customerName;

    private String mailingCountry;

    private String mailingProvince;

    private String mailingCity;

    private String mailingRegion;

    private String mailingOther;

    private String mailingPostalCode;

    private String mailingIsExpire;

    private String businessScope;

    private String identifyType;

    private String identifyNumber;

    private Date validStartDate;

    private Date validEndDate;

    private String validIsExpire;

    private String controllerName;

    private String contCertificateType;

    private String contCertificateNum;

    private Date contCertificateStartTime;

    private Date contCertificateEndTime;

    private String contCertificateIsExpire;

    private String legalpersonName;

    private String legalCertificateType;

    private String legalCertificateNum;

    private Date legalCertificateStartTime;

    private Date legalCertificateEndTime;

    private String legalCertificateIsExpire;

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

    public String getMailingIsExpire() {
        return mailingIsExpire;
    }

    public void setMailingIsExpire(String mailingIsExpire) {
        this.mailingIsExpire = mailingIsExpire == null ? null : mailingIsExpire.trim();
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
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

    public Date getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(Date validStartDate) {
        this.validStartDate = validStartDate;
    }

    public Date getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(Date validEndDate) {
        this.validEndDate = validEndDate;
    }

    public String getValidIsExpire() {
        return validIsExpire;
    }

    public void setValidIsExpire(String validIsExpire) {
        this.validIsExpire = validIsExpire == null ? null : validIsExpire.trim();
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName == null ? null : controllerName.trim();
    }

    public String getContCertificateType() {
        return contCertificateType;
    }

    public void setContCertificateType(String contCertificateType) {
        this.contCertificateType = contCertificateType == null ? null : contCertificateType.trim();
    }

    public String getContCertificateNum() {
        return contCertificateNum;
    }

    public void setContCertificateNum(String contCertificateNum) {
        this.contCertificateNum = contCertificateNum == null ? null : contCertificateNum.trim();
    }

    public Date getContCertificateStartTime() {
        return contCertificateStartTime;
    }

    public void setContCertificateStartTime(Date contCertificateStartTime) {
        this.contCertificateStartTime = contCertificateStartTime;
    }

    public Date getContCertificateEndTime() {
        return contCertificateEndTime;
    }

    public void setContCertificateEndTime(Date contCertificateEndTime) {
        this.contCertificateEndTime = contCertificateEndTime;
    }

    public String getContCertificateIsExpire() {
        return contCertificateIsExpire;
    }

    public void setContCertificateIsExpire(String contCertificateIsExpire) {
        this.contCertificateIsExpire = contCertificateIsExpire == null ? null : contCertificateIsExpire.trim();
    }

    public String getLegalpersonName() {
        return legalpersonName;
    }

    public void setLegalpersonName(String legalpersonName) {
        this.legalpersonName = legalpersonName == null ? null : legalpersonName.trim();
    }

    public String getLegalCertificateType() {
        return legalCertificateType;
    }

    public void setLegalCertificateType(String legalCertificateType) {
        this.legalCertificateType = legalCertificateType == null ? null : legalCertificateType.trim();
    }

    public String getLegalCertificateNum() {
        return legalCertificateNum;
    }

    public void setLegalCertificateNum(String legalCertificateNum) {
        this.legalCertificateNum = legalCertificateNum == null ? null : legalCertificateNum.trim();
    }

    public Date getLegalCertificateStartTime() {
        return legalCertificateStartTime;
    }

    public void setLegalCertificateStartTime(Date legalCertificateStartTime) {
        this.legalCertificateStartTime = legalCertificateStartTime;
    }

    public Date getLegalCertificateEndTime() {
        return legalCertificateEndTime;
    }

    public void setLegalCertificateEndTime(Date legalCertificateEndTime) {
        this.legalCertificateEndTime = legalCertificateEndTime;
    }

    public String getLegalCertificateIsExpire() {
        return legalCertificateIsExpire;
    }

    public void setLegalCertificateIsExpire(String legalCertificateIsExpire) {
        this.legalCertificateIsExpire = legalCertificateIsExpire == null ? null : legalCertificateIsExpire.trim();
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