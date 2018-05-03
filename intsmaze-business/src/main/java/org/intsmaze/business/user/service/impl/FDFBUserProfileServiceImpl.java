package org.intsmaze.business.user.service.impl;

import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.user.service.IUserProfileService;
import org.intsmaze.business.user.vo.FDFBUserProfileVo;
import org.intsmaze.core.util.DateUtil;
import org.intsmaze.persistence.dao.FDFBUserProfileDao;
import org.intsmaze.persistence.pojos.FDFBUserProfile;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBUserProfileServiceImpl extends CommonService implements IUserProfileService{

	@Autowired
	FDFBUserProfileDao userProfileDao;
	
	 
	public FDFBUserProfileVo getUserProfileByUserid(String userid) {
		
		FDFBUserProfile up = userProfileDao.getUserProfileByUserid(userid);
		if(up == null)
		{
			return null;
		}
		else
		{
			FDFBUserProfileVo upv = new FDFBUserProfileVo();
			return (FDFBUserProfileVo)copyBean(up, upv);
		}
	}

	 
	public int saveOrUpdateUserProfile(FDFBUserProfileVo upv) {
		// TODO Auto-generated method stub
		FDFBUserProfile up = new FDFBUserProfile();
		copyBean(upv, up);
		if(null==up.getSeqno() || "".equals(up.getSeqno().trim()))
		{
			up.setCreateon(DateUtil.getTimestamp());
			up.setModifyon(DateUtil.getTimestamp());
			return userProfileDao.insert(up);
		}
		else
		{
			up.setModifyon(DateUtil.getTimestamp());
			up.setCreateby("");
			up.setCreateon(null);
			return userProfileDao.updateByPrimaryKeySelective(up);
		}
	}

}
