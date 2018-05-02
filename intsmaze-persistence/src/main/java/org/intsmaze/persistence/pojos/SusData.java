package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;


public class SusData  extends BasePojo {
	public SusData() {
		super();
		susCustomer = new SusCustomer();
	}
	private static final long serialVersionUID = 330459076646669573L;

	private String seqno;  //可疑交易数据记录ID

    private String transSpot; //可疑交易发生地代码

    private String stcr;  //特征

    private String sdgr;  //可疑程度

    private String tkms;  //采取措施

    private String ssds;  //可疑行为描述

    private String insertOrganId; //填报机构

    private String insertOper;  //填报人

    private Date insertTime;  //录入时间

    private String status;  //状态

    private Date reportTime;  //报告时间

    private String reportId;  //报告报文ID

    private String sscat;  //可疑类型

    private String conditionCode;  //可疑条件

    private String explication;  //合理解释

    private String firstId;  //初审人员ID

    private Date firstEndTime;  //初审完成时间

    private String secondId;  //运营复核人员ID

	private Date secondEndTime;  //运营复核完成时间

    private String finalId;  //

    private Date finalEndTime;  //

    private String approveFinishId;  //合规上报人员ID
 
    private Date approveFinishTime; //合规上报完成时间

    private String policyType;  //保单类型   1--代表个险      

    private String ctid;  //组织机构代码      

    private String crnm;  //法定代表人姓名  

    private String crit;  //证件类型        

    private String crid;  //证件号码        

    private String approveId;  //总公司审批人员

    private Date approveTime;  //总公司审批操作时间

    private String inspectorStatus;  //运营复核状态

    private String agreeSuggestion;  //

    private String cTransMrk;  //

    private Date tTransTm;  //

    private String firstInspectOperate;  //初审意见

    private String secondInspectOperate;  //复核意见
 
    private String approveOperate;  //总公司意见

    private String review;  //审核人

    private Date secondReviewTime;  //运营复核完成时间

    private String secondReviewOperate;  //运营复核意见

    private Date enterAuditTime;  //进入审核时间

    private String createby;  //

    private Date createon;  //暂时还没有加

    private String modifyby;  //

    private Date modifyon;  //
    

    
    private SusCustomer  susCustomer ; //客户表的关联
    
    private String policyNo;
    
    private String customerName;
    
    private String customerId;
    
    
    public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName == null ? null : customerName.trim();;
	}

	public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo == null ? null : policyNo.trim();
    }
   

	public SusCustomer getSusCustomer() {
		return susCustomer;
	}

	public void setSusCustomer(SusCustomer susCustomer) {
		this.susCustomer = susCustomer;
	}

    public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
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
		this.secondId = secondId;
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



    public String getCrid() {
        return crid;
    }

    public void setCrid(String crid) {
        this.crid = crid == null ? null : crid.trim();
    }



    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getInspectorStatus() {
        return inspectorStatus;
    }

    public void setInspectorStatus(String inspectorStatus) {
        this.inspectorStatus = inspectorStatus == null ? null : inspectorStatus.trim();
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

    public Date getSecondReviewTime() {
        return secondReviewTime;
    }

    public void setSecondReviewTime(Date secondReviewTime) {
        this.secondReviewTime = secondReviewTime;
    }

    public String getSecondReviewOperate() {
        return secondReviewOperate;
    }

    public void setSecondReviewOperate(String secondReviewOperate) {
        this.secondReviewOperate = secondReviewOperate == null ? null : secondReviewOperate.trim();
    }

    public Date getEnterAuditTime() {
        return enterAuditTime;
    }

    public void setEnterAuditTime(Date enterAuditTime) {
        this.enterAuditTime = enterAuditTime;
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
	public String getFirstId() {
		return firstId;
	}

	public void setFirstId(String firstId) {
		this.firstId = firstId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
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
		this.finalId = finalId;
	}

	public Date getFinalEndTime() {
		return finalEndTime;
	}

	public void setFinalEndTime(Date finalEndTime) {
		this.finalEndTime = finalEndTime;
	}

	public String getApproveFinishId() {
		return approveFinishId;
	}

	public void setApproveFinishId(String approveFinishId) {
		this.approveFinishId = approveFinishId;
	}

	public String getCrit() {
		return crit;
	}

	public void setCrit(String crit) {
		this.crit = crit;
	}

	public String getApproveId() {
		return approveId;
	}

	public void setApproveId(String approveId) {
		this.approveId = approveId;
	}

   
}