/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-18 上午8:55:10
 * @version :
 */

package org.intsmaze.business.user.service;

import java.util.List;
import java.util.Map;
import org.intsmaze.business.user.vo.FDFBRolePermitVo;

public interface IRolePermitService {

	public void addRolePermit(FDFBRolePermitVo frp);
	 
	public void delRolePermit(String seqno);
	
	public void delRolePermitByRoleId(String roleid);
	
	public List getRolePermitListByRoleid(String roleid);
	
	public void updateRolePermitByRoleid(String roleid, String allPermit, Map userInfo);
	
}
