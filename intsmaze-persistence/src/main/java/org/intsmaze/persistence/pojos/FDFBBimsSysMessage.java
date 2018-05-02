package org.intsmaze.persistence.pojos;

import java.sql.Timestamp;
import org.intsmaze.persistence.common.BasePojo;


public class FDFBBimsSysMessage extends BasePojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2104234103599652937L;

	private String seqno;

    private String userid;

    private String messagecontent;

    private String pageurl;

    private String status;

    private String refsheetid;

    private String sheettype;

    private String messagetype;

    private Timestamp createon;

    private String createby;

    private Timestamp modifyon;

    private String modifyby;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getMessagecontent() {
        return messagecontent;
    }

    public void setMessagecontent(String messagecontent) {
        this.messagecontent = messagecontent == null ? null : messagecontent.trim();
    }

    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl == null ? null : pageurl.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRefsheetid() {
        return refsheetid;
    }

    public void setRefsheetid(String refsheetid) {
        this.refsheetid = refsheetid == null ? null : refsheetid.trim();
    }

    public String getSheettype() {
        return sheettype;
    }

    public void setSheettype(String sheettype) {
        this.sheettype = sheettype == null ? null : sheettype.trim();
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype == null ? null : messagetype.trim();
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
}