package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class FDFBReceiptManage extends BasePojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2623443551541503439L;

	private String seqno;

    private String zipName;

    private String fileName;

    private String sfid;

    private String ertn;

    private String fcer;

    private String erlc;
    
    private String chn;

    private String errs;

    private String rctn;

    private String fcrc;

    private String ocnm;

    private String otdt;

    private String otcd;

    private String otic;

    private String rclc;

    private String rcsg;

    private Date createDate;
    
    private String mark;
    
    private String text;
    
    private String path;
    
    private String unit;
    
    private Date update_time;

    public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getZipName() {
        return zipName;
    }

    public void setZipName(String zipName) {
        this.zipName = zipName == null ? null : zipName.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getSfid() {
        return sfid;
    }

    public void setSfid(String sfid) {
        this.sfid = sfid == null ? null : sfid.trim();
    }

    public String getErtn() {
        return ertn;
    }

    public void setErtn(String ertn) {
        this.ertn = ertn == null ? null : ertn.trim();
    }

    public String getFcer() {
        return fcer;
    }

    public void setFcer(String fcer) {
        this.fcer = fcer == null ? null : fcer.trim();
    }

    public String getErlc() {
        return erlc;
    }

    public void setErlc(String erlc) {
        this.erlc = erlc == null ? null : erlc.trim();
    }
    
    public String getChn(){
    	return chn;
    }
    
    public void setChn(String chn){
    	this.chn=chn;
    }

    public String getErrs() {
        return errs;
    }

    public void setErrs(String errs) {
        this.errs = errs == null ? null : errs.trim();
    }

    public String getRctn() {
        return rctn;
    }

    public void setRctn(String rctn) {
        this.rctn = rctn == null ? null : rctn.trim();
    }

    public String getFcrc() {
        return fcrc;
    }

    public void setFcrc(String fcrc) {
        this.fcrc = fcrc == null ? null : fcrc.trim();
    }

    public String getOcnm() {
        return ocnm;
    }

    public void setOcnm(String ocnm) {
        this.ocnm = ocnm == null ? null : ocnm.trim();
    }

    public String getOtdt() {
        return otdt;
    }

    public void setOtdt(String otdt) {
        this.otdt = otdt == null ? null : otdt.trim();
    }

    public String getOtcd() {
        return otcd;
    }

    public void setOtcd(String otcd) {
        this.otcd = otcd == null ? null : otcd.trim();
    }

    public String getOtic() {
        return otic;
    }

    public void setOtic(String otic) {
        this.otic = otic == null ? null : otic.trim();
    }

    public String getRclc() {
        return rclc;
    }

    public void setRclc(String rclc) {
        this.rclc = rclc == null ? null : rclc.trim();
    }

    public String getRcsg() {
        return rcsg;
    }

    public void setRcsg(String rcsg) {
        this.rcsg = rcsg == null ? null : rcsg.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public void setMark(String mark) {
		this.mark=mark;
	}
	
	public String getMark(){
		return mark;
	}

	public void setText(String text) {
		this.text=text;
	}
	
	public String getText(){
		return text;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}