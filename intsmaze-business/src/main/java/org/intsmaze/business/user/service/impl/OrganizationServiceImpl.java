package org.intsmaze.business.user.service.impl;

import java.util.List;

import org.intsmaze.business.user.service.OrganizationService;
import org.intsmaze.persistence.dao.OrganizationDao;
import org.intsmaze.persistence.pojos.Organization;
import org.springframework.beans.factory.annotation.Autowired;

public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	OrganizationDao organizationDao;
	
	 
	public void saveOrgList(List<Organization> orgList){
		
		for(Organization o:orgList){
			
			Organization org = new Organization();
			org.setOrgInnerCoding(o.getOrgInnerCoding());
			
			List list = organizationDao.selectAll(org);
			
			if(list.size() < 1){
				organizationDao.insert(o);
			}else{
				org = (Organization) list.get(0);
				o.setSeqno(org.getSeqno());
				
				organizationDao.updateByPrimaryKeySelective(o);
			}
			
			
		}
		
	}
	
	 
	public List selectAll(Organization organization){
		return organizationDao.selectAll(organization);
	}

}
