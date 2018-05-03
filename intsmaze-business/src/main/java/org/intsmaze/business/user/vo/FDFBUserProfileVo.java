package org.intsmaze.business.user.vo;

import java.sql.Timestamp;

import org.intsmaze.business.vo.BaseVo;

public class FDFBUserProfileVo extends BaseVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1400436580416671331L;

	private String seqno;

    private String userStyle;

    private String userHomepage;

    private String userAtt1;

    private String userAtt2;

    private String userAtt3;

    private Timestamp createon;

    private String createby;

    private Timestamp modifyon;

    private String modifyby;
    
    private String userId;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getUserStyle() {
        return userStyle;
    }

    public void setUserStyle(String userStyle) {
        this.userStyle = userStyle == null ? null : userStyle.trim();
    }

    public String getUserHomepage() {
        return userHomepage;
    }

    public void setUserHomepage(String userHomepage) {
        this.userHomepage = userHomepage == null ? null : userHomepage.trim();
    }

    public String getUserAtt1() {
        return userAtt1;
    }

    public void setUserAtt1(String userAtt1) {
        this.userAtt1 = userAtt1 == null ? null : userAtt1.trim();
    }

    public String getUserAtt2() {
        return userAtt2;
    }

    public void setUserAtt2(String userAtt2) {
        this.userAtt2 = userAtt2 == null ? null : userAtt2.trim();
    }

    public String getUserAtt3() {
        return userAtt3;
    }

    public void setUserAtt3(String userAtt3) {
        this.userAtt3 = userAtt3 == null ? null : userAtt3.trim();
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}