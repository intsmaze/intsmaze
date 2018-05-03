package org.intsmaze.business.vo;


import java.sql.Timestamp;

public class FDFBBimsMaintainSheetShare extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1193639168563964299L;

	private String seqno;

	private String boothseqno;

	private String boothid;

	private String areaid;

	private String boothstyle;

	private String boothaddress;

	private String phonetype1;

	private String problemtype;

	private String problemsource;

	private String problemdesc;

	private Timestamp problemtime;

	private String phonenumber1;

	private String phonenumber2;

	private String phonetype2;

	private String periodtime;

	private String manufacturer;

	private String electricboxadd;

	private String billaccount;

	private String accepter;

	private String maintaindepid;

	private String processerid1;

	private String processerid2;

	private String processer1name;

	private String processer2name;

	private String dispatchid;

	private String processresult;

	private Timestamp sendtime;

	private String cleaningscore;

	private Integer sheetid;

	private String status;

	private Integer maintaincount;

	private String workloadstate;

	private String oldproblemtype;

	private String phonestatus;

	private Integer uploadtimes;

	private Integer resendtimes;

	private Timestamp madeon;

	private String madeby;

	private Timestamp checkedon;

	private String checkedby;

	private Timestamp createon;

	private String createby;

	private Timestamp modifyon;

	private String modifyby;
	
	private Timestamp wantfinishtime;
	
	private Timestamp finishtime;
	
	private String rejectreason;
	
	private String multipleStatus;
	
	private String viewprocesserid;
	
	private String oldprocesserid1;

	private String oldprocesserid2;

	private String oldprocesser1name;

	private String oldprocesser2name;

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno == null ? null : seqno.trim();
	}

	public String getBoothseqno() {
		return boothseqno;
	}

	public void setBoothseqno(String boothseqno) {
		this.boothseqno = boothseqno == null ? null : boothseqno.trim();
	}

	public String getBoothid() {
		return boothid;
	}

	public void setBoothid(String boothid) {
		this.boothid = boothid == null ? null : boothid.trim();
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid == null ? null : areaid.trim();
	}

	public String getBoothstyle() {
		return boothstyle;
	}

	public void setBoothstyle(String boothstyle) {
		this.boothstyle = boothstyle == null ? null : boothstyle.trim();
	}

	public String getBoothaddress() {
		return boothaddress;
	}

	public void setBoothaddress(String boothaddress) {
		this.boothaddress = boothaddress == null ? null : boothaddress.trim();
	}

	public String getPhonetype1() {
		return phonetype1;
	}

	public void setPhonetype1(String phonetype1) {
		this.phonetype1 = phonetype1 == null ? null : phonetype1.trim();
	}

	public String getProblemtype() {
		return problemtype;
	}

	public void setProblemtype(String problemtype) {
		this.problemtype = problemtype == null ? null : problemtype.trim();
	}

	public String getProblemsource() {
		return problemsource;
	}

	public void setProblemsource(String problemsource) {
		this.problemsource = problemsource == null ? null : problemsource
				.trim();
	}

	public String getProblemdesc() {
		return problemdesc;
	}

	public void setProblemdesc(String problemdesc) {
		this.problemdesc = problemdesc == null ? null : problemdesc.trim();
	}

	public Timestamp getProblemtime() {
		return problemtime;
	}

	public void setProblemtime(Timestamp problemtime) {
		this.problemtime = problemtime;
	}

	public String getPhonenumber1() {
		return phonenumber1;
	}

	public void setPhonenumber1(String phonenumber1) {
		this.phonenumber1 = phonenumber1 == null ? null : phonenumber1.trim();
	}

	public String getPhonenumber2() {
		return phonenumber2;
	}

	public void setPhonenumber2(String phonenumber2) {
		this.phonenumber2 = phonenumber2 == null ? null : phonenumber2.trim();
	}

	public String getPhonetype2() {
		return phonetype2;
	}

	public void setPhonetype2(String phonetype2) {
		this.phonetype2 = phonetype2 == null ? null : phonetype2.trim();
	}

	public String getPeriodtime() {
		return periodtime;
	}

	public void setPeriodtime(String periodtime) {
		this.periodtime = periodtime == null ? null : periodtime.trim();
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer == null ? null : manufacturer.trim();
	}

	public String getElectricboxadd() {
		return electricboxadd;
	}

	public void setElectricboxadd(String electricboxadd) {
		this.electricboxadd = electricboxadd == null ? null : electricboxadd
				.trim();
	}

	public String getBillaccount() {
		return billaccount;
	}

	public void setBillaccount(String billaccount) {
		this.billaccount = billaccount == null ? null : billaccount.trim();
	}

	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter == null ? null : accepter.trim();
	}

	public String getMaintaindepid() {
		return maintaindepid;
	}

	public void setMaintaindepid(String maintaindepid) {
		this.maintaindepid = maintaindepid == null ? null : maintaindepid
				.trim();
	}

	public String getProcesserid1() {
		return processerid1;
	}

	public void setProcesserid1(String processerid1) {
		this.processerid1 = processerid1 == null ? null : processerid1.trim();
	}

	public String getProcesserid2() {
		return processerid2;
	}

	public void setProcesserid2(String processerid2) {
		this.processerid2 = processerid2 == null ? null : processerid2.trim();
	}

	public String getProcesser1name() {
		return processer1name;
	}

	public void setProcesser1name(String processer1name) {
		this.processer1name = processer1name == null ? null : processer1name
				.trim();
	}

	public String getProcesser2name() {
		return processer2name;
	}

	public void setProcesser2name(String processer2name) {
		this.processer2name = processer2name == null ? null : processer2name
				.trim();
	}

	public String getDispatchid() {
		return dispatchid;
	}

	public void setDispatchid(String dispatchid) {
		this.dispatchid = dispatchid == null ? null : dispatchid.trim();
	}

	public String getProcessresult() {
		return processresult;
	}

	public void setProcessresult(String processresult) {
		this.processresult = processresult == null ? null : processresult
				.trim();
	}

	public Timestamp getSendtime() {
		return sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}

	public String getCleaningscore() {
		return cleaningscore;
	}

	public void setCleaningscore(String cleaningscore) {
		this.cleaningscore = cleaningscore == null ? null : cleaningscore
				.trim();
	}

	public Integer getSheetid() {
		return sheetid;
	}

	public void setSheetid(Integer sheetid) {
		this.sheetid = sheetid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Integer getMaintaincount() {
		return maintaincount;
	}

	public void setMaintaincount(Integer maintaincount) {
		this.maintaincount = maintaincount;
	}

	public String getWorkloadstate() {
		return workloadstate;
	}

	public void setWorkloadstate(String workloadstate) {
		this.workloadstate = workloadstate == null ? null : workloadstate
				.trim();
	}

	public String getOldproblemtype() {
		return oldproblemtype;
	}

	public void setOldproblemtype(String oldproblemtype) {
		this.oldproblemtype = oldproblemtype == null ? null : oldproblemtype
				.trim();
	}

	public String getPhonestatus() {
		return phonestatus;
	}

	public void setPhonestatus(String phonestatus) {
		this.phonestatus = phonestatus == null ? null : phonestatus.trim();
	}

	public Integer getUploadtimes() {
		return uploadtimes;
	}

	public void setUploadtimes(Integer uploadtimes) {
		this.uploadtimes = uploadtimes;
	}

	public Integer getResendtimes() {
		return resendtimes;
	}

	public void setResendtimes(Integer resendtimes) {
		this.resendtimes = resendtimes;
	}

	public Timestamp getMadeon() {
		return madeon;
	}

	public void setMadeon(Timestamp madeon) {
		this.madeon = madeon;
	}

	public String getMadeby() {
		return madeby;
	}

	public void setMadeby(String madeby) {
		this.madeby = madeby == null ? null : madeby.trim();
	}

	public Timestamp getCheckedon() {
		return checkedon;
	}

	public void setCheckedon(Timestamp checkedon) {
		this.checkedon = checkedon;
	}

	public String getCheckedby() {
		return checkedby;
	}

	public void setCheckedby(String checkedby) {
		this.checkedby = checkedby == null ? null : checkedby.trim();
	}

	public Timestamp getCreateon() {
		return createon;
	}

	public void setCreateon(Timestamp createon) {
		this.createon = createon;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby == null ? null : createby.trim();
	}

	public Timestamp getModifyon() {
		return modifyon;
	}

	public void setModifyon(Timestamp modifyon) {
		this.modifyon = modifyon;
	}

	public String getModifyby() {
		return modifyby;
	}

	public void setModifyby(String modifyby) {
		this.modifyby = modifyby == null ? null : modifyby.trim();
	}

	public Timestamp getWantfinishtime() {
		return wantfinishtime;
	}

	public void setWantfinishtime(Timestamp wantfinishtime) {
		this.wantfinishtime = wantfinishtime;
	}

	public Timestamp getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Timestamp finishtime) {
		this.finishtime = finishtime;
	}

	public String getRejectreason() {
		return rejectreason;
	}

	public void setRejectreason(String rejectreason) {
		this.rejectreason = rejectreason;
	}

	public String getMultipleStatus() {
		return multipleStatus;
	}

	public void setMultipleStatus(String multipleStatus) {
		this.multipleStatus = multipleStatus;
	}

	public String getViewprocesserid() {
		return viewprocesserid;
	}

	public void setViewprocesserid(String viewprocesserid) {
		this.viewprocesserid = viewprocesserid;
	}

	public String getOldprocesserid1() {
		return oldprocesserid1;
	}

	public void setOldprocesserid1(String oldprocesserid1) {
		this.oldprocesserid1 = oldprocesserid1;
	}

	public String getOldprocesserid2() {
		return oldprocesserid2;
	}

	public void setOldprocesserid2(String oldprocesserid2) {
		this.oldprocesserid2 = oldprocesserid2;
	}

	public String getOldprocesser1name() {
		return oldprocesser1name;
	}

	public void setOldprocesser1name(String oldprocesser1name) {
		this.oldprocesser1name = oldprocesser1name;
	}

	public String getOldprocesser2name() {
		return oldprocesser2name;
	}

	public void setOldprocesser2name(String oldprocesser2name) {
		this.oldprocesser2name = oldprocesser2name;
	}
}