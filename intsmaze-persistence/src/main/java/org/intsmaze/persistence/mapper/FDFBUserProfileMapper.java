package org.intsmaze.persistence.mapper;

import org.intsmaze.persistence.pojos.FDFBUserProfile;

public interface FDFBUserProfileMapper {
	
	public FDFBUserProfile getUserProfileByUserid(String userid);
}