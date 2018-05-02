package org.intsmaze.persistence.pojos;


import java.sql.Timestamp;

import org.intsmaze.persistence.common.BasePojo;

public class FDFBButtonCustomConfig extends BasePojo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -47841481045632710L;

	private String seqno;

    private String pagename;

    private String pageurl;

    private String username;

    private String columnname;

    private String columndesc;

    private String processstatus;

    private Timestamp createon;

    private String createby;

    private Timestamp modifyon;

    private String modifyby;

    private String delstatus;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename == null ? null : pagename.trim();
    }

    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl == null ? null : pageurl.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getColumnname() {
        return columnname;
    }

    public void setColumnname(String columnname) {
        this.columnname = columnname == null ? null : columnname.trim();
    }

    public String getColumndesc() {
        return columndesc;
    }

    public void setColumndesc(String columndesc) {
        this.columndesc = columndesc == null ? null : columndesc.trim();
    }

    public String getProcessstatus() {
        return processstatus;
    }

    public void setProcessstatus(String processstatus) {
        this.processstatus = processstatus == null ? null : processstatus.trim();
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

    public String getDelstatus() {
        return delstatus;
    }

    public void setDelstatus(String delstatus) {
        this.delstatus = delstatus == null ? null : delstatus.trim();
    }
}