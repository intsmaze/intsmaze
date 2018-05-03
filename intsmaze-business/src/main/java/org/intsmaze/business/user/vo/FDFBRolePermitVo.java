package org.intsmaze.business.user.vo;

import java.sql.Timestamp;
import java.util.Date;

import org.intsmaze.business.vo.BaseVo;

public class FDFBRolePermitVo extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4399311062457204355L;

	private String seqno;

    private String roleid;

    private String permitid;

    private Timestamp createon;

    private Timestamp modifyon;

    private String createby;

    private String modifyby;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getPermitid() {
        return permitid;
    }

    public void setPermitid(String permitid) {
        this.permitid = permitid == null ? null : permitid.trim();
    }

    public Date getCreateon() {
        return createon;
    }

    public void setCreateon(Timestamp createon) {
        this.createon = createon;
    }

    public Date getModifyon() {
        return modifyon;
    }

    public void setModifyon(Timestamp modifyon) {
        this.modifyon = modifyon;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public String getModifyby() {
        return modifyby;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby == null ? null : modifyby.trim();
    }
}