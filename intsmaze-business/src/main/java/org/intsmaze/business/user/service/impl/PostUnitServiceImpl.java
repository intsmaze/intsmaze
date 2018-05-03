package org.intsmaze.business.user.service.impl;

import java.util.List;

import org.intsmaze.business.user.service.PostUnitService;
import org.intsmaze.persistence.dao.PostUnitDao;
import org.intsmaze.persistence.pojos.PostUnit;
import org.springframework.beans.factory.annotation.Autowired;

public class PostUnitServiceImpl implements PostUnitService {

	@Autowired
	PostUnitDao postUnitDao;
	
	 
	public void savePostUnitList(List<PostUnit> postUnitList) {
		for(PostUnit p:postUnitList) {
			
			PostUnit postUnit = new PostUnit();
			postUnit.setPostInnerCoding(p.getPostInnerCoding());
			postUnit.setUnitInnerCoding(p.getUnitInnerCoding());
			
			List list = postUnitDao.selectAllByPage(postUnit);
			
			if(list.size() < 1){
				postUnitDao.insert(p);
			}else{
				postUnit = (PostUnit) list.get(0);
				postUnit.setStatus(p.getStatus());
				
				postUnitDao.updateByPrimaryKey(postUnit);
				
			}
			
		}
		
	}

}
