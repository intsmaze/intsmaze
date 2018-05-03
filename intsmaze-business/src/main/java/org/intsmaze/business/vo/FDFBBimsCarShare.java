package org.intsmaze.business.vo;

import java.sql.Timestamp;

import org.intsmaze.business.springmvc.controller.BusinessUtil;
import org.intsmaze.core.util.DateUtil;

public class FDFBBimsCarShare extends BaseVo{
    private String seqno;

    private String platenumber;

    private String status;

    private String mileage;

    private String enginenumber;

    private Timestamp  buytime;

    private String buyerid;

    private Timestamp  createon;

    private String createby;

    private Timestamp  modifyon;

    private String modifyby;
    
    private String beginDate;
    
    private String endDate;
    
    public String buytimeDesc;
    
    private String statusDesc;
    
    private String multipleStatus;

    public String getMultipleStatus() {
		return multipleStatus;
	}

	public void setMultipleStatus(String multipleStatus) {
		this.multipleStatus = multipleStatus;
	}
    
    public String getStatusDesc() {
		return BusinessUtil.getValueByCodeAndKey("key_car_status", this.getStatus());
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
    
	public String getBuytimeDesc() {
		return DateUtil.formatDate(this.getBuytime());
	}

	public void setBuytimeDesc(String buytimeDesc) {
		this.buytimeDesc = buytimeDesc;
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

    public String getPlatenumber() {
        return platenumber;
    }

    public void setPlatenumber(String platenumber) {
        this.platenumber = platenumber == null ? null : platenumber.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage == null ? null : mileage.trim();
    }

    public String getEnginenumber() {
        return enginenumber;
    }

    public void setEnginenumber(String enginenumber) {
        this.enginenumber = enginenumber == null ? null : enginenumber.trim();
    }

    public Timestamp  getBuytime() {
        return buytime;
    }

    public void setBuytime(Timestamp   buytime) {
        this.buytime = buytime;
    }

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid == null ? null : buyerid.trim();
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