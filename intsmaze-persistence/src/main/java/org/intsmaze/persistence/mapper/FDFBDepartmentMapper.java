package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBDepartment;

public interface FDFBDepartmentMapper {
    
    List selectAllByPage(FDFBDepartment record);

	List selectAll();

	int getAllFDFBDepsCount(FDFBDepartment record);
}