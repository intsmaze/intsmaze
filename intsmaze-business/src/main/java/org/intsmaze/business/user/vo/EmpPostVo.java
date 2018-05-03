package org.intsmaze.business.user.vo;

import org.intsmaze.business.vo.BaseVo;

public class EmpPostVo extends BaseVo{
    private String seqno;

    private Integer version;

    private String empInnerCoding;

    private String postInnerCoding;

    private String status;

    private String mainPost;

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

    public String getEmpInnerCoding() {
        return empInnerCoding;
    }

    public void setEmpInnerCoding(String empInnerCoding) {
        this.empInnerCoding = empInnerCoding == null ? null : empInnerCoding.trim();
    }

    public String getPostInnerCoding() {
        return postInnerCoding;
    }

    public void setPostInnerCoding(String postInnerCoding) {
        this.postInnerCoding = postInnerCoding == null ? null : postInnerCoding.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMainPost() {
        return mainPost;
    }

    public void setMainPost(String mainPost) {
        this.mainPost = mainPost == null ? null : mainPost.trim();
    }
}