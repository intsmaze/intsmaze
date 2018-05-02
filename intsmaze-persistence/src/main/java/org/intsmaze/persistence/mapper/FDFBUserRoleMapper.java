package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBUserRole;

public interface FDFBUserRoleMapper {
    List getRolesByUserid(FDFBUserRole record);
    
    int deleteUserRolesByUserid(String userid);
    
    int getCountByUseridAndRoleid(String userid, String roleid);
}