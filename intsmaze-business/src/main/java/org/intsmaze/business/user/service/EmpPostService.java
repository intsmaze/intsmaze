package org.intsmaze.business.user.service;

import java.util.List;

import org.intsmaze.persistence.pojos.EmpPost;

public interface EmpPostService {

	void saveEmpPostList(List<EmpPost> empPostList);
	
	public void setMain(String empInnerCoding);
	
}
