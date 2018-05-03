package org.intsmaze.business.user.service;

import org.intsmaze.business.user.vo.FDFBUserProfileVo;

public interface IUserProfileService {
	
	public FDFBUserProfileVo getUserProfileByUserid(String userid);
	
	public int saveOrUpdateUserProfile(FDFBUserProfileVo upv);

}
