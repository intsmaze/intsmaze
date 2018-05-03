package org.intsmaze.business.user.service;

import java.util.List;
import java.util.Map;

import org.intsmaze.business.user.vo.FDFBRoleVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface IRoleService {

	public int getAllFDFBRolesCount(FDFBRoleVo user);

	@CacheEvict(key="'AllRoleList'", value = "FDFB.application.cache")//新增role后清空AllRoleList缓存
	public void addFDFBRole(FDFBRoleVo user);

	public List selectAllByPage(FDFBRoleVo user);

	public FDFBRoleVo selectByPrimaryKey(String seqno);
	
	public FDFBRoleVo updateRoleDynamic(FDFBRoleVo record);
	
	@Cacheable(value="FDFB.application.cache",key="'AllRoleList'")
	public List selectAll();
	
	public List<FDFBRoleVo> selectRolesByRoleIds(String roleIds);
	
	public int deleteUserBySeqno(String seqno);

	public List<Map> selectUserList(Map mapBean);

}
