package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;


public class FDFBBimsArea extends BasePojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1915576889480650705L;

	private String seqno;

    private String areaname;

    private String areadesc;

    private String belongpro;

    private String belongcity;

    private String areacode;

    private String cleaningid;

    private String maintainid;

    private String status;

    private String ispack;

    private String packcompany;

    private String packfaulttype;

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

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public String getAreadesc() {
        return areadesc;
    }

    public void setAreadesc(String areadesc) {
        this.areadesc = areadesc == null ? null : areadesc.trim();
    }

    public String getBelongpro() {
        return belongpro;
    }

    public void setBelongpro(String belongpro) {
        this.belongpro = belongpro == null ? null : belongpro.trim();
    }

    public String getBelongcity() {
        return belongcity;
    }

    public void setBelongcity(String belongcity) {
        this.belongcity = belongcity == null ? null : belongcity.trim();
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    public String getCleaningid() {
        return cleaningid;
    }

    public void setCleaningid(String cleaningid) {
        this.cleaningid = cleaningid == null ? null : cleaningid.trim();
    }

    public String getMaintainid() {
        return maintainid;
    }

    public void setMaintainid(String maintainid) {
        this.maintainid = maintainid == null ? null : maintainid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIspack() {
        return ispack;
    }

    public void setIspack(String ispack) {
        this.ispack = ispack == null ? null : ispack.trim();
    }

    public String getPackcompany() {
        return packcompany;
    }

    public void setPackcompany(String packcompany) {
        this.packcompany = packcompany == null ? null : packcompany.trim();
    }

    public String getPackfaulttype() {
        return packfaulttype;
    }

    public void setPackfaulttype(String packfaulttype) {
        this.packfaulttype = packfaulttype == null ? null : packfaulttype.trim();
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
}