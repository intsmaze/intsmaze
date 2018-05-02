package org.intsmaze.persistence.mapper;

import java.util.List;

public interface FDFBRolePermitMapper {

	List selectAll();
    
    int delRolePermitByRoleId(String roleid);
    
    List getRolePermitListByRoleid(String roleid);
}