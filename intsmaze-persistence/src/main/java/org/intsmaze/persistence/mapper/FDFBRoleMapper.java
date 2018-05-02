package org.intsmaze.persistence.mapper;

import java.util.List;
import java.util.Map;

import org.intsmaze.persistence.pojos.FDFBRole;
import org.intsmaze.persistence.pojos.FDFBUser;

public interface FDFBRoleMapper {

	List selectAllByPage(FDFBRole record);

	List selectAll();

	int getAllFDFBRolesCount(FDFBRole record);
	
	List selectRolesByRoleIds(FDFBRole record);

	List<Map> selectUserList(Map mapBean);
}