package org.intsmaze.business.user.vo;

import org.intsmaze.business.vo.BaseVo;

public class UnitVo extends BaseVo {
    private String seqno;

    private Integer version;

    private String unitInnerCoding;

    private String orgId;

    private String status;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUnitInnerCoding() {
        return unitInnerCoding;
    }

    public void setUnitInnerCoding(String unitInnerCoding) {
        this.unitInnerCoding = unitInnerCoding == null ? null : unitInnerCoding.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}