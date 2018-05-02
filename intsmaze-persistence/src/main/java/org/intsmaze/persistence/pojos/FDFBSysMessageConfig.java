package org.intsmaze.persistence.pojos;

import java.sql.Timestamp;

import org.intsmaze.persistence.common.BasePojo;


public class FDFBSysMessageConfig extends BasePojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8423701698705481253L;

	private String seqno;

    private String sheettype;

    private String sheetstate;

    private String messagetemplate;

    private String roleid;

    private String depid;

    private String pageurl;

    private Timestamp createon;

    private String createby;

    private Timestamp modifyon;

    private String modifyby;
    
    private String messagetype;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getSheettype() {
        return sheettype;
    }

    public void setSheettype(String sheettype) {
        this.sheettype = sheettype == null ? null : sheettype.trim();
    }

    public String getSheetstate() {
        return sheetstate;
    }

    public void setSheetstate(String sheetstate) {
        this.sheetstate = sheetstate == null ? null : sheetstate.trim();
    }

    public String getMessagetemplate() {
        return messagetemplate;
    }

    public void setMessagetemplate(String messagetemplate) {
        this.messagetemplate = messagetemplate == null ? null : messagetemplate.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getDepid() {
        return depid;
    }

    public void setDepid(String depid) {
        this.depid = depid == null ? null : depid.trim();
    }

    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl == null ? null : pageurl.trim();
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

	public String getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}
}