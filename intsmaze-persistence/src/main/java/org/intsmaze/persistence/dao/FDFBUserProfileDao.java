package org.intsmaze.persistence.dao;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBUserProfileMapper;
import org.intsmaze.persistence.pojos.FDFBUserProfile;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBUserProfileDao extends BaseDao<FDFBUserProfile> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4703575188260820798L;

	@Autowired
	FDFBUserProfileMapper userProfileMapper;

	public FDFBUserProfile getUserProfileByUserid(String userid) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		FDFBUserProfile up = userProfileMapper.getUserProfileByUserid(userid);
		return up;
	}
}
