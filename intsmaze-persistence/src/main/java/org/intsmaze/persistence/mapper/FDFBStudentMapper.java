package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBStudent;

public interface FDFBStudentMapper {

	List selectAllByPage(FDFBStudent user);
	    
	int getAllUsersCount(FDFBStudent user);
}