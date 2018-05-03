package org.intsmaze.business.user.vo;

import java.sql.Timestamp;
import java.util.List;

import org.intsmaze.business.vo.BaseVo;

public class FDFBUserRoleVo extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1932401000892821900L;

	private String seqno;

    private String userid;

    private String roleid;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
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
    
    public static String convertRoleListToRoleIds(List<FDFBUserRoleVo> list)
    {
    	if(list == null || list.size() < 1)
    	{
    		return "";
    	}
    	else
    	{
    		
    		StringBuffer sb = new StringBuffer("");
    		for(int i=0,n=list.size(); i<n; i++)
			{
				FDFBUserRoleVo urv = (FDFBUserRoleVo)list.get(i);
				sb.append("'").append(urv.getRoleid()).append("'").append(",");
			}
    		String result = sb.toString();
    		if(result.endsWith(","))
    		{
    			result = result.substring(0,result.length()-1);
    		}
    		return result;
    	}
    }
}
