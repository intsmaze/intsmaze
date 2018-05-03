package org.intsmaze.business.user.vo;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.intsmaze.business.vo.BaseVo;
import org.intsmaze.business.vo.validator.InsertGroup;
import org.intsmaze.business.vo.validator.UpdateGroup;

public class FDFBRoleVo extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7634027194516408063L;

	private String seqno;

    @NotNull(message = "user.rolename.null")
    @Size(min = 1,message="user.rolename.null")
    private String rolename;

    @NotNull(message = "user.roledesc.null")
    @Size(min = 1,message="user.roledesc.null")
    private String roledesc;

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

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
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