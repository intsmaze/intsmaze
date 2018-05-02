package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class FDFBSysConfig extends BasePojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3848308681547191642L;

	private String seqno;

    private String syskey;

    private String sysvalue;

    private String syscode;

    private String status;

    private String sysdesc;

    private Date createon;

    private String createby;

    private Date modifyon;

    private String modifyby;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateon() {
        return createon;
    }

    public void setCreateon(Date createon) {
        this.createon = createon;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getModifyon() {
        return modifyon;
    }

    public void setModifyon(Date modifyon) {
        this.modifyon = modifyon;
    }

    public String getModifyby() {
        return modifyby;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby == null ? null : modifyby.trim();
    }

	public String getSyskey() {
		return syskey;
	}

	public void setSyskey(String syskey) {
		this.syskey = syskey;
	}

	public String getSysvalue() {
		return sysvalue;
	}

	public void setSysvalue(String sysvalue) {
		this.sysvalue = sysvalue;
	}

	public String getSyscode() {
		return syscode;
	}

	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}

	public String getSysdesc() {
		return sysdesc;
	}

	public void setSysdesc(String sysdesc) {
		this.sysdesc = sysdesc;
	}
}