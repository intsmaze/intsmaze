package org.intsmaze.business.user.service.impl;

import java.util.List;

import org.intsmaze.business.user.service.EmpPostService;
import org.intsmaze.persistence.dao.EmpPostDao;
import org.intsmaze.persistence.pojos.EmpPost;
import org.springframework.beans.factory.annotation.Autowired;

public class EmpPostServiceImpl implements EmpPostService{
	
	@Autowired
	EmpPostDao empPostDao;
	
	public void saveEmpPostList(List<EmpPost> empPostList){
		for(EmpPost e:empPostList){
			
			EmpPost empPost = new EmpPost();
			empPost.setEmpInnerCoding(e.getEmpInnerCoding());
			empPost.setPostInnerCoding(e.getPostInnerCoding());
			
			List list = empPostDao.selectAllByPage(empPost);
			
			if(list.size() < 1){
				
				if( "1".equals(e.getMainPost()) ){
					empPostDao.setMainPost(e.getEmpInnerCoding());
				}
				
				empPostDao.insert(e);
			}else{
				empPost = (EmpPost) list.get(0);
				empPost.setStatus(e.getStatus());
				empPost.setMainPost(e.getMainPost());
				
				empPostDao.updateByPrimaryKey(empPost);
			}
		}
	}
	
	public void setMain(String empInnerCoding){
		empPostDao.setMainPost(empInnerCoding);
	}
}
