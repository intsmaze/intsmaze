package org.intsmaze.business.vo;

import java.sql.Timestamp;

import javax.validation.constraints.Size;

import org.intsmaze.core.util.Constant;

public class FDFBBimsProjSheetShare extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4352374352386133512L;

	private String seqno;

	private Integer sheetid;

	private String depid;

	private Timestamp starttime;

	private String sheettype;

	private String boothseqno;

	private String boothid;

	private String boothstyle;

	private String boothareaid;

	private String regions;

	private String boothaddress;

	private String phonenumber1;

	private String phonenumber2;

	private String phonetype1;

	private String phonetype2;

	private String electricboxadd;

	private String electricboxid;

	private String belongstation;

	private String billaccount;

	private String cleaningcompany;

	private String addequipment;

	private String roaddistribut;

	private String boothstate;

	private String newboothid;

	private String newboothstyle;

	private String newboothareaid;

	private String newregions;

	private String newboothaddress;

	private String newphonenumber1;

	private String newphonenumber2;

	private String newphonetype1;

	private String newphonetype2;

	private String newelectricboxadd;

	private String newelectricboxid;

	private String newbelongstation;

	private String newbillaccount;

	private String newcleaningcompany;

	private String newaddequipment;

	private String newroaddistribut;

	private String newboothstate;

	private Timestamp constructtime;

	private String acceptancer;

	private Timestamp finishtime;

	private String acceptancestate;

	private String maintainresult;

	private Timestamp acceptancetime;

	private String maintainaccept;

	private Timestamp datachangetime;

	private String sendcarid;

	private String status;

	private String phonestatus;

	private String rejectreason;

	private Timestamp madeon;

	private String madeby;

	private Timestamp checkedon;

	private String checkedby;

	private Timestamp createon;

	private String createby;

	private Timestamp modifyon;

	private String modifyby;

	private String applyreason;

	private String periodtime;
	
	private String newperiodtime;
	
	private Timestamp wantfinishtime;
	
	private String workloadstatus;
	
	private String filenumber;

	private String manufacturer;

	private String newfilenumber;

	private String newmanufacturer;
	
	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno == null ? null : seqno.trim();
	}

	public Integer getSheetid() {
		return sheetid;
	}

	public void setSheetid(Integer sheetid) {
		this.sheetid = sheetid;
	}

	public String getDepid() {
		return depid;
	}

	public void setDepid(String depid) {
		this.depid = depid == null ? null : depid.trim();
	}

	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public String getSheettype() {
		return sheettype;
	}

	public void setSheettype(String sheettype) {
		this.sheettype = sheettype == null ? null : sheettype.trim();
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

	public String getBoothstyle() {
		return boothstyle;
	}

	public void setBoothstyle(String boothstyle) {
		this.boothstyle = boothstyle == null ? null : boothstyle.trim();
	}

	public String getBoothareaid() {
		return boothareaid;
	}

	public void setBoothareaid(String boothareaid) {
		this.boothareaid = boothareaid == null ? null : boothareaid.trim();
	}

	public String getRegions() {
		return regions;
	}

	public void setRegions(String regions) {
		this.regions = regions == null ? null : regions.trim();
	}

	public String getBoothaddress() {
		return boothaddress;
	}

	public void setBoothaddress(String boothaddress) {
		this.boothaddress = boothaddress == null ? null : boothaddress.trim();
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

	public String getPhonetype1() {
		return phonetype1;
	}

	public void setPhonetype1(String phonetype1) {
		this.phonetype1 = phonetype1 == null ? null : phonetype1.trim();
	}

	public String getPhonetype2() {
		return phonetype2;
	}

	public void setPhonetype2(String phonetype2) {
		this.phonetype2 = phonetype2 == null ? null : phonetype2.trim();
	}

	public String getElectricboxadd() {
		return electricboxadd;
	}

	public void setElectricboxadd(String electricboxadd) {
		this.electricboxadd = electricboxadd == null ? null : electricboxadd
				.trim();
	}

	public String getElectricboxid() {
		return electricboxid;
	}

	public void setElectricboxid(String electricboxid) {
		this.electricboxid = electricboxid == null ? null : electricboxid
				.trim();
	}

	public String getBelongstation() {
		return belongstation;
	}

	public void setBelongstation(String belongstation) {
		this.belongstation = belongstation == null ? null : belongstation
				.trim();
	}

	public String getBillaccount() {
		return billaccount;
	}

	public void setBillaccount(String billaccount) {
		this.billaccount = billaccount == null ? null : billaccount.trim();
	}

	public String getCleaningcompany() {
		return cleaningcompany;
	}

	public void setCleaningcompany(String cleaningcompany) {
		this.cleaningcompany = cleaningcompany == null ? null : cleaningcompany
				.trim();
	}

	public String getAddequipment() {
		return addequipment;
	}

	public void setAddequipment(String addequipment) {
		this.addequipment = addequipment == null ? null : addequipment.trim();
	}

	public String getRoaddistribut() {
		return roaddistribut;
	}

	public void setRoaddistribut(String roaddistribut) {
		this.roaddistribut = roaddistribut == null ? null : roaddistribut
				.trim();
	}

	public String getBoothstate() {
		return boothstate;
	}

	public void setBoothstate(String boothstate) {
		this.boothstate = boothstate == null ? null : boothstate.trim();
	}

	public String getNewboothid() {
		return newboothid;
	}

	public void setNewboothid(String newboothid) {
		this.newboothid = newboothid == null ? null : newboothid.trim();
	}

	public String getNewboothstyle() {
		return newboothstyle;
	}

	public void setNewboothstyle(String newboothstyle) {
		this.newboothstyle = newboothstyle == null ? null : newboothstyle
				.trim();
	}

	public String getNewboothareaid() {
		return newboothareaid;
	}

	public void setNewboothareaid(String newboothareaid) {
		this.newboothareaid = newboothareaid == null ? null : newboothareaid
				.trim();
	}

	public String getNewregions() {
		return newregions;
	}

	public void setNewregions(String newregions) {
		this.newregions = newregions == null ? null : newregions.trim();
	}

	public String getNewboothaddress() {
		return newboothaddress;
	}

	public void setNewboothaddress(String newboothaddress) {
		this.newboothaddress = newboothaddress == null ? null : newboothaddress
				.trim();
	}

	public String getNewphonenumber1() {
		return newphonenumber1;
	}

	public void setNewphonenumber1(String newphonenumber1) {
		this.newphonenumber1 = newphonenumber1 == null ? null : newphonenumber1
				.trim();
	}

	public String getNewphonenumber2() {
		return newphonenumber2;
	}

	public void setNewphonenumber2(String newphonenumber2) {
		this.newphonenumber2 = newphonenumber2 == null ? null : newphonenumber2
				.trim();
	}

	public String getNewphonetype1() {
		return newphonetype1;
	}

	public void setNewphonetype1(String newphonetype1) {
		this.newphonetype1 = newphonetype1 == null ? null : newphonetype1
				.trim();
	}

	public String getNewphonetype2() {
		return newphonetype2;
	}

	public void setNewphonetype2(String newphonetype2) {
		this.newphonetype2 = newphonetype2 == null ? null : newphonetype2
				.trim();
	}

	public String getNewelectricboxadd() {
		return newelectricboxadd;
	}

	public void setNewelectricboxadd(String newelectricboxadd) {
		this.newelectricboxadd = newelectricboxadd == null ? null
				: newelectricboxadd.trim();
	}

	public String getNewelectricboxid() {
		return newelectricboxid;
	}

	public void setNewelectricboxid(String newelectricboxid) {
		this.newelectricboxid = newelectricboxid == null ? null
				: newelectricboxid.trim();
	}

	public String getNewbelongstation() {
		return newbelongstation;
	}

	public void setNewbelongstation(String newbelongstation) {
		this.newbelongstation = newbelongstation == null ? null
				: newbelongstation.trim();
	}

	public String getNewbillaccount() {
		return newbillaccount;
	}

	public void setNewbillaccount(String newbillaccount) {
		this.newbillaccount = newbillaccount == null ? null : newbillaccount
				.trim();
	}

	public String getNewcleaningcompany() {
		return newcleaningcompany;
	}

	public void setNewcleaningcompany(String newcleaningcompany) {
		this.newcleaningcompany = newcleaningcompany == null ? null
				: newcleaningcompany.trim();
	}

	public String getNewaddequipment() {
		return newaddequipment;
	}

	public void setNewaddequipment(String newaddequipment) {
		this.newaddequipment = newaddequipment == null ? null : newaddequipment
				.trim();
	}

	public String getNewroaddistribut() {
		return newroaddistribut;
	}

	public void setNewroaddistribut(String newroaddistribut) {
		this.newroaddistribut = newroaddistribut == null ? null
				: newroaddistribut.trim();
	}

	public String getNewboothstate() {
		return newboothstate;
	}

	public void setNewboothstate(String newboothstate) {
		this.newboothstate = newboothstate == null ? null : newboothstate
				.trim();
	}

	public Timestamp getConstructtime() {
		return constructtime;
	}

	public void setConstructtime(Timestamp constructtime) {
		this.constructtime = constructtime;
	}

	public String getAcceptancer() {
		return acceptancer;
	}

	public void setAcceptancer(String acceptancer) {
		this.acceptancer = acceptancer == null ? null : acceptancer.trim();
	}

	public Timestamp getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Timestamp finishtime) {
		this.finishtime = finishtime;
	}

	public String getAcceptancestate() {
		return acceptancestate;
	}

	public void setAcceptancestate(String acceptancestate) {
		this.acceptancestate = acceptancestate == null ? null : acceptancestate
				.trim();
	}

	public String getMaintainresult() {
		return maintainresult;
	}

	public void setMaintainresult(String maintainresult) {
		this.maintainresult = maintainresult == null ? null : maintainresult
				.trim();
	}

	public Timestamp getAcceptancetime() {
		return acceptancetime;
	}

	public void setAcceptancetime(Timestamp acceptancetime) {
		this.acceptancetime = acceptancetime;
	}

	public String getMaintainaccept() {
		return maintainaccept;
	}

	public void setMaintainaccept(String maintainaccept) {
		this.maintainaccept = maintainaccept == null ? null : maintainaccept
				.trim();
	}

	public Timestamp getDatachangetime() {
		return datachangetime;
	}

	public void setDatachangetime(Timestamp datachangetime) {
		this.datachangetime = datachangetime;
	}

	public String getSendcarid() {
		return sendcarid;
	}

	public void setSendcarid(String sendcarid) {
		this.sendcarid = sendcarid == null ? null : sendcarid.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getPhonestatus() {
		return phonestatus;
	}

	public void setPhonestatus(String phonestatus) {
		this.phonestatus = phonestatus == null ? null : phonestatus.trim();
	}

	public String getRejectreason() {
		return rejectreason;
	}

	public void setRejectreason(String rejectreason) {
		this.rejectreason = rejectreason == null ? null : rejectreason.trim();
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

	public String getApplyreason() {
		return applyreason;
	}

	public void setApplyreason(String applyreason) {
		this.applyreason = applyreason;
	}

	public String getPeriodtime() {
		return periodtime;
	}

	public void setPeriodtime(String periodtime) {
		this.periodtime = periodtime;
	}

	public String getNewperiodtime() {
		return newperiodtime;
	}

	public void setNewperiodtime(String newperiodtime) {
		this.newperiodtime = newperiodtime;
	}

	public Timestamp getWantfinishtime() {
		return wantfinishtime;
	}

	public void setWantfinishtime(Timestamp wantfinishtime) {
		this.wantfinishtime = wantfinishtime;
	}

	public String getWorkloadstatus() {
		return workloadstatus;
	}

	public void setWorkloadstatus(String workloadstatus) {
		this.workloadstatus = workloadstatus;
	}

	public String getFilenumber() {
		return filenumber;
	}

	public void setFilenumber(String filenumber) {
		this.filenumber = filenumber;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getNewfilenumber() {
		return newfilenumber;
	}

	public void setNewfilenumber(String newfilenumber) {
		this.newfilenumber = newfilenumber;
	}

	public String getNewmanufacturer() {
		return newmanufacturer;
	}

	public void setNewmanufacturer(String newmanufacturer) {
		this.newmanufacturer = newmanufacturer;
	}
}