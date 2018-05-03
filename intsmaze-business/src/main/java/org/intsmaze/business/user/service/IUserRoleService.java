package org.intsmaze.business.user.service;

import java.util.List;

import org.intsmaze.business.user.vo.FDFBUserRoleVo;

public interface IUserRoleService {
	
	public List<FDFBUserRoleVo> getRolesByUserid(String userid);
	
	public int deleteUserRolesByUserid(String userid);
	
	public int addUserRole(FDFBUserRoleVo record);

	public int getCountByUseridAndRoleid(String userid, String roleid);
}
