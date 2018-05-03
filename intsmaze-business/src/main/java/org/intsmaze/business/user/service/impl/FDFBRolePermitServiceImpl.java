package org.intsmaze.business.user.service.impl;

import java.util.List;
import java.util.Map;

import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.user.service.IRolePermitService;
import org.intsmaze.business.user.vo.FDFBRolePermitVo;
import org.intsmaze.core.exception.FDFBRuntimeException;
import org.intsmaze.core.util.Constant;
import org.intsmaze.persistence.dao.FDFBRolePermitDao;
import org.intsmaze.persistence.pojos.FDFBRolePermit;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBRolePermitServiceImpl extends CommonService implements IRolePermitService {

	@Autowired
	FDFBRolePermitDao rolePermitDao;
	
	 
	public void addRolePermit(FDFBRolePermitVo frp) {
		// TODO Auto-generated method stub
		FDFBRolePermit frp1 = new FDFBRolePermit();
		frp1 = (FDFBRolePermit)copyBean(frp, frp1);
		rolePermitDao.insert(frp1);
	}

	 
	public void delRolePermit(String seqno) {
		// TODO Auto-generated method stub
		rolePermitDao.deleteByPrimaryKey(seqno);
	}

	 
	public void delRolePermitByRoleId(String roleid) {
		// TODO Auto-generated method stub
		rolePermitDao.delRolePermitByRoleId(roleid);
	}
	
	public List getRolePermitListByRoleid(String roleid)
	{
		return rolePermitDao.getRolePermitListByRoleid(roleid);
	}

	 
	public void updateRolePermitByRoleid(String roleid, String allPermit, Map userInfo) {
		// TODO Auto-generated method stub
		String[] permitArray = allPermit.split(";");
		if(permitArray.length > 0)
		{
			rolePermitDao.delRolePermitByRoleId(roleid);
			for(int i=0,n=permitArray.length; i<n; i++)
			{
				FDFBRolePermitVo rpv = new FDFBRolePermitVo();
				rpv.setRoleid(roleid);
				rpv.setPermitid(permitArray[i]);
				rpv.setCreateby((String)userInfo.get(Constant.USER_NAME_SESSION));
				rpv.setModifyby((String)userInfo.get(Constant.USER_NAME_SESSION));
				addRolePermit(rpv);
			}
		}
//		throw new FDFBRuntimeException("test transaction manager");//transaction test success!
	}

}
