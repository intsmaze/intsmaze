package org.intsmaze.business.vo;


import java.sql.Timestamp;

import org.intsmaze.business.vo.BaseVo;

public class FDFBBimsUnmatchedCallShare extends BaseVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3980616135398513028L;


	private String seqno;

    private String phonenumber;

    private String channel;

    private String calltype;

    private Timestamp calltime;

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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getCalltype() {
        return calltype;
    }

    public void setCalltype(String calltype) {
        this.calltype = calltype == null ? null : calltype.trim();
    }

    public Timestamp getCalltime() {
        return calltime;
    }

    public void setCalltime(Timestamp calltime) {
        this.calltime = calltime;
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