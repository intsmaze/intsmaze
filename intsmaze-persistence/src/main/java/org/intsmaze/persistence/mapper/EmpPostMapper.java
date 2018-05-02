package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.EmpPost;

public interface EmpPostMapper {
	
	List selectAllByPage(EmpPost empPost);
	
	void setMainPost(String empInnerCoding);
}