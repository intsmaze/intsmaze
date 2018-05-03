package org.intsmaze.business.user.vo;

import org.intsmaze.business.vo.BaseVo;

public class PostUnitVo extends BaseVo{
    private String seqno;

    private Integer version;

    private String postInnerCoding;

    private String unitInnerCoding;

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

    public String getPostInnerCoding() {
        return postInnerCoding;
    }

    public void setPostInnerCoding(String postInnerCoding) {
        this.postInnerCoding = postInnerCoding == null ? null : postInnerCoding.trim();
    }

    public String getUnitInnerCoding() {
        return unitInnerCoding;
    }

    public void setUnitInnerCoding(String unitInnerCoding) {
        this.unitInnerCoding = unitInnerCoding == null ? null : unitInnerCoding.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}