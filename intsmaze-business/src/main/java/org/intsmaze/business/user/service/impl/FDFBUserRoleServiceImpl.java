package org.intsmaze.business.user.service.impl;

import java.util.List;

import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.user.service.IUserRoleService;
import org.intsmaze.business.user.vo.FDFBUserRoleVo;
import org.intsmaze.persistence.dao.FDFBUserRoleDao;
import org.intsmaze.persistence.pojos.FDFBUserRole;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBUserRoleServiceImpl extends CommonService implements IUserRoleService{

	@Autowired
	private FDFBUserRoleDao userRoleDao;
	
	 
	public List<FDFBUserRoleVo> getRolesByUserid(String userid) {
		// TODO Auto-generated method stub
		return copyList(userRoleDao.getRolesByUserid(userid), FDFBUserRole.class, FDFBUserRoleVo.class) ;
		
	}

	 
	public int deleteUserRolesByUserid(String userid) {
		// TODO Auto-generated method stub
		return userRoleDao.deleteUserRolesByUserid(userid);
	}

	 
	public int addUserRole(FDFBUserRoleVo record) {
		// TODO Auto-generated method stub
		FDFBUserRole ur = new FDFBUserRole();
		return userRoleDao.insert((FDFBUserRole)copyBean(record, ur));
	}

	 
	public int getCountByUseridAndRoleid(String userid, String roleid) {
		// TODO Auto-generated method stub
		return userRoleDao.getCountByUseridAndRoleid(userid, roleid);
	}

}
