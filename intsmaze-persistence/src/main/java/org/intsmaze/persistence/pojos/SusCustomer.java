package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class SusCustomer extends BasePojo{
	
	private static final long serialVersionUID = 6132580437580185810L;

    private String seqno;

    private String tradeFeature;

    private String applicantType;

    private String address;

    private String vocation;

    private String customerId;

    private String customerName;

    private String customerCertiType;

    private String customerCertiCode;

    private String customerOtherCertiType;

    private String countryId;

    private String customerTelNum;

    private String contactInfo;

    private String crmn;       //法人信息

    private String crit;

    private String crot;

    private String crid;

    private String shareholderName;  //股东信息

    private String shareholderCertiType;

    private String shareholderCertiCode;

    private String shareholderOtherCertiType;

    private String caoi;    //开户行

    private String tcan;   //资金账户

    private String customerType;

    private String insertTime;

    private String susHistory;

    private String workplace;

    private String reviewStatus;

    private String customerSsds;

    private String crimeType;

    private String sdgr;   //可疑程度

    private String urgencyDegree; //紧急程度

    private String triggerPoint;  //触发点

    private String otherTriggerPoint;

    private Integer reportTimes;

    private String submissionDirection;

    private String doubtAnalysis;

    private String firestReportedName;

    private String tkms;

    private String modIdentifying;

    private String firstName;

    private String firstInspectOperate;

    private String firstEndTime;

    private String secondName;

    private String secondInspectOperate;

    private String secondEndTime;

    private String finalName;

    private String finalEndTime;
    
    private String reportTime;
    
    private String jobNameDetails;
    
    public String getJobNameDetails() {
        return jobNameDetails;
    }

    public void setJobNameDetails(String jobNameDetails) {
        this.jobNameDetails = jobNameDetails == null ? null : jobNameDetails.trim();
    }
    
    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime == null ? null : reportTime.trim();
    }
    
    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getTradeFeature() {
        return tradeFeature;
    }

    public void setTradeFeature(String tradeFeature) {
        this.tradeFeature = tradeFeature == null ? null : tradeFeature.trim();
    }

    public String getApplicantType() {
        return applicantType;
    }

    public void setApplicantType(String applicantType) {
        this.applicantType = applicantType == null ? null : applicantType.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation == null ? null : vocation.trim();
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

    public String getCustomerCertiType() {
        return customerCertiType;
    }

    public void setCustomerCertiType(String customerCertiType) {
        this.customerCertiType = customerCertiType == null ? null : customerCertiType.trim();
    }

    public String getCustomerCertiCode() {
        return customerCertiCode;
    }

    public void setCustomerCertiCode(String customerCertiCode) {
        this.customerCertiCode = customerCertiCode == null ? null : customerCertiCode.trim();
    }

    public String getCustomerOtherCertiType() {
        return customerOtherCertiType;
    }

    public void setCustomerOtherCertiType(String customerOtherCertiType) {
        this.customerOtherCertiType = customerOtherCertiType == null ? null : customerOtherCertiType.trim();
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId == null ? null : countryId.trim();
    }

    public String getCustomerTelNum() {
        return customerTelNum;
    }

    public void setCustomerTelNum(String customerTelNum) {
        this.customerTelNum = customerTelNum == null ? null : customerTelNum.trim();
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo == null ? null : contactInfo.trim();
    }

    public String getCrmn() {
        return crmn;
    }

    public void setCrmn(String crmn) {
        this.crmn = crmn == null ? null : crmn.trim();
    }

    public String getCrit() {
        return crit;
    }

    public void setCrit(String crit) {
        this.crit = crit == null ? null : crit.trim();
    }

    public String getCrot() {
        return crot;
    }

    public void setCrot(String crot) {
        this.crot = crot == null ? null : crot.trim();
    }

    public String getCrid() {
        return crid;
    }

    public void setCrid(String crid) {
        this.crid = crid == null ? null : crid.trim();
    }

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName == null ? null : shareholderName.trim();
    }

    public String getShareholderCertiType() {
        return shareholderCertiType;
    }

    public void setShareholderCertiType(String shareholderCertiType) {
        this.shareholderCertiType = shareholderCertiType == null ? null : shareholderCertiType.trim();
    }

    public String getShareholderCertiCode() {
        return shareholderCertiCode;
    }

    public void setShareholderCertiCode(String shareholderCertiCode) {
        this.shareholderCertiCode = shareholderCertiCode == null ? null : shareholderCertiCode.trim();
    }

    public String getShareholderOtherCertiType() {
        return shareholderOtherCertiType;
    }

    public void setShareholderOtherCertiType(String shareholderOtherCertiType) {
        this.shareholderOtherCertiType = shareholderOtherCertiType == null ? null : shareholderOtherCertiType.trim();
    }

    public String getCaoi() {
        return caoi;
    }

    public void setCaoi(String caoi) {
        this.caoi = caoi == null ? null : caoi.trim();
    }

    public String getTcan() {
        return tcan;
    }

    public void setTcan(String tcan) {
        this.tcan = tcan == null ? null : tcan.trim();
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime == null ? null : insertTime.trim();
    }

    public String getSusHistory() {
        return susHistory;
    }

    public void setSusHistory(String susHistory) {
        this.susHistory = susHistory == null ? null : susHistory.trim();
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace == null ? null : workplace.trim();
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus == null ? null : reviewStatus.trim();
    }

    public String getCustomerSsds() {
        return customerSsds;
    }

    public void setCustomerSsds(String customerSsds) {
        this.customerSsds = customerSsds == null ? null : customerSsds.trim();
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType == null ? null : crimeType.trim();
    }

    public String getSdgr() {
        return sdgr;
    }

    public void setSdgr(String sdgr) {
        this.sdgr = sdgr == null ? null : sdgr.trim();
    }

    public String getUrgencyDegree() {
        return urgencyDegree;
    }

    public void setUrgencyDegree(String urgencyDegree) {
        this.urgencyDegree = urgencyDegree == null ? null : urgencyDegree.trim();
    }

    public String getTriggerPoint() {
        return triggerPoint;
    }

    public void setTriggerPoint(String triggerPoint) {
        this.triggerPoint = triggerPoint == null ? null : triggerPoint.trim();
    }

    public String getOtherTriggerPoint() {
        return otherTriggerPoint;
    }

    public void setOtherTriggerPoint(String otherTriggerPoint) {
        this.otherTriggerPoint = otherTriggerPoint == null ? null : otherTriggerPoint.trim();
    }

    public Integer getReportTimes() {
        return reportTimes;
    }

    public void setReportTimes(Integer reportTimes) {
        this.reportTimes = reportTimes;
    }

    public String getSubmissionDirection() {
        return submissionDirection;
    }

    public void setSubmissionDirection(String submissionDirection) {
        this.submissionDirection = submissionDirection == null ? null : submissionDirection.trim();
    }

    public String getDoubtAnalysis() {
        return doubtAnalysis;
    }

    public void setDoubtAnalysis(String doubtAnalysis) {
        this.doubtAnalysis = doubtAnalysis == null ? null : doubtAnalysis.trim();
    }

    public String getFirestReportedName() {
        return firestReportedName;
    }

    public void setFirestReportedName(String firestReportedName) {
        this.firestReportedName = firestReportedName == null ? null : firestReportedName.trim();
    }

    public String getTkms() {
        return tkms;
    }

    public void setTkms(String tkms) {
        this.tkms = tkms == null ? null : tkms.trim();
    }

    public String getModIdentifying() {
        return modIdentifying;
    }

    public void setModIdentifying(String modIdentifying) {
        this.modIdentifying = modIdentifying == null ? null : modIdentifying.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getFirstInspectOperate() {
        return firstInspectOperate;
    }

    public void setFirstInspectOperate(String firstInspectOperate) {
        this.firstInspectOperate = firstInspectOperate == null ? null : firstInspectOperate.trim();
    }

    public String getFirstEndTime() {
        return firstEndTime;
    }

    public void setFirstEndTime(String firstEndTime) {
        this.firstEndTime = firstEndTime == null ? null : firstEndTime.trim();
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName == null ? null : secondName.trim();
    }

    public String getSecondInspectOperate() {
        return secondInspectOperate;
    }

    public void setSecondInspectOperate(String secondInspectOperate) {
        this.secondInspectOperate = secondInspectOperate == null ? null : secondInspectOperate.trim();
    }

    public String getSecondEndTime() {
        return secondEndTime;
    }

    public void setSecondEndTime(String secondEndTime) {
        this.secondEndTime = secondEndTime == null ? null : secondEndTime.trim();
    }

    public String getFinalName() {
        return finalName;
    }

    public void setFinalName(String finalName) {
        this.finalName = finalName == null ? null : finalName.trim();
    }

    public String getFinalEndTime() {
        return finalEndTime;
    }

    public void setFinalEndTime(String finalEndTime) {
        this.finalEndTime = finalEndTime == null ? null : finalEndTime.trim();
    }
}