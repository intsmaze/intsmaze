package org.intsmaze.business.user.service;

import java.util.List;

import org.intsmaze.persistence.pojos.Organization;

public interface OrganizationService {

	void saveOrgList(List<Organization> orgList);
	
	public List selectAll(Organization organization);
	
}
