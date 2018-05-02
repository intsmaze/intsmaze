package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.Organization;

public interface OrganizationMapper {
	List selectAll(Organization organization);
	
	
}