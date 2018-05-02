package org.intsmaze.persistence.pojos;

import java.sql.Timestamp;

import org.intsmaze.persistence.common.BasePojo;

public class FDFBDepartment extends BasePojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5295331336641798583L;

	private String seqno;

    private String depname;

    private String depdesc;

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

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname == null ? null : depname.trim();
    }

    public String getDepdesc() {
        return depdesc;
    }

    public void setDepdesc(String depdesc) {
        this.depdesc = depdesc == null ? null : depdesc.trim();
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