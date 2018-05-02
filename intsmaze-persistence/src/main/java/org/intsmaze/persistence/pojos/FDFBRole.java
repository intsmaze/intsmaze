package org.intsmaze.persistence.pojos;

import java.sql.Timestamp;

import org.intsmaze.persistence.common.BasePojo;

public class FDFBRole extends BasePojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7078909918137212895L;

	private String seqno;

    private String rolename;

    private String roledesc;

    private Timestamp createon;

    private String createby;

    private Timestamp modifyon;

    private String modifyby;
    
    private String roleIds;

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

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
}