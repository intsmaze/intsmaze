package org.intsmaze.persistence.pojos;

import org.intsmaze.persistence.common.BasePojo;

public class SusTransInfo extends BasePojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1445653159410427021L;

	private String seqno;   

    private String suDataId;
    
    private String address;

    private String tradeFeature;

    private String istp;

    private String prodName;

    private String isog;

    private String partyId;

    private String organCode;

    private String customerName;

    private String customerCertiType;

    private String customerOtherCertiType;

    private String customerCertiCode;

    private String policyNo;

    private String policyEffectTime;

    private String isat;

    private String isfe;

    private String ispt;

    private String ctes;

    private String insuredName;

    private String insuredCertiType;

    private String insuredCertiOtherType;

    private String insuredCertiCode;

    private String relationship;

    private String benifitName;

    private String benifitCertiCode;

    private String benifitOtherCertiType;

    private String benifitCertiType;

    private String organId;

    private String crdr;

    private String cstp;

    private String remarkOne;

    private String remarkTwo;

    private String addtime;

    private String curKind;

    private String tradeTime;

    private String curPrem;     

    private String prodTradeType;   //<ITTP>保险交易类型</ITTP>
    
    private String unit;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getSuDataId() {
        return suDataId;
    }

    public void setSuDataId(String suDataId) {
        this.suDataId = suDataId == null ? null : suDataId.trim();
    }

    public String getTradeFeature() {
        return tradeFeature;
    }

    public void setTradeFeature(String tradeFeature) {
        this.tradeFeature = tradeFeature == null ? null : tradeFeature.trim();
    }

    public String getIstp() {
        return istp;
    }

    public void setIstp(String istp) {
        this.istp = istp == null ? null : istp.trim();
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public String getIsog() {
        return isog;
    }

    public void setIsog(String isog) {
        this.isog = isog == null ? null : isog.trim();
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId == null ? null : partyId.trim();
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode == null ? null : organCode.trim();
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

    public String getCustomerOtherCertiType() {
        return customerOtherCertiType;
    }

    public void setCustomerOtherCertiType(String customerOtherCertiType) {
        this.customerOtherCertiType = customerOtherCertiType == null ? null : customerOtherCertiType.trim();
    }

    public String getCustomerCertiCode() {
        return customerCertiCode;
    }

    public void setCustomerCertiCode(String customerCertiCode) {
        this.customerCertiCode = customerCertiCode == null ? null : customerCertiCode.trim();
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo == null ? null : policyNo.trim();
    }

    public String getPolicyEffectTime() {
        return policyEffectTime;
    }

    public void setPolicyEffectTime(String policyEffectTime) {
        this.policyEffectTime = policyEffectTime == null ? null : policyEffectTime.trim();
    }


    public String getIspt() {
        return ispt;
    }

    public void setIspt(String ispt) {
        this.ispt = ispt == null ? null : ispt.trim();
    }

    public String getCtes() {
        return ctes;
    }

    public void setCtes(String ctes) {
        this.ctes = ctes == null ? null : ctes.trim();
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName == null ? null : insuredName.trim();
    }

    public String getInsuredCertiType() {
        return insuredCertiType;
    }

    public void setInsuredCertiType(String insuredCertiType) {
        this.insuredCertiType = insuredCertiType == null ? null : insuredCertiType.trim();
    }

    public String getInsuredCertiOtherType() {
        return insuredCertiOtherType;
    }

    public void setInsuredCertiOtherType(String insuredCertiOtherType) {
        this.insuredCertiOtherType = insuredCertiOtherType == null ? null : insuredCertiOtherType.trim();
    }

    public String getInsuredCertiCode() {
        return insuredCertiCode;
    }

    public void setInsuredCertiCode(String insuredCertiCode) {
        this.insuredCertiCode = insuredCertiCode == null ? null : insuredCertiCode.trim();
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship == null ? null : relationship.trim();
    }

    public String getBenifitName() {
        return benifitName;
    }

    public void setBenifitName(String benifitName) {
        this.benifitName = benifitName == null ? null : benifitName.trim();
    }

    public String getBenifitCertiCode() {
        return benifitCertiCode;
    }

    public void setBenifitCertiCode(String benifitCertiCode) {
        this.benifitCertiCode = benifitCertiCode == null ? null : benifitCertiCode.trim();
    }

    public String getBenifitOtherCertiType() {
        return benifitOtherCertiType;
    }

    public void setBenifitOtherCertiType(String benifitOtherCertiType) {
        this.benifitOtherCertiType = benifitOtherCertiType == null ? null : benifitOtherCertiType.trim();
    }

    public String getBenifitCertiType() {
        return benifitCertiType;
    }

    public void setBenifitCertiType(String benifitCertiType) {
        this.benifitCertiType = benifitCertiType == null ? null : benifitCertiType.trim();
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId == null ? null : organId.trim();
    }

    public String getCrdr() {
        return crdr;
    }

    public void setCrdr(String crdr) {
        this.crdr = crdr == null ? null : crdr.trim();
    }

    public String getCstp() {
        return cstp;
    }

    public void setCstp(String cstp) {
        this.cstp = cstp == null ? null : cstp.trim();
    }

    public String getRemarkOne() {
        return remarkOne;
    }

    public void setRemarkOne(String remarkOne) {
        this.remarkOne = remarkOne == null ? null : remarkOne.trim();
    }

    public String getRemarkTwo() {
        return remarkTwo;
    }

    public void setRemarkTwo(String remarkTwo) {
        this.remarkTwo = remarkTwo == null ? null : remarkTwo.trim();
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }

    public String getCurKind() {
        return curKind;
    }

    public void setCurKind(String curKind) {
        this.curKind = curKind == null ? null : curKind.trim();
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime == null ? null : tradeTime.trim();
    }

    public String getCurPrem() {
        return curPrem;
    }

    public void setCurPrem(String curPrem) {
        this.curPrem = curPrem == null ? null : curPrem.trim();
    }

    public String getProdTradeType() {
        return prodTradeType;
    }

    public void setProdTradeType(String prodTradeType) {
        this.prodTradeType = prodTradeType == null ? null : prodTradeType.trim();
    }
    public String getIsat() {
		return isat;
	}

	public void setIsat(String isat) {
		this.isat = isat= isat == null ? null : isat.trim();
	}

	public String getIsfe() {
		return isfe;
	}

	public void setIsfe(String isfe) {
		this.isfe = isfe= isfe == null ? null : isfe.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address = address == null ? null : address.trim();
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit = unit == null ? null : unit.trim();
	}

}