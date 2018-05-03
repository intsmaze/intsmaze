package org.intsmaze.business.vo;

import java.sql.Timestamp;

import org.intsmaze.business.springmvc.controller.BusinessUtil;
import org.intsmaze.core.util.DateUtil;

public class FDFBBimsSendcarShare extends BaseVo{
    private String seqno;

    private String vehicleid;

    private String platenumber;

    private String beginmileage;

    private String endmileage;

    private Timestamp  sendtime;

    private String roadtoll;

    private String depid;

    private String refillmiles;

    private String refillcost;

    private String refillcardid;

    private String cardremain;

    private String status;

    private String driverid;

    private String drivername;

    private String rejectreason;

    private Timestamp  madeon;

    private String madeby;

    private String madename;

    private Timestamp  checkedon;

    private String checkedby;

    private String checkedname;

    private Timestamp  createon;

    private String createby;

    private Timestamp  modifyon;

    private String modifyby;
    
    private String beginDate;
    
    private String endDate;
    
    public String sendtimeDesc;
    
    private String statusDesc;
    
    private String multipleStatus;
    
    private String depDesc;
    
	public String getDepDesc() {
		return BusinessUtil.getDepNameByDepid(this.getDepid());
	}

	public void setDepDesc(String depDesc) {
		this.depDesc = depDesc;
	}
    
//    private String depDesc;
//    
//	public String getDepDesc() {
//		return BusinessUtil.getDepNameByDepid(this.getDepid());
//	}
//
//	public void setDepDesc(String depDesc) {
//		this.depDesc = depDesc;
//	}

    public String getMultipleStatus() {
		return multipleStatus;
	}

	public void setMultipleStatus(String multipleStatus) {
		this.multipleStatus = multipleStatus;
	}
    
    public String getStatusDesc() {
		return BusinessUtil.getValueByCodeAndKey("key_sendcar_status", this.getStatus());
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
    
	public String getSendtimeDesc() {
		return DateUtil.formatDate(this.getSendtime());
	}

	public void setSendtimeDesc(String sendtimeDesc) {
		this.sendtimeDesc = sendtimeDesc;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	
    public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    public String getPlatenumber() {
        return platenumber;
    }

    public void setPlatenumber(String platenumber) {
        this.platenumber = platenumber == null ? null : platenumber.trim();
    }

    public String getBeginmileage() {
        return beginmileage;
    }

    public void setBeginmileage(String beginmileage) {
        this.beginmileage = beginmileage == null ? null : beginmileage.trim();
    }

    public String getEndmileage() {
        return endmileage;
    }

    public void setEndmileage(String endmileage) {
        this.endmileage = endmileage == null ? null : endmileage.trim();
    }

    public Timestamp  getSendtime() {
        return sendtime;
    }

    public void setSendtime(Timestamp   sendtime) {
        this.sendtime = sendtime;
    }

    public String getRoadtoll() {
        return roadtoll;
    }

    public void setRoadtoll(String roadtoll) {
        this.roadtoll = roadtoll == null ? null : roadtoll.trim();
    }

    public String getDepid() {
        return depid;
    }

    public void setDepid(String depid) {
        this.depid = depid == null ? null : depid.trim();
    }

    public String getRefillmiles() {
        return refillmiles;
    }

    public void setRefillmiles(String refillmiles) {
        this.refillmiles = refillmiles == null ? null : refillmiles.trim();
    }

    public String getRefillcost() {
        return refillcost;
    }

    public void setRefillcost(String refillcost) {
        this.refillcost = refillcost == null ? null : refillcost.trim();
    }

    public String getRefillcardid() {
        return refillcardid;
    }

    public void setRefillcardid(String refillcardid) {
        this.refillcardid = refillcardid == null ? null : refillcardid.trim();
    }

    public String getCardremain() {
        return cardremain;
    }

    public void setCardremain(String cardremain) {
        this.cardremain = cardremain == null ? null : cardremain.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername == null ? null : drivername.trim();
    }

    public String getRejectreason() {
        return rejectreason;
    }

    public void setRejectreason(String rejectreason) {
        this.rejectreason = rejectreason == null ? null : rejectreason.trim();
    }

    public Timestamp  getMadeon() {
        return madeon;
    }

    public void setMadeon(Timestamp   madeon) {
        this.madeon = madeon;
    }

    public String getMadeby() {
        return madeby;
    }

    public void setMadeby(String madeby) {
        this.madeby = madeby == null ? null : madeby.trim();
    }

    public String getMadename() {
        return madename;
    }

    public void setMadename(String madename) {
        this.madename = madename == null ? null : madename.trim();
    }

    public Timestamp  getCheckedon() {
        return checkedon;
    }

    public void setCheckedon(Timestamp   checkedon) {
        this.checkedon = checkedon;
    }

    public String getCheckedby() {
        return checkedby;
    }

    public void setCheckedby(String checkedby) {
        this.checkedby = checkedby == null ? null : checkedby.trim();
    }

    public String getCheckedname() {
        return checkedname;
    }

    public void setCheckedname(String checkedname) {
        this.checkedname = checkedname == null ? null : checkedname.trim();
    }

    public Timestamp  getCreateon() {
        return createon;
    }

    public void setCreateon(Timestamp   createon) {
        this.createon = createon;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Timestamp  getModifyon() {
        return modifyon;
    }

    public void setModifyon(Timestamp   modifyon) {
        this.modifyon = modifyon;
    }

    public String getModifyby() {
        return modifyby;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby == null ? null : modifyby.trim();
    }
}