package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.OrganizationMapper;
import org.intsmaze.persistence.pojos.Organization;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class OrganizationDao extends BaseDao<Organization> {
	
	@Autowired
	OrganizationMapper organizationMapper;
	
	public List selectAll(Organization organization){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return organizationMapper.selectAll(organization);
	}
	
}
